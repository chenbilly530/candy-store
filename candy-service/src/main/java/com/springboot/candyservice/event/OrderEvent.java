package com.microservice.candyorderservice.event;

import com.microservice.candyorderservice.entity.CandyOrder;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEvent implements Serializable {
    static final long serialVersionUID = 6068603155243264383L;

    private UUID id;

    private OrderStatus orderStatus;

    private CandyOrder candyOrder;

}
