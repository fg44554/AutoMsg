package com.lwz.automsg.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.lwz.automsg.enumClass.JsonTemp;
import com.lwz.automsg.utils.*;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@Controller
public class WebhookController {

    @Autowired
    ChangePathUtil changePathUtil;

    @Autowired
    SendImageUtil sendImageUtil;

    @Autowired
    GetChatIdUtil getChatIdUtil;

    @PostMapping("/yuque")
    public String redirect(@RequestBody Object req) throws IOException {

        System.out.println("一条请求进入方法");

        String WEBHOOK_TOKEN = "https://open.feishu.cn/open-apis/bot/v2/hook/310a67b9-4b33-4701-8285-fa8adede8ed5";
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        String key_word="项目更新";
        String s = JacksonUtil.toJSON(req);
        System.out.println(s);
        //获取"result"中的json字符串
        JSONObject parse = (JSONObject) JSON.parse(s);
        //获取字符数组

        String data = parse.getString("data");
        JSONObject dataParse = (JSONObject) JSON.parse(data);
        String book = dataParse.getString("book");
        JSONObject bookParse = (JSONObject) JSON.parse(book);
        String user = dataParse.getString("user");
        JSONObject userParse = (JSONObject) JSON.parse(user);
        String body_html = dataParse.getString("body_html");
        String description = bookParse.getString("description");
        String bookTitle = bookParse.getString("name");
        String title = dataParse.getString("title");
        String name = userParse.getString("name");
        String url = dataParse.getString("path");
        String body = dataParse.getString("body_html");

        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        ResolveUrlUtil resolveUrlUtil = new ResolveUrlUtil();
        List<String> img = resolveUrlUtil.imgs(body_html);
        UUID uuid=null;
        String imgName="";
        for(String imgUrl:img){
            //用uuid来生成随机文件名
            uuid = UUID.randomUUID();
            imgName = uuid.toString();
            //将文件down到本地
            if (os != null && os.toLowerCase().contains("linux")) {
                DownImageUtil.downloadImg(imgUrl,
                        changePathUtil.linuxFilePath,
                        imgName +".jpg");
                System.out.println(imgUrl);
            } else {
                DownImageUtil.downloadImg(imgUrl,
                        changePathUtil.windowsFilePath,
                        imgName +".jpg");
                System.out.println(imgUrl);
            }

        }
        //获取飞书端imgKey
        String imgKey = sendImageUtil.sendImg(imgName);
        System.out.println(imgKey);

        //构建一个json格式字符串textMsg，其内容是接收方需要的参数和消息内容
        String richTextMsg= JsonTemp.msgCard(getChatIdUtil.getChatId(),title,imgKey,description,url,name);
        StringEntity se = new StringEntity(richTextMsg, "utf-8");
        httppost.setEntity(se);
        System.out.println(richTextMsg);
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
        return "hello";
    }
    @RequestMapping("/test")
    public String test(){
        System.out.println("这是一个测试！");
        return "hello";
    }
    @RequestMapping("getOS")
    public void osName(){
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        System.out.println(os);
    }

}
