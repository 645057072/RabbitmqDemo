package com.hualixin.persite;

import com.hualixin.util.ConnexttionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

public class Sender {
    /**
     * 使用交换机模式发送持久化消息
     */

    private static String EXCAHNGE_NAME="testpersite";
    public static void main(String[] args)throws Exception{
        Connection connection = ConnexttionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明持久化的交换机.参数2：消息是否持久化
        channel.exchangeDeclare(EXCAHNGE_NAME,"direct",true,false,null);
        //发送持久化消息
        channel.basicPublish(EXCAHNGE_NAME,"abc", MessageProperties.PERSISTENT_TEXT_PLAIN,"持久化的消息".getBytes());
        channel.close();
        connection.close();
    }
}
