import java.util.Arrays;

public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length();

        int count = 0, res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
            char c = blocks.charAt(i);
            if (c == 'W') count++;
            if (i + 1 < k) continue;

            // 更新：更新答案。一般是更新最大值/最小值。
            res = Math.min(count, res);

            // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。
            c = blocks.charAt(i + 1 - k);
            if (c == 'W') count--;
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minimumRecolors("WBBWWBBWBW", 7));
    }
}