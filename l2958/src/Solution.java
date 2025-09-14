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

    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;

        for (int l = 0, r = 0; r < n; r++) {
            map.merge(nums[r], 1, Integer::sum);
            // map只加一不做其他操作,这一条语句就可以
            while (map.get(nums[r]) > k) {
//                int key = nums[l++];
//                if (map.get(key) == 1) {
//                    map.remove(key);
//                } else {
//                    map.put(key, map.get(key) - 1);
//                }
                map.merge(nums[l++], -1, Integer::sum);
                // map只减一不做其他操作,这一条语句就可以
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxSubarrayLength(new int[]{1,2,3,1,2,3,1,2}, 2));
    }
}