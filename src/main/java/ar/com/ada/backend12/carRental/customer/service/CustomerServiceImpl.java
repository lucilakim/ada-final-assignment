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
    public Customer save(Customer c) {
        return customerDAO.save(c);
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public Optional<Customer> get(Integer idCardNumber) {
        return customerDAO.findByIdCardNumber(idCardNumber);
    }

    @Override
    public CustomerList getAll() {
        return new CustomerList(customerDAO.findAll());
    }

    @Override
    public boolean delete(Integer idCardNumber) {
        return false;
    }
}
