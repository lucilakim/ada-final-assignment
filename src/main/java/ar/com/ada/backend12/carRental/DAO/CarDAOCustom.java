package ar.com.ada.backend12.carRental.DAO;

import ar.com.ada.backend12.carRental.model.Car;

import java.math.BigDecimal;
import java.util.List;

public interface CarDAOCustom {
    public List<Car> getAll(
            Integer typeId
            , Integer passengersNumber
            , String airConditioning
            , BigDecimal dailyRent
            //, String onlyAvailable
    );
}
