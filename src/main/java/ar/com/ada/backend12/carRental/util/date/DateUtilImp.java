package ar.com.ada.backend12.carRental.util.date;

import ar.com.ada.backend12.carRental.car.controller.CarController;
import ar.com.ada.backend12.carRental.util.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtilImp implements DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);
    private DateFormat sdf;

    @Autowired
    public DateUtilImp() {
        super();
    }

    public DateUtilImp(DateFormat sdf) {
        this.sdf = sdf;
    }

    /**
     * Converts a String to a java.util.Date Object.
     * @param dateFormat Desired date format
     * @param s date in String Object.
     * @return An Object of type date from the entered String.
     * @throws Exception message --> Date format is incorrect.
     */
    @Override
    public Date parseDate(String dateFormat, String s) throws Exception {
        sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        Date date = null;
        try {
            date = sdf.parse(s);
        }  catch (ParseException e) {
            logger.error("Error trying to change data type from string to date");
            throw new Exception();
        }
        return date;
    }

    @Override
    public Date parseDate(String s) throws Exception {
        try {
            return parseDate("yyyy-MM-dd", s);
        } catch (Exception e) {
            logger.error("Error trying to change data type from string to date");
            throw new Exception("Error trying to change data type from string to date");
        }
    }

    /**
     * Converts a java.util.Date Object to a String.
     * @param date The date to be converted.
     * @return A String with the date in the entered format.
     */
    @Override
    public String parseString(Date date) {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
