package com.example.userconsumer.controller;

import com.example.userconsumer.service.ConsumerApi;
import com.example.userconsumer.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author: Derek
 * @DateTime: 2020/12/31 9:40
 * @Description: TODO
 */
@RestController
@SuppressWarnings("all")
public class ConsumerController {

    @Autowired
    ConsumerApi api;

    @Value("${server.port}")
    String port;

    @RequestMapping("alive")
    public String alive(){
        return "ConsumerPort:"+port+api.alive();
    }

    @RequestMapping("getById")
    public String getById(){
        return "ConsumerPort:"+port+"-->>"+api.getById(1);
    }

    @GetMapping("/register")
    public Map<String, String> reg(){
        HashMap<String, String> m = new HashMap<>();
        m.put("user","aaa");
        return api.reg(m);
    }





    @Autowired
    RestService restService;

    /**
     * test get Object
     * @return
     */
    @RequestMapping("/alive2")
    public String alive2(){
        return restService.alive();
    }



}
