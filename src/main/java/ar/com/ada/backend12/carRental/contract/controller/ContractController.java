package ar.com.ada.backend12.carRental.contract.controller;

import ar.com.ada.backend12.carRental.car.controller.CarController;
import ar.com.ada.backend12.carRental.contract.dto.PatchContractReqBody;
import ar.com.ada.backend12.carRental.contract.model.*;
import ar.com.ada.backend12.carRental.contract.service.ContractService;
import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import ar.com.ada.backend12.carRental.util.api.message.ApiMessage;
import ar.com.ada.backend12.carRental.util.date.DateUtil;
import ar.com.ada.backend12.carRental.util.date.validation.DateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private DateValidator dateValidator;
    @Autowired
    private DateUtil dateUtil;


    @PostMapping("/contract")
    private ResponseEntity<ApiReturnable> save(
            @RequestParam(name = "carPlateId") String carPlateId
            ,@RequestParam(name = "idCardNumber") Integer idCardNumber
            ,@RequestParam(name = "startDay") String stringStartDay
            ,@RequestParam(name = "duration") Integer duration
            ,@RequestParam(name = "amountPaid") BigDecimal amountPaid
    ){
        Date startDay = null;
        if(dateValidator.isValid(stringStartDay)){
            try {
                startDay = dateUtil.parseDate(stringStartDay);
            } catch (Exception e) {
                logger.error("Error trying to change data type from string to date", e);
                return new ResponseEntity<ApiReturnable>(new ApiMessage("An error has occurred and the Customer could not be inserted."), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<ApiReturnable>(new ApiMessage("The date format is not valid. The expected format is yyyy-MM-dd"), HttpStatus.BAD_REQUEST);
        }

        ContractBase contractBase = new ContractBase(carPlateId,idCardNumber,startDay,duration,amountPaid);
        ContractInfo contractInfo = contractService.save(contractBase,carPlateId, idCardNumber);
        return new ResponseEntity<ApiReturnable>(contractInfo, HttpStatus.CREATED);

    }

    @GetMapping("/contract/{contractNumber}")
    private ResponseEntity<ApiReturnable> get(
            @PathVariable(name = "contractNumber") Integer contractNumber
    ){
        Optional<ContractBase> contractBase = contractService.get(contractNumber);
        ContractFull ContractFull = contractService.getFullContract(contractBase.get());
        return new ResponseEntity<>(ContractFull, HttpStatus.OK);

    }

    @GetMapping("/contract")
    private ResponseEntity<ApiReturnable> getAll(){
        ContractInfoList contractInfoList = contractService.getAll();
        return new ResponseEntity<>(contractInfoList, HttpStatus.OK);
    }

    @PatchMapping(value = "/contract/{contractNumber}")
    private ResponseEntity<ApiReturnable> update (
            @PathVariable(name = "contractNumber") Integer contractNumber,
            @RequestBody PatchContractReqBody body
    ) {
        BigDecimal twoDecimalsAmountPaid = body.getAmountPaid().setScale(2);
        contractService.update(contractNumber,twoDecimalsAmountPaid);

        return new ResponseEntity<>(new ApiMessage("Contract: " + contractNumber + " closed. And your ending balance is $0."), HttpStatus.OK);
    }
}
