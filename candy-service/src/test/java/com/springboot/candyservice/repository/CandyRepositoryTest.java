package com.springboot.candyservice.repository;

import com.springboot.candyservice.BaseIntegrationTest;
import com.springboot.candyservice.entity.Candy;
import com.springboot.candyservice.entity.CandyType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CandyRepositoryTest extends BaseIntegrationTest {

    @Autowired
    private CandyRepository candyRepository;

    @Test
    public void testFindCandyById() {
        Candy candy =  createCandy();
        UUID id = candy.getId();
        candyRepository.save(candy);

        Optional<Candy> savedCandyOptional = candyRepository.findById(id);
        Candy savedCandy = null;
        if  (savedCandyOptional.isPresent()){
            savedCandy = savedCandyOptional.get();
        }
        assertThat(savedCandy).isNotNull();
        assertThat(savedCandy.getName()).isEqualTo("Chocolate");
        assertThat(savedCandy.getDescription()).isEqualTo("chocolate description");
        assertThat(savedCandy.getType()).isEqualTo(CandyType.HARD_CANDY);

    }

    private Candy createCandy(){
        Candy candy = new Candy()
                .builder()
                .id(UUID.randomUUID())
                .name("Chocolate")
                .description("chocolate description")
                .type(CandyType.HARD_CANDY)
                .build();

        return candy;
    }
}