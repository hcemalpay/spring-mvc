package com.example.mvc.services;

import com.example.mvc.models.Car;
import java.util.List;

public interface CarService {

    void insert(Car c);

    Car getById(Long id);

    void delete(Car c);

    List<Car> findAll();

}
