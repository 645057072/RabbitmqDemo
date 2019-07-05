package com.hualixin.spring2;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

@Component("cofirmCallBackListener")
public class CofirmCallBackListener implements RabbitTemplate.ConfirmCallback {


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.err.println("确认回调 ack=="+ack+"      cause=="+cause);
    }
}
