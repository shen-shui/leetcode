public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    public int countCompleteSubstrings(String word, int k) {
        char[] c = word.toCharArray();
        int n = c.length;

        int res = 0;
        for (int i = 1; i <= 26 && i * k <= n; i++) {// 滑动窗口长度为 i * k
            int count = 0;
            int size = 0;
            int[] cnt = new int[26];
            for (int j = 0; j < n; j++) {
                if (j != 0) {
                    if (Math.abs(c[j] - c[j - 1]) > 2) {
                        cnt = new int[26];
                        size = 0;
                        count = 0;
                    }
                }
                // 入
                int index = c[j] - 97;
                if (cnt[index] == k - 1) count++;
                else if (cnt[index] == k) count--;
                cnt[index]++;
                size++;
                if(size < i * k) continue;

                // 更新
                if (count == i) {
//                    System.out.println(word.substring(j + 1 - i * k, j + 1));
                    res++;
                }

                // 出
                index = c[j + 1 - i * k] - 97;
                if (cnt[index] == k) count--;
                else if (cnt[index] == k + 1) count++;
                cnt[index]--;
                size--;
            }
        }
       return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countCompleteSubstrings("hmfttfmhpttmpphmhthfpffphftfhthtmhthhfphfmmm", 1));
    }
}