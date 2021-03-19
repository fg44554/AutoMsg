package com.lwz.automsg.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

/**
 * @author lwz
 */
@Service
public class SendImageUtil {

@Autowired
ChangePathUtil changePathUtil;

    public  CloseableHttpClient getHttpClient() {
        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(),
                    NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", new PlainConnectionSocketFactory())
                    .register("https", sslConnectionSocketFactory)
                    .build();

            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(100);
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslConnectionSocketFactory)
                    .setDefaultCookieStore(new BasicCookieStore())
                    .setConnectionManager(cm).build();
            return httpclient;
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    public String SendImageByApacheHttpClient(File file) throws IOException {

        CloseableHttpClient client = this.getHttpClient();

        HttpPost post = new HttpPost("https://open.feishu.cn/open-apis/image/v4/put/");
        final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        FileBody bin = new FileBody(file);
        builder.addPart("image", bin);
        builder.addTextBody("image_type", "message");
        HttpEntity multiPartEntity = builder.build();

        post.setEntity(multiPartEntity);
        AccessTokenUtil accessTokenController = new AccessTokenUtil();
        System.out.println("------------------------------------------"+accessTokenController.getTenantAccessToken());
        String tenantAccessToken = accessTokenController.getTenantAccessToken();
        post.setHeader("Authorization", "Bearer "+tenantAccessToken+"");
        CloseableHttpResponse response = client.execute(post);

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


    public  String sendImg(String imageName) throws IOException {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        String path="";
        if (os != null && os.toLowerCase().contains("linux")) {
            path=changePathUtil.linuxFilePath;
        } else {
            path=changePathUtil.windowsFilePath;
        }
            File file = new File(path+imageName+".jpg");
            String result = SendImageByApacheHttpClient(file);
            System.out.println(result);
            System.out.println(getFileName());
            JSONObject imgKeyJson = (JSONObject) JSONObject.parse(result);
            String data = imgKeyJson.getString("data");
            JSONObject dataJson = (JSONObject) JSONObject.parse(data);
            String imageKey = dataJson.getString("image_key");
            System.out.println("在sendImg方法中"+imageKey);
            return imageKey;
    }


    public  String getFileName() {
        // 路径
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        String path="";
        if (os != null && os.toLowerCase().contains("linux")) {
            path=changePathUtil.linuxFilePath;
        } else {
            path=changePathUtil.windowsFilePath;
        }
        System.out.println("subString前"+path);
        path = path.substring(0,path.length()-1);
        System.out.println("subString后"+path);
        File f = new File(path);
        if (!f.exists()) {
            System.out.println(path + " not exists");
            return "";
        }
        File fa[] = f.listFiles();
            File fs = fa[fa.length-1];
        return fs.getName();
    }


}
