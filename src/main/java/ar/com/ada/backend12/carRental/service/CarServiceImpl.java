package ar.com.ada.backend12.carRental.service;

import ar.com.ada.backend12.carRental.DAO.CarDAO;
import ar.com.ada.backend12.carRental.model.Car;
import ar.com.ada.backend12.carRental.model.CarList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{
    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);
    @Autowired
    CarDAO carDAO;

    @Override
    public Car save(Car c) throws Exception{
        logger.info("Trying to insert a car.");
        logger.debug("carId ["+ c.getCarId() +"]");
        try {
            carDAO.save(c);
            return c;
        } catch (Exception ex) {
            String exceptionMessage = "Error registering the car in carService.";
            logger.error(exceptionMessage, ex);
            throw new Exception(exceptionMessage, ex);
        }
    }

    @Override
    public Car update(String plate, Car c) throws Exception {
        logger.info("Trying to Update a car.");
        logger.debug("carId ["+ c.getCarId() +"]");

        Optional<Car> currentCar = this.get(plate);
        if(currentCar.isPresent()){
            Car updatedCar = currentCar.get();
            if(c.getBrand() != null) {updatedCar.setBrand(c.getBrand());}
            if(c.getModel() != null) {updatedCar.setModel(c.getModel());}
            if(c.getYear() != null) {updatedCar.setYear(c.getYear());}
            if(c.getColor() != null) {updatedCar.setColor(c.getColor());}
            if(c.getTypeId() != null) {updatedCar.setTypeId(c.getTypeId());}
            if(c.getPassengersNumber() != null) {updatedCar.setPassengersNumber(c.getPassengersNumber());}
            if(c.getMileage() != null) {updatedCar.setMileage(c.getMileage());}
            if(c.getAirConditioning() != null) {updatedCar.setAirConditioning(c.getAirConditioning());}
            if(c.getDailyRent() != null) {updatedCar.setDailyRent(c.getDailyRent());}
            try{
                return carDAO.save(updatedCar);
            } catch (Exception e) {
                String exceptionMessage = "Failed to update the car in service";
                logger.error(exceptionMessage, e);
                throw new Exception(exceptionMessage, e);
            }
        } else {
            return null;
        }
    }

    @Override
    public Optional<Car> get(String plate) throws Exception{
        logger.info("Trying to Get a car.");
        logger.debug("plate ["+ plate +"]");
        try {
            return carDAO.findByPlate(plate);
        } catch (Exception e) {
            String exceptionMessage = "Error obtaining the car from CarService.";
            logger.error(exceptionMessage, e);
            throw new Exception(exceptionMessage, e);
        }
    }

    @Override
    public CarList getAll(Integer typeId, Integer passengersNumber, String airConditioning, BigDecimal dailyRent) throws Exception{
        logger.info("Trying to Get All cars.");
        try {
            return new CarList(carDAO.getAll(typeId, passengersNumber, airConditioning, dailyRent));
        } catch (Exception e) {
            String exceptionMessage = "Error obtaining the car from CarService.";
            logger.error(exceptionMessage, e);
            throw new Exception(exceptionMessage, e);
        }
    }

    @Override
    public boolean delete(String plate) throws Exception {
        logger.info("Trying to Delete a car.");
        logger.debug("plate ["+ plate +"]");
        Optional<Car> optionalCar = this.get(plate);
        if (optionalCar.isPresent()) {
            carDAO.delete(optionalCar.get());
            return true;
        }
        return false;
    }
}
