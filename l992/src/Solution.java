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


    public int subarraysWithKDistinct(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;

        // 记录 > k的个数
        Map<Integer, Integer> map1 = new HashMap<>();
        int left1 = 0;
        // 记录 >= k的个数
        Map<Integer, Integer> map2 = new HashMap<>();
        int left2 = 0;

        for (int right = 0; right < n; right++) {
            int num = nums[right];
            map1.merge(num, 1, Integer::sum);
            map2.merge(num, 1, Integer::sum);

            while (map1.size() > k && left1 <= right) {
                num = nums[left1++];
                map1.merge(num, -1, Integer::sum);
                if (map1.get(num) == 0) map1.remove(num);
            }

            while (map2.size() >= k && left2 <= right) {
                num = nums[left2++];
                map2.merge(num, -1, Integer::sum);
                if (map2.get(num) == 0) map2.remove(num);
            }

            ans += left2 - left1;
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subarraysWithKDistinct(new int[]{1,2,1,2,3}, 2));
    }
}

