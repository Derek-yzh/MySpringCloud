package com.example.userconsumer.hystrixBack;

import com.example.userconsumer.service.ConsumerApi;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * @Author: Derek
 * @DateTime: 2021/1/1 10:37
 * @Description: hystrix back
 */
@Component
public class UserProviderBack implements ConsumerApi {
    @Override
    public String alive() {
        return "alive 降级了";
    }

    @Override
    public String getById(Integer id) {
        return "getById 降级了";
    }

    @Override
    public Map<String, String> reg(Map<String, String> map) {
        return Collections.singletonMap("error","register 降级了");
    }
}
