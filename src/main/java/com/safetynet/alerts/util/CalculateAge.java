package com.safetynet.alerts.util;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class CalculateAge {

    public static Integer calculateAge( Date birthdate) {
        Period.between(birthdate.toInstant()
                .atZone(ZoneId.of("UTC"))
                .toLocalDate(), LocalDate.now()).getYears();
        return   Period.between(birthdate.toInstant()
                .atZone(ZoneId.of("UTC"))
                .toLocalDate(), LocalDate.now()).getYears();
    }
}
