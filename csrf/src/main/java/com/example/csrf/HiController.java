package com.example.csrf;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Derek
 * @DateTime: 2021/1/6 20:38
 * @Description: TODO
 */
@RestController
public class HiController {
    @RequestMapping("/hi")
    public String hi(){
        System.out.println("hi...");
        return "hi";
    }


}
