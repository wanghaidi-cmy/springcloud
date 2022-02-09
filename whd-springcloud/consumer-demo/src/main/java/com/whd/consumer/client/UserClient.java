package com.whd.consumer.client;

import com.whd.consumer.client.fallback.UserClientFallback;
import com.whd.consumer.config.FeignConfig;
import com.whd.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wanghaidi
 * @create 2022-02-10 8:51
 * 声明当前类是一个feign客户端，指定服务名称为user-service
 */
@FeignClient(value = "user-service", fallback = UserClientFallback.class, configuration = FeignConfig.class)

public interface UserClient {
    /**
     * http://user-service/user/id
     *
     * @param id 查询的id
     * @return 返回值 返回查询的值
     */
    @GetMapping("/user/{id}")
    User queryById(@PathVariable Long id);
}
