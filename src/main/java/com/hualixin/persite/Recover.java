package com.hualixin.persite;

import com.hualixin.util.ConnexttionUtil;
import com.rabbitmq.client.*;
import org.springframework.core.NamedInheritableThreadLocal;

import java.io.IOException;

public class Recover {
    private static String EXCAHNGE_NAME="testpersite";
    private static String QUEUE_NAME="testpersitequeue";
    public static void main(String[] args)throws Exception {
        Connection connection = ConnexttionUtil.getConnection();
        final Channel channel = connection.createChannel();
        //声明持久化交换机
        channel.exchangeDeclare(EXCAHNGE_NAME,"direct",true,false, null);
        //声明持久化队列  参数2:消息是否持久化
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        channel.queueBind(QUEUE_NAME,EXCAHNGE_NAME,"abc");

        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接收到持久化消息"+new String(body) );
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //注册消费者,参数2，自动确认，代表我们收到消息后自动告诉服务器，我们收到消息了
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
