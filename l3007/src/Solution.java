import java.util.Arrays;

class Solution {
    /**
     *  数位dp + 二分
     */
    public long findMaximumNumber(long k, int x) {
        this.x = x;
        long left = 0;
        // 上界怎么设置？
        long right = (k + 1) << x;
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (check(mid, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private long num;
    private long x;
    private long memo[][];

    private boolean check(long mid, long k) {
        this.num = mid;
        // 计算mid的二进制数有多少位
        // Long.numberOfLeadingZeros(mid)计算mid二进制数有多少前导0
        int m = 64 - Long.numberOfLeadingZeros(mid);
        memo = new long[m][m + 1];
        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }
        long ans = dfs(m, 0, true);
        return ans <= k;
    }

    /**
     * 数位dp函数模版
     * @param i 现在是num的第i位二进制数
     * @param mask 用来计算1的个数
     * @param isLimit 判断是否是上一个位数的最大值，如果是最大值，那么下一位就会被限制
     */
    private long dfs(int i, int mask, boolean isLimit) {
        // 如果算到头了，返回mask
        if (i <= 0) return mask;
        // 如果之前计算过了，就不重复计算了
        if (!isLimit && memo[i][mask] != -1) {
            return memo[i][mask];
        }

        long res = 0;
        int up = isLimit ? (int)((num >> (i - 1)) & 1) : 1;
        for (int d = 0; d <= up; d++) {
            res += dfs(i - 1, (d == 1 && i % x == 0) ? mask + 1 : mask, isLimit && d == up);
        }

        if (!isLimit && memo[i][mask] == -1) {
            memo[i][mask] = res;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMaximumNumber(7, 2));
    }
}