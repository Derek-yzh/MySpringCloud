package com.example.eurekaprovider.controller;

import com.example.eurekaprovider.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Derek
 * @DateTime: 2020/12/30 17:37
 * @Description: TODO
 */
@RestController
public class MainController {

    @PostMapping("postForLocation")
    public URI postParam(@RequestBody Map<String,String> map, HttpServletResponse response) throws Exception {
        URI uri = new URI("https://www.baidu.com/s?wd=" + map.get("name").trim());
        response.addHeader("Location", uri.toString());
        return uri;
    }

    @RequestMapping("getMap")
    public Map<String,String> getMap(@RequestParam String name){
        return Collections.singletonMap(name,"provider-"+name);
    }

    @Value("${server.port}")
    String port;
    @RequestMapping("/getPort")
    public String getPort(){
        return "我的port: " + port;
    }

    @Autowired
    HealthStatusService healthStatusService;

    @RequestMapping("/health")
    public String health(@RequestParam("status") Boolean status){
        healthStatusService.setStatus(status);
        return healthStatusService.getStatus().toString();
    }

}
