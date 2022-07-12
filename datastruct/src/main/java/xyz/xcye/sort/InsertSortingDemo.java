package xyz.xcye.sort;

import java.util.Arrays;

/**
 * @author qsyyke
 * @date Created in 2022/7/9 11:47
 */


public class InsertSortingDemo {
    public static void main(String[] args) {
        int[] arr = {2,56,34,1,78,3};

        new InsertSorting().insertSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}

class InsertSorting {
    public void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];
            int insertIndex = i - 1;

            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                // 待插入的数比arr[insertIndex]还小，那么insertIndex后的数都将右移
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            arr[insertIndex + 1] = insertValue;

        }
    }
}