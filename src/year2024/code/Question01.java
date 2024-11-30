package year2024.code;

import helpers.Helper;

import java.io.IOException;

public class Question01 {
    public static void main(String[] args) throws IOException {
        Q01Part1.run();
        Q01Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 1);
    }
}

class Q01Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question01.getInput();
    }
}

class Q01Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question01.getInput();
    }
}
