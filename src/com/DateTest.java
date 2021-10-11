package com;

import java.util.Calendar;
import java.util.TimeZone;

public class DateTest {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2021);
        cal.set(Calendar.MONTH, Calendar.AUGUST);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        System.out.println(cal.getTime());

        cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 30);
        cal.roll(Calendar.DAY_OF_MONTH, 1);
        System.out.println(cal.getTime());
        System.out.println();

        //getDefault() - получает id зоны из ОС.
        TimeZone zone = TimeZone.getDefault();
        System.out.println(zone.toZoneId());

        //getAvaiblablesIds() - массив возможных значения id зон.
        String[] zones = TimeZone.getAvailableIDs();
        for (String str : zones) {
            System.out.println(str);
        }

    }
}
