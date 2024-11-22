package com.korsnaike.restmapcities.repository;

import com.korsnaike.restmapcities.model.City;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CityRepository {
    private final String filePath = "cities.csv";

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Пропустить заголовок
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                City city = new City(
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3],
                        Boolean.parseBoolean(parts[4]),
                        Integer.parseInt(parts[5])
                );
                cities.add(city);
            }
        } catch (IOException e) {
            // Если файл отсутствует, возвращаем пустой список
            System.out.println("CSV file not found, starting with an empty list.");
        }
        return cities;
    }

    public void saveAll(List<City> cities) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("id,name,country,phoneCode,isCapital,population\n");
            for (City city : cities) {
                writer.write(String.format("%s,%s,%s,%s,%b,%d\n",
                        city.getId(),
                        city.getName(),
                        city.getCountry(),
                        city.getPhoneCode(),
                        city.isCapital(),
                        city.getPopulation()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
