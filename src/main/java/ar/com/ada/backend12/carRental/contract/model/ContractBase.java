package ar.com.ada.backend12.carRental.contract.model;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "CONTRACT")
public class ContractBase implements ApiReturnable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTRACT_NUMBER")
    private Integer contractNumber;

    @Column(name = "IS_RENTED")
    private Boolean isRented;

    @Column(name = "CAR_PLATE_ID")
    private String carPlateId;

    @Column(name = "CUSTOMER_ID")
    private Integer idCardNumber;
    @Column(name = "START_DAY")
    private Date startDay;

    @Column(name = "DURATION")
    private Integer duration;

    @Column(name = "AMOUNT_PAID")
    private BigDecimal amountPaid;

    public ContractBase() {
    }

    public ContractBase(String carPlateId, Integer idCardNumber, Date startDay, Integer duration, BigDecimal amountPaid) {
        this.isRented = true;
        this.carPlateId = carPlateId;
        this.idCardNumber = idCardNumber;
        this.startDay = startDay;
        this.duration = duration;
        this.amountPaid = amountPaid;
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

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractBase that = (ContractBase) o;
        return Objects.equals(contractNumber, that.contractNumber) && Objects.equals(isRented, that.isRented) && Objects.equals(carPlateId, that.carPlateId) && Objects.equals(idCardNumber, that.idCardNumber) && Objects.equals(startDay, that.startDay) && Objects.equals(duration, that.duration) && Objects.equals(amountPaid, that.amountPaid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractNumber, isRented, carPlateId, idCardNumber, startDay, duration, amountPaid);
    }

    @Override
    public String toString() {
        return "ContractBase{" +
                "contractNumber=" + contractNumber +
                ", isRented=" + isRented +
                ", carPlateId='" + carPlateId + '\'' +
                ", idCardNumber=" + idCardNumber +
                ", startDay=" + startDay +
                ", duration=" + duration +
                ", amountPaid=" + amountPaid +
                '}';
    }
}