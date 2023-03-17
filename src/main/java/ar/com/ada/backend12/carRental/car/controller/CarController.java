package ar.com.ada.backend12.carRental.car.controller;

import ar.com.ada.backend12.carRental.car.model.Car;
import ar.com.ada.backend12.carRental.car.model.CarList;
import ar.com.ada.backend12.carRental.car.service.CarService;
import ar.com.ada.backend12.carRental.car.dto.PatchCarReqBody;
import ar.com.ada.backend12.carRental.car.validation.CarValidator;
import ar.com.ada.backend12.carRental.util.api.message.ApiMessage;
import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import ar.com.ada.backend12.carRental.util.date.DateUtil;
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
    @Autowired
    private CarService carService;
    @Autowired
    DateUtil dateUtil;

    @PostMapping("/car")
    public ResponseEntity<ApiReturnable> save(
            @RequestParam(name = "carPlateId") String carPlateId,
            @RequestParam(name = "brand") String brand,
            @RequestParam(name = "model") String model,
            @RequestParam(name = "year") Year year,
            @RequestParam(name = "color") String color,
            @RequestParam(name = "carType") String carType,
            @RequestParam(name = "passengersNumber") Integer passengersNumber,
            @RequestParam(name = "mileage") Integer mileage,
            @RequestParam(name = "airConditioning") String airConditioning,
            @RequestParam(name = "dailyRent") BigDecimal dailyRent
    ) {
        CarValidator.validateSaveInputs(carPlateId, brand, model, color, carType, passengersNumber, mileage, airConditioning, dailyRent);
        logger.info("Trying to insert a Car in the database.");
        logger.debug(String.format("carPlateId [ %s ].", carPlateId));
        Car c = new Car(carPlateId, brand, model, year, color, carType, passengersNumber, mileage, airConditioning, dailyRent);
        Car car = carService.save(c);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @PatchMapping("/car/{carPlateId}")
    private ResponseEntity<ApiReturnable> update(
            @PathVariable(name = "carPlateId") String carPlateId,
            @RequestBody PatchCarReqBody body
    ) {
        CarValidator.validateUpdateInputs(carPlateId, body.getBrand(), body.getModel(), body.getColor(), body.getCarType(), body.getPassengersNumber(), body.getMileage(), body.getAirConditioning(), body.getDailyRent());
        logger.info("Trying to update a Car in the database.");
        logger.debug(String.format("carPlateId [ %s ].", carPlateId));
        Car car = new Car(carPlateId, body.getBrand(), body.getModel(), body.getYear(), body.getColor(), body.getCarType(), body.getPassengersNumber(), body.getMileage(), body.getAirConditioning(), body.getDailyRent());
        Car updatedCar = carService.update(carPlateId, car);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @GetMapping("/car/{carPlateId}")
    private ResponseEntity<ApiReturnable> get(
            @PathVariable(name = "carPlateId") String carPlateId
    ) {
        CarValidator.validateCarPlateId(carPlateId);
        logger.info("Trying to get a Car in the database.");
        logger.debug(String.format("carPlateId [ %s ].", carPlateId));
        Optional<Car> car = carService.get(carPlateId);
        return new ResponseEntity<>(car.get(), HttpStatus.OK);
    }

    @GetMapping("/car")
    private ResponseEntity<ApiReturnable> getAll(
            @RequestParam(name = "carType", required = false) String carType,
            @RequestParam(name = "passengersNumber", required = false) Integer passengersNumber,
            @RequestParam(name = "airConditioning", required = false) String airConditioning,
            @RequestParam(name = "dailyRent", required = false) BigDecimal dailyRent,
            @RequestParam(name = "onlyAvailable", required = false) String onlyAvailable
    ) {
        if (onlyAvailable != null) onlyAvailable = onlyAvailable.trim().toLowerCase();
        CarValidator.validateGetAllInput(carType, passengersNumber, airConditioning, dailyRent, onlyAvailable);
        logger.info("Trying to get all Cars in the database.");
        CarList carList = carService.getAll(carType, passengersNumber, airConditioning, dailyRent, onlyAvailable);
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    @DeleteMapping("/car/{carPlateId}")
    private ResponseEntity<ApiReturnable> delete(
            @PathVariable(name = "carPlateId") String carPlateId
    ) {
        CarValidator.validateCarPlateId(carPlateId);
        logger.info("Trying to delete a Car in the database.");
        logger.debug(String.format("carPlateId [ %s ].", carPlateId));
        carService.delete(carPlateId);
        return new ResponseEntity<>(new ApiMessage(String.format("The car with license plate %s was successfully removed.", carPlateId)), HttpStatus.OK);
    }
}
