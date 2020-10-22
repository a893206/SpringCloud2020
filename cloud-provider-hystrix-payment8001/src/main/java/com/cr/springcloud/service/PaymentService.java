package com.cr.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author cr
 * @date 2020-10-21 13:27
 */
@Service
public class PaymentService {
    public String paymentInfoOk(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfoOk，id：" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    public String paymentInfoTimeOut(Integer id) {
//        int age = 10 / 0;
        int timeout = 3000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfoTimeOut，id：" + id + " 耗时(毫秒)：" + timeout;
    }

    public String paymentInfoTimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " 8001系统繁忙或运行报错，请稍后再试，id：" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + IdUtil.simpleUUID();
    }

    public String paymentCircuitBreakerFallback(Integer id) {
        return "id不能为负数，请稍后再试，id：" + id;
    }
}
