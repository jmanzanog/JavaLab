import java.util.Arrays;
import java.util.Map;

public class BestSumRecursive {
    private static int[] shortestCombination;

    static int[] normalCalc(int targetSum, int[] numbers) {
        if (targetSum == 0) return new int[]{};
        if (targetSum < 0) return null;
        for (int number : numbers) {
            int root = targetSum - number;
            int[] result = normalCalc(root, numbers);
            if (result != null) {
                int[] combination = Arrays.copyOf(result, result.length + 1);
                combination[combination.length - 1] = number;
                shortestCombination = combination;
            }
        }

        return shortestCombination;
    }


    static int[] memoizationCalc(int targetSum, int[] numbers, Map<Integer, int[]> memo) {
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        if (targetSum == 0) return new int[]{};
        if (targetSum < 0) return null;
        int root = 0;
        for (int number : numbers) {
            root = targetSum - number;
            int[] result = memoizationCalc(root, numbers, memo);
            if (result != null) {
                int[] destArray = Arrays.copyOf(result, result.length + 1);
                destArray[destArray.length - 1] = number;
                memo.put(root, destArray);
                return destArray;
            }
        }
        memo.put(root, null);
        return null;
    }


    public static void main(String[] args) {

//        System.out.println(normalCalc(7, new int[]{2, 3}));
//        System.out.println(normalCalc(7, new int[]{2, 4}));
//        System.out.println(normalCalc(8, new int[]{2, 3, 5}));
        //System.out.println(Arrays.toString(normalCalc(7, new int[]{5, 3, 4 ,7})));
        System.out.println(Arrays.toString(normalCalc(8, new int[]{1, 4, 5})));
        // System.out.println(Arrays.toString(memoizationCalc(7, new int[]{2, 3}, new HashMap<>())));
        //System.out.println(Arrays.toString(memoizationCalc(300, new int[]{7, 14}, new HashMap<>())));

    }
}
