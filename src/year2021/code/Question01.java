package year2021.code;

import helpers.Helper;

import java.io.IOException;

public class Question01 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2021, 1);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        int[] values = Helper.StringArrayToInt(input);
        int increased = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i]> values[i-1]) {
                increased++;
            }
        }
        System.out.println(increased);
    }

    private static void part2(String[] input) {
        int[] values = Helper.StringArrayToInt(input);
        int increased = 0;
        for (int i = 3; i < values.length; i++) {
            if (values[i] + values[i-1] + values[i-2] > values[i-1] + values[i-2] + values [i-3]) {
                increased++;
            }
        }
        System.out.println(increased);
    }
}
