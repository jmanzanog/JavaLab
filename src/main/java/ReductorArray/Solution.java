package ReductorArray;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'comparatorValue' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER_ARRAY b
     *  3. INTEGER d
     */

    public static int comparatorValue(List<Integer> a, List<Integer> b, int d) {
        // Write your code here
        Collections.sort(a);
        Collections.sort(b);

        int result = 0;
        for (int i = 0; i < a.size(); i++) {
            int aIntervalStart = a.get(i) - d;
            int aIntervalEnd = a.get(i) + d;

            // find location of a in array b
            int bIntervalStart = 0;
            int bIntervalEnd = b.size() - 1;
            if (aIntervalEnd < b.get(bIntervalStart) || aIntervalStart > b.get(bIntervalEnd)) {
                result++;
            } else {
                while (bIntervalEnd - bIntervalStart > 1) {
                    int middle = (bIntervalStart + bIntervalEnd) / 2;
                    if (b.get(middle) < a.get(i)) {
                        bIntervalStart = middle;
                    } else {
                        bIntervalEnd = middle;
                    }
                }

                if (b.get(bIntervalStart) < aIntervalStart && b.get(bIntervalEnd) > aIntervalEnd) {
                    result++;
                }
            }
        }

        return result;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int aCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = IntStream.range(0, aCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int bCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> b = IntStream.range(0, bCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int d = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.comparatorValue(a, b, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

//https://github.com/krvavizmaj/codechallenges/blob/caed819363ca1e95cf89b66f8c1b9dbbf3f7ee8c/src/phillipscodechalenge/Solution2.java