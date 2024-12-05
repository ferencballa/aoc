package year2019.code;

import helpers.Helper;

import java.io.IOException;

public class Question16 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2019, 16);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        int[] basePattern = {0, 1, 0, -1};
        int[] currentList = Helper.StringArrayToIntArray(input[0].split(""));
        for (int numberOfPhases = 0; numberOfPhases < 100; numberOfPhases++) {
            int[] newList = new int[currentList.length];
            for (int i = 0; i < currentList.length; i++) {
                int[] pattern = new int[currentList.length + 1];
                int positionInBasePattern = 0;
                int numRepeat = 0;
                for (int patternPosition = 0; patternPosition < pattern.length; patternPosition++) {
                    pattern[patternPosition] = basePattern[positionInBasePattern];
                    numRepeat++;
                    if (numRepeat == i+1) {
                        numRepeat = 0;
                        positionInBasePattern++;
                        positionInBasePattern %= 4;
                    }
                }
                for (int currentListLength = 0; currentListLength < newList.length; currentListLength++) {
                    newList[i] += currentList[currentListLength] * pattern[currentListLength+1];
                }
                newList[i] = Math.abs(newList[i] % 10);
            }
            currentList = newList;
        }
        for (int i = 0; i < 8; i++) {
            System.out.print(currentList[i]);
        }
        System.out.print("\n");
    }

    private static void part2(String[] input) {
        int[] currentListShort = Helper.StringArrayToIntArray(input[0].split(""));
        int offset = 0;
        for (int i = 0; i < 7; i++) {
            offset += currentListShort[i] * Math.pow(10, 6-i);
        }
        if (offset > currentListShort.length/2) {
            int[] currentListFull = new int[currentListShort.length * 10000];
            for (int i = 0; i < 10000; i++) {
                for (int j = 0; j < currentListShort.length; j++) {
                    currentListFull[i * currentListShort.length + j] = currentListShort[j];
                }
            }
            int[] currentList = new int[currentListFull.length - offset];
            for (int i = 0; i < currentListFull.length - offset; i++) {
                currentList[i] = currentListFull[i + offset];
            }
            for (int numberOfPhases = 0; numberOfPhases < 100; numberOfPhases++) {
                int[] newList = new int[currentList.length];
                int newValue = 0;
                for (int i = currentList.length - 1; i >= 0; i--) {
                    newValue += currentList[i];
                    newValue %= 10;
                    newList[i] = newValue;
                }
                currentList = newList;
            }
            for (int i = 0; i < 8; i++) {
                System.out.print(currentList[i]);
            }
            System.out.print("\n");
        } else {
            System.out.print("TODO");
        }
    }
}
