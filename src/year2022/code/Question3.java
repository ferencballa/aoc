package year2022.code;

import helpers.Helper;

import java.io.IOException;

public class Question3 {
    public static void main(String[] args) throws IOException {
        Q3Part1.run();
        Q3Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 3);
    }
}

class Q3Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question3.getInput();
    }
}

class Q3Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question3.getInput();
    }
}
