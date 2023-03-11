package ar.com.ada.backend12.carRental.contract.service;

import ar.com.ada.backend12.carRental.contract.model.ContractBase;
import ar.com.ada.backend12.carRental.contract.model.ContractFull;
import ar.com.ada.backend12.carRental.contract.model.ContractInfo;
import ar.com.ada.backend12.carRental.contract.model.ContractInfoList;

import java.util.Optional;

public interface ContractService {
    public ContractInfo save(ContractBase contractBase);
    public Optional<ContractBase> get(Integer contractNumber);
    public ContractInfo getInfoContract(ContractBase contractBase);
    public ContractFull getFullContract(ContractBase contractBase);
    public ContractInfoList getAll();
    public ContractInfo update(ContractBase contractBase);
}
