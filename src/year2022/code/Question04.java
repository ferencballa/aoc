package year2022.code;

import helpers.Helper;

import java.io.IOException;

public class Question04 {
    public static void main(String[] args) throws IOException {
        Q04Part1.run();
        Q04Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 4);
    }
}

class Q04Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question04.getInput();
        int overlap = 0;
        for (int i = 0; i < input.length; i++) {
            String[] lineParts = input[i].split(",");
            String[] left = lineParts[0].split("-");
            String[] right = lineParts[1].split("-");
            int leftLow = Integer.parseInt(left[0]);
            int leftHigh = Integer.parseInt(left[1]);
            int rightLow = Integer.parseInt(right[0]);
            int rightHigh = Integer.parseInt(right[1]);
            if ((leftLow <= rightLow && leftHigh >= rightHigh || (rightLow <= leftLow && rightHigh >= leftHigh))) {
                overlap++;
            }
        }
        System.out.println(overlap);
    }
}

class Q04Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question04.getInput();
        int overlap = 0;
        for (int i = 0; i < input.length; i++) {
            String[] lineParts = input[i].split(",");
            String[] left = lineParts[0].split("-");
            String[] right = lineParts[1].split("-");
            int leftLow = Integer.parseInt(left[0]);
            int leftHigh = Integer.parseInt(left[1]);
            int rightLow = Integer.parseInt(right[0]);
            int rightHigh = Integer.parseInt(right[1]);
            if (
                    leftLow == rightLow ||
                    leftLow == rightHigh ||
                    leftHigh == rightLow ||
                    leftHigh == rightHigh ||
                    (leftLow < rightLow && leftHigh >= rightLow) ||
                    (rightLow < leftLow && rightHigh >= leftLow)
            ) {
                overlap++;
            }
        }
        System.out.println(overlap);
    }
}
