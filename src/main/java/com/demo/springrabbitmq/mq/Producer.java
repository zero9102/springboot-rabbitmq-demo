package com.demo.springrabbitmq.mq;

import com.demo.springrabbitmq.bean.DemoMessage;
import com.demo.springrabbitmq.config.RabbitMQConfig;
import com.rabbitmq.client.AMQP;
import java.util.Hashtable;
import java.util.Map;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
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

    public void sendFanoutMessage(DemoMessage message) {
        sendMsg(RabbitMQConfig.FANOUT_EXCHANGE, null, message);
    }

    public void sendHeaderAMsg(String message) {
        MessageProperties properties = new MessageProperties();
        properties.setHeader("headerA", "sms");
        Message obj = new Message(message.getBytes(), properties);
        rabbitTemplate.convertAndSend(RabbitMQConfig.HEADERS_EXCHANGE, null, obj);
    }

    public void sendDelayMsg(DemoMessage message, int delayTimeMilliSeconds) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE, RabbitMQConfig.DELAY_KEY,
                message, msg -> {
                    msg.getMessageProperties().setDelay(delayTimeMilliSeconds);
                    return msg;
                });
    }

    public void sendDelayMsg2(DemoMessage message, int delayTimeMilliSeconds) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_DIRECT_EX, RabbitMQConfig.DELAY_KEY2,
                message, msg -> {
                    msg.getMessageProperties().setExpiration("" + delayTimeMilliSeconds);
                    return msg;
                });
    }

    private void sendMsg(String exchange, String keyQueue, DemoMessage message) {
        rabbitTemplate.convertAndSend(exchange, keyQueue,
                message);
    }
}
