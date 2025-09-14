import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>(); // 存储是否满足maxLetters要求
        Map<String, Integer> m = new HashMap<>(); // 存储出现符合条件字符串的次数
        StringBuilder sb = new StringBuilder();

        int res = 0;
        for (int i = 0; i < n; i++) {
            // 入
            char c = s.charAt(i);
            sb.append(c);
            map.merge(c, 1, Integer::sum);
            if (i + 1 < minSize) continue;

            // 更新答案
            if (map.size() <= maxLetters) {
                m.merge(sb.toString(), 1, Integer::sum);
                res = Math.max(res, m.get(sb.toString()));
            }

            // 出
            c = s.charAt(i + 1 - minSize);
            sb.deleteCharAt(0);
            if (map.get(c) == 1) {
                map.remove(c);
            } else {
                map.put(c, map.get(c)-1);
            }
        }

        return res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxFreq("aababcaab", 2, 3, 4));
    }
}