package com.carviator.geo.repository;

/**
 * Created by Shagov on 18/06/2017.
 */

import com.carviator.geo.entity.Locality;
import org.springframework.data.repository.CrudRepository;

public interface LocalityRepository extends CrudRepository<Locality, Long> {
}
