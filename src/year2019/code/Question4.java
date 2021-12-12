package year2019.code;
import helpers.Helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Question4 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2019/input/Question4.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        String[] values = input[0].split("-");
        int[] bounds = Helper.StringArrayToInt(values);
        int count = 0;
        for (int i = bounds[0]; i <= bounds[1]; i++) {
            int d1 = Math.floorDiv(i, 100000);
            int d2 = Math.floorDiv(i - d1 * 100000, 10000);
            int d3 = Math.floorDiv(i - d1 * 100000 - d2 * 10000, 1000);
            int d4 = Math.floorDiv(i - d1 * 100000 - d2 * 10000 - d3 * 1000, 100);
            int d5 = Math.floorDiv(i - d1 * 100000 - d2 * 10000 - d3 * 1000 - d4 * 100, 10);
            int d6 = i - d1 * 100000 - d2 * 10000 - d3 * 1000 - d4 * 100 - d5 * 10;
            if (d1 <= d2 && d2 <= d3 && d3 <= d4 && d4 <= d5 && d5 <= d6 &&
                    (d1 == d2 || d2 == d3 || d3 == d4 || d4 == d5 || d5 == d6)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static void part2(String[] input) {
        String[] values = input[0].split("-");
        int[] bounds = Helper.StringArrayToInt(values);
        int count = 0;
        for (int i = bounds[0]; i <= bounds[1]; i++) {
            int d1 = Math.floorDiv(i, 100000);
            int d2 = Math.floorDiv(i - d1 * 100000, 10000);
            int d3 = Math.floorDiv(i - d1 * 100000 - d2 * 10000, 1000);
            int d4 = Math.floorDiv(i - d1 * 100000 - d2 * 10000 - d3 * 1000, 100);
            int d5 = Math.floorDiv(i - d1 * 100000 - d2 * 10000 - d3 * 1000 - d4 * 100, 10);
            int d6 = i - d1 * 100000 - d2 * 10000 - d3 * 1000 - d4 * 100 - d5 * 10;
            if (d1 <= d2 && d2 <= d3 && d3 <= d4 && d4 <= d5 && d5 <= d6 &&
                    ((d1 == d2 && d2 != d3) || (d2 == d3 && d1 != d2 && d3 != d4) || (d3 == d4 && d2 != d3 && d4 != d5) || (d4 == d5 && d3 != d4 && d5 != d6) || (d5 == d6 && d4 != d5))) {
                count++;
            }
        }
        System.out.println(count);
    }
}
