package com.lwz.automsg;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.lwz.automsg.DTO.BookDTO;
import com.lwz.automsg.DTO.JsonDTO;
//import lombok.extern.slf4j.Slf4j;

import com.lwz.automsg.utils.ImgTagUtils;
import com.lwz.automsg.utils.JacksonUtil;

import com.lwz.automsg.utils.ResolveUrlUtil;
import com.taobao.api.ApiException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest

class AutomsgApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){

    }

    public static void main(String[] args) throws IOException, ApiException {

//        String URL = "https://api.tapd.cn/quickstart/testauth";
//        HttpClient httpclient = HttpClients.createDefault();
//        HttpGet request = new HttpGet(URL);
//        request.addHeader("Content-Type", "application/json; charset=utf-8");
//        request.addHeader("Authorization","Basic RnFLV1ZjM1Q6MTI2RkYzMDAtM0JGNS1FRDU3LTVCM0EtRjUyRkJCQzM0M0Ex");
//        String key_word="项目更新";
//        HttpResponse execute = httpclient.execute(request);
//
//        System.out.println("请求头"+EntityUtils.toString(execute.getEntity(),"UTF-8"));

        List<String> s= ImgTagUtils.listImgSrc("<!doctype html><div class=\"lake-content\" typography=\"classic\"><p class=\"ne-p\"><strong><span class=\"ne-text\">1.要实现一个什么系统/功能？</span></strong></p><p class=\"ne-p\"><span class=\"ne-text\">基于webhook（网络钩子技术）通过特定事件，如语雀知识库更新事件、发布事件、评论新增、评论删除等，tapd如新需求发布、需求进展更新发送等，在飞书客户端群聊通过自定义机器人响应文本、富文本、图片等消息，做到实时提醒大家需求的进展或改动，每个人完成的进度等，或是更好更便捷的分享语雀知识库中的知识资源。</span></p><p class=\"ne-p\"><span class=\"ne-text\">通过增加适配器的方法来控制不同的应用去对接飞书，希望不只是实现一个桥梁的功能，还具有中继器/适配器的特性特点，做到能随应用的改变，简单配置过后就可以轻松对接。</span></p><p class=\"ne-p\"><span class=\"ne-text\">在飞书客户端做一些交互式的操作，比如通过@机器人，可以查询一些想得到的知识。</span></p><p class=\"ne-p\"><strong><span class=\"ne-text\">2.这个项目的意义</span></strong></p><p class=\"ne-p\"><span class=\"ne-text\">提高工作效率，监控工作进展，增进知识储备交流与分享。</span></p><p class=\"ne-p\"><strong><span class=\"ne-text\">3.实现的手段，思路想法</span></strong></p><p class=\"ne-p\"><span class=\"ne-text\">在初步搭好框架后，整个项目有了一个具体的运行链路，首先要做的是在飞书创建一个自定义机器人，飞书端会生成一个回调url，其他支持webhook的产品通过特定事件触发回调url来实现消息的发送，第二部要做的是把自己项目的地址暴露在外网，让语雀或tapd可以访问，搭建好这些之后在语雀或tapd中模拟消息请求，第三步在程序中先对语雀或tapd发过来的json进行解析，若果是图片则要先down到本地，在上传到飞书服务器，飞书返回一个唯一图片识别码再通过项目中转填入到要发送的json中，通过httpclient.execute(httppost)</span><span class=\"ne-text\" style=\"color: #CC7832\">;</span><span class=\"ne-text\">发送消息。</span></p><p class=\"ne-p\"><strong><span class=\"ne-text\">4.实现细节（语雀）</span></strong></p><p class=\"ne-p\"><img src=\"https://cdn.nlark.com/yuque/0/2021/png/12649111/1615772985378-6b8449ef-82cc-4314-ab5a-477329a85e52.png\" width=\"320\"></p><p class=\"ne-p\" style=\"text-indent: 2em\"><span class=\"ne-text\">1.在飞书中创建自定义机器人，获取webhookURL</span></p><p class=\"ne-p\" style=\"text-indent: 2em\"><img src=\"https://cdn.nlark.com/yuque/0/2021/png/12649111/1615772283865-d782be83-f45c-42ea-8860-d4b1467c7f9a.png\" width=\"609\"></p><p class=\"ne-p\" style=\"text-indent: 2em\"><span class=\"ne-text\">2.当语雀发生特定事件时，调用上述获得的wenhookURL在程序中对飞书进行连接，使飞书能够对语雀的事件进行响应：</span></p><pre data-language=\"java\" style=\"border: 1px solid #e8e8e8; border-radius: 2px; background: #f9f9f9; padding: 16px; font-size: 13px; color: #595959\">String WEBHOOK_TOKEN = &quot;feishuWebhookUrl&quot;;<br />HttpClient httpclient = HttpClients.createDefault();<br />HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);<br />httppost.addHeader(&quot;Content-Type&quot;, &quot;application/json; charset=utf-8&quot;);<br />String richTextMsg = &quot;json&quot;;<br />StringEntity se = new StringEntity(richTextMsg, &quot;utf-8&quot;);<br />httppost.setEntity(se);<br />HttpResponse response = httpclient.execute(httppost);</pre><p class=\"ne-p\" style=\"margin-left: 2em\"><span class=\"ne-text\">3.通过工具ngrok，将项目的请求网址暴露在外网</span></p><p class=\"ne-p\" style=\"margin-left: 2em\"><img src=\"https://cdn.nlark.com/yuque/0/2021/png/12649111/1615772056928-42d7cd9e-663f-41a8-8978-f80436d50f17.png\" width=\"424.5\"></p><p class=\"ne-p\" style=\"margin-left: 2em\"><span class=\"ne-text\">4.在语雀端配置好已暴露在外网的请求链接，并选择特定的触发事件</span></p><p class=\"ne-p\" style=\"margin-left: 2em\"><img src=\"https://cdn.nlark.com/yuque/0/2021/png/12649111/1615772400301-a64772b2-e6f0-4e84-afa8-4f53d6160b07.png\" width=\"617.5\"></p><p class=\"ne-p\" style=\"margin-left: 2em\"><span class=\"ne-text\">5.触发事件，向飞书发送消息</span></p><p class=\"ne-p\" style=\"margin-left: 2em; text-indent: 2em\"><span class=\"ne-text\">语雀会使用 HTTP POST 请求 </span><span class=\"ne-text\">Webhooks</span><span class=\"ne-text\"> URL，具体的 body 是一个 JSON 数据结构.</span></p><p class=\"ne-p\" style=\"margin-left: 2em; text-indent: 2em\"><span class=\"ne-text\">（1）文本类消息</span></p><p class=\"ne-p\" style=\"margin-left: 4em; text-indent: 2em\"><span class=\"ne-text\">请求的消息体：</span></p><pre data-language=\"json\" style=\"border: 1px solid #e8e8e8; border-radius: 2px; background: #f9f9f9; padding: 16px; font-size: 13px; color: #595959\">{<br />    &quot;msg_type&quot;: &quot;text&quot;,<br />    &quot;content&quot;: {<br />        &quot;text&quot;: &quot;新更新提醒&quot;<br />    }<br />} </pre><p class=\"ne-p\"><br></p><p class=\"ne-p\" style=\"margin-left: 4em\"><span class=\"ne-text\">（2）富文本类消息</span></p><p class=\"ne-p\" style=\"margin-left: 4em; text-indent: 2em\"><span class=\"ne-text\">富文本类消息在文本消息的基础上，可以对标题进行加粗加黑，可划分段落，可设置超链接</span></p><p class=\"ne-p\" style=\"margin-left: 4em; text-indent: 2em\"><span class=\"ne-text\">请求的消息体</span></p><pre data-language=\"json\" style=\"border: 1px solid #e8e8e8; border-radius: 2px; background: #f9f9f9; padding: 16px; font-size: 13px; color: #595959\">{<br />    &quot;msg_type&quot;: &quot;post&quot;,<br />    &quot;content&quot;: {<br />        &quot;post&quot;: {<br />            &quot;zh_cn&quot;: {<br />                &quot;title&quot;: &quot;项目更新通知&quot;,<br />                &quot;content&quot;: [<br />                    [<br />                        {<br />                            &quot;tag&quot;: &quot;text&quot;,<br />                            &quot;text&quot;: &quot;项目有更新: &quot;<br />                        },<br />                        {<br />                            &quot;tag&quot;: &quot;a&quot;,<br />                            &quot;text&quot;: &quot;请查看&quot;,<br />                            &quot;href&quot;: &quot;http://www.example.com/&quot;<br />                        }<br />                    ]<br />                ]<br />            }<br />        }<br />    }<br />}</pre><p class=\"ne-p\" style=\"text-indent: 2em; margin-left: 2em\"><span class=\"ne-text\">（3）发送群名片</span></p><p class=\"ne-p\" style=\"text-indent: 2em; margin-left: 4em\"><span class=\"ne-text\">请求的消息体</span></p><pre data-language=\"json\" style=\"border: 1px solid #e8e8e8; border-radius: 2px; background: #f9f9f9; padding: 16px; font-size: 13px; color: #595959\">{<br />    &quot;msg_type&quot;: &quot;share_chat&quot;,<br />    &quot;content&quot;:{<br />        &quot;share_chat_id&quot;: &quot;oc_f5b1a7eb27ae2c7b6adc2a74faf339ff&quot;<br />    }<br />} </pre><p class=\"ne-p\" style=\"text-indent: 2em; margin-left: 2em\"><span class=\"ne-text\">（4）发送图片</span></p><p class=\"ne-p\" style=\"margin-left: 6em\"><span class=\"ne-text\">请求的消息体</span></p><pre data-language=\"json\" style=\"border: 1px solid #e8e8e8; border-radius: 2px; background: #f9f9f9; padding: 16px; font-size: 13px; color: #595959\">{<br />    &quot;msg_type&quot;:&quot;image&quot;,<br />    &quot;content&quot;:{<br />        &quot;image_key&quot;: &quot;img_ecffc3b9-8f14-400f-a014-05eca1a4310g&quot;<br />    }<br />}</pre><p class=\"ne-p\" style=\"text-indent: 2em; margin-left: 4em\"><span class=\"ne-text\">发送图片的具体细节：</span></p><p class=\"ne-p\" style=\"text-indent: 2em; margin-left: 6em\"><span class=\"ne-text\">1.接收来自语雀的json并拿到文档中第一张图片的请求地址，需要在字符串中解析出图片url</span></p><pre data-language=\"java\" style=\"border: 1px solid #e8e8e8; border-radius: 2px; background: #f9f9f9; padding: 16px; font-size: 13px; color: #595959\">List&lt;String&gt; img = resolveUrlUtil.imgs(body_html);<br />StringBuffer stringBuffer = new StringBuffer();<br />for(String sImg:img){<br />    stringBuffer.append(sImg);<br />}<br />String imgUrl=stringBuffer.toString();</pre><p class=\"ne-p\" style=\"text-indent: 2em; margin-left: 6em\"><span class=\"ne-text\">2.拿到地址后使用工具类将此图片down到本地</span></p><pre data-language=\"java\" style=\"border: 1px solid #e8e8e8; border-radius: 2px; background: #f9f9f9; padding: 16px; font-size: 13px; color: #595959\">DownImageController.downImg(&quot;url&quot;,&quot;imagePath&quot;,&quot;imageName&quot;)</pre><p class=\"ne-p\" style=\"text-indent: 2em; margin-left: 6em\"><span class=\"ne-text\">3.使用工具类将图片上传到飞书服务器获取唯一的image_key</span></p><pre data-language=\"java\" style=\"border: 1px solid #e8e8e8; border-radius: 2px; background: #f9f9f9; padding: 16px; font-size: 13px; color: #595959\">File file = new File(&quot;imgPath&quot;);<br />String result = SendImageByApacheHttpClient(file);</pre><p class=\"ne-p\" style=\"margin-left: 8em\"><span class=\"ne-text\">4.请求成功后的响应体，成功拿到image_key</span></p><pre data-language=\"json\" style=\"border: 1px solid #e8e8e8; border-radius: 2px; background: #f9f9f9; padding: 16px; font-size: 13px; color: #595959\">{<br />\t&quot;code&quot;: 0,<br />\t&quot;data&quot;: {<br />\t\t&quot;image_key&quot;: &quot;fd3a475a-0c27-4071-a9a6-8712b84b0cb6&quot;<br />\t},<br />\t&quot;msg&quot;: &quot;ok&quot;<br />}</pre><p class=\"ne-p\" style=\"margin-left: 8em\"><span class=\"ne-text\">5.向飞书端发送文本、富文本、图片消息</span></p><pre data-language=\"java\" style=\"border: 1px solid #e8e8e8; border-radius: 2px; background: #f9f9f9; padding: 16px; font-size: 13px; color: #595959\">  String richTextMsg = &quot;{\\&quot;email\\&quot;:\\&quot;fanlv@bytedance.com\\&quot;,\\&quot;msg_type\\&quot;:\\&quot;post\\&quot;,&quot; +<br />                &quot;\\&quot;content\\&quot;:{\\&quot;post\\&quot;:{\\&quot;zh_cn\\&quot;:{\\&quot;title\\&quot;:\\&quot;我是一个标题\\&quot;,&quot; +<br />                &quot;\\&quot;content\\&quot;:[[{\\&quot;tag\\&quot;:\\&quot;text\\&quot;,\\&quot;un_escape\\&quot;:true,\\&quot;text\\&quot;:\\&quot;第一行&amp;nbsp;:\\&quot;},&quot; +<br />                &quot;{\\&quot;tag\\&quot;:\\&quot;a\\&quot;,\\&quot;text\\&quot;:\\&quot;超链接\\&quot;,\\&quot;href\\&quot;:\\&quot;http://www.feishu.cn\\&quot;},&quot; +<br />                &quot;{\\&quot;tag\\&quot;:\\&quot;at\\&quot;,\\&quot;user_id\\&quot;:\\&quot;ou_18eac85d35a26f989317ad4f02e8bbbb\\&quot;}],&quot; +<br />                &quot;[{\\&quot;tag\\&quot;:\\&quot;text\\&quot;,\\&quot;text\\&quot;:\\&quot;第二行 :\\&quot;},{\\&quot;tag\\&quot;:\\&quot;text\\&quot;,\\&quot;text\\&quot;:\\&quot;文本测试\\&quot;}],&quot; +<br />                &quot;[{\\&quot;tag\\&quot;:\\&quot;img\\&quot;,\\&quot;image_key\\&quot;:\\&quot;img_6a742745-9629-4175-8cf0-79b6f7cc781g\\&quot;,\\&quot;width\\&quot;:300,\\&quot;height\\&quot;:300}]]}}}}&quot;;</pre><p class=\"ne-p\"><strong><span class=\"ne-text\">5.飞书-语雀待完善功能迭代</span></strong></p><p class=\"ne-p\"><span class=\"ne-text\">字体看看能不能设置一下。</span></p><p class=\"ne-p\"><span class=\"ne-text\">当文档中未插入图片时，提供一个语雀的默认图片返回。</span></p><p class=\"ne-p\"><span class=\"ne-text\">测试关于评论的操作。</span></p><p class=\"ne-p\"><span class=\"ne-text\">bug：当我删除一篇文章时收到的提醒是创建了一篇文章</span></p><p class=\"ne-p\"><span class=\"ne-text\">bug：当我更新了一个带图片的文章时，图片处标题为null</span></p><p class=\"ne-p\"><span class=\"ne-text\">bug：当我创建或更新了一篇没有图片的文章时，空指针</span></p><p class=\"ne-p\"><span class=\"ne-text\">bug：tapd接口不通</span></p><p class=\"ne-p\"><strong><span class=\"ne-text\">6.新的想法和思路</span></strong></p><p class=\"ne-p\"><strong><span class=\"ne-text\">hubot，适配器</span></strong></p><p class=\"ne-p\"><strong><span class=\"ne-text\">增加飞书端的webhook调用可以实现交互的操作</span></strong></p><p class=\"ne-p\"><strong><span class=\"ne-text\">模式的切换：语雀端切换到tapd，可在外部对配置文件做出改变，动态更改appid等.........</span></strong></p><p class=\"ne-p\"><strong><span class=\"ne-text\"></span></strong></p><p class=\"ne-p\"><strong><span class=\"ne-text\">7.即将上线的功能：</span></strong></p><p class=\"ne-p\"><span class=\"ne-text\">搭建个人博客，基于git，对接webhook实现自动部署</span></p><p class=\"ne-p\"><span class=\"ne-text\">对接飞书与gitee</span></p><p class=\"ne-p\"><span class=\"ne-text\">对接飞书与tapd</span></p><p class=\"ne-p\"><strong><span class=\"ne-text\">8.项目进展</span></strong><span class=\"ne-text\">：</span></p><p class=\"ne-p\" style=\"text-indent: 2em\"><strong><span class=\"ne-text\">飞书-语雀待完善功能迭代</span></strong></p><p class=\"ne-p\" style=\"text-indent: 2em\"><span class=\"ne-text\">字体看看能不能设置一下。</span></p><p class=\"ne-p\" style=\"text-indent: 2em\"><span class=\"ne-text\">当文档中未插入图片时，提供一个语雀的默认图片返回。</span></p><p class=\"ne-p\" style=\"text-indent: 2em\"><span class=\"ne-text\">测试关于评论的操作。</span></p><p class=\"ne-p\" style=\"text-indent: 2em\"><span class=\"ne-text\">bug：当我删除一篇文章时收到的提醒是创建了一篇文章</span></p><p class=\"ne-p\" style=\"text-indent: 2em\"><span class=\"ne-text\">bug：当我更新了一个带图片的文章时，图片处标题为null</span></p><p class=\"ne-p\" style=\"text-indent: 2em\"><span class=\"ne-text\">bug：当我创建或更新了一篇没有图片的文章时，空指针</span></p><p class=\"ne-p\" style=\"text-indent: 2em\"><span class=\"ne-text\">bug：tapd接口不通</span></p><p class=\"ne-p\" style=\"margin-left: 2em\"><strong><span class=\"ne-text\">飞书-tapd待完善功能迭代</span></strong></p><p class=\"ne-p\" style=\"margin-left: 2em\"><strong><span class=\"ne-text\">已打通</span></strong></p><p class=\"ne-p\" style=\"margin-left: 2em\"><span class=\"ne-text\" style=\"color: #444444; font-size: 16px\">需求创建、需求更新、需求删除、缺陷创建、缺陷更新、缺陷删除、任务创建、任务更新、任务删除、发布评审创建、发布评审更新，这些模板。1231231231231231231231231231231231123123123123123123123123123123</span></p></div>");
        System.out.println(s.get(0));

        DingTalkClient client = new DefaultDingTalkClient("");
        OapiRobotSendRequest request = new OapiRobotSendRequest();
//        request.setText("{\n" +
//                "    \"at\": {\n" +
//                "        \"atMobiles\":[\n" +
//                "            \"13304855087\"\n" +
//                "        ],\n" +
//                "        \"atUserIds\":[\n" +
//                "            \"user123\"\n" +
//                "        ],\n" +
//                "        \"isAtAll\": false\n" +
//                "    },\n" +
//                "    \"text\": {\n" +
//                "        \"content\":\"我就是我, @13304855087 是不一样的烟火\"\n" +
//                "    },\n" +
//                "    \"msgtype\":\"text\"\n" +
//                "}");
//        request.setMsgtype("text");
//        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
//        text.setContent("{\n" +
//                "    \"at\": {\n" +
//                "        \"atMobiles\":[\n" +
//                "            \"1330855087\"\n" +
//                "        ],\n" +
//                "        \"atUserIds\":[\n" +
//                "            \"user123\"\n" +
//                "        ],\n" +
//                "        \"isAtAll\": false\n" +
//                "    },\n" +
//                "    \"text\": {\n" +
//                "        \"content\":\"我就是我, @lwz 是不一样的烟火\"\n" +
//                "    },\n" +
//                "    \"msgtype\":\"text\"\n" +
//                "}");
//        request.setText(text);
//        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//        at.setAtMobiles(Arrays.asList("17568071568"));
//// isAtAll类型如果不为Boolean，请升级至最新SDK
//        at.setIsAtAll(false);
////        at.setAtUserIds(Arrays.asList("109929","32099"));
//        request.setAt(at);

//        request.setMsgtype("link");
//        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
//        link.setMessageUrl("https://www.dingtalk.com/");
//        link.setPicUrl("");
//        link.setTitle("时代的火车向前开");
//        link.setText("这个即将发布的新版本，创始人xx称它为红树林。而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是红树林");
//        request.setLink(link);
//
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("杭州天气");
        markdown.setText("#### 杭州天气 @156xxxx8827\n" +
                "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
                "> ![screenshot](https://cdn.nlark.com/yuque/0/2021/png/12649111/1615772985378-6b8449ef-82cc-4314-ab5a-477329a85e52.png)\n"  +
                "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
        request.setMarkdown(markdown);
        OapiRobotSendResponse response = client.execute(request);

    }
}
