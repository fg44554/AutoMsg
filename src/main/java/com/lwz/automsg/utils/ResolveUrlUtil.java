package com.lwz.automsg.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResolveUrlUtil {
    public   List<String> getImg(String detail) {
        String regex = "src=\"(.*?)\"";
        ;
        List<String> list = new ArrayList<>();

        Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        Matcher ma = pa.matcher(detail);
        while (ma.find()) {
            String src = ma.group();
            String regex1 = "http(.*?)(.jpg|.png)";
            Pattern pa1 = Pattern.compile(regex1, Pattern.DOTALL);
            Matcher ma1 = pa1.matcher(src);
            while (ma1.find()) {
                list.add(ma1.group());
            }
        }
        for (String str : list) {
            System.out.println("解析后图片:" + str);
        }
        return list;
    }
    public  List<String> imgs(String content) {
        //创建存放src值的list
        List<String> urlList = new ArrayList<String>();
        //匹配字符串中的img标签
        Pattern p =  Pattern.compile("<(img|IMG)(.*?)(>|></img>|/>)");
        Matcher matcher = p.matcher(content);
        boolean hasPic = matcher.find();
        //判断是否含有图片
        if (hasPic == true) {
            //如果含有图片，那么持续进行查找，直到匹配不到
            while (hasPic) {
                //获取第二个分组的内容，也就是 (.*?)匹配到的
                String group = matcher.group(2);
                //匹配图片的地址
                Pattern srcText =Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
                Matcher matcher2 = srcText.matcher(group);
                if (matcher2.find()) {
                    try {
                        urlList.add(matcher2.group(3));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                hasPic = matcher.find();//判断是否还有img标签
            }

        }
        return urlList;
    }
}
