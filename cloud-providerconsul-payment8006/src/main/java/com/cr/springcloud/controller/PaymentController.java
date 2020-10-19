package com.cr.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author cr
 * @date 2020-10-19 23:55
 */
@Slf4j
@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consul")
    public String consul() {
        return "SpringCloud with Consul：" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
