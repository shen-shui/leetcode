import java.util.TreeMap;

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

    // TreeMap 有序map，会按照key的大小进行排序
    public long continuousSubarrays(int[] nums) {
        int n = nums.length;
        int left = 0;
        long ans = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int right = 0; right < n; right++) {
            map.merge(nums[right], 1, Integer::sum);

            while(map.lastKey() - map.firstKey() > 2) {
                if (map.get(nums[left]) == 1) {
                    map.remove(nums[left]);
                } else {
                    map.merge(nums[left], -1, Integer::sum);
                }
                left++;
            }

            ans += right - left + 1;
        }
        return ans;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.continuousSubarrays(new int[]{5, 4, 2, 4}));
    }
}

