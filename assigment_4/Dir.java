import java.io.IOException;

public class Dir {
     /**
     * Binary Search Tree (BST), s 398
     * (Algorithm 3.3) from lab3
     * Used in the Symbol Graph.
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
         * returns the number of key-value pairs in this symbol table
         */
        public int size() {
            return size(root);
        }

        /**
         * Returns the number of key-value pairs in this symbol table
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

        private Value get(Node x, Key key) {  // Return value associated with key in the subtree rooted at x
            if (x == null)                    // return null if the node has no key
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
            if (x == null) return new Node(key, val, 1); //if it is the first node in the tree
            int cmp = key.compareTo(x.key);              //otherwise compare the key and the key at the root
            if (cmp < 0)                                 //if key is smaller than the key at the root go left
                x.left = put(x.left, key, val);          //recrusive call
            else if (cmp > 0)                            //if key is bigger than the key at the root go right
                x.right = put(x.right, key, val);        //recrusive call
            else
                x.val = val;                             //else if key is equal to the root-key update the value

            x.N = size(x.left) + size(x.right) + 1;      //update det number of nodes in subtree
            return x;                                    //return root
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

        /*
         * checks if the symbol table contain the given key
         */
        public boolean contains(Key key) {
            return get(key) != null;
        }

        /**
         * Returns all keys in the BST
         */
        public Iterable<Key> keys() {
            return keys(min(), max());
        }

        public Iterable<Key> keys(Key lo, Key hi) { //return the all keys in the givem range
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
     * This Digraph data type is identical to Graph (page 526).
     * Except that addEdge() only calls add() once.
     */
    public static class Digraph {
        private final int V;        //number of vertices
        private int E;              //number of edges
        private Bag<Integer>[] adj; //adjacency lists

        public Digraph(int V) {
            this.V = V;
            this.E = 0;
            adj = (Bag<Integer>[]) new Bag[V]; //create array of lists.
            for (int v = 0; v < V; v++)
                adj[v] = new Bag<Integer>();   //initialize all lists to empty
        }

        public Digraph(In in) {
            this(in.readInt());            //read V and construct this graph
            int E = in.readInt();          //read E
            for (int i = 0; i < E; i++) {  //add an edge.
                int v = in.readInt();      //read a vertex,
                int w = in.readInt();      //read another vertex,
                addEdge(v, w);             //and add edge connecting them
            }
        }

        public int V() {
            return V;
        }

        public int E() {
            return E;
        }

        public void addEdge(int v, int w) {
            adj[v].add(w); //Add w to v’s list i.e connect w to v
            E++;           //increse number of edges
        }

        public Iterable<Integer> adj(int v) {
            return adj[v];
        }
    }

    /**
     * This Symbol Graph allows clients to define graphs with String vertex names instead of integer indices.
     * It maintains instance variables st (a symbol table that maps names to indices), keys
     * (an array that maps indices to names), and G (a graph, with integer vertex names).
     * To build these data structures, it makes two passes through the graph definition
     * (each line has a string and a list of adjacent strings, separated by the delimiter sp).
     */
    public static class SymbolGraph {
        private BST<String, Integer> st;  //String -> index
        private String[] keys;           //index -> String
        private Digraph G;                 //the graph

        public SymbolGraph(String stream, String sp) {   //takes a input stream and a delimiter as arguments
            st = new BST<String, Integer>();             //create a symbol table, in this case a BST
            In in = new In(stream);                      //text file from main
            while (in.hasNextLine()) {                   //build the index by reading strings to associate each distinct string with an index
                String[] a = in.readLine().split(sp);
                for (int i = 0; i < a.length; i++)
                    if (!st.contains(a[i]))              //if the BST does not already contain the string, put i into the table
                        st.put(a[i], st.size());         //put the string with the value of the current size of the table
            }
            keys = new String[st.size()];  //inverted index to get string keys
            for (String name : st.keys())
                keys[st.get(name)] = name; //an array with the keys

            G = new Digraph(st.size());  //create a graph with the size of the ST
            in = new In(stream);         //second pass

            while (in.hasNextLine()) { //build the graph by connecting the first vertex on each line to all the others
                String[] a = in.readLine().split(sp);
                int v = st.get(a[0]);  //get the value of the string/key in the table
                for (int i = 1; i < a.length; i++)
                    G.addEdge(v, st.get(a[i]));  //add an egde between the values(int)
            }
        }

        /**
         * Check if the ST contains the given string
         */
        public boolean contains(String s) {
            return st.contains(s);
        }

        /**
         * Get the index of a given string
         */
        public int index(String s) {
            return st.get(s);
        }

        /**
         * Get the string name of av given index
         */
        public String name(int v) {
            return keys[v];
        }

        public Digraph G() {
            return G;
        }
    }

    /**
     * Depth First Directed Path uses depth-first search to find paths to all the vertices
     * in a graph that are connected to a given start vertex s.
     */
    public static class DepthFirstDirectedPaths {
        private boolean[] marked;  //marked[v] = true iff v is reachable from s
        private final int s;       //source vertex

        /**
         * Computes a directed path from s to every other vertex in digraph G
         */
        public DepthFirstDirectedPaths(Digraph G, int s) {
            marked = new boolean[G.V()];
            this.s = s;
            dfs(G, s);
        }

        /**
         * Computes depth-first search and marks all reachable vertices w
         * with true.
         */
        private void dfs(Digraph G, int v) {
            marked[v] = true;        //mark vertex v as visited
            for (int w : G.adj(v)) { //go through all vertex in the list
                if (!marked[w]) {    //if unmarked
                    dfs(G, w);       //recrusively visit all unmakred vertices adjecent to v
                }
            }
        }

        /**
         * Is there a directed path from the source vertex s to vertex v
         */
        public boolean hasPathTo(int v) {
            return marked[v]; //if marked[v] is true then there is a path from s to v
        }
    }

    /**
     * Test client.
     * Reads in a command-line text file of strings and two strings from standard input.
     * Creates a Symbol Graph and uses DFS to find out if there is a path between
     * the given vertices.
     */
    public static void main(String[] args) throws IOException {

        SymbolGraph sg = new SymbolGraph(args[0], args[1]);  //create a symbol graph of input stream and delimeter
        Digraph DG = sg.G();         //get the graph of the symbol graph
        String source1 = args[2];  //the first vertex to find a path from
        String source2 = args[3];  //the vertex to find a path to


        int s1 = sg.index(source1);  //get the index of the word1
        DepthFirstDirectedPaths bfs = new DepthFirstDirectedPaths(DG, s1); //find path in the graph G from vertex s1
        int s2 = sg.index(source2); //get the index of the word2

        if (bfs.hasPathTo(s2)) {
            StdOut.println("There is a path between " + source1 + " and " + source2);
        } else StdOut.println("No path");
    }
}
