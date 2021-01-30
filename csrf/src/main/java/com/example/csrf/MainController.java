package com.example.csrf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Derek
 * @DateTime: 2021/1/5 21:27
 * @Description: TODO
 */
@Controller
public class MainController {

    @GetMapping("/login.html")
    public String login(){
        return "login";
    }

}
