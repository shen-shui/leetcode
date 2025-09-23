import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    /**
     * 优化：可以直接开始找差值的时候就开始比较最大差值，这样既能计算差值，又能比较该改变那个链nums1
     */
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int mod = (int)1e9 + 7;
        int n = nums1.length;
        // 复制一个数组，用来排序，后续用来找到底是哪个值离nums2[i]差值最小，就改变该值
        int[] sorted = nums1.clone();
        Arrays.sort(sorted);
        // 遍历nums1，记录差值，顺便找改变哪个能让差值改变最大
        long diffs = 0;
        int maxn = 0;
        for (int i = 0; i < n; i++) {
            // 先把差值加上
            int diff = Math.abs(nums1[i] - nums2[i]);
            diffs += diff;
            // 开始二分，寻找最小差值
            int idx = lowerBound(sorted, nums2[i]);
            // 需要比较 >= nums2[i] 和 < nums2[i] 的值来确定到底用哪个差值最小
            // 如果idx没有到达最右边，跟右侧的比较
            if (idx < n) {
                maxn = Math.max(maxn, diff - (sorted[idx] - nums2[i]));
            }
            // 如果idx没有达到最左边，跟左侧的比较
            if (idx > 0) {
                maxn = Math.max(maxn, diff - (nums2[i] - sorted[idx - 1]));
            }
            // 可以替换掉下面繁杂的判断
//            if (idx == 0) {
//                nextDiff = Math.abs(nums2[i] - diffs[0]);
//            } else if (idx == n) {
//                nextDiff = Math.abs(nums2[i] - diffs[n - 1]);
//                idx = n - 1;
//            } else {
//                if (Math.abs(nums2[i] - diffs[idx - 1]) < Math.abs(nums2[i] - diffs[idx])) {
//                    nextDiff = Math.abs(nums2[i] - diffs[idx - 1]);
//                    idx = idx - 1;
//                } else {
//                    nextDiff = Math.abs(nums2[i] - diffs[idx]);
//                }
//            }
//            if (curDiff - nextDiff > max) {
//                max = curDiff - nextDiff;
//                index = i;
//                value = diffs[idx];
//            }
        }
        return (int) ((diffs - maxn) % mod);
    }



    /**
     *  最蠢的方法，遍历整个nums2数组，找到改变最大的差值，然后记录该值，最后遍历返回答案
     */
    public int minAbsoluteSumDiff1(int[] nums1, int[] nums2) {
        int mod = (int)1e9+7;
        int n = nums1.length;
        int[] diffs = nums1.clone();
        Arrays.sort(diffs);
        int max = -1;
        int index = 0;
        int value = 0;
        for (int i = 0; i < n; i++) {
            int idx = lowerBound(diffs, nums2[i]);
            int curDiff = Math.abs(nums2[i] - nums1[i]);
            int nextDiff = 0;
            if (idx == 0) {
                nextDiff = Math.abs(nums2[i] - diffs[0]);
            } else if (idx == n) {
                nextDiff = Math.abs(nums2[i] - diffs[n - 1]);
                idx = n - 1;
            } else {
                if (Math.abs(nums2[i] - diffs[idx - 1]) < Math.abs(nums2[i] - diffs[idx])) {
                    nextDiff = Math.abs(nums2[i] - diffs[idx - 1]);
                    idx = idx - 1;
                } else {
                    nextDiff = Math.abs(nums2[i] - diffs[idx]);
                }
            }
            if (curDiff - nextDiff > max) {
                max = curDiff - nextDiff;
                index = i;
                value = diffs[idx];
            }
        }
        nums1[index] = value;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.abs(nums1[i] - nums2[i]);
        }
        return (int) (sum % mod);
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

        System.out.println(solution.minAbsoluteSumDiff1(new int[]{2,4,6,8,10}, new int[]{2,4,6,8,10}));
    }
}