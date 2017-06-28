package com.carviator.geo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String name;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JsonBackReference
	private List<District> districts;

	public Country() {
	}

	public Country(String name) {
		this.name = name;
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

	public List<District> getDistrictsList() {
		return districts;
	}

	public void setDistrictsList(List<District> districts) {
		this.districts = districts;
	}

	public void addDistrict(District district) {
		if (null == districts)
			districts = new ArrayList<>();

		districts.add(district);
	}

}