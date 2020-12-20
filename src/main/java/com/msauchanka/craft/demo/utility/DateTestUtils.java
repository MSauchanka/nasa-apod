package com.msauchanka.craft.demo.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTestUtils {

    private DateTimeFormatter defaultDateFormatter;

    public DateTestUtils(String dateFormat) {
        defaultDateFormatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    public String getTodayDate() {
        return LocalDate.now().format(defaultDateFormatter);
    }

    public String getYesterdayDate() {
        return LocalDate.now().minusDays(1).format(defaultDateFormatter);
    }

    public String getNextMonthDate() {
        return LocalDate.now().plusMonths(1).format(defaultDateFormatter);
    }
}
