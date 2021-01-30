package com.example.eurekaprovider.service;

import lombok.Data;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

/**
 * @Author: Derek
 * @DateTime: 2020/12/30 17:33
 * @Description: TODO
 */
@Service
@Data
public class HealthStatusService implements HealthIndicator {

    private Boolean status = true;

    @Override
    public Health health() {
        if (status)
            return new Health.Builder().up().build();
        return new Health.Builder().down().build();
    }

}
