package com.example.proyectotesting.controller.mvc;

import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.repository.ManufacturerRepository;
import com.example.proyectotesting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller // mvc
public class ManufacturerController {

	@Autowired
	ManufacturerRepository repository;
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/manufacturers")
	public String list(Model model) {
		List<Manufacturer> manufacturers = repository.findAll();
		model.addAttribute("manufacturers", manufacturers);
		return "manufacturer-list";
	}
	
	@GetMapping("/manufacturers/{id}/view")
	public String view(@PathVariable Long id, Model model) {
		if (id == null) {
			return "redirect:/manufacturers";
		}
		Optional<Manufacturer> manOpt = repository.findById(id);
		if (manOpt.isPresent()) {
			model.addAttribute("manufacturer", manOpt.get());
			return "manufacturer-view";
		}
		return "redirect:/manufacturers";
	}
	
	@GetMapping("/manufacturers/{id}/edit")
	public String loadForm(@PathVariable Long id, Model model) {
		if(id == null)
			return "redirect:/manufacturers";
		
		Optional<Manufacturer> manOpt = repository.findById(id);
		if (manOpt.isPresent()) {
			model.addAttribute("manufacturer", manOpt.get());
			model.addAttribute("products", productRepository.findAllByManufacturerIdOrManufacturerIdIsNull(id));
			return "manufacturer-edit";
		}
		model.addAttribute("NOTIFICATION", "No existe el fabricante solicitado.");
		model.addAttribute("manufacturers", repository.findAll());
		return "manufacturer-list";
	}
	
	@GetMapping("/manufacturers/new")
	public String showForm(Model model) {
		model.addAttribute("manufacturer", new Manufacturer());
		model.addAttribute("products", productRepository.findAllByManufacturerIdIsNull());
		return "manufacturer-edit";
	}
	
	@PostMapping("/manufacturers")
	public String save(@ModelAttribute("manufacturer") Manufacturer manufacturer) {
		for (Product product : manufacturer.getProducts()) 
			product.setManufacturer(manufacturer);
		
		repository.save(manufacturer);
		return "redirect:/manufacturers";
	}


	@GetMapping("/manufacturers/{id}/delete")
	public String delete(@PathVariable Long id) {
		repository.deleteById(id);
		return "redirect:/manufacturers";
	}
	
	@GetMapping("/manufacturers/delete/all")
	public String deleteAll() {
		repository.deleteAll();
		return "redirect:/manufacturers";
	}
	
	
}
