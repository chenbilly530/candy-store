package com.microservice.candyorderservice.service;

import com.microservice.candyorderservice.entity.CandyOrder;

public interface CandyOrderService {

    public boolean placeOrder(CandyOrder candyOrder);

}

