package com.example.eurekaconsumer.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author: Derek
 * @DateTime: 2020/12/30 15:16
 * @Description: Eureka Consumer
 */
@RestController
@SuppressWarnings("all")
public class MainController {

    @Autowired
    DiscoveryClient client;

    @Autowired
    EurekaClient client2;

    @Autowired
    LoadBalancerClient lb;

    @GetMapping("/getServices")
    public String getServices(){
        System.out.println(client.getServices());
        return "success";
    }

    @GetMapping("/getInstances")
    public String getInstances(){
        List<ServiceInstance> instances = client.getInstances("eureka-provider");
        for (ServiceInstance instance : instances) {
            System.out.println(instance);
        }
        return "success";
    }

    @GetMapping("/doService")
    public String doService() {
        // 具体服务
        //	List<InstanceInfo> instances = client2.getInstancesById("localhost:provider:8001");
        // 使用服务名 ，找列表
        List<InstanceInfo> instances = client2.getInstancesByVipAddress("eureka-provider", false);
        for (InstanceInfo ins : instances) {
            System.out.println(ToStringBuilder.reflectionToString(ins));
        }
        if(instances.size()>0) {
            // 可能多个微服务 这里调了第一个provider微服务
            InstanceInfo instanceInfo = instances.get(0);
            System.out.println("instanceInfo:   "+instanceInfo);
            if(instanceInfo.getStatus() == InstanceInfo.InstanceStatus.UP) {
                String url = "http://" + instanceInfo.getHostName() +":"+ instanceInfo.getPort() + "/";
                System.out.println("url:    " + url);
                RestTemplate restTemplate = new RestTemplate();
                String respStr = restTemplate.getForObject(url, String.class);
                System.out.println("respStr:    "  + respStr);
            }
        }
        return "success";
    }

    @GetMapping("/doService2")
    public String doService2() {
        ServiceInstance instanceInfo = lb.choose("eureka-provider");
        System.out.println("instanceInfo:   "+instanceInfo);

        String url = "http://" + instanceInfo.getHost() +":"+ instanceInfo.getPort() + "/";
        System.out.println("url:    " + url);

        RestTemplate restTemplate = new RestTemplate();
        String respStr = restTemplate.getForObject(url, String.class);
        System.out.println("respStr:    "  + respStr);
        return "success";
    }

}
