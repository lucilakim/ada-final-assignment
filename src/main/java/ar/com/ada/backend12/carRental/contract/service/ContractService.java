package ar.com.ada.backend12.carRental.contract.service;

import ar.com.ada.backend12.carRental.contract.model.ContractBase;
import ar.com.ada.backend12.carRental.contract.model.ContractFull;
import ar.com.ada.backend12.carRental.contract.model.ContractList;

import java.util.Optional;

public interface ContractService {
    public ContractFull save(ContractBase contractBase);
    public Optional<ContractBase> get(Integer contractNumber);
    public ContractList getAll();
    public ContractFull update(ContractBase contractBase);
}
