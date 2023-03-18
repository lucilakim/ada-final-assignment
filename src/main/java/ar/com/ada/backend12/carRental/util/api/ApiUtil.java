package ar.com.ada.backend12.carRental.util.api;

import ar.com.ada.backend12.carRental.customer.dto.CustomerDto;

import java.util.Date;

public interface ApiUtil {
    public Date parseDate(String stringBirthDate);
    public int getAge(Date birthDate);
    public CustomerDto getCustomerDto(Integer idCardNumber, String firstName, String lastName, String phoneNumber, Date birthDate, Date idCardExpiration, Integer associatedContract);
    public String convertUppercase(String string);
}
