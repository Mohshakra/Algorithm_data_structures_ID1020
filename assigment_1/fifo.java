/**
 *  Author          : Mohamed Shakra
 *  Generation Date : 31-08-2020
 *  fifo : it has the following methods.
 *  Methods : 
 *            add: to add before the first element
 *            Remove_last  : remove the first that been put at first element
 *            print : printing the array after each insert/remove. t.ex. [1,2,3,45] 
 *  constrains : it takes a fixed length of input, because of using an array.
 *  input : circular_list<Integer> cl = new circular_list<Integer>();
 *      fi.add(12);
        fi.add(13);
 *  output: [13,12]
 *  Time O(n)
 *  memory O(n)
*/


import java.util.Iterator;

public class fifo<Item> {

    int size;

    public fifo(){
        size = 0;
    }

    private class fifo_iterator implements Iterator<Item>{

    private Node current = first;
    public Item next(){
        Item item= current.item;
        current = current.next;
        return item;
    }
    @Override
    public boolean hasNext() {
        return current != null;
    }
    }



    private Node first;
    private Node last;
    public class Node{
        Item item;
        Node next;
        Node prev;
    }

    public void remove(){
        if(first == last){
            first = null;
            size = 0;
            print();
        }
        else{
        first = first.next;
        size--;
        print();
        }
    }
    public void add(Item item){
        Node node =  new Node();
        node.item = item;

        if(first == null){
            size++;
            first = node;
            last  = node;
            print();

        }
        else{
            size++;
            last.next = node;
            node.prev = last;
            last = node;
            print();
        }
    }


    public void print(){
        if(first == null) System.out.println("[ ]");
        if(first == last) System.out.println("[" + first.item + "]");
        if(first !=last){
            Node pointer = first;
            System.out.println("");
            System.out.print("[");
            for(int i= 0; i < size ; i++ ){
                System.out.print(pointer.item);
                if(pointer.next != null) System.out.print(",");
                else System.out.print("]");
                pointer = pointer.next;
            }
            System.out.println("");

        }

    }



    public static void main(String[] args) {
        fifo<Integer> fi = new fifo<Integer>();
        fi.add(12);
        fi.add(13);
        fi.add(14);
        fi.add(15);
        fi.add(16);
        fi.add(17);
        fi.remove();
        fi.remove();
        
        
      
        //fi.print();


    }

}