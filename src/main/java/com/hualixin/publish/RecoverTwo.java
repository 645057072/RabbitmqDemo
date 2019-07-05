package com.hualixin.publish;

import com.rabbitmq.client.*;
import com.hualixin.util.ConnexttionUtil;

import java.io.IOException;

public class RecoverTwo {

    private static final String EXCHANGE_NAME="testexchenge";

    public static void main(String[] args)throws  Exception {
        Connection connection = ConnexttionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare("testqueue2", false, false, false, null);
        channel.queueBind("testqueue2", EXCHANGE_NAME, "");
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("订阅者收到消息2" + new String(body));

                channel.basicAck(envelope.getDeliveryTag(), false);//参数2：false为确认收到消息，true为拒绝收到消息
            }
        };
        channel.basicConsume("testqueue2", false, consumer);
    }
}
