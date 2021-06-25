import java.util.*;

public class MapUses {
    public static void main(String args[]) {
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] times = {"morning", "afternoon", "evening", "night", "day"};

        java.util.Date utilDate = new java.util.Date();
        System.out.println("java.util.Date is : " + utilDate);
        java.sql.Date sqlDate = convert(utilDate);
        System.out.println("java.sql.Date is : " + sqlDate);

        Map<String,String> event = new HashMap<String, String>();
        event.put("name","Fulano");
        event.put("city","Mexico");
        event.put("time","morning");
        event.put("day","Friday");

        System.out.println("Existe name: " + event.containsKey("name"));
        System.out.println("Existe city: " + event.containsKey("name"));
        System.out.println("Existe time: " + event.containsKey("name"));
        System.out.println("Existe day: " + event.containsKey("day"));

        String name = ((event.get("name") == "") ? "you" : event.get("name"));
        String city = ((event.get("city") == "") ? "the earth": event.get("city"));
        String time = ((Arrays.asList(times).contains(event.get("time")) == false) ? "day" : event.get("time"));
        String day = ((Arrays.asList(days).contains(event.get("day")) == false) ? "week": event.get("day"));


        String greeting = "Good " + time + ", " + name + " of "+ city + ".";
        greeting += " \nHappy " + day +"!";
        System.out.println(greeting);

    }

    private static java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
//
//    Map<Integer, String> nombreMap = new HashMap<Integer, String>();
//nombreMap.size(); // Devuelve el numero de elementos del Map
//        nombreMap.isEmpty(); // Devuelve true si no hay elementos en el Map y false si si los hay
//        nombreMap.put(K clave, V valor); // Añade un elemento al Map
//        nombreMap.get(K clave); // Devuelve el valor de la clave que se le pasa como parámetro o 'null' si la clave no existe
//        nombreMap.clear(); // Borra todos los componentes del Map
//        nombreMap.remove(K clave); // Borra el par clave/valor de la clave que se le pasa como parámetro
//        nombreMap.containsKey(K clave); // Devuelve true si en el map hay una clave que coincide con K
//        nombreMap.containsValue(V valor); // Devuelve true si en el map hay un Valor que coincide con V
//        nombreMap.values(); // Devuelve una "Collection" con los valores del Map


//Return new response parseo JSON