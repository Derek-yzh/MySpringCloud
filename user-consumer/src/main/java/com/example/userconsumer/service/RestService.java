package com.example.userconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Derek
 * @DateTime: 2021/1/1 11:27
 * @Description: Hystrix by restTemplate
 */
@Service
public class RestService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(defaultFallback = "back")
    public String alive() {
        String url = "http://user-provider/alive";
        return restTemplate.getForObject(url, String.class);
    }

    public String back(){
        return "restTemplate 降级";
    }

}
