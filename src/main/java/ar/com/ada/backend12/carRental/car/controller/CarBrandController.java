package ar.com.ada.backend12.carRental.car.controller;

import ar.com.ada.backend12.carRental.car.model.CarBrands;
import ar.com.ada.backend12.carRental.car.service.CarBrandService;
import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarBrandController {
    Logger logger = LoggerFactory.getLogger(CarController.class);
    @Autowired
    CarBrandService carBrandService;
    
    @GetMapping("/carBrand")
    private ResponseEntity<ApiReturnable> getAll() {
        logger.info("Trying to delete a Car in the database.");
        CarBrands carBrands = carBrandService.getAll();
        return new ResponseEntity<>(carBrands, HttpStatus.OK);
    }
}
