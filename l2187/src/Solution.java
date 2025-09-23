import java.util.Arrays;
import java.util.OptionalInt;

class Solution {
    /**
     * 二分答案 divide
     */
    // ()
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 0;
        int right = Arrays.stream(nums).max().getAsInt();

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (!check(nums, mid, threshold)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private boolean check(int[] nums, int divide, int threshold) {
        int sum = 0;
        for (int num : nums) {
            sum += (num + divide - 1) / divide;
            if (sum > threshold) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.smallestDivisor(new int[]{1,2,5,9}, 6));
    }
}