package year2024.code;

import helpers.Helper;

import java.io.IOException;

public class Question04 {
    public static void main(String[] args) throws IOException {
        Q04Part1.run();
        Q04Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 4);
    }
}

class Q04Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question04.getInput();
        int width = input[0].length();
        int height = input.length;
        int count = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (input[y].charAt(x) == 'X') {
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            stepLoop:
                            for (int st = 1; st <= 3; st++) {
                                int newX = x + dx * st;
                                int newY = y + dy * st;
                                if (newX < 0 || newX == width || newY < 0 || newY == height) {
                                    break stepLoop;
                                } else {
                                    if (st == 1 && input[newY].charAt(newX) != 'M') {
                                        break stepLoop;
                                    } else if (st == 2 && input[newY].charAt(newX) != 'A') {
                                        break stepLoop;
                                    } else if (st == 3 && input[newY].charAt(newX) != 'S') {
                                        break stepLoop;
                                    } else if (st == 3) {
                                        count++;
                                    }
                                }
                            }
                        }
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
        int width = input[0].length();
        int height = input.length;
        int count = 0;
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                if (input[y].charAt(x) == 'A') {
                    if (((input[y-1].charAt(x-1) == 'M' && input[y+1].charAt(x+1) == 'S') || (input[y-1].charAt(x-1) == 'S' && input[y+1].charAt(x+1) == 'M')) &&
                            ((input[y+1].charAt(x-1) == 'M' && input[y-1].charAt(x+1) == 'S') || (input[y+1].charAt(x-1) == 'S' && input[y-1].charAt(x+1) == 'M'))) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
