//Actual List Class with methods
//this is the class which will house the main method and will have the constructor for a new list as well as the methods needed to implement a working list

import java.util.Random;

public class SkipList{


    //instance variables
    int listHeight;      //needed to keep track of how high the list gets and also needed to know when to add new sentinels
    SkipNodes head;      //this will be a pointer to the head of the list at all times. This will change everytime a level is added
    SkipNodes tail;      //this is the end of the list just for fun and will stay the same
    int numInserts = 0;

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
        head = new SkipNodes(Integer.MIN_VALUE,1,null,null,null,null);    //the head pointer now points to the -inf sentinel
        tail = new SkipNodes(100001,1,null, head, null, null); //the tail pointer now points to the +inf sentinel
        head.next = tail;
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

    //needs to be done when new sentinels are formed
    public void setTail(SkipNodes newLast){
        this.tail = newLast;
    }

    public void insert(int value,boolean pres){
        
        //call search to search for the right spot
        search(value, 'i',pres);
    }

    public int promote(boolean present){
        //this funtion will be called from the search function
        //it will need to know if the command line has a value in the 2nd slot
        //if it does, seed with time,if it does not have an input, seed with 42
        //Thus, the result of if that argument is there will have to have a flag passed into the search function and read by the promote function

        Random rng;
        if(present == true){
            //there is an argument, so seed with time
            rng = new Random(System.currentTimeMillis());
        }
        else{
            long seed = 42;
            rng = new Random(seed);
        }
        return ((int)(rng.nextDouble()*2));
       //this result will tell the program if to promote or not
       //we will promote if the value returned is equal to 1(Heads)
       //if not, we will not promote and this is in effect(Tails) 
    }

    public void delete(int val){
        search(val, 'd',false);
    }

    //Workhorse of SKipLists
    //Is called whenever an insert, delete or search needs to be done
    public boolean search(int goal,char option,boolean argumentPresent){
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
                        if(curr.up == null){
                            curr.previous.next = curr.next;
                            curr.next.previous = curr.previous;
                        }
                        else{
                            while(curr.up!= null){
                                curr.previous.next = curr.next;
                                curr.next.previous = curr.previous;
                                curr = curr.up;
                                curr.down.up = null;
                                curr.down = null;
                            }
                        }
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
                                //Create node to be inserted and manipulate the pointers
                                SkipNodes Node = new SkipNodes(goal,i, curr.next,curr,null, null);
                                numInserts++;
                                //at this point 50 is pointing to 60 and 30 but 30 is still pointing to 60 and 60 to 30
                                curr.next.previous = Node;
                                //60 now points to 50(Node)
                                curr.next = Node;
                                //30 now points to 50(Node)
                                //now we need to check to see if the insertion should be promoted
                                //1 for Heads
                                //0 for Tails
                                int p = promote(argumentPresent);
                                System.out.println("P is now " + p);
                                while(p == 1){
                                    //need to take care of sentinels for new level
                       
                                    //update i so it can fill in the right level to the Node
                                    i++;
                                    //create node to be inserted in the next level
                                    //right now curr is at 30 still
                                    //NOde is at 50
                                    //for promotions we need to move the current pointer to a node in the level above
                                    while(curr.up==null && curr.previous != null){
                                        curr = curr.previous;
                                    }
                                    if(curr.up == null && curr.previous == null){
                                        //it's at -inf on bottom level and there is no level above
                                        SkipNodes leftSentinel = new SkipNodes(Integer.MIN_VALUE,i,null,null,null,curr);
                                        SkipNodes rightSentinel = new SkipNodes(Integer.MAX_VALUE,i,null,null,null,tail);
                                        curr.up = leftSentinel;
                                        tail.up = rightSentinel;
                                        leftSentinel.next = rightSentinel;
                                        rightSentinel.previous = leftSentinel;
                                        listHeight= i;
                                        setHead(leftSentinel);
                                        setTail(rightSentinel);
                                    }
                                    curr = curr.up;       //curr is now on the level above 
                             //////////////////////////////////////////////////////////////////////
                            //         null              null                        null        //
                            //          |                 |                           |          //
                            //<------[-inf]--><---[30(curr is HERE)]-------><------[+inf]-->null //
                            //          |                 |                          |           //
                            //null<--[-inf]------><-----[30]--><--[50]--><--[60]-->[+inf]-->null //
                            //         |                 |         |         |       |           //
                            //        null             null      null       null    null         //
                            //////////////////////////////////////////////////////////////////////

                                    
                                    SkipNodes riser = new SkipNodes(goal, i, curr.next,curr,null,Node);
                                    curr.next.previous = riser;
                                    curr.next = riser;
                                    Node.up = riser;
                                    p = promote(argumentPresent);
                                }
                                return found;
                                
                            case 's':
                                return found;   //found would be false
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
        
        //point to head...top of list
        SkipNodes currp = head;
        //pointing at -inf at the top
        //must go until at the bottom -inf
        while(currp.down !=null){
            currp = currp.down;
        }
        //when it exits this loop currp.down == null...at bottom
        //now we need to go until currp.next == null...which is the +inf at the bottom
        while(currp.next != null){
            if(currp.next.data == 100001 && currp.data == Integer.MIN_VALUE){
                //list is empty
                System.out.print("The list is empty");
                return;
            }
            currp = currp.next;
            System.out.println(currp.data + ";");
            //two cases...either there is something above this node or there isn't
            while(currp.up != null){
                //there is a node above
                SkipNodes upCurr = currp.up;
                System.out.print(upCurr.data + ";");
            }
            System.out.print("\n");
        }
        System.out.println("---End of Skip List---");



    }

}

