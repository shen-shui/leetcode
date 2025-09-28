import java.util.Arrays;

class Solution {
    /**
     *  二分求最大
     */
    public int maximumRemovals(String s, String p, int[] removable) {
        int n = removable.length;
        char[] chars = s.toCharArray();
        char[] chars1;
        char[] charp = p.toCharArray();
        int left = 0;
        int right = n + 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            chars1 = chars.clone();
            if (check(chars1, charp, removable, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean check(char[] chars, char[] charp, int[] removable, int mid) {
        for (int i = 0; i < mid; i++) {
            chars[removable[i]] = 0;
        }
        int left = 0;
        int right = 0;
        while (right < charp.length) {
            if (chars[left] == charp[right]) {
                left++;
                right++;
            } else {
                left++;
            }
            if (left == chars.length && right != charp.length) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumRemovals("abcbddddd", "abcd", new int[]{3,2,1,4,5,6}));
    }
}