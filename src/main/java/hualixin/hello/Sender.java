package hualixin.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import hualixin.util.ConnexttionUtil;

public class Sender {

    private static final String QUEUE="testhello";
    public static void main(String[] args) throws Exception {

        //创建连接
        Connection connection= ConnexttionUtil.getConnection();
       //创建通道
        Channel channel=connection.createChannel();
        //获取参数
        //参数1:队列的名字
        //参数2：是否持久化，我们的队列是存在内存中的，如果rabbitmq重启会失效，如果我们设置为true，则会保存到erlang自带数据库，重启后会自动读取
        //参数3：是否排外，有两个作用，第一个当我们的连接关闭后，是否自动删除，作用二，是否私有当天的队列，如果私有了，其他通道不可访问
        //参数4：自动删除
        //参数5：我们的一些其他参数
        channel.queueDeclare(QUEUE,false,false,false,null);
        //发送消息
        channel.basicPublish("",QUEUE,null,"发送消息".getBytes());
        //关闭连接
        channel.close();
    }


}
