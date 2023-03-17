package ar.com.ada.backend12.carRental.util.api;

import java.util.Date;

public interface AppUtil {
    public Date parseDate(String stringBirthDate);
    public int getAge(Date birthDate);
}
