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
@ToString
public class OrderEvent implements Serializable {
    static final long serialVersionUID = -3658680415926952163L;

    private UUID id;

    private OrderStatus orderStatus;

    private CandyOrder candyOrder;

}
