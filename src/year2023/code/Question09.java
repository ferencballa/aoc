package year2023.code;

import helpers.Helper;

import java.io.IOException;

public class Question09 {
    public static void main(String[] args) throws IOException {
        Q09Part1.run();
        Q09Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 9);
    }
}

class Q09Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question09.getInput();
        long[][] values = new long[input.length][];
        long total = 0;
        for (int i = 0; i < input.length; i++) {
            String[] lineVals = input[i].split(" ");
            values[i] = new long[lineVals.length];
            for (int j = 0; j < lineVals.length; j++) {
                values[i][j] = Long.parseLong(lineVals[j]);
            }
            total += calculateNextVal(values[i]);
        }
        System.out.println(total);
    }

    private static long calculateNextVal(long[] values) {
        boolean allZeroes = true;
        for (long val : values) {
            if (val != 0) {
                allZeroes = false;
                break;
            }
        }
        if (allZeroes) {
            return 0;
        } else {
            long[] valuesDelta = new long[values.length-1];
            for (int i = 0; i < valuesDelta.length; i++) {
                valuesDelta[i] = values[i + 1] - values[i];
            }
            long deltaToNext = calculateNextVal(valuesDelta);
            return values[values.length - 1] + deltaToNext;
        }
    }
}

class Q09Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question09.getInput();
        long[][] values = new long[input.length][];
        long total = 0;
        for (int i = 0; i < input.length; i++) {
            String[] lineVals = input[i].split(" ");
            values[i] = new long[lineVals.length];
            for (int j = 0; j < lineVals.length; j++) {
                values[i][j] = Long.parseLong(lineVals[j]);
            }
            total += calculateNextVal(values[i]);
        }
        System.out.println(total);
    }

    private static long calculateNextVal(long[] values) {
        boolean allZeroes = true;
        for (long val : values) {
            if (val != 0) {
                allZeroes = false;
                break;
            }
        }
        if (allZeroes) {
            return 0;
        } else {
            long[] valuesDelta = new long[values.length-1];
            for (int i = 0; i < valuesDelta.length; i++) {
                valuesDelta[i] = values[i + 1] - values[i];
            }
            long deltaToNext = calculateNextVal(valuesDelta);
            return values[0] - deltaToNext;
        }
    }
}
