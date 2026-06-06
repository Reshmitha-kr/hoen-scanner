package com.skyscanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

    @GET
    public Map<String, Object> search(@QueryParam("city") String city) {
        Map<String, Object> result = new HashMap<>();
        List<String> hotels = new ArrayList<>();
        List<String> rentalCars = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();

            // Load hotels
            InputStream hotelsStream = getClass().getResourceAsStream("/hotels.json");
            Hotel[] hotelArray = mapper.readValue(hotelsStream, Hotel[].class);
            for (Hotel hotel : hotelArray) {
                if (hotel.getCity().equalsIgnoreCase(city)) {
                    hotels.add(hotel.getTitle());
                }
            }

            // Load rental cars
            InputStream carsStream = getClass().getResourceAsStream("/rental_cars.json");
            RentalCar[] carArray = mapper.readValue(carsStream, RentalCar[].class);
            for (RentalCar car : carArray) {
                if (car.getCity().equalsIgnoreCase(city)) {
                    rentalCars.add(car.getTitle());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        result.put("hotels", hotels);
        result.put("rentalCars", rentalCars);
        return result;
    }
}