import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    // 不定长滑动窗口
//    //外层循环扩展右边界，内层循环扩展左边界
//    for (int l = 0, r = 0 ; r < n ; r++) {
//        //当前考虑的元素
//        while (l <= r && check()) {//区间[left,right]不符合题意
//            //扩展左边界
//        }
//        //区间[left,right]符合题意，统计相关信息
//    }


    static int cnt;

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        cnt = 0;

        int posX = location.get(0), posY = location.get(1);
        List<Double> angList = new ArrayList<>(points.size());
        for (List<Integer> point : points) {
            double tag = calculateAngle(posX, posY, point.get(0), point.get(1));
            if (tag != -1) {
                angList.add(tag);
            }
        }
        Collections.sort(angList);

        List<Double> result = new ArrayList<>(angList.size() * 2);
        result.addAll(angList);

        for (double ang : angList) {
            if (ang <= angle) {
                result.add(ang + 360);
            } else {
                break;
            }
        }
        if (result.isEmpty()) return cnt;

        int ans = 0;
        for (int l = 0, r = 0; r < result.size(); r++) {
            while (result.get(r) - result.get(l) > angle) {
                l++;
            }
            ans = Math.max(ans, r - l + 1 + cnt);
        }
        return ans;
    }

    private double calculateAngle(int posX, int posY, int x, int y) {
        int dx = x - posX, dy = y - posY;

        if (dx == 0 && dy == 0) {
            cnt += 1;
            return -1;
        }
        double angleRad = Math.atan2(dx, dy);
        double angleDeg = Math.toDegrees(angleRad);

        return angleDeg >= 0 ? angleDeg : angleDeg + 360;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.visiblePoints(
                Arrays.asList(
                Arrays.asList(1, 1),
                Arrays.asList(1, 1),
                Arrays.asList(1, 1)
        ), 1, Arrays.asList(1, 1)));
    }
}

