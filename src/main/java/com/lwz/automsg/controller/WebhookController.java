package com.lwz.automsg.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.lwz.automsg.service.SelectDingTemp;
import com.lwz.automsg.service.SelectTemp;
import com.lwz.automsg.utils.*;
import com.taobao.api.ApiException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.IOException;
import java.util.Arrays;
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

    @Autowired
    SelectTemp selectTemp;

    @Autowired
    SelectDingTemp selectDingTemp;
    /**
     * 语雀-飞书
     */
    @PostMapping("/yuque")
    public String redirect(@RequestBody Object req) throws IOException {

        System.out.println("一条请求进入方法");
//        https://open.feishu.cn/open-apis/bot/v2/hook/d2659769-1583-4b6e-97be-fce221e40668
        String WEBHOOK_TOKEN = "https://open.feishu.cn/open-apis/bot/v2/hook/c3bc22de-e95e-4289-95bd-5d6f6b3b94ed";
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
        String richTextMsg=selectTemp.tempAdapter(s,imgKey);
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
    /**
     * tapd-飞书接口
     */
    @PostMapping("/tapd")
    public String redirectTAPD(@RequestBody Object req) throws IOException {
        String URL = "https://api.tapd.cn/quickstart/testauth";
        HttpClient httpclient = HttpClients.createDefault();
        HttpGet request = new HttpGet(URL);
        request.addHeader("Content-Type", "application/json; charset=utf-8");
        request.addHeader("Authorization","Basic RnFLV1ZjM1Q6MTI2RkYzMDAtM0JGNS1FRDU3LTVCM0EtRjUyRkJCQzM0M0Ex");
        String key_word="项目更新";
        String s = JacksonUtil.toJSON(req);
        System.out.println(s);
        HttpResponse execute = httpclient.execute(request);
        System.out.println(EntityUtils.toString(execute.getEntity(),"UTF-8"));

        return "";
    }

    /**
     * 语雀- 钉钉
     * */
    @PostMapping("/yuqueding")
    public String yuQueDing(@RequestBody Object req) throws ApiException, IOException {
        String s = JacksonUtil.toJSON(req);
        System.out.println(s);
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=a6e4cf7618bbd1361dce83ee238b194e624527be349a0730634572bc5c551ae7");
        OapiRobotSendRequest request;
        request = selectDingTemp.tempAdapterForDing(s);
//        request.setMsgtype("text");
//        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
//        text.setContent("测试文本消息");
//        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList("18846558370"));
        at.setIsAtAll(false);
        request.setAt(at);
//
//        request.setMsgtype("link");
//        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
//        link.setMessageUrl("https://www.dingtalk.com/");
//        link.setPicUrl("");
//        link.setTitle("时代的火车向前开");
//        link.setText("这个即将发布的新版本，创始人xx称它为红树林。而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是红树林");
//        request.setLink(link);
//
//        request.setMsgtype("markdown");
//        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
//        markdown.setTitle("杭州天气");
//        markdown.setText("#### 杭州天气 @156xxxx8827\n" +
//                "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
//                "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n"  +
//                "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
//        request.setMarkdown(markdown);
        System.out.println("发送消息！");
        OapiRobotSendResponse response = client.execute(request);
        return "";
        //haha嗷嗷
    }

    /**
     * gitee- 飞书
     * */

}

