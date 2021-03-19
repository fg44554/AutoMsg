package com.lwz.automsg.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lwz.automsg.utils.JacksonUtil;
import com.lwz.automsg.utils.ResolveUrlUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author lwz
 */
public class DownImageUtil {
    /**
     * 抓取网上的图片
     * [延伸]居然都支持下载网页了
     * @param imgSrc
     * @param filePath
     * @param fileName
     */
    public static void downloadImg(String imgSrc, String filePath, String fileName){
        try{
            URL url = new URL(imgSrc);
            URLConnection conn = url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //输出流
            InputStream str = conn.getInputStream();

            //控制流的大小为1k
            byte[] bs = new byte[1024];

            //读取到的长度
            int len = 0;

            //是否需要创建文件夹
            File saveDir = new File(filePath);
            if(!saveDir.exists()){
                saveDir.mkdir();
            }
            File file = new File(saveDir+File.separator+fileName);

            //实例输出一个对象
            FileOutputStream out = new FileOutputStream(file);

            //循环判断，如果读取的个数b为空了，则is.read()方法返回-1，具体请参考InputStream的read();
            while ((len = str.read(bs)) != -1) {
                //将对象写入到对应的文件中
                out.write(bs, 0, len);
            }

            //刷新流
            out.flush();
            //关闭流
            out.close();
            str.close();

            System.out.println("下载成功");

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void redirect(@RequestBody Object req) throws IOException {
        String s = JacksonUtil.toJSON(req);
        //获取"result"中的json字符串
        JSONObject parse = (JSONObject) JSON.parse(s);
        //获取字符数组
        String data = parse.getString("data");
        JSONObject dataParse = (JSONObject) JSON.parse(data);
        dataParse.getString("body_html");
    }

}
