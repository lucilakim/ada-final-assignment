package ar.com.ada.backend12.carRental.service;

import ar.com.ada.backend12.carRental.DAO.CarDAO;
import ar.com.ada.backend12.carRental.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{
    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);
    @Autowired
    CarDAO carDAO;

    @Override
    public Car save(Car c) throws Exception{
        logger.info("Attempting to register a car.");
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
    public Car get(String plate) {
        return null;
    }

    @Override
    public List<Car> getAll() throws Exception {
        return null;
    }

    @Override
    public Car update(String plate, Car c) throws Exception {
        return null;
    }

    @Override
    public boolean delete(String plate) throws Exception {
        return false;
    }
}
