package xyz.xcye.sort;

import java.util.Arrays;

/**
 * @author qsyyke
 * @date Created in 2022/7/9 11:06
 */


public class SelectSortingDemo {
    public static void main(String[] args) {
        int[] arr = {3,9,-1,10,-2};

        new SelectSorting().selectSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}

class SelectSorting {
    public void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int smallestNum = arr[i];
            int selectIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < smallestNum) {
                    smallestNum = arr[j];
                    selectIndex = j;
                }
            }

            // 交换
            arr[selectIndex] = arr[i];
            arr[i] = smallestNum;
        }
    }
}
