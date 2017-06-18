package com.carviator.geo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shagov on 18/06/2017.
 */

@Entity
@Table(name = "Locality")
public class Locality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    private Country country;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Street> streets;

    public Locality() {
    }

    public Locality(String name, Country country) {
        this.name = name;
        this.country = country;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }

    public void addStreetEntity(Street street) {
        if (null == streets)
            streets = new ArrayList<>();

        streets.add(street);
    }
}
