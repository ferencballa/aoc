package year2019.code;
import helpers.Helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Question5 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2019/input/Question5.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        String[] stringValues = input[0].split(",");
        int[] values = Helper.StringArrayToInt(stringValues);
        int curPos = 0;
        while (values[curPos] - Math.floorDiv(values[curPos], 100) * 100 != 99) {
            int curValue = values[curPos];
            int op = curValue - Math.floorDiv(curValue, 100) * 100;
            if (op == 1) {
                int mode3 = Math.floorDiv(curValue, 10000);
                int mode2 = Math.floorDiv(curValue - mode3 * 10000, 1000);
                int mode1 = Math.floorDiv(curValue - mode3 * 10000 - mode2 * 1000, 100);
                int val1 = mode1 == 0 ? values[values[curPos + 1]] : values[curPos + 1];
                int val2 = mode2 == 0 ? values[values[curPos + 2]] : values[curPos + 2];
                values[values[curPos + 3]] = val1 + val2;
                curPos += 4;
            } else if (op == 2) {
                int mode3 = Math.floorDiv(curValue, 10000);
                int mode2 = Math.floorDiv(curValue - mode3 * 10000, 1000);
                int mode1 = Math.floorDiv(curValue - mode3 * 10000 - mode2 * 1000, 100);
                int val1 = mode1 == 0 ? values[values[curPos + 1]] : values[curPos + 1];
                int val2 = mode2 == 0 ? values[values[curPos + 2]] : values[curPos + 2];
                values[values[curPos + 3]] = val1 * val2;
                curPos += 4;
            } else if (op == 3) {
                values[values[curPos + 1]] = 1;
                curPos += 2;
            } else if (op == 4) {
                int mode1 = Math.floorDiv(curValue, 100);
                int val1;
                if (mode1 == 0) {
                    val1 = values[values[curPos + 1]];
                } else {
                    val1 = values[curPos + 1];
                }
                System.out.println(val1);
                curPos += 2;
            } else {
                System.out.println("Something went wrong");
            }
        }
    }

    private static void part2(String[] input) {
        String[] stringValues = input[0].split(",");
        int[] values = Helper.StringArrayToInt(stringValues);
        int curPos = 0;
        while (values[curPos] - Math.floorDiv(values[curPos], 100) * 100 != 99) {
            int curValue = values[curPos];
            int op = curValue - Math.floorDiv(curValue, 100) * 100;
            if (op == 1) {
                int mode3 = Math.floorDiv(curValue, 10000);
                int mode2 = Math.floorDiv(curValue - mode3 * 10000, 1000);
                int mode1 = Math.floorDiv(curValue - mode3 * 10000 - mode2 * 1000, 100);
                int val1 = mode1 == 0 ? values[values[curPos + 1]] : values[curPos + 1];
                int val2 = mode2 == 0 ? values[values[curPos + 2]] : values[curPos + 2];
                values[values[curPos + 3]] = val1 + val2;
                curPos += 4;
            } else if (op == 2) {
                int mode3 = Math.floorDiv(curValue, 10000);
                int mode2 = Math.floorDiv(curValue - mode3 * 10000, 1000);
                int mode1 = Math.floorDiv(curValue - mode3 * 10000 - mode2 * 1000, 100);
                int val1 = mode1 == 0 ? values[values[curPos + 1]] : values[curPos + 1];
                int val2 = mode2 == 0 ? values[values[curPos + 2]] : values[curPos + 2];
                values[values[curPos + 3]] = val1 * val2;
                curPos += 4;
            } else if (op == 3) {
                values[values[curPos + 1]] = 5;
                curPos += 2;
            } else if (op == 4) {
                int mode1 = Math.floorDiv(curValue, 100);
                int val1;
                if (mode1 == 0) {
                    val1 = values[values[curPos + 1]];
                } else {
                    val1 = values[curPos + 1];
                }
                System.out.println(val1);
                curPos += 2;
            } else if (op == 5) {
                int mode2 = Math.floorDiv(curValue, 1000);
                int mode1 = Math.floorDiv(curValue - mode2 * 1000, 100);
                int val1 = mode1 == 0 ? values[values[curPos + 1]] : values[curPos + 1];
                int val2 = mode2 == 0 ? values[values[curPos + 2]] : values[curPos + 2];
                if (val1 != 0) {
                    curPos = val2;
                } else {
                    curPos += 3;
                }
            } else if (op == 6) {
                int mode2 = Math.floorDiv(curValue, 1000);
                int mode1 = Math.floorDiv(curValue - mode2 * 1000, 100);
                int val1 = mode1 == 0 ? values[values[curPos + 1]] : values[curPos + 1];
                int val2 = mode2 == 0 ? values[values[curPos + 2]] : values[curPos + 2];
                if (val1 == 0) {
                    curPos = val2;
                } else {
                    curPos += 3;
                }
            } else if (op == 7) {
                int mode3 = Math.floorDiv(curValue, 10000);
                int mode2 = Math.floorDiv(curValue - mode3 * 10000, 1000);
                int mode1 = Math.floorDiv(curValue - mode3 * 10000 - mode2 * 1000, 100);
                int val1 = mode1 == 0 ? values[values[curPos + 1]] : values[curPos + 1];
                int val2 = mode2 == 0 ? values[values[curPos + 2]] : values[curPos + 2];
                if (val1 < val2) {
                    values[values[curPos + 3]] = 1;
                } else {
                    values[values[curPos + 3]] = 0;
                }
                curPos += 4;
            } else if (op == 8) {
                int mode3 = Math.floorDiv(curValue, 10000);
                int mode2 = Math.floorDiv(curValue - mode3 * 10000, 1000);
                int mode1 = Math.floorDiv(curValue - mode3 * 10000 - mode2 * 1000, 100);
                int val1 = mode1 == 0 ? values[values[curPos + 1]] : values[curPos + 1];
                int val2 = mode2 == 0 ? values[values[curPos + 2]] : values[curPos + 2];
                if (val1 == val2) {
                    values[values[curPos + 3]] = 1;
                } else {
                    values[values[curPos + 3]] = 0;
                }
                curPos += 4;
            } else {
                System.out.println("Something went wrong");
            }
        }
    }
}
