package com.microservice.candyorderservice.entity;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CandyOrder {

    UUID id;

    UUID customerId;

    List<CandyOrderLine> orderLines;
}
