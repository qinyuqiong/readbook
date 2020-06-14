package com.wenhua.readbook.msm.contreller;

import com.wenhua.readbook.msm.service.MsmService;
import com.wenhua.readbook.msm.untils.RandomUtil;
import com.wenhua.readbook_common.StatusReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 阿里云实现短信验证
 * @version 1.0 2020/5/12
 * @auther LENOVO
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 发送短信的方法
     * @return
     */
    @GetMapping("send/{phone}")
    public StatusReturn sendMsm(@PathVariable String phone){
        //1从redis获取验证码，如果获取不到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return StatusReturn.success();
        }
        //2如果redis获取不到进行阿里云发送
        //生成随机值，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String , Object> param = new HashMap();
        param.put("code",code);
        //调用service发送短信的方法
        boolean isSend= msmService.send(param,phone);
        if (isSend == false){
            return StatusReturn.error();
        }
        redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
        return StatusReturn.success();
    }
}
