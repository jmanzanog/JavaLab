import java.util.HashMap;
import java.util.Map;

public class CanSumRecursive {

    static Boolean normalCalc(int targetSum, int[] numbers) {
        if (targetSum == 0) return true;
        if (targetSum < 0) return false;
        int root;
        for (int number : numbers) {
            root = targetSum - number;
            if (normalCalc(root, numbers)) {
                return true;
            }
        }

        return false;
    }

    static Boolean memoizationCalc(int targetSum, int[] numbers, Map<Integer, Boolean> memo) {
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        if (targetSum == 0) return true;
        if (targetSum < 0) return false;
        int root = 0;
        for (int number : numbers) {
            root = targetSum - number;
            if (memoizationCalc(root, numbers, memo)) {
                memo.put(root, true);
                return true;
            }
        }
        memo.put(root, false);
        return false;
    }


    public static void main(String[] args) {

//        System.out.println(normalCalc(7, new int[]{2, 3}));
//        System.out.println(normalCalc(7, new int[]{2, 4}));
//        System.out.println(normalCalc(8, new int[]{2, 3, 5}));
        //System.out.println(normalCalc(300, new int[]{7, 14}));
        System.out.println(memoizationCalc(300, new int[]{7, 14}, new HashMap<>()));

    }
}
