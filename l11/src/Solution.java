import java.util.Arrays;

public class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1;
        int res = 0;
        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int tempRes = h * (right - left);
            res = Math.max(tempRes, res);
            if (height[left] < height[right]) left++;
            else right--;
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}