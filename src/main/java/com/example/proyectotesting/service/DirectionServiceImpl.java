package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.repository.DirectionRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DirectionServiceImpl implements DirectionService{

    DirectionRepository directionRepository;

    public DirectionServiceImpl(DirectionRepository directionRepository){
        this.directionRepository = directionRepository;
    }

    @Override
    public List<Direction> findAll() {
        List<Direction> answer;
        answer = directionRepository.findAll();
        if (answer.size() < 1)
            System.out.println("Empty Table");
        return answer;
    }

    @Override
    public Optional<Direction> findOne(Long id) {
        try {
            Optional<Direction> optionalanswer = directionRepository.findById(id);
            if (optionalanswer.isEmpty())
                System.out.println("No result");
            return optionalanswer;
        }
        catch(IllegalArgumentException error){
            error.printStackTrace();
            return Optional.empty();
        }
    }
    @Override
    public boolean existsById(Long id) {
        return directionRepository.existsById(id);
    }
    @Override
    public Direction save(Direction direction) {
        try {
            if (direction != null) {

                return directionRepository.save(direction);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException error) {
            error.printStackTrace();
        }
        return null;
    }
    @Override
    public Long count() {

        long answer =directionRepository.count();
        if (answer < 1)
            System.out.println("Empty Table");
        return answer;
    }

    @Override
    public boolean deleteById(Long id) {

        try {
            // directionrepo.exists()
            Optional<Direction> directionoptional = directionRepository.findById(id);
            if (directionoptional.isEmpty()) {
                System.out.println("No result");
                throw new IllegalArgumentException();
            }
            Direction directionans = directionoptional.get();
            directionRepository.delete(directionans);
            return true;
        }
        catch(Exception error){
            error.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAll() {

        try {
            directionRepository.deleteAll();
            return true;
        }
        catch(Exception error){
            error.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Direction> findByCityAndCountry(String city, String country) {
        List<Direction> result = new ArrayList<>();
        if (city == null || country == null)
            return result;
        return directionRepository.findByCityAndCountry(city,country);
    }

}
