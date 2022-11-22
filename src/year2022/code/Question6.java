package year2022.code;

import helpers.Helper;

import java.io.IOException;

public class Question6 {
    public static void main(String[] args) throws IOException {
        Q6Part1.run();
        Q6Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 6);
    }
}

class Q6Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question6.getInput();
    }
}

class Q6Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question6.getInput();
    }
}
