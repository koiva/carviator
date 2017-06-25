package com.carviator.geo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    private District district;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JsonBackReference
    private List<Street> streets;

    public Locality() {
    }

    public Locality(String name, District district) {
        this.name = name;
        this.district = district;
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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
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
