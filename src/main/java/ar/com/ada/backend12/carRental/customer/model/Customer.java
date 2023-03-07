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
    private String idCardExpiration;

    public Customer() {
    }

    public Customer(String firstName, String lastName, Date birthDate, Integer idCardNumber, String idCardExpiration) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.idCardNumber = idCardNumber;
        this.idCardExpiration = idCardExpiration;
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

    public String getIdCardExpiration() {
        return idCardExpiration;
    }

    public void setIdCardExpiration(String idCardExpiration) {
        this.idCardExpiration = idCardExpiration;
    }
}
