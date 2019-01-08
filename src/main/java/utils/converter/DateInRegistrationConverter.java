package utils.converter;

import java.sql.Date;

public class DateInRegistrationConverter {

    public Date dateConverter (java.util.Date utilDate) {
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }
}
