/**
 *  Author          : Mohamed Shakra
 *  Generation Date : 23-09-2020
 *  hash :  It uses java inbuild hash function to show the distrubution of the hashes
 *  Methods : get , put
 *  input : hash.java < input.txt
 *  output: The hash distrubution.
 *  Time  :worst o(N) best o(N)
 *  memory O(n)
*/


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class hash {

    /**
     * Symbol Table, Linked List, each node contains a value and key and reference to next node
     * Used in the separate chaining hash table
     */
    public static class SequentialSearchST<Key, Value> {
        private Node first;   // first node in the linked list

        private class Node {  // linked-list node
            Key key;
            Value val;
            Node next;

            public Node(Key key, Value val, Node next) {
                this.key = key;
                this.val = val;
                this.next = next;
            }
        }

        /**
         * Search for a given key and return the associated value
         */
        public Value get(Key key) {
            for (Node x = first; x != null; x = x.next) //go through all nodes
                if (key.equals(x.key))                  //check if the key at the node equals the given key
                    return x.val;                       // search hit
            return null;                                // search miss
        }

        /**
         * Insert a given key and value, update the value if the
         * key already exist
         */
        public void put(Key key, Value val) {
            for (Node x = first; x != null; x = x.next) //search if the key already exist, in that case update the value
                if (key.equals(x.key)) {                //check if equal
                    x.val = val;                        //search hit, update val
                    return;
                }
            first = new Node(key, val, first);          //search miss, add new node
        }

        /**
         * Check if the symbol table contains a given key
         */
        public boolean contains(Key key) {
            return get(key) != null;       //false if search miss in get
        }

        /**
         * Method to print the linked list, key: value,
         */
        public void printST() {
            Node node = first;      //want to iterate the list, begin at the first node
            while (node != null) {
                System.out.print(" " + node.key + ": " + node.val + ",");  //print each node in the linked list "key: value"
                node = node.next;   //go to next node
            }
        }

        /**
         * Iterator to go through all keys, returns a queue with
         * the keys
         */
        public Iterable<Key> keys() {
            Queue<Key> queue = new Queue<Key>();
            for (Node x = first; x != null; x = x.next) //go through all nodes
                queue.enqueue(x.key);                   //add to queue
            return queue;
        }
    }

    /**
     * Separate chaining hash table.
     * Each hash code refers to a linked list (ST). So the objects
     * with same hash code is placed in the same linked list with
     * its key and value to avoid collision resolution.
     */
    public static class SeparateChainingHashST<Key, Value> {
        private static final int INIT_CAPACITY = 4;
        private int N;                                //number of key-value pairs
        private int M;                                //hash table size
        private SequentialSearchST<Key, Value>[] st;  //array of ST objects

        public SeparateChainingHashST() {             //constructor
            this(INIT_CAPACITY);
        }

        public SeparateChainingHashST(int M) {  //Create M linked lists.
            this.M = M;
            st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
            for (int i = 0; i < M; i++)
                st[i] = new SequentialSearchST();  //linked list for each hash code
        }

        /**
         * Resize the hash table. Create a temp table and copie all
         * key-value pairs and then uppdate the original hash table
         * to be the temp hash table
         */
        private void resize(int chains) {
            SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains); //create a temp table
            for (int i = 0; i < M; i++) {            //copie all hashCodes
                for (Key key : st[i].keys()) {       //copie all linked lists
                    temp.put(key, st[i].get(key));
                }
            }
            this.M = temp.M;       //then our original hashtable equals the temp
            this.N = temp.N;
            this.st = temp.st;
        }

        /**
         * The hash function uses the built-in hash function for strings in Java.
         * Returns a hash code value for the object
         */
        private int hash(Key key) {                   //returns the hash index from the key
            return (key.hashCode() & 0x7fffffff) % M; //modulo the size of the hash table, there are M diffrent hashcodes
        }

        /**
         * Returns the value of given key
         */
        public Value get(Key key) {
            return (Value) st[hash(key)].get(key);
        }

        /**
         * Insert key-value pair to the hash table
         */
        public void put(Key key, Value val) {
            if (N >= 8 * M) resize(2 * M);    //double table size if average length of list >= 8

            int i = hash(key);
            if (!st[i].contains(key))
                N++;                          //if hash does not already contains the key, put the key in value in linked list
            st[i].put(key, val);
        }


        /**
         * Method to print the hash table, call the method printST
         * hash. key: value
         */
        public void printTable() {
            for (int i = 0; i < M; i++) {
                System.out.print(i + ".");
                st[i].printST();
                System.out.println();
                System.out.println();
            }
        }
    }

    /**
     * Test program, read words from given textfile and put them into the
     * hashtable then print the table using print methods
     */
    public static void main(String[] args) throws IOException {

        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();
        BufferedReader rd = new BufferedReader(new FileReader("gutenberg.txt"));
        String line;
        int i = 0;

        while ((line = rd.readLine()) != null) {
            line = rd.readLine();
            String words[] = line.split(" ");
            for (String x : words) {
                st.put(x, i);
                i++;
            }
        }
        /**
         * Prints the hashtable
         * hash. key: value
         * (each hash has a linked list of key: value)
         */
        st.printTable();
    }
}