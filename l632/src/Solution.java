import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public int[] smallestRange(List<List<Integer>> nums) {
        int n = nums.size();
        int sumLen = 0;
        for (List<Integer> list : nums) {
            sumLen += list.size();
        }
        // 记录所有分组(确定滑动窗口的符合题意条件所需的数组)
        int[] cnt = new int[n];
        // 使用pairs进行分组，好使用滑动窗口来确保最短空间容纳三个组的数字
        int[][] pairs = new int[sumLen][2];
        int index = 0;
        for (int i = 0; i < n; i++) {
            List<Integer> list = nums.get(i);
            for (int num : list) {
                pairs[index][0] = num;
                pairs[index++][1] = i;
            }
        }
        // 按照第一维进行排序
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        int left = 0;
        int startL = -1, startR = pairs[pairs.length-1][0];
        for (int right = 0; right < pairs.length; right++) {
            cnt[pairs[right][1]]++;
            if (cnt[pairs[right][1]] == 1) {
                n--;
            }

            while (n == 0) {
                // 更新答案
                if (pairs[right][0] - pairs[left][0] < startR - startL) {
                    startL = pairs[left][0];
                    startR = pairs[right][0];
                }

                cnt[pairs[left][1]]--;
                if (cnt[pairs[left++][1]] == 0) n++;
            }
        }
        return new int[]{startL, startR};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> nums = new ArrayList<List<Integer>>();
        List<Integer> list1 = Arrays.asList(4, 10, 15, 24, 26);
        List<Integer> list2 = Arrays.asList(0, 9, 12, 20);
        List<Integer> list3 = Arrays.asList(5, 18, 22, 30);
        nums.add(list1);
        nums.add(list2);
        nums.add(list3);

        System.out.println(Arrays.toString(solution.smallestRange(nums)));
    }
}

