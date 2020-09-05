package com.springboot.candyservice.service;

import com.springboot.candyservice.entity.Candy;
import com.springboot.candyservice.repository.CandyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CandyServiceImpl implements CandyService {

    private CandyRepository candyRepository;

    public CandyServiceImpl(CandyRepository candyRepository) {
        this.candyRepository = candyRepository;
    }

    @Override
    public Candy getCandyById(UUID id) {
        Optional<Candy> resOptional = candyRepository.findById(id);
        return resOptional.orElse(null);
    }

    @Override
    public Candy addCandy(Candy newCandy) {
        if (candyRepository.findById(newCandy.getId()) == null){
            return candyRepository.save(newCandy);
        }
        return null;
    }
}
