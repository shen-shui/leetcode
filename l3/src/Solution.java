import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    // 666
    // 滑动窗口
//    //外层循环扩展右边界，内层循环扩展左边界
//    for (int l = 0, r = 0 ; r < n ; r++) {
//        //当前考虑的元素
//        while (l <= r && check()) {//区间[left,right]不符合题意
//            //扩展左边界
//        }
//        //区间[left,right]符合题意，统计相关信息
//    }

    /**
     * 哈希表
     * 因为只有小写字母,哈希表可以用数组来代替
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        char[] cs = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        int ans = 0;
        for (int l = 0, r = 0; r < cs.length; r++) {
            char c1 = cs[r];
            map.merge(c1, 1, Integer::sum);
            while (map.get(c1) > 1) {
                char c2 = cs[l++];
                if (map.get(c2) == 1) {
                    map.remove(c2);
                } else {
                    map.put(c2, map.get(c2) - 1);
                }
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    /**
     * 哈希集合
     * 因为只有小写字母,哈希表可以用boolean数组来代替
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        char[] cs = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int ans = 0;
        for (int l = 0, r = 0; r < cs.length; r++) {
            char c1 = cs[r];
            while (set.contains(c1)) {
                char c2 = cs[l++];
                set.remove(c2);
            }
            set.add(c1);
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstring1("abcabcbb"));

    }
}