//First Lab
import java.util.*;
import java.io.*;

public class Lab01{
    //first search method
    //private static Scanner x, y;

    public static void findByBruteForce(String fileOne, String fileTwo){
 
        Scanner x;
        Scanner y;
        
        try{
            x = new Scanner(new File(fileOne));
            y = new Scanner(new File(fileTwo));
        }
        catch(Exception e){
            System.out.println("Not there due");
        }

        while(x.hasNext()){
            String current = x.next();
            while(y.hasNext()){
                String currentTwo = y.next();
                if(current == currentTwo){
                    System.out.println(current);
                }
            }
        }
        //close files
        x.close();
        y.close();
    }

    /*public void findByBinarySearch(String fileOne,String fileTwo){
   

        try{
            x = new Scanner(new File(fileOne));
            y = new Scanner(new File(fileTwo));
        }
        catch(Exception e){
            System.out.println("Could not read file");
        }

        int count = 0;
        while(x.hasNext()){
            count++;
        }
        System.out.println("The count for file one is" + count);

        int count_Two = 0;
        while(y.hasNext()){
            count_Two++;
        }
        System.out.println("The count for file two is "+count_Two);

        x.close();
        y.close();
    }*/
   /* public void findByFinese(String fileOne, String fileTwo){
    }*/

    //main method
    public static void main(String[] args){
        
        String file = "list1.txt";
        String file_Two = "list2.txt";

        //first search method call
        findByBruteForce(file, file_Two);
        
        //secondsearch method call
       // findByBinarySearch(file, file_Two);

        //third search method call
       // findByFinesse(file, file_Two);

    }
    
}

