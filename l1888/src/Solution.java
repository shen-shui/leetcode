import java.util.Arrays;

public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    /*
    思路:把s扩展为2倍，就能得到任意次数操作一的子字符串，然后把字符串长度作为定长，进行滑动窗口
     */
    public int minFlips(String s) {
        int k = s.length();
        s += s;
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            // 统计满足两种序列的个数，哪个多就反转另一个;
            int c = s.charAt(i);
            if (c % 2 == i % 2) sb.append(0); // 满足该条件，代表符合01010101这样序列的个数
            else sb.append(1); // 该条件代表符合10101010这样序列的个数
        }

        String ls = sb.toString();

        int minOp = 0, res = Integer.MAX_VALUE;
        int count = 0; // 记录0的个数，因为滑动窗口长度为k，所以记录一个就行
        for (int i = 0; i < n; i++) {
            // 入
            int c = ls.charAt(i);
            if (c % 2 == 0) count++;
            if (i + 1 < k) continue;

            // 更新答案
            if (count > k / 2) minOp = k - count;
            else minOp = count;
            res = Math.min(minOp, res);

            // 出
            c = ls.charAt(i + 1 - k);
            if (c % 2 == 0) count--;
        }
        return res;
    }

    /*
    优化
     */
    public int minFlips1(String s) {
        char[] cs = s.toCharArray();
        int n = s.length(); // 因为要扩展s变成2被，不需要真正扩容两倍，还是用原来的当成循环数组来处理就可以了

        int minOp = 0, res = Integer.MAX_VALUE; // 不需要count, minOp就能处理count的功能
        for (int i = 0; i < n * 2 - 1; i++) {
            // 入
            if (cs[i % n] % 2 == i % 2) {
                // 代表满足101010或010101中某一个序列的标准，无须知道是哪个序列，因为滑动窗口每动一次，标准就会改变一次
                // 我们只需知道哪个序列个数少，修改那个序列即可
                minOp++;
            }
            if (i + 1 < n) continue;

            // 更新答案
            res = Math.min(res, minOp * 2 > n ? n - minOp : minOp);

            // 出
            if (cs[i + 1 - n] % 2 == (i + 1 - n) % 2) minOp--;

        }
        return res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minFlips("111000"));
        System.out.println(solution.minFlips1("111000"));
    }
}