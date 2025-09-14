/**
 * 二分算法
 * 需要注意while的判断条件 判断条件均是区间内没有元素
 * 区间[] [) ()的选择
 */
public class Solution {
    public int maximumCount(int[] nums) {
        int pos = nums.length - lowerBound(nums, 1);
        int neg = lowerBound(nums, 0);
        return Math.max(pos, neg);
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
        System.out.println(solution.maximumCount(new int[]{-3,-2,-1,0,0,1,2}));
    }
}
