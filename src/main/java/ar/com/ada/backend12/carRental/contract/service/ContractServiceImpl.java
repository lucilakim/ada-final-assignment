package ar.com.ada.backend12.carRental.contract.service;

import ar.com.ada.backend12.carRental.car.DAO.CarDAO;
import ar.com.ada.backend12.carRental.car.model.Car;
import ar.com.ada.backend12.carRental.contract.DAO.ContractDAO;
import ar.com.ada.backend12.carRental.contract.model.ContractBase;
import ar.com.ada.backend12.carRental.contract.model.ContractFull;
import ar.com.ada.backend12.carRental.contract.model.ContractList;
import ar.com.ada.backend12.carRental.customer.DAO.CustomerDAO;
import ar.com.ada.backend12.carRental.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractServiceImpl  implements ContractService{
    @Autowired
    ContractDAO contractDAO;
    @Autowired
    CarDAO carDAO;
    @Autowired
    CustomerDAO customerDAO;


    @Override
    public ContractFull save(ContractBase contractBase) {
        ContractBase returnedContract = contractDAO.save(contractBase);

        if(returnedContract == null) {
            return null;
        } else {
            Optional<Car> car = carDAO.findById(returnedContract.getCarPlateId());
            Optional<Customer> customer = customerDAO.findById(returnedContract.getIdCardNumber());

            return new ContractFull(returnedContract,car.get(), customer.get());
        }
    }

    @Override
    public Optional<ContractBase> get(Integer contractNumber) {
        return Optional.empty();
    }

    @Override
    public ContractList getAll() {
        return null;
    }

    @Override
    public ContractFull update(ContractBase contractBase) {
        return null;
    }
}
