/**
 *  Author          : Mohamed Shakra
 *  Generation Date : 23-09-2020
 *  pick :  takes an array list of strings and a string with a word. And then
 *    search for the given word
 *  Methods : find index : to find the occurancy of a word.
 *  
 *  input : pick.java the < input.txt
 *  output: The places where the words appears.
 *  Time  :worst o(log(n)) best o(log(n))
 *  memory O(n)
*/



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class pick {
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
      

        public Iterable<Key> keys(Key lo, Key hi) { //return the all keys in the givem range

            Queue<Key> queue = new Queue<Key>();
            if (lo.compareTo(hi) > 0) return queue;
            for (int i = rank(lo); i < rank(hi); i++) //go through the array and enqueue all keys
                queue.enqueue(keys[i]);
            if (contains(hi)) queue.enqueue(keys[rank(hi)]);
            return queue;
        }
    }
    private static final String FILE_PATH = "/Users/mohamedshakra/Desktop/Algorithm_data_structure/assigment_3/upp_4/words.txt";

    private static Scanner fileScanner(String filePath){
        try {
            return new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("No file!");
            return null;
        }
    }

    public static BinarySearchST<String, ArrayList<Integer>> build(String filePath) {
        BinarySearchST<String, ArrayList<Integer>> searcher = new BinarySearchST<>(200000);
        Scanner scanner = fileScanner(filePath);
        if(scanner == null) {
            return null;
        }
        scanner.useDelimiter("");
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        while(scanner.hasNext()) {  //keep scaning
            char c = scanner.next().toLowerCase().charAt(0);
            if(Character.isAlphabetic(c)) { //Counting each char
                sb.append(c);               //appending them in one word
                counter++;                  // increasing the number of chars.
                continue;
            }
            String word = sb.toString();    
            if(word.length() == 0) {
                if(c == ' ') {              //if word is nothing yet we count one place from begining.
                    counter++;              // adding the number of spaces.
                }
                continue;
            }
            ArrayList<Integer> list = searcher.get(word);
            if(list == null) {
                list = new ArrayList<>();        // initiate a list
            }
            list.add(counter - word.length());  //it counts how many chars from begining to the word 
            searcher.put(word, list);           // Puts the list and the word inside the tree
            sb = new StringBuilder();           // begin counting agin after adding the last one.
            if(c == ' ') {
                counter++;
            }
        }
        return searcher;
    }

    public static void findIndex(String word, BinarySearchST<String, ArrayList<Integer>> searcher) {
        ArrayList<Integer> list = searcher.get(word);
        if(list == null) {
            System.out.println("Not found");
            return;
        }
        System.out.println(searcher.get(word));
    }

    public static void main(String[] args) {
        BinarySearchST<String, ArrayList<Integer>> searcher = build(FILE_PATH);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the word you want to find: ");
        findIndex(scanner.nextLine().toLowerCase(), searcher);
    }
}