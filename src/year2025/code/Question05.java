package year2025.code;

import helpers.Helper;

import java.io.IOException;

public class Question05 {
    public static void main(String[] args) throws IOException {
        Q05Part1.run();
        Q05Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2025, 5);
    }
}

class Q05Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question05.getInput();
    }
}

class Q05Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question05.getInput();
    }
}
