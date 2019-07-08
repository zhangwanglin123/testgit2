package com.example.demo.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MsgReceiver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

   /* @RabbitHandler
    public void process(String content) {
        logger.info("接收处理队列A当中的消息： " + content);
    }*/


}
