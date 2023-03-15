package ar.com.ada.backend12.carRental.car.model;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Year;

@Entity
@Table(name = "CAR")
public class Car implements ApiReturnable {
    @Id
    @Column(name = "CAR_PLATE_ID")
    private String carPlateId;
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "YEAR")
    private Year year;
    @Column(name = "COLOR")
    private String color;
    @Column(name = "CAR_TYPE")
    private String carType;
    @Column(name = "PASSENGERS_NUMBER")
    private Integer passengersNumber;
    @Column(name = "MILEAGE")
    private Integer mileage;
    @Column(name = "AIR_CONDITIONING")
    private String airConditioning;
    @Column(name = "DAILY_RENT")
    private BigDecimal dailyRent;

    public Car() {
    }

    public Car(String carPlateId, String brand, String model, Year year, String color, String carType, Integer passengersNumber, Integer mileage, String airConditioning, BigDecimal dailyRent) {
        this.carPlateId = carPlateId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.carType = carType;
        this.passengersNumber = passengersNumber;
        this.mileage = mileage;
        this.airConditioning = airConditioning;
        this.dailyRent = dailyRent;
    }

    public String getCarPlateId() {
        return carPlateId;
    }

    public void setCarPlateId(String carPlateId) {
        this.carPlateId = carPlateId;
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
