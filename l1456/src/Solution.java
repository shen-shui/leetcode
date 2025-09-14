import java.util.HashSet;
import java.util.Set;

public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    public int maxVowels(String s, int k) {
        int n = s.length();

        int vowel = 0, res = 0;
        for (int i = 0; i < n; i++) {
            // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowel++;
            }
            if (i < k - 1) continue;

            // 更新：更新答案。一般是更新最大值/最小值。
            res = Math.max(vowel, res);

            // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。
            c = s.charAt(i - k + 1);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowel--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxVowels("abciiidef", 3));
    }
}