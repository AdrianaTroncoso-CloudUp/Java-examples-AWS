package smart.notifications.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Fechas {

    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatDate.format(date);
    }

    public static String getDateWithMoreHours() {

        Date fechaActual = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.MONTH, 4);
        Date fechaFinal = calendar.getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatDate.format(fechaFinal);
    }

    public static Long getDateWithMinusHours(Date date, int hours) {

        int hoursMinus = -1 * hours;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hoursMinus);
        Date minusHourBack = calendar.getTime();

        return minusHourBack.getTime();
    }

    public static String getNameDay(Date date) {

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfWeek() + "";
    }

    public static String getNameMonth(Date date) {

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonth() + "";
    }

    public static int getYear(Date date) {

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getYear();
    }

    public static int getDayOfMonth(Date date) {

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth();
    }

    public static int getNumWeek(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static Date parseStringToDate(String date){
        SimpleDateFormat simpleDateFormat = null;
        Date d = null;
        try {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            d = simpleDateFormat.parse(date);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return d;
    }

    public static String convertUnixDate(long unixTime) {

        Date date = new java.util.Date(unixTime * 1000L);

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String formattedDate = sdf.format(date);

        return formattedDate;
    }

    public static boolean validateTokenValidity(String dateString) throws ParseException{

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = formatter.parse(dateString);

        long timestamp = date.getTime()/1000L;

        long unixTimeCurrent =  (System.currentTimeMillis() / 1000L);

        if(unixTimeCurrent > timestamp){
            return true;
        }else{
            return false;
        }

    }

    public static String dateFormater(String dateFrom, String expectedFormat, String oldFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
        Date date = null;
        String convertedDate = null;
        try {
            date = dateFormat.parse(dateFrom);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(expectedFormat);
            convertedDate = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertedDate;
    }
}
