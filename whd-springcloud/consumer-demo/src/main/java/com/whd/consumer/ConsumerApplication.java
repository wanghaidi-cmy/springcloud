package com.whd.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author 86198
 */
@SpringBootApplication
/**
 * 开启eureka客户端发现功能
 */
@EnableDiscoveryClient
/**
 * 开启熔断功能
 */
@EnableCircuitBreaker
//@SpringCloudApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    /**
     * @LoadBalanced 负载均衡注解
     */
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
