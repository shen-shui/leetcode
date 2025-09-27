import java.util.Arrays;

class Solution {
    /**
     * 二分答案 t
     */
    // []
    public double separateSquares(int[][] squares) {
        int n = squares.length;
        Arrays.sort(squares, (a, b) -> a[1] - b[1]);
        double left = squares[0][1];
        Arrays.sort(squares, (a, b) -> a[1] + a[2] - b[1] - b[2]);
        double right = squares[n - 1][1] + squares[n - 1][2];
        // 浮点数没办法二分， 题目说误差在10-5次方就行
        // 那么可以计算出二分次数，原长度是L，每次二分减半 L/2^k <= 10-5 就能求出最小二分次数满足要求
        for (int i = 0; i <= 48; i++) {
            double mid = left + (right - left) / 2;
            if (!check(squares, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return (left + right) / 2;
    }

    private boolean check(int[][] squares, double mid) {
        double downS = 0;
        double upS = 0;
        for (int[] square : squares) {
            if (mid > square[1]) {
                downS += Math.min((mid - square[1]), square[2]) * square[2];
            }
            if (square[1] + square[2] > mid) {
                upS += Math.min((square[1] + square[2] - mid), square[2]) * square[2];
            }
        }
        return upS - downS < 1e-5;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.separateSquares(new int[][]{
                {12,13,15}, {19,23,1}
        }));
    }
}