package ar.com.ada.backend12.carRental.customer.service;

import ar.com.ada.backend12.carRental.customer.DAO.CustomerDAO;
import ar.com.ada.backend12.carRental.customer.model.Customer;
import ar.com.ada.backend12.carRental.customer.model.CustomerList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public Customer save(Customer c) {
        if (this.get(c.getIdCardNumber()).isPresent()) {
            return null;
        }
        return customerDAO.save(c);
    }

    @Override
    public Customer update(Customer customer) {
        Optional<Customer> currentCustomer = this.get(customer.getIdCardNumber());
        if(currentCustomer.isPresent()) {
            Customer updatedCustomer = currentCustomer.get();
            if(customer.getFirstName() != null) { updatedCustomer.setFirstName(customer.getFirstName());}
            if(customer.getLastName() != null) { updatedCustomer.setLastName(customer.getLastName());}
            if(customer.getBirthDate() != null) { updatedCustomer.setBirthDate(customer.getBirthDate());}
            if(customer.getIdCardExpiration() != null) { updatedCustomer.setIdCardExpiration(customer.getIdCardExpiration());}
            if(customer.getPhoneNumber() != null) { updatedCustomer.setPhoneNumber(customer.getPhoneNumber());}

            return customerDAO.save(updatedCustomer);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Customer> get(Integer idCardNumber) {
        return customerDAO.findById(idCardNumber);
    }

    @Override
    public CustomerList getAll() {
        return new CustomerList(customerDAO.findAll());
    }

    @Override
    public boolean delete(Integer idCardNumber) {
        Optional<Customer> customer = this.get(idCardNumber);
        if (customer.isPresent()) {
            customerDAO.delete(customer.get());
            return true;
        } else {
            return false;
        }
    }
}
