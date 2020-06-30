import java.util.stream.IntStream;


public class MultiplesNaturalNumbers {

    //If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
    //Find the sum of all the multiples of 3 or 5 below 1000.
    private static int target = 1000;

    public static void main(String[] args) {
        //approach 1 ask for module and accumulate totalsum
        int totalSum = IntStream.range(1, target).filter(n -> n % 3 == 0 || n % 5 == 0).sum();
        System.out.println(totalSum);

        //approach 2  the natural numbers 1 + 2 + 3 + 4 + ⋯ is a divergent series described by (p * (p + 1)) / 2
        // so we can represent 3+6+9+12+......+999=3*(1+2+3+4+...+333) = n * (p * (p + 1)) / 2
        System.out.println(SumDivisibleBy(3) + SumDivisibleBy(5) - SumDivisibleBy(15));

    }

    private static int SumDivisibleBy(int n) {
        int p = target / n;
        return n * (p * (p + 1)) / 2;
    }


}
