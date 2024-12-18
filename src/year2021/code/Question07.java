package year2021.code;
import helpers.Helper;

import java.io.IOException;

public class Question07 {
    public static void main(String[] args) throws IOException {
        String[] inp = Helper.getInputForYearAndTask(2021, 7);
        int[] input = Helper.StringArrayToIntArray(inp[0].split(","));
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(int[] input) {
        int smallest = 100;
        int biggest = 100;
        for (int inp : input) {
            if (inp < smallest) {
                smallest = inp;
            }
            if (inp > biggest) {
                biggest = inp;
            }
        }
        int smallestDiff = Integer.MAX_VALUE;
        for (int i = smallest; i <= biggest; i++) {
            int diff = 0;
            for (int inp : input) {
                diff += Math.abs(i - inp);
            }
            if (diff < smallestDiff) {
                smallestDiff = diff;
            }
        }
        System.out.println(smallestDiff);
    }

    private static void part2(int[] input) {
        int smallest = 100;
        int biggest = 100;
        for (int inp : input) {
            if (inp < smallest) {
                smallest = inp;
            }
            if (inp > biggest) {
                biggest = inp;
            }
        }
        int smallestDiff = Integer.MAX_VALUE;
        for (int i = smallest; i <= biggest; i++) {
            int diff = 0;
            for (int inp : input) {
                double x = Math.abs(i - inp);
                diff += (x/2) * (x+1);
            }
            if (diff < smallestDiff) {
                smallestDiff = diff;
            }
        }
        System.out.println(smallestDiff);
    }
}
