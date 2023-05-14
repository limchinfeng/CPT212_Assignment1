import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CounterFloatingRadix {

    static int counter=0;
    public static float[] FloatRadixsort(float[] arr) {

        // return if number is 1
        counter++;
        if(arr.length == 1){
            counter++;
            return arr;
        }
        
        // Find the maximum value in the input array
        float maxValue = getMaxValue(arr);
        counter+=2;

        // Initialize the exponent to be a very small value
        double exponent = 0.000000000001;
        counter++;

        // Create two arrays of ArrayLists to hold the values of the input array during
        // sorting.
        // Each ArrayList in the arrays will hold the values with a particular radix
        // during a pass.
        List<Float>[] Array1 = createArrayLists();
        counter+=2;
        List<Float>[] Array2 = createArrayLists();
        counter+=2;
        
        // First pass: Sort the values of the input array based on their least significant digit
        // and add them to the corresponding ArrayList in Array1.
        counter++;// 1 value assignment i=0
        for (int i = 0; i < arr.length; i++) {
            int index = (int) ((arr[i] * exponent) % 10); 
            float value = arr[i]; 
            Array1[index].add(value); 
            counter+=13;
        }

        // Multiply the radix by 10 to move to the next digit to the left of the decimal
        // point.
        exponent *= 10;
        counter+=2;
        // Repeat the process until all significant digits have been sorted
        for (; maxValue / exponent > 0; exponent *= 10) {

            // For each pass, empty each ArrayList in Array1 into the appropriate ArrayList in Array2
            // based on the next digit to the left of the decimal point.
            counter++;// 1 value assignment i=0
            for (int i = 0; i < 10; i++) {
                counter++;// 1 value assignment j=0
                for (int j = 0; j < Array1[i].size(); j++) { 
                    float Array1Value = Array1[i].get(j);
                    int index1 = (int) ((Array1Value / exponent) % 10);
                    Array2[index1].add(Array1Value);
                    counter+=14;
                }
                Array1[i].clear();
                counter+=5;
            }
            // Multiply the radix by 10 to move to the next digit to the left of the decimal point.
            exponent *= 10;
            counter+=2;
            // For each pass, empty each ArrayList in Array2 into the appropriate ArrayList in Array1
            // based on the next digit to the left of the decimal point.
            counter++;// 1 value assignment i=0
            for (int i = 0; i < 10; i++) {

                counter++;// 1 value assignment j=0
                for (int j = 0; j < Array2[i].size(); j++) {
                    float Array2Value = Array2[i].get(j);
                    int index2 = (int) ((Array2Value / exponent) % 10);
                    Array1[index2].add(Array2Value); 
                    counter+=14;
                }
                Array2[i].clear();
                counter+=5;
            }
            counter+=4;
        }

        // Copy the sorted values from Array1 into a new float array and return it.
        float[] sortedArray = new float[arr.length];
        counter+=3;

        int arrCount = 0;
        counter++;

        counter++;// 1 value assignment i=0
        for (int i = 0; i < 10; i++) {

            while (!Array1[i].isEmpty()) {
                float sortedArrayValue = Array1[i].remove(0);
                sortedArray[arrCount] = sortedArrayValue;
                arrCount++; 
                counter+=10;
            }
            counter+=3; // for loop
        }
        counter++;
        return sortedArray;
    }

    // A method that finds the maximum value in an array of floats
    private static float getMaxValue(float[] arr) {
        float maxValue = arr[0];
        counter+=2;

        counter++;// 1 value assignment i=1
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
                counter+=4;
            }
            counter+=4;
        }
        counter++;
        return maxValue;
    }

    // This method creates an array of 10 empty ArrayLists of Floatradix file
    private static List<Float>[] createArrayLists() {
        List<Float>[] arrays = new ArrayList[10];
        counter+=2;

        counter++;// 1 value assignment i=0
        for (int i = 0; i < 10; i++) {
            arrays[i] = new ArrayList<>();
            counter+=6;
        }

        counter++;
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

        int [] n = {1,10,100,1000,10000,20000, 40000, 60000, 80000, 100000};

        for(int i=0; i<n.length; i++){

            int number = n[i];
            
            float ar[]=new float[number];

            Random rand = new Random();
            for(int j=0; j<n[i] ;j++){
                ar[j] = rand.nextFloat(n[i]);
            }
            
            float[] sortedArrCounter= FloatRadixsort(ar);
            System.out.println("n = "+n[i] +" , counter = "+counter);
            counter=0;
        }
    }
}