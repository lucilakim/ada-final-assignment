package ar.com.ada.backend12.carRental.service;

import ar.com.ada.backend12.carRental.DAO.CarDAO;
import ar.com.ada.backend12.carRental.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

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
    public Optional<Car> get(String plate) throws Exception{
        try {
            Optional<Car> optionalCar = carDAO.findByPlate(plate);
            return optionalCar;
        } catch (Exception ex) {
            String exceptionMessage = "Error obtaining the car from CarService.";
            logger.error(exceptionMessage, ex);
            throw new Exception(exceptionMessage, ex);
        }

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
