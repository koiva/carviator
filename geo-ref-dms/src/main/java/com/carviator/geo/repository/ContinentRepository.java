package com.carviator.geo.repository;

/**
 * Created by Shagov on 18/06/2017.
 */

import com.carviator.geo.entity.Continent;
import org.springframework.data.repository.CrudRepository;

public interface ContinentRepository extends CrudRepository<Continent, Long> {
}
