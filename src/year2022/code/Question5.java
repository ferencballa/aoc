package year2022.code;

import helpers.Helper;

import java.io.IOException;

public class Question5 {
    public static void main(String[] args) throws IOException {
        Q5Part1.run();
        Q5Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 5);
    }
}

class Q5Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question5.getInput();
    }
}

class Q5Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question5.getInput();
    }
}
