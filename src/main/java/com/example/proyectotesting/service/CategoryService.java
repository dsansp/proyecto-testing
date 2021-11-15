package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Category;

import java.util.List;
import java.util.Optional;

/**
 * Interface con las operaciones de la entidad Category
 */
public interface CategoryService {

    /**
     * Método que devuelve una lista de categorias
     *
     * @return findAll()
     */
    List<Category> findAll();


    /**
     * Método que retorna un Optional si el id es igual a null
     * o id es menor o igual a 0 id <= 0
     *
     * @param id recibe como parámetro un Long id
     * @return findOne(Long id)
     */
    Optional<Category> findOne(Long id);


    /**
     * Método que devuelve un boolean si existe o no existe el id que se pide
     *
     * @param id Long id
     * @return existsById(Long id)
     */
    boolean existsById(Long id);


    /**
     * Método que devuelve un Optional de categorias por color de las categorias
     *
     * @param color String color
     * @return findOne(String color)
     */
    Optional<Category> findOne(String color);


    /**
     * Método que guarda una categoria que sea igual a null
     *
     * @param category Category category
     * @return save(Category category)
     */
    Category save(Category category);


    /**
     * Método que devuelva un numero de categorias
     *
     * @return count()
     */
    long count();

    /**
     * Método que devuelve un boolean al borrar un id
     *
     * @param id Long id
     * @return deleteById(Long id)
     */

    boolean deleteById(Long id);


    /**
     * Método que devuelve un boolean al borrar todas las categorias
     *
     * @return deleteAll()
     */

    boolean deleteAll();
}
