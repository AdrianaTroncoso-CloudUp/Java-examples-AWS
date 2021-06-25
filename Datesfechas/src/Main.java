import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.Arrays;

public class Main {

    public static void main(String args[])
    {
//        java.util.Date hoy = new java.util.Date();
//        System.out.println("java.util.Date is : " + hoy);
//        java.sql.Date sqlDate = convert(hoy);
//        System.out.println("java.sql.Date is : " + sqlDate);
//        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
//        System.out.println("dateFormated date is : " + df.format(hoy));
//
//
//        String persona_fecha_nacimiento = "1990-01-31";
//        Date cumple= null;
//        try {
//            cumple = new SimpleDateFormat("YYYY-MM-dd").parse(persona_fecha_nacimiento);
//            System.out.println(persona_fecha_nacimiento+"\t"+cumple);
//            System.out.println("cumple : " + cumple);
//            java.sql.Date sqlDateCumple = convert(cumple);
//            System.out.println("java.sql.Date cumple is : " + sqlDateCumple);
//            DateFormat dfCumple = new SimpleDateFormat("YYYY-MM-dd");
//            System.out.println("dateFormated cumple date is : " + df.format(cumple));
//            if (sqlDateCumple.compareTo(sqlDate) > 0) {
//                System.out.println("La fecha de cumpleaños tiene un formato incorrecto.");
//            }else {
//                System.out.println("Bien :D");
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        java.util.Date hoy = new java.util.Date();
        java.sql.Date sqlDate = convert(hoy);
        String persona_fecha_nacimiento = "1990-01-31";
        Date cumple= null;
        try {
            cumple = new SimpleDateFormat("YYYY-MM-dd").parse(persona_fecha_nacimiento);
            java.sql.Date sqlDateCumple = convert(cumple);
            System.out.println("java.sql.Date cumple is : " + sqlDateCumple);
            if (sqlDateCumple.compareTo(sqlDate) > 0) {
                System.out.println("La fecha de cumpleaños tiene un formato incorrecto.");
            }else {
//                context.getLogger().log(
                String fechasBien = "hoy:" + sqlDate + " ; fecha de nacimiento:" + sqlDateCumple +" .";
//                context.getLogger().log(fechasBien);
                System.out.println("Bien :D ´\n "+fechasBien);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private static java.sql.Date convert(java.util.Date fecha) {
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        return fechaSQL;
    }

}