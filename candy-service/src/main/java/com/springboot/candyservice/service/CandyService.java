package com.springboot.candyservice.service;

import com.springboot.candyservice.entity.Candy;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface CandyService {

    public Candy getCandyById(UUID id);

    public Candy addCandy(Candy newCandy);
}
