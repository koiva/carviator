package com.carviator.geo.initializers;

import com.carviator.geo.entity.Continent;
import com.carviator.geo.entity.Point;
import com.carviator.geo.repository.ContinentRepository;
import com.carviator.geo.repository.PointRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Shagov on 25/06/2017.
 */
@Component
public class InitializerOfContinents {
    @Bean
    CommandLineRunner init(ContinentRepository continentRepository,
                           PointRepository pointRepository) {
        return (evt) -> {
            continentRepository.save(new Continent("Asia"));
            continentRepository.save(new Continent("Europe"));
            continentRepository.save(new Continent("Africa"));
            continentRepository.save(new Continent("Australia and Oceania"));
            continentRepository.save(new Continent("North America"));
            continentRepository.save(new Continent("South America"));

            Point point = new Point("Test", "Description of test point", 123.456, -789.159, null, continentRepository.findByName("Asia"));
            pointRepository.save(point);
        };
    }
}
