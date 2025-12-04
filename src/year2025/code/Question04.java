package year2025.code;

import helpers.Helper;

import java.io.IOException;

public class Question04 {
    public static void main(String[] args) throws IOException {
        Q04Part1.run();
        Q04Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2025, 4);
    }
}

class Q04Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question04.getInput();
        boolean[][] grid = new boolean[input[0].length()][input.length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length(); j++) {
                grid[j][i] = input[i].charAt(j) == '@';
            }
        }
        int count = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[x][y]) {
                    int neighbours = 0;
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            if (!(dx == 0 && dy == 0)) {
                                if (!(x + dx < 0 || y + dy < 0 || x + dx == grid[0].length || y + dy == grid.length)) {
                                    if (grid[x + dx][y + dy]) {
                                        neighbours++;
                                    }
                                }
                            }
                        }
                    }
                    if (neighbours < 4) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}

class Q04Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question04.getInput();
        boolean[][] grid = new boolean[input[0].length()][input.length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length(); j++) {
                grid[j][i] = input[i].charAt(j) == '@';
            }
        }
        int totalCount = 0;
        int count = -1;
        while (count != 0) {
            count = 0;
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[0].length; x++) {
                    if (grid[x][y]) {
                        int neighbours = 0;
                        for (int dx = -1; dx <= 1; dx++) {
                            for (int dy = -1; dy <= 1; dy++) {
                                if (!(dx == 0 && dy == 0)) {
                                    if (!(x + dx < 0 || y + dy < 0 || x + dx == grid[0].length || y + dy == grid.length)) {
                                        if (grid[x + dx][y + dy]) {
                                            neighbours++;
                                        }
                                    }
                                }
                            }
                        }
                        if (neighbours < 4) {
                            count++;
                            grid[x][y] = false;
                        }
                    }
                }
            }
            totalCount += count;
        }
        System.out.println(totalCount);
    }
}
