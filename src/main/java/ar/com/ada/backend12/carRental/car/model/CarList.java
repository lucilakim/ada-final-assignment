package ar.com.ada.backend12.carRental.car.model;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;

import java.util.List;

public class CarList implements ApiReturnable {
    private List<Car> cars;

    public CarList() {
    }

    public CarList(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
