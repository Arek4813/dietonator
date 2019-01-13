package utils.converter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateConverter {

    public Date dateinRegistrationConverter(java.util.Date utilDate) {
        Date sqlDate = new Date(utilDate.getTime());
        return sqlDate;
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
