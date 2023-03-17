package ar.com.ada.backend12.carRental.carType.model;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;

import java.util.List;

public class CarTypeList implements ApiReturnable {
    List<CarType> carTypeList;

    public CarTypeList() {
    }

    public CarTypeList(List<CarType> carTypeList) {
        this.carTypeList = carTypeList;
    }

    public List<CarType> getCarTypeList() {
        return carTypeList;
    }

    public void setCarTypeList(List<CarType> carTypeList) {
        this.carTypeList = carTypeList;
    }
}
