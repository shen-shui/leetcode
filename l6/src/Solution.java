import java.util.ArrayList;
import java.util.List;

public class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        } else {
            int n = s.length();

            StringBuilder sb = new StringBuilder();
            int jumpSE = (numRows - 1) * 2;
            for (int i = 0; i < n; i += jumpSE) {
                sb.append(s.charAt(i));
            }

            int line = numRows - 2;
            for (int i = 0; i < line; i++) {
                int end = 2 + i * 2;
                int start = jumpSE - end;
                boolean flag = true;
                for (int j = i + 1; j < n;) {
                    if (flag) {
                        sb.append(s.charAt(j));
                        flag = false;
                        j += start;
                    } else {
                        sb.append(s.charAt(j));
                        flag = true;
                        j += end;
                    }
                }
            }

            int startE = numRows - 1;
            for (int i = startE; i < n; i += jumpSE) {
                sb.append(s.charAt(i));
            }
            return sb.toString();
        }
    }

    /*
            大神方法：极致的i和flag完成了赋值操作
     */
    public String convert1(String s, int numRows) {
        if (numRows == 1) {return s;}
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            list.get(i).append(c);
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : list) {
            res.append(row);
        }

        return res.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.convert("A", 1));
    }
}