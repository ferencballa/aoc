package year2025.code;

import helpers.Helper;

import java.io.IOException;

public class Question11 {
    public static void main(String[] args) throws IOException {
        Q11Part1.run();
        Q11Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2025, 11);
    }
}

class Q11Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question11.getInput();
    }
}

class Q11Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question11.getInput();
    }
}
