import java.util.Arrays;

public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    // 灵神解答
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        final int SIZE = 50;
        int[] cnt = new int[2 * SIZE + 1];
        for (int i = 0; i < k - 1; i++) {
            cnt[nums[i] + SIZE]++;
        }

        for (int i = k - 1; i < n; i++) {
            // 入
            cnt[nums[i] + SIZE]++;

            // 更新
            int left = x;
            for (int j = 0; j < SIZE; j++) {
                left -= cnt[j];
                if (left <= 0) {
                    res[i + 1 - k] = j - SIZE;
                    break;
                }
            }

            // 出
            cnt[nums[i + 1 - k] + SIZE]--;
        }

        return res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.getSubarrayBeauty(new int[]{1, -1, -3, -2, 3}, 3, 2)));
    }
}