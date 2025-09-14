public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    public int minSwaps(int[] nums) {
        int n = nums.length;
        int k = 0;
        for (int num : nums) {
            if (num == 1) k++;
        }
        if (k == 0) return 0;

        int count = 0, res = 0;
        for (int i = 0; i < k - 1; i++) {
            if (nums[i] == 1) count++;
        }

        for (int i = k - 1, j = 0; j < n; i++, j++) {
            // 入
            if (nums[i % n] == 1) count++;

            // 更新答案
            res = Math.max(count, res);

            // 出
            if (nums[(i + n + 1 - k) % n] == 1) count--;
        }

        return k - res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minSwaps(new int[]{0,1,1,1,0,0,1,1,0}));
    }
}