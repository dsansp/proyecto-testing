package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ProductService --> ProductRepository --> Base de datos
 */
@Service
public class  ProductServiceImpl implements ProductService {

    private ProductRepository repository; // dependencia

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> findOne(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();

        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<Product> findByPriceBetween(Double min, Double max) {
        List<Product> result = new ArrayList<>();
        if (min == null || max == null)
            return result;

        if (min < 0 || max <= 0)
            return result;

        if (min >= max)
            return result;

        return repository.findByPriceBetween(min, max);

    }

    @Override
    public List<Product> findByManufacturer(String manufacturer) {
        List<Product> result = new ArrayList<>();
        if(manufacturer == null || manufacturer.isEmpty() )
            return result;

        return repository.findByManufacturerName(manufacturer);
    }

    @Override
    public Double calculateShippingCost(Product product, Direction direction) {

        if(product == null || direction == null || direction.getCountry() == null)
            return 0d;

        double result = 2.99;
        // pais
        if(!direction.getCountry().equalsIgnoreCase("Spain"))
            result += 20; // 20 € de envío fuera de españa

        return result;
    }

    @Override
    public Product save(Product product) {
        if(product == null)
            return null;

        return repository.save(product);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null || !repository.existsById(id))
            return false;

        try{
            repository.deleteById(id);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        try{
            repository.deleteAll();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
