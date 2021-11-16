package com.example.proyectotesting.controller.mvc;

import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.repository.CategoryRepository;
import com.example.proyectotesting.repository.ManufacturerRepository;
import com.example.proyectotesting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller()
@RequestMapping(value="/products")
public class ProductController {

	@Autowired
	ProductRepository repository;
	
	@Autowired
	ManufacturerRepository manufacturerRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping
	public String obtenerLista(Model model) {
		// Recuperar productos de la base de datos utilizando repository
		List<Product> products = repository.findAll();
		// Con el parámetro cargamos la lista de productos en la petición
		model.addAttribute("products", products);
		// Retornamos a la vista jsp
		return "product-list";
	}
	
	@GetMapping("/new")
	public String obtenerFormulario(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("manufacturers", manufacturerRepository.findAll());
		model.addAttribute("categories", categoryRepository.findAll());
		return "product-edit";
	}
	
	@PostMapping
	public String crearProducto(@ModelAttribute("product") Product product) {
		repository.save(product);
		return "redirect:/products";
	}
	
	@GetMapping("/{id}/view")
	public String verProducto(@PathVariable Long id, Model model) {
		if(id == null) // 1. se comprueba que el id no sea nulo
			return "redirect:/products";
		
		Optional<Product> productOpt = repository.findById(id);
		if (productOpt.isPresent()) { // 2. se comprueba que existe un producto para ese id
			model.addAttribute("product", productOpt.get());
			return "product-view";
		}
		model.addAttribute("error", "No existe el producto solicitado");
		return "redirect:/products";
	}
	
	@GetMapping("/{id}/edit")
	public String editarProducto(@PathVariable Long id, Model model) {
		if(id == null) // 1. se comprueba que el id no sea nulo
			return "redirect:/products";
		
		Optional<Product> productOpt = repository.findById(id);
		if (productOpt.isPresent()) { // 2. se comprueba que existe un producto para ese id
			model.addAttribute("product", productOpt.get());
			model.addAttribute("manufacturers", manufacturerRepository.findAll());
			model.addAttribute("categories", categoryRepository.findAll());
			return "product-edit";
		}
		model.addAttribute("error", "No existe el producto solicitado");
		return "redirect:/products";
	}
	
	
	@GetMapping("/{id}/delete")
	public String borrarProducto(@PathVariable Long id) {
		repository.deleteById(id);
		return "redirect:/products";
	}
	
	@GetMapping("/delete/all")
	public String borrarProductos() {
		repository.deleteAll();
		return "redirect:/products";
	}
	
	@GetMapping("/new/manufacturer/{id}")
	public String formWithManufacturer(@PathVariable Long id, Model model) {
		Manufacturer manufacturer = manufacturerRepository.findById(id).get();
		model.addAttribute("manufacturer", manufacturer);
		return "product-edit-withmanufacturer";
	}

}
