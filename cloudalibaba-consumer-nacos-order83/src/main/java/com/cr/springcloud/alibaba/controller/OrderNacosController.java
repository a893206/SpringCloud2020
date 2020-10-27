package com.cr.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author cr
 * @date 2020-10-27 15:50
 */
@Slf4j
@RestController
@RequestMapping("/consumer/payments/nacos")
public class OrderNacosController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/{id}")
    public String paymentInfo(@PathVariable Integer id) {
        return restTemplate.getForObject(serverURL + "/payments/nacos/" + id, String.class);
    }
}
