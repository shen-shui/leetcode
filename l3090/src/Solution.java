import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public int maximumLengthSubstring(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (int l = 0, r = 0; r < n; r++) {
            char c1 = s[r];
            map.merge(c1, 1, Integer::sum);
            while (map.get(c1) > 2) {
                char c2 = s[l++];
                if (map.get(c2) == 1) {
                    map.remove(c2);
                } else {
                    map.put(c2, map.get(c2) - 1);
                }
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumLengthSubstring("bcbbbcba"));
    }
}