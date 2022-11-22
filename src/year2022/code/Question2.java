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
    }
}

class Q2Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question2.getInput();
    }
}
