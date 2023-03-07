package ar.com.ada.backend12.carRental.customer.model;

import ar.com.ada.backend12.carRental.util.ApiReturnable;

import java.util.List;

public class CustomerList implements ApiReturnable {
    private List<Customer> customerList;

    public CustomerList() {
    }

    public CustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
