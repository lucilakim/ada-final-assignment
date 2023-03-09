package ar.com.ada.backend12.carRental.contract.model;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;

import java.math.BigDecimal;
import java.util.Date;

public class ContractFull implements ApiReturnable {
    protected Integer contractNumber;
    protected Date startDay;
    protected Integer duration;
    protected BigDecimal dailyRent;
    protected Integer arrearsDays;

    private BigDecimal balance;
    private BigDecimal amountPaid;
    private BigDecimal amountDue;
    private BigDecimal arrearsDue;
    private BigDecimal totalBalance;

    public ContractFull() {
    }

    public ContractFull(ContractBase contractBase) {
        this.contractNumber = contractBase.getContractNumber();
        this.startDay = contractBase.getStartDay();
        this.duration = contractBase.getDuration();
        this.dailyRent = contractBase.getDailyRent();
        this.arrearsDays = contractBase.getArrearsDays();
        this.amountPaid = contractBase.getAmountPaid();

        this.balance = calculateBalance(duration, dailyRent);
        this.amountDue = calculateAmountDue(balance, amountPaid);
        this.arrearsDue = calculateArrearsDue(arrearsDays);
        this.totalBalance = calculateTotalBalance(amountDue, arrearsDue);
    }

    public Integer getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(Integer contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getDailyRent() {
        return dailyRent;
    }

    public void setDailyRent(BigDecimal dailyRent) {
        this.dailyRent = dailyRent;
    }

    public Integer getArrearsDays() {
        return arrearsDays;
    }

    public void setArrearsDays(Integer arrearsDays) {
        this.arrearsDays = arrearsDays;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public BigDecimal getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(BigDecimal amountDue) {
        this.amountDue = amountDue;
    }

    public BigDecimal getArrearsDue() {
        return arrearsDue;
    }

    public void setArrearsDue(BigDecimal arrearsDue) {
        this.arrearsDue = arrearsDue;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    private BigDecimal calculateBalance(Integer duration, BigDecimal dailyRent)
    {
        return dailyRent.multiply(BigDecimal.valueOf(duration));
    }

    private BigDecimal calculateAmountDue(BigDecimal balance, BigDecimal amountPaid)
    {
        return balance.subtract(amountPaid);
    }

    private BigDecimal calculateArrearsDue(Integer arrearsDays)
    {
        BigDecimal arrearsAmount = dailyRent.multiply(BigDecimal.valueOf(2));
        return arrearsAmount.multiply(BigDecimal.valueOf(arrearsDays));
    }

    private BigDecimal calculateTotalBalance(BigDecimal amountDue, BigDecimal arrearsDue)
    {
        return amountDue.add(arrearsDue);
    }


    @Override
    public String toString() {
        return "ContractFull{" +
                "contractNumber=" + contractNumber +
                ", startDay=" + startDay +
                ", duration=" + duration +
                ", dailyRent=" + dailyRent +
                ", arrearsDays=" + arrearsDays +
                ", balance=" + balance +
                ", amountPaid=" + amountPaid +
                ", amountDue=" + amountDue +
                ", arrearsDue=" + arrearsDue +
                ", totalBalance=" + totalBalance +
                '}';
    }
}
