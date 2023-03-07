package ar.com.ada.backend12.carRental.controller;

import ar.com.ada.backend12.carRental.model.Car;
import ar.com.ada.backend12.carRental.model.CarList;
import ar.com.ada.backend12.carRental.service.CarService;
import ar.com.ada.backend12.carRental.util.ApiMessage;
import ar.com.ada.backend12.carRental.util.ApiReturnable;
import ar.com.ada.backend12.carRental.validation.DateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Optional;


@RestController
public class CarController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    private static final ResponseEntity<ApiReturnable> NOT_FOUND =
            new ResponseEntity<ApiReturnable>(new ApiMessage("Car not found."), HttpStatus.NOT_FOUND);
    private static final ResponseEntity<ApiReturnable> CARS_NOT_FOUND =
            new ResponseEntity<ApiReturnable>(new ApiMessage("No cars were found with these specifications."), HttpStatus.NOT_FOUND);
    private static final ResponseEntity<ApiReturnable> ERROR_MESSAGE_500 =
            new ResponseEntity<ApiReturnable>(new ApiMessage("An internal error has occurred. Please try again."), HttpStatus.INTERNAL_SERVER_ERROR);

    @Autowired
    private CarService carService;

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

    @GetMapping("/car/{plate}")
    private ResponseEntity<ApiReturnable> get(
            @PathVariable(name = "plate") String plate
    ){
        Optional<Car> optionalCar;
        try{
            optionalCar = carService.get(plate);
        } catch (Exception e) {
            logger.error("Error getting the car from CarController.");
            return new ResponseEntity<ApiReturnable>(new ApiMessage("Error trying to register the car. Please try again later."), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(!optionalCar.isEmpty()) {
            Car car = optionalCar.get();
            return new ResponseEntity<ApiReturnable>(car, HttpStatus.OK);
        } else {
            return NOT_FOUND;
        }
    }

    @GetMapping("/car")
    private ResponseEntity<ApiReturnable> getAll(
              @RequestParam(name = "typeId" , required = false) Integer typeId
             ,@RequestParam(name = "passengersNumber", required = false) Integer passengersNumber
             ,@RequestParam(name = "airConditioning", required = false) String airConditioning
             ,@RequestParam(name = "dailyRent", required = false) BigDecimal dailyRent
             //,@RequestParam(name = "onlyAvailable", required = false) String onlyAvailable
    ){
        CarList carList = carService.getAll(typeId, passengersNumber, airConditioning, dailyRent);

        if (!carList.getCarList().isEmpty()){
            return new ResponseEntity<ApiReturnable>(carList, HttpStatus.OK);
        } else {
            return CARS_NOT_FOUND;
        }

    }

    @PatchMapping("/car/{plate}")
    private ResponseEntity<ApiReturnable> update(
            @RequestParam(name = "brand", required = false) String brand
            ,@RequestParam(name = "model", required = false) String model
            ,@RequestParam(name = "year", required = false) Year year
            ,@RequestParam(name = "color", required = false) String color
            ,@RequestParam(name = "typeId", required = false) Integer typeId
            ,@RequestParam(name = "plate") String plate
            ,@RequestParam(name = "passengersNumber", required = false) Integer passengersNumber
            ,@RequestParam(name = "mileage", required = false) Integer mileage
            ,@RequestParam(name = "airConditioning", required = false) String airConditioning
            ,@RequestParam(name = "dailyRent", required = false) BigDecimal dailyRent
    ){
        Car car = new Car(brand,model,year,color,typeId,plate,passengersNumber,mileage,airConditioning,dailyRent);

        try{
            Car updatedCar = carService.update(plate, car);
            if(updatedCar != null) {
                return new ResponseEntity<ApiReturnable>(updatedCar, HttpStatus.OK);
            } else {
                return NOT_FOUND;
            }
        } catch (Exception e) {
            logger.error("Failed to update the car in controller");
            return new ResponseEntity<ApiReturnable>(new ApiMessage("An internal error has occurred and we were unable to update the car. Please try again later."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/car/{plate}")
    private ResponseEntity<ApiReturnable> delete(
            @PathVariable(name = "plate") String plate) {
        try {
            Boolean deleted = carService.delete(plate);
            if(deleted){
                return new ResponseEntity<ApiReturnable>(new ApiMessage("The car with license plate " + plate + " was successfully removed."),HttpStatus.OK);
            } else {
                return NOT_FOUND;
            }

        } catch (Exception e) {
            logger.error("Failed to update the car in controller");
            return new ResponseEntity<ApiReturnable>(new ApiMessage("An internal error has occurred and we were unable to update the car. Please try again later."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
