package com.demo.springrabbitmq.config;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

/**
 *  rabbitmq 配置.
 */
@Configuration
public class RabbitMQConfig {

    /**
     *  直连队列
     */
    public static final String DIRECT_QUEUE = "demoDirectQueue";

    /**
     *  直连交换机
     */
    public static final String DIRECT_EXCHANGE = "demoDirectExchange";

    /**
     *  直连绑定路由 键
     */
    public static final String DIRECT_BINDING_ROUTING_KEY = "demoDirectRoutingKey";

    public static final String TOPIC_QUEUE_ONE = "topicQueue.one";
    public static final String TOPIC_QUEUE_TWO = "topicQueue.two";
    public static final String TOPIC_QUEUE_THREE = "topicQueue.three";
    public static final String TOPIC_QUEUE_PATTERN = "topicQueue.#";
    public static final String TOPIC_EXCHANGE = "topicExchange";

    public static final String FANOUT_QUEUE_ONE = "fanoutQueue.ONE";
    public static final String FANOUT_QUEUE_THREE = "fanoutQueue.THREE";
    public static final String FANOUT_QUEUE_TWO = "fanoutQueue.TWO";
    public static final String FANOUT_EXCHANGE = "fanoutExchange";

    public static final String HEADERS_QUEUE_A = "headersQueueA";
    public static final String HEADERS_QUEUE_B = "headersQueueB";
    public static final String HEADERS_EXCHANGE = "headerExchange";

    public static final String DELAY_EXCHANGE = "delayExchange";
    public static final String DELAY_QUEUE = "delayQueue";
    public static final String DELAY_KEY = "delayKey";

    public static final String DELAY_DIRECT_EX = "delayDirectExchange";
    public static final String DELAY_QUEUE2 = "delayQueue2";
    public static final String DELAY_KEY2 = "delayKey2";

    public static final String DEAD_LETTER_EXCHANGE = "deadLetterExchange";
    public static final String DEAD_LETTER_QUEUE = "deadLetterQueue";
    public static final String DEAD_LETTER_KEY = "deadLetterKey";



    /**
     *  声明 直连 队列
     */
    @Bean
    public Queue demoDirectQueue() {
        return new Queue(DIRECT_QUEUE, true);
    }

    /**
     *  声明直连 交换机
     */
    @Bean
    public DirectExchange demoDirectExchange() {
        return new DirectExchange(DIRECT_EXCHANGE, true, true);
    }

    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(demoDirectQueue()).to(demoDirectExchange()).with(DIRECT_BINDING_ROUTING_KEY);
    }

    @Bean
    public Queue topicQueueOne() {
        return new Queue(TOPIC_QUEUE_ONE, true);
    }

    @Bean
    public Queue topicQueueTwo() {
        return new Queue(TOPIC_QUEUE_TWO, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding bindingTopicExchange() {
        return BindingBuilder.bind(topicQueueOne()).to(topicExchange()).with(TOPIC_QUEUE_ONE);
    }

    @Bean
    public Binding bindingTopicExchange2() {
        return BindingBuilder.bind(topicQueueTwo()).to(topicExchange()).with(TOPIC_QUEUE_PATTERN);
    }

    @Bean
    public Queue fanoutQueueOne() {
        return new Queue(FANOUT_QUEUE_ONE);
    }

    @Bean
    public Queue fanoutQueueTwo() {
        return new Queue(FANOUT_QUEUE_TWO);
    }

    @Bean
    public Queue fanoutQueueThree() {
        return new Queue(FANOUT_QUEUE_THREE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding bindingFanoutOneExchange() {
        return BindingBuilder.bind(fanoutQueueOne()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutTwoExchange() {
        return BindingBuilder.bind(fanoutQueueTwo()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutThreeExchange() {
        return BindingBuilder.bind(fanoutQueueThree()).to(fanoutExchange());
    }

    /**
     *  headers config
     */
    @Bean
    public Queue headersQueueA() {
        return new Queue(HEADERS_QUEUE_A);
    }

    @Bean
    public Queue headersQueueB() {
        return new Queue(HEADERS_QUEUE_B);
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(HEADERS_EXCHANGE);
    }

    @Bean
    public Binding bindingHeadersA() {
        Map<String, Object> headerMap = new HashMap<>(4);
        headerMap.put("headerA", "sms");
        return BindingBuilder.bind(headersQueueA()).to(headersExchange()).whereAll(headerMap).match();
    }

    @Bean
    public Binding bindingHeadersB() {
        Map<String, Object> headerMap = new HashMap<>(4);
        headerMap.put("headerB", "email");
        return BindingBuilder.bind(headersQueueB()).to(headersExchange()).whereAll(headerMap).match();
    }


    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        //属性参数 交换机名称 交换机类型 是否持久化 是否自动删除 配置参数
        return new CustomExchange(DELAY_EXCHANGE, "x-delayed-message",
                true, false, args);
    }

    @Bean
    public Queue delayQueue() {
        //属性参数 队列名称 是否持久化
        return new Queue(DELAY_QUEUE, true);
    }


    /**
     * 给延时队列绑定交换机
     */
    @Bean
    public Binding configureDelayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_KEY).noargs();
    }


    @Bean
    public DirectExchange delayDirectExchange() {
        return new DirectExchange(DELAY_DIRECT_EX);
    }

    /**
     *  consumer to DEAD_LETER_QUEUE, not delay queue.
     */
    @Bean
    public Queue delayQueue2() {
        Map<String, Object> args = new HashMap<>(4);
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", DEAD_LETTER_KEY);
        // 过期时间10s, 可以在消息属性中设置， 一般不这样设置， 这样容易导致一个队列的延迟时间是10s
        // 如果messge 属性中设置了Expiration， 那么此处生效。
        args.put("x-message-ttl", 10000);
        return new Queue(DELAY_QUEUE2, true, false, false, args);
    }

    @Bean
    public Binding configDelayBinging() {
        return BindingBuilder.bind(delayQueue2()).to(delayDirectExchange()).with(DELAY_KEY2);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    /**
     * 死信接收队列
     *
     * @return
     */
    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DEAD_LETTER_QUEUE);
    }

    /**
     * 死信交换机绑定消费队列
     *
     * @return
     */
    @Bean
    public Binding userOrderReceiveBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DEAD_LETTER_KEY);
    }
}
