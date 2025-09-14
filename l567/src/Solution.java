import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    public boolean checkInclusion(String s1, String s2) {
        int n = s2.length();
        int k = s1.length();
        int[] arrS1 = new int[26];

        int count = 0;
        for (int i = 0; i < k; i++) {
            if (arrS1[s1.charAt(i) - 97] == 0) {
                count++;
            }
            arrS1[s1.charAt(i) - 97]++;
        }

        for (int i = 0; i < n; i++) {
            // 入
            if(arrS1[s2.charAt(i) - 97] == 1) {
                count --;
            }
            arrS1[s2.charAt(i) - 97]--;
            if (i + 1 < k) continue;

            if (count == 0) {
                return true;
            }

            if (arrS1[s2.charAt(i + 1 - k) - 97] == 0) {
                count++;
            }
            arrS1[s2.charAt(i + 1 - k) - 97]++;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.checkInclusion("ab", "eidbaooo"));
    }
}