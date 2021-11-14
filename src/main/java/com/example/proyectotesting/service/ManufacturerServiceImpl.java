package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private ManufacturerRepository repository;

    public ManufacturerServiceImpl(ManufacturerRepository repository){
        this.repository = repository;
    }


    @Override
    public List<Manufacturer> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Manufacturer> findOne(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();
        return repository.findById(id);
    }

    @Override
    public List<Manufacturer> findByYear(Integer year) {
        List<Manufacturer> result = new ArrayList<>();
        if (year == null || year <= 0)
            return result;
        return repository.findByYear(year);
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        if(manufacturer == null)
            return null;
        return repository.save(manufacturer);
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
    public List<Manufacturer> findManufacturerByCountry(String country) {
        List<Manufacturer> result = new ArrayList<>();
        if(country == null || country.isEmpty())
            return result;
        return repository.findManufacturerByDirectionCountry(country);
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

