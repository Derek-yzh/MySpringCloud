package com.example.eurekaconsumer.controller;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

/**
 * @Author: Derek
 * @DateTime: 2020/12/30 20:20
 * @Description:
 *
 * test()方法此时无效 要想使用 去掉EurekaConsumerApplication类下@LoadBalanced注解
 *     @Bean
 *     @LoadBalanced //注意此处与Controller中restTemplate有关
 *     RestTemplate getRestTemplate(){
 *         return new RestTemplate();
 *     }
 */
@SuppressWarnings("all")
@RestController
public class Controller {

    @Autowired
    RestTemplate restTemplate;

    /**
     * test get Object
     * @return
     */
    @RequestMapping("/testObj")
    public String testObj(){
        String url = "http://eureka-provider/getPort";
        System.out.println(restTemplate.getForEntity(url, String.class));
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * test get map + Param
     * @return
     */
    @RequestMapping("/getMap")
    public Object getMap(){
        String url = "http://eureka-provider/getMap?name={1}";
        System.out.println(restTemplate.getForEntity(url, Map.class,"bbb"));
        return restTemplate.getForObject(url, Map.class,"aaa");
    }

    /**
     * test postForLocation + mapParam
     * @return
     */
    @GetMapping("/postForLocation")
    public Object postForLocation(HttpServletResponse response) throws Exception {
        String url ="http://eureka-provider/postForLocation?name={name}";

        Map<String, String> map = Collections.singletonMap("name", " memeda");
        URI location = restTemplate.postForLocation(url, map,Map.class);

        System.out.println(location);
        response.sendRedirect(location.toURL().toString());
        return null;
    }








    @Autowired
    LoadBalancerClient lb;

    /**
     * 无效接口！
     * @return
     */
    @RequestMapping("/test")
    public String test(){
        ServiceInstance instanceInfo = lb.choose("eureka-provider");
        String url = "http://" + instanceInfo.getHost() +":"+ instanceInfo.getPort() + "/getPort";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

}
