package com.demo.springrabbitmq.mq;

import com.demo.springrabbitmq.bean.DemoMessage;
import com.demo.springrabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  消息生产者.
 */
@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDirect(DemoMessage message) {
        sendMsg(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.DIRECT_BINDING_ROUTING_KEY,
                message);
    }

    public void sendTopicOne(DemoMessage message) {
        sendMsg(RabbitMQConfig.TOPIC_EXCHANGE, RabbitMQConfig.TOPIC_QUEUE_ONE, message);
    }

    public void sendTopicTwo(DemoMessage message) {
        sendMsg(RabbitMQConfig.TOPIC_EXCHANGE, RabbitMQConfig.TOPIC_QUEUE_TWO, message);
    }

    public void sendTopicThree(DemoMessage message) {
        sendMsg(RabbitMQConfig.TOPIC_EXCHANGE, RabbitMQConfig.TOPIC_QUEUE_THREE, message);
    }

    private void sendMsg(String exchange, String keyQueue, DemoMessage message) {
        rabbitTemplate.convertAndSend(exchange, keyQueue,
                message);
    }
}
