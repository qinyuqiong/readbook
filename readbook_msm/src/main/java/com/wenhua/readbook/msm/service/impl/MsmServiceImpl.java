package com.wenhua.readbook.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.wenhua.readbook.msm.handler.ConstantPropertiesUtil;
import com.wenhua.readbook.msm.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @version 1.0 2020/5/12
 * @auther LENOVO
 */
@Service
public class MsmServiceImpl implements MsmService {

    /**
     * 发送短信方法
     * @param param
     * @param phone
     * @return
     */
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        if (StringUtils.isEmpty(phone)){
            return false;
        }
        DefaultProfile profile = DefaultProfile.getProfile("default", ConstantPropertiesUtil.KEYID,ConstantPropertiesUtil.KEYSECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        //设置相关固定参数
        CommonRequest request = new CommonRequest();
        //请求方式
        request.setMethod(MethodType.POST);
        //请求地址
        request.setDomain("dysmsapi.aliyuncs.com");
        //版本号
        request.setVersion("2017-05-25");
        //请求的方法
        request.setAction("SendSms");

        //设置发送相关的参数
        //手机号
        request.putQueryParameter("PhoneNumbers", phone);
        //签名名称，阿里设置的
        request.putQueryParameter("SignName", "我的学习在线教育网站");
        //阿里云的模板code
        request.putQueryParameter("TemplateCode", "SMS_189714086");
        //验证码
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        //发送
        try {
            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
