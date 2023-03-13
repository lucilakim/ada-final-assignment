package ar.com.ada.backend12.carRental.contract.service;

import ar.com.ada.backend12.carRental.contract.model.*;

import java.math.BigDecimal;
import java.util.Optional;

public interface ContractService {
    public ContractInfo save(ContractBase contractBase,String carPlateId, Integer idCardNumber);
    public Optional<ContractBase> get(Integer contractNumber);
    public ContractInfo getInfoContract(ContractBase contractBase);
    public ContractFull getFullContract(ContractBase contractBase);
    public ContractInfoList getAll();
    public void update(Integer contractNumber, BigDecimal amountPaid);
}
