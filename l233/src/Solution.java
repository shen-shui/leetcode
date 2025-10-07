import java.util.Arrays;

class Solution {
    /**
     *  数位dp
     *  原始版：超时
     *  优化：考虑增加一个数据结构，记录没限制的情况下能加多少个
     */
    public int countDigitOne(int n) {
        char[] s = String.valueOf(n).toCharArray();
        // 创建几维数组来记忆化取决于dfs的参数
        // 现在创建两维数组就是为了记忆化 i 和 mask，固定isLimit所以不用记忆化
        // 递归的过程中会多次用到重复的 i 和 mask，记忆化会减少计算量
        int[][] memo = new int[s.length][s.length];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 0, true, s, memo);
    }

    /**
     * 数位dp函数模版
     * @param i 现在是字符串的第i位数
     * @param mask 用来计算1的个数
     * @param isLimit 判断是否是上一个位数的最大值，如果是最大值，那么下一位就会被限制
     */
    private int dfs(int i, int mask, boolean isLimit, char[] s, int[][] memo) {
        // 已经dfs完了，返回1的个数
        if (i == s.length) return mask;
        // 如果第n位没限制时可以加入多少个
        if (!isLimit && memo[i][mask] != -1) {
            return memo[i][mask];
        }
        // 如果该位置有限制
        int res = 0;
        int up = isLimit ? s[i] - '0' : 9;
        for (int d = 0; d <= up; d++) {
            res += dfs(i + 1, d == 1 ? mask + 1 : mask, isLimit && (d == up), s, memo);
        }

        if (!isLimit && memo[i][mask] == -1) {
            memo[i][mask] = res;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countDigitOne(824883294));
    }
}