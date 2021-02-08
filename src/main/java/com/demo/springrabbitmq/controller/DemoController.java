package com.demo.springrabbitmq.controller;


import com.demo.springrabbitmq.bean.DemoMessage;
import com.demo.springrabbitmq.mq.Producer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private Producer producer;

    @GetMapping("test")
    public String sayHello() {
        return String.format("%s-%s", "Hello, world!", LocalDateTime.now());
    }

    @GetMapping("sendDirect")
    public String sendMessage() {
        DemoMessage message = DemoMessage.builder().msgId(UUID.randomUUID().toString())
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .data("Hello, world!")
                .build();
        producer.sendDirect(message);
        return "SendMessageOK";
    }

    @GetMapping("sendTopicOne")
    public String sendTopicOne() {
        DemoMessage message = DemoMessage.builder().msgId(UUID.randomUUID().toString())
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .data("Hello, world, topic1!")
                .build();
        producer.sendTopicOne(message);
        return "SendMessageOKTopic1";
    }


    @GetMapping("sendTopicTwo")
    public String sendTopicTwo() {
        DemoMessage message = DemoMessage.builder().msgId(UUID.randomUUID().toString())
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .data("Hello, world, topic2!")
                .build();
        producer.sendTopicTwo(message);
        return "SendMessageOKTopic2";
    }


    @GetMapping("sendTopicThree")
    public String sendTopicThree() {
        DemoMessage message = DemoMessage.builder().msgId(UUID.randomUUID().toString())
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .data("Hello, world, topic3!")
                .build();
        producer.sendTopicThree(message);
        return "SendMessageOKTopic3";
    }


    @GetMapping("sendFanoutMsg")
    public String sendFanoutMsg() {
        DemoMessage message = DemoMessage.builder().msgId(UUID.randomUUID().toString())
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .data("Hello, world, fanoutMsg !")
                .build();
        producer.sendFanoutMessage(message);
        return "SendMessageOKFanout";
    }

    @GetMapping("sendHeaderMsg")
    public String sendHeaderMsg() {
        String message = "hello, world! header message!";
        producer.sendHeaderAMsg(message);
        return "SendMessageOKHeader.";
    }

    @GetMapping("sendDelayMsg")
    public String sendDelayMsg() {
        DemoMessage message = DemoMessage.builder().msgId(UUID.randomUUID().toString())
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .data("Hello, world, DelayMsg !")
                .build();
        producer.sendDelayMsg(message, 30000);
        return "SendDelayMessageOK";
    }

    @GetMapping("sendDelayMsg2")
    public String sendDelayMsg2() {
        DemoMessage message = DemoMessage.builder().msgId(UUID.randomUUID().toString())
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .data("Hello, world, DelayMsg22 !")
                .build();
        producer.sendDelayMsg2(message, 30000);
        return "SendDelayMessageOK2";
    }
}
