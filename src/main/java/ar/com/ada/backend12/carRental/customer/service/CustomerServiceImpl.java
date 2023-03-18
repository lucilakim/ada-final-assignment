package ar.com.ada.backend12.carRental.customer.service;

import ar.com.ada.backend12.carRental.contract.model.ContractBase;
import ar.com.ada.backend12.carRental.contract.service.ContractService;
import ar.com.ada.backend12.carRental.customer.DAO.CustomerDAO;
import ar.com.ada.backend12.carRental.customer.dto.CustomerDto;
import ar.com.ada.backend12.carRental.customer.model.Customer;
import ar.com.ada.backend12.carRental.customer.dto.CustomersDto;
import ar.com.ada.backend12.carRental.exception.BadRequestException;
import ar.com.ada.backend12.carRental.exception.NotFoundException;
import ar.com.ada.backend12.carRental.util.api.ApiUtil;
import ar.com.ada.backend12.carRental.util.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    ContractService contractService;
    @Autowired
    DateUtil dateUtil;

    @Autowired
    ApiUtil apiUtil;

    @Override
    public CustomerDto save(Customer c) {
        Integer customerIdNumber = c.getIdCardNumber();
        Optional<Customer> customer = customerDAO.findById(customerIdNumber);
        if (customer.isPresent()) {
            throw new BadRequestException(String.format("Customer with the idNumber: %s already exists.", customerIdNumber));
        }
        Customer returnedCustomer = customerDAO.save(c);
        CustomerDto customerDto = apiUtil.getCustomerDto(returnedCustomer.getIdCardNumber(), returnedCustomer.getFirstName(),
                returnedCustomer.getLastName(), returnedCustomer.getPhoneNumber(), returnedCustomer.getBirthDate(),
                returnedCustomer.getIdCardExpiration(), returnedCustomer.getAssociatedContract());
        return customerDto;
    }

    @Override
    public CustomerDto update(Customer customer) {
        Optional<Customer> currentCustomer = this.get(customer.getIdCardNumber());
        if(currentCustomer.isEmpty()) {
            throw new NotFoundException(String.format("The client with ID number: %s was not found", customer.getIdCardNumber()));
        }

        Customer updatedCustomer = currentCustomer.get();
        if(customer.getFirstName() != null) { updatedCustomer.setFirstName(customer.getFirstName());}
        if(customer.getLastName() != null) { updatedCustomer.setLastName(customer.getLastName());}
        if(customer.getBirthDate() != null) { updatedCustomer.setBirthDate(customer.getBirthDate());}
        if(customer.getIdCardExpiration() != null) { updatedCustomer.setIdCardExpiration(customer.getIdCardExpiration());}
        if(customer.getPhoneNumber() != null) { updatedCustomer.setPhoneNumber(customer.getPhoneNumber());}

        Customer returnedCustomer = customerDAO.save(updatedCustomer);
        CustomerDto customerDto = apiUtil.getCustomerDto(returnedCustomer.getIdCardNumber(), returnedCustomer.getFirstName(),
                returnedCustomer.getLastName(), returnedCustomer.getPhoneNumber(), returnedCustomer.getBirthDate(),
                returnedCustomer.getIdCardExpiration(), returnedCustomer.getAssociatedContract());
        return customerDto;
    }

    @Override
    public Optional<Customer> get(Integer idCardNumber) {
        Optional<Customer> customer = customerDAO.findById(idCardNumber);
        if(customer.isEmpty()) throw new NotFoundException(String.format("The client with ID number: %s was not found", idCardNumber));
        return customer;
    }

    @Override
    public CustomerDto getReturnableCustomer(Integer idCardNumber) {
        Optional<Customer> customerOptional = customerDAO.findById(idCardNumber);
        if(customerOptional.isEmpty()) throw new NotFoundException(String.format("The client with ID number: %s was not found", idCardNumber));

        Customer customer = customerOptional.get();

        CustomerDto customerDto = new CustomerDto(idCardNumber, customer.getFirstName(),
                customer.getLastName(), customer.getPhoneNumber(), customer.getAssociatedContract());
        customerDto.setBirthDate(dateUtil.parseString(customer.getBirthDate()));
        customerDto.setIdCardExpiration(dateUtil.parseString(customer.getIdCardExpiration()));
        return customerDto;
    }

    @Override
    public CustomersDto getAll() {
        List<Customer> customers = customerDAO.findAll();
        List<CustomerDto> dto = new ArrayList<>();
        if(customers.isEmpty()) throw new NotFoundException("The list of clients is empty. There is no client yet.");

        Customer customer = null;
        CustomerDto customerDto = null;
        for (int i = 0; i < customers.size(); i++) {
            customer = customers.get(i);
            customerDto = apiUtil.getCustomerDto(customer.getIdCardNumber(), customer.getFirstName(),
                    customer.getLastName(), customer.getPhoneNumber(), customer.getBirthDate(),
                    customer.getIdCardExpiration(), customer.getAssociatedContract());
            dto.add(customerDto);
        }
        CustomersDto customersDto = new CustomersDto(dto);
        return customersDto;
    }

    @Override
    public void delete(Integer idCardNumber) {
        Optional<Customer> customer = this.get(idCardNumber);
        if(customer.isEmpty()) {
            throw new NotFoundException(String.format("The customer with ID number: %s was not found", idCardNumber));
        }

        Optional<ContractBase> contractBase = contractService.getByIdCardNumber(customer.get().getIdCardNumber());
        if(contractBase.isPresent()) {
            throw new BadRequestException(String.format("The customer: %s cannot be deleted because it has/had this contract associated: %s.", idCardNumber, contractBase.get().getContractNumber()));
        }

        customerDAO.delete(customer.get());
    }
}
