public class Solution {
    /**
     * 字符串方法
     * @param num
     * @param k
     * @return
     */
    public int divisorSubstrings(int num, int k) {
        String s = String.valueOf(num);
        int ans = 0;
        for (int i = k; i <= s.length(); i++) {
            int x = Integer.parseInt(s.substring(i - k, i));
            if (x == 0) continue;
            if (num % x == 0) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 数学方法
     * @param num
     * @param k
     * @return
     */
    public int divisorSubstrings1(int num, int k) {
        int m = (int) Math.pow(10, k);
        int n = num;
        int res = 0;

        while (n / (int) Math.pow(10, k-1) != 0) {
            if (n % m == 0) {
                n /= 10;
                continue;
            }
            if(num % (n % m) == 0) res++;
            n /= 10;
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.divisorSubstrings(240, 2));
        System.out.println(solution.divisorSubstrings1(430043, 2));

    }
}