package com.carviator.geo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Shagov on 18/06/2017.
 */

@Entity
@Table(name = "Point")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String descr;

    @NotNull
    private Double lat;

    @NotNull
    private Double lon;

    @ManyToOne
    private Street street;

    @ManyToOne
    private Continent continent;

    public Point() {
    }

    public Point(String name, String descr, Double lat, Double lon, Street street, Continent continent) {
        this.name = name;
        this.descr = descr;
        this.lat = lat;
        this.lon = lon;
        this.street = street;
        this.continent = continent;
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

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
