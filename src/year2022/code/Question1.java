package year2022.code;

import helpers.Helper;

import java.io.IOException;

public class Question1 {
    public static void main(String[] args) throws IOException {
        Q1Part1.run();
        Q1Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 1);
    }
}

class Q1Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question1.getInput();
    }
}

class Q1Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question1.getInput();
    }
}
