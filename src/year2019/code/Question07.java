package year2019.code;
import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Question07 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2019, 7);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        String[] stringValues = input[0].split(",");
        ArrayList<int[]> phaseSettings = new ArrayList<>();
        int[] values = Helper.StringArrayToInt(stringValues);
        int[] phaseValues = {0, 1, 2, 3, 4};
        createPermutations(phaseValues, 5, phaseSettings);
        int maxSignal = 0;
        for (int[] phaseSetting : phaseSettings) {
            int ampInput = 0;
            for (int phase : phaseSetting) {
                ampInput = runAmp(phase, ampInput, values.clone(), 0).x;
            }
            maxSignal = Math.max(maxSignal, ampInput);
        }
        System.out.println(maxSignal);
    }

    private static void part2(String[] input) {
        String[] stringValues = input[0].split(",");
        int[] values = Helper.StringArrayToInt(stringValues);
        ArrayList<int[]> phaseSettings = new ArrayList<>();
        int[] phaseValues = {5, 6, 7, 8, 9};
        createPermutations(phaseValues, 5, phaseSettings);
        int maxSignal = 0;
        for (int[] phaseSetting : phaseSettings) {
            int ampInput = 0;
            int[] values1 = values.clone();
            int[] values2 = values.clone();
            int[] values3 = values.clone();
            int[] values4 = values.clone();
            int[] values5 = values.clone();
            Point p1 = runAmp(phaseSetting[0], ampInput, values1, 0);
            Point p2 = runAmp(phaseSetting[1], p1.x, values2, 0);
            Point p3 = runAmp(phaseSetting[2], p2.x, values3, 0);
            Point p4 = runAmp(phaseSetting[3], p3.x, values4, 0);
            Point p5 = runAmp(phaseSetting[4], p4.x, values5, 0);
            ampInput = p5.x;
            while (p5.x != Integer.MIN_VALUE) {
                p1 = runAmp(-1, ampInput, values1, p1.y);
                p2 = runAmp(-1, p1.x, values2, p2.y);
                p3 = runAmp(-1, p2.x, values3, p3.y);
                p4 = runAmp(-1, p3.x, values4, p4.y);
                p5 = runAmp(-1, p4.x, values5, p5.y);
                if (p5.x != Integer.MIN_VALUE) {
                    ampInput = p5.x;
                }
            }
            maxSignal = Math.max(maxSignal, ampInput);
        }
        System.out.println(maxSignal);
    }

    private static void createPermutations(int[] inp, int size, ArrayList<int[]> phaseSettings) {
        if (size == 1) {
            phaseSettings.add(inp.clone());
        }
        for (int i = 0; i < size; i++) {
            createPermutations(inp, size - 1, phaseSettings);
            if (size % 2 == 1) {
                int temp = inp[0];
                inp[0] = inp[size - 1];
                inp[size - 1] = temp;
            } else {
                int temp = inp[i];
                inp[i] = inp[size - 1];
                inp[size - 1] = temp;
            }
        }
    }

    private static Point runAmp(int phaseSetting, int inputValue, int[] values, int curPos) {
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
                if (phaseSetting != -1) {
                    values[values[curPos + 1]] = phaseSetting;
                    phaseSetting = -1;
                } else {
                    values[values[curPos + 1]] = inputValue;
                }
                curPos += 2;
            } else if (op == 4) {
                int mode1 = Math.floorDiv(curValue, 100);
                int val1;
                if (mode1 == 0) {
                    val1 = values[values[curPos + 1]];
                } else {
                    val1 = values[curPos + 1];
                }
                curPos += 2;
                return new Point(val1, curPos);
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
        return new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }
}
