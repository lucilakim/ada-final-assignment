package ar.com.ada.backend12.carRental.car.util;

import java.util.Date;

public interface CustomerUtil {
    public Date parseDate(String stringBirthDate);
    public int getAge(Date birthDate);
}
