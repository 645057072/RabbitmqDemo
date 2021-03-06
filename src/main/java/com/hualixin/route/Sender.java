package com.hualixin.route;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.hualixin.util.ConnexttionUtil;

public class Sender {
    /**
     * 使用Rabbitmq中路由模式发送消息
     */
    private final static String EXCHANGE_NAME="testroute";

    public static void main(String[] args)throws Exception {
        Connection connection = ConnexttionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");//设置路由模式
        channel.basicPublish(EXCHANGE_NAME,"key2",null,"路由消息".getBytes());
        channel.close();
        connection.close();
    }
}
