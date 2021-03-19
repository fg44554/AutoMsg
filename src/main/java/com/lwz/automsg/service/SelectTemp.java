package com.lwz.automsg.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lwz.automsg.DTO.*;
import com.lwz.automsg.enumClass.JsonTemp;
import com.lwz.automsg.utils.GetChatIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
     * @author lwz
     */
    @Service
    public class SelectTemp {
        @Autowired
        JsonTemp jsonTemp;
        @Autowired
        GetChatIdUtil getChatIdUtil;
        /**
         * 抽取常用的第一层jsonKey减少代码冗余
         */
        private JSONObject getJson(String json){
            return  (JSONObject) JSON.parse(((JSONObject) JSON.parse(json)).getString("data"));
        }
        /**
         * @param json controller层传的第一层jsonKey,消息适配器
         */
        public String tempAdapter(String json,String imgKey) throws IOException {
            if (getJson(json).getString("published_at").equals(getJson(json).getString("first_published_at"))) {
                System.out.println("事件：首次发布，创建文档，文案：xxx公开了新的知识");
                //返回jsonTemp中的模板
                return jsonTemp.createNoticeCard(jsonToBean(getJson(json),imgKey));
            }else if("delete".equals(getJson(json).getString("action_type"))){
                return jsonTemp.deleteNoticeCard(jsonToBean(getJson(json),imgKey));
            }else if("update".equals(getJson(json).getString("action_type"))){
                return jsonTemp.updateNoticeCard(jsonToBean(getJson(json),imgKey));
            }
            return "";
        }

        //:todo 封装json
        public JsonDTO jsonToBean(JSONObject jsonObject,String imgKey) throws IOException {
            JsonDTO jsonDTO = new JsonDTO();
            DataDTO dataDTO = new DataDTO();
            RootDTO rootDTO = new RootDTO();
            UserDTO userDTO = new UserDTO();
            BookDTO bookDTO = new BookDTO();
            String title = jsonObject.getString("title");
            String description = jsonObject.getString("description");
            String user = jsonObject.getString("user");
            String bodyHtml = jsonObject.getString("bodyHtml");
            JSONObject userParse = (JSONObject)JSONObject.parse(user);
            String name = userParse.getString("name");
            String path = jsonObject.getString("path");
            userDTO.setName(name);
            dataDTO.setTitle(title);
            dataDTO.setPath(path);
            bookDTO.setDescription(description);
            dataDTO.setBodyHtml(bodyHtml);
            jsonDTO.setCharId(getChatIdUtil.getChatId());
            jsonDTO.setImageKey(imgKey);
            jsonDTO.setBookDTO(bookDTO);
            jsonDTO.setDataDTO(dataDTO);
            jsonDTO.setRootDTO(rootDTO);
            jsonDTO.setUserDTO(userDTO);
            return jsonDTO;
        }
}
