import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 不定长滑动窗口
//    //外层循环扩展右边界，内层循环扩展左边界
//    for (int l = 0, r = 0 ; r < n ; r++) {
//        //当前考虑的元素
//        while (l <= r && check()) {//区间[left,right]不符合题意
//            //扩展左边界
//        }
//        //区间[left,right]符合题意，统计相关信息
//    }

    public int maxRepOpt1(String text) {
        char[] chars = text.toCharArray();
        int n = chars.length;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            map.merge(c, 1, Integer::sum);
        }

        int ans = 0;
        for (int i = 0; i < n;) {
            // 找到一段连续的[i, j)
            int j = i;
            while (j < n && chars[j] == chars[i]) {
                j++;
            }
            int curCnt = j - i;

            // 如果这一段长度小于该字符出现的总数，并且前面或后面有空位，则使用 curCnt + 1 更新答案
            if ((map.get(chars[i]) > curCnt) && (j < n || i > 0)) {
                ans = Math.max(ans, curCnt + 1);
            }

            // 如果这一段的后面仍然是连续的该字符，继续计算
            int k = j + 1;
            while (k < n && chars[k] == chars[i]) {
                k++;
            }
            ans = Math.max(ans, Math.min(k - i, map.get(chars[i])));
            i = j;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxRepOpt1("bbababaaaa"));
    }
}

