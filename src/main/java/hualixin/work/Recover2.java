package hualixin.work;

import com.rabbitmq.client.*;
import hualixin.util.ConnexttionUtil;

import java.io.IOException;

public class Recover2 {
    private static final String QUEUE="testwork";

    public static void main(String[] args)throws Exception {
        Connection connection = ConnexttionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE,false,false,false,null);
        channel.basicQos(1);//告诉服务器在确认接收消息前，不在给消费者发送新消息
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者者2收到的消息："+new String(body));
                try {
                    Thread.sleep(850);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(),false);//参数2：false为确认收到消息，true为拒绝收到消息
            }
        };
        //注册消费者,参数2，手动确认，代表我们收到消息后手动告诉服务器，我们收到消息了
        channel.basicConsume(QUEUE,false, consumer);
    }
}
