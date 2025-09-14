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


    public long countOfSubstrings(String word, int k) {
        char[] c = word.toCharArray();
        int n = c.length;
        long ans = 0;

        // 记录 > k的个数
        Map<Character, Integer> map1 = new HashMap<>();
        int cnt1 = 0;
        int left1 = 0;
        // 记录 > k的个数
        Map<Character, Integer> map2 = new HashMap<>();
        int cnt2 = 0;
        int left2 = 0;

        for(int right = 0; right < n; right++) {
            char ch = c[right];
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                map1.merge(ch, 1, Integer::sum);
                map2.merge(ch, 1, Integer::sum);
            } else {
                cnt1++;
                cnt2++;
            }

            while (map1.size() == 5 && cnt1 > k) {
                ch = c[left1++];
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    map1.merge(ch, -1, Integer::sum);
                    if (map1.get(ch) == 0) map1.remove(ch);
                } else {
                    cnt1--;
                }
            }

            while (map2.size() == 5 && cnt2 >= k && left2 <= right) {
                ch = c[left2++];
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    map2.merge(ch, -1, Integer::sum);
                    if (map2.get(ch) == 0) map2.remove(ch);
                } else {
                    cnt2--;
                }
            }

            ans += left2 - left1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countOfSubstrings("aeiou", 0));
    }
}

