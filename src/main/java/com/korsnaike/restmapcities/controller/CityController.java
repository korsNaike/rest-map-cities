package com.korsnaike.restmapcities.controller;

import com.korsnaike.restmapcities.model.City;
import com.korsnaike.restmapcities.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public Optional<City> getCityById(@PathVariable String id) {
        return cityService.getCityById(id);
    }

    @PostMapping
    public City addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }

    @PutMapping("/{id}")
    public City updateCity(@RequestBody City city, @PathVariable String id) {
        return cityService.updateCity(city, id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCity(@PathVariable String id) {
        return cityService.deleteCity(id);
    }

    @GetMapping("/stats")
    public Map<String, String> getStatistics() {
        return cityService.getStatistics();
    }
}
