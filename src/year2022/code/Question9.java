package year2022.code;

import helpers.Helper;

import java.io.IOException;

public class Question9 {
    public static void main(String[] args) throws IOException {
        Q9Part1.run();
        Q9Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 9);
    }
}

class Q9Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question9.getInput();
    }
}

class Q9Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question9.getInput();
    }
}
