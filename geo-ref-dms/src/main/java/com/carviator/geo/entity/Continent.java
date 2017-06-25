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
@Table(name = "Continent")
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JsonBackReference
    private List<Point> points;

    public Continent() {
    }

    public Continent(String name) {
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

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void addPointEntity(Point point) {
        if (null == points)
            points = new ArrayList<>();

        points.add(point);
    }


}
