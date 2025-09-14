import java.util.HashMap;
import java.util.HashSet;

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

    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        HashSet<Integer> set = new HashSet<>();
        int res = 0, sum = 0;

        for (int l = 0, r = 0; r < n; r++) {
            int value = nums[r];
            while (set.contains(value)) {
                sum -= nums[l];
                set.remove(nums[l++]);
            }
            set.add(value);
            sum += value;
            res = Math.max(res, sum);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumUniqueSubarray(new int[]{4,2,4,5,6}));
    }
}