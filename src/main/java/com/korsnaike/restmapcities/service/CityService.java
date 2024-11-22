package com.korsnaike.restmapcities.service;

import com.korsnaike.restmapcities.model.City;
import com.korsnaike.restmapcities.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    private List<City> citiesCache;

    public CityService() {
        this.citiesCache = new ArrayList<>();
    }

    // Инициализация данных из CSV при старте
    public void init() {
        this.citiesCache = cityRepository.findAll();
    }

    public List<City> getAllCities() {
        if (citiesCache.toArray().length == 0) {
            init();
        }
        return citiesCache;
    }

    public Optional<City> getCityById(String id) {
        if (citiesCache.toArray().length == 0) {
            init();
        }
        return citiesCache.stream().filter(city -> city.getId().equals(id)).findFirst();
    }

    public City addCity(City city) {
        if (citiesCache.toArray().length == 0) {
            init();
        }
        city.setId(UUID.randomUUID().toString());
        citiesCache.add(city);
        cityRepository.saveAll(citiesCache);
        return city;
    }

    public City updateCity(City city, String idCityToUpdate) {
        if (citiesCache.toArray().length == 0) {
            init();
        }
        this.deleteCity(idCityToUpdate);
        city.setId(idCityToUpdate);
        citiesCache.add(city);
        cityRepository.saveAll(citiesCache);
        return city;
    }

    public boolean deleteCity(String id) {
        if (citiesCache.toArray().length == 0) {
            init();
        }
        boolean removed = citiesCache.removeIf(city -> city.getId().equals(id));
        if (removed) {
            cityRepository.saveAll(citiesCache);
        }
        return removed;
    }

    public Map<String, String> getStatistics() {
        if (citiesCache.toArray().length == 0) {
            init();
        }
        int totalPopulation = citiesCache.stream().mapToInt(City::getPopulation).sum();
        int totalCities = citiesCache.size();
        Map<String, String> results = new HashMap<>();
        results.put("totalCities", String.valueOf(totalCities));
        results.put("totalPopulation", String.valueOf(totalPopulation));
        return results;
    }

}
