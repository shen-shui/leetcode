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

    public int maximumBeauty(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int flag = nums[0];
        int ans = 0;
        for (int l = 0, r = 0; r < n; r++) {
            while (nums[r] - flag > 2 * k) {
                flag = nums[++l];
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumBeauty(new int[]{4, 6, 1, 2}, 2));
        System.out.println(solution.maximumBeauty(new int[]{5,57,46}, 15));
    }
}