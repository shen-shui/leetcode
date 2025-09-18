import java.util.Arrays;

/**
 * 二分算法
 * 需要注意while的判断条件 判断条件均是区间内没有元素
 * 区间[] [) ()的选择
 */
public class Solution {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, (a, b) -> a[0] - b[0]);
        int[] ans = new int[queries.length];

        for (int i = 1; i < items.length; i++) {
            items[i][1] = Math.max(items[i-1][1], items[i][1]);
        }

        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];
            int index = lowerBound(items, query + 1) - 1;
            if (index == -1) {
                ans[i] = 0;
                continue;
            }
            ans[i] = items[index][1];
        }
        return ans;
    }

    // []
    private int lowerBound(int[][] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid][0] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] items = new int[][]{
            {10,1000}
        };
        System.out.println(Arrays.toString(solution.maximumBeauty(items, new int[]{1})));
    }
}