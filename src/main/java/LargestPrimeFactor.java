public class LargestPrimeFactor {
    //The prime factors of 13195 are 5, 7, 13 and 29.
    //What is the largest prime factor of the number 600851475143 ?
    public static void main(String[] args) {
        System.out.println(lastPrimeFactor(600851475143l));
        System.out.println(lastPrimeFactor2(21));
    }


    //approach 1
    static long lastPrimeFactor(long n) {
        long factor = 2;
        long lastFactor = 1;
        while (n > 1) {
            if (n % factor == 0) {
                lastFactor = factor;
                n = n / factor;
                while (n % factor == 0) {
                    n = n / factor;
                }
            }
            factor = factor + 1;

        }

        return lastFactor;
    }

    static long lastPrimeFactor2(long n) {
        long factor;
        long lastFactor = 1;
        long maxFactor;
        if (n % 2 == 0) {
            lastFactor = 2;
            n = n / 2;
            while (n % 2 == 0) {
                n = n / 2;
            }
        }
        factor = 3;
        maxFactor = (long) Math.sqrt(n);
        while (n > 1 && factor <= maxFactor) {
            if (n % factor == 0) {
                n = n / factor;
                lastFactor = factor;
                while (n % factor == 0) {
                    n = n / factor;
                }
                maxFactor = (long) Math.sqrt(n);
            }
            factor = factor + 2;
        }
        if (n == 1) {
            return lastFactor;
        } else {
            return n;
        }
    }
}
