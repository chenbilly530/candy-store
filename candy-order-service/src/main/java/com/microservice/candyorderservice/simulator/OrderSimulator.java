package com.microservice.candyorderservice.simulator;

import com.microservice.candyorderservice.entity.CandyOrder;
import com.microservice.candyorderservice.entity.CandyOrderLine;
import com.microservice.candyorderservice.service.CandyOrderService;
import com.microservice.candyorderservice.service.CandyOrderServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class OrderSimulator {

    private CandyOrderServiceImpl candyOrderService;

    @Scheduled(fixedRate = 5000)
    public void placeOrder(){
        CandyOrder candyOrder = createCandyOrder();
        candyOrderService.placeOrder(createCandyOrder());
        log.info("Place new order " + candyOrder.getId());
    }

    private CandyOrder createCandyOrder(){

        CandyOrder candyOrder =
                CandyOrder
                        .builder()
                        .id(UUID.randomUUID())
                        .orderLines(createCandyOrderLines())
                        .build();
        return candyOrder;
    }

    private List<CandyOrderLine> createCandyOrderLines(){
        List<CandyOrderLine> candyOrderLines = new ArrayList<>();
        int numOfOrderLines =  (int)(Math.random()*3) + 1;
        for(int i=0; i<numOfOrderLines; i++) {
            int quantity = (int) (Math.random() * 20) + 1;
            CandyOrderLine candyOrderLine =
                    CandyOrderLine
                            .builder()
                            .id(UUID.randomUUID())
                            .quantity(quantity)
                            .build();
            candyOrderLines.add(candyOrderLine);
        }
        return candyOrderLines;
    }
}
