import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FibonacciRecursive {

    static BigInteger normalCalc(int n) {
        if (n <= 2) {
            return BigInteger.ONE;
        }
        return normalCalc(n - 1).add(normalCalc(n - 2));
    }

    static BigInteger memoizationCalc(Integer n, Map<Integer, BigInteger> memo) {
        // System.out.println(memo);
        if (memo.containsKey(n)) return memo.get(n);
        if (n <= 2) {
            return BigInteger.ONE;
        }
        memo.put(n, memoizationCalc(n - 1, memo).add(memoizationCalc(n - 2, memo)));
        return memo.get(n);
    }


    public static void main(String[] args) {
//        System.out.println(normalCalc(6));
//        System.out.println(normalCalc(7));
//        System.out.println(normalCalc(8));
        System.out.println(memoizationCalc(1000, new HashMap<>()));
    }
}
