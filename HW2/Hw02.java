//hw class    

import java.io.*;
import java.util.Scanner;

public class Hw02{
//main method
    //will house function calls and main the program based on user input
    public static void main(String[] args){

        complexityIndicator();
        //need to read from file now
        File file = null;
        String filename;
        if(0 < args.length){
            filename = args[0];
            file = new File(filename);
            System.out.println("For the input file named " + filename);
        }
        else{
            System.out.println("Nothing there dude!");
            System.exit(0);
        }

        try{
            Scanner input = new Scanner(file);
            //make new list
            SkipList mySkip = null;// = new SkipList();
            while(input.hasNextLine()){
                String letter = input.next();
                //System.out.println(letter);
                if(letter.equals("i") || letter.equals("d") || letter.equals("s")){
                    boolean there = false;
                    if(args.length > 1){
                        there = true;
                        mySkip = new SkipList(42);
                        System.out.println("With the RNG seeded,");
                    }
                    else{
                        mySkip = new SkipList(System.currentTimeMillis());
                        System.out.println("With the RNG unseeded,");
                        
                    }
                    while(input.hasNextInt()){

                        int num = input.nextInt();
                    System.out.println("The number is " + num);
                    switch(letter){
                        //options
                        case "i": mySkip.insert(num,there);
                                  break;
                        case "d": mySkip.delete(num);
                                  break;
                        case "s": SkipNodes result = mySkip.search(num);
                                  if(result.data == num){
                                      System.out.println(num + " found");
                                  }
                                  else
                                      System.out.println(num + " NOT FOUND");
                                  break;
                    }
                    }
                }
                else{
                    System.out.println("the current Skip List is shown below:");
                    switch(letter){
                        case "p":mySkip.printAll();
                                 break;
                        default: System.out.println("Try again");
                    }
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    public static void complexityIndicator(){
        System.err.println("ch004919;5.0;35");
    }

}
