//Nodes for Skip List
//This will be the class for the individual Nodes for the list
//IN the train analogy, this will be the individual stops on the expressway and the normal line
//They will each have an integer in them as their data and four pointers
///////////////////////////////////////////////////////////////////////////////////////////
//             null             null        null        null             null            //
//              |                |           |           |                |              // 
//              |                |           |           |                |              //
//null<-- [Sentinel Node]--><--[Node]--><--[Node]--><--[Node]--><--[Sentinel Node]-->null//
//              |                |           |           |                |              //
//              |                |           |           |                |              //
//             null             null        null        null             null            //
///////////////////////////////////////////////////////////////////////////////////////////


public class SkipNodes{

    //instance variables
    int data;
    SkipNodes next;
    SkipNodes previous;
    SkipNodes up;
    SkipNodes down;

    //constructor
    public SkipNodes(int data, SkipNodes next, SkipNodes previous, SkipNodes up, SkipNodes down){
        //set instance variables for Node
        this.data = data;
        this.next = next;
        this.previous = previous;
        this.up = up;
        this.down = down;
    }

    ///////////////////////
    //getters and setters//
    ///////////////////////

    ////////
    //Data//
    ////////
    public void setData(int data){
        this.data = data;
    }

    public int getData(){
        return data;
    }
//------------------------------------------------//
    ////////
    //Next//
    ////////

    public void setNext(SkipNodes next){
        this.next = next;
    }

    public SkipNodes getNext(){
        return next;
    }
//------------------------------------------------//
    ////////////
    //Previous//
    ////////////

    public void setPrevious(SkipNodes previous){
        this.previous = previous;
    }

    public SkipNodes getPrevious(){
        return previous;
    }
//-----------------------------------------------//
    //////
    //Up//
    //////

    public void setUp(SkipNodes up){
        this.up = up;
    }

    public SkipNodes getUp(){
        return up;
    }
//----------------------------------------------//
    ////////
    //Down//
    ////////

    public void setDown(SkipNodes down){
        this.down = down;
    }

    public SkipNodes getDown(){
        return down;
    }
}
