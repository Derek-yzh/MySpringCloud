package com.example.userapi.service;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: Derek
 * @DateTime: 2020/12/31 10:11
 * @Description: 接口定义
 */
//@RequestMapping("/user")
public interface UserApi {

    @GetMapping("/alive")
    String alive();

    /**
     * 这里的GetMapping是给Feign看的  get请求user-provider？id={1}
     * @param id 也是给Feign看的
     * @return
     */
    @GetMapping("/getById")
    String getById(@RequestParam("id") Integer id);

    @PostMapping("/register")
    Map<String, String> reg(@RequestBody Map<String,String> map);

}
