package ar.com.ada.backend12.carRental.customer.service;

import ar.com.ada.backend12.carRental.customer.dto.CustomerDto;
import ar.com.ada.backend12.carRental.customer.model.Customer;
import ar.com.ada.backend12.carRental.customer.dto.CustomersDto;

import java.util.Optional;

public interface CustomerService {
    public CustomerDto save(Customer customer) ;
    public CustomerDto update(Customer customer);
    public Optional<Customer> get(Integer idCardNumber);
    public CustomerDto getReturnableCustomer(Integer idCardNumber);
    public CustomersDto getAll();
    public void delete(Integer idCardNumber);
}
