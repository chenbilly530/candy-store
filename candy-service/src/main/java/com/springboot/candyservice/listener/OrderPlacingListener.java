package com.springboot.candyservice.listener;

import com.springboot.candyservice.config.JmsConfig;
import com.springboot.candyservice.event.OrderEvent;
import com.springboot.candyservice.event.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderPlacingListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.PLACE_ORDER)
    public void listenForOrder(@Payload OrderEvent incomingEvent,
                               @Headers MessageHeaders headers,
                               Message message) throws JMSException {
        log.info("receive order message " + incomingEvent.toString());
        OrderEvent outgoingEvent =
                           OrderEvent
                                   .builder()
                                   .orderStatus(OrderStatus.APPROVED)
                                   .candyOrder(incomingEvent.getCandyOrder())
                                   .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), outgoingEvent);
    }
}

//@RequiredArgsConstructor
//@Component
//public class HelloWorldMessageListener {
//
//    private final JmsTemplate jmsTemplate;
//
////    @JmsListener(destination = JmsConfig.MY_QUEUE)
////    public void listen(@Payload HelloWorldMessage helloWorldMessage,
////                       @Headers MessageHeaders headers,
////                       Message message){
////        System.out.println("I got a message");
////        System.out.println(helloWorldMessage);
////    }
//
//    @JmsListener(destination = JmsConfig.MY_SEND_RECEIVE_QUEUE)
//    public void listenForHello(@Payload HelloWorldMessage helloWorldMessage,
//                               @Headers MessageHeaders headers,
//                               Message message) throws JMSException {
////        System.out.println("I got a message");
////        System.out.println(helloWorldMessage);
//        HelloWorldMessage playloadMessage = HelloWorldMessage
//                .builder()
//                .id(UUID.randomUUID())
//                .message("World")
//                .build();
//
//        jmsTemplate.convertAndSend(message.getJMSReplyTo(), playloadMessage);
//    }
//}

