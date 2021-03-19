package com.lwz.automsg.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author lwz
 */
public class AccessTokenUtil {
    public String getTenantAccessToken(){
        String url="https://open.feishu.cn/open-apis/auth/v3/tenant_access_token/internal/";
        String jsonStr="{\"app_id\":\"cli_a0a894976df9900c\",\"app_secret\":\"CoUp6VAeeD6OKpNo6LPhggVBuSK1o1lS\"}";
        String result= HttpClientUtil.doPostJson(url,jsonStr);
        JSONObject parse = (JSONObject)JSONObject.parse(result);
        return parse.getString("tenant_access_token");
    }


    @Test
    public void test(){
//        System.out.println(this.getGroupList());
    }

}
