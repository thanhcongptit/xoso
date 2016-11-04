package inet.util;

import java.sql.Timestamp;
import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public DateUtil() {
    }

    public static Date toDate(String strSource) {
        return toDate(strSource, DateFormat.getDateInstance());
    }

    public static Date toDate(String strSource, String strFormat) {
        SimpleDateFormat fmt = new SimpleDateFormat(strFormat);        
        return toDate(strSource, ((DateFormat) (fmt)));
    }

    public static Date toDate(String strSource, DateFormat fmt) {
        try {
            fmt.setLenient(false);
            return fmt.parse(strSource);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Date addSecond(Date dt, int iValue) {
        return add(dt, iValue, 13);
    }

    public static Date addMinute(Date dt, int iValue) {
        return add(dt, iValue, 12);
    }

    public static Date addHour(Date dt, int iValue) {
        return add(dt, iValue, 10);
    }

    public static Date addDay(Date dt, int iValue) {
        return add(dt, iValue, 5);
    }

    public static Date addMonth(Date dt, int iValue) {
        return add(dt, iValue, 2);
    }

    public static Date addYear(Date dt, int iValue) {
        return add(dt, iValue, 1);
    }

    public static Date add(Date dt, int iValue, int iType) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(dt);
        cld.add(iType, iValue);
        return cld.getTime();
    }

    public static int compareDateTime(String strStartDate, String strEndDate,
            String formatMask) throws Exception {
        try {
            Date datStaDateTime;
            Date datEndDateTime;
            SimpleDateFormat fmtDate = new SimpleDateFormat(formatMask);
            datStaDateTime = fmtDate.parse(strStartDate);
            datEndDateTime = fmtDate.parse(strEndDate);
            return datEndDateTime.compareTo(datStaDateTime);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static int getAge(Date birthDay) {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        int age = 0;
        birthDate.setTime(birthDay);
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }

        age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year   
        if ((birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3)
                || (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
            age--;

            // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        } else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH))
                && (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age;
    }
    
    public static String timestamp2String(Timestamp source, String strFormat){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
            return sdf.format(source);
        }catch(Exception ex){
            return "";
        }
    }
    
    public static String date2String(Date source, String strFormat){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
            return sdf.format(source);
        }catch(Exception ex){
            return "";
        }
    }
    
    public static Timestamp toTimestamp(String source, String fmt){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(fmt);
            Date d = sdf.parse(source);
            return new Timestamp(d.getTime());
        }catch(Exception ex){
            return null;
        }
    }
}
