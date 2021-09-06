import java.util.Arrays;

public class BestSumTabulation {

    static Integer[] normalCalc(int targetSum, int[] numbers) {
        Integer[][] table = new Integer[targetSum + 1][];
        table[0] = new Integer[]{};
        for (int i = 0; i <= targetSum; i++) {
            if (table[i] != null) {
                for (int num : numbers) {
                    if (i + num <= targetSum) {
                        Integer[] unionArray = Arrays.copyOf(table[i], table[i].length + 1);
                        unionArray[unionArray.length - 1] = num;
                        if (table[i + num] == null || table[i + num].length > unionArray.length) {
                            table[i + num] = unionArray;
                        }
                    }
                }
            }
        }
        return table[targetSum];
    }


    public static void main(String[] args) {


        System.out.println(Arrays.toString(normalCalc(7, new int[]{5, 3, 4, 7})));
        System.out.println(Arrays.toString(normalCalc(8, new int[]{2, 3, 5})));
        System.out.println(Arrays.toString(normalCalc(8, new int[]{1, 4, 5})));
        System.out.println(Arrays.toString(normalCalc(100, new int[]{1, 2, 5, 25})));
        System.out.println(Arrays.toString(normalCalc(100, new int[]{25, 1, 5, 2})));


    }
}
