package com.example.eurekaconsumer;

import com.example.eurekaconsumer.interceptor.LoggingClientHttpRequestInterceptor;
import com.netflix.loadbalancer.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class EurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced //注意此处与Controller中restTemplate有关
    RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new LoggingClientHttpRequestInterceptor());
        return restTemplate;
    }

    /**
     * 自定义负载均衡策略
     * @return
     */
    @Bean
    public IRule myRule(){
        //return new RetryRule();
        return new RandomRule();
    }

    @RequestMapping
    public String test(){
        return "eureka-consumer";
    }


}
