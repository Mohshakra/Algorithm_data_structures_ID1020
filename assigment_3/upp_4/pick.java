/**
 *  Author          : Mohamed Shakra
 *  Generation Date : 23-09-2020
 *  pick :  takes an array list of strings and a string with a word. And then
 *    search for the given word
 *  Methods : find index : to find the occurancy of a word.
 *  
 *  input : pick.java the < input.txt
 *  output: The places where the words appears.
 *  Time  :worst o(N) best o(N)
 *  memory O(n)
*/



import java.util.ArrayList;

public class pick {

 
    public static void findIndex(ArrayList<String> AList, String word) {

        for (int index = 0; index < AList.size(); index++) {     //iterate the list and search for the word
            String key = AList.get(index);                       //take every word in the text
            if (key.equals(word))                                //if this equal to the given word
                StdOut.println(index);                           //print the index
        }
    }

    public static void main(String[] args) {
        ArrayList<String> AList = new ArrayList<String>();          //create an ArrayList from Javas standard library
        while (!StdIn.isEmpty())
            AList.add(StdIn.readString().toLowerCase());            //add the text to the arraylist, only lower case letters

        String word = args[0];                                     //word to search for
        findIndex(AList, word);                                    //call the method to find the index

    }

}