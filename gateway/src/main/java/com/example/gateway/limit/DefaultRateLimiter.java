package com.example.gateway.limit;

import com.google.common.util.concurrent.RateLimiter;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter;
import org.springframework.cloud.gateway.support.ConfigurationService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.constraints.DecimalMin;
import java.util.HashMap;
import java.util.Objects;

/**
 * @Author: Derek
 * @DateTime: 2021/3/7 15:43
 * @Description: 令牌桶限流
 */
//@Component
//@Primary
public class DefaultRateLimiter extends AbstractRateLimiter<DefaultRateLimiter.Config> {

    public DefaultRateLimiter() {
        super(Config.class, "default-rate-limit", new ConfigurationService());
    }

    /**
     * 每秒一个请求，每秒发一个令牌
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(3);

    @Override
    public Mono<Response> isAllowed(String routeId, String id) {
        Config config = getConfig().get(routeId);
        RATE_LIMITER.setRate(Objects.isNull(config.getPermitsPerSecond()) ? 1 : config.getPermitsPerSecond());
        boolean isAllow = RATE_LIMITER.tryAcquire();
        return Mono.just(new Response(isAllow, new HashMap<>()));
    }

    @Validated
    @Data
    @Accessors(chain = true)
    public static class Config {
        @DecimalMin("0.1")
        private Double permitsPerSecond;
    }

}
