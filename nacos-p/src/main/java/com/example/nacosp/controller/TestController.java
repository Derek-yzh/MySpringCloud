package com.example.nacosp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Derek
 * @DateTime: 2021/3/6 23:03
 * @Description: Test nacos
 */
@RestController
@RefreshScope//加此注解后 value会随配置的值发生变化
public class TestController {

    @Value("${testNacos}")
    private String testNacos;

    @GetMapping("testNacos")
    public String testNacos(){
        return testNacos;
    }

}
