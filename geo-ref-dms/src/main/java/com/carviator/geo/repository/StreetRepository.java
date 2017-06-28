package com.carviator.geo.repository;

/**
 * Created by Shagov on 18/06/2017.
 */

import com.carviator.geo.entity.Street;
import org.springframework.data.repository.CrudRepository;

public interface StreetRepository extends CrudRepository<Street, Long> {
    Street findByName(String name);
}
