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

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        if (k == 0 || k == 1) return 0;

        int ans = 0, left = 0, multi = 1;
        for (int right = 0; right < n; right++) {
            multi *= nums[right];

            while (multi >= k) {
                multi /= nums[left++];
            }

            // 更新答案
            ans += right - left + 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numSubarrayProductLessThanK(new int[]{1,2,3}, 0));
    }
}

