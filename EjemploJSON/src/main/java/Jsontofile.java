package com.mx;

import java.io.FileWriter;
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


public class Jsontofile {
    private static FileWriter file;

    public static void main(String[] args) {
        System.out.println("Inicio");
        int num = 0;

        SortedMap<String, String> elements = new TreeMap();
        elements.put("Key1", "Value1");
        elements.put("Key2", "Value2");
        elements.put("Key3", "Value3");

        Gson gson = new Gson();
        Type gsonType = new TypeToken<HashMap>(){}.getType();
        String gsonString = gson.toJson(elements,gsonType);
        System.out.println(gsonString);

        try {
            num =num+1;
            String numero = String.valueOf(num);
            // Constructs a FileWriter given a file name, using the platform's default charset
            String nombretxt = "/Users/AdrianaAmeliaTroncos/Documents/Java examples/ValtidaeNumber/Archivosdetexto/Texto_"+numero+".txt";
            file = new FileWriter(nombretxt);
            file.write(gsonString);//.toJSONString());
            CrunchifyLog("Successfully Copied JSON Object to File...");
            CrunchifyLog("\nJSON Object: " + gsonString);//obj);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    static public void CrunchifyLog(String str) {
        System.out.println("str");
    }
}



