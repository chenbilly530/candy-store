package com.microservice.candyorderservice.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CandyOrderLine {

    UUID id;

    int quantity;
}
