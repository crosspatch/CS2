//Generic Node class

public class Node<T>{
    //instance variables
    T data;
    Node<T> next;

    //constructor if no pointer is passed
    Node(T object){
        this(object,null);
    }

    //contructor if data and next passed
    Node(T info, Node<T> pointer){
        data = info;
        next = pointer;
    }

    //getter for data
    private T getData(){
        return data;
    }

    //getter for next Node
    private Node<T> getNext(){
        return next;
    }
}

