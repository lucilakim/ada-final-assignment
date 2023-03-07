package ar.com.ada.backend12.carRental.customer.service;

import ar.com.ada.backend12.carRental.customer.model.Customer;
import ar.com.ada.backend12.carRental.customer.model.CustomerList;

import java.util.Date;
import java.util.Optional;

public interface CustomerService {
    public Customer save(Customer customer) throws Exception;
    public Customer update(String firstName, String lastName, Date birthDate, Integer idCardNumber, Date idCardExpiration, String phoneNumber);
    public Optional<Customer> get(Integer idCardNumber) throws Exception;
    public CustomerList getAll();
    public boolean delete(Integer idCardNumber);
}
