import java.util.HashMap;
import java.util.Map;

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

    public int longestSubarray(int[] nums) {
        int n = nums.length;
        boolean flag = true;
        int res = 0;
        for (int l = 0, r = 0; r < n; r++) {
            if (nums[r] == 0 && !flag) {
                while (!flag) {
                    if (nums[l++] == 0) {
                        flag = true;
                    }
                }
                flag = false;
            } else if (nums[r] == 0 && flag) {
                flag = false;
            }
            res = Math.max(r - l, res);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestSubarray(new int[]{0,1,1,1,0,1,1,0,1}));
    }
}