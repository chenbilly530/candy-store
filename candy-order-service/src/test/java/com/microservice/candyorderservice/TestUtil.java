package com.microservice.candyorderservice;

import com.microservice.candyorderservice.entity.CandyOrder;
import com.microservice.candyorderservice.entity.CandyOrderLine;

import java.util.Arrays;
import java.util.UUID;

public class TestUtil {

    public static CandyOrder createCandyOrder(UUID customerId, UUID orderLineId1, UUID orderLineId2){
        CandyOrderLine candyOrderLine1 =
                new CandyOrderLine()
                        .builder()
                        .id(orderLineId1)
                        .quantity(20)
                        .build();

        CandyOrderLine candyOrderLine2 =
                new CandyOrderLine()
                        .builder()
                        .id(orderLineId2)
                        .quantity(30)
                        .build();
        CandyOrder candyOrder =
                new CandyOrder()
                        .builder()
                        .customerId(customerId)
                        .orderLines(Arrays.asList(candyOrderLine1, candyOrderLine2))
                        .id(UUID.randomUUID())
                        .build();

        return  candyOrder;
    }
}
