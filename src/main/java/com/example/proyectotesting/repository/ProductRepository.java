package com.example.proyectotesting.repository;

import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findAllByManufacturerId(Long id);
	
	List<Product> findAllByManufacturerIdOrManufacturerIdIsNull(Long id);
	
	List<Product> findAllByManufacturerIdIsNull();

	List<Product> findAllByManufacturerIdNotIn(List<Long> ids);

	List<Product> findByManufacturerName(String name);

	List<Product> findByPriceBetween(Double min, Double max);


}
