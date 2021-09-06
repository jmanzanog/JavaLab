import java.util.Arrays;
import java.util.List;

public class CanSumTabulation {

    static boolean normalCalc(int targetSum, int[] numbers) {
        boolean[] table = new boolean[targetSum + 1];
        table[0] = true;
        for (int i = 0; i <= targetSum; i++) {
            if (table[i]) {
                for (int num : numbers) {
                    if (i + num <= targetSum) {
                        table[i + num] = true;
                    }
                }
            }
        }
        return table[targetSum];
    }

    public static void main(String[] args) {

//        System.out.println(normalCalc(7, new int[]{2, 3}));
//        System.out.println(normalCalc(7, new int[]{5, 3, 4, 7}));
//        System.out.println(normalCalc(7, new int[]{2, 4}));
//        System.out.println(normalCalc(8, new int[]{2, 3, 5}));
//        System.out.println(normalCalc(300, new int[]{7, 14}));

//        List<Integer> ar = Arrays.asList(1, 2, 3, 4, 10, 11);
//        System.out.println(ar.stream().reduce(0, Integer::sum));
//
//        List<Integer> a = null;
//        List<Integer> b = null;
//
//        int a1 = 0,b1 = 0;
//        for (int i =0; i<=2; i++){
//            if (a.get(i) > b.get(i)) a1++;
//            if (a.get(i) < b.get(i)) b1++;
//        }
//        int[] c=  new int[]{a1,b1};

    }

}
