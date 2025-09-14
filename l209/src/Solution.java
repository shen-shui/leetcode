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


    // 和不定长滑动窗口公式出现区别
    // 如果先判断是否符合题意
    // 那么永远无法更新答案
    // 所以要在while循环中更新答案
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int left = 0;
        int res = Integer.MAX_VALUE, sum = 0;

        for (int num : nums) {
            sum += num;
        }
        if (sum < target) return 0;

        sum = 0;

        for (int right = 0; right < n; right++) {
            sum += nums[right];

            while (sum >= target) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left++];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    }
}

