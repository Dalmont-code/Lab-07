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

        public Month fromString(final String text) {
            Month out = null;
            Collection<Month> months = 
            List.of(JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
            JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER);

            for (final Month elem : months) {
                if (elem.name.substring(0, text.length()) == text.substring(0, text.length())) {
                    if (out != null) {
                        throw new IllegalArgumentException("Ambiguous argument: " + text);
                    }

                    out = elem;
                }
            }

            if (out == null) {
                throw new NoSuchElementException(text + " is not a month");
            }

            return out;
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return null;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
    }
}
