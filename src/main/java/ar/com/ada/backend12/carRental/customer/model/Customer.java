package ar.com.ada.backend12.carRental.customer.model;

import ar.com.ada.backend12.carRental.util.ApiReturnable;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements ApiReturnable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    @Column(name = "IDENTITY_CARD_NUMBER")
    private Integer idCardNumber;
    @Column(name = "IDENTITY_CARD_EXPIRATION")
    private Date idCardExpiration;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    public Customer() {
    }

    public Customer(String firstName, String lastName, Date birthDate, Integer idCardNumber, Date idCardExpiration, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.idCardNumber = idCardNumber;
        this.idCardExpiration = idCardExpiration;
        this.phoneNumber = phoneNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(Integer idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public Date getIdCardExpiration() {
        return idCardExpiration;
    }

    public void setIdCardExpiration(Date idCardExpiration) {
        this.idCardExpiration = idCardExpiration;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", idCardNumber=" + idCardNumber +
                ", idCardExpiration=" + idCardExpiration +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
