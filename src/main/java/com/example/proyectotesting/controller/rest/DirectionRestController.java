package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.service.DirectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DirectionRestController {

    private DirectionService directionService;

    public DirectionRestController (DirectionService directionService) {

        this.directionService = directionService;
    }

    // Peticiones HTTP
    @GetMapping("/api/directions")
    public List<Direction> findAll(){
        return directionService.findAll();
    }

    @GetMapping("/api/directions/{id}")
    public ResponseEntity<Direction> findOne(@PathVariable Long id){
        Optional<Direction> directionOpt = directionService.findOne(id);
        return ResponseEntity.of(directionOpt);
    }

    @PostMapping("/api/directions")
    public ResponseEntity<Direction> create(@RequestBody Direction direction){
        if(direction.getId() != null)
            return ResponseEntity.badRequest().build();

        Direction result = directionService.save(direction);
        return ResponseEntity.ok(result); //HTTP Status 200
    }

    @PutMapping("/api/directions")
    public ResponseEntity<Direction> update(@RequestBody Direction direction){
        if(direction.getId() == null)
            return ResponseEntity.badRequest().build();
        if(!directionService.existsById(direction.getId()))
            return ResponseEntity.notFound().build();

        Direction result = directionService.save(direction);
        return ResponseEntity.ok(result); //HTTP Status 200
    }

}
