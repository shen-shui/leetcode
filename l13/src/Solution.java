public class Solution {
    public int romanToInt(String s) {
        int n = s.length();
        int sum = 0;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':
                    temp = 1;
                    sum += 1;
                    break;
                case 'V':
                    if (temp == 1) {
                        sum -= 2;
                    }
                    sum += 5;
                    break;
                case 'X':
                    if (temp == 1) {
                        sum -= 2;
                    }
                    temp = 3;
                    sum += 10;
                    break;
                case 'L':
                    if (temp == 3) {
                        sum -= 20;
                    }
                    sum += 50;
                    break;
                case 'C':
                    if (temp == 3) {
                        sum -= 20;
                    }
                    temp = 5;
                    sum += 100;
                    break;
                case 'D':
                    if (temp == 5) {
                        sum -= 200;
                    }
                    sum += 500;
                    break;
                case 'M':
                    if (temp == 5) {
                        sum -= 200;
                    }
                    sum += 1000;
                    break;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.romanToInt("IV"));
    }
}