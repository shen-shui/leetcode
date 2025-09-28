import java.util.*;

class Solution {
    /**
     *  二分求最大
     */
    public int maximumLength(String s) {
        char temp = 0;
        int cnt = 1;
        // 将连续的某个字符出现的个数记录下来
        Map<Character, List<Integer>> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (c == temp) {
                cnt++;
            } else if (temp != 0) {
                map.computeIfAbsent(temp, k -> new ArrayList<>()).add(cnt);
                cnt = 1;
            }
            temp = c;
        }
        map.computeIfAbsent(temp, k -> new ArrayList<>()).add(cnt);
        int left = 0;
        int right = s.length();
        while (left + 1 <right) {
            int mid = left + (right - left) / 2;
            if (check(map, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left == 0 ? -1 : left;
    }

    private boolean check(Map<Character, List<Integer>> map, int mid) {
        if (mid == 0) return false;
        for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
            int cnt = 0;
            for (Integer count : entry.getValue()) {
                cnt += (count - mid >= 0) ? count - mid + 1 : 0;
                if (cnt >= 3) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumLength("bbc"));
    }
}