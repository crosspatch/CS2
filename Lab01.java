//First Lab 
import java.util.*;
import java.io.*; 
public class Lab01{ 
    //first search method 

    public static void findByBruteForce(String fileOne, String fileTwo){
 

        System.out.println("The method findByBruteFore found the following match(es):");

        Scanner x;
        Scanner y;
        ArrayList<String> listOne = new ArrayList<String>();
        ArrayList<String> listTwo = new ArrayList<String>();
        
        try{
            x = new Scanner(new File(fileOne));
            y = new Scanner(new File(fileTwo));
            
            listOne.add(x.next());
            while(x.hasNext()){
                listOne.add(x.next());
            }

            listTwo.add(y.next());
            while(y.hasNext()){
                listTwo.add(y.next());
            }

            /*for(int t = 0; t<listTwo.size(); t++){
                System.out.println(listTwo.get(t));
            }*/


            for(int i = 0; i<listOne.size();i++){
                for(int j = 0; j<listTwo.size(); j++){
                    String curr_one = listOne.get(i);
                    String curr_two = listTwo.get(j);
                    if(curr_one.compareToIgnoreCase(curr_two) == 0){
                        System.out.println(curr_one);
                    }
                }
            }

            //close files
            x.close();
            y.close();

        }
        catch(Exception e){
            System.out.println("Not there dude");
        }


    }

    public static void findByBinarySearch(String file_One,String file_Two){
   
        System.out.println("The method findByBinarySearch found the following match(es):");

        Scanner x;
        Scanner y;
        ArrayList<String> listOne = new ArrayList<String>();
        ArrayList<String> listTwo = new ArrayList<String>();

        try{
            x = new Scanner(new File(file_One));
            y = new Scanner(new File(file_Two));

            listOne.add(x.next());
            while(x.hasNext()){
                listOne.add(x.next());
            }

            listTwo.add(y.next());
            while(y.hasNext()){
                listTwo.add(y.next());
            }

            for(int i=0; i< listOne.size(); i++){
				        int low = 0;
				        int high = listOne.size() - 1;
				        int middle = (low + high + 1)/2;
				        int location = -1;

                do{

								// Debugging print statements.
								/*System.out.println("Searching for: " + listOne.get(i));
								for(int j = 0; j < listTwo.size(); j++) {
									System.out.print(listTwo.get(j) + " ");
									if(j == low) System.out.print("(low) ");
									if(j == middle) System.out.print("(mid) ");
									if(j == high) System.out.print("(hi) ");
								}
								System.out.println();*/


                String curr_key = listOne.get(i);
                String curr_middle = listTwo.get(middle);
                if(curr_key.compareToIgnoreCase(curr_middle) == 0)
                    location = middle;
                else if(curr_key.compareToIgnoreCase(curr_middle) < 0)
                    high = middle - 1;
                else
                    low = middle + 1;
                middle =(low + high + 1)/2;
                }
                while((low <= high) && (location == -1));
								if(location != -1) System.out.println(listTwo.get(location));
            }
            x.close();
            y.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Could not read file");
        }

    
    }
    public static void findByFinesse(String fileOne, String fileTwo){

        System.out.println("The method findByFinesse found the following match(es):");

        String Marker_One;
        String Marker_Two;
        ArrayList<String> list_One = new ArrayList<String>();
        ArrayList<String> list_Two = new ArrayList<String>();

        Scanner x;
        Scanner y;

        try{
            x = new Scanner(new File(fileOne));
            y = new Scanner(new File(fileTwo));

            list_One.add(x.next());
            while(x.hasNext()){
                list_One.add(x.next());
            }

            list_Two.add(y.next());
            while(y.hasNext()){
                list_Two.add(y.next());
            }

            //System.out.println("The size of List One is " + list_One.size());
            //System.out.println("The size of List Two is " + list_Two.size());
            
            int size;

            if(list_One.size() > list_Two.size()){
                size = list_One.size();
            }
            else
                size = list_Two.size();

            int i = 0;
            int j = 0;

            //set initial markers
            Marker_One = list_One.get(i);
            //System.out.println("Marker_One is currently: "+ Marker_One);
            Marker_Two = list_Two.get(j);
            //System.out.println("Marker_Two is currently: "+Marker_Two);

            while(i <= size || j <= size){
                //System.out.println("At the beginning of the loop, Marker One is: " + Marker_One + " and Marker Two is: " + Marker_Two);
                if(Marker_One.compareToIgnoreCase(Marker_Two) == 0){
                    System.out.println(Marker_One);
                    i++;
                    j++;
                    if(i == size || j == size)
                        return;
                    Marker_One = list_One.get(i);
                    //System.out.println("There was just a match, Marker_One is currently: " + Marker_One +" and i is now " + i);
                    Marker_Two = list_Two.get(j);
                    //System.out.println("There was just a match and Marker_Two is currently: " + Marker_Two +" and j is now " + j);
                }
                else if(Marker_One.compareToIgnoreCase(Marker_Two) < 0){
                    i++;
                    if(i == size)
                        return;
                    Marker_One = list_One.get(i);
                    //System.out.println("Marker_One came before Marker_Two and there was no match so Marker_One is now: "+ Marker_One +"and i is now " + i);
                }
                else{
                    j++;
                    if(j == size)
                        return;
                    Marker_Two = list_Two.get(j);
                    //System.out.println("Marker_Two came before Marker_One and there was no match so Marker_Two is now: "+ Marker_Two +"and j is now " + j);
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    //main method
    public static void main(String[] args){
        
        String file = "list1.txt";
        String file_Two = "list2.txt";

        //first search method call
        findByBruteForce(file, file_Two);
        
        //secondsearch method call
        findByBinarySearch(file, file_Two);

        //third search method call
        findByFinesse(file, file_Two);

    }
    
}

