/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author iNET
 */
public class DatePro {

    public static String convertDayOfWeek(String input) {
        if ("hai".equalsIgnoreCase(input)) {
            return "2";
        }
        if ("ba".equalsIgnoreCase(input)) {
            return "3";
        }
        if ("tu".equalsIgnoreCase(input)) {
            return "4";
        }
        if ("nam".equalsIgnoreCase(input)) {
            return "5";
        }
        if ("sau".equalsIgnoreCase(input)) {
            return "6";
        }
        if ("bay".equalsIgnoreCase(input)) {
            return "7";
        }
        if ("cn".equalsIgnoreCase(input)) {
            return "cn";
        }

        return null;
    }

    public static String getDateOfWeek(int d, int m, int y) {
        Today today = new Today(d, m, y);
        String strThu = today.getDayOfWeek().toLowerCase().replace("thu", "");
        strThu = strThu.replace("chu nhat", "cn");
        return strThu.trim();
    }

    public static String getDateOfWeekDDMMYYYY(String date) {
        String[] arrDate = date.split("/");
        int day = Integer.valueOf(arrDate[0]);
        int month = Integer.valueOf(arrDate[1]);
        int year = Integer.valueOf(arrDate[2]);

        return getDateOfWeek(day, month, year);
    }

    public static String getDateOfWeek(String date) {
        if(date == null){
            return null;
        }
        String[] arrDate = date.split("/");
        if(arrDate == null || arrDate.length < 3){
            return null;
        }
        int day = Integer.valueOf(arrDate[0]);
        int month = Integer.valueOf(arrDate[1]);
        int year = Integer.valueOf(arrDate[2]);
        Today today = new Today(day, month, year);
        return today.getDayOfWeek();
    }

    //startdate dd/mm/yyyy
    //enddate dd/mm/yyyy
    public static long getNumberDay(String startdate, String enddate) {
        long result = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(startdate);
            d2 = format.parse(enddate);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

//        long diffSeconds = diff / 1000 % 60;
//        long diffMinutes = diff / (60 * 1000) % 60;
//        long diffHours = diff / (60 * 60 * 1000) % 24;
            result = diff / (24 * 60 * 60 * 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //dd/mm/yyyy
    //dayofweek
    //dd/mm/yyyy
    public static String getDateDDMMYYYY(String ddmmyyyy, String dayofweek) {
        String dd = ddmmyyyy;
        String dow = getDateOfWeekDDMMYYYY(dd);
        int step = 1;
        while (!dayofweek.contains(dow.toUpperCase())) {
            dd = DateProc.getDateString(DateProc.getPreviousDate(DateProc.String2Timestamp(dd)));
            //System.out.println("dd--"+dd);
            dow = getDateOfWeekDDMMYYYY(dd);
            //System.out.println("dow--"+dow);
            if (step > 7) {
                break;
            }
            step++;
        }
        System.out.println("dayofweek==>>" + dayofweek + "==ddmmyyyy==>>" + dd);
        return dd;
    }

    public static List<String> getListDateDDMMYYYY(String dayOfWeek, int numDay) {
        List<String> list = new ArrayList<String>();
        Today today = new Today();
        String ddmmyyyy = "";
        if (today.getDay() > 10) {
            ddmmyyyy = today.getDay() + "/";
        } else {
            ddmmyyyy = "0" + today.getDay() + "/";
        }

        if (today.getMonth() > 10) {
            ddmmyyyy = ddmmyyyy + today.getMonth() + "/";
        } else {
            ddmmyyyy = ddmmyyyy + "0" + today.getMonth() + "/";
        }
        ddmmyyyy = ddmmyyyy + today.getYear();

        ddmmyyyy = getDateDDMMYYYY(ddmmyyyy, dayOfWeek);

        for (int i = 0; i < numDay; i++) {
            list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -(7 * i)));
        }

        return list;
    }

    //lay ngay hom truoc
    public static String getDatePrevToday(int prev) {
        Today today = new Today();
        String ddmmyyyy = "";
        if (today.getDay() > 10) {
            ddmmyyyy = today.getDay() + "/";
        } else {
            ddmmyyyy = "0" + today.getDay() + "/";
        }

        if (today.getMonth() > 10) {
            ddmmyyyy = ddmmyyyy + today.getMonth() + "/";
        } else {
            ddmmyyyy = ddmmyyyy + "0" + today.getMonth() + "/";
        }
        ddmmyyyy = ddmmyyyy + today.getYear();

        ddmmyyyy = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -prev);

        return ddmmyyyy;
    }

    public static void main(String[] agr) {
        //System.out.println("day "+getDatePrevToday(55));
        System.out.println("thu " + getDateDDMMYYYY("07/04/2015", "4-CN"));
//        List<String> list=getListDateDDMMYYYY("2");
//        for(int i=0;i<list.size();i++){
//            System.out.println("ddmmyyyy---"+list.get(i));
//        }
    }
}
