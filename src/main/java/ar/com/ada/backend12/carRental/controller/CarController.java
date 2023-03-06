package ar.com.ada.backend12.carRental.controller;

import ar.com.ada.backend12.carRental.model.Car;
import ar.com.ada.backend12.carRental.service.CarService;
import ar.com.ada.backend12.carRental.util.ApiMessage;
import ar.com.ada.backend12.carRental.util.ApiReturnable;
import ar.com.ada.backend12.carRental.validation.DateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Year;


@RestController
public class CarController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    private static final ResponseEntity<ApiReturnable> NOT_FOUND =
            new ResponseEntity<ApiReturnable>(new ApiMessage("Car not found."), HttpStatus.NOT_FOUND);
    private static final ResponseEntity<ApiReturnable> ERROR_MESSAGE_500 =
            new ResponseEntity<ApiReturnable>(new ApiMessage("An internal error has occurred. Please try again."), HttpStatus.INTERNAL_SERVER_ERROR);

    @Autowired
    private CarService carService;

    @Autowired
    private DateValidator dateValidator;

    @PostMapping("/car")
    public ResponseEntity<ApiReturnable> save(
            @RequestParam(name = "brand") String brand
            ,@RequestParam(name = "model") String model
            ,@RequestParam(name = "year") Year year
            ,@RequestParam(name = "color") String color
            ,@RequestParam(name = "typeId") Integer typeId
            ,@RequestParam(name = "plate") String plate
            ,@RequestParam(name = "passengersNumber") Integer passengersNumber
            ,@RequestParam(name = "mileage") Integer mileage
            ,@RequestParam(name = "airConditioning") String airConditioning
            ,@RequestParam(name = "dailyRent") BigDecimal dailyRent
            ){
        Car c = new Car(brand,model,year,color,typeId,plate,passengersNumber,mileage,airConditioning,dailyRent);

        logger.info("Trying to insert Employee in the database.");
        logger.debug("name ["+ c.getBrand() +"]");
        logger.debug("name ["+ c.getModel() +"]");

        try {
            Car car = carService.save(c);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error registering the car in controller.");
            return new ResponseEntity<ApiReturnable>(new ApiMessage("Error trying to register the car. Please try again later."), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ApiReturnable>(c, HttpStatus.CREATED);
    }
}
