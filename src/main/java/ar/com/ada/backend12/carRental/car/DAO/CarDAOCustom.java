package ar.com.ada.backend12.carRental.car.DAO;

import ar.com.ada.backend12.carRental.car.model.Car;

import java.math.BigDecimal;
import java.util.List;

public interface CarDAOCustom {
    public List<Car> getAll(
            String carType
            , Integer passengersNumber
            , String airConditioning
            , BigDecimal dailyRent
            , String onlyAvailable
    );
}
