package ar.com.ada.backend12.carRental.service;

import ar.com.ada.backend12.carRental.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    public Car save(Car c) throws Exception;
    public Optional<Car> get(String plate) throws Exception;
    public List<Car> getAll() throws Exception;
    public Car update(String plate, Car c) throws Exception;
    public boolean delete(String plate) throws Exception;
}
