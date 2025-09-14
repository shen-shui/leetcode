import java.util.Arrays;

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


    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int ans = 0;

        // 记录 > goal 的子数组左端点
        int left1 = 0, sum1 = 0;
        // 记录 = goal 的子数组左端点
        int left2 = 0, sum2 = 0;
        for (int right = 0; right < n; right++) {
            sum1 += nums[right];
            sum2 += nums[right];

            while (sum1 > goal) {
                sum1 -= nums[left1++];
            }

            while (sum2 >= goal && left2 - 1 < right) {
                sum2 -= nums[left2++];
            }

            ans += left2 - left1;
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numSubarraysWithSum(new int[]{0,0,0,0,0}, 0));
    }
}

