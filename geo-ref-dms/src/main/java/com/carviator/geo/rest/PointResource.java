package com.carviator.geo.rest;

import com.carviator.geo.entity.Point;
import com.carviator.geo.repository.ContinentRepository;
import com.carviator.geo.repository.PointRepository;
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
@RequestMapping(ResourceConstant.GEO_POINT)
@CrossOrigin
public class PointResource {

    private static Logger log = Logger.getLogger(PointResource.class);
    @Autowired
    ContinentRepository continentRepository;

    @Autowired
    PointRepository pointRepository;

    @RequestMapping(path ="", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Point>> getPoints() {
        HttpStatus httpStatus = HttpStatus.OK;
        Iterable<Point> pointIterable = pointRepository.findAll();
        List<Point> points = new ArrayList<>();
        for (Point point: pointIterable) {
            points.add(point);
        }
        log.info("Points [" + points.size() + "] found.");
        if(points.size() == 0) httpStatus = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(points, httpStatus);
    }

    @RequestMapping(path = "", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Point> createPoint(@RequestBody
                                               Point point) {
        Point pointExists = pointRepository.findByName(point.getName());
        if (pointExists != null) {
            log.warn("Point with name [" + point.getName() + "] already exists.");
            return new ResponseEntity<>(point, HttpStatus.FOUND);
        }
        pointRepository.save(point);
        log.info("Point with name [" + point.getName() + "] has been persisted.");
        return new ResponseEntity<>(point, HttpStatus.CREATED);
    }
}
