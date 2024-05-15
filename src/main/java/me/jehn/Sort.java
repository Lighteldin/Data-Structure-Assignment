package me.jehn;

import java.math.BigDecimal;
import java.util.Random;

public class Sort {
    //GENERATING 9 ARRAYS; 3 SORTED, 3 INVERTED, 3 RANDOM
    private final int SIZE1D = 10000;
    private int[] sortedArray1 = sortedArray(SIZE1D), sortedArray2 = sortedArray(SIZE1D), sortedArray3 = sortedArray(SIZE1D);
    private int[] invertedArray1 = invertedArray(SIZE1D), invertedArray2 = invertedArray(SIZE1D), invertedArray3 = invertedArray(SIZE1D);
    private int[] randomArray1 = randomArray(SIZE1D), randomArray2 = randomArray(SIZE1D), randomArray3 = randomArray(SIZE1D);

    //EXTERNAL: THESE ATTRIBUTES ARE TO HELP WITH THE GUI AND SIMULATION
    private final int SIZE2D = 3;
    double[][] q2DArr = new double[SIZE2D][SIZE2D], c2DArr = new double[SIZE2D][SIZE2D], b2DArr = new double[SIZE2D][SIZE2D];
    private double qComparison, qInterchange, cComparison, cInterchange, bComparison, bInterchange;

    public Sort() {
        qSortDetails(sortedArray1,invertedArray1,randomArray1);
        cSortDetails(sortedArray2,invertedArray2,randomArray2);
        bSortDetails(sortedArray3,invertedArray3,randomArray3);
    }
    //SORTING ALGORITHM FUNCTIONS:
    //QUICK SORT:
    public void quickSort(int[] arr, int low, int high){
        if (low < high){
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }
    private int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            qComparison++;
            if (arr[j]<=pivot){
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, high);
        return i+1;
    }
    private void swap(int[] arr, int i, int j) {
        qInterchange++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    //COUNTING SORT
    public void countingSort(int[] arr){
        int max = getMax(arr);
        int[] count = new int[max+1];
        for (int num : arr){
            count[num]++;
        }
        for (int i=1 ; i<=max ; i++){
            count[i] += count[i-1];
        }
        int[] output = new int[arr.length];
        for (int i=arr.length-1; i>=0 ;i--  ){
            output[count[arr[i]]-1] = arr[i];
            count[arr[i]]--;
        }
        for (int i=0; i<arr.length;i++){
            arr[i]=output[i];
        }
    }
    private int getMax(int[] arr){
        int max = -1 ;
        for (int i : arr){
            if (i > max) max=i;
        }
        return max;
    }
    //BUBBLE SORT
    public void bubbleSort(int arr[]) {
        int interchanges;
        for (int pass = 0; pass < arr.length; pass++ ) {
            interchanges=0;
            for (int i = 0; i < arr.length - 1; i++) {
                bComparison++;
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    bInterchange++;
                    interchanges++;
                }
            }
            if (interchanges==0) return;
        }
    }


    //GENERATING ARRAYS
    public static int[] sortedArray(int size){
        int[] arr = new int[size];
        for (int i=0 ; i<size ; i++){
            arr[i] = i+1 ;
        }
        return arr;
    }
    public static int[] invertedArray(int size){
        int[] arr = new int[size];
        int j = 0;
        for (int i=size ; i>=1 ; i--){
            arr[j] = i;
            j++;
        }
        return arr;
    }
    public static int[] randomArray(int size){
        int[] arr = sortedArray(size);
        Random rand = new Random();

        for (int i = 0; i < size ; i++) {
            int randomIndexToSwap = rand.nextInt(arr.length);
            int temp = arr[randomIndexToSwap];
            arr[randomIndexToSwap] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }


    //EXTERNAL METHODS TO HELP IN GUI:
    private void qSortDetails(int[] sortedArr, int[] reversedArr, int[] randomArr){
        double x;
        //SORTED ARRAY
        resetVar();
        x = measureExecutionTime(() -> quickSort(sortedArr, 0, sortedArr.length-1));
        update2DArray(q2DArr, x, qComparison, qInterchange, 's');

        //REVERSED ARRAY
        resetVar();
        x = measureExecutionTime(() -> quickSort(reversedArr, 0, reversedArr.length-1));
        update2DArray(q2DArr, x, qComparison, qInterchange, 'i');

        //RANDOM ARRAY
        resetVar();
        x = measureExecutionTime(() -> quickSort(randomArr, 0, randomArr.length-1));
        update2DArray(q2DArr, x, qComparison, qInterchange, 'r');
    }
    private void cSortDetails(int[] sortedArr, int[] reversedArr, int[] randomArr){
        double x;
        //SORTED ARRAY
        resetVar();
        x = measureExecutionTime(() -> countingSort(sortedArr));
        update2DArray(c2DArr, x, cComparison, cInterchange, 's');

        //REVERSED ARRAY
        resetVar();
        x = measureExecutionTime(() -> countingSort(reversedArr));
        update2DArray(c2DArr, x, cComparison, cInterchange, 'i');

        //RANDOM ARRAY
        resetVar();
        x = measureExecutionTime(() -> countingSort(randomArr));
        update2DArray(c2DArr, x, cComparison, cInterchange, 'r');
    }
    private void bSortDetails(int[] sortedArr, int[] reversedArr, int[] randomArr){
        double x;
        //SORTED ARRAY
        resetVar();
        x = measureExecutionTime(() -> bubbleSort(sortedArr));
        update2DArray(b2DArr, x, bComparison, bInterchange, 's');

        //REVERSED ARRAY
        resetVar();
        x = measureExecutionTime(() -> bubbleSort(reversedArr));
        update2DArray(b2DArr, x, bComparison, bInterchange, 'i');

        //RANDOM ARRAY
        resetVar();
        x = measureExecutionTime(() -> bubbleSort(randomArr));
        update2DArray(b2DArr, x, bComparison, bInterchange, 'r');
    }
    private void resetVar(){
        qComparison = 0;
        qInterchange = 0;
        cComparison = 0;
        cInterchange = 0;
        bComparison = 0;
        bInterchange = 0;
    }
    private double measureExecutionTime(Runnable function) {
        long startTime = System.nanoTime();

        // Call the function
        function.run();

        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000f;
    }
    private void printArray(int[] arr){
        for (int x : arr)
            System.out.print(x+" ");
        System.out.println();
    }
    private void print2DArray(double[][]arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) System.out.print(BigDecimal.valueOf(Math.round(arr[i][j] * 100.0) / 100.0).toPlainString()+" ");
            System.out.println();
        }
        System.out.println();
    }
    private void update2DArray(double[][]arr2D, double time, double comparison, double interchange, char sortType){
        switch(sortType){
            case 's':
            case 'S':
                arr2D[0][0] = time;
                arr2D[0][1] = comparison;
                arr2D[0][2] = interchange;
                break;
            case 'i':
            case 'I':
                arr2D[1][0] = time;
                arr2D[1][1] = comparison;
                arr2D[1][2] = interchange;
                break;
            case 'r':
            case 'R':
                arr2D[2][0] = time;
                arr2D[2][1] = comparison;
                arr2D[2][2] = interchange;
                break;
        }
    }
    public static double get2DArrayElemnt(double[][] arr2D,int index){
        switch(index){
            case 1:
                return arr2D[0][0];
            case 2:
                return arr2D[0][1];
            case 3:
                return arr2D[0][2];
            case 4:
                return arr2D[1][0];
            case 5:
                return arr2D[1][1];
            case 6:
                return arr2D[1][2];
            case 7:
                return arr2D[2][0];
            case 8:
                return arr2D[2][1];
            case 9:
                return arr2D[2][2];
            default:
                return 0;
        }
    }
}
