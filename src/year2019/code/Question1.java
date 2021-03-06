package year2019.code;
import helpers.Helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Question1 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2019/input/Question1.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        int[] values = Helper.StringArrayToInt(input);
        int total = 0;
        for (int val : values) {
            total += Math.floorDiv(val, 3) -2;
        }
        System.out.println(total);
    }

    private static void part2(String[] input) {
        int[] values = Helper.StringArrayToInt(input);
        int total = 0;
        for (int val : values) {
            while (val > 0) {
                int next = Math.floorDiv(val, 3) - 2;
                if (next > 0) {
                    total += next;
                }
                val = next;
            }
        }
        System.out.println(total);
    }
}
