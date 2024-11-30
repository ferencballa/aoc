package year2025.code;

import helpers.Helper;

import java.io.IOException;

public class Question02 {
    public static void main(String[] args) throws IOException {
        Q02Part1.run();
        Q02Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2025, 2);
    }
}

class Q02Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question02.getInput();
    }
}

class Q02Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question02.getInput();
    }
}
