#include <stdio.h>


int main(){
    char input[100];
    char c = getchar();
    int count = 0;

    while(c != '\n' && count < 100){
        input[count++] = c;  
        c = getchar();

    }
    while (count != -1)
    {
        putchar(input[count--]);

    }
    return 0;
}