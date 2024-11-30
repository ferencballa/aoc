package year2025.code;

import helpers.Helper;

import java.io.IOException;

public class Question03 {
    public static void main(String[] args) throws IOException {
        Q03Part1.run();
        Q03Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2025, 3);
    }
}

class Q03Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question03.getInput();
    }
}

class Q03Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question03.getInput();
    }
}
