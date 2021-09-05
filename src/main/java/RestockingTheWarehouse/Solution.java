package RestockingTheWarehouse;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'restock' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY itemCount
     *  2. INTEGER target
     */

    public static int restock(List<Integer> itemCount, int target) {
        // Write your code here
        int purchased = 0;
        for (Integer i : itemCount) {
            purchased += i;
            if (purchased >= target)
                break;
        }
        return Math.abs(target - purchased);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int itemCountCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> itemCount = IntStream.range(0, itemCountCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int target = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.restock(itemCount, target);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
//https://github.com/leyicai/LeetCode-Solution/blob/50a9cea7ccfe9baae4de3891834e9b3c30e56d50/java/RestockingTheWarehouse.java