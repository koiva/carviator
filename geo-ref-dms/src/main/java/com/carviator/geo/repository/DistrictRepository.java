package com.carviator.geo.repository;

/**
 * Created by Shagov on 18/06/2017.
 */

import com.carviator.geo.entity.District;
import org.springframework.data.repository.CrudRepository;

public interface DistrictRepository extends CrudRepository<District, Long> {
    District findByName(String name);
}
