package com.mx;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;   // Import the FileWriter class


public class ValidarNumeros {


    public static void main(String[] args) {
        String numerosFile = "C:/Users/AdrianaAmeliaTroncos/Documents/Java examples/ValidateNumber/Archivosdetexto/numeros.txt";
        String raizArchivoFinal = "C:/Users/AdrianaAmeliaTroncos/Documents/Java examples/ValidateNumber/Archivosdetexto/resultado_";
        try {
            int n = 0;
            n=n+1;
            File myObj = new File(numerosFile);
            Scanner myReader = new Scanner(myObj);
            FileWriter myWriter = new FileWriter(raizArchivoFinal+n+".txt");
            int u=0;

            String peticion ="aws pinpoint phone-number-validate --number-validate-request='{\"IsoCountryCode\":\"MX\", \"PhoneNumber\": \"" ;
            String numeroData;
            while (myReader.hasNextLine()) {
                String numeroCelular = myReader.nextLine();
                System.out.println(numeroCelular);
                u=u+1;
                numeroData= peticion+numeroCelular+"\"}'";
                String postNum="{ \"PhoneNumber\": \""+numeroCelular+"\", \"IsoCountryCode\": \"MX\"}";
                myWriter.write(u+"° Número '"+numeroCelular+"': \n"+numeroData+"\n "+postNum+"\n\n");
            }
            myReader.close();
            myWriter.write("\nFin :)");
            myWriter.close();
            System.out.println("Successfully wrote to the file!!!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}

