//Actual List Class with methods
//this is the class which will house the main method and will have the constructor for a new list as well as the methods needed to implement a working list



public class SkipList{


    //main method
    //will house function calls and main the program based on user input
    public static void main(String[] args){

    }


    //instance variables
    private int listHeight;      //needed to keep track of how high the list gets and also needed to know when to add new sentinels
    private SkipNodes head;      //this will be a pointer to the head of the list at all times. This will change everytime a level is added
    private SkipNodes tail;      //this is the end of the list just for fun and will stay the same

    //constructor
    //when called will create a new list which will only have the sentinels and the list will look like the image below
    ////////////////////////////////////////////////////////////////////////
    //             null                 null            //
    //              |                    |              //
    //null<--[-inf sentinel]--><--[+inf sentinel]-->null//
    //             |                    |               //
    //            null                 null             //
    //////////////////////////////////////////////////////
    public SkipList(){
        listHeight = 1;                                             //the list always begins with one level
        head = new SkipNodes(-100000,1,lastNode,null,null,null);    //the head pointer now points to the -inf sentinel
        tail = new SkipNodes(100000,1,null, firstNode, null, null); //the tail pointer now points to the +inf sentinel
    }

    //setter for level
    public void setListHeight(int l){
        this.listHeight = l;
    }

    //getter for level
    public int getListHeight(){
        return listHeight;
    }

    //Head will change with levels so need getters and setters for that as well
    public void setHead(SkipNodes newFirst){
        this.head = newFirst;
    }

    //will return the current head of the list
    public SkipNodes getHead(){
        return head;
    }

    public void insert(int value,SkipNodes c){
        
        //make node to insert
        SkipNodes Node = new SkipNodes(value,1,null, null, null, null);
        //call search to search for the right spot
        search(value, 'i',Node);
    }

    public int promote(){

        return(); 
    }

    public void delete(){
    }

    //Workhorse of SKipLists
    //Is called whenever an insert, delete or search needs to be done
    public boolean search(int goal,char option ){
        //variable to let us know the value has or has not been found
        boolean found=false;

        //A pointer to the current Node we're at in the list
        //set to the head initially
        SkipNodes curr = head;

        //keep searching until it's found or we reach the end of the list
        while(found != true){
            //Cases:
            ////Either we have found the key or we have not at each node
            ///We are guaranteed no repeats in this assignment so we would only find a key if it is to be deleted or it's just a regular search
            ///If we don't find it and we are at the bottom of the list where the down pointer points to null, then we are either inserting
           /// or the item is not in the list and we have to return not found

            //Case 1: Key is found
            if(curr.data == goal){
                //set boolean to true
                found = true;
                //switch statement for two possibilities when a key is found
                switch (option){
                    //regular search in which case we just return the key was found with the boolean value
                    case 's':            
                        return found;
                    //what was found is to be deleted
                    case 'd':
                        //need to write this
                        break;
                }
            }
            //Okay so it wasn't that node...gotta look to see if going to right would be going too far
            //go down in that case
            else{
                //Okay so the key was not found, now we have to decide if we will go to the right or go down
                //in this data structure, we keep going right until the next node is greater than what we are looking for
                //for this case we go down if an only if the down node is not null
                //if the down node is null, we are at the bottom of the list and we must either insert or return not found
                if(curr.next.data > goal){
                    if(curr.down == null){
                        //on bottom level and number not found
                        switch(option){
                            //key needs to be inserted...at this point we would be at the node right before we would be going to far
                            //for example if we want to insert 50 and the bottom list consists of 30 and 60, we would be at 30
                            //////////////////////////////////////////////////////////////////////
                            //         null              null                        null       //
                            //          |                 |                           |         //
                            //<------[-inf]-----><------[30]-----------><----------[+inf]-->null//
                            //          |                |                           |          //
                            //null<--[-inf]--><--[30(WE ARE HERE)]--><--[60]--><--[+inf]-->null //
                            //         |                |                |          |           //
                            //        null             null            null        null         //
                            //////////////////////////////////////////////////////////////////////
                            case 'i':
                                int i = 1;
                                SkipNodes Node = new SkipNodes(goal,i, curr.right,curr,null, null);
                                int p = promote();
                                while(p == 1){

                                    i++;
                                    SkipNodes Node = new SkipNodes(goal, i, curr.up.right, curr.up,null,curr.right);
                                    curr.right.up = Node;
                                    curr.up.right.left = Node;
                                    curr.up.right = Node;
                                    //may need to use recursion?
                                    p = promote();
                                }
                                return;
                                break;
                            case 's':
                                return;   //found would be false
                        }
                    }
                    curr = curr.down;
                }
                else
                    curr = curr.next;
            }
        }
        //I don't understand why I need a return here
        return found;
    }

    public void printAll(){
    }

}

