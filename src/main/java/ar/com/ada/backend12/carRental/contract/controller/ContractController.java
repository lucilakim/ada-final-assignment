package ar.com.ada.backend12.carRental.contract.controller;

import ar.com.ada.backend12.carRental.contract.DAO.ContractDAO;
import ar.com.ada.backend12.carRental.contract.model.ContractBase;
import ar.com.ada.backend12.carRental.contract.model.ContractFull;
import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Date;

import static java.math.BigDecimal.valueOf;

@RestController
public class ContractController {
    @Autowired
    ContractDAO dao;

    @GetMapping("/")
    private ResponseEntity<ApiReturnable> get() {
        ContractBase contractBase = new ContractBase(2, Date.valueOf("2000-09-09"),15, BigDecimal.valueOf(30), BigDecimal.valueOf(315),3);
        ContractFull salida = new ContractFull(contractBase);
        return new ResponseEntity<ApiReturnable>(salida, HttpStatus.OK);
    }
}
