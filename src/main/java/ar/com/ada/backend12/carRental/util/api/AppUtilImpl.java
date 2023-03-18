package ar.com.ada.backend12.carRental.util.api;

import ar.com.ada.backend12.carRental.customer.dto.CustomerDto;
import ar.com.ada.backend12.carRental.util.date.DateUtil;
import ar.com.ada.backend12.carRental.util.date.validation.DateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class AppUtilImpl implements ApiUtil {
    private static final Logger logger = LoggerFactory.getLogger(AppUtilImpl.class);
    @Autowired
    private DateValidator dateValidator;
    @Autowired
    private DateUtil dateUtil;

    public AppUtilImpl() {
    }

    public AppUtilImpl(DateValidator dateValidator, DateUtil dateUtil) {
        this.dateValidator = dateValidator;
        this.dateUtil = dateUtil;
    }

    @Override
    public Date parseDate(String stringDate) {
        try {
            return dateUtil.parseDate(stringDate);
        } catch (Exception e) {
            logger.error("Error trying to change the data type from string to date in birthDate", e);
            throw new RuntimeException("An error has occurred and the Customer could not be updated.");
        }
    }

    @Override
    public int getAge(Date birthDate) {
        Date currentDate = new Date();
        OffsetDateTime startOdt = birthDate.toInstant().atOffset(ZoneOffset.UTC);
        OffsetDateTime endOdt = currentDate.toInstant().atOffset(ZoneOffset.UTC);
        return Period.between(startOdt.toLocalDate(), endOdt.toLocalDate()).getYears();
    }

    @Override
    public CustomerDto getCustomerDto(Integer idCardNumber, String firstName, String lastName, String phoneNumber, Date birthDate, Date idCardExpiration, Integer associatedContract) {
        CustomerDto customerDto = new CustomerDto(idCardNumber, firstName, lastName, phoneNumber, associatedContract);
        customerDto.setBirthDate(dateUtil.parseString(birthDate));
        customerDto.setIdCardExpiration(dateUtil.parseString(idCardExpiration));
        return customerDto;
    }
    @Override
    public String convertUppercase(String string) {
        if (string == null || string.isEmpty()) {
            return string;
        } else {
            return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
        }
    }
}
