package com.example.gateway;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @Author: Derek
 * @DateTime: 2021/3/7 14:35
 * @Description: 过滤器
 */
@Component
public class MyFilter implements Ordered, GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        List<String> list = queryParams.get("id");
        if (null == list || list.size() == 0) {
            //直接return 不再走接下来的filter
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            DataBuffer dataBuffer = exchange.getResponse().bufferFactory().wrap("no Auth".getBytes());
            return exchange.getResponse().writeWith(Mono.just(dataBuffer));
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
