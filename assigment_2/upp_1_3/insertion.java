/**
 *  Author          : Mohamed Shakra
 *  Generation Date : 20-09-2020
 *  insertion : it has the following methods.
 *  Methods : arrange: to arrange the array in ascending order
 *            Invcount : to list all the inversions
 *            print    ; to print after each input
 *  
 *  constrains : it takes a fixed length of input, because of using an array.
 *  input : 3 4 1 4 5
 *  output: [2,1]
 *  Time worst case : o(n^2) _ Best case : O(n)
 *  memory O(1)
*/
import java.util.Scanner;
public class insertion {
    
    public static int[] arrange(int[] arr){
        int n = arr.length; 
        int swaps =  0 ;

        print(arr);
        for (int i = 1; i < n; ++i) { 
            int key = arr[i]; 
            int j = i - 1; 
            
           // Jämför elementen två och två
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                j = j - 1;
                swaps++; 
                // adderar antal swaps

            } 
            // forsätter pekaren till nästa element
            arr[j + 1] = key; 
            print(arr);


        } 
        System.out.println("The number of swaps : " + swaps);
        return arr;
    }
    static int getInvCount(int arr[]) 
    {   
        // när första element är större and andra det räknas en inversion.
        int n = arr.length;
        int inv_count = 0; 
        for (int i = 0; i < n - 1; i++) 
            for (int j = i + 1; j < n; j++) 
                if (arr[i] > arr[j]){
                    System.out.println("[index: "+ i +" value: "+ arr[i] +" index: "+ j + " value: " + arr[j] +"]");
                    inv_count++; 
                }
        return inv_count; 
    } 
  

     
    
    public static void print(int numbers []){
        for(int i = 0 ; i < numbers.length; i++)
        System.out.print(numbers[i]);
        System.out.println("");

    }
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("The coantity of the number that will be sorted");
    int str = sc.nextInt();
    System.out.println("Please enter the numbers:");
    int[] numbers = new int[str];
    for(int i = 0 ; i < numbers.length ; i++ ){
        str = sc.nextInt();
        numbers[i]= str;

    }
    System.out.println("Number of inversions are " + getInvCount(numbers)); 
    System.out.println("Sorting ...");
    arrange(numbers);
    

}
}