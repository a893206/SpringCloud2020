package com.cr.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author cr
 * @date 2020-10-20 00:06
 */
@Slf4j
@RestController
@RequestMapping("/consumer/payments")
public class OrderController {
    public static final String INVOKE_URL = "http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consul")
    public String paymentInfo() {
        return restTemplate.getForObject(INVOKE_URL + "/payments/consul", String.class);
    }
}
