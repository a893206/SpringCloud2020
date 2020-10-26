package com.cr.springcloud.controller;

import com.cr.springcloud.entities.CommonResult;
import com.cr.springcloud.entities.Payment;
import com.cr.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping()
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL, payment, CommonResult.class);
    }

    @PostMapping("/postForEntity")
    public CommonResult<Integer> create1(@RequestBody Payment payment) {
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL, payment, CommonResult.class);

        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info(entity.getStatusCode() + "\t" + entity.getHeaders());
            return entity.getBody();
        }
        return new CommonResult<>(444, "操作失败");
    }

    @GetMapping("/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/" + id, CommonResult.class);
    }

    @GetMapping("/getForEntity/{id}")
    public CommonResult<Payment> getPaymentById1(@PathVariable Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/" + id, CommonResult.class);

        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info(entity.getStatusCode() + "\t" + entity.getHeaders());
            return entity.getBody();
        }
        return new CommonResult<>(444, "操作失败");
    }

    @GetMapping("/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances.isEmpty()) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        return restTemplate.getForObject(serviceInstance.getUri() + "/payments/lb", String.class);
    }

    @GetMapping("/zipkin")
    public String zipkin() {
        return restTemplate.getForObject(PAYMENT_URL + "/zipkin", String.class);
    }
}
