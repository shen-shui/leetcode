import java.util.Arrays;

public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    public int[] decrypt(int[] code, int k) {
        int n = code.length;

        int[] res = new int[n];
        if (k == 0) {
            Arrays.fill(res, 0);
            return res;
        }
        int count = 0, sum = 0;

        for (int i = 0; i < Math.abs(k) - 1; i++) {
            sum += code[i];
        }

        for (int i = Math.abs(k) - 1; i < n; i=(i+1)%n) {
            sum += code[i];

            count++;
            if (k > 0) {
                res[(i + n - k) % n] = sum;
            } else {
                res[(i + 1) % n] = sum;
            }
            if (count == n) break;

            sum -= code[(i + n + 1 - Math.abs(k)) % n];
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.decrypt(new int[]{5, 7, 1, 4}, 3)));
    }
}