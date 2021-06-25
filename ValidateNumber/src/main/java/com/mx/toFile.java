package com.mx;

import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors

public class toFile {

    public static void main(String[] args) {
        try {
            int n = 0;
            n=n+1;
            FileWriter myWriter = new FileWriter("C:/Users/AdrianaAmeliaTroncos/Documents/Java examples/ValidateNumber/Archivosdetexto/filename"+n+".txt");
            String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
            int u=0;
            for (String i : cars) {
                u=u+1;
                System.out.println(i+"\n");
                myWriter.write("Carro "+u+": "+i+"\n");
            }
            myWriter.write("\nFiles in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
