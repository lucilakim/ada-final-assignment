package ar.com.ada.backend12.carRental.car.controller;

import ar.com.ada.backend12.carRental.car.model.CarTypeList;
import ar.com.ada.backend12.carRental.car.service.CarTypeService;
import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarTypeController {
    Logger logger = LoggerFactory.getLogger(CarTypeController.class);
    @Autowired
    CarTypeService carTypeService;

    @GetMapping("/carType")
    private ResponseEntity<ApiReturnable> getAll() {
        logger.info("Trying to get all the Car Types.");
        CarTypeList carTypeList = carTypeService.getAll();
        return new ResponseEntity<>(carTypeList, HttpStatus.OK);
    }
}
