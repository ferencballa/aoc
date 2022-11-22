package year2021.code;

import helpers.Helper;

import java.io.IOException;

public class Question20 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2021, 20);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        calculatePixels(input, 10, 2);
    }

    private static void part2(String[] input) {
        calculatePixels(input, 200, 50);
    }

    private static void calculatePixels(String[] input, int padding, int amountOfTransformations) {
        int[] indexes = new int[512];
        String[] indexesStr = input[0].split("");
        for (int i = 0; i < 512; i++) {
            indexes[i] = indexesStr[i].equals("#") ? 1 : 0;
        }
        int imageHeigt = input.length - 2;
        int imageWidth = input[2].length();
        int[][] image = new int[imageHeigt + 2 * padding][imageWidth + 2 * padding];
        for (int i = 0; i < imageHeigt; i++) {
            String[] line = input[i+2].split("");
            for (int j = 0; j < imageWidth; j++) {
                image[padding + i][padding + j] = line[j].equals("#") ? 1 : 0;
            }
        }
        for (int transforms = 0; transforms < amountOfTransformations; transforms++) {
            int[][] oldImage = new int[image.length][image[0].length];
            for (int i = 0; i < image.length; i++) {
                System.arraycopy(image[i], 0, oldImage[i], 0, image[0].length);
            }
            for (int i = 1; i < imageHeigt + 2 * padding - 1; i++) {
                for (int j = 1; j < imageWidth + 2 * padding - 1; j++) {
                    int index = 0;
                    index += 256 * oldImage[i-1][j-1];
                    index += 128 * oldImage[i-1][j];
                    index += 64 * oldImage[i-1][j+1];
                    index += 32 * oldImage[i][j-1];
                    index += 16 * oldImage[i][j];
                    index += 8 * oldImage[i][j+1];
                    index += 4 * oldImage[i+1][j-1];
                    index += 2 * oldImage[i+1][j];
                    index += oldImage[i+1][j+1];
                    image[i][j] = indexes[index];
                }
            }
        }
        int lit = 0;
        for (int i = padding/2; i < imageHeigt + 2 * padding - padding / 2; i++) {
            for (int j = padding/2; j < imageWidth + 2 * padding - padding/2; j++) {
                lit += image[i][j];
            }
        }
        /*for (int i = padding/2; i < imageHeigt + 2 * padding - padding/2; i++) {
            for (int j = padding/2; j < imageWidth + 2 * padding - padding/2; j++) {
                System.out.print(image[i][j] == 1 ? "#" : ".");
            }
            System.out.println("");
        }*/
        System.out.println(lit);
    }
}
