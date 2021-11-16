package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    Optional<Manufacturer> findOne(Long id);

    List<Manufacturer> findByYear(Integer year);

    Manufacturer save(Manufacturer manufacturer);

    long count();

    boolean deleteById(Long id);

    List<Manufacturer> findManufacturerByCountry(String country);

    boolean deleteAll();
}
