package com.cr.springcloud.controller;

import com.cr.springcloud.entities.CommonResult;
import com.cr.springcloud.entities.Payment;
import com.cr.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author cr
 * @date 2020-10-20 18:10
 */
@Slf4j
@RestController
@RequestMapping("/consumer/payments")
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @PostMapping()
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        return paymentFeignService.create(payment);
    }

    @GetMapping("/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/feign/timeout")
    public String paymentFeignTimeout() {
        return paymentFeignService.paymentFeignTimeout();
    }
}
