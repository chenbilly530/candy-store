package com.microservice.candyorderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.candyorderservice.config.JmsConfig;
import com.microservice.candyorderservice.entity.CandyOrder;
import com.microservice.candyorderservice.entity.CandyOrderLine;
import com.microservice.candyorderservice.event.OrderEvent;
import com.microservice.candyorderservice.event.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.activemq.artemis.jms.client.ActiveMQTextMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CandyOrderServiceImpl implements CandyOrderService{

    private CandyOrderLineServiceImpl candyOrderLineService;

    private JmsTemplate jmsTemplate;

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public boolean placeOrder(CandyOrder candyOrder) {
        if (candyOrder != null){
            List<CandyOrderLine> candyOrderLines =
                    candyOrder.getOrderLines();
            for (CandyOrderLine candyOrderLine :  candyOrderLines){
                Boolean isValid = candyOrderLineService.isValidOrderLine(candyOrderLine);
                if  (! isValid){
                    return false;
                }
            }
            OrderEvent orderEvent =
                            OrderEvent
                                .builder()
                                .id(UUID.randomUUID())
                                .orderStatus(OrderStatus.NEW)
                                .candyOrder(candyOrder)
                                .build();

            Message receivedMessage = jmsTemplate.sendAndReceive(JmsConfig.PLACE_ORDER, new MessageCreator() {
                        @Override
                        public Message createMessage(Session session) throws JMSException {
                            Message placeOrderMessage = null;
                            try {
                                placeOrderMessage = session.createTextMessage(objectMapper.writeValueAsString(orderEvent));
                                placeOrderMessage.setStringProperty("_type", "com.springboot.candyservice.event.OrderEvent");
                                return placeOrderMessage;
                            } catch(JsonProcessingException ex){
                                throw new JMSException("JMS Exception");
                            }
                        }
            });

            String receivedOrderEventJson = ((ActiveMQTextMessage) receivedMessage).getText();
            OrderEvent receivedOrderEvent = objectMapper.readValue(receivedOrderEventJson, OrderEvent.class);
            System.out.println(receivedOrderEvent.toString());
            return true;
        }
        return false;
    }
}
