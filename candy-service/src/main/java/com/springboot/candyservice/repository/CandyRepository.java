package com.springboot.candyservice.repository;

import com.springboot.candyservice.entity.Candy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandyRepository extends JpaRepository<Candy, UUID> {
}
