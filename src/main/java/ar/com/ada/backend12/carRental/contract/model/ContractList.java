package ar.com.ada.backend12.carRental.contract.model;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;

import java.util.List;

public class ContractList implements ApiReturnable {
    private List<ContractFull> contractFullList;

    public ContractList() {
    }

    public ContractList(List<ContractFull> contractFullList) {
        this.contractFullList = contractFullList;
    }

    public List<ContractFull> getContractFullList() {
        return contractFullList;
    }

    public void setContractFullList(List<ContractFull> contractFullList) {
        this.contractFullList = contractFullList;
    }
}
