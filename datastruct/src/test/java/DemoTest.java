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
