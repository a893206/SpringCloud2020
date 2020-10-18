package com.cr.springcloud.controller;

import com.cr.springcloud.entities.CommonResult;
import com.cr.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author cr
 * @date 2020-10-18 14:20
 */
@Slf4j
@RestController
@RequestMapping("/consumer/payments")
public class OrderController {
    //    public static final String PAYMENT_URL = "http://localhost:8001/payments";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/payments";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping()
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL, payment, CommonResult.class);
    }

    @GetMapping("/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/" + id, CommonResult.class);
    }
}
