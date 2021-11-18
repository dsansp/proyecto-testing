package com.example.proyectotesting.repository;

import com.example.proyectotesting.entities.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long>{


    List<Direction> findAllById(Long id);

    List<Direction> findByCityAndCountry(String city, String country);
}
