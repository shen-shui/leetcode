import java.util.Arrays;

public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。


    // 空闲块1:startTime[0] - 0
    // 空闲块2:startTime[1] - endTime[0]
    // ...
    // 最后一个空闲块:eventTime - endTime[n - 1]
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] free = new int[n + 1];

        free[0] = startTime[0];
        for (int i = 1; i < n + 1; i++) {
            if (i == n) {
                free[i] = eventTime - endTime[i - 1];
            } else {
                free[i] = startTime[i] - endTime[i - 1];
            }
        }

        int m = k + 1;
        int sum = 0, res = 0;
        for (int i = 0; i < free.length; i++) {
            // 入
            sum += free[i];
            if (i + 1 < m) continue;

            // 更新
            res = Math.max(sum, res);

            // 出
            sum -= free[i + 1 - m];
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxFreeTime(5, 1, new int[]{1, 3}, new int[]{2, 5}));
    }
}