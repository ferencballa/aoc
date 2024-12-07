package year2024.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;

public class Question07 {
    public static void main(String[] args) throws IOException {
        Q07Part1.run();
        Q07Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 7);
    }
}

class Q07Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question07.getInput();
        long count = 0;
        for (String line : input) {
            String[] parts = line.split(": ");
            long result = Long.parseLong(parts[0]);
            ArrayList<Long> vals = Helper.StringArrayToLongArrayList(parts[1].split(" "));
            long comb = vals.remove(0);
            if (validResult(result, comb, (ArrayList<Long>) vals.clone(), true) ||validResult(result, comb, (ArrayList<Long>) vals.clone(), false)) {
                count += result;
            }
        }
        System.out.println(count);
    }

    private static boolean validResult(long result, long comb, ArrayList<Long> vals, boolean add) {
        if (vals.isEmpty()) {
            return result == comb;
        }
        long next = vals.remove(0);
        if (add) {
            comb += next;
        } else {
            comb *= next;
        }
        if (comb > result) {
            return false;
        } else {
            return validResult(result, comb, (ArrayList<Long>) vals.clone(), true) ||validResult(result, comb, (ArrayList<Long>) vals.clone(), false);
        }
    }
}

class Q07Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question07.getInput();
        long count = 0;
        for (String line : input) {
            String[] parts = line.split(": ");
            long result = Long.parseLong(parts[0]);
            ArrayList<Long> vals = Helper.StringArrayToLongArrayList(parts[1].split(" "));
            long comb = vals.remove(0);
            if (validResult(result, comb, (ArrayList<Long>) vals.clone(), 0) ||validResult(result, comb, (ArrayList<Long>) vals.clone(), 1) ||validResult(result, comb, (ArrayList<Long>) vals.clone(), 2)) {
                count += result;
            }
        }
        System.out.println(count);
    }

    private static boolean validResult(long result, long comb, ArrayList<Long> vals, int op) {
        if (vals.isEmpty()) {
            return result == comb;
        }
        long next = vals.remove(0);
        if (op == 0) {
            comb += next;
        } else if (op == 1) {
            comb *= next;
        } else {
            comb = Long.parseLong(Long.toString(comb).concat(Long.toString(next)));
        }
        if (comb > result) {
            return false;
        } else {
            return validResult(result, comb, (ArrayList<Long>) vals.clone(), 0) ||validResult(result, comb, (ArrayList<Long>) vals.clone(), 1) ||validResult(result, comb, (ArrayList<Long>) vals.clone(), 2);
        }
    }
}