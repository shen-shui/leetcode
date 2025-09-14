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

    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int target = 0;
        for (int num : nums) {
            target += num;
        }
        target -= x;
        if (target < 0) return -1;

        int res = -1;
        int sum = 0;

        for (int l = 0, r = 0; r < n; r++) {
            sum += nums[r];
            while (sum > target) {
                sum -= nums[l++];
            }
            if (sum == target) {
                res = Math.max(res, r - l + 1);
            }
        }
        return res < 0 ? -1 :n - res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minOperations(new int[]{3,2,20,1,1,3}, 10));
    }
}