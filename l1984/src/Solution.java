import java.util.Arrays;

public class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) return 0;
        int res = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = k - 1; i < n; i++) {
            res = Math.min(Math.abs(nums[i] - nums[i + 1 - k]), res);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minimumDifference(new int[]{9,4,1,7}, 2));
    }
}