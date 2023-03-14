package ar.com.ada.backend12.carRental.contract.dto;

import jakarta.persistence.Column;

import java.util.Date;

public class PatchCustomerBody {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String idCardExpiration;

    private String phoneNumber;

    public PatchCustomerBody() {
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdCardExpiration() {
        return idCardExpiration;
    }

    public void setIdCardExpiration(String idCardExpiration) {
        this.idCardExpiration = idCardExpiration;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
