package com.lwz.automsg.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

public class JacksonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JacksonUtil.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        /**
         * 默认非空不输出，时间格式
         */
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 将 Java 对象转为 JSON 字符串
     */
    public static <T> String toJSON(T obj) {
        String jsonStr;
        try {
            jsonStr = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("Java 转 JSON 出错！",e);
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    /**
     * 将 JSON 字符串转为 Java 对象
     */
    public static <T> T fromJSON(String json,Class<T> type) {
        T obj;
        try {
            obj = objectMapper.readValue(json,type);
        } catch (Exception e) {
            logger.error("JSON 转 Java 出错！",e);
            throw new RuntimeException(e);
        }
        return obj;
    }
}
