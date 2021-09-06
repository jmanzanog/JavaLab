import java.math.BigInteger;
import java.util.Map;

public class GridTravelerTabulation {

    static int normalCalc(int m, int n) {
        int[][] table = new int[m + 1][n + 1];
        if (n == 1 && m == 1) {
            return 1;
        }
        table[1][1] = 1;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                int current = table[i][j];
                if (j + 1 <= n) {
                    table[i][j + 1] += current;
                }
                if (i + 1 <= m) {
                    table[i + 1][j] += current;
                }
            }
        }

        // System.out.println(Arrays.deepToString(table));
        return table[m][n];
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

        System.out.println(normalCalc(1, 1));
        System.out.println(normalCalc(2, 3));
        System.out.println(normalCalc(3, 2));
        System.out.println(normalCalc(3, 3));
        System.out.println(normalCalc(18, 18));
        // System.out.println(memoizationCalc(3, 3, new HashMap<>()));
        // System.out.println(memoizationCalc(18, 18, new HashMap<>()));
        // System.out.println(memoizationCalc(1000, new HashMap<>()));
    }
}
