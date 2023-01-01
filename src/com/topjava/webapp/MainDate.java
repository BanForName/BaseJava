package com.topjava.webapp;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainDate {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Date date = new Date();
        System.out.println(date);
        System.out.println(System.currentTimeMillis() - start);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        System.out.println(calendar.getTime());

        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        LocalDateTime ldt = LocalDateTime.of(ld, lt);
        System.out.println(ldt);

        SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd");
        System.out.println(sdf.format(date));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YY/MM/dd");
        System.out.println(dtf.format(ldt));

        // java.time.Duration представляет собой промежуток,
        // основанный на времени (часы, минуты, секунды, наносекунды).
        // Его можно создать либо напрямую, либо вывести в качестве разности между другими типами:

        Duration sixHours = Duration.ofHours(6);
        System.out.println(sixHours);

        LocalDateTime lhs = LocalDateTime.of(2020, 12, 15, 22, 23);
        LocalDateTime rhs = LocalDateTime.of(2021, 1, 1, 12, 45, 18);

        Duration different = Duration.between(lhs, rhs);
        System.out.println(different);

        // java.time.Period двойник Duration, только не на основе промежутка времени, а на основе даты (года, месяца, дня).
        Period threeQuartes = Period.ofMonths(9);
        System.out.println(threeQuartes);

        LocalDate firstDate = LocalDate.of(2020, 7, 12);
        LocalDate secDate = LocalDate.of(2021, 1, 1);

        Period diff = Period.between(firstDate, secDate);
        System.out.println(diff);

    }
}