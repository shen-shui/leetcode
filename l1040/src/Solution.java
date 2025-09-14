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

    // 脑子不好使，做不出来
    public int[] numMovesStonesII(int[] stones) {
        int n = stones.length;
        int minX = Integer.MAX_VALUE, maxX = 0;
        Arrays.sort(stones);
        int right = -1;
        for (int i = 0; i < n; i++) {
            int num = stones[i];
            right = i - 1;
            for (int j = i; j < n; j++) {
                if (stones[j] - num + 1 > n) break;
                right++;
            }

            int op = n - (right - i + 1);
            if (op > maxX) maxX = op;
            else if (op < minX) minX = op;
        }
        return new int[]{minX, maxX};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.numMovesStonesII(new int[]{6,5,4,3,10})));
    }
}

