package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Question06 {
    public static void main(String[] args) throws IOException {
        Q06Part1.run();
        runWithSize(4);
        System.out.println("Single line solution for m: " + 4);
        singleLineSolution(getInput(), 4);
        Q06Part2.run();
        runWithSize(14);
        System.out.println("Single line solution for m: " + 14);
        singleLineSolution(getInput(), 14);
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 6);
    }

    static void runWithSize(int m) throws IOException {
        System.out.println("Running for m: " + m);
        String[] input = Question06.getInput();
        int[] lastIndex = new int[26];
        Arrays.fill(lastIndex, -1 * m);
        int notDuplicate = 0;
        String inp = input[0];
        int i = 0;
        boolean rangeFound = false;
        while (!rangeFound) {
            if (i - lastIndex[inp.charAt(i) - 97] > notDuplicate) {
                notDuplicate++;
            } else {
                notDuplicate = i - lastIndex[inp.charAt(i) - 97];
            }
            lastIndex[inp.charAt(i) - 97] = i;
            if (notDuplicate == m) {
                rangeFound = true;
            }
            i++;
        }
        System.out.println(i);
    }

    private static void singleLineSolution(String[] input, int m) {
        System.out.println(Collections.min(IntStream.range(0, input[0].length() - m).map(i ->
                IntStream.range(i, i + m).mapToObj(index1 ->
                        IntStream.range(index1 + 1, i + m).mapToObj(index2 ->
                                input[0].charAt(index1) != input[0].charAt(index2)
                        ).reduce(true, Boolean::logicalAnd)).reduce(true, Boolean::logicalAnd) ?
                        i + m : Integer.MAX_VALUE).boxed().collect(Collectors.toList())));
    }
}

class Q06Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question06.getInput();
        String inp = input[0];
        char char1 = inp.charAt(0);
        char char2 = inp.charAt(1);
        char char3 = inp.charAt(2);
        char char4 = inp.charAt(3);
        int index = 4;
        while (char1 == char2 || char1 == char3 || char1 == char4 || char2 == char3 || char2 == char4 || char3 == char4) {
            char1 = char2;
            char2 = char3;
            char3 = char4;
            char4 = inp.charAt(index);
            index++;
        }
        System.out.println(index);
    }
}

class Q06Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question06.getInput();
        String inp = input[0];
        char[] charArr = new char[14];
        for (int i = 0; i < charArr.length; i++) {
            charArr[i] = inp.charAt(i);
        }
        int index = 14;
        boolean notFound = false;
        for (int i = 0; i < charArr.length - 1; i++) {
            for (int j = i + 1; j < charArr.length; j++) {
                if (charArr[i] == charArr[j]) {
                    notFound = true;
                }
            }
        }
        while (notFound) {
            for (int i = 0; i < charArr.length - 1; i++) {
                charArr[i] = charArr[i+1];
            }
            charArr[13] = inp.charAt(index);
            index++;
            notFound = false;
            for (int i = 0; i < charArr.length - 1; i++) {
                for (int j = i + 1; j < charArr.length; j++) {
                    if (charArr[i] == charArr[j]) {
                        notFound = true;
                    }
                }
            }
        }
        System.out.println(index);
    }
}
