package com.cr.springcloud.controller;

import com.cr.springcloud.entities.CommonResult;
import com.cr.springcloud.entities.Payment;
import com.cr.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cr
 * @date 2020-10-17 17:15
 */
@Slf4j
@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping()
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果：" + result);

        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功，serverPort：" + serverPort, result);
        }
        return new CommonResult<>(444, "插入数据库失败", null);
    }

    @GetMapping("/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);

        if (payment != null) {
            return new CommonResult<>(200, "查询成功，serverPort：" + serverPort, payment);
        }
        return new CommonResult<>(444, "没有对应记录，查询id：" + id, null);
    }

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service = " + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("instance = " + instance);
        }

        return discoveryClient;
    }

    @GetMapping("/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }
}
