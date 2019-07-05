package com.hualixin.spring2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ApplicationContext2.xml"})
public class TestMain {
        @Autowired
        private PublishUtil publishUtil;
        private static String exCHANGE="DIRECT_EX";
        private static String queue="CONFIRMS_TESTS";

    /**exchange,queue全对
     * 确认回调 ack==true cause==null
     * @throws Exception
     */
    @Test
        public void test()throws Exception{
            String message="当前时间是:"+System.currentTimeMillis();
            publishUtil.send(exCHANGE,queue,message);
        }

    /**参数：exchange错误,queue对
     *消费者确认收到了消息(Body:'当前时间是:1562317422201' MessageProperties [headers={spring_listener_return_correlation=482b7ab2-6f38-451d-835a-3dc1a923a408}, timestamp=null, messageId=null, userId=null, receivedUserId=null, appId=null, clusterId=null, type=null, correlationId=null, correlationIdString=null, replyTo=null, contentType=text/plain, contentEncoding=UTF-8, contentLength=0, deliveryMode=null, receivedDeliveryMode=PERSISTENT, expiration=null, priority=0, redelivered=true, receivedExchange=DIRECT_EX, receivedRoutingKey=CONFIRMS_TESTS, receivedDelay=null, deliveryTag=1, messageCount=0, consumerTag=amq.ctag-HIyT6ucHwFQlzbQAc2ofWQ, consumerQueue=CONFIRMS_TESTS])
     * 结果：确认回调 ack==false      cause==channel error; protocol method: #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no exchange 'DIRECT_EX1' in vhost 'test', class-id=60, method-id=40)
     * @throws Exception
     */
    @Test
    public void test2()throws Exception{
        String message="当前时间是:"+System.currentTimeMillis();
        publishUtil.send(exCHANGE+1,queue,message);
    }

    /**参数：exchange对,queue错
     * 消费者确认收到了消息(Body:'当前时间是:1562317422201' MessageProperties [headers={spring_listener_return_correlation=482b7ab2-6f38-451d-835a-3dc1a923a408}, timestamp=null, messageId=null, userId=null, receivedUserId=null, appId=null, clusterId=null, type=null, correlationId=null, correlationIdString=null, replyTo=null, contentType=text/plain, contentEncoding=UTF-8, contentLength=0, deliveryMode=null, receivedDeliveryMode=PERSISTENT, expiration=null, priority=0, redelivered=true, receivedExchange=DIRECT_EX, receivedRoutingKey=CONFIRMS_TESTS, receivedDelay=null, deliveryTag=1, messageCount=0, consumerTag=amq.ctag-X477QSsIS7s38ay4aB6VcQ, consumerQueue=CONFIRMS_TESTS])
     * 失败 messsage==当前时间是:1562318510857  replyCode==NO_ROUTE  replyText==NO_ROUTE  exchange==DIRECT_EX  rotingkeyCONFIRMS_TESTS1
     * 确认回调 ack==true      cause==null
     * @throws Exception
     */
    @Test
    public void test3()throws Exception{
        String message="当前时间是:"+System.currentTimeMillis();
        publishUtil.send(exCHANGE,queue+1,message);
    }

    /**参数：exchange错,queue错
     * 消费者确认收到了消息(Body:'当前时间是:1562317422201' MessageProperties [headers={spring_listener_return_correlation=482b7ab2-6f38-451d-835a-3dc1a923a408}, timestamp=null, messageId=null, userId=null, receivedUserId=null, appId=null, clusterId=null, type=null, correlationId=null, correlationIdString=null, replyTo=null, contentType=text/plain, contentEncoding=UTF-8, contentLength=0, deliveryMode=null, receivedDeliveryMode=PERSISTENT, expiration=null, priority=0, redelivered=true, receivedExchange=DIRECT_EX, receivedRoutingKey=CONFIRMS_TESTS, receivedDelay=null, deliveryTag=1, messageCount=0, consumerTag=amq.ctag-Va-KsuiO7y1cVr5mQd5A6g, consumerQueue=CONFIRMS_TESTS])
     * 确认回调 ack==false      cause==channel error; protocol method: #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no exchange 'DIRECT_EX1' in vhost 'test', class-id=60, method-id=40)
     * @throws Exception
     */
    @Test
    public void test4()throws Exception{
        String message="当前时间是:"+System.currentTimeMillis();
        publishUtil.send(exCHANGE+1,queue+1,message);
    }
}
