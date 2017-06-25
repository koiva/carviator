package com.carviator.geo.rest;

import com.carviator.geo.entity.Country;
import com.carviator.geo.repository.CountryRepository;
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
@RequestMapping(ResourceConstant.GEO_COUNTRY)
@CrossOrigin
public class CountryResource {

    private static Logger log = Logger.getLogger(CountryResource.class);
    @Autowired
    CountryRepository countryRepository;

    @RequestMapping(path ="", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Country>> getCoutries() {
        HttpStatus httpStatus = HttpStatus.OK;
        Iterable<Country> countryIterable = countryRepository.findAll();
        List<Country> countries = new ArrayList<>();
        for (Country country: countryIterable) {
            countries.add(country);
        }
        log.info("Countries [" + countries.size() + "] found.");
        if(countries.size() == 0) httpStatus = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(countries, httpStatus);
    }

    @RequestMapping(path = "", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Country> createCountry(@RequestBody
                                               Country country) {
        Country countryExists = countryRepository.findByName(country.getName());
        if (countryExists != null) {
            log.warn("Country with name [" + country.getName() + "] already exists.");
            return new ResponseEntity<>(country, HttpStatus.FOUND);
        }
        countryRepository.save(country);
        log.info("Country with name [" + country.getName() + "] has been persisted.");
        return new ResponseEntity<>(country, HttpStatus.CREATED);
    }
}
