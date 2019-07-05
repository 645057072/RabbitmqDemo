package com.hualixin.topic;

import com.rabbitmq.client.*;
import com.hualixin.util.ConnexttionUtil;

import java.io.IOException;

public class RecoverTopicOne {
    private final static String EXCHANGE_NEMA="testtopic";

    public static void main(String[] args)throws Exception {
        Connection connection = ConnexttionUtil.getConnection();
       final Channel channel = connection.createChannel();
        channel.queueDeclare("testtopic1",false,false,false,null);
        channel.queueBind("testtopic1",EXCHANGE_NEMA,"key.*");
        channel.basicQos(1);
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)throws IOException{
                System.out.println("接收到topic消息1:"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume("testtopic1",false,consumer);
    }
}
