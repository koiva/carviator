package com.carviator.geo.repository;

/**
 * Created by Shagov on 18/06/2017.
 */

import com.carviator.geo.entity.Point;
import org.springframework.data.repository.CrudRepository;

public interface PointRepository extends CrudRepository<Point, Long> {
    Point findByName(String name);
}
