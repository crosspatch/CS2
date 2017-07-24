//Actual List Class with methods
//this is the class which will house the main method and will have the constructor for a new list as well as the methods needed to implement a working list

import java.util.Random;

public class SkipList{


    //instance variables
    int listHeight = 0;      //needed to keep track of how high the list gets and also needed to know when to add new sentinels
    SkipNodes head = null;      //this will be a pointer to the head of the list at all times. This will change everytime a level is added
    SkipNodes tail = null;      //this is the end of the list just for fun and will stay the same
    int numInserts = 0;
    Random rng = new Random();

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
       
        
        //the list always begins with one level
        head = new SkipNodes(Integer.MIN_VALUE);    //the head pointer now points to the -inf sentinel
                                                    //                                                 null
                                                 //                                                     |
        tail = new SkipNodes(Integer.MAX_VALUE); //no pointers assigned...node looks like this null<--[+inf]-->null
                                                 //                                                     |
                                                 //                                                    null  
        //now for pointer manipulation      
        head.next = tail;
        tail.previous = head; 
        //now it looks like schematic before constructor
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
        //if value is in the list, we return
        //if not, we insert it and flip the coin to see if we need to promote it or not
        SkipNodes curr = search(value);

        if(curr.data == value){
            return;//it's a duplicate
        }

        //now we need to actually insert the value
        //curr is at value before point where we would need to insert
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
        //now we need to actually make the node to be inserted
        SkipNodes insert = new SkipNodes(value);
        numInserts++;
        insert.next = curr.next; //50 points to 60
        insert.previous = curr;  //50 points to 30
        //at this point 50 is pointing to 60 and 30 but 30 is still pointing to 60 and 60 to 30
        curr.next.previous = insert;     //60 now points to 50(insert)
        curr.next = insert;              //30 now points to 50(insert)
        
        //now we need to check to see if the insertion should be promoted
        //1 for Heads
        //0 for Tails
        //int p = promote(argumentPresent);
        //System.out.println("P is now " + p);
        while((promote(pres)) == 1){
            //need to take care of sentinels for new level
                       
            //create node to be inserted in the next level
            //right now curr is at 30 still
            //NOde is at 50
            //for promotions we need to move the current pointer to a node in the level above
            while(curr.up==null && curr.previous != null){
                curr = curr.previous;
            }
            if(curr.up == null && curr.previous == null){
                //it's at -inf on bottom level and there is no level above
                SkipNodes leftSentinel = new SkipNodes(Integer.MIN_VALUE);   //points to nothing right now
                SkipNodes rightSentinel = new SkipNodes(Integer.MAX_VALUE);
                //we have to set down and right for the left guy
                //we have to set down and left for the right guy
                //we also have to reset head and tail when we're finished so we can do this again
                leftSentinel.down = curr;
                rightSentinel.down = tail;
                curr.up = leftSentinel;
                tail.up = rightSentinel;
                leftSentinel.next = rightSentinel;
                rightSentinel.previous = leftSentinel;
                listHeight++;
                setHead(leftSentinel);
                setTail(rightSentinel);
            }
            curr = curr.up;       //curr is now on the level above 
            ///////////////////////////////////////////////////////////////////////
            //         null              null                        null        //
            //          |                 |                           |          //
            //<------[-inf]--><---[30(curr is HERE)]-------><------[+inf]-->null //
            //          |                 |                          |           //
            //null<--[-inf]------><-----[30]--><--[50]--><--[60]-->[+inf]-->null //
            //         |                 |         |         |       |           //
            //        null             null      null       null    null         //
            ///////////////////////////////////////////////////////////////////////
                                    
            SkipNodes riser = new SkipNodes(value);
            //pointer manipulation again woot!
            riser.next = curr.next;
            riser.previous = curr;
            riser.down = insert;
            curr.next.previous = riser;
            curr.next = riser;
            insert.up = riser;
        
        }
    }

    public int promote(boolean present){
        //this funtion will be called from the search function
        //it will need to know if the command line has a value in the 2nd slot
        //if it does, seed with time,if it does not have an input, seed with 42
        //Thus, the result of if that argument is there will have to have a flag passed into the search function and read by the promote function

        if(present == true){
            //there is an argument, so seed with time
            rng.setSeed(System.currentTimeMillis());
        }
        else{
            long seed = 42;
            rng.setSeed(seed);
        }
        int res = ((int)(rng.nextDouble()*2));
        return res;
       //this result will tell the program if to promote or not
       //we will promote if the value returned is equal to 1(Heads)
       //if not, we will not promote and this is in effect(Tails) 
    }

    public void delete(int val){
        SkipNodes curr = search(val);
        //so the search either found the value or didn't
        if(curr.data != val){
            return;
            //value was not found and there is nothing to delete
        }
        if(curr.up == null){
            curr.previous.next = curr.next;
            curr.next.previous = curr.previous;
        }
        else{
            while(curr.up != null){
                curr.previous.next = curr.next;
                curr.next.previous = curr.previous;
                curr = curr.up;
                curr.down.up = null;
                curr.down = null;
            }
        }
    }

    //Workhorse of SKipLists
    //Is called whenever an insert, delete or search needs to be done
    public SkipNodes search(int goal){
        //A pointer to the current Node we're at in the list
        //set to the head initially
        SkipNodes curr = head;

        //keep searching until it's found or we reach the end of the list
        while(true){
            //Case 1: Key is found
            if(curr.data == goal){
                //return the node
                return curr;
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
                        //return node before where the node would be
                        return curr;
                    }
                    curr = curr.down;
                }
                else
                    curr = curr.next;
            }
        }
    }

    public void printAll(){
        
        if(head.next == tail){
            System.out.println("The list is empty");
            return;
        }

        SkipNodes curr;
        //point to head...top of list
        SkipNodes currp = head;
        //pointing at -inf at the top
        //must go until at the bottom -inf
        while(currp.down !=null){
            currp = currp.down;
        }
        //when it exits this loop currp.down == null...at bottom
        //now we need to go until currp.next == null...which is the +inf at the bottom
        while(currp.next != null && currp.data != Integer.MAX_VALUE){
            curr = currp = currp.next;
            do{
                System.out.println(curr.data + ";");
                curr = curr.up;
            }while(currp.up != null);
            System.out.print("\n");
        }
        System.out.println("---End of Skip List---");



    }

}
