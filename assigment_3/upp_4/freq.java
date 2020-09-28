/**
 *  Author          : Mohamed Shakra
 *  Generation Date : 23-09-2020
 *  freq :  it's compares between ordered array st and binary search tree algorithm
 *  Methods : get : to get the value
 *            put : to initate a vale and key
 *  
 *  input : freq.java 5 < input.txt
 *  output: the most common word with 4 chars
 *  Time  : Binary search tree : worst o(N) best o(log(n))
 *          Ordered search tree : worst,best(log(n))
 *  memory O(n)
*/
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class freq {

    /**
     * Binary Search Tree (BST), s 398
     * (Algorithm 3.3)
     */
    public static class BST<Key extends Comparable<Key>, Value> {
        private Node root;

        /**
         * Each node contains a key, value, reference to left and right
         * and number of nodes in subtree N
         */
        private class Node {
            private Key key;
            private Value val;
            private Node left, right;
            private int N;

            public Node(Key key, Value val, int N) {  //constructor
                this.key = key;
                this.val = val;
                this.N = N;
            }
        }

        /**
         * Returns the number of key-value pairs in this symbol table
         */
        public int size() {
            return size(root);
        }

        /**
         * Returns number of key-value pairs in BST rooted at x
         */
        private int size(Node x) {
            if (x == null)
                return 0;
            else
                return x.N;
        }

        /**
         * Returns the value of the given key
         */
        public Value get(Key key) {
            return get(root, key);
        }

        private Value get(Node x, Key key) {  //return value associated with key in the subtree rooted at x
            if (x == null)                    //return null if the node has no key
                return null;
            int cmp = key.compareTo(x.key);   //otherwise compare the key and the key at the root
            if (cmp < 0)                      //if key is smaller than the key at the root go left
                return get(x.left, key);      //recrusive call
            else if (cmp > 0)                 //if key is bigger than the key at the root go right
                return get(x.right, key);     //recrusive call
            else
                return x.val;                 //return value assoc with node
        }

        /**
         * Inserts the given key-value pair into the symbol table, overwrite the old
         * value with the new value if the symbol table already contains the key
         */
        public void put(Key key, Value val) {  // Search for key. Update value if found; grow table if new.
            root = put(root, key, val);
        }

        private Node put(Node x, Key key, Value val) {
            if (x == null) return new Node(key, val, 1); //if it is the first node in the tree or found the right place for the new node
            int cmp = key.compareTo(x.key);              //otherwise compare the key and the key at current node
            if (cmp < 0)                                 //if key is smaller than the key at the current node go left (i.e lexicographically samller)
                x.left = put(x.left, key, val);          //recrusive call, to go down i the tree to find the right place
            else if (cmp > 0)                            //if key is bigger than the key at the root go right (i.e lexicographically greater)
                x.right = put(x.right, key, val);        //recrusive call, to go down i the tree to find the right place
            else
                x.val = val;                             //else if key is equal to the current node update the value

            x.N = size(x.left) + size(x.right) + 1;      //update det number of nodes in subtree
            return x;                                    //return root
        }

        /**
         * Checks if the symbol table contain the given key
         */
        public boolean contains(Key key) {
            return get(key) != null;
        }

        /**
         * Returns the smallest key in the BST
         */
        public Key min() {
            return min(root).key;
        }

        private Node min(Node x) {
            if (x.left == null) return x;
            return min(x.left);
        }

        /**
         * Returns the biggest key in the BST
         */
        public Key max() {
            return max(root).key;
        }

        private Node max(Node x) {
            if (x.right == null) return x;
            else return max(x.right);
        }

        /**
         * Returns all keys in the BST
         */
        public Iterable<Key> keys() {
            return keys(min(), max());
        }

        public Iterable<Key> keys(Key lo, Key hi) { //return the all keys in a queue
            Queue<Key> queue = new Queue<Key>();
            keys(root, queue, lo, hi);
            return queue;
        }

        private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
            if (x == null) return;                                    //if x == null, done
            int cmplo = lo.compareTo(x.key);                          //compare if the lowest key is samller than current key
            int cmphi = hi.compareTo(x.key);                          //compare if the highest key is bigger then current key
            if (cmplo < 0)                                            //samller - go left and recrusive call
                keys(x.left, queue, lo, hi);
            if (cmplo <= 0 && cmphi >= 0)                             //equal - add key to queue
                queue.enqueue(x.key);
            if (cmphi > 0)                                            //greater - go right and recrusive call
                keys(x.right, queue, lo, hi);
        }
    }

    /**
     * Binary search (in an ordered array) (BSST), s 381
     * (Algorithm 3.2)
     */
    public static class BinarySearchST<Key extends Comparable<Key>, Value> {
        private static final int INIT_CAPACITY = 2;
        private Key[] keys;                           //array for the keys
        private Value[] vals;                         //array for the values
        private int n = 0;

        public BinarySearchST() {  //constructor
            this(INIT_CAPACITY);
        }

        /**
         * Initializes an empty BSST with the specified initial capacity
         */
        public BinarySearchST(int capacity) {
            keys = (Key[]) new Comparable[capacity];
            vals = (Value[]) new Object[capacity];
        }

        /**
         * Method to resize the symboltable
         */
        private void resize(int capacity) {
            Key[] tempk = (Key[]) new Comparable[capacity];  //create a temp array for keys with
            Value[] tempv = (Value[]) new Object[capacity];  //create a temp array for values
            for (int i = 0; i < n; i++) {                    //iterate through all keys and values and copie to temp
                tempk[i] = keys[i];
                tempv[i] = vals[i];
            }
            vals = tempv;                                    //update so original array equals temp
            keys = tempk;                                    //update so original array equals temp
        }

        /**
         * Returns the number of key-value pairs in this BSST
         */
        public int size() {
            return n;
        }

        /**
         * Returns true if this BSST is empty
         */
        public boolean isEmpty() {
            return size() == 0;
        }

        /**
         * Check if the symbol table contain the given key
         */
        public boolean contains(Key key) {
            return get(key) != null;
        }

        /**
         * Returns the value of the given key
         */
        public Value get(Key key) {
            if (isEmpty()) return null;                               //check if BSST is empty
            int i = rank(key);                                        //find the right index for the key
            if (i < n && keys[i].compareTo(key) == 0) return vals[i]; //check if key equals the key att index i
            return null;
        }

        /**
         * Returns the number of keys in this BSST less than key
         * (the index for a given key)
         */
        public int rank(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to rank() is null");

            int lo = 0, hi = n - 1;                   //lo first array index, hi last array index
            while (lo <= hi) {                        //do as long as hi is bigger or equal to lo
                int mid = lo + (hi - lo) / 2;         //find index at the middle of the array
                int cmp = key.compareTo(keys[mid]);   //compare if the key is bigger or samller than key at middle
                if (cmp < 0) hi = mid - 1;            //smaller put hi as mid-1, subarray
                else if (cmp > 0) lo = mid + 1;       //bigger put lo as mid+1, subarray -> while
                else return mid;
            }
            return lo;
        }

        /**
         * Inserts the given key-value pair into the BSST, overwriting the old
         * value with the new value if the BSST already contains the given key
         */
        public void put(Key key, Value val) {
            if (key == null) throw new IllegalArgumentException("first argument to put() is null");

            int i = rank(key);                             //find the right index for key

            if (i < n && keys[i].compareTo(key) == 0) {    //if key is already in table
                vals[i] = val;                             //update the value
                return;
            }
            if (n == keys.length) resize(2 * keys.length);  //if array is full double the array

            for (int j = n; j > i; j--) {                   //move all the elements to the right upto index i where the new will be
                keys[j] = keys[j - 1];
                vals[j] = vals[j - 1];
            }
            keys[i] = key;                                  //insert the new key
            vals[i] = val;                                  //insert the new value
            n++;                                            //increase n
        }

        /**
         * Returns the smallest key in the BSST
         */
        public Key min() {
            if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
            return keys[0];
        }

        /**
         * Returns the largest key in the BSST
         */
        public Key max() {
            if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
            return keys[n - 1];
        }

        /**
         * Returns all keys in the BSST
         */
        public Iterable<Key> keys() {
            return keys(min(), max()); //in the range of min and manx
        }

        public Iterable<Key> keys(Key lo, Key hi) { //return the all keys in the givem range

            Queue<Key> queue = new Queue<Key>();
            if (lo.compareTo(hi) > 0) return queue;
            for (int i = rank(lo); i < rank(hi); i++) //go through the array and enqueue all keys
                queue.enqueue(keys[i]);
            if (contains(hi)) queue.enqueue(keys[rank(hi)]);
            return queue;
        }
    }

    /**
     * Reads in a command-line integer and sequence of words from
     * standard input and prints out a word (whose length exceeds
     * the threshold) that occurs most frequently to standard output.
     * The Stopwatch from java library compute the runtime of the
     * Frequency test.
     */
    public static void main(String[] args) throws IOException {

        /**
         * choose which symbol table to test
         */
        BST<String, Integer> st = new BST<String, Integer>();
        //BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();

        //int minlen = Integer.parseInt(args[0]);
        int x = 1;

        /**
         * Compute the frequency of all words
         * value counts the number of appearance
         */
        ArrayList<String> AList = new ArrayList<String>();  
        while (!StdIn.isEmpty()) {
            x++;
            String key = StdIn.readString();
            st.put(key, x);
        }
        System.out.println(st.get("the"));
        // find a key with the highest frequency count
        


    
    }
}
