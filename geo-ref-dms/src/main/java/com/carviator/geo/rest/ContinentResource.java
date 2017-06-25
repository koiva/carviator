package com.carviator.geo.rest;

import com.carviator.geo.entity.Continent;
import com.carviator.geo.repository.ContinentRepository;
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
@RequestMapping(ResourceConstant.GEO_CONTINENT)
@CrossOrigin
public class ContinentResource {

    private static Logger log = Logger.getLogger(ContinentResource.class);
    @Autowired
    ContinentRepository continentRepository;

    @RequestMapping(path ="", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Continent>> getContinents() {
        HttpStatus httpStatus = HttpStatus.OK;
        Iterable<Continent> continentIterable = continentRepository.findAll();
        List<Continent> continents = new ArrayList<>();
        for (Continent continent: continentIterable) {
            continents.add(continent);
        }
        log.info("Continents [" + continents.size() + "] found.");
        if(continents.size() == 0) httpStatus = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(continents, httpStatus);
    }

    @RequestMapping(path = "", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Continent> createContinent(@RequestBody
                                               Continent continent) {
        Continent continentExists = continentRepository.findByName(continent.getName());
        if (continentExists != null) {
            log.warn("Continent with name [" + continent.getName() + "] already exists.");
            return new ResponseEntity<>(continent, HttpStatus.FOUND);
        }
        continentRepository.save(continent);
        log.info("Continent with name [" + continent.getName() + "] has been persisted.");
        return new ResponseEntity<>(continent, HttpStatus.CREATED);
    }
}
