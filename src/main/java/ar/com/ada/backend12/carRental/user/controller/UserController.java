package ar.com.ada.backend12.carRental.user.controller;

import ar.com.ada.backend12.carRental.user.model.User;
import ar.com.ada.backend12.carRental.user.service.UserService;
import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import ar.com.ada.backend12.carRental.util.api.message.ApiMessage;
import ar.com.ada.backend12.carRental.util.date.DateUtil;
import ar.com.ada.backend12.carRental.util.date.validation.DateValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;
    @Autowired
    DateValidator dateValidator;
    @Autowired
    DateUtil dateUtil;

    @PostMapping("/user")
    private ResponseEntity<ApiReturnable> save(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "expirationDate") String stringExpirationDate) {

        Date expirationDate = null;
        if(dateValidator.isValid(stringExpirationDate)){
            try {
                expirationDate = dateUtil.parseDate(stringExpirationDate);
            } catch (Exception e) {
                logger.error("Error trying to change data type from string to date", e);
                return new ResponseEntity<ApiReturnable>(new ApiMessage("An error has occurred and the Customer could not be inserted."), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<ApiReturnable>(new ApiMessage("The date format is not valid. The expected format is yyyy-MM-dd"), HttpStatus.BAD_REQUEST);
        }

        User user = userService.save(username,password,expirationDate);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
