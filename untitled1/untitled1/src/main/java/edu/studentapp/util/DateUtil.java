package edu.studentapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
/**
 * Допоміжні функції до роботи з датами. *
 * @authorMarco Jakob
 */
    /**
     * Шаблон дати, який використовується для перетворення. Можна міняти на свій.
     */
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * Повертає отриману дату у вигляді добре відформатованого рядка.
     * Використовується певний {@link DateUtil#DATE_PATTERN}. *
     *
     * @paramdate - дата, яка буде повернена у вигляді рядка * @returnвідформатований рядок
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static boolean validDate(String dateString) {
        return DateUtil.parse(dateString) != null;// Намагаємось розібрати рядок.
    }
}