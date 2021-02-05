package com.example.configclient.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Derek
 * @DateTime: 2021/1/5 14:12
 * @Description: TODO
 */
@RestController
@RefreshScope//配置中心自动刷新
public class MainController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${config.info}")
    String info;

    @RequestMapping("/test")
    public String test(){
        return info;
    }
    @GetMapping("/refresh")
    public String refresh(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE,"application/json");
        HttpEntity<String> request = new HttpEntity<>(headers);
        restTemplate.postForEntity("http://localhost:8999/actuator/refresh",request,String.class);
        return "刷新成功";
    }


}
