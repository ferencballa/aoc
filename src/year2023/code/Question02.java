package year2023.code;

import helpers.Helper;

import java.io.IOException;

public class Question02 {
    public static void main(String[] args) throws IOException {
        Q02Part1.run();
        Q02Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 2);
    }
}

class Q02Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question02.getInput();
        int count = 0;
        for (String s : input) {
            String[] parts = s.split(" ");
            int i = 2;
            boolean impossible = false;
            while (i < parts.length - 1) {
                if ((parts[i+1].startsWith("red") && Integer.parseInt(parts[i]) > 12) ||
                        (parts[i+1].startsWith("green") && Integer.parseInt(parts[i]) > 13) ||
                        (parts[i+1].startsWith("blue") && Integer.parseInt(parts[i]) > 14)) {
                    impossible = true;
                }
                i += 2;
            }
            if (!impossible) {
                count += Integer.parseInt(parts[1].substring(0, parts[1].length()-1));
            }
        }
        System.out.println(count);
    }
}

class Q02Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question02.getInput();
        int count = 0;
        for (String s : input) {
            String[] parts = s.split(" ");
            int i = 2;
            int minRed = 0;
            int minGreen = 0;
            int minBlue = 0;
            while (i < parts.length - 1) {
                if (parts[i+1].startsWith("red")) {
                    minRed = Math.max(Integer.parseInt(parts[i]), minRed);
                }
                else if (parts[i+1].startsWith("green")) {
                    minGreen = Math.max(Integer.parseInt(parts[i]), minGreen);
                }
                else {
                    minBlue = Math.max(Integer.parseInt(parts[i]), minBlue);
                }
                i += 2;
            }
            count += minRed * minGreen * minBlue;
        }
        System.out.println(count);
    }
}
