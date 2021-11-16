package com.example.proyectotesting.repository;

import com.example.proyectotesting.entities.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long>{


    List<Manufacturer> findByYear(Integer year);

    List<Manufacturer> findManufacturerByDirectionCountry(String country);
}
