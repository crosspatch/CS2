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

    public void insert(){
    }

    public void promote(){

    }

    public void delete(){
    }

    public boolean search(int goal,char option){
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
                                insert();
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

