package ar.com.ada.backend12.carRental.car.dto;

import java.math.BigDecimal;
import java.time.Year;

public class PatchCarReqBody {
    private String carPlateId;

    private String brand;
    private String model;
    private Year year;
    private String color;
    private String carType;
    private Integer passengersNumber;
    private Integer mileage;
    private String airConditioning;
    private BigDecimal dailyRent;

    public PatchCarReqBody() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Integer getPassengersNumber() {
        return passengersNumber;
    }

    public void setPassengersNumber(Integer passengersNumber) {
        this.passengersNumber = passengersNumber;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(String airConditioning) {
        this.airConditioning = airConditioning;
    }

    public BigDecimal getDailyRent() {
        return dailyRent;
    }

    public void setDailyRent(BigDecimal dailyRent) {
        this.dailyRent = dailyRent;
    }

}
