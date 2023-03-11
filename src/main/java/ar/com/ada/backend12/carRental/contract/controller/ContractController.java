package ar.com.ada.backend12.carRental.contract.controller;

import ar.com.ada.backend12.carRental.car.controller.CarController;
import ar.com.ada.backend12.carRental.contract.model.ContractBase;
import ar.com.ada.backend12.carRental.contract.model.ContractFull;
import ar.com.ada.backend12.carRental.contract.model.ContractInfo;
import ar.com.ada.backend12.carRental.contract.service.ContractService;
import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
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

        try {
            ContractBase contractBase = new ContractBase(carPlateId,idCardNumber,startDay,duration,amountPaid);
            ContractInfo contractInfo = contractService.save(contractBase);
            return new ResponseEntity<ApiReturnable>(contractInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ApiReturnable>(new ApiMessage("An error has occurred and the Contract could not be inserted."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contract/{contractNumber}")
    private ResponseEntity<ApiReturnable> get(
            @PathVariable(name = "contractNumber") Integer contractNumber
    ){
        try {
            Optional<ContractBase> contractBase = contractService.get(contractNumber);
            if (contractBase.isPresent()) {
                ContractFull ContractFull = contractService.getFullContract(contractBase.get());
                return new ResponseEntity<ApiReturnable>(ContractFull, HttpStatus.OK);
            } else {
                return new ResponseEntity<ApiReturnable>(new ApiMessage("Contract: " + contractNumber + " not found"), HttpStatus.NOT_FOUND);
        }
        } catch (Exception e) {
            return new ResponseEntity<ApiReturnable>(new ApiMessage("No"), HttpStatus.OK);
        }
    }

}
