package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    Optional<Category> findOne(Long id);

    boolean existsById(Long id);

    Optional<Category> findOne(String color);

    Category save(Category category);

    long count();

    boolean deleteById(Long id);
    boolean deleteAll();
}
