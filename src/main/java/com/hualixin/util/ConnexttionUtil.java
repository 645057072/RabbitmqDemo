package com.hualixin.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnexttionUtil {

    public static Connection getConnection()throws Exception{
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("192.168.182.128");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("test");
        connectionFactory.setPassword("test");
        connectionFactory.setVirtualHost("test");
        return connectionFactory.newConnection();//创建一个新的连接
    }
}
