import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;
        ArrayList<String> res = new ArrayList<>();
        int cnt = 0, bg = 0;
        for (int i = 0; i < n; i++) {
            cnt += words[i].length();
            if (i + 1 == words.length || cnt + words[i + 1].length() + 1 > maxWidth) {
                res.add(fillWords(words, bg, i, maxWidth, i + 1 == words.length));
                bg = i + 1;
                cnt = 0;
                continue;
            }
            cnt += 1;
        }
        return res;
    }

    private String fillWords(String[] words, int bg, int ed, int maxWidth, boolean lastLine) {
        int count = ed - bg + 1;
        int space = maxWidth - count;
        for (int i = bg; i <= ed; i++) {
            space -= words[i].length();
        }
        // 额外需要加入多少空格数
        int spaceAvg = (count == 1) ? 1 : space / (count - 1);
        // 因为要均匀并且左边比右边多，所以还要计算余数，从左往右加一，加完为止
        int spaceExtra = (count == 1) ? 0 : space % (count - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = bg; i < ed; i++) {
            sb.append(words[i]);
            if (lastLine) {
                sb.append(" ");
                continue;
            }
            int spaceEvery = 1 + spaceAvg + ((i - bg > spaceExtra) ? 0 : 1);
            while (spaceEvery-- > 0) {
                sb.append(" ");
            }
        }
        sb.append(words[ed]);

        int lastSpaceCnt = maxWidth - sb.length();
        while (lastSpaceCnt-- > 0) {
            sb.append(" ");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> strings = solution.fullJustify(
                new String[]{"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"}, 20);
        System.out.println(strings);
    }
}