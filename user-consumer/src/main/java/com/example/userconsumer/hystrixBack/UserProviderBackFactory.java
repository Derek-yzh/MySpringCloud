package com.example.userconsumer.hystrixBack;

import com.example.userconsumer.service.ConsumerApi;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * @Author: Derek
 * @DateTime: 2021/1/1 11:01
 * @Description: hystrix back 更细度的降级
 */
@Component
public class UserProviderBackFactory implements FallbackFactory<ConsumerApi> {

    /**
     *
     * @param cause 包含本地异常+远端异常
     * @return
     */
    @Override
    public ConsumerApi create(Throwable cause) {

        return new ConsumerApi() {
            @Override
            public String alive() {
                System.out.println(cause);
                if(cause instanceof FeignException.InternalServerError) {
                    System.out.println("InternalServerError");
                    return "远程服务报错";
                }else if(cause instanceof RuntimeException) {
                    return "请求时异常：" + cause;
                }else {
                    return "都算不上";
                }
            }

            @Override
            public String getById(Integer id) {
                return "UserProviderBackFactory: getById 降级了";
            }

            @Override
            public Map<String, String> reg(Map<String, String> map) {
                return Collections.singletonMap("error","UserProviderBackFactory: register 降级了");
            }
        };
    }
}
