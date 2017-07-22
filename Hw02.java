//hw class    
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
            SkipList mySkip = new SkipList();
            while(input.hasNextLine()){
                String letter = input.next();
                if(letter.equals("i") || letter.equals("d") || letter.equals("s")){

                    int num = input.nextInt();
                    switch(letter){
                        boolean there = false;
                        if(args[1] != null){
                            there = true;
                            System.out.println("With the RNG seeded,");
                        }
                        else
                            System.out.println("With the RNG unseeded,");
                        //options
                        case "i": mySkip.insert(num,there);
                                  break;
                        case "d": mySkip.delete(num);
                                  break;
                        case "s": mySkip.search(num,'s',there);
                                  break;
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
}
        