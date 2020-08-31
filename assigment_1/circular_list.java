/**
 *  Author          : Mohamed Shakra
 *  Generation Date : 31-08-2020
 *  Circular_list : it has the following methods.
 *  Methods : Add_last to add after the last element.
 *            Add_first to add before the first element
 *            Remove_first : remove the first element
 *            Remove_last  : remove the last element
 *            print : printing the array after each insert/remove. t.ex. [1,2,3,45] 
 *  constrains : it takes a fixed length of input, because of using an array.
 *  input : circular_list<Integer> cl = new circular_list<Integer>();
 *       cl.add_first(1);
 *       cl.add_first(2);
 *  output: [2,1]
 *  Time O(n)
 *  memory O(n)
*/


public class circular_list<Item> {
    int size;

    public circular_list(){
        size = 0;
    }

    private Node first;
    private Node last;

    class Node{
        Item item;
        Node next;
    }

    public void add_first(Item input){
        Node node = new Node();
        node.item = input;
        if(first == null) 
        {
            size ++;
            first = node;
            last  = node;
        
        }
        else{
            size++;
            node.next = first;
            first = node;
        }
        print();
    }
    public void add_last(Item input){
        Node node = new Node();
        node.item = input;
        if(first == null) 
        {
            size++;
            first = node;
            last  = node;
        
        }
        else{
            size++;
            last.next = node;
            last = node;
        }
        print();
    }
    public void remove_first(){
        if(first == last)
        {
            size = 0;
            first = null;
        }
        else{
            first = first.next;
            size--;
        }
        print();

    }
    
    public void remove_last(){

        if(first == last)
        {
            first = null;
        }
        else{
            Node node =  new Node();
            node = first;
            for(int i = 0; i < size-2; i++){
                node = node.next;
            }
            last = node;
            size--;
        }
        print();

    }

  

    public void print(){
        if(size == 0) System.out.println("[ ]");
        if(size == 1) System.out.println("[" + first.item + "]");
        else{
            Node pointer = first;
            System.out.println("");
            System.out.print("[");
            for(int i= 0; i < size ; i++){
                System.out.print(pointer.item);
                if((pointer != last) && (pointer.next != null)) System.out.print(",");
                else System.out.print("]");
                pointer = pointer.next;
            }
            System.out.println("");

        }
        System.out.println("The size of the list is: " + size );


    }





    public static void main(String[] args) {

        circular_list<Integer> cl = new circular_list<Integer>();
        cl.add_first(1);
        cl.add_first(2);
        cl.add_first(3);
        cl.add_first(4);
        cl.add_first(5);
        cl.remove_last();
        cl.remove_last();
        cl.remove_last();
        cl.remove_last();

    
    
    
    
        
    }
    
}