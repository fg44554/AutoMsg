package com.lwz.automsg.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.lwz.automsg.utils.ImgTagUtils;
import com.lwz.automsg.utils.ResolveUrlUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class SelectDingTemp {


    /**
     * 抽取常用的第一层jsonKey减少代码冗余
     */
    private JSONObject getJson(String json) {
        return (JSONObject) JSON.parse(((JSONObject) JSON.parse(json)).getString("data"));
    }

    /**
     * 适配器
     */
    public OapiRobotSendRequest tempAdapterForDing(String json) throws IOException {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("actionCard");
        OapiRobotSendRequest.Actioncard actioncard = new OapiRobotSendRequest.Actioncard();
        actioncard.setBtnOrientation("1");
        List<String> s= ImgTagUtils.listImgSrc(getJson(json).getString("body_html"));
        actioncard.setText("> ![screenshot]("+s.get(0)+")\n");
        if (getJson(json).getString("published_at").equals(getJson(json).getString("first_published_at"))) {
            System.out.println("事件：首次发布，创建文档，文案：xxx公开了新的知识");
            //返回jsonTemp中的模板
            actioncard.setSingleTitle(((JSONObject) JSON.parse(getJson(json).getString("user"))).getString("name") + "发布了一篇文章");
            actioncard.setSingleURL("https://www.yuque.com/" + getJson(json).getString("path"));
            request.setActionCard(actioncard);
            return request;
        } else if ("delete".equals(getJson(json).getString("action_type"))) {
            actioncard.setSingleTitle(((JSONObject) JSON.parse(getJson(json).getString("user"))).getString("name") + "删除了一篇文章");
            request.setActionCard(actioncard);
            return request;
        } else if ("update".equals(getJson(json).getString("action_type"))) {
            actioncard.setSingleTitle(((JSONObject) JSON.parse(getJson(json).getString("user"))).getString("name") + "更新了一篇文章");
            request.setActionCard(actioncard);
            return request;
        }
        return request;

    }
}