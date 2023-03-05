package com.example.mvc.services.impl;

import com.example.mvc.models.Car;
import com.example.mvc.repo.CarRepository;
import com.example.mvc.services.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    @Transactional
    public void insert(Car car) {

        carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {

        return carRepository.findAll();
    }

    @Override
    public Car getById(Long id) {

        return carRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(Car car) {

        carRepository.delete(car);
    }

}
