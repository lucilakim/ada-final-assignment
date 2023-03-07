package ar.com.ada.backend12.carRental.customer.service;


import ar.com.ada.backend12.carRental.customer.DAO.CustomerDAO;
import ar.com.ada.backend12.carRental.customer.model.Customer;
import ar.com.ada.backend12.carRental.customer.model.CustomerList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService{
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public Customer save(Customer c) throws Exception{
        logger.info("Trying to insert a customer");
        try {
            Customer customer = customerDAO.save(c);
            return customer;
        } catch (Exception e){
            logger.error("An error has occurred trying to enter a user");
            throw new Exception();
        }
    }

    @Override
    public Customer update(String firstName, String lastName, Date birthDate, Integer idCardNumber, Date idCardExpiration, String phoneNumber) {
        return null;
    }

    @Override
    public Optional<Customer> get(Integer idCardNumber) {
        return Optional.empty();
    }

    @Override
    public CustomerList getAll() {
        return null;
    }

    @Override
    public boolean delete(Integer idCardNumber) {
        return false;
    }
}
