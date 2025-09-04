package finalexam;

import java.util.*;

public class LC39_CombinationSum_PPE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] candidates = new int[n];
        
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }
        
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        
        for (List<Integer> combination : result) {
            for (int i = 0; i < combination.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(combination.get(i));
            }
            System.out.println();
        }
    }
    
    private static void backtrack(List<List<Integer>> result, List<Integer> temp, 
                                 int[] candidates, int remain, int start) {
        if (remain < 0) return;
        if (remain == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            temp.add(candidates[i]);
            backtrack(result, temp, candidates, remain - candidates[i], i);
            temp.remove(temp.size() - 1);
        }
    }
}