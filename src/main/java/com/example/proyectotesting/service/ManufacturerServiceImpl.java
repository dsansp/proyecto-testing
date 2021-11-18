package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.repository.ManufacturerRepository;
import com.example.proyectotesting.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private ManufacturerRepository manufacturerRepository;
    private ProductRepository productRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, ProductRepository productRepository){
        this.manufacturerRepository = manufacturerRepository;
        this.productRepository = productRepository;
    }


    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findOne(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();
        return manufacturerRepository.findById(id);
    }

    @Override
    public List<Manufacturer> findByYear(Integer year) {
        List<Manufacturer> result = new ArrayList<>();
        if (year == null || year <= 0)
            return result;
        return manufacturerRepository.findByYear(year);
    }

    @Override
    public boolean existsById(Long id) {
        return manufacturerRepository.existsById(id);
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        if(manufacturer == null)
            return null;

        for (Product product : manufacturer.getProducts())
            product.setManufacturer(manufacturer);

        if(manufacturer.getId() == null) // crear un nuevo fabricante
            return manufacturerRepository.save(manufacturer);

        // editar un fabricante existente
        Optional<Manufacturer> manufacturerOptional = this.manufacturerRepository.findById(manufacturer.getId());
        if(manufacturerOptional.isEmpty())
            return null;

        Manufacturer manufacturerDB = manufacturerOptional.get();

        List<Product> products = new ArrayList<>(manufacturer.getProducts());
        for (Product productDB : manufacturerDB.getProducts()){ // productos originales
            boolean check = false;
            for(Product product : manufacturer.getProducts()){ // nuevos productos
                if (productDB.getId() == product.getId()) {
                    check = true;
                    break;
                }
            }
            if(!check){
                productDB.setManufacturer(null);
            }
        }
        products.addAll(manufacturerDB.getProducts());

        if (!products.isEmpty())
            productRepository.saveAll(products);

        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public long count() {
        return manufacturerRepository.count();
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null || !manufacturerRepository.existsById(id))
            return false;
        try{
            manufacturerRepository.deleteById(id);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;


    }

    @Override
    public List<Manufacturer> findManufacturerByCountry(String country) {
        List<Manufacturer> result = new ArrayList<>();
        if(country == null || country.isEmpty())
            return result;
        return manufacturerRepository.findManufacturerByDirectionCountry(country);
    }
        @Override
    public boolean deleteAll() {
        try{
            manufacturerRepository.deleteAll();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    }

