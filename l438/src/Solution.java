import java.util.*;

public class Solution {
    // 滑动窗口
//    //外层循环扩展右边界，内层循环扩展左边界
//    for (int l = 0, r = 0 ; r < n ; r++) {
//        //当前考虑的元素
//        while (l <= r && check()) {//区间[left,right]不符合题意
//            //扩展左边界
//        }
//        //区间[left,right]符合题意，统计相关信息
//    }

    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) return new ArrayList<Integer>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];

        List<Integer> res = new ArrayList<>();
        int l = 0, r = 0;

        for (char c : p.toCharArray()) {
            ++pCount[c - 'a'];
        }

        while (r < s.length()) {
            ++sCount[s.charAt(r) - 'a'];
            ++r;

            if (r - l > p.length()) {
                --sCount[s.charAt(l) - 'a'];
                ++l;
            }

            if (r - l == p.length() && Arrays.equals(sCount, pCount)) {
                res.add(l);
            }
        }

        return res;
    }

    /*
    使用match替代掉每一次都需要比对两个数组是否equals
     */
    public List<Integer> findAnagrams2(String s, String p) {
        if (s.length() < p.length()) return new ArrayList<Integer>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];

        List<Integer> res = new ArrayList<>();
        int l = 0, r = 0;
        int match = 0;

        for (char c : p.toCharArray()) {
            ++pCount[c - 'a'];
        }

        while (r < s.length()) {
            if (sCount[s.charAt(r) - 'a'] < pCount[s.charAt(r) - 'a']) match++;
            ++sCount[s.charAt(r) - 'a'];
            ++r;

            if (r - l > p.length()) {
                if (sCount[s.charAt(l) - 'a'] <= pCount[s.charAt(l) - 'a']) match--;
                --sCount[s.charAt(l) - 'a'];
                ++l;
            }

            if (r - l == p.length() && match == p.length()) {
                res.add(l);
            }
        }

        return res;
    }

    public List<Integer> findAnagrams3(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) return new ArrayList<>();

        int[] count = new int[26];
        List<Integer> res = new ArrayList<>();
        int l = 0, r = 0;
        int differ = 0;

        for (int i = 0; i < pLen; i++) {
            ++count[s.charAt(i) - 'a'];
            --count[p.charAt(i) - 'a'];
        }

        for (int j : count) {
            if (j != 0) differ++;
        }

        if (differ == 0) res.add(0);

        for (int i = 0; i < sLen - pLen; i++) {
            // 检查左边界
            if (count[s.charAt(i) - 'a'] == 1) differ--;
            else if (count[s.charAt(i) - 'a'] == 0) differ++;
            --count[s.charAt(i) - 'a'];

            // 检查右边界
            if (count[s.charAt(i + pLen) - 'a'] == -1) differ--;
            else if (count[s.charAt(i + pLen) - 'a'] == 0) differ++;
            ++count[s.charAt(i + pLen) - 'a'];

            // 判断differ
            if (differ == 0) res.add(i + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findAnagrams3("cbaebabacd", "abc"));
    }
}