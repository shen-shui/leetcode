import java.util.HashMap;
import java.util.Map;
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

    public int beautifulBouquet(int[] flowers, int cnt) {
        int n = flowers.length;
        long ans = 0;
        int left = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int exceed = 0;

        for (int right = 0; right < n; right++) {
            map.merge(flowers[right], 1, Integer::sum);
            if (map.get(flowers[right]) > cnt) {
                exceed++;
            }

            while (exceed > 0) {
                if (map.get(flowers[left]) > cnt) {
                    exceed--;
                }

                map.merge(flowers[left], -1, Integer::sum);
                left++;
            }

            ans += right - left + 1;
        }
        return Math.toIntExact(ans % 1_000_000_007);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.beautifulBouquet(new int[]{1,2,3,2}, 1));
    }
}

