package com.microservice.candyorderservice.service;

import com.microservice.candyorderservice.TestUtil;
import com.microservice.candyorderservice.entity.CandyOrder;
import com.microservice.candyorderservice.entity.CandyOrderLine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandyOrderServiceImplTest {

    @InjectMocks
    private CandyOrderServiceImpl candyOrderService;

    @Mock
    private CandyOrderLineServiceImpl candyOrderLine;

    @Mock
    private JmsTemplate jmsTemplate;

    @Captor
    private ArgumentCaptor<CandyOrder> messageArgumentCaptor;

    @Test
    void placeOrder_shouldSendOutJMSMessage_whenOrderIsValid() {

        when(candyOrderLine.isValidOrderLine(any(CandyOrderLine.class))).thenReturn(true);

        UUID customerId = UUID.randomUUID();
        UUID orderLine1 = UUID.randomUUID();
        UUID orderLine2 = UUID.randomUUID();

        Boolean res =  candyOrderService.placeOrder(TestUtil.createCandyOrder(customerId, orderLine1, orderLine2));

        verify(jmsTemplate, times(1)).convertAndSend(anyString(), messageArgumentCaptor.capture());
        CandyOrder candyOrderMessage = messageArgumentCaptor.getValue();
        assertThat(candyOrderMessage.getCustomerId()).isEqualTo(customerId);
        assertThat(candyOrderMessage.getOrderLines().size()).isEqualTo(2);
        List<CandyOrderLine> candyOrderLines = candyOrderMessage.getOrderLines();
        assertThat(candyOrderLines.get(0).getQuantity()).isEqualTo(20);
        assertThat(candyOrderLines.get(1).getQuantity()).isEqualTo(30);
        assertThat(res).isEqualTo(true);
    }

    @Test
    void placeOrder_shouldNotSendOutJMSMessage_whenOrderIsNotValid() {
        when(candyOrderLine.isValidOrderLine(any(CandyOrderLine.class))).thenReturn(false);

        UUID customerId = UUID.randomUUID();
        UUID orderLine1 = UUID.randomUUID();
        UUID orderLine2 = UUID.randomUUID();

        boolean res = candyOrderService.placeOrder(TestUtil.createCandyOrder(customerId, orderLine1, orderLine2));
        verify(jmsTemplate, times(0)).convertAndSend(anyString(), messageArgumentCaptor.capture());
        assertThat(res).isEqualTo(false);
    }

}