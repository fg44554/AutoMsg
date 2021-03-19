package com.lwz.automsg.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author lwz
 */
@Service
public class GetChatIdUtil {
    @Autowired
    SendImageUtil sendImageUtil;
    public String getGroupList() throws IOException {
        CloseableHttpClient client = sendImageUtil.getHttpClient();

        HttpGet get = new HttpGet("https://open.feishu.cn/open-apis/chat/v4/list?page_size=20");
        get.addHeader("Content-Type","application/json; charset=utf-8");
        AccessTokenUtil accessTokenController = new AccessTokenUtil();
        System.out.println("------------------------------------------"+accessTokenController.getTenantAccessToken());
        String tenantAccessToken = accessTokenController.getTenantAccessToken();
        get.addHeader("Authorization", "Bearer "+tenantAccessToken+"");
        CloseableHttpResponse response = client.execute(get);

        System.out.println("http response code:" + response.getStatusLine().getStatusCode());
        for (Header header: response.getAllHeaders()) {
            System.out.println(header.toString());
        }
        HttpEntity resEntity = response.getEntity();


        if (resEntity == null) {
            System.out.println("never here?");
            return "";
        }
        System.out.println("Response content length: " + resEntity.getContentLength());
        return EntityUtils.toString(resEntity);
    }

    public String CharId(String s){
        JSONObject parse = (JSONObject)JSONObject.parse(s);
        String data = parse.getString("data");
        System.out.println(data);
        JSONObject dataJson = (JSONObject) JSONObject.parse(data);
        JSONArray jsonArray = dataJson.getJSONArray("groups");
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        return jsonObject.getString("chat_id");
    }

    public String getChatId() throws IOException {
        return this.CharId(this.getGroupList());
    }
}
