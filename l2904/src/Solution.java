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

    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int sum = 0;

        for (char c : s.toCharArray()) {
            if (c == '1') sum++;
        }
        if (sum < k) return "";

        sum = 0;
        int left = 0, minLen = Integer.MAX_VALUE;
        String ans = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') sum++;

            while (sum >= k) {
                if (s.charAt(left) == '1') {
                    if (sum == k) {
                        break;
                    } else {
                        sum--;
                    }
                }
                left++;
            }

            if (sum == k && (right - left + 1) <= minLen) {
                if (ans.isEmpty()) {
                    ans = s.substring(left, right + 1);
                } else {
                    if ((right - left + 1) < minLen) {
                        ans = s.substring(left, right + 1);
                    } else {
                        ans = ans.compareTo(s.substring(left, right + 1)) < 0 ? ans : s.substring(left, right + 1);
                    }
                }
                minLen = right - left + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.shortestBeautifulSubstring("100011001", 3));
    }
}

