package year2023.code;

import helpers.Helper;

import java.io.IOException;

public class Question10 {
    public static void main(String[] args) throws IOException {
        Q10Part1.run();
        Q10Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 10);
    }
}

class Q10Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question10.getInput();
    }
}

class Q10Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question10.getInput();
    }
}
