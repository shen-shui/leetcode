import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    // 定长滑动窗口
    // 入：下标为 i 的元素进入窗口，更新相关统计量。如果 i<k−1 则重复第一步。
    // 更新：更新答案。一般是更新最大值/最小值。
    // 出：下标为 i−k+1 的元素离开窗口，更新相关统计量。

    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length(); // 字符串s长度
        int m = words.length; // words长度
        int w = words[0].length(); // 每个words的长度
        Map<String, Integer> map = new HashMap<>(); // 记录words中每个单词的出现次数
        for (String word : words) {
            map.merge(word, 1, Integer::sum);
        }

        List<Integer> res = new ArrayList<>(); // 记录串联字串在s中的索引

        /*
        解释一下为什么要循环w次
        我们将整个字符串s分成w组，分别为
            0, w, 2w, 3w, ...
            1, w+1, 2w+1, 3w+1, ...
            ...
            w-1, 2w-1, 3w-1, ...
        这样就可以让滑动窗口一次移动w个了
         */
        for (int i = 0; i < w; i++) {
            Map<String, Integer> window = new HashMap<>();// 记录每个窗口中的单词的出现次数
            int cnt = 0;// 记录当前窗口匹配的单词个数
            for (int j = i; j + w <= n; j+=w) {
                // 入
                String sTemp = s.substring(j, j + w);
                window.merge(sTemp, 1, Integer::sum);
                if (map.get(sTemp) != null && window.get(sTemp) <= map.get(sTemp)) cnt++;
                if (j - i + w < m * w) continue;

                // 更新
                if (cnt == m) res.add(j + w - m * w);

                // 出
                sTemp = s.substring(j + w - m * w, j + 2 * w - m * w);
                if (map.get(sTemp) != null && window.get(sTemp) <= map.get(sTemp)) cnt--;
                if (window.get(sTemp) == 1) {
                    window.remove(sTemp);
                } else {
                    window.put(sTemp, window.get(sTemp)-1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] str = new String[]{"foo","bar"};
        System.out.println(solution.findSubstring("barfoothefoobarman", str));
    }
}