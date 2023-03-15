package ar.com.ada.backend12.carRental.contract.dto;

import java.math.BigDecimal;

public class PatchContractReqBody {
    private BigDecimal amountPaid;

    public PatchContractReqBody() {
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }
}
