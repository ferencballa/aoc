package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Question03 {
    public static void main(String[] args) throws IOException {
        Q03Part1.run();
        Q03Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 3);
    }
}

class Q03Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question03.getInput();
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
        System.out.println("1 line function:");
        singleLineSolution(input);
    }

    private static void singleLineSolution(String[] input) {
        System.out.println(Arrays.stream(input).map(line ->
                        Collections.max(Arrays.stream(line.substring(0, line.length() / 2).split("")).flatMap(ch1 ->
                                        Arrays.stream(line.substring(line.length() / 2, line.length()).split("")).map(ch2 ->
                                                ch1.equals(ch2) ? (int) ch1.charAt(0) : 0
                                                )
                                ).collect(Collectors.toList()))
                )
                .map(n -> n >= 97 ? n-96 : n-38).reduce(0, Integer::sum));
    }
}

class Q03Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question03.getInput();
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
        System.out.println("1 line function:");
        singleLineSolution(input);
    }

    private static void singleLineSolution(String[] input) {
        System.out.println(IntStream.range(0, input.length / 3).map(i ->
                Collections.max(Arrays.stream(input[3 * i].split("")).flatMap(ch1 ->
                                Arrays.stream(input[3 * i + 1].split("")).flatMap(ch2 ->
                                        Arrays.stream(input[3 * i + 2].split("")).map(ch3 ->
                                                ch1.equals(ch2) && ch1.equals(ch3) ? (int) ch1.charAt(0) : 0
                                        ))).collect(Collectors.toList()))
                )
                .map(n -> n >= 97 ? n-96 : n-38).reduce(0, Integer::sum));
    }
}
