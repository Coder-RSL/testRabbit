package com.feidian.Unit2;

import com.rabbitmq.client.Channel;

import java.util.Scanner;

public class Task02 {
    private static final String TASK_QUEUE_NAME="ack_queue";//创建一个队列先
    public static void main(String[] args) throws Exception {
        try(Channel channel=RabbitMqUtils.getChannel();)
        {
            channel.queueDeclare(TASK_QUEUE_NAME,false,false,false,null);

            //从控制台当中接受信息
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String message = scanner.next();
                channel.basicPublish("",TASK_QUEUE_NAME,null,message.getBytes());
                System.out.println("发送消息完成:"+message);
            }
        }
    }
}
