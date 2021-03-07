package com.example.userprovider.controller;

import com.example.userapi.service.UserApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Derek
 * @DateTime: 2020/12/31 9:47
 * @Description: TODO
 */
@RestController
public class UserController implements UserApi {

    @Value("${server.port}")
    String port;

    AtomicInteger count = new AtomicInteger();

    @Override
    public String alive() {
        System.out.println("准备睡");
        //TODO 测试降级
        //try { TimeUnit.MILLISECONDS.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
        int i = count.getAndIncrement();
        System.out.println(port+"===第"+i+"次调用===");
        //int teat = 1/0;
        return "userController alive ok ! port: "+port;
    }

    @Override
    public String getById(Integer id) {
        return "ProviderPort:"+port+id.toString();
    }

    @Override
    public Map<String, String> reg(Map<String, String> map) {
        HashMap<String, String> m = new HashMap<>();
        m.put("user",map.get("user"));
        return m;
    }


    /*@GetMapping("/alive")
    public String alive(){
        return "userController alive ok";
    }*/

}
