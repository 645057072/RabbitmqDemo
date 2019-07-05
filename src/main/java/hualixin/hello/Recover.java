package hualixin.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import hualixin.util.ConnexttionUtil;


public class Recover {
    private static final String QUEUE="testhello";
    public static void main(String[] args)throws Exception {
        //创建连接
        Connection connection= ConnexttionUtil.getConnection();
        //创建通道
        Channel channel=connection.createChannel();
        channel.queueDeclare(QUEUE,false,false,false,null);
      QueueingConsumer consumer=new QueueingConsumer(channel);//创建一个消费者信息
        //获取连接 参数2：自动获取
        channel.basicConsume(QUEUE,true, consumer);//

        QueueingConsumer.Delivery delivery=consumer.nextDelivery();//如果没有消息会自行等待，如果有获取后会自动销毁，是一次性的
        String message=new String(delivery.getBody());
        System.out.println(message);
    }
}
