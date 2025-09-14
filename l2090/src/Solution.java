import java.util.Arrays;

public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        long sum = 0L;
        int nk = k * 2 + 1;
        for (int i = 0; i < n; i++) {
            // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
            sum += nums[i];
            if (i + 1 < nk) continue;

            // 更新：更新答案。一般是更新最大值/最小值。
            res[i - k] = (int) (sum / nk);

            // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。
            sum -= nums[i + 1 - nk];
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i : solution.getAverages(new int[]{10000, 10000, 10000, 10000, 10000, 10000}, 2)) {
            System.out.println(i);
        }
    }
}