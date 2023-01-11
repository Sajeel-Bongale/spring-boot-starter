package com.clairvoyant.employees.utils;

import java.time.LocalDate;
import java.time.Period;

public final class DateUtil {
    private DateUtil() {

    }

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}