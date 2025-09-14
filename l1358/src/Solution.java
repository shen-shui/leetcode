import java.util.Arrays;
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


    public int numberOfSubstrings(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        // 记录数字出现次数
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;

        int left = 0;
        for (int right = 0; right < n; right++) {
            map.merge(c[right], 1, Integer::sum);

            while (map.size() == 3) {
                map.merge(c[left], -1, Integer::sum);
                if (map.get(c[left]) == 0) map.remove(c[left]);
                left++;
            }
            ans += left;
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numberOfSubstrings("abcabc"));
    }
}

