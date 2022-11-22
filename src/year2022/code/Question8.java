package year2022.code;

import helpers.Helper;

import java.io.IOException;

public class Question8 {
    public static void main(String[] args) throws IOException {
        Q8Part1.run();
        Q8Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 8);
    }
}

class Q8Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question8.getInput();
    }
}

class Q8Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question8.getInput();
    }
}
