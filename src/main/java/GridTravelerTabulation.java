import java.math.BigInteger;
import java.util.Map;

public class GridTravelerTabulation {

    static BigInteger normalCalc(int m, int n) {
        BigInteger[][] table = new BigInteger[m + 1][n + 1];
        if (n == 1 && m == 1) {
            return BigInteger.ONE;
        }
        table[1][1] = BigInteger.ONE;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (table[i][j] == null) {
                    table[i][j] = BigInteger.ZERO;
                }
                BigInteger current = table[i][j];
                if (j + 1 <= n) {
                    table[i][j + 1] = current.add(table[i][j + 1] == null ? BigInteger.ZERO : table[i][j + 1]);
                }
                if (i + 1 <= m) {
                    table[i + 1][j] = current.add(table[i + 1][j] == null ? BigInteger.ZERO : table[i + 1][j]);
                }
            }
        }

        // System.out.println(Arrays.deepToString(table));
        return table[m][n];
    }

    public static void main(String[] args) {

        System.out.println(normalCalc(1, 1));
        System.out.println(normalCalc(2, 3));
        System.out.println(normalCalc(3, 2));
        System.out.println(normalCalc(3, 3));
        System.out.println(normalCalc(18, 18));
    }
}
