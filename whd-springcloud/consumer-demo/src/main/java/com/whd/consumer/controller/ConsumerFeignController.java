package com.whd.consumer.controller;

import com.whd.consumer.client.UserClient;
import com.whd.consumer.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghaidi
 * @create 2022-02-10 8:58
 * Feign处理器编写
 */
@RestController
@RequestMapping("/cf")
@Slf4j
public class ConsumerFeignController {
    /**
     * 注入userClient客户端对象
     */
    @Autowired
    private UserClient userClient;

    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userClient.queryById(id);
    }
}
