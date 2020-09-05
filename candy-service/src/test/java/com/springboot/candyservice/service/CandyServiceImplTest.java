package com.springboot.candyservice.service;

import com.springboot.candyservice.entity.Candy;
import com.springboot.candyservice.entity.CandyType;
import com.springboot.candyservice.repository.CandyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandyServiceImplTest {

    @InjectMocks
    private CandyServiceImpl candyService;

    @Mock
    private CandyRepository candyRepository;

    @Captor
    private ArgumentCaptor<Candy> candyCaptor;

    private UUID id;

    @BeforeEach
    public void setup(){
        id = UUID.randomUUID();
    }

    @Test
    void getCandyById_shouldReturnAnTargetCandy_whenFindAMatchCandy() {

        when(candyRepository.findById(any(UUID.class))).thenReturn(Optional.of(createAnCandy()));

        Candy result = candyService.getCandyById(id);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Chocolate");
        assertThat(result.getDescription()).isEqualTo("chocolate description");
        assertThat(result.getType()).isEqualTo(CandyType.HARD_CANDY);
    }

    @Test
    void addCandy_shouldAddAnNewCandy() {
        when(candyRepository.findById(any(UUID.class))).thenReturn(null);

        Candy result = candyService.addCandy(createAnCandy());

        verify(candyRepository, times(1)).findById(any(UUID.class));
        verify(candyRepository, times(1)).save(candyCaptor.capture());
        Candy savedCandy =  candyCaptor.getValue();
        assertThat(savedCandy).isNotNull();
        assertThat(savedCandy.getName()).isEqualTo("Chocolate");
        assertThat(savedCandy.getDescription()).isEqualTo("chocolate description");
        assertThat(savedCandy.getType()).isEqualTo(CandyType.HARD_CANDY);
    }

    private Candy createAnCandy(){
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