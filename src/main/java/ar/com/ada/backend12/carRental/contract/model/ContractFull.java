package ar.com.ada.backend12.carRental.contract.model;

import ar.com.ada.backend12.carRental.car.model.Car;
import ar.com.ada.backend12.carRental.customer.model.Customer;
import ar.com.ada.backend12.carRental.util.api.ApiReturnable;

import java.math.BigDecimal;
import java.util.Date;

public class ContractFull implements ApiReturnable {
    private Integer contractNumber;
    private Boolean isRented;
    private String carPlateId;
    private Integer idCardNumber;
    private Date startDay;
    private Integer duration;

    private BigDecimal dailyRent;
    private BigDecimal balance;

    private BigDecimal amountPaid;

    private BigDecimal amountDue;
    private Integer arrearsDays;
    private BigDecimal arrearsDue;
    private BigDecimal totalBalance;

    public ContractFull() {
    }

    public ContractFull(ContractBase contractBase, Car car, Customer customer) {
        this.contractNumber = contractBase.getContractNumber();
        this.isRented = contractBase.getRented();
        this.carPlateId = contractBase.getCarPlateId();
        this.idCardNumber = contractBase.getIdCardNumber();
        this.startDay = contractBase.getStartDay();
        this.duration = contractBase.getDuration();
        this.dailyRent = car.getDailyRent();
        this.balance = this.calculateBalance(duration,dailyRent);
        this.amountPaid = contractBase.getAmountPaid();
        this.amountDue = this.calculateAmountDue(balance,amountPaid);
        this.arrearsDays = 0;
        this.arrearsDue = BigDecimal.valueOf(0);
        this.totalBalance = this.calculateTotalBalance(amountDue, arrearsDue);
    }

    public Integer getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(Integer contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Boolean getRented() {
        return isRented;
    }

    public void setRented(Boolean rented) {
        isRented = rented;
    }

    public String getCarPlateId() {
        return carPlateId;
    }

    public void setCarPlateId(String carPlateId) {
        this.carPlateId = carPlateId;
    }

    public Integer getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(Integer idCardNumber) {
        this.idCardNumber = idCardNumber;
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

    public Integer getArrearsDays() {
        return arrearsDays;
    }

    public void setArrearsDays(Integer arrearsDays) {
        this.arrearsDays = arrearsDays;
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
                ", isRented=" + isRented +
                ", carPlateId='" + carPlateId + '\'' +
                ", idCardNumber=" + idCardNumber +
                ", startDay=" + startDay +
                ", duration=" + duration +
                ", dailyRent=" + dailyRent +
                ", balance=" + balance +
                ", amountPaid=" + amountPaid +
                ", amountDue=" + amountDue +
                ", arrearsDays=" + arrearsDays +
                ", arrearsDue=" + arrearsDue +
                ", totalBalance=" + totalBalance +
                '}';
    }
}
