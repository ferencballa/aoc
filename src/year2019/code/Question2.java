package year2019.code;
import helpers.Helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Question2 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2019/input/Question2.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        String[] stringValues = input[0].split(",");
        int[] values = Helper.StringArrayToInt(stringValues);
        values[1] = 12;
        values[2] = 2;
        int curPos = 0;
        while (values[curPos] != 99) {
            if (values[curPos] == 1) {
                values[values[curPos + 3]] = values[values[curPos + 1]] + values[values[curPos + 2]];
            } else if (values[curPos] == 2) {
                values[values[curPos + 3]] = values[values[curPos + 1]] * values[values[curPos + 2]];
            } else {
                System.out.println("Something went wrong");
            }
            curPos += 4;
        }
        System.out.println(values[0]);
    }

    private static void part2(String[] input) {
        String[] stringValues = input[0].split(",");
        int[] vals = Helper.StringArrayToInt(stringValues);
        int noun = -1;
        int verb = -1;
        totalLoop:
        for (int i = 0; i < 100; i++) {
            noun = i;
            for (int j = 0; j < 100; j++) {
                int[] values = new int[vals.length];
                System.arraycopy(vals, 0, values, 0, vals.length);
                verb = j;
                values[1] = i;
                values[2] = j;
                int curPos = 0;
                while (values[curPos] != 99) {
                    if (values[curPos] == 1) {
                        values[values[curPos + 3]] = values[values[curPos + 1]] + values[values[curPos + 2]];
                    } else if (values[curPos] == 2) {
                        values[values[curPos + 3]] = values[values[curPos + 1]] * values[values[curPos + 2]];
                    } else {
                        System.out.println("Something went wrong");
                    }
                    curPos += 4;
                }
                if (values[0] == 19690720) {
                    break totalLoop;
                }
            }
        }
        System.out.println(100 * noun + verb);
    }
}
