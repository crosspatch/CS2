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
            System.out.println(filename + " contains:");
        }
        else{
            System.out.println("Nothing there");
            System.exit(0);
        }

        try{
            Scanner input = new Scanner(file);
            while(input.hasNextLine()){
                String letter = input.next();
                if(input.hasNext()){
                    String name = input.next();
                    switch (letter){
                        case "i": hashInsert(name);
                                break;
                        case "d": hashDelete(name);
                                break;
                        case "s": hashSearch(name);
                                break;
                        case "p": hashPrint();
                                break;
                        case "q": hashQuit();
                        default: System.out.println("Try again");
                                break;
                    }
                    System.out.printf("%s %s%n", letter, name);
                }
                else{
                    System.out.printf("%s%n", letter);
                    return;
                }
            }
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
    public static void hashInsert(String Name){

    }

    //Method for Deletion
    public static void hashDelete(String Name){

    }
    
    //Method for Searching through the Table
    public static void hashSearch(String Name){

    }

    //Method for Printing items
    public static void hashPrint(){
        System.out.println("The Hash Table contains: ");


    }

    //Method for quitting from current choice
    public static void hashQuit(){

    }
}
