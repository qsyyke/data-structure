/**
 * @author qsyyke
 * @date Created in 2022/7/9 18:21
 */


public class DemoTest {
    public static void main(String[] args) {
        System.out.println(13 & 1);
        int[] arr = {6,5,5};
        System.out.println(majorityElement(arr));
    }
    public static int majorityElement(int[] nums) {
        int halfCount = (nums.length / 2 + 1);
        int tempNum = 0;
        for(int i = 0; i < nums.length;  i++) {
            int count = 0;
            for(int j = 0; j < nums.length; j++) {
                if(nums[i] == nums[j]) {
                    count++;
                }
            }
            if (count >= halfCount) {
                tempNum = nums[i];
                break;
            }
        }
        return tempNum;
    }
}


class Solution {
    public static void main(String[] args) {
        //int[][] arr = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int[][] arr = {{-5}};
        System.out.println(findNumberIn2DArray(arr, -5));
    }
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        for (int[] ints : matrix) {
            if (ints.length == 0) {
                break;
            }
            if (ints[ints.length - 1] >= target) {
                int low = 0;
                int high = ints.length - 1;
                int mid;
                while (low <= high) {
                    mid = (low + high) / 2;
                    if (target < ints[mid]) {
                        high = mid - 1;
                    } else if (target > ints[mid]) {
                        low = mid + 1;
                    } else if (target == ints[mid]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}