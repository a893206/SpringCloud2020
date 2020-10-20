package com.cr.springcloud.service;

import com.cr.springcloud.entities.CommonResult;
import com.cr.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author cr
 * @date 2020-10-20 18:05
 */
@FeignClient(value = "CLOUD-PAYMENT-SERVICE", path = "/payments")
public interface PaymentFeignService {
    @PostMapping()
    CommonResult<Integer> create(@RequestBody Payment payment);

    @GetMapping("/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/feign/timeout")
    String paymentFeignTimeout();
}
