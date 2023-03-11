package ar.com.ada.backend12.carRental.contract.model;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;

import java.util.List;

public class ContractList implements ApiReturnable {
    private List<ContractInfo> contractInfoList;

    public ContractList() {
    }

    public ContractList(List<ContractInfo> contractInfoList) {
        this.contractInfoList = contractInfoList;
    }

    public List<ContractInfo> getContractFullList() {
        return contractInfoList;
    }

    public void setContractFullList(List<ContractInfo> contractInfoList) {
        this.contractInfoList = contractInfoList;
    }
}
