package com.example.configclient.controler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Derek
 * @DateTime: 2021/1/5 14:12
 * @Description: TODO
 */
@RestController
public class MainController {

    @Value("${config.info}")
    String info;

    @RequestMapping("/test")
    public String test(){
        return info;
    }

}
