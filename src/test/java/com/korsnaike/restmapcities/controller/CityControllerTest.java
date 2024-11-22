package com.korsnaike.restmapcities.controller;

import com.korsnaike.restmapcities.model.City;
import com.korsnaike.restmapcities.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CityControllerTest {

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCities() {
        City city1 = new City("1", "City1", "Country1", "123", true, 100000);
        City city2 = new City("2", "City2", "Country2", "456", false, 50000);
        List<City> cities = Arrays.asList(city1, city2);

        when(cityService.getAllCities()).thenReturn(cities);

        List<City> result = cityController.getAllCities();

        assertEquals(2, result.size());
        assertEquals("City1", result.get(0).getName());
        assertEquals("Country1", result.get(0).getCountry());
        assertTrue(result.get(0).isCapital());
        assertEquals(100000, result.get(0).getPopulation());
    }

    @Test
    void getCityById() {
        City city = new City("1", "City1", "Country1", "123", true, 100000);

        when(cityService.getCityById("1")).thenReturn(Optional.of(city));

        Optional<City> result = cityController.getCityById("1");

        assertTrue(result.isPresent());
        assertEquals("City1", result.get().getName());
        assertEquals("Country1", result.get().getCountry());
        assertTrue(result.get().isCapital());
        assertEquals(100000, result.get().getPopulation());
    }

    @Test
    void addCity() {
        City city = new City("1", "City1", "Country1", "123", true, 100000);

        when(cityService.addCity(any(City.class))).thenReturn(city);

        City result = cityController.addCity(city);

        assertEquals("City1", result.getName());
        assertEquals("Country1", result.getCountry());
        assertTrue(result.isCapital());
        assertEquals(100000, result.getPopulation());
    }

    @Test
    void updateCity() {
        City city = new City("1", "UpdatedCity", "UpdatedCountry", "789", false, 70000);

        when(cityService.updateCity(any(City.class), eq("1"))).thenReturn(city);

        City result = cityController.updateCity(city, "1");

        assertEquals("UpdatedCity", result.getName());
        assertEquals("UpdatedCountry", result.getCountry());
        assertFalse(result.isCapital());
        assertEquals(70000, result.getPopulation());
    }

    @Test
    void deleteCity() {
        String cityId = "1";

        when(cityService.deleteCity(cityId)).thenReturn(true);

        boolean result = cityController.deleteCity(cityId);

        assertTrue(result);
    }

    @Test
    void getStatistics() {
        Map<String, String> stats = new HashMap<>();
        stats.put("count", "10");

        when(cityService.getStatistics()).thenReturn(stats);

        Map<String, String> result = cityController.getStatistics();

        assertEquals("10", result.get("count"));
    }
}