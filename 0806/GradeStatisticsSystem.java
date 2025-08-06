import java.util.Arrays;

public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] grades = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};
        double avg = Arrays.stream(grades).average().orElse(0);
        int max = Arrays.stream(grades).max().getAsInt();
        int min = Arrays.stream(grades).min().getAsInt();
        
        int[] gradeCounts = new int[5];
        Arrays.stream(grades).forEach(g -> {
            if(g >= 90) gradeCounts[0]++;
            else if(g >= 80) gradeCounts[1]++;
            else if(g >= 70) gradeCounts[2]++;
            else if(g >= 60) gradeCounts[3]++;
            else gradeCounts[4]++;
        });
        
        long aboveAvg = Arrays.stream(grades).filter(g -> g > avg).count();
        
        System.out.println("成績: " + Arrays.toString(grades));
        System.out.printf("平均: %.1f 最高: %d 最低: %d\n", avg, max, min);
        System.out.printf("A:%d B:%d C:%d D:%d F:%d\n", gradeCounts[0], gradeCounts[1], gradeCounts[2], gradeCounts[3], gradeCounts[4]);
        System.out.println("高於平均: " + aboveAvg);
    }
}