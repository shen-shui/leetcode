import java.util.Arrays;

/**
 * 二分算法
 * 需要注意while的判断条件 判断条件均是区间内没有元素
 * 区间[] [) ()的选择
 */
public class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] queryInt = new int[queries.length];
        int[] wordInt = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            wordInt[i] = findCount(words[i]);
        }
        Arrays.sort(wordInt);
        for (int i = 0; i < queries.length; i++) {
            queryInt[i] = wordInt.length - lowerBound(wordInt, findCount(queries[i]) + 1);
        }
        return queryInt;
    }

    private int findCount(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        int count = 0;
        for (char c : chars) {
            if (c == chars[0]) count++;
        }
        return count;
    }

    // [)
    private int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.numSmallerByFrequency(new String[]{"cbd"}, new String[]{"zaaaz"})));
    }
}
