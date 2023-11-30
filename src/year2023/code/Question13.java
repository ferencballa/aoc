package year2023.code;

import helpers.Helper;

import java.io.IOException;

public class Question13 {
    public static void main(String[] args) throws IOException {
        Q13Part1.run();
        Q13Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 13);
    }
}

class Q13Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question13.getInput();
    }
}

class Q13Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question13.getInput();
    }
}