import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 二分算法
 * 需要注意while的判断条件 判断条件均是区间内没有元素
 * 区间[] [) ()的选择
 */
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int start = lowerBound(nums, target);
//        int start1 = lowerBound1(nums, target);
//        int start2 = lowerBound2(nums, target);

        if (start == nums.length || nums[start] != target) return new int[]{-1, -1};

        int end = lowerBound(nums, target + 1) - 1;
//        int end1 = lowerBound1(nums, target + 1) - 1;
//        int end2 = lowerBound2(nums, target + 1) - 1;
//        System.out.println(start + " " + start1 + " " + start2);
//        System.out.println(end + " " + end1 + " " + end2);

        return new int[]{start, end};
    }

    // 二分算法:左闭右闭
    // []的while条件为left < right
    // left - 1 左边均为 < target
    // right + 1 右边均为 > target
    // 当left > right 区间内最多包含一个元素，该元素满足题意，不需要再次移动left或者right
    // 由于有可能是 left > right 所以可以返回 left
    private int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // 二分算法:左闭右开
    // [)的while条件为left < right
    // left - 1 左边均为 < target
    // right 右边均为 > target
    // 当left >= right 区间内不包含元素，可以直接返回答案
    // 由于是左闭右开，所以答案可以返回 left 或 right
    private int lowerBound1(int[] nums, int target) {
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
        return left; // right
    }

    // 二分算法:左开右开
    // ()的while条件为left + 1 < right
    // left 左边均为 < target
    // right 右边均为 > target
    // 当left + 1 >= right 区间内不包含元素，可以直接返回答案
    // 由于当nums[mid] = target时会把r 移动到 mid ，所以答案可以返回right
    private int lowerBound2(int[] nums, int target) {
        int left = -1;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid; // 开区间，排除不掉mid元素
            } else {
                right = mid;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.searchRange(new int[]{1}, 1)));
    }
}