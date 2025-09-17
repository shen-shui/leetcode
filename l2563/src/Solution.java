import java.util.Arrays;

/**
 * 二分算法
 * 需要注意while的判断条件 判断条件均是区间内没有元素
 * 区间[] [) ()的选择
 */
public class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        long ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int l = lowerBound(nums, i, lower - nums[i]);
            int r = lowerBound(nums, i, upper - nums[i] + 1);
            ans += r - l;
        }
        return ans;
    }

    // ()
    private int lowerBound(int[] nums, int left, int target) {
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
        System.out.println(solution.countFairPairs(new int[]{0,1,7,4,4,5}, 3, 6));
    }
}