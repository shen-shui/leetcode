public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;

        double avg = 0, res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
            avg += (double) nums[i] / k;
            if (i < k - 1) continue;

            // 更新：更新答案。一般是更新最大值/最小值。
            res = Math.max(avg, res);

            // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。
            avg -= (double) nums[i - k + 1] / k;
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMaxAverage(new int[]{-1}, 1));
    }
}