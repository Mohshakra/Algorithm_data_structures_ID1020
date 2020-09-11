import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

public class parentheses {
    int max = 100;
    char buffer[] = new char[max];
    int size; 
    int pos ;


    public void push(final char in) {
        if (size < max) {
            ++size;
            buffer[pos++] = in;
        } else
            System.out.println("Stack Overflow");
    }
    
    public char pop(){
            char out = buffer[--pos];
        return out;
        

    }

    public void check(String input){
        parentheses sta1 = new parentheses();
        parentheses sta2 = new parentheses();
        for(int i= 0 ; i<input.length(); i++){
            char c = input.charAt(i);
            if(c == ('(') || c == ('{') || c == ('[')){
            sta1.push(input.charAt(i));
            }
            else{
                sta2.push(input.charAt(i));

            }
        }
        for(int i= 0 ; i < sta1.size ; i++){
            char c = sta1.pop();
            char cd = sta2.pop();
            if(((c == '(')&&(cd == ')'))||((c == '[')&&(cd == ']'))||((c == '{')&&(cd == '}'))){
                continue;
            }
            else{
                System.out.println("The parentheses is not equal");
            }
        }
    }




    public static void main(final String[] args) {
        parentheses test = new parentheses();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        test.check(str);
    

    
}
}