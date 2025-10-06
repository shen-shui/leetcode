import java.util.Arrays;

class Solution {
    /**
     *  数位dp初尝试
     *  遇到还需要再做，跟着题解做
     */
    public int countSpecialNumbers(int n) {
        // 换成字符数组好按位运算
        char[] s = Integer.toString(n).toCharArray();
        // 记录如果某一位没限制，那么后面位置有多少中情况可以选
        // 避免重复计算
        int[][] memo = new int[s.length][1 << 10];
        for (int[] row : memo) Arrays.fill(row, -1);
        // 为什么是true false
        return dfs(0, 0, true, false, s, memo);
    }

    /**
     * 数位dp函数模版
     * @param i 现在是字符串的第i位数
     * @param mask 用来判断每个数位是否各不相同（位运算与集合论）
     * @param isLimit 判断是否是上一个位数的最大值，如果是最大值，那么下一位就会被限制
     * @param isNum 判断最开始的数位是否为0，为0的话不能加入mask，不然会和后面的0冲突
     */
    private int dfs(int i, int mask, boolean isLimit, boolean isNum, char[] s, int[][] memo) {
        if (i == s.length) return isNum ? 1 : 0;
        if (!isLimit && isNum && memo[i][mask] != -1) {
            return memo[i][mask];
        }
        int res = 0;
        if (!isNum) { // 跳过当前数位
            res = dfs(i + 1, mask, false, false, s, memo);
        }
        // 看 isLimit 判断该位置最大能填多少  true:s[i]  false:9
        int up = isLimit ? s[i] - '0' : 9;
        // 枚举要填入的数字d
        // 如果前面位置是0，那么要从1开始
        for (int d = isNum ? 0 : 1; d <= up; d++) {
            // mask >> d & 1 取mask的第d位来和1做与操作，如果结果是0，就说明mask没有该数
            if ((mask >> d & 1) == 0) {
                res += dfs(i + 1, mask | (1 << d), isLimit && d == up, true, s, memo);
            }
        }
        if (!isLimit && isNum) {
            // 记录某一位没限制，那么后面位置有多少中情况可以选的个数，避免重复运算
            memo[i][mask] = res;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countSpecialNumbers(20));
    }
}