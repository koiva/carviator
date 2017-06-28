package com.carviator.geo.rest;

import com.carviator.geo.entity.Locality;
import com.carviator.geo.entity.Street;
import com.carviator.geo.repository.LocalityRepository;
import com.carviator.geo.repository.StreetRepository;
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
@RequestMapping(ResourceConstant.GEO_STREET)
@CrossOrigin
public class StreetResource {

    private static Logger log = Logger.getLogger(StreetResource.class);
    @Autowired
    StreetRepository streetRepository;

    @RequestMapping(path ="", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Street>> getStreets() {
        HttpStatus httpStatus = HttpStatus.OK;
        Iterable<Street> streetIterable = streetRepository.findAll();
        List<Street> streets = new ArrayList<>();
        for (Street street: streetIterable) {
            streets.add(street);
        }
        log.info("Streets [" + streets.size() + "] found.");
        if(streets.size() == 0) httpStatus = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(streets, httpStatus);
    }

    @RequestMapping(path = "", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Street> createStreet(@RequestBody
                                               Street street) {
        Street streetExists = streetRepository.findByName(street.getName());
        if (streetExists != null) {
            log.warn("Locality with name [" + street.getName() + "] already exists.");
            return new ResponseEntity<>(street, HttpStatus.FOUND);
        }
        streetRepository.save(street);
        log.info("Street with name [" + street.getName() + "] has been persisted.");
        return new ResponseEntity<>(street, HttpStatus.CREATED);
    }
}
