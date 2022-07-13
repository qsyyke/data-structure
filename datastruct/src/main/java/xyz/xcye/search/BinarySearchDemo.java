package xyz.xcye.search;

/**
 * @author qsyyke
 * @date Created in 2022/7/12 16:50
 */


public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] arr = {0, 1,16,24,35,47,59,62,73,88,99};
        System.out.println(binarySearch(arr, 99));
        System.out.println(insertSearch(arr, 99));
    }

    /**
     * 使用折半查找返回key对应的下标
     * @param arr
     * @param key
     * @return
     */
    private static int binarySearch(int[] arr, int key) {
        int low,high, mid;
        low = 0;
        // 因为是下标，所以需要减一
        high = arr.length - 1;

        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] > key) {
                // 往左进行查找
                high = mid - 1;
            }else if (arr[mid] < key) {
                // 往右进行查找
                low = mid + 1;
            }else {
                return mid;
            }
        }
        return 0;
    }

    /**
     * 插值查找，改动mid部分
     * @param arr
     * @param key
     * @return
     */
    private static int insertSearch(int[] arr, int key) {
        int low,high, mid;
        low = 0;
        // 因为是下标，所以需要减一
        high = arr.length - 1;

        while (low <= high) {
            // 改动部分n
            mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low]);
            if (arr[mid] > key) {
                // 往左进行查找
                high = mid - 1;
            }else if (arr[mid] < key) {
                // 往右进行查找
                low = mid + 1;
            }else {
                return mid;
            }
        }
        return 0;
    }
}
