package ar.com.ada.backend12.carRental.contract.service;

import ar.com.ada.backend12.carRental.car.DAO.CarDAO;
import ar.com.ada.backend12.carRental.car.model.Car;
import ar.com.ada.backend12.carRental.contract.DAO.ContractDAO;
import ar.com.ada.backend12.carRental.contract.model.*;
import ar.com.ada.backend12.carRental.customer.DAO.CustomerDAO;
import ar.com.ada.backend12.carRental.customer.model.Customer;
import ar.com.ada.backend12.carRental.exception.BadRequestException;
import ar.com.ada.backend12.carRental.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
    public ContractInfo save(ContractBase contractBase) {
        ContractBase returnedContract = contractDAO.save(contractBase);

        if(returnedContract == null) {
            return null;
        } else {
            Optional<Car> car = carDAO.findById(returnedContract.getCarPlateId());
            Optional<Customer> customer = customerDAO.findById(returnedContract.getIdCardNumber());

            return new ContractInfo(returnedContract,car.get());
        }
    }

    @Override
    public Optional<ContractBase> get(Integer contractNumber) {
        return contractDAO.findById(contractNumber);
    }

    @Override
    public ContractInfo getInfoContract(ContractBase contractBase) {
        Optional<Car> car = carDAO.findById(contractBase.getCarPlateId());
        Optional<Customer> customer = customerDAO.findById(contractBase.getIdCardNumber());

        return new ContractInfo(contractBase,car.get());
    }
    @Override
    public ContractFull getFullContract(ContractBase contractBase) {
        Optional<Car> car = carDAO.findById(contractBase.getCarPlateId());
        Optional<Customer> customer = customerDAO.findById(contractBase.getIdCardNumber());

        return new ContractFull(contractBase,car.get(), customer.get());
    }
    @Override
    public ContractInfoList getAll() {
        List<ContractBase> contractBaseList = contractDAO.findAll();
        ContractBase contractBase = null;
        ContractInfo contractInfo = null;
        Optional<Car> car = null;
        List<ContractInfo> listContract = new ArrayList<ContractInfo>();

        for (int i = 0; i<contractBaseList.size(); i++) {
             contractBase = contractBaseList.get(i);
             car = carDAO.findById(contractBase.getCarPlateId());

             if (car.isEmpty()) {
                 return  null;
             } else {
                 contractInfo = new ContractInfo(contractBase, car.get());
                listContract.add(contractInfo);
             }
        }
        return new ContractInfoList(listContract);
    }

//    @Override
//    public ContractUpdateResult update(Integer contractNumber, BigDecimal amountPaid) {
//        Optional<ContractBase> contractBase = contractDAO.findById(contractNumber);
//
//        if(contractBase.isEmpty()) {
//            return new ContractUpdateResult(ContractUpdateCode.NOT_FOUND);
//        }
//
//        if(!contractBase.get().getRented()) {
//            return new ContractUpdateResult(ContractUpdateCode.NOT_RENTED);
//        }
//
//        Optional<Car> car = carDAO.findById(contractBase.get().getCarPlateId());
//        ContractInfo contractInfo = new ContractInfo(contractBase.get(), car.get());
//        if (!contractInfo.getTotalBalance().equals(amountPaid)) {
//            String message = String.format("Contract: %s The amount paid should be equals to the total balance. " +
//                    "The total balance is: %s, and the amount paid is: %s.",
//                    contractNumber, contractInfo.getTotalBalance(),amountPaid);
//            return new ContractUpdateResult(ContractUpdateCode.INCORRECT_AMOUNT, message);
//        }
//
//        contractBase.get().setRented(false);
//        contractDAO.save(contractBase.get());
//        return new ContractUpdateResult(ContractUpdateCode.UPDATED);
//    }

    @Override
    public void update(Integer contractNumber, BigDecimal amountPaid) {
        Optional<ContractBase> contractBase = contractDAO.findById(contractNumber);

        if(contractBase.isEmpty()) {
            throw new NotFoundException("Contract: " + contractNumber + " not found.");
        }

        if(!contractBase.get().getRented()) {
            throw new BadRequestException("Contract: " + contractNumber + " is not a current contract because it is closed.");
        }

        Optional<Car> car = carDAO.findById(contractBase.get().getCarPlateId());
        ContractInfo contractInfo = new ContractInfo(contractBase.get(), car.get());
        if (!contractInfo.getTotalBalance().equals(amountPaid)) {
            String message = String.format("Contract: %s The amount paid should be equals to the total balance. " +
                    "The total balance is: %s, and the amount paid is: %s.",
                    contractNumber, contractInfo.getTotalBalance(),amountPaid);
            throw new BadRequestException(message);
        }

        contractBase.get().setRented(false);
        contractDAO.save(contractBase.get());
    }
}
