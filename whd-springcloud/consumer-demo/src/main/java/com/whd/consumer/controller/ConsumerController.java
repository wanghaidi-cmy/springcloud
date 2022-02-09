package com.whd.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.whd.consumer.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author wanghaidi
 * @create 2022-02-08 23:37
 */
@RestController
@RequestMapping("/consumer")
@Slf4j
/**
 * 默认降级方法
 */
@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    /**
     * 服务降级
     */
    //@HystrixCommand(fallbackMethod = "queryByIdFallback")
    @HystrixCommand
    public String queryById(@PathVariable("id") Long id) {//User
//        String url = "http://localhost:9091/user/8";
        //获取eureka中注册的user-service实例
        /*List<ServiceInstance> serviceInstances = discoveryClient.getInstances("user-service");
        ServiceInstance serviceInstance = serviceInstances.get(0);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + id;*/
        String url = "http://user-service/user/" + id;
        return restTemplate.getForObject(url, String.class);
    }

    public String queryByIdFallback(Long id) {
        log.error("查询用户信息失败。id:{}", id);
        return "对不起，网络连接失败！";
    }

    public String defaultFallback() {
        return "默认提示对：不起，网络连接失败！";
    }
}
