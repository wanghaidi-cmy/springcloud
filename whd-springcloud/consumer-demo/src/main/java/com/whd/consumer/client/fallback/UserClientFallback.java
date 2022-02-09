package com.whd.consumer.client.fallback;

import com.whd.consumer.client.UserClient;
import com.whd.consumer.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @author wanghaidi
 * @create 2022-02-10 10:17
 */
@Component
public class UserClientFallback implements UserClient {
    @Override
    public User queryById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("用户异常");
        return user;
    }
}
