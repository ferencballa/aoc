package year2022.code;

import helpers.Helper;

import java.io.IOException;

public class Question21 {
    public static void main(String[] args) throws IOException {
        Q21Part1.run();
        Q21Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 21);
    }
}

class Q21Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question21.getInput();
    }
}

class Q21Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question21.getInput();
    }
}
