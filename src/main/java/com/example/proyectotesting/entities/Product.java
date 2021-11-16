package com.example.proyectotesting.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="productos")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String description;
	private Integer quantity;
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "id_manufacturer")
	private Manufacturer manufacturer;
	
	@ManyToMany
	@JoinTable(name = "product_category",
	joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
	private List<Category> categories = new ArrayList<>();
	
	public Product() {
		
	}
	

	public Product(String name, String description, Integer quantity, Double price, Manufacturer manufacturer) {
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.manufacturer = manufacturer;
	}


	public List<Category> getCategories() {
		return categories;
	}


	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	
	
}
