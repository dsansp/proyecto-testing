package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.entities.Category;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryRestController {

    private CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/api/category")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/api/category/{id}")
    public ResponseEntity<Category> findOne(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findOne(id);
        return ResponseEntity.of(categoryOptional);

    }

    @PostMapping("api/category")
    public ResponseEntity<Category> create(@RequestBody Category category) {
    if  (category.getId() != null)
       return ResponseEntity.badRequest().build();// faltaba return
        Category cat=   categoryService.save(category);
    return ResponseEntity.ok(cat);
    }
    @PutMapping("api/category")
    public ResponseEntity<Category> update(@RequestBody Category category) {
        if  (category.getId() == null)
          return  ResponseEntity.badRequest().build(); // faltaba el return
        if (!categoryService.existsById(category.getId()))
       return  ResponseEntity.notFound().build(); //faltaba el return
        Category result=   categoryService.save(category);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/api/category/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id){

        if(!categoryService.existsById(id))
            return ResponseEntity.notFound().build();

        boolean result = categoryService.deleteById(id);
        if (result)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

    }
    @DeleteMapping("/api/category")
    public ResponseEntity<Category> deleteAll(){

        if(categoryService.deleteAll())
            return ResponseEntity.noContent().build(); // HTTP Status es 204 NO CONTENT
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // HTTP Status es 409 CONFLICT
    }
}