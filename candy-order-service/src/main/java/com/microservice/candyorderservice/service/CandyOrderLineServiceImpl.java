package com.microservice.candyorderservice.service;

import com.microservice.candyorderservice.entity.CandyOrderLine;
import org.springframework.stereotype.Service;

@Service
public class CandyOrderLineServiceImpl implements CandyOrderLineService {

    public boolean isValidOrderLine(CandyOrderLine candyOrderLine) {
        return true;
    }
}
