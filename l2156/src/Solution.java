import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    // 倒序
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        char[] c = s.toCharArray();
        int n = c.length;

        long hash = 0;
        long pk = 1;
        int ans = 0;
        for (int i = n - 1; i >= n - k; i--) {
            hash = (hash * power + c[i] - 96) % modulo;
            pk = pk * power % modulo;
        }
        if (hash == hashValue) ans = n - k;

        for (int i = n - k - 1; i >= 0; i--) {
            hash = (hash * power + (c[i] - 96) - pk * (c[i + k] - 96) % modulo + modulo) % modulo;
            if (hash == hashValue) {
                ans = i;
            }
        }
        return s.substring(ans, ans + k);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subStrHash("fbxzaad", 31, 100, 3, 32));
    }
}