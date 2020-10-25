package com.cr.springcloud.service.impl;

import com.cr.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * @author cr
 * @date 2020-10-25 23:27
 */
@EnableBinding(Source.class)
public class IMessageProviderImpl implements IMessageProvider {
    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        System.out.println("serial = " + serial);
        output.send(MessageBuilder.withPayload(serial).build());
        return serial;
    }
}
