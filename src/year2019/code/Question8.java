package year2019.code;
import helpers.Helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Question8 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2019, 8);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        int[] pixels = Helper.StringArrayToInt(input[0].split(""));
        int numOfLayers = pixels.length / 150;
        int fewest0 = Integer.MAX_VALUE;
        int mult = 0;
        for (int i = 0; i < numOfLayers; i++) {
            int zeroes = 0;
            int ones = 0;
            int twos = 0;
            for (int j = 0; j < 150; j++) {
                int val = pixels[i * 150 + j];
                if (val == 0) {
                    zeroes++;
                } else if (val == 1) {
                    ones++;
                } else if (val == 2) {
                    twos++;
                }
            }
            if (zeroes < fewest0) {
                fewest0 = zeroes;
                mult = ones * twos;
            }
        }
        System.out.println(mult);
    }

    private static void part2(String[] input) {
        int[] pixels = Helper.StringArrayToInt(input[0].split(""));
        int numOfLayers = pixels.length / 150;
        int[] visPix = new int[150];
        for (int i = 0; i < 150; i++) {
            visPix[i] = 2;
        }
        for (int i = 0; i < numOfLayers; i++) {
            for (int j = 0; j < 150; j++) {
                if (visPix[j] == 2) {
                    int val = pixels[i * 150 + j];
                    visPix[j] = val;
                }
            }
        }
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 25; i++) {
                System.out.print(visPix[j * 25 + i] == 1 ? "#" : ".");
            }
            System.out.println();
        }
    }
}
