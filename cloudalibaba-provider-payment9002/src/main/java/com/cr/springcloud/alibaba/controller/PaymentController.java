package com.cr.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cr
 * @date 2020-10-27 15:18
 */
@RestController
@RequestMapping("/payments/nacos")
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/{id}")
    public String getPayment(@PathVariable Integer id) {
        return "nacos registry，serverPort：" + serverPort + "\t id：" + id;
    }
}
