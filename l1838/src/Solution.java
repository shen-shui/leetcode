import java.util.Arrays;
import java.util.Collections;

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

    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) return 1;
        Arrays.sort(nums);

        long cnt = 0; // 记录改变次数
        int len = 1; // 记录现在的频数
        int ans = 0; // 答案

        for (int l = 0, r = 1; r < n; r++) {
            cnt += (long) (nums[r] - nums[r - 1]) * len;
            len++;
            while (cnt > k) {
                cnt -= nums[r] - nums[l++];
                len--;
            }
            ans = Math.max(ans, len);
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxFrequency(new int[]{1,1,1,1,1,1,1,1,1,1,10000}, 1));
    }
}

