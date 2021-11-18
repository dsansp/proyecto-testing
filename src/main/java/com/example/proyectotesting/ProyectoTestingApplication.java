package com.example.proyectotesting;

import com.example.proyectotesting.entities.Category;
import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.repository.CategoryRepository;
import com.example.proyectotesting.repository.ManufacturerRepository;
import com.example.proyectotesting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ProyectoTestingApplication implements CommandLineRunner {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ManufacturerRepository manRepository;

	@Autowired
	CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoTestingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Direction direction1 = new Direction("Calle falsa", "33010", "León", "Spain");
		Direction direction2 = new Direction("Calle verdadera", "11322", "Madrid", "Spain");

		Manufacturer adidas = new Manufacturer("Adidas","2343235325G",60000,1949);
		adidas.setDirection(direction1);
		manRepository.save(adidas);

		Manufacturer nike = new Manufacturer("Nike","2343235325G",60000,1977);
		nike.setDirection(direction2);
		manRepository.save(nike);

		Category category1 = new Category("Libros", "black");
		Category category2 = new Category("Computación", "blue");
		Category category3 = new Category("Hogar", "white");
		Category category4 = new Category("Moda", "brown");
		List<Category> categories = Arrays.asList(category1, category2, category3, category4);
		categoryRepository.saveAll(categories);

		Product product1 = new Product("Balón", "Lorem impsum dolor", 2, 10.99, adidas);
		product1.getCategories().add(category1);
		product1.getCategories().add(category2);
		productRepository.save(product1);

		Product product2 = new Product("Mesa", "Lorem impsum dolor", 8, 99.99, adidas);
		product2.getCategories().add(category1);
		product2.getCategories().add(category2);
		product2.getCategories().add(category3);
		productRepository.save(product2);

		Product product3 = new Product("Botella", "Lorem impsum dolor", 5, 99.99, adidas);
		product3.getCategories().add(category1);
		product3.getCategories().add(category4);
		productRepository.save(product3);

		Product product4 = new Product("WebCam", "Lorem impsum dolor", 12, 99.99, nike);
		productRepository.save(product4);

		Product product5 = new Product("Zapatillas", "Lorem impsum dolor", 12, 99.99, null);
		productRepository.save(product5);
	}
}
