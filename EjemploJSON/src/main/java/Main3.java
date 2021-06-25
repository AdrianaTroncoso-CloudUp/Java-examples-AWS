import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.HashMap;
import java.io.*;
import org.json.simple.JSONObject;

public class Main3 {
    public static void main(String[] args) throws IOException {


        JSONObject obj1=new JSONObject();
        obj1.put("name","sonoo");
        obj1.put("age",new Integer(27));
        obj1.put("salary",new Double(600000));
        System.out.print(obj1);




        SortedMap<String, String> elements = new TreeMap();
        elements.put("Key1", "Value1");
        elements.put("Key2", "Value2");
        elements.put("Key3", "Value3");

        Gson gson = new Gson();
        Type gsonType = new TypeToken<HashMap>(){}.getType();
        String gsonString = gson.toJson(elements,gsonType);
        System.out.println(gsonString);


//        List objList = new ArrayList();
//        objList.add("obj1");
//        objList.add("obj2");
//        objList.add("obj3");
//        HashMap objMap = new HashMap();
//        objMap.put("key1", "value1");
//        objMap.put("key2", "value2");
//        objMap.put("key3", "value3");
//        System.out.println("JSONArray :: " + (JSONArray) JSONSerializer.toJSON(objList));
//        System.out.println("JSONObject :: " + (JSONObject) JSONSerializer.toJSON(objMap));


    }

}
