package ar.com.ada.backend12.carRental.contract.model;

import ar.com.ada.backend12.carRental.car.model.Car;
import ar.com.ada.backend12.carRental.customer.model.Customer;

public class ContractFull extends ContractInfo {
    private Car car;
    private Customer customer;

    public ContractFull() {
    }

    public ContractFull(ContractBase contractBase, Car car, Customer customer) {
        super(contractBase, car);
        this.car = car;
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "ContractFull{" +
                "car=" + car +
                ", customer=" + customer +
                '}';
    }
}
