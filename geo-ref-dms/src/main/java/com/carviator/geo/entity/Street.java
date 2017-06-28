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
@Table(name = "Street")
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    private Locality locality;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JsonBackReference
    private List<Point> points;

    public Street() {
    }

    public Street(String name, Locality locality) {
        this.name = name;
        this.locality = locality;
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

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
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
