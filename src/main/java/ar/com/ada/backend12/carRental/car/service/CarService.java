package ar.com.ada.backend12.carRental.car.service;

import ar.com.ada.backend12.carRental.car.model.CarList;
import ar.com.ada.backend12.carRental.car.model.Car;

import java.math.BigDecimal;
import java.util.Optional;

public interface CarService {
    public Car save(Car c) throws Exception;
    public Optional<Car> get(String plate) throws Exception;
    public CarList getAll(Integer typeId, Integer passengersNumber, String airConditioning, BigDecimal dailyRent) throws Exception;
    public Car update(String plate, Car c) throws Exception;
    public boolean delete(String plate) throws Exception;
}