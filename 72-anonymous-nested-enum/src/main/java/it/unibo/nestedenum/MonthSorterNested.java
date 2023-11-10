package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Month.fromString(o2).days - Month.fromString(o1).days;
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Month.fromString(o1).order - Month.fromString(o2).order;
            } 
        };
    }

    enum Month {
        JANUARY(31,"January",1),FEBRUARY(28,"February",2),MARCH(31,"March",3),
        APRIL(30,"April",4),MAY(31,"May",5),JUNE(30,"June",6),
        JULY(31,"July",7),AUGUST(31,"August",8),SEPTEMPBER(30,"September",9),
        OCTOBER(31,"October",10),NOVEMBER(30,"November",11),DECEMBER(31,"December",12);

        private final int days;
        private final String name;
        private final int order;

        private Month (int days, String name, int order) {
            this.days = days;
            this.name = name;
            this.order = order;
        }

        public int getNumberOfDays(){
            return this.days;
        }

        /**
         * fromString returns the corresponding Month, trying to guess it from a String.
         * This method checks the following formats: "Month", "MONTH", "month"; for 
         * intance "jANuAry" won't be found, also "J" will lead to the first occurrence.
         * If it can't find the corresponding month throws an illegal argument exception.
         * @param attemp the String that should be guessed as a Month
         * @return the Month corresponding
         */
        static Month fromString(String attemp) {
            for (Month m : Month.values()) {
                if (m.name.contains(attemp) || m.name.toLowerCase().contains(attemp) ||
                m.name.toUpperCase().contains(attemp)) {
                    return m;
                }
            }
            throw new IllegalArgumentException();
        }
    }
}
