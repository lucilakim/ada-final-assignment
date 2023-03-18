package ar.com.ada.backend12.carRental.customer.model;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements ApiReturnable {
    @Id
    @Column(name = "ID_CARD_NUMBER")
    private Integer idCardNumber;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    @Column(name = "ID_CARD_EXPIRATION")
    private Date idCardExpiration;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ASSOCIATED_CONTRACT")
    private Integer associatedContract;

    public Customer() {
    }

    public Customer(Integer idCardNumber, String firstName, String lastName, Date birthDate, Date idCardExpiration, String phoneNumber) {
        this(idCardNumber, firstName, lastName, birthDate, idCardExpiration, phoneNumber, null);
    }

    public Customer(Integer idCardNumber, String firstName, String lastName, Date birthDate, Date idCardExpiration, String phoneNumber, Integer associatedContract) {
        this.idCardNumber = idCardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.idCardExpiration = idCardExpiration;
        this.phoneNumber = phoneNumber;
        this.associatedContract = associatedContract;
    }

    public Integer getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(Integer idCardNumber) {
        this.idCardNumber = idCardNumber;
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

    public Integer getAssociatedContract() {
        return associatedContract;
    }

    public void setAssociatedContract(Integer associatedContract) {
        this.associatedContract = associatedContract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(idCardNumber, customer.idCardNumber) && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(birthDate, customer.birthDate) && Objects.equals(idCardExpiration, customer.idCardExpiration) && Objects.equals(phoneNumber, customer.phoneNumber) && Objects.equals(associatedContract, customer.associatedContract);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCardNumber, firstName, lastName, birthDate, idCardExpiration, phoneNumber, associatedContract);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idCardNumber=" + idCardNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", idCardExpiration=" + idCardExpiration +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", associatedContract=" + associatedContract +
                '}';
    }
}
