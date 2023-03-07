package ar.com.ada.backend12.carRental.service;

import ar.com.ada.backend12.carRental.model.Car;
import ar.com.ada.backend12.carRental.model.CarList;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CarService {
    public Car save(Car c) throws Exception;
    public Optional<Car> get(String plate) throws Exception;
    public CarList getAll(Integer typeId, Integer passengersNumber, String airConditioning, BigDecimal dailyRent);
    public Car update(String plate, Car c) throws Exception;
    public boolean delete(String plate) throws Exception;
}
