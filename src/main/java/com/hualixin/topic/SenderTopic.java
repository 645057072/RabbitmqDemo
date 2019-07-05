package com.hualixin.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.hualixin.util.ConnexttionUtil;

public class SenderTopic {
    private final static String EXCHANGE_NAME="testtopic";

    public static void main(String[] args)throws Exception {
        Connection connection = ConnexttionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
        channel.basicPublish(EXCHANGE_NAME,"key.1.2",null,"topic模式消息".getBytes());
        channel.close();
        connection.close();

    }
}
