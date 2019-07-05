package com.hualixin.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Springtest {
    public static void main(String[] args)throws Exception {
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");
        RabbitTemplate template=context.getBean(RabbitTemplate.class);
        template.convertAndSend("spring 发送的消息");
        ((ClassPathXmlApplicationContext) context).destroy();
    }
}
