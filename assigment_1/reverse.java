/**
 *  Author          : Mohamed Shakra
 *  Generation Date : 31-08-2020
 *  This program takes in a string from the user and reverse it with iterative and recursive method.
 *  constrains : it takes a fixed length of input, because of using an array.
 *  input : asdfajnqewr
 *  output: rweqnjafdsa
 *  Time O(n^2)/ O(n)
 *  memory O(n)
*/

import java.util.Scanner;

class reverse {

    int max = 100;
    char buffer[] = new char[max];
    int size; 
    int pos ;

public reverse(){
    size = 0;
    pos = -1;
}

public void push(final char in) {
    if (size < max) {
        ++size;
        buffer[++pos] = in;
    } else
        System.out.println("Stack Overflow");
}

public void pop() {
    if (pos == -1) {
        System.out.println("Stack underflow");
    } else {
        final char out = buffer[pos--];
        System.out.print(out);
    }
}

public static void rev(String str) {
    

    reverse stack = new reverse();
    for(int i = 0; i < str.length() ; i++) {
        stack.push(str.charAt(i));
    }

    for(int j = 0; j < stack.size ; j++){
        stack.pop();
    }


}

public static String reverseString(String str)
{
    if (str.isEmpty())
        return str;
    //Calling Function Recursively
    return reverseString(str.substring(1)) + str.charAt(0);
}
 

public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();
    rev(str);
    System.out.println("");
    String reversed = reverseString(str);
    System.out.println(reversed);



    
}
}
