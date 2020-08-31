/**
 *  Author          : Mohamed Shakra
 *  Generation Date : 31-08-2020
 *  This program takes in a string from the user and reverse it with iterative method.
 *  constrains : it takes a fixed length of input, because of using an array.
 *  input : asdfajnqewr
 *  output: rweqnjafdsa
 *  Time O(n)
 *  memory O(n)
*/


#include <stdio.h>


int main(){
    char input[100];
    char c = getchar();
    int count = 0;

    while(c != '\n' && count < 100){   //takes char till 99 charcters.
        input[count++] = c;  
        c = getchar();

    }
    while (count != -1)
    {
        putchar(input[count--]);

    }
    return 0;
}