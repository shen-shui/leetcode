import java.util.*;

class Solution {
    /**
     *  二分求最大
     */
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = -1;
        int right = n / 2 + 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check(nums, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return 2 * left;
    }

    private boolean check(int[] nums, int mid) {
        if (mid == 0) return true;
        // 有mid对，那么就去前mid个找对应的j，如果找不到就意味着没有
        int left = 0;
        int right = mid;

        while (right < nums.length) {
            if (2 * nums[left] <= nums[right]) {
                left++;
                right++;
                if (left == mid) {
                    return true;
                }
            } else {
                right++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxNumOfMarkedIndices(new int[]{7,6,8}));
    }
}