/**
 *  Author          : Mohamed Shakra
 *  Generation Date : 31-08-2020
 *  Circular_list : it has the following methods.
 *  Methods : Add: add before the first
 *            remove: remove the chosen element
 *            add_prev: add prev the element
 *            add_next: add next to the element
 *            ascending: 
 *            print : printing the array after each insert/remove. t.ex. [1,2,3,45] 

 *  input : queue<Integer> fi = new queue<Integer>();
        fi.add(11);
        fi.add(13);
        output: [13,11]
        queue<Integer> asc = new queue<Integer>();

         asc.ascending(12);
        asc.ascending(13);
 *  output: [12,13]
 *  Time O(n)
 *  memory O(n)
*/

public class queue<Item> {
    int size;

    public queue(){
        size = 0;
    }

    private Node first;
    private Node last;

    class Node{
        Item item;
        Node next;
        Node prev;

    }
    public void remove(int index){

        Node rm =  new Node();
        rm = first;

        if(index == size){
            last = last.prev;
            last.next = first;
            first.prev = last;
        }
        if(1 == index){
            first.next.prev = last;
            first = first.next;
            last.next = first;
            size--;

        }
        else{
        for(int i = 1 ; i < index; i++){
            rm = rm.next;
        }
            rm.prev.next = rm.next;
            rm.next.prev = rm.prev;
            size--;
        }
       
       

        print();
    }
    public void add(Item item){
        Node node =  new Node();
        node.item = item;

        if(first == null){
            size++;
            first = node;
            last  = node;
            node.next = node;
            node.prev = node;
        }
        else{
            size++;
            node.next = first;
            last.next = node;
            first.prev = node;
            node.prev  = last;
            first = node;
        }
        print();

    }
    public void add_prev(Item x, Node current){
        Node input =  new Node();

        if(current != first){
        input.item = x;      
        current.prev.next = input;
        input.prev = current.prev;
        input.next = current;
        current.prev = input;
        size++;

        }
        else
        {
            add(x);

        }
    }
    public void add_next(Item x, Node current){
        Node input =  new Node();
        input.item = x;
        current.next = input;
        input.prev = current;
        input.next = current;
        current.prev = input;
        last = input;
        size++;
    }

    public void ascending(Item item){    //add in ascending order from left or right of the first elemeent in case of 1 node , in case of 2 or more it takes a trip on the list and place the item in right position. 
        Node rm = new Node();

        rm = first;
        boolean i = true;
        while(i){
        if((size == 0))
        {
            add(item);
            break;
        }
        if(size == 1){
            if((int)item > (int)rm.item) add_next(item, first);
            else add_prev(item , first);
            break;
        }
        else{
        while(i){
            if(((int)item < (int)rm.item)) 
            {   
                add_prev(item, rm); 
                i = false;
            }
            if(((int)item > (int)rm.item)){ 
                if(rm != last){rm = rm.next;}
                else {
                    add_next(item, last);
                    i = false;
                }
                
            } 
        }
    }
};
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
        queue<Integer> fi = new queue<Integer>();
        fi.add(11);
        fi.add(13);
        fi.add(14);
        fi.add(9);
        //fi.remove(1);

        queue<Integer> asc = new queue<Integer>();
      

        //asc.ascending(12);
        //asc.ascending(13);
        //asc.ascending(10);
        //asc.ascending(15);



    }

}
