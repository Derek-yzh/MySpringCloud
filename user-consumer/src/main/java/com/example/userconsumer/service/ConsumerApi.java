package com.example.userconsumer.service;

import com.example.userapi.service.UserApi;
import com.example.userconsumer.hystrixBack.UserProviderBack;
import com.example.userconsumer.hystrixBack.UserProviderBackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: Derek
 * @DateTime: 2020/12/31 9:41
 * @Description:
 * test hystrix :
 *      UserProviderBack：粗一点的降级
 *      UserProviderBackFactory:更细度的降级
 */
//@FeignClient(name = "xxoo",url = "http://localhost:8020/") // 此项不结合Eureka的使用 (不用这个)
//@FeignClient(name = "user-provider",fallback = UserProviderBack.class)
@FeignClient(name = "user-provider",fallbackFactory = UserProviderBackFactory.class)
public interface ConsumerApi extends UserApi {


}
