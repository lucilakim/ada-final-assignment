package ar.com.ada.backend12.carRental.model;

import ar.com.ada.backend12.carRental.util.ApiReturnable;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Year;

@Entity
@Table(name = "CAR")
public class Car implements ApiReturnable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_ID")
    private Integer carId;
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "YEAR")
    private Year year;
    @Column(name = "COLOR")
    private String color;
    @Column(name = "TYPE_ID")
    private Integer typeId;
    @Column(name = "PLATE")
    private String plate;
    @Column(name = "PASSENGERS_NUMBER")
    private Integer passengersNumber;
    @Column(name = "MILEAGE")
    private Integer mileage;
    @Column(name = "AIR_CONDITIONING")
    private String airConditioning;
    @Column(name = "DAILY_RENT")
    private BigDecimal dailyRent;

    public Car() {
        super();
    }

    public Car(String brand, String model, Year year, String color, Integer typeId, String plate, Integer passengersNumber, Integer mileage, String airConditioning, BigDecimal dailyRent) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.typeId = typeId;
        this.plate = plate;
        this.passengersNumber = passengersNumber;
        this.mileage = mileage;
        this.airConditioning = airConditioning;
        this.dailyRent = dailyRent;;
    }

    public Car(Integer carId, String brand, String model, Year year, String color, Integer typeId, String plate, Integer passengersNumber, Integer mileage, String airConditioning, BigDecimal dailyRent) {
        this(brand,model,year,color,typeId,plate,passengersNumber,mileage,airConditioning,dailyRent);
        this.carId = carId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
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
