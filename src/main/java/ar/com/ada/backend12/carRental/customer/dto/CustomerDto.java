package ar.com.ada.backend12.carRental.customer.dto;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;


public class CustomerDto implements ApiReturnable {
    private Integer idCardNumber;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String idCardExpiration;
    private String phoneNumber;

    public CustomerDto() {
    }

    public CustomerDto(Integer idCardNumber, String firstName, String lastName, String phoneNumber) {
        this(idCardNumber, firstName, lastName, null, null, phoneNumber);
    }

    public CustomerDto(Integer idCardNumber, String firstName, String lastName, String birthDate, String idCardExpiration, String phoneNumber) {
        this.idCardNumber = idCardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.idCardExpiration = idCardExpiration;
        this.phoneNumber = phoneNumber;
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
