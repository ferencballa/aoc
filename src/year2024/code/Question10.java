package year2024.code;

import helpers.Helper;

import java.io.IOException;

public class Question10 {
    public static void main(String[] args) throws IOException {
        Q10Part1.run();
        Q10Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 10);
    }
}

class Q10Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question10.getInput();
        int height = input.length;
        int width = input[0].length();
        int[][] heights = new int[height][];
        for (int i = 0; i < height; i++) {
            heights[i] = Helper.StringToIntArray(input[i]);
        }
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (heights[i][j] == 0) {
                    boolean[][] trailEnds = new boolean[height][width];
                    trailEnds = getTrailEnds(heights, i, j, 0, trailEnds);
                    for (int k = 0; k < height; k++) {
                        for (int l = 0; l < width; l++) {
                            if (trailEnds[k][l]) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }

    private static boolean[][] getTrailEnds(int[][] input, int i, int j, int height, boolean[][] trailEnds) {
        if (height == 9) {
            trailEnds[i][j] = true;
            return trailEnds;
        } else {
            if (i > 0) {
                if (input[i-1][j] == height+1) {
                    getTrailEnds(input, i-1, j, height+1, trailEnds);
                }
            }
            if (i < input.length - 1) {
                if (input[i+1][j] == height+1) {
                    getTrailEnds(input, i+1, j, height+1, trailEnds);
                }
            }
            if (j > 0) {
                if (input[i][j-1] == height+1) {
                    getTrailEnds(input, i, j-1, height+1, trailEnds);
                }
            }
            if (j < input[0].length - 1) {
                if (input[i][j+1] == height+1) {
                    getTrailEnds(input, i, j+1, height+1, trailEnds);
                }
            }
            return trailEnds;
        }
    }
}

class Q10Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question10.getInput();
        int height = input.length;
        int width = input[0].length();
        int[][] heights = new int[height][];
        for (int i = 0; i < height; i++) {
            heights[i] = Helper.StringToIntArray(input[i]);
        }
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (heights[i][j] == 0) {
                    count += getTrailEnds(heights, i, j, 0);
                }
            }
        }
        System.out.println(count);
    }

    private static int getTrailEnds(int[][] input, int i, int j, int height) {
        if (height == 9) {
            return 1;
        } else {
            int count = 0;
            if (i > 0) {
                if (input[i-1][j] == height+1) {
                    count += getTrailEnds(input, i-1, j, height+1);
                }
            }
            if (i < input.length - 1) {
                if (input[i+1][j] == height+1) {
                    count += getTrailEnds(input, i+1, j, height+1);
                }
            }
            if (j > 0) {
                if (input[i][j-1] == height+1) {
                    count += getTrailEnds(input, i, j-1, height+1);
                }
            }
            if (j < input[0].length - 1) {
                if (input[i][j+1] == height+1) {
                    count += getTrailEnds(input, i, j+1, height+1);
                }
            }
            return count;
        }
    }
}
