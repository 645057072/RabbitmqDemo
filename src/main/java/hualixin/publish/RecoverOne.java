package hualixin.publish;

import com.rabbitmq.client.*;
import hualixin.util.ConnexttionUtil;

import java.io.IOException;

public class RecoverOne {
    private static final String EXCHANGE_NAME="testexchenge";


    public static void main(String[] args)throws  Exception{
        Connection connection = ConnexttionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare("testqueue",false,false,false,null);
        channel.queueBind("testqueue",EXCHANGE_NAME,"");
        channel.basicQos(1);
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("订阅者收到消息1"+new String(body));

                channel.basicAck(envelope.getDeliveryTag(),false);//参数2：false为确认收到消息，true为拒绝收到消息
            }
        };
        channel.basicConsume("testqueue",false,consumer);
    }
}
