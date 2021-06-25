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

public class EjerciciosPeques {

    public static void main(String args[]) {


//        String persona_fecha_nacimiento = "1990-01-31";
//        Date cumple = convertir(persona_fecha_nacimiento);
//        String hoy = String.valueOf(LocalDate.now());
//        Date fechaHoy = convertir(hoy);
////        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//
////        try {
////            cumple = (Date) formatter.parse(String.valueOf(persona_fecha_nacimiento));
////            today = (Date) formatter.parse(String.valueOf(hoy));
//////            cumple1 = formatter.format(cumple);
////        } catch (ParseException parseException) {
////            parseException.printStackTrace();
////        }
//        java.sql.Date sqlFechaNacimiento = new java.sql.Date(cumple.getTime());
////        System.out.println("hoy:" + formatter.format(hoy) + " ; fecha de nacimiento:" + formatter.format(cumple) +"sqlDate:" + sqlFechaNacimiento+ " .");
//        if (cumple.compareTo(fechaHoy) > 0) {
//            System.out.println("La fecha de cumpleaños tiene un formato incorrecto.");
//        }


        java.util.Date utilDate = new java.util.Date();
        System.out.println("java.util.Date is : " + utilDate);
        java.sql.Date sqlDate = convert(utilDate);
        System.out.println("java.sql.Date is : " + sqlDate);
        DateFormat df = new SimpleDateFormat("dd/MM/YYYY - hh:mm:ss");
        System.out.println("dateFormated date is : " + df.format(utilDate));
    }



    private static java.sql.Date convertir(String fecha) {
        Date fechaAdate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fechaAdate = (Date) formatter.parse(String.valueOf(fecha));
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        return (java.sql.Date) fechaAdate;
    }

    private static java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}




// Initialization of a Map
// using Generics
//        Map<String, String> hm1 = new HashMap<String, String>();
//
//        // Inserting the Elements
//        hm1.put("Uno", "Geeks");
//        hm1.put("Dos", "For");
//        hm1.put("Tres", "Geeks");
//
//        for (Map.Entry mapElement : hm1.entrySet()) {
//            String key = (String)mapElement.getKey();
//
//            // Finding the value
//            String value = (String)mapElement.getValue();
//
//            System.out.println(key + " : " + value);
//
//        }
//        System.out.println("Hm1: "+ hm1.get("Uno"));