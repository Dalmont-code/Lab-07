package it.unibo.nestedenum;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    enum Month {
        JANUARY("January", 31),
        FEBRUARY("February", 28),
        MARCH("March", 31),
        APRIL("April", 30),
        MAY("May", 31),
        JUNE("June", 30),
        JULY("July", 31),
        AUGUST("August", 31),
        SEPTEMBER("September", 30),
        OCTOBER("October", 31),
        NOVEMBER("November", 30),
        DECEMBER("December", 31);

        private String name;
        private int days;

        private Month(final String name, final int days) {
            this.name = name;
            this.days = days;
        }

        private static List<Month> monthList() {
            return List.of(JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
            JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER);
        }

        public static Month fromString(final String text) {
            Month out = null;
            
            for (final Month elem : monthList()) {
                if (elem.name.toLowerCase().contains(text.toLowerCase())) {
                    if (out != null) {
                        throw new IllegalArgumentException("Ambiguous argument: " + text);
                    }

                    out = elem;
                }
            }

            if (out == null) {
                throw new IllegalArgumentException(text + " is not a month");
            }

            return out;
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Month.fromString(o1).days - Month.fromString(o2).days;
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (Month.fromString(o1).equals(Month.fromString(o2))) {
                    return 0;
                }

                for (final Month elem : Month.monthList()) {
                    if (elem.equals(Month.fromString(o1))) {
                        return -1;
                    }

                    if (elem.equals(Month.fromString(o2))) {
                        return 1;
                    }
                }

                return 0;
            }
        };
    }
}
