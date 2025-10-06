import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

class Solution {
    /**
     *  二分求最大
     */
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int left = -1;
        int right = Collections.min(stock) + budget + 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check(n, budget, mid, composition, stock, cost)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean check(int n, int budget, int mid, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        for (List<Integer> list : composition) {
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += Math.max((long)list.get(i) * mid - stock.get(i), 0) * cost.get(i);
                if (sum > budget) break;
            }
            if (sum <= budget) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // 创建示例数据
        int n = 1; // 金属种类数量
        int k = 7; // 机器数量
        int budget = 48; // 预算

        // 创建合金配方列表
        List<List<Integer>> composition = new ArrayList<>();
        List<Integer> machine1 = new ArrayList<>();
        machine1.add(1);
//        machine1.add(1);
//        machine1.add(1);
        List<Integer> machine2 = new ArrayList<>();
        machine2.add(5);
//        machine2.add(1);
//        machine2.add(10);
        List<Integer> machine3 = new ArrayList<>();
        machine2.add(9);
//        machine2.add(1);
//        machine2.add(10);
        List<Integer> machine4 = new ArrayList<>();
        machine2.add(6);
//        machine2.add(1);
//        machine2.add(10);
        List<Integer> machine5 = new ArrayList<>();
        machine2.add(4);
//        machine2.add(1);
//        machine2.add(10);
        List<Integer> machine6 = new ArrayList<>();
        machine2.add(2);
//        machine2.add(1);
//        machine2.add(10);
        List<Integer> machine7 = new ArrayList<>();
        machine2.add(4);
//        machine2.add(1);
//        machine2.add(10);
        composition.add(machine1);
        composition.add(machine2);
        composition.add(machine3);
        composition.add(machine4);
        composition.add(machine5);
        composition.add(machine6);
        composition.add(machine7);

        // 创建库存列表
        List<Integer> stock = new ArrayList<>();
        stock.add(6);
//        stock.add(0);
//        stock.add(0);

        // 创建成本列表
        List<Integer> cost = new ArrayList<>();
        cost.add(1);
//        cost.add(2);
//        cost.add(3);

        // 创建解决方案实例
        Solution solution = new Solution();

        // 调用方法并输出结果
        int result = solution.maxNumberOfAlloys(n, k, budget, composition, stock, cost);
        System.out.println("最大合金数量: " + result);
    }
}