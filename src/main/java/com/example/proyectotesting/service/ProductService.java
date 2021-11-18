package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findOne(Long id);

    boolean existsById(Long id);

    List<Product> findByPriceBetween(Double min, Double max);

    List<Product> findByManufacturer(String manufacturer);

    Double calculateShippingCost(Product product, Direction direction);

    Product save(Product product);

    long count();

    boolean deleteById(Long id);

    boolean deleteAll();
}
