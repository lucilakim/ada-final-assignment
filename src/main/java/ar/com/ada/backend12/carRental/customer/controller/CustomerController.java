package ar.com.ada.backend12.carRental.customer.controller;

import ar.com.ada.backend12.carRental.customer.service.CustomerService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class CustomerController {
    private static Logger logger = (Logger) LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

}
