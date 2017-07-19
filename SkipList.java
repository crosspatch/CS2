//Actual List Class with methods

public class SkipList{
    //instance variables
    private int listLevel;
    private SkipNodes firstNode;
    private SkipNodes lastNode;

    //constructor
    public SkipList(){
        listLevel = 1;
        firstNode = new SkipNodes(-100000,1,lastNode,null,null,null);
        lastNode = new SkipNodes(100000,1,null, firstNode, null, null); 
    }

    //setter for level
    public void setListLevel(int l){
        this.listLevel = l;
    }

    //getter for level
    public int getListLevel(){
        return listLevel;
    }

    //firstNode will change with levels so need getters and setters for that as well
    public void setFirstNode(SkipNodes newFirst){
        this.firstNode = newFirst;
    }

    public SkipNodes getFirstNode(){
        return firstNode;
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

    public boolean search(int goal,char option ){
        boolean found=false;
        //have a current Node
        SkipNodes curr = new SkipNodes(firstNode.data, firstNode.level, firstNode.next, firstNode.previous, firstNode.up, firstNode.down);
        while(found != true){
            if(curr.data == goal){
                found = true;
                //case
                switch (option){
                    case 's':
                        return found;
                    case 'd':
                        //probaby need to pass inthe current node into each function
                        delete();
                        break;
                }
            }
            //Okay so it wasn't that node...gotta look to see if going to right would be going too far
            //go down in that case
            else{

                if(curr.next.data > goal){
                    if(curr.down == null){
                        //on bottom level and number not found
                        switch(option){
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
                                return found;
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

