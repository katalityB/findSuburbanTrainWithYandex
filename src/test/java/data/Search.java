package data;

import org.assertj.core.util.DateUtil;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Search {
    String transportType;
    String today;
    String nextSaturday;
    String from;
    String fromSuggest;
    String to;
    String toSuggest;
    String expectedH1;
    String expectedSearchTitle;
    String startFromHour; // format "12:00"
    Integer ticketMaxPrice;
    boolean preferentialPrice;


    public Search() {
        this.transportType = "Электричка";
        this.from = "Екатеринбург";
        this.to = "Каменск-Уральский";
        this.today = getTodayFormatted();
        this.nextSaturday = getNextSaturdayFormatted();
        this.expectedH1 = "Расписание электричек из Екатеринбурга-Пасс. в Каменск-Уральский";
        this.expectedSearchTitle = "суббота";
        this.startFromHour = "12:00"; // format "12:00"
        this.ticketMaxPrice = 200;
        this.preferentialPrice = false;
    }

    public String getTransportType() {
        return transportType;
    }

    public String getToday() {
        return today;
    }

    public String getNextSaturday() {
        return nextSaturday;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getFromSuggest() {
        return fromSuggest;
    }

    public String getToSuggest() {
        return toSuggest;
    }

    public String getExpectedH1() {
        return expectedH1;
    }

    public String getExpectedSearchTitle() {
        return expectedSearchTitle;
    }

    public String getStartFromHour() {
        return startFromHour;
    }

    public Integer getTicketMaxPrice() {
        return ticketMaxPrice;
    }

    public boolean isPreferentialPrice() {
        return preferentialPrice;
    }

    private String getTodayFormatted() {
        DateTime dt = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        return fmt.print(dt);
    }

    private String getNextSaturdayFormatted() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date inputDate = format.parse(this.getToday());
            calendar.setTime(inputDate);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
            calendar.add(Calendar.DATE, 1);
        }

        return format.format(calendar.getTime());
    }
}
