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

public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();
    rev(str);



    
}
}
