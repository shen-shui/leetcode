public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;

        int sum = 0, res = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                sum += customers[i];
            }
        }

        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 1) {
                sum += customers[i];
            }
            if (i + 1 < minutes) continue;

            res = Math.max(sum, res);

            if (grumpy[i + 1 - minutes] == 1) {
                sum -= customers[i + 1 - minutes];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxSatisfied(new int[]{1,0,1,2,1,1,7,5}, new int[]{0,1,0,1,0,1,0,1}, 3));
    }
}