import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FloatingRadix {

    public static float[] FloatRadixsort(float[] arr) {

        // return if number is 1
        if(arr.length == 1)
            return arr;

        // Find the maximum value in the input array
        float maxValue = getMaxValue(arr);

        // Initialize the exponent to be a very small value
        double exponent = 0.000000000001;

        // Create two arrays of ArrayLists to hold the values of the input array during
        // sorting.
        // Each ArrayList in the arrays will hold the values with a particular radix
        // during a pass.
        List<Float>[] Array1 = createArrayLists();
        List<Float>[] Array2 = createArrayLists();

        // First pass: Sort the values of the input array based on their least
        // significant radix (the digit
        // to the right of the decimal point) and add them to the corresponding
        // ArrayList in Array1.
        for (int i = 0; i < arr.length; i++) {
            int index = (int) ((arr[i] * exponent) % 10);
            float value = arr[i];
            Array1[index].add(value);
        }

        // Multiply the radix by 10 to move to the next digit to the left of the decimal
        // point.
        exponent *= 10;

        // Repeat the process until all significant digits have been sorted
        for (; maxValue / exponent > 0; exponent *= 10) {

            // For each pass, empty each ArrayList in Array1 into the appropriate ArrayList in Array2
            // based on the next digit to the left of the decimal point.
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < Array1[i].size(); j++) {
                    float Array1Value = Array1[i].get(j);
                    int index1 = (int) ((Array1Value / exponent) % 10);
                    Array2[index1].add(Array1Value);
                }
                Array1[i].clear();
            }
            // Multiply the radix by 10 to move to the next digit to the left of the decimal point.
            exponent *= 10;
        
            // For each pass, empty each ArrayList in Array2 into the appropriate ArrayList in Array1
            // based on the next digit to the left of the decimal point.
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < Array2[i].size(); j++) {
                    float Array2Value = Array2[i].get(j);
                    int index2 = (int) ((Array2Value / exponent) % 10);
                    Array1[index2].add(Array2Value);
                }
                Array2[i].clear();
            }
        }

        // Copy the sorted values from Array1 into a new float array and return it.
        float[] sortedArray = new float[arr.length];
        int arrCount = 0;
        for (int i = 0; i < 10; i++) {
            while (!Array1[i].isEmpty()) {
                float sortedArrayValue = Array1[i].remove(0);
                sortedArray[arrCount] = sortedArrayValue;
                arrCount++;
            }
        }
        return sortedArray;
    }

    // A method that finds the maximum value in an array of floats
    private static float getMaxValue(float[] arr) {
        float maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        return maxValue;
    }

    // This method creates an array of 10 empty ArrayLists of Float radix file
    private static List<Float>[] createArrayLists() {
        List<Float>[] arrays = new ArrayList[10];
        for (int i = 0; i < 10; i++) {
            arrays[i] = new ArrayList<>();
        }
        return arrays;
    }

    // This method prints the elements of an array of floats
    static void print(float arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // This is the main method where the program starts executing
    public static void main(String[] args) {
        // Create an unsorted array of floats
        float[] arr = { 0.0014f, 0.0013f, 0.008f, 2.56707f, 0.2403f, 1.102f, 0.606f, 0.016f, 0.304f, 0.807f, 0.707f,
                0.506f, 0.809f, 101.809f, 100.809f };
        System.out.print("Original Array: ");
        print(arr);
        // Sort the array using Floating Radix Sort algorithm and store the sorted array
        // in sortedArr
        float[] sortedArray = FloatRadixsort(arr);
        System.out.print("Sorted Array: ");
        print(sortedArray);
        System.out.print("\n");

        // create 2 arrays which have random positive integer number inside the array
        float[] arr2 = new float[10];
        float[] arr3 = new float[10];
        Random rand = new Random();
        
        // assign a random created float number to the array
        for (int i = 0; i < arr2.length; i++) {
           arr2[i] = rand.nextFloat(10000);
           arr3[i] = rand.nextFloat(1000);
        }

        // Sort the array using Float Radix Sort algorithm and store the sorted array
        // in sortedArr2 and sortedArr3
        float[] sortedArray2 = FloatRadixsort(arr2);
        float[] sortedArray3 = FloatRadixsort(arr3);

        // print the sorted Random Array 2
        System.out.print("\nOriginal Random Array 1: ");
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
