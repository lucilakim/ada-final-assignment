package ar.com.ada.backend12.carRental.contract.model;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "CONTRACT")
public class ContractBase implements ApiReturnable {
    @Id
    @Column(name = "CONTRACT_NUMBER")
    private Integer contractNumber;
    @Column(name = "START_DAY")
    private Date startDay;
    @Column(name = "DURATION")
    private Integer duration;
    @Column(name = "DAILY_RENT")
    private BigDecimal dailyRent;

    @Column(name = "AMOUNT_PAID")
    private BigDecimal amountPaid;

    @Column(name = "ARREARS_DAYS")
    private Integer arrearsDays;

    public ContractBase() {
    }

    public ContractBase(Integer contractNumber, Date startDay, Integer duration, BigDecimal dailyRent, BigDecimal amountPaid, Integer arrearsDays) {
        this.contractNumber = contractNumber;
        this.startDay = startDay;
        this.duration = duration;
        this.dailyRent = dailyRent;
        this.amountPaid = amountPaid;
        this.arrearsDays = arrearsDays;
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

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Integer getArrearsDays() {
        return arrearsDays;
    }

    public void setArrearsDays(Integer arrearsDays) {
        this.arrearsDays = arrearsDays;
    }
}
