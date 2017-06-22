//CS2 HW 1

import java.io.*;
import java.util.Scanner;
import java.lang.*;

public class Hw01{

    //main method
    public static void main(String[] args){


        complexityIndicator();

        File file = null;
        String filename;
        int numOfOperations;
        if(0 < args.length){
            numOfOperations = Integer.parseInt(args[0]);
            filename = args[1];
            file = new File(filename);
            System.out.println("The integer given is " +numOfOperations);
            System.out.println("The name of the file given is " + filename);
        }
        else{
            System.out.println("Nothing there");
            System.exit(0);
        }

        try{
            int counter = 0;
            Scanner input = new Scanner(file);
            while(input.hasNext()){
                String letter = input.next();
                String name = input.next();
                System.out.printf("The current letter is %s and the current name is %s%n",letter,name);
                counter++;
            }
            System.out.println(counter);
        }
        catch(FileNotFoundException e){
            System.out.println("FIle not found");
        }


    }


    //methods needed
    

    public static void complexityIndicator(){

        System.err.println("ch004919;5.0;20");
    }

    //Method for insertion
   /* public static void hashInsert(){

    }

    //Method for Deletion
    public static void hashDelete(){

    }
    
    //Method for Searching through the Table
    public static void hashSearch(){

    }

    //Method for Printing items
    public static void hashPrint(){

    }

    //Method for quitting from current choice
    public static void hashQuit(){

    }*/
}
