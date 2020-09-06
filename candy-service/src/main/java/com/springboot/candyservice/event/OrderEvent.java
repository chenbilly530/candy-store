package com.springboot.candyservice.event;

import com.springboot.candyservice.entity.CandyOrder;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEvent implements Serializable {
    static final long serialVersionUID = -3658680415926952163L;

    private UUID id;

    private OrderStatus orderStatus;

    private CandyOrder candyOrder;

}
