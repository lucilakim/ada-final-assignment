package ar.com.ada.backend12.carRental.contract.model;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;

import java.util.List;

public class ContractInfoList implements ApiReturnable {
    private List<ContractInfo> contractInfoList;

    public ContractInfoList() {
    }

    public ContractInfoList(List<ContractInfo> contractInfoList) {
        this.contractInfoList = contractInfoList;
    }

    public List<ContractInfo> getContractInfoList() {
        return contractInfoList;
    }

    public void setContractInfoList(List<ContractInfo> contractInfoList) {
        this.contractInfoList = contractInfoList;
    }

    public boolean isEmpty(){
        if (contractInfoList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}
