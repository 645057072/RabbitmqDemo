package hualixin.route;

import com.rabbitmq.client.*;
import hualixin.util.ConnexttionUtil;

import java.io.IOException;

public class RecoverOne {
    private final static String EXCHANGE_NAME="testroute";

    public static void main(String[] args)throws Exception {
        Connection connection = ConnexttionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare("testroutequeue1",false,false,false,null);

        //参数3，标记，绑定到交换机的一个标记，只有交换机上标志与其一直时才可以接收到消息
        channel.queueBind("testroutequeue1",EXCHANGE_NAME,"key1");
        //如果要执行多个消息，只需要多执行一次标记
        //channel.queueBind("",EXCHANGE_NAME,"key2");
        channel.basicQos(1);
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接收到路由消息111"+new String(body) );
                channel.basicAck(envelope.getDeliveryTag(),false);//参数2：false为确认收到消息，true为拒绝收到消息
            }
        };

        channel.basicConsume("testroutequeue1",false,consumer);

    }
}
