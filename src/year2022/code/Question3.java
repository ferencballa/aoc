package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.HashMap;

public class Question3 {
    public static void main(String[] args) throws IOException {
        Q3Part1.run();
        Q3Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 3);
    }
}

class Q3Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question3.getInput();
        int[][] inputsAsInts = new int[input.length][];
        for (int i = 0; i < input.length; i++) {
            inputsAsInts[i] = new int[input[i].length()];
            for (int j = 0; j < inputsAsInts[i].length; j++) {
                int val = (int) input[i].charAt(j);
                if (val >= 97) {
                    inputsAsInts[i][j] = val - 96;
                } else {
                    inputsAsInts[i][j] = val - 38;
                }
            }
        }
        int totalPrios = 0;
        for (int i = 0; i < input.length; i++) {
            int totalLineLength = inputsAsInts[i].length;
            HashMap<Integer, Integer> numberInLeft = new HashMap<>();
            for (int j = 0; j < totalLineLength/2; j++) {
                numberInLeft.put(inputsAsInts[i][j], 1);
            }
            boolean badValFound = false;
            for (int j = totalLineLength/2; j < totalLineLength; j++) {
                Integer numberInLeftAmount = numberInLeft.get(inputsAsInts[i][j]);
                if (!badValFound && numberInLeftAmount != null) {
                    totalPrios += inputsAsInts[i][j];
                    badValFound = true;
                }
            }
        }
        System.out.println(totalPrios);
    }
}

class Q3Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question3.getInput();
        int[][] inputsAsInts = new int[input.length][];
        for (int i = 0; i < input.length; i++) {
            inputsAsInts[i] = new int[input[i].length()];
            for (int j = 0; j < inputsAsInts[i].length; j++) {
                int val = (int) input[i].charAt(j);
                if (val >= 97) {
                    inputsAsInts[i][j] = val - 96;
                } else {
                    inputsAsInts[i][j] = val - 38;
                }
            }
        }
        int totalPrios = 0;
        for (int i = 0; i < input.length; i+=3) {
            int totalLineLength1 = inputsAsInts[i].length;
            HashMap<Integer, Integer> numberInLeft1 = new HashMap<>();
            for (int j = 0; j < totalLineLength1; j++) {
                numberInLeft1.put(inputsAsInts[i][j], 1);
            }
            int totalLineLength2 = inputsAsInts[i+1].length;
            HashMap<Integer, Integer> numberInLeft2 = new HashMap<>();
            for (int j = 0; j < totalLineLength2; j++) {
                numberInLeft2.put(inputsAsInts[i+1][j], 1);
            }
            boolean badValFound = false;
            int totalLineLength3 = inputsAsInts[i+2].length;
            for (int j = 0; j < totalLineLength3; j++) {
                Integer numberInLeftAmount1 = numberInLeft1.get(inputsAsInts[i+2][j]);
                Integer numberInLeftAmount2 = numberInLeft2.get(inputsAsInts[i+2][j]);
                if (!badValFound && numberInLeftAmount1 != null && numberInLeftAmount2 != null) {
                    totalPrios += inputsAsInts[i+2][j];
                    badValFound = true;
                }
            }
        }
        System.out.println(totalPrios);
    }
}
