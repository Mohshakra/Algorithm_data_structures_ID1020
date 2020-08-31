#include <stdio.h>


/**
 *  Author          : Mohamed Shakra
 *  Generation Date : 31-08-2020
 *  This program takes in a string from the user and reverse it with recursive method
 *  input : asdfajnqewr
 *  output: rweqnjafdsa
 *  Time O(n)
 *  memory O(n)
 * 
*/


char reverse(){
    char c;
    c = getchar();     // Read char
    if(c != '\n'){     // waits until /n
        reverse();     // call reverse recusivley 
        putchar(c);
    }

}



int main(){

    reverse();
    return 0;
}