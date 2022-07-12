package xyz.xcye.sort;

import java.util.Arrays;

/**
 * @author qsyyke
 * @date Created in 2022/7/9 16:38
 */


public class QuickSortDemo {
    public static void main(String[] args) {
        int[] arr = {12,0,-1,50,7};

        new QuickSort().quickSort(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(System.out::println);

    }
}

class QuickSort {
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot;
            // 取第low个作为枢轴值
            pivot = arr[low];

            while (low < high) {
                while (low < high && arr[high] >= pivot) {
                    // 找出枢轴值右边小于枢轴值的值
                    high--;
                }

                // 找到了小于枢轴值的值 将此arr[low]和arr[high]进行交换
                swap(arr, low, high);

                // 找出枢轴值左边比枢轴值大的数
                while (low < high && arr[low] <= pivot) {
                    low++;
                }

                // 在枢轴值左边找到一个比枢轴值大的数，将他们讲个交换顺序，如果是第一次，
                // 不能保证该枢轴值右边的一定都比枢轴值大
                swap(arr,low, high);

                quickSort(arr, 0, low - 1);
                quickSort(arr, low + 1, high);
            }
        }
    }

    /**
     * 将arr[low]和arr[high]进行交换
     * @param arr
     * @param low
     * @param high
     */
    private void swap(int[] arr, int low, int high) {
        int tempNum = arr[high];
        arr[high] = arr[low];
        arr[low] = tempNum;
    }
}
