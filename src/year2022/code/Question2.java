package year2022.code;

import helpers.Helper;

import java.io.IOException;

public class Question2 {
    public static void main(String[] args) throws IOException {
        Q2Part1.run();
        Q2Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 2);
    }
}

class Q2Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question2.getInput();
        int total = 0;
        for (int i = 0; i < input.length; i++) {
            String[] line = input[i].split(" ");
            if (line[1].equals("X")) {
                total += 1;
                if (line[0].equals("A")) {
                    total += 3;
                }
                if (line[0].equals("C")) {
                    total += 6;
                }
            }
            if (line[1].equals("Y")) {
                total += 2;
                if (line[0].equals("B")) {
                    total += 3;
                }
                if (line[0].equals("A")) {
                    total += 6;
                }
            }
            if (line[1].equals("Z")) {
                total += 3;
                if (line[0].equals("C")) {
                    total += 3;
                }
                if (line[0].equals("B")) {
                    total += 6;
                }
            }
        }
        System.out.println(total);
    }
}

class Q2Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question2.getInput();
        int total = 0;
        for (int i = 0; i < input.length; i++) {
            String[] line = input[i].split(" ");
            if (line[1].equals("X")) {
                total += 0;
                if (line[0].equals("A")) {
                    total += 3;
                }
                if (line[0].equals("B")) {
                    total += 1;
                }
                if (line[0].equals("C")) {
                    total += 2;
                }
            }
            if (line[1].equals("Y")) {
                total += 3;
                if (line[0].equals("A")) {
                    total += 1;
                }
                if (line[0].equals("B")) {
                    total += 2;
                }
                if (line[0].equals("C")) {
                    total += 3;
                }
            }
            if (line[1].equals("Z")) {
                total += 6;
                if (line[0].equals("A")) {
                    total += 2;
                }
                if (line[0].equals("B")) {
                    total += 3;
                }
                if (line[0].equals("C")) {
                    total += 1;
                }
            }
        }
        System.out.println(total);
    }
}
