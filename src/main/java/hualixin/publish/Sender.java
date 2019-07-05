package hualixin.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import hualixin.util.ConnexttionUtil;

public class Sender {
    private static final String EXCHANGE_NAME="testexchenge";

    public static void main(String[] args) throws Exception{
        Connection connection = ConnexttionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");//定义一个交换机，类型为fanout，发布订阅者消息
        //发布订阅者模式，首先发送到交换机中，而交换机没有保存功能的，如果没有订阅者获取消息，消息会消失
        channel.basicPublish(EXCHANGE_NAME,"",false,null,"发布订阅者模式".getBytes());
        channel.close();
        connection.close();
    }
}
