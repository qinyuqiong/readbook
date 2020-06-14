package com.wenhua.readbook.msm.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description:在服务器启动时候，让这个类读取配置文件的内容
 * @author: yuqiong
 * @createDate: 2020/4/4
 * @version: 1.0
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    /**
     * 获取配置文件的值,@value赋值，${}符号用于取到它代表的值
     */
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyid;

    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;

    //定义常量
    public static String ENDPOINT;
    public static String KEYID;
    public static String KEYSECRET;
    public static String BUCKETNAME;

    /**
     * 服务器启动的时候ConstantPropertiesUtil初始化
     * 调用里面的afterPropertiesSet读取配置文件
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
        KEYID = keyid;
        KEYSECRET = keysecret;
        BUCKETNAME = bucketname;
    }
}
