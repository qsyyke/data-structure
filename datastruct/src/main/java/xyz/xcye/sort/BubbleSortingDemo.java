package xyz.xcye.sort;

import java.util.Arrays;

/**
 * @author qsyyke
 * @date Created in 2022/7/9 09:23
 */


public class BubbleSortingDemo {

    public static void main(String[] args) {
        int[] arr = {3,9,-1,10,-2};
        new BubbleSorting().bubbleSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}

class BubbleSorting {
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int originNum = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = originNum;
                }
            }
        }
    }
}
