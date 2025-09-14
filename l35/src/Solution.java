import java.util.Arrays;

/**
 * 二分算法
 * 需要注意while的判断条件 判断条件均是区间内没有元素
 * 区间[] [) ()的选择
 */
public class Solution {
    public int searchInsert(int[] nums, int target) {
        return lowerBound(nums, target);
    }

    // ()
    private int lowerBound(int[] nums, int target) {
        int left = -1;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return right;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.searchInsert(new int[]{1,3,5,6}, 5));
    }
}