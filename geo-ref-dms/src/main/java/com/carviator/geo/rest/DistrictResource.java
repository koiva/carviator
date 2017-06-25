package com.carviator.geo.rest;

import com.carviator.geo.entity.District;
import com.carviator.geo.repository.DistrictRepository;
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
@RequestMapping(ResourceConstant.GEO_DISTRICT)
@CrossOrigin
public class DistrictResource {

    private static Logger log = Logger.getLogger(DistrictResource.class);
    @Autowired
    DistrictRepository districtRepository;

    @RequestMapping(path ="", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<District>> getDistricts() {
        HttpStatus httpStatus = HttpStatus.OK;
        Iterable<District> districtIterable = districtRepository.findAll();
        List<District> districts = new ArrayList<>();
        for (District district: districtIterable) {
            districts.add(district);
        }
        log.info("Districts [" + districts.size() + "] found.");
        if(districts.size() == 0) httpStatus = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(districts, httpStatus);
    }

    @RequestMapping(path = "", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<District> createDistrict(@RequestBody
                                               District district) {
        District countryExists = districtRepository.findByName(district.getName());
        if (countryExists != null) {
            log.warn("District with name [" + district.getName() + "] already exists.");
            return new ResponseEntity<>(district, HttpStatus.FOUND);
        }
        districtRepository.save(district);
        log.info("District with name [" + district.getName() + "] has been persisted.");
        return new ResponseEntity<>(district, HttpStatus.CREATED);
    }
}
