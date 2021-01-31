package com.demo.springrabbitmq.config;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
