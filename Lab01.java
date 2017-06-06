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

            int low = 0;
            int high = listOne.size() - 1;
            int middle = (low + high + 1)/2;
            int location = -1;

            for(int i=0; i< listOne.size(); i++){
                do{
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
                System.out.println(listOne.get(location));
            }
            x.close();
            y.close();
        }
        catch(Exception e){
            System.out.println("Could not read file");
        }

    
    }
   /* public void findByFinese(String fileOne, String fileTwo){
    }*/

    //main method
    public static void main(String[] args){
        
        String file = "list1.txt";
        String file_Two = "list2.txt";

        //first search method call
        findByBruteForce(file, file_Two);
        
        //secondsearch method call
        findByBinarySearch(file, file_Two);

        //third search method call
       // findByFinesse(file, file_Two);

    }
    
}

