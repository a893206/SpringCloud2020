package con.cr.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author cr
 * @date 2020-10-21 20:48
 */
@Component
public class PaymentFallbackServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfoOk(Integer id) {
        return "PaymentFallbackServiceImpl，paymentInfoOk";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return "PaymentFallbackServiceImpl，paymentInfoTimeOut";
    }
}
