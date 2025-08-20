import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;

public class M03_TopKConvenience {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.nextLine(); // 消耗換行符
        
        // 使用最小堆來維護Top-K，堆中元素為(銷量, 商品名稱)
        // 當銷量相同時，按照字典序排列（題目未明確要求，但範例顯示銷量相同時按名稱排序）
        PriorityQueue<Product> minHeap = new PriorityQueue<>(k, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                if (p1.qty != p2.qty) {
                    return p1.qty - p2.qty; // 按銷量升序
                } else {
                    return p2.name.compareTo(p1.name); // 銷量相同時按名稱降序（字典序反向）
                }
            }
        });
        
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            int qty = scanner.nextInt();
            scanner.nextLine(); // 消耗換行符
            
            Product product = new Product(name, qty);
            
            if (minHeap.size() < k) {
                minHeap.offer(product);
            } else {
                if (compareProducts(product, minHeap.peek()) > 0) {
                    minHeap.poll();
                    minHeap.offer(product);
                }
            }
        }
        
        // 將結果從堆中取出並反轉順序（從大到小）
        Product[] result = new Product[minHeap.size()];
        int index = minHeap.size() - 1;
        while (!minHeap.isEmpty()) {
            result[index--] = minHeap.poll();
        }
        
        // 輸出結果
        for (Product p : result) {
            System.out.println(p.name + " " + p.qty);
        }
    }
    
    // 比較兩個商品，返回值大於0表示p1應該排在p2前面（在Top-K中）
    private static int compareProducts(Product p1, Product p2) {
        if (p1.qty != p2.qty) {
            return p1.qty - p2.qty;
        } else {
            return p2.name.compareTo(p1.name); // 銷量相同時按名稱降序比較
        }
    }
    
    static class Product {
        String name;
        int qty;
        
        public Product(String name, int qty) {
            this.name = name;
            this.qty = qty;
        }
    }
}/* Time Complexity: O(n log K)
 * 說明：我們維護一個大小為K的最小堆，對每個商品進行一次堆操作（插入或刪除），
 *       每次堆操作的時間複雜度為O(log K)，總共n個商品，因此總時間複雜度為O(n log K)。
 *       當K遠小於n時，這種方法比完全排序更加高效。
 */