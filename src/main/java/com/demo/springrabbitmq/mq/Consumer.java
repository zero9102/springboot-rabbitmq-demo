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


    @RabbitListener(queues = RabbitMQConfig.FANOUT_QUEUE_ONE)
    public void processFanoutOne(DemoMessage demoMessage) {
        log.info("receive fanout1 msg: date: {}" , LocalDateTime.now());
        log.info("[receive fanout1 rabbit mq: {}", demoMessage);
    }

    @RabbitListener(queues = RabbitMQConfig.FANOUT_QUEUE_TWO)
    public void processFanoutTwo(DemoMessage demoMessage) {
        log.info("receive fanout2 msg: date: {}" , LocalDateTime.now());
        log.info("[receive fanout2 rabbit mq: {}", demoMessage);
    }

    @RabbitListener(queues = RabbitMQConfig.FANOUT_QUEUE_THREE)
    public void processFanoutThree(DemoMessage demoMessage) {
        log.info("receive fanout3 msg: date: {}" , LocalDateTime.now());
        log.info("[receive fanout3 rabbit mq: {}", demoMessage);
    }


    @RabbitListener(queues = RabbitMQConfig.HEADERS_QUEUE_A)
    public void processHeadersA(byte[] demoMessage) {
        log.info("receive headerA msg: date: {}" , LocalDateTime.now());
        log.info("[receive headerA rabbit mq: {}", new String(demoMessage));
    }

    @RabbitListener(queues = RabbitMQConfig.HEADERS_QUEUE_B)
    public void processHeadersB(byte[] demoMessage) {
        log.info("receive headerB msg: date: {}" , LocalDateTime.now());
        log.info("[receive headerB rabbit mq: {}", new String(demoMessage));
    }

    @RabbitListener(queues = RabbitMQConfig.DELAY_QUEUE)
    public void processDelayQueue(DemoMessage demoMessage) {
        log.info("receive delayQueue msg: date: {}" , LocalDateTime.now());
        log.info("[receive delayQueue rabbit mq: {}", demoMessage);
    }

    @RabbitListener(queues = RabbitMQConfig.DEAD_LETTER_QUEUE)
    public void processDelayQueue2(DemoMessage demoMessage) {
        log.info("receive delayQueue2 msg: date: {}" , LocalDateTime.now());
        log.info("[receive delayQueue2 rabbit mq: {}", demoMessage);
    }
}
