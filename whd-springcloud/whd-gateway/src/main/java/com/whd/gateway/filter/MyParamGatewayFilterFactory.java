package com.whd.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author wanghaidi
 * @create 2022-02-10 16:12
 * 编写局部过滤器
 */
@Component
public class MyParamGatewayFilterFactory extends AbstractGatewayFilterFactory<MyParamGatewayFilterFactory.Config> {
    static final String PARAM_NAME = "param";

    public MyParamGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            //http://localhost:10010/api/user/8?name=itcast config.param == name
            //获取请求参数中的param对应的参数名的参数值
            ServerHttpRequest request = exchange.getRequest();
            if (request.getQueryParams().containsKey(config.param)) {
                request.getQueryParams().get(config.param).forEach(value ->
                        System.out.printf("------局部过滤器------%s = %s------", config.param, value));
            }
            return chain.filter(exchange);
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(PARAM_NAME);
    }

    public static class Config {
        /**
         * 对应在配置过滤器的时候指定的参数名称
         */
        private String param;

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }
    }

}
