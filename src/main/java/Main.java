import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        System.out.println("Wrong date: " + getTrueDate(2019, 1, 1));
        System.out.println("True date: " + getWrongDate(2019, 1, 1));
        System.out.println("Native date: " + getNativeJavaDate(2019, 1, 1));
    }

    private static String getTrueDate(int year, int weekOfYear, int weekDay) {
                DateTime dt = new DateTime()
                .withWeekyear(year)
                .withWeekOfWeekyear(weekOfYear)
                .withDayOfWeek(weekDay);
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("EEEE dd.MM.yyyy");
        dateTimeFormatter.withLocale(Locale.FRENCH);
        return dateTimeFormatter.print(dt);
    }

    private static String getWrongDate(int year, int weekOfYear, int weekDay) {
                DateTime dt = new DateTime()
                .withYear(year)
                .withWeekOfWeekyear(weekOfYear)
                .withDayOfWeek(weekDay);
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("EEEE dd.MM.yyyy");
        return dateTimeFormatter.print(dt);
    }

    private static LocalDate getNativeJavaDate(int year, int weekOfYear, int weekShift) {
        int weekDay = (weekShift == 1) ? Calendar.TUESDAY : (weekShift == 2) ? Calendar.THURSDAY : Calendar.MONDAY;

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        cal.set(Calendar.DAY_OF_WEEK, weekDay);
        cal.set(Calendar.YEAR, year);
        return cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
