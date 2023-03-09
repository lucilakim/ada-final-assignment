package ar.com.ada.backend12.carRental.util;

import java.util.Date;

public interface DateUtil {
    public Date parseDate(String dateFormat, String s) throws Exception;
    public Date parseDate(String s) throws Exception;
    public String parseString(Date date);
}
