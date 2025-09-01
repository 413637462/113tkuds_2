import java.util.*;

public class MovingAverage {
    private int size;
    private Queue<Integer> window;
    private double sum;

    public MovingAverage(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.sum = 0;
    }

    public double next(int val) {
        if (window.size() == size) {
            sum -= window.poll();
        }
        window.offer(val);
        sum += val;
        return sum / window.size();
    }

    public double getMedian() {
        List<Integer> temp = new ArrayList<>(window);
        Collections.sort(temp);
        int n = temp.size();
        if (n % 2 == 0)
            return (temp.get(n / 2 - 1) + temp.get(n / 2)) / 2.0;
        else
            return temp.get(n / 2);
    }

    public int getMin() {
        return Collections.min(window);
    }

    public int getMax() {
        return Collections.max(window);
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);
        System.out.println(ma.next(1)); // 1.0
        System.out.println(ma.next(10)); // 5.5
        System.out.println(ma.next(3)); // 4.666...
        System.out.println(ma.next(5)); // 6.0
        System.out.println("Median: " + ma.getMedian()); // 5
        System.out.println("Min: " + ma.getMin()); // 3
        System.out.println("Max: " + ma.getMax()); // 10
    }
}
