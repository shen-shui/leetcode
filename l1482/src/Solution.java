import java.util.Arrays;

class Solution {
    /**
     * 二分答案 t
     */
    // ()
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (n < (long)m * k) return -1;
        int left = Arrays.stream(bloomDay).min().getAsInt() - 1;
        int right = Arrays.stream(bloomDay).max().getAsInt() + 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (!check(bloomDay, mid, m, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private boolean check(int[] bloomDay, int mid, int m, int k) {
        // 滑动窗口（查看是否满足m朵花的要求）
        int left = 0;
        int right = 0;
        int cnt = 0;
        int res = 0;
        while (right < bloomDay.length) {
            // 必须是连续的，又一个不满足，cnt立马归0
            if (bloomDay[right] <= mid) {
                cnt++;
            } else {
                cnt = 0;
            }

            // 更新答案
            // 如果有一组满足了，那么左指针直接跳到右指针位置
            if (cnt == k) {
                res++;
                if (res >= m) return true;
                right++;
                left = right;
                cnt = 0;
                continue;
            }

            if (right - left + 1 > k) {
                left++;
            }
            right++;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minDays(new int[]{7,7,7,7,12,7,7}, 2, 3));
    }
}