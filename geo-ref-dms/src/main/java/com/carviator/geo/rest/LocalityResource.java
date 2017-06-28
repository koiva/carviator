package com.carviator.geo.rest;

import com.carviator.geo.entity.Continent;
import com.carviator.geo.entity.Locality;
import com.carviator.geo.repository.ContinentRepository;
import com.carviator.geo.repository.LocalityRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shagov on 18/06/2017.
 */

@RestController
@RequestMapping(ResourceConstant.GEO_LOCALITY)
@CrossOrigin
public class LocalityResource {

    private static Logger log = Logger.getLogger(LocalityResource.class);
    @Autowired
    LocalityRepository loclaityRepository;

    @RequestMapping(path ="", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Locality>> getLocalities() {
        HttpStatus httpStatus = HttpStatus.OK;
        Iterable<Locality> localityIterable = loclaityRepository.findAll();
        List<Locality> localities = new ArrayList<>();
        for (Locality locality: localityIterable) {
            localities.add(locality);
        }
        log.info("Localities [" + localities.size() + "] found.");
        if(localities.size() == 0) httpStatus = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(localities, httpStatus);
    }

    @RequestMapping(path = "", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Locality> createLocality(@RequestBody
                                               Locality locality) {
        Locality localityExists = loclaityRepository.findByName(locality.getName());
        if (localityExists != null) {
            log.warn("Locality with name [" + locality.getName() + "] already exists.");
            return new ResponseEntity<>(locality, HttpStatus.FOUND);
        }
        loclaityRepository.save(locality);
        log.info("Locality with name [" + locality.getName() + "] has been persisted.");
        return new ResponseEntity<>(locality, HttpStatus.CREATED);
    }
}
