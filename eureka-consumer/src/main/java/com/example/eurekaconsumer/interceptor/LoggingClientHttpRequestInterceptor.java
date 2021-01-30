package com.example.eurekaconsumer.interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @Author: Derek
 * @DateTime: 2020/12/30 23:53
 * @Description: 拦截器
 */
public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        System.out.println("拦截啦！！！");
        System.out.println("URI:\t"+request.getURI());

        ClientHttpResponse response = execution.execute(request, body);

        System.out.println("Headers::\t"+response.getHeaders());
        return response;
    }
}