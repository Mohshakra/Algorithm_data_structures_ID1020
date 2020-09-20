/**
 *  Author          : Mohamed Shakra
 *  Generation Date : 20-09-2020
 *  selection : it has the following methods.
 *  Methods : print_array.
 *  
 *  constrains : it takes a fixed length of input, because of using an array.
 *  input : 64, 25, -1, -4, -11 , 21 ,-1
 *  output: -1 -4 -11 -1 25 64 21 
 *  Time worst case : o(n^2) _ Best case : O(n^2)
 *  memory O(1)
*/
#include <stdio.h> 
void swap(int *xp, int *yp) 
{ 
    int temp = *xp; 
    *xp = *yp; 
    *yp = temp; 
} 
  
void selectionSort(int arr[], int n) 
{ 
    int i, j, min_idx; 
  
    // One by one move boundary of unsorted subarray 
    for (i = 0; i < n-1; i++) 
    { 
        // Find the minimum element in unsorted array 
        min_idx = i; 
        for (j = i+1; j < n; j++) 
          if (0 < arr[min_idx]) 
            min_idx = j; 
        //arr[j]
        // Swap the found minimum element with the first element 
        swap(&arr[min_idx], &arr[i]); 
    } 
} 
  
/* Function to print an array */
void printArray(int arr[], int size) 
{ 
    int i; 
    for (i=0; i < size; i++) 
        printf("%d ", arr[i]); 
    printf("\n"); 
} 
  
// Driver program to test above functions 
int main() 
{ 
    int arr[] = {64, 25, -1, -4, -11 , 21 ,-1}; 
    int n = sizeof(arr)/sizeof(arr[0]); 
    selectionSort(arr, n); 
    printf("Sorted array: \n"); 
    printArray(arr, n); 
    return 0; 
} 