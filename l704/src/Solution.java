/**
 * 二分算法
 * 需要注意while的判断条件 判断条件均是区间内没有元素
 * 区间[] [) ()的选择
 */
public class Solution {
    public int search(int[] nums, int target) {
        int ans = lowerBound(nums, target);
        if (ans == nums.length || nums[ans] != target) {
            return -1;
        }
        return ans;
    }

    // [)
    private int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.search(new int[]{-1,0,3,5,9,12}, 9));
    }
}