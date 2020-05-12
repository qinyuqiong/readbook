package com.wenhua.readbook.msm.service;

import java.util.Map;

/**
 * @version 1.0 2020/5/12
 * @auther LENOVO
 */
public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
