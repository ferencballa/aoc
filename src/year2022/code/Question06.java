package year2022.code;

import helpers.Helper;

import java.io.IOException;

public class Question06 {
    public static void main(String[] args) throws IOException {
        Q06Part1.run();
        Q06Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 6);
    }
}

class Q06Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question06.getInput();
        String inp = input[0];
        char char1 = inp.charAt(0);
        char char2 = inp.charAt(1);
        char char3 = inp.charAt(2);
        char char4 = inp.charAt(3);
        int index = 4;
        while (char1 == char2 || char1 == char3 || char1 == char4 || char2 == char3 || char2 == char4 || char3 == char4) {
            char1 = char2;
            char2 = char3;
            char3 = char4;
            char4 = inp.charAt(index);
            index++;
        }
        System.out.println(index);
    }
}

class Q06Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question06.getInput();
        String inp = input[0];
        char[] charArr = new char[14];
        for (int i = 0; i < charArr.length; i++) {
            charArr[i] = inp.charAt(i);
        }
        int index = 14;
        boolean notFound = false;
        for (int i = 0; i < charArr.length - 1; i++) {
            for (int j = i + 1; j < charArr.length; j++) {
                if (charArr[i] == charArr[j]) {
                    notFound = true;
                }
            }
        }
        while (notFound) {
            for (int i = 0; i < charArr.length - 1; i++) {
                charArr[i] = charArr[i+1];
            }
            charArr[13] = inp.charAt(index);
            index++;
            notFound = false;
            for (int i = 0; i < charArr.length - 1; i++) {
                for (int j = i + 1; j < charArr.length; j++) {
                    if (charArr[i] == charArr[j]) {
                        notFound = true;
                    }
                }
            }
        }
        System.out.println(index);
    }
}
