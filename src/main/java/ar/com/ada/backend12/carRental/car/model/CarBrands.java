package ar.com.ada.backend12.carRental.car.model;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;

import java.util.List;

public class CarBrands implements ApiReturnable {
    List<String> carBrands;

    public CarBrands() {
    }

    public CarBrands(List<String> carBrands) {
        this.carBrands = carBrands;
    }

    public List<String> getCarBrands() {
        return carBrands;
    }

    public void setCarBrands(List<String> carBrands) {
        this.carBrands = carBrands;
    }
}
