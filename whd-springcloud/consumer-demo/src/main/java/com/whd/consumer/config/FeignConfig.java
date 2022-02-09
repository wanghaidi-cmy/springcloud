package com.whd.consumer.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wanghaidi
 * @create 2022-02-10 10:29
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        //记录所有请求和响应的明细
        return Logger.Level.FULL;
    }
}
