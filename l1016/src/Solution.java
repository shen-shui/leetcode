import java.util.HashSet;
import java.util.Set;

public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    // 暴力解法
    public boolean queryString(String s, int n) {
        for (int i = 1; i <= n; i++) {
            if (!s.contains(Integer.toBinaryString(i))) {
                return false;
            }
        }
        return true;
    }

    // 进阶解法
    public boolean queryString1(String s, int n) {
        Set<Integer> set = new HashSet<>();
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {// 外层循环:表示从第几个二进制数来计算字串
            int x = c[i] - '0';// 取0或1
            if (x == 0) continue;
            for (int j = i + 1; x <= n; j++) {
                set.add(x);// 加入1或0的二进制字符字串
                if (j == c.length) break;
                x = (x << 1) | (c[j] - '0'); // x左移一位并加上下一个位置上的1或0
            }
        }
        return set.size() == n;
    }

    // 终极滑动窗口，思路见灵神解答
    // 还是太高端了，暂时看不懂
    // https://leetcode.cn/problems/binary-string-with-substrings-representing-1-to-n/solutions/2265097/san-chong-suan-fa-cong-bao-li-dao-you-hu-nmtq/
    public boolean queryString2(String s, int n) {
        if (n == 1) return s.contains("1");

        int k = 31 - Integer.numberOfLeadingZeros(n); // n的二进制数减1
        if (s.length() < Math.max(n - (1 << k) + k + 1, (1 << (k - 1)) + k - 1)) {
            return false;
        }

        return check(s, k, n / 2 + 1, (1 << k) - 1) && check(s, k + 1, 1 << k, n);
    }

    // 对于长为 k 的在 [lower, upper] 内的二进制数，判断这些数 s 是否都有
    private boolean check(String s, int k, int lower, int upper) {
        if (lower > upper) return true;
        Set<Integer> set = new HashSet<>();
        int mask = (1 << (k - 1)) - 1;
        int x = Integer.parseInt(s.substring(0, k - 1), 2);
        for (int i = k - 1, m = s.length(); i < m; i++) {
            // & mask 可以去掉最高比特位，从而实现滑窗的「出」
            // << 1 | (s.charAt(i) - '0') 即为滑窗的「入」
            x = ((x & mask) << 1) | (s.charAt(i) - '0');
            if (lower <= x && x <= upper)
                set.add(x);
        }
        return set.size() == upper - lower + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.queryString("0110", 4));
        System.out.println(solution.queryString1("0110", 4));

    }
}