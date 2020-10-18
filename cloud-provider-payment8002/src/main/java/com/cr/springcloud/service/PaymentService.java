package com.cr.springcloud.service;

import com.cr.springcloud.entities.Payment;

/**
 * @author cr
 * @date 2020-10-17 17:08
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(Long id);
}
