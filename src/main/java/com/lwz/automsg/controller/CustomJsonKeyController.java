package com.lwz.automsg.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lwz.automsg.utils.*;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * @author lwz
 */
@RestController
public class CustomJsonKeyController {
    @Autowired
    ChangePathUtil changePathUtil;
    @Autowired
    SendImageUtil sendImageUtil;

    @PostMapping("/getJson")
    public void redirect(@RequestBody Object req) throws IOException {
        String WEBHOOK_TOKEN = "https://open.feishu.cn/open-apis/bot/v2/hook/310a67b9-4b33-4701-8285-fa8adede8ed5";

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        String key_word = "项目更新";
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
        ResolveUrlUtil resolveUrlUtil = new ResolveUrlUtil();
        List<String> img = resolveUrlUtil.imgs(body_html);
        StringBuffer stringBuffer = new StringBuffer();
        for (String sImg : img) {
            stringBuffer.append(sImg);
        }

        String imgUrl = stringBuffer.toString();
        System.out.println(imgUrl);
        int max = 100, min = 1;
        int ran2 = (int) (Math.random() * (max - min) + min);
        //用uuid来生成随机文件名
        UUID uuid = UUID.randomUUID();
        String imgName = uuid.toString();

        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        //todo:如果不是图片或为其他格式文件需要判断
        //将文件down到本地
        if (os != null && os.toLowerCase().contains("linux")) {
            DownImageUtil.downloadImg(imgUrl,
                    changePathUtil.linuxFilePath,
                    imgName + ".jpg");
        } else {
            DownImageUtil.downloadImg(imgUrl,
                    changePathUtil.windowsFilePath,
                    imgName + ".jpg");
        }
        //获取飞书端imgKey
        String imgKey = sendImageUtil.sendImg(imgName);
        System.out.println(imgKey);
        String description = bookParse.getString("description");
        String bookTitle = bookParse.getString("name");
        String title = dataParse.getString("title");
        String name = userParse.getString("name");
        String url = dataParse.getString("path");
        //构建一个json格式字符串textMsg，其内容是接收方需要的参数和消息内容
        String textMsg = "{\"msg_type\":\"text\",\"content\":{\"text\":\"来自语雀的消息  创作人:" + name + "标题:" + title + "内容:" + bookTitle + "\"}}";
        //TODO 添加标题、添加图片、添加文本、添加链接
//        String imgKey=;
        String richTextMsg = "{\"email\":\"fanlv@bytedance.com\",\"msg_type\":\"post\"," +
                "\"content\":{\"post\":{\"zh_cn\":{\"title\":\"" + title + "\"," +
                "\"content\":[[{\"tag\":\"text\",\"un_escape\":true,\"text\":\"第一行&nbsp;:\"}," +
                "{\"tag\":\"a\",\"text\":\"超链接\",\"href\":\"http://www.feishu.cn\"}," +
                "{\"tag\":\"at\",\"user_id\":\"ou_18eac85d35a26f989317ad4f02e8bbbb\"}]," +
                "[{\"tag\":\"text\",\"text\":\"第二行 :\"},{\"tag\":\"text\",\"text\":\"文本测试\"}]," +
                "[{\"tag\":\"img\",\"image_key\":\"" + imgKey + "\",\"width\":300,\"height\":300}]]}}}}";
        StringEntity se = new StringEntity(richTextMsg, "utf-8");
        httppost.setEntity(se);
        System.out.println(richTextMsg);
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
    }
}
