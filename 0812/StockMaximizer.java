import java.util.PriorityQueue;

public class StockMaximizer {
    public static int maxProfit(int K, int[] prices) {
        if (prices == null || prices.length < 2 || K <= 0) {
            return 0;
        }

        // 使用最小堆來存儲買入價格
        PriorityQueue<Integer> buyHeap = new PriorityQueue<>();
        // 使用最大堆來存儲潛在利潤
        PriorityQueue<Integer> profitHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < prices.length; i++) {
            // 檢查是否有更低的買入價格
            if (!buyHeap.isEmpty() && prices[i] > buyHeap.peek()) {
                int profit = prices[i] - buyHeap.poll();
                profitHeap.offer(profit);
                // 將當前價格重新放入買入堆，因為可能可以再次交易
                buyHeap.offer(prices[i]);
            }
            // 總是將當前價格視為潛在買入點
            buyHeap.offer(prices[i]);
        }

        int totalProfit = 0;
        // 取出前K個最大利潤
        for (int i = 0; i < K && !profitHeap.isEmpty(); i++) {
            totalProfit += profitHeap.poll();
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        // 測試案例1
        int[] prices1 = {2, 4, 1};
        int K1 = 2;
        System.out.println(maxProfit(K1, prices1)); // 2

        // 測試案例2
        int[] prices2 = {3, 2, 6, 5, 0, 3};
        int K2 = 2;
        System.out.println(maxProfit(K2, prices2)); // 7

        // 測試案例3
        int[] prices3 = {1, 2, 3, 4, 5};
        int K3 = 2;
        System.out.println(maxProfit(K3, prices3)); // 4

        // 邊界測試：空陣列
        System.out.println(maxProfit(1, new int[0])); // 0

        // 邊界測試：K=0
        System.out.println(maxProfit(0, prices1)); // 0
    }
}