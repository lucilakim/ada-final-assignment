package ar.com.ada.backend12.carRental.car.model;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;

import java.util.List;

public class CarBrands implements ApiReturnable {
    List<CarBrand> carBrandS;

    public CarBrands() {
    }

    public CarBrands(List<CarBrand> carBrandS) {
        this.carBrandS = carBrandS;
    }

    public List<CarBrand> getCarBrandS() {
        return carBrandS;
    }

    public void setCarBrandS(List<CarBrand> carBrandS) {
        this.carBrandS = carBrandS;
    }
}
