package com.example.gateway;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * @Author: Derek
 * @DateTime: 2021/3/7 14:22
 * @Description: 自定义路由规则
 */
public class MyRule extends AbstractLoadBalancerRule {

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
    }

    @Override
    public Server choose(Object key) {
        List<Server> servers = this.getLoadBalancer().getReachableServers();
        System.out.println("Reachable servers: " + servers);
        return servers.get(0);
    }

}
