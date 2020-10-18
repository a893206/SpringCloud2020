package com.cr.springcloud.dao;

import com.cr.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cr
 * @date 2020-10-17 16:37
 */
@Mapper
public interface PaymentDao {
    int create(Payment payment);

    Payment getPaymentById(Long id);
}
