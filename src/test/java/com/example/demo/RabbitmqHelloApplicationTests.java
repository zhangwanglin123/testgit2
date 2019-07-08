package com.example.demo;


import com.example.demo.rabbitmq.MsgProducer;
import com.example.demo.rabbitmq.MsgReceiverC_one;
import com.example.demo.rabbitmq.MsgReceiverC_two;
import com.example.demo.rabbitmq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class RabbitmqHelloApplicationTests {

    @Autowired
    private Sender sender;

    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    private MsgReceiverC_one  one;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MsgReceiverC_two two;

    /*
     * 测试消息队列
     */
    @Test
    public void test1(){
        this.sender.send("Hello RabbitMQ");
    }

 /*   @Test
    public void test2(){
        for(int i=0;i<100;i++){
           // this.msgProducer.sendMsg("这是我发送的第"+i+"个消息");
            this.one.process("这是我发送的第"+i+"个消息");
            this.two.process("这是我发送的第"+i+"个消息");
        }

    }*/

    @Test
    public void contextLoads(){
   Map<String,Object> map  = new HashMap<>();
    map.put("msg","这是第一个消息队列");
        map.put("data", Arrays.asList("helloworld",123,true));
        rabbitTemplate.convertAndSend("my-mq-exchange_A","spring-boot-routingKey_A","这是第一个消息队列");
    }
    @Test
    public void receive(){
        Object o = rabbitTemplate.receive("QUEUE_A");
        System.out.print(o.getClass());
        System.out.print(o);
    }

}
