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
    int level;

    //constructor
    public SkipNodes(int data, int level, SkipNodes n, SkipNodes prev, SkipNodes u, SkipNodes d){
        //set instance variables for Node
        this.data = data;
        this.level = level;
        this.next = n;
        this.previous = prev;
        this.up = u;
        this.down = d;
    }

    public int getData(){
        return data;
    }

    //getter for level
    public int level(){
        return level;
    }

    //getter for next
    public SkipNodes getNext(){
        return next;
    }

    //getter for prev
    public SkipNodes getPrevious(){
        return previous;
    }

    //getter for up
    public SkipNodes getUp(){
        return up;
    }

    //getter for down
    public SkipNodes getDown(){
        return down;
    }
}
