import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        long sum = 0;
        for (int i : cardPoints) sum += i;
        if (n == k) return (int) sum;

        long sub = 0, res = 0;
        int m = n - k;
        for (int i = 0; i < n; i++) {
            sub += cardPoints[i];
            if (i + 1 < m) continue;

            res = Math.max(res, sum - sub);

            sub -= cardPoints[i + 1 - m];
        }
        return (int) res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxScore(new int[]{9,7,7,9,7,7,9}, 7));
    }
}