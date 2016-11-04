/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hanhlm
 */
public class DatePro
{
  
  public static List<String> getListDateDDMMYYYY(String dayOfWeek,int numDay){
        List<String> list=new ArrayList<String>();
        Today today=new Today();
        String ddmmyyyy="";
        if(today.getDay()>10){
            ddmmyyyy=today.getDay()+"/";
        }else{
            ddmmyyyy="0"+today.getDay()+"/";
        }
        
        if(today.getMonth()>10){
            ddmmyyyy=ddmmyyyy+today.getMonth()+"/";
        }else{
            ddmmyyyy=ddmmyyyy+"0"+today.getMonth()+"/";
        }
        ddmmyyyy=ddmmyyyy+today.getYear();
        
        ddmmyyyy=getDateDDMMYYYY(ddmmyyyy, dayOfWeek);
        
        for(int i=0;i<numDay;i++){
            list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -(7*i)));
        }
        
        return list;
    }  
    
  public static String getDateOfWeek(int d, int m, int y)
  {
    Today today = new Today(d, m, y);
    String strThu = today.getDayOfWeek().toLowerCase().replace("thu", "");
    strThu = strThu.replace("chu nhat", "cn");
    return strThu.trim().toUpperCase();
  }

  public static String getDateOfWeekDDMMYYYY(String date) {
    String[] arrDate = date.split("/");
    int day = Integer.valueOf(arrDate[0]).intValue();
    int month = Integer.valueOf(arrDate[1]).intValue();
    int year = Integer.valueOf(arrDate[2]).intValue();

    return getDateOfWeek(day, month, year);
  }

  public static long getNumberDay(String startdate, String enddate)
  {
    long result = 0L;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    Date d1 = null;
    Date d2 = null;
    try {
      d1 = format.parse(startdate);
      d2 = format.parse(enddate);

      long diff = d2.getTime() - d1.getTime();

      result = diff / 86400000L;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return result;
  }

  public static String getDateDDMMYYYY(String ddmmyyyy, String dayofweek)
  {
    String dd = ddmmyyyy;
    String dow = getDateOfWeekDDMMYYYY(dd);
    while (!dayofweek.contains(dow.toUpperCase())) {
      dd = DateProc.getDateString(DateProc.getPreviousDate(DateProc.String2Timestamp(dd)));

      dow = getDateOfWeekDDMMYYYY(dd);
    }

    return dd;
  }

  public static List<String> getListDateDDMMYYYY(String dayOfWeek) {
    List list = new ArrayList();
    Today today = new Today();
    String ddmmyyyy = today.getDay() + "/" + today.getMonth() + "/" + today.getYear();
    ddmmyyyy = getDateDDMMYYYY(ddmmyyyy, dayOfWeek);
    list.add(ddmmyyyy);
    list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -7));
    list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -14));
    list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -21));
    list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -28));
    list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -35));
    list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -42));
    list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -49));
    return list;
  }

  public static void main(String[] agr)
  {
    String dow = getDateOfWeekDDMMYYYY("22/11/2015");
    System.out.println(dow);
  }
}