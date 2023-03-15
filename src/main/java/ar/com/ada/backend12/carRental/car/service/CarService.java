package ar.com.ada.backend12.carRental.car.service;

import ar.com.ada.backend12.carRental.car.model.CarBrands;
import ar.com.ada.backend12.carRental.car.model.CarList;
import ar.com.ada.backend12.carRental.car.model.Car;

import java.math.BigDecimal;
import java.util.Optional;

public interface CarService {
    public Car save(Car c);
    public Optional<Car> get(String plate);
    public CarList getAll(String carType, Integer passengersNumber, String airConditioning, BigDecimal dailyRent);
    public Car update(String plate, Car c);

    public void delete(String plate);
    public CarBrands getCarBrands();

}
