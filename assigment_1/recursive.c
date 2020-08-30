#include <stdio.h>

char reverse(){
    char c;
    c = getchar();
    if(c != '\n'){
        reverse();
        putchar(c);
    }

}



int main(){

    reverse();
    return 0;
}