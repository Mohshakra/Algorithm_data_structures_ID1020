/**
 *  Author          : Mohamed Shakra
 *  Generation Date : 22-09-2020
 *  filter : to filter a text file from anything but the words
 *
 *  constrains : it takes a fixed length of input, because of using an array.
 *  input :  text_file.txt
 *  output: filtered file with just words and chars.
 *
*/

#include <stdio.h>
#include <ctype.h>
void filter (FILE** fp){

char c;
  while ((c = fgetc(*fp)) != EOF){
      // finding non-chars
    if (!(c == ' ') && !(c == '\n') && isalpha(c) == 0){
      // to point to the position of the nonchar
        fseek(*fp, -1, SEEK_CUR);
        // to put a space insted of non-char.
      fputc(' ', *fp);

    }
  }
}

int main(void){
  FILE *fp;
  fp = fopen("gutenberg.txt", "r+");
  filter(&fp);
  fclose(fp);

  printf("Filtered\n");
}
