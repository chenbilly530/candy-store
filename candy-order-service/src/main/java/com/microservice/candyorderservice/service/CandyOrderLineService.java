package com.microservice.candyorderservice.service;

import com.microservice.candyorderservice.entity.CandyOrderLine;
import org.springframework.stereotype.Service;

public interface CandyOrderLineService {

    public boolean isValidOrderLine(CandyOrderLine candyOrderLine);
}
