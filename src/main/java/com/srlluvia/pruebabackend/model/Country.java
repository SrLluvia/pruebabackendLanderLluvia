package com.srlluvia.pruebabackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Country {

	@Id
	private String name;
	private int population;
	
	public Country() {
		super();
	}

	public Country(String name, int population) {
		super();
		this.name = name;
		this.population = population;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String getName() {
		return name;
	}

	public int getPopulation() {
		return population;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", population=" + population + "]";
	}
	
}
