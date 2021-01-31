package com.demo.springrabbitmq.mq;

import com.demo.springrabbitmq.bean.DemoMessage;
import com.demo.springrabbitmq.config.RabbitMQConfig;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *  RabbitMQ 接收消息。
 */
@Slf4j
@Component
public class Consumer {

    /**
     *  direct queue
     * @param demoMessage
     */
    @RabbitListener(queues = RabbitMQConfig.DIRECT_QUEUE)
    public void process(DemoMessage demoMessage) {
        log.info("receive direct msg: date: {}" , LocalDateTime.now());
        log.info("[receive direct rabbit mq: {}", demoMessage);
    }

    /**
     *  topic queue one
     */
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE_ONE)
    public void processTopic1(DemoMessage demoMessage) {
        log.info("receive topic1 msg: date: {}" , LocalDateTime.now());
        log.info("[receive topic1 rabbit mq: {}", demoMessage);
    }

    /**
     *  topic queue two
     */
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE_TWO)
    public void processTopic2(DemoMessage demoMessage) {
        log.info("receive topic2 msg: date: {}" , LocalDateTime.now());
        log.info("[receive topic2 rabbit mq: {}", demoMessage);
    }
}
