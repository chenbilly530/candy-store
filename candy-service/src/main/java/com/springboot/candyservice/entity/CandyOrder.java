package com.springboot.candyservice.entity;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandyOrder {

    UUID id;

    UUID customerId;

    List<CandyOrderLine> orderLines;
}
