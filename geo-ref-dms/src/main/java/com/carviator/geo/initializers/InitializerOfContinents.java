package com.carviator.geo.initializers;

import com.carviator.geo.entity.*;
import com.carviator.geo.repository.*;
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
                           PointRepository pointRepository,
                           CountryRepository countryRepository,
                           DistrictRepository districtRepository,
                           LocalityRepository localityRepository,
                           StreetRepository streetRepository) {
        return (evt) -> {
            //Continents
            continentRepository.save(new Continent("Asia"));
            continentRepository.save(new Continent("Europe"));
            continentRepository.save(new Continent("Africa"));
            continentRepository.save(new Continent("Australia and Oceania"));
            continentRepository.save(new Continent("North America"));
            continentRepository.save(new Continent("South America"));
            //Countries
            countryRepository.save(new Country("Russia"));
            countryRepository.save(new Country("Finland"));
            countryRepository.save(new Country("USA"));
            countryRepository.save(new Country("Poland"));
            countryRepository.save(new Country("Japan"));
            countryRepository.save(new Country("Australia"));
            countryRepository.save(new Country("Burundi"));
            countryRepository.save(new Country("Chili"));
            //Districts
            districtRepository.save(new District("Malopolskije", countryRepository.findByName("Poland")));
            districtRepository.save(new District("Tokio", countryRepository.findByName("Japan")));
            districtRepository.save(new District("Moscow", countryRepository.findByName("Russia")));
            districtRepository.save(new District("Leningradskij", countryRepository.findByName("Russia")));
            //Localities
            localityRepository.save(new Locality("Tosno", districtRepository.findByName("Leningradskij")));

            //Street
            streetRepository.save(new Street("Moskovskaja", localityRepository.findByName("Tosno")));

            //Points
            pointRepository.save(new Point("Test", "Description of test point", 123.456, -789.159, null, continentRepository.findByName("Asia")));
            pointRepository.save(new Point("Prisma", "Prisma in Tosno (48)", 123.456, -789.159, streetRepository.findByName("Moskovskaja"), continentRepository.findByName("Europe")));
        };
    }
}
