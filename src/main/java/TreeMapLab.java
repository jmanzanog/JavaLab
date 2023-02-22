import java.util.ArrayList;
import java.util.Collection;
import java.util.NavigableMap;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class TreeMapLab {
    public static void main(String[] args) {
        String[][] binRanges = {
                {"4444 4444 11", "4444 4444 44", "Visa credit"},
                {"4500 0000 55", "4999 9999 00", "Visa debit"},
                {"4999 9999 99", "5555 0000 00", "Master credit"},
                {"6666 4444 11", "7777 0000 00", "Amex"}
        };
        String cardNumber = "4733 6109 7901 2139";
        String cardType = findCardType(binRanges, cardNumber);
        System.out.println("Card Type: " + cardType);

    }

    private static String findCardType(String[][] binRanges, String cardNumber) {
        String bin = cardNumber.replaceAll(" ", "").substring(0, 10);
        NavigableMap<String, String> rangeMap = new TreeMap<>();

        for (String[] range : binRanges) {
            String start = range[0].replaceAll(" ", "");
            String end = range[1].replaceAll(" ", "");
            rangeMap.put(start, range[2]);
            rangeMap.put(end, null);
        }

        return rangeMap.floorEntry(bin).getValue();
    }
}
