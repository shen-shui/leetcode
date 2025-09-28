import java.util.Arrays;

class Solution {
    /**
     *  二分求最大
     */
    public int maxValue(int n, int index, int maxSum) {
        int[] nums = new int[n];
        int left = 0;
        int right = maxSum - n + 2;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check(nums, mid, index)) {
                left = mid;
            } else {
                right = mid;
            }
        }
    }

    private boolean check(int[] nums, int mid, int index) {
        // 向前推，不包含mid
        // 末项 mid - 1
        // 首项 index + 1 >= mid  -> 1
        //      index + 1 < mid -> mid - index
        //
        // 向后推
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}