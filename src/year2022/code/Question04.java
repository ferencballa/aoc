package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

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
        for (String s : input) {
            String[] lineParts = s.split(",");
            String[] left = lineParts[0].split("-");
            String[] right = lineParts[1].split("-");
            int leftLow = Integer.parseInt(left[0]);
            int leftHigh = Integer.parseInt(left[1]);
            int rightLow = Integer.parseInt(right[0]);
            int rightHigh = Integer.parseInt(right[1]);
            if ((leftLow <= rightLow && leftHigh >= rightHigh) || (rightLow <= leftLow && rightHigh >= leftHigh)) {
                overlap++;
            }
        }
        System.out.println(overlap);
        System.out.println("1 line function:");
        singleLineSolution(input);
    }

    private static void singleLineSolution(String[] input) {
        System.out.println(Arrays.stream(input).map(line ->
                (
                        (Integer.parseInt(Arrays.stream(line.split(",")).flatMap(linePart -> Arrays.stream(linePart.split("-"))).collect(Collectors.toList()).get(0)) <=
                        Integer.parseInt(Arrays.stream(line.split(",")).flatMap(linePart -> Arrays.stream(linePart.split("-"))).collect(Collectors.toList()).get(2)) &&
                        Integer.parseInt(Arrays.stream(line.split(",")).flatMap(linePart -> Arrays.stream(linePart.split("-"))).collect(Collectors.toList()).get(1)) >=
                        Integer.parseInt(Arrays.stream(line.split(",")).flatMap(linePart -> Arrays.stream(linePart.split("-"))).collect(Collectors.toList()).get(3))) ||
                        (Integer.parseInt(Arrays.stream(line.split(",")).flatMap(linePart -> Arrays.stream(linePart.split("-"))).collect(Collectors.toList()).get(2)) <=
                        Integer.parseInt(Arrays.stream(line.split(",")).flatMap(linePart -> Arrays.stream(linePart.split("-"))).collect(Collectors.toList()).get(0)) &&
                        Integer.parseInt(Arrays.stream(line.split(",")).flatMap(linePart -> Arrays.stream(linePart.split("-"))).collect(Collectors.toList()).get(3)) >=
                        Integer.parseInt(Arrays.stream(line.split(",")).flatMap(linePart -> Arrays.stream(linePart.split("-"))).collect(Collectors.toList()).get(1)))
                ) ? 1 : 0
        ).reduce(0, Integer::sum));
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
        for (String s : input) {
            String[] lineParts = s.split(",");
            String[] left = lineParts[0].split("-");
            String[] right = lineParts[1].split("-");
            int leftLow = Integer.parseInt(left[0]);
            int leftHigh = Integer.parseInt(left[1]);
            int rightLow = Integer.parseInt(right[0]);
            int rightHigh = Integer.parseInt(right[1]);
            if (!(leftHigh < rightLow || leftLow > rightHigh)) {
                overlap++;
            }
        }
        System.out.println(overlap);
        System.out.println("1 line function:");
        singleLineSolution(input);
    }

    private static void singleLineSolution(String[] input) {
        System.out.println(Arrays.stream(input).map(line ->
                (
                        Integer.parseInt(Arrays.stream(line.split(",")).flatMap(linePart -> Arrays.stream(linePart.split("-"))).collect(Collectors.toList()).get(1)) <
                        Integer.parseInt(Arrays.stream(line.split(",")).flatMap(linePart -> Arrays.stream(linePart.split("-"))).collect(Collectors.toList()).get(2)) ||
                        Integer.parseInt(Arrays.stream(line.split(",")).flatMap(linePart -> Arrays.stream(linePart.split("-"))).collect(Collectors.toList()).get(0)) >
                        Integer.parseInt(Arrays.stream(line.split(",")).flatMap(linePart -> Arrays.stream(linePart.split("-"))).collect(Collectors.toList()).get(3))
                ) ? 0 : 1
        ).reduce(0, Integer::sum));
    }
}
