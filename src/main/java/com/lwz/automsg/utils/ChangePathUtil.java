package com.lwz.automsg.utils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author lwz
 */
@Component
@Configuration
@PropertySource(value ="classpath:/application.properties")
public class ChangePathUtil {

    @Value("${linux_file_path}")
    public  String linuxFilePath;

    @Value("${windows_file_path}")
    public  String windowsFilePath;


}

