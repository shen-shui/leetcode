import java.util.*;

/**
 * 二分算法
 * 需要注意while的判断条件 判断条件均是区间内没有元素
 * 区间[] [) ()的选择
 */
public class Solution {



    public List<Integer> solveQueries(int[] nums, int[] queries) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        // 用一个哈希表存入一个链表来存相同数值的下标
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // 遍历queries数组
        for ()
    }


    /**
     * 暴力解法：超时
     */
//    public List<Integer> solveQueries(int[] nums, int[] queries) {
//        List<Integer> ans = new ArrayList<>();
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int num : nums) {
//            map.merge(num, 1, Integer::sum);
//        }
//
//        for (int i = 0; i < queries.length; i++) {
//            int index = queries[i];
//            int num = nums[index];
//            if (map.get(num) == 1) {
//                ans.add(-1);
//                continue;
//            }
//            for (int j = 1; j <= nums.length / 2 + 1; j++) {
//                int right = nums[(index + j) % nums.length];
//                int left = nums[(index + nums.length - j) % nums.length];
//                if (right == num || left == num) {
//                    ans.add(j);
//                    break;
//                }
//            }
//        }
//        return ans;
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(new List[]{solution.solveQueries(new int[]{1, 3, 1, 4, 1, 3, 2}, new int[]{0, 3, 5})}));
    }
}
