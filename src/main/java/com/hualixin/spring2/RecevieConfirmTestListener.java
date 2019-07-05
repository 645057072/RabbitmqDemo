package com.hualixin.spring2;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component("recevieConfirmTestListener")
public class RecevieConfirmTestListener implements ChannelAwareMessageListener {
    /**
     * 收到消息的时候执行的监听
     * @param message
     * @param channel
     * @throws Exception
     */

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        try {
            System.err.println("消费者确认收到了消息" + message);
        } catch (Exception e){
            e.printStackTrace();
            //参数2：false为确认收到消息，true为拒绝收到消息
        channel.basicAck(Long.parseLong(message.getMessageProperties().getConsumerTag()), false);
    }
    }
}
