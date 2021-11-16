package com.example.proyectotesting.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="fabricantes")
public class Manufacturer implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="cif")
	private String cif;
	
	@Column(name="num_eployees")
	private Integer numEmployees;
	
	@Column(name="year")
	private Integer year;

	@JsonIgnore
	@OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
	private List<Product> products = new ArrayList<Product>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="direction_id")
	private Direction direction;
	
	public Manufacturer(String name, String cif, Integer numEmployees, Integer year) {
		this.name = name;
		this.cif = cif;
		this.numEmployees = numEmployees;
		this.year = year;
	}
	public Manufacturer() {
		super();
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

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public Integer getNumEmployees() {
		return numEmployees;
	}

	public void setNumEmployees(Integer numEmployees) {
		this.numEmployees = numEmployees;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	@Override
	public String toString() {
		return "Manufacturer [id=" + id + ", name=" + name + ", cif=" + cif + ", numEmployees=" + numEmployees
				+ ", year=" + year + "]";
	}
	
	

}
