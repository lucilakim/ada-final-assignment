package ar.com.ada.backend12.carRental.customer.service;

import ar.com.ada.backend12.carRental.customer.model.Customer;
import ar.com.ada.backend12.carRental.customer.model.CustomerList;

import java.util.Date;
import java.util.Optional;

public interface CustomerService {
    public Customer save(Customer customer) ;
    public Customer update(Customer customer);
    public Optional<Customer> get(Integer idCardNumber);
    public CustomerList getAll();
    public void delete(Integer idCardNumber);
}
