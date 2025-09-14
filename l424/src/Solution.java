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

    public int characterReplacement(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] freq = new int[26];

        int ans = 0;
        // 历史窗口最大值，记录历史窗口中某字母的最大值
        // 如果多出的字母 > k 的话，就滑动窗口来找另一个窗口的最大值
        // 找到窗口最大值，就一直滑动窗口，然后返回长度 - 左端点就可以拿到 maxCount + k了
        int maxCount = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            freq[chars[right] - 'A']++;
            maxCount = Math.max(maxCount, freq[chars[right] - 'A']);

            if (right - left + 1 > maxCount + k) {
                freq[chars[left] - 'A']--;
                left++;
            }
        }
        return chars.length - left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.characterReplacement("ABAB", 2));
    }
}

