package com.carviator.geo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shagov on 18/06/2017.
 */

@Entity
@Table(name = "Street")
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    private Locality country;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Point> points;

    public Street() {
    }

    public Street(String name, Locality country) {
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

    public Locality getCountry() {
        return country;
    }

    public void setCountry(Locality country) {
        this.country = country;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void addStreetEntity(Point point) {
        if (null == points)
            points = new ArrayList<>();

        points.add(point);
    }
}
