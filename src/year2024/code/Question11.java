package year2024.code;

import helpers.Helper;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question11 {
    public static void main(String[] args) throws IOException {
        Q11Part1.run();
        Q11Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 11);
    }
}

class Q11Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question11.getInput();
        String[] stringVals = input[0].split(" ");
        ArrayList<Long> vals = new ArrayList<>();
        for (String s : stringVals) {
            vals.add(Long.parseLong(s));
        }
        int times = 25;
        for (int t = 0; t < times; t++) {
            for (int index = 0; index < vals.size(); index++) {
                if (vals.get(index) == 0L) {
                    vals.set(index, 1L);
                } else {
                    int digits = (int) Math.floor(Math.log10(vals.get(index))) + 1;
                    if (digits % 2 == 0) {
                        long val1 = Long.parseLong(String.valueOf(vals.get(index)).substring(0, digits / 2));
                        long val2 = Long.parseLong(String.valueOf(vals.get(index)).substring(digits / 2));
                        vals.set(index, val1);
                        index++;
                        vals.add(index, val2);
                    } else {
                        vals.set(index, vals.get(index) * 2024);
                    }
                }
            }
        }
        for (long val : vals) {
            System.out.print(val + " ");
        }
        System.out.println();
        System.out.println(vals.size());
    }
}

class Q11Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question11.getInput();
        String[] stringVals = input[0].split(" ");
        ArrayList<Long> vals = new ArrayList<>();
        for (String s : stringVals) {
            vals.add(Long.parseLong(s));
        }
        int times = 75;
        HashMap<Pair<Long, Integer>, Long> amountOfItemsForStartValAndDepth = new HashMap<>();
        long count = 0;
        for (int index = 0; index < vals.size(); index++) {
            count += countItemsForStartValAndDepth(vals.get(index), times, amountOfItemsForStartValAndDepth);
        }
        System.out.println(count);
    }

    private static long countItemsForStartValAndDepth(long val, int depth, HashMap<Pair<Long, Integer>, Long> amountOfItemsForStartValAndDepth) {
        if (amountOfItemsForStartValAndDepth.containsKey(Pair.of(val, depth))) {
            return amountOfItemsForStartValAndDepth.get(Pair.of(val, depth));
        } else {
            long count = 0L;
            if (depth == 1) {
                int digits = (int) Math.floor(Math.log10(val)) + 1;
                if (digits % 2 == 0) {
                    count = 2;
                } else {
                    count = 1;
                }
            } else {
                if (val == 0) {
                    count = countItemsForStartValAndDepth(1, depth - 1, amountOfItemsForStartValAndDepth);
                } else {
                    int digits = (int) Math.floor(Math.log10(val)) + 1;
                    if (digits % 2 == 0) {
                        long val1 = Long.parseLong(String.valueOf(val).substring(0, digits / 2));
                        long val2 = Long.parseLong(String.valueOf(val).substring(digits / 2));
                        count = countItemsForStartValAndDepth(val1, depth - 1, amountOfItemsForStartValAndDepth);
                        count += countItemsForStartValAndDepth(val2, depth - 1, amountOfItemsForStartValAndDepth);
                    } else {
                        count = countItemsForStartValAndDepth(val * 2024, depth - 1, amountOfItemsForStartValAndDepth);
                    }
                }
            }
            amountOfItemsForStartValAndDepth.put(Pair.of(val, depth), count);
            return count;
        }
    }
}
