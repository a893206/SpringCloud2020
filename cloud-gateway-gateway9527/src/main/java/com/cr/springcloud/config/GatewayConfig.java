package com.cr.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cr
 * @date 2020-10-22 18:12
 */
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route_guonei", r -> r.path("/guonei").uri("http://news.baidu.com/guonei"))
                .route("path_route_guoji", r -> r.path("/guoji").uri("http://news.baidu.com/guoji"))
                .build();
    }
}
