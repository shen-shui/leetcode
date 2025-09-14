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

    public int waysToSplit(int[] nums) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        int index = 1;
        for (int num : nums) {
            prefix[index] = prefix[index - 1] + num;
            index++;
        }
        // nums:    1 1 1
        // prefix:  0 1 2 3
        // left prefix[left]
        // mid prefix[right] - prefix[left]
        // right prefix[n + 1] - prefix[right]
        long ans = 0;
        int left = 1;
        for (int right = 1; right < n + 1; right++) {

            if (2 * prefix[left] > prefix[right]) continue;
            if (prefix[right] - prefix[left] > prefix[n + 1] - prefix[right])
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.characterReplacement("ABAB", 2));
    }
}

