import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class GridTravelerRecursive {

    static BigInteger normalCalc(int n, int m) {
        if (n == 1 && m == 1) {
            return BigInteger.ONE;
        }
        if (n == 0 || m == 0) {
            return BigInteger.ZERO;
        }
        return normalCalc(n - 1, m).add(normalCalc(n, m - 1));
    }

    static BigInteger memoizationCalc(Integer n, Integer m, Map<String, BigInteger> memo) {
        // System.out.println(memo);
        String key = n + "_" + m;
        if (memo.containsKey(key)) return memo.get(key);
        if (n == 1 && m == 1) {
            return BigInteger.ONE;
        }
        if (n == 0 || m == 0) {
            return BigInteger.ZERO;
        }
        memo.put(key, memoizationCalc(n - 1, m, memo).add(memoizationCalc(n, m - 1, memo)));
        return memo.get(key);
    }


    public static void main(String[] args) {

        System.out.println(normalCalc(3, 3));
        System.out.println(memoizationCalc(3, 3, new HashMap<>()));
        System.out.println(memoizationCalc(18, 18, new HashMap<>()));
        // System.out.println(memoizationCalc(1000, new HashMap<>()));
    }
}
