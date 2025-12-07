package year2025.code;

import helpers.Helper;

import java.io.IOException;

public class Question06 {
    public static void main(String[] args) throws IOException {
        Q06Part1.run();
        Q06Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2025, 6);
    }
}

class Q06Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question06.getInput();
        String[] operations = input[input.length - 1].split(" +");
        long[][] values = new long[operations.length][input.length - 1];
        for (int i = 0; i < input.length - 1; i++) {
            long[] row = Helper.StringArrayToLongArray(input[i].split(" +"));
            for (int j = 0; j < operations.length; j++) {
                values[j][i] = row[j];
            }
        }
        long count = 0;
        for (int i = 0; i < values.length; i++) {
            long subCount = 0;
            if (operations[i].equals("*")) {
                subCount = 1;
            }
            for (int j = 0; j < values[0].length; j++) {
                if (operations[i].equals("*")) {
                    subCount *= values[i][j];
                } else {
                    subCount += values[i][j];
                }
            }
            count += subCount;
        }
        System.out.println(count);
    }
}

class Q06Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question06.getInput();
        long runningCount = 0;
        long currentCount = 0;
        boolean operationPlus = input[input.length - 1].charAt(0) == '+';
        if (!operationPlus) {
            currentCount = 1;
        }
        int maxLength = 0;
        for (int i = 0; i < input.length; i++) {
            maxLength = Math.max(maxLength, input[i].length());
        }
        for (int i = 0; i < maxLength; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < input.length - 1; j++) {
                if (i < input[j].length() && input[j].charAt(i) != ' ') {
                    sb.append(input[j].charAt(i));
                }
            }
            if (sb.length() != 0) {
                if (operationPlus) {
                    currentCount += Long.parseLong(sb.toString());
                } else {
                    currentCount *= Long.parseLong(sb.toString());
                }
            } else {
                runningCount += currentCount;
                currentCount = 0;
                operationPlus = input[input.length - 1].charAt(i + 1) == '+';
                if (!operationPlus) {
                    currentCount = 1;
                }
            }
        }
        runningCount += currentCount;
        System.out.println(runningCount);
    }
}
