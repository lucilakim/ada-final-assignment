package ar.com.ada.backend12.carRental.contract.controller;

import ar.com.ada.backend12.carRental.car.controller.CarController;
import ar.com.ada.backend12.carRental.car.service.CarService;
import ar.com.ada.backend12.carRental.car.validation.CarValidator;
import ar.com.ada.backend12.carRental.contract.dto.PatchContractReqBody;
import ar.com.ada.backend12.carRental.contract.model.*;
import ar.com.ada.backend12.carRental.contract.service.ContractService;
import ar.com.ada.backend12.carRental.contract.validation.ContractValidator;
import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import ar.com.ada.backend12.carRental.util.api.ApiUtil;
import ar.com.ada.backend12.carRental.util.api.message.ApiMessage;
import ar.com.ada.backend12.carRental.util.date.DateUtil;
import ar.com.ada.backend12.carRental.util.date.validation.DateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@RestController
public class ContractController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);
    @Autowired
    ContractService contractService;
    @Autowired
    CarService carService;
    @Autowired
    DateValidator dateValidator;
    @Autowired
    DateUtil dateUtil;
    @Autowired
    ApiUtil apiUtil;

    @PostMapping("/contract")
    private ResponseEntity<ApiReturnable> save(
            @RequestParam(name = "carPlateId") String carPlateId
            , @RequestParam(name = "idCardNumber") Integer idCardNumber
            , @RequestParam(name = "startDay") String stringStartDay
            , @RequestParam(name = "duration") Integer duration
            , @RequestParam(name = "amountPaid") BigDecimal amountPaid
    ) {
        CarValidator.validateCarPlateId(carPlateId);
        BigDecimal carDailyRent = null;
        Date startDay = null;
        if (CarValidator.carPlateIdIsValid(carPlateId)) carDailyRent = carService.get(carPlateId).get().getDailyRent();
        ContractValidator.validateSaveInputs(carPlateId, idCardNumber, stringStartDay, duration, amountPaid, carDailyRent);
        if (stringStartDay != null) startDay = apiUtil.parseDate(stringStartDay);

        ContractBase contractBase = new ContractBase(carPlateId, idCardNumber, startDay, duration, amountPaid);
        logger.info("Trying to save a Contract in the database.");
        logger.debug(String.format("contractNumber [ %s ].", contractBase.getContractNumber()));
        ContractInfo contractInfo = contractService.save(contractBase, carPlateId, idCardNumber);
        return new ResponseEntity<>(contractInfo, HttpStatus.CREATED);
    }

    @GetMapping("/contract/{contractNumber}")
    private ResponseEntity<ApiReturnable> get(
            @PathVariable(name = "contractNumber") Integer contractNumber
    ) {
        ContractValidator.validateContractNumber(contractNumber);
        logger.info("Trying to get a Contract from the database.");
        logger.debug(String.format("contractNumber [ %s ].", contractNumber));
        Optional<ContractBase> contractBase = contractService.get(contractNumber);
        ContractFull ContractFull = contractService.getFullContract(contractBase.get());
        return new ResponseEntity<>(ContractFull, HttpStatus.OK);
    }

    @GetMapping("/contract")
    private ResponseEntity<ApiReturnable> getAll() {
        logger.info("Trying to get all the Contracts in the database.");
        ContractInfoList contractInfoList = contractService.getAll();
        return new ResponseEntity<>(contractInfoList, HttpStatus.OK);
    }

    @PatchMapping(value = "/contract/{contractNumber}")
    private ResponseEntity<ApiReturnable> update(
            @PathVariable(name = "contractNumber") Integer contractNumber,
            @RequestBody PatchContractReqBody body
    ) {
        ContractValidator.validateContractNumber(contractNumber);
        ContractInfo contractInfo = null;
        BigDecimal totalBalance = null;
        Optional<ContractBase> contractBase = contractService.get(contractNumber);
        if (contractBase.isPresent()) contractInfo = contractService.getInfoContract(contractBase.get());
        if (contractInfo != null) totalBalance = contractInfo.getTotalBalance();
        ContractValidator.validateUpdateContract(body.getAmountPaid(), totalBalance);

        logger.info("Trying to update a Contract in the database.");
        logger.debug(String.format("contractNumber [ %s ].", contractNumber));
        BigDecimal twoDecimalsAmountPaid = body.getAmountPaid().setScale(2);
        contractService.update(contractNumber, twoDecimalsAmountPaid);
        return new ResponseEntity<>(new ApiMessage("Contract: " + contractNumber + " closed. And your ending balance is $0."), HttpStatus.OK);
    }
}
