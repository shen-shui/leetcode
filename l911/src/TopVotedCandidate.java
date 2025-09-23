import java.util.*;

class TopVotedCandidate {
    // 记录 (t, i) t为时刻 i为候选人
    private List<int[]> list = new ArrayList<>();
    /**
     * 查看题解思路：从开始计票就记录哪个时刻谁的票数最多，然后对这个数据结构进行二分，找到某一时刻谁的票数最多
     * 时间复杂度为 n + Q * log(m)
     */
    public TopVotedCandidate(int[] persons, int[] times) {
        int n = persons.length;
        Map<Integer, Integer> map = new HashMap<>();
        int maxCnt = 0;
        for (int i = 0; i < n; i++) {
            map.merge(persons[i], 1, Integer::sum);
            if (map.get(persons[i]) >= maxCnt) {
                maxCnt = map.get(persons[i]);
                list.add(new int[]{times[i], persons[i]});
            }
        }
    }

    public int q(int t) {
        int idx = lowerBound(list, t + 1) - 1;
        if (idx == -1) return 0;
        else return list.get(idx)[1];
    }

    /**
     * 超时，每次拿到一个q，都要循环一次map，复杂度太高
     * n 为 persons.length
     * Q 为 查询次数
     * K 为 map.size()
     * m 为 map.get(i).length
     * 时间复杂度为 n + Q * K * log(m)
     */
//    public TopVotedCandidate(int[] persons, int[] times) {
//        int n = persons.length;
//        for (int i = 0; i < n; i++) {
//            map.computeIfAbsent(persons[i], k -> new ArrayList<>());
//            List<int[]> list = map.get(persons[i]);
//            list.add(new int[]{times[i], list.size() + 1});
//        }
//    }
//
//    public int q(int t) {
//        int n = map.size();
//        int ans = 0;
//        int maxTs = 0, maxScore = 0;
//        int[] ts = new int[n];
//        int[] scores = new int[n];
//        for (int i = 0; i < map.size(); i++) {
//            List<int[]> list = map.get(i);
//            int idx = lowerBound(list, t + 1) - 1;
//            if (idx == -1) {
//                ts[i] = 0;
//                scores[i] = 0;
//            } else {
//                ts[i] = list.get(idx)[0];
//                scores[i] = list.get(idx)[1];
//            }
//
//            if (scores[i] > maxScore) {
//                maxScore = scores[i];
//                maxTs = ts[i];
//                ans = i;
//            } else if (scores[i] == maxScore) {
//                if (ts[i] > maxTs) {
//                    maxTs = ts[i];
//                    ans = i;
//                }
//            }
//        }
//        return ans;
//    }

    // ()
    private int lowerBound(List<int[]> nums, int target) {
        int left = -1;
        int right = nums.size();
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid)[0] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        TopVotedCandidate topVotedCandidate = new TopVotedCandidate(new int[]{0,1,2,3,0}, new int[]{11,14,81,83,87});
        System.out.println(topVotedCandidate.q(88));

    }
}