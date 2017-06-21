//CS2 HW 1
//Christine Lynch
//ch004919

import java.io.*;
import java.util.Scanner;
import java.lang.*;

public class Hw01{

    //main method
    public static void main(String[] args){


        File file = null;
        if(0 < args.length){
            file = new File(args[0]);
        }
        else{
            System.out.println("Nothing there");
            System.exit(0);
        }

        try{
            
            Scanner input = new Scanner(file);
            while(input.hasNext()){
                String letter = input.next();
                String name = input.next();
                System.out.printf("The current letter is %c and the current name is %s",letter,name);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("FIle not found");
        }


    }


    //methods needed

    //Method for insertion
   /* public void hashInsert(){

    }

    //Method for Deletion
    public void hashDelete(){

    }
    
    //Method for Searching through the Table
    public void hashSearch(){

    }

    //Method for Printing items
    public void hashPrint(){

    }

    //Method for quitting from current choice
    public void hashQuit(){

    }*/
}
