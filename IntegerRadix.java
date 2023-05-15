import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IntegerRadix {
    
    public static int[] IntegerRadixsort(int[] arr) {

        // return if number is 1
        if(arr.length == 1)
            return arr;

        // Find the maximum value in the input array
        int maxValue = getMaxValue(arr);

        // Set the starting value for the exponent
        int exponent = 1;

        // Create two arrays of ArrayLists to hold the values of the input array during
        // sorting.
        // Each ArrayList in the arrays will hold the values with a particular radix
        // during a pass.
        List<Integer>[] Array1 = createArrayLists();
        List<Integer>[] Array2 = createArrayLists();

        // First pass: Sort the values of the input array based on their least significant digit
        // and add them to the corresponding ArrayList in Array1.
        for (int i = 0; i < arr.length; i++) {
            int index = (arr[i] / exponent) % 10;
            int value = arr[i];
            Array1[index].add(value);
        }

        // Multiply the radix by 10 to move to the the next significant digit
        exponent *= 10;

        // Repeat the process until all significant digits have been sorted
        for (; maxValue / exponent > 0; exponent *= 10) {

            // For each pass, empty each ArrayList in Array1 into the appropriate ArrayList in Array2
            // based on the next significant digit
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < Array1[i].size(); j++) {
                    int Array1Value = Array1[i].get(j);
                    int index1 = (Array1Value / exponent) % 10;
                    Array2[index1].add(Array1Value);
                }
                Array1[i].clear();
            }
            
            // Multiply the radix by 10 to move to the next significant digit
            exponent *= 10;
        
            // For each pass, empty each ArrayList in Array2 into the appropriate ArrayList in Array1
            // based on the next significant digit
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < Array2[i].size(); j++) {
                    int Array2Value = Array2[i].get(j);
                    int index2 = ((Array2Value / exponent) % 10);
                    Array1[index2].add(Array2Value);
                }
                Array2[i].clear();
            }
        }

        // Copy the sorted values from Array1 into a new int array and return it.
        int[] sortedArray = new int[arr.length];
        int arrCount = 0;
        for (int i = 0; i < 10; i++) {
            while (!Array1[i].isEmpty()) {
                int sortedArrayValue = Array1[i].remove(0);
                sortedArray[arrCount] = sortedArrayValue;
                arrCount++;
            }
        }
        return sortedArray;
    }

    // A method that finds the maximum value in an array of integers
    private static int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        return maxValue;
    }

    // This method creates an array of 10 empty ArrayLists of Integer radix file
    private static List<Integer>[] createArrayLists() {
        List<Integer>[] arrays = new ArrayList[10];
        for (int i = 0; i < 10; i++) {
            arrays[i] = new ArrayList<>();
        }
        return arrays;
    }

    // This method prints the elements of an array of integer
    static void print(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // This is the main method where the program starts executing
    public static void main(String[] args) {
        // Create an unsorted array of Integer
        int[] arr = { 170, 61, 503, 275, 426, 87, 677, 409 };
        System.out.print("Original Array: ");
        print(arr);
        // Sort the array using Integer Radix Sort algorithm and store the sorted array
        // in sortedArr
        int[] sortedArray = IntegerRadixsort(arr);
        System.out.print("Sorted Array: ");
        print(sortedArray);
        System.out.print("\n");

        // create 2 arrays which have random positive integer number inside the array
        int[] arr2 = new int[10];
        int[] arr3 = new int[10];
        Random rand = new Random();
        
        // assign a random created integer number to the array
        for (int i = 0; i < arr2.length; i++) {
           arr2[i] = rand.nextInt(10000);
           arr3[i] = rand.nextInt(1000);
        }

        // Sort the array using Integer Radix Sort algorithm and store the sorted array
        // in sortedArr2 and sortedArr3
        int[] sortedArray2 = IntegerRadixsort(arr2);
        int[] sortedArray3 = IntegerRadixsort(arr3);

        // print the sorted Random Array 1
        System.out.print("Original Random Array 1: ");
        print(arr2);
        System.out.print("Sorted Random Array 1: ");
        print(sortedArray2);

        System.out.print("\n");

        // print the sorted Random Array 2
        System.out.print("Original Random Array 2: ");
        print(arr3);
        System.out.print("Sorted Random Array 2: ");
        print(sortedArray3);

    }
}
