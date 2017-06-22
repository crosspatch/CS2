//CS2 HW 1
//

import java.io.*;
import java.util.Scanner;
import java.lang.*;

public class Hw01{

    static Node[] hashTable;
    //main method
    public static void main(String[] args){


        complexityIndicator();

        File file = null;
        String filename;
        int numOfOperations;
        if(0 < args.length){
            numOfOperations = Integer.parseInt(args[0]);
            hashTable = new Node[numOfOperations];
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
                if(letter.equals("i") || letter.equals("d") || letter.equals("s")){
                
                    String name = input.next();
                    switch (letter){
                        case "i": hashInsert(name);
                                break;
                        case "d": hashDelete(name);
                                break;
                        case "s": hashSearch(name);
                                break;
                       
                    }
                    System.out.printf("%s %s%n", letter, name);    
                }
                else{
                    switch (letter){
                        case "p": hashPrint();
                                break;
                        case "q": return;
                        default: System.out.println("Try again");
                                break;
                    }
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
    public static int keyGenerator(String name, int n){
        int C = 27;
        int h = 0;
        
        for(int i = 0;i < name.length();i++){
            char curr = name.charAt(i);
            h =(h*C + ((int)curr)-96)%n;
        }
		System.out.println("The key is " + h);
		return h;
    }
    

    public static void complexityIndicator(){

        System.err.println("ch004919;5.0;20");
    }

    //Method for insertion
    public static void hashInsert(String Name){
        int key = keyGenerator(Name, hashTable.length);
        if(hashTable[key] != null){
            //there is a list
            
            // lead starts at the first node on the list; lag stays one step behind
            Node lead = hashTable[key];
            Node lag = null;
            
            while(lead != null){
                lag = lead;         // let lag catch up to lead
                lead = lead.link;   // move lead forward
            }
            // at this point, lead just went to null, and lag points to
            // the node we fell off OF (the last one in the list)
            Node newNode = new Node(Name);
            lag.link = newNode;
            
        }
        else{
            Node newNode = new Node(Name);
            hashTable[key] = newNode;
        }
    }

    //Method for Deletion
    public static void hashDelete(String Name){
        
        int key = keyGenerator(Name, hashTable.length);
        if(hashTable[key] == null){
            System.out.println("The word is not in the list");
            return;
        }
        //there is a list...check to see if that word is in the list
        // lead starts at the first node on the list; lag stays one step behind
        Node lead = hashTable[key];
        Node lag = null;
            
        while(lead != null){
            
            //check the data
            if(lead.data.equals(Name)){
                //it is in the list
                //we want to delete the node lead points at.
                if(lag == null){
                    //we are at the first word
                    hashTable[key] = lead.link;
                    return;
                }
                if(lead == null){
                    //lead is the last node
                    lag.link = null;
                    return;
                }
                //it was found in the middle of the list
                lag.link = lead.link;
                return;
            }
            lag = lead;         // let lag catch up to lead
            lead = lead.link;   // move lead forward
        }
        //didn't find word
        System.out.println("The word is not in the list");
       
    }
    
    //Method for Searching through the Table
    public static void hashSearch(String Name){
        
        int key = keyGenerator(Name, hashTable.length);
        //if key isn't being used
        if(hashTable[key] == null){
            System.out.println("The word is not in the list");
            return;
        }
        //there is a list...check to see if that word is in the list
        // lead starts at the first node on the list; lag stays one step behind
        Node lead = hashTable[key];
            
        while(lead != null){
            
            //check the data
            if(lead.data.equals(Name)){
                //it is in the list
                System.out.println(key + Name);
                return;
            }
            lead = lead.link;   // move lead forward
        }
        //didn't find word
        System.out.println("The word is not in the list");
        
        

    }

    //Method for Printing items
    public static void hashPrint(){
        System.out.println("The Hash Table contains: ");
        for(int i = 0;i < hashTable.length;i++){
            
            System.out.printf("%d. List (first->last): ", i);
            if(hashTable[i]==null){
                //there is no list
                System.out.print("\n");
                return;
            }
            
            
            Node lead = hashTable[i];
            while(lead != null){
                System.out.print(i + "/" + lead.data + ";");
                lead = lead.link;
            }
            System.out.print("\n");
            
        }


    }
}    
class Node{
        
    String data;
    Node link = null; 
        
    public Node(String Name){
        data = Name;
    }
}
    

