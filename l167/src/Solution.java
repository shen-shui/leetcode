import java.util.Arrays;

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int index1 = 0;
        int index2 = n - 1;
        int[] res = new int[2];
        while (true) {
            if (target == numbers[index1] + numbers[index2]) {
                res[0] = index1 + 1;
                res[1] = index2 + 1;
                break;
            } else if (target < numbers[index1] + numbers[index2]) {
                index2--;
            } else {
                index1++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] res = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(res));
    }
}