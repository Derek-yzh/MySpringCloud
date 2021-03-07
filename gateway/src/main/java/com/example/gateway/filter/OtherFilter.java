package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: Derek
 * @DateTime: 2021/3/7 15:01
 * @Description: OtherFilter
 */
@Component
public class OtherFilter implements Ordered, GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Other filter .. ");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 10;
    }

}
