package ar.com.ada.backend12.carRental.model;

import ar.com.ada.backend12.carRental.util.ApiReturnable;

import java.util.List;

public class CarList implements ApiReturnable {
    private List<Car> carList;

    public CarList() {
    }

    public CarList(List<Car> carList) {
        this.carList = carList;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
