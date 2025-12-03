package year2025.code;

import helpers.Helper;

import java.io.IOException;

public class Question01 {
    public static void main(String[] args) throws IOException {
        Q01Part1.run();
        Q01Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2025, 1);
    }
}

class Q01Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question01.getInput();
        int pos = 50;
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            int turn = Integer.parseInt(input[i].substring(1));
            if (input[i].charAt(0) == 'L') {
                turn *= -1;
            }
            pos = (pos + turn) % 100;
            if (pos == 0) {
                count ++;
            }
        }
        System.out.println(count);
    }
}

class Q01Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question01.getInput();
        int pos = 50;
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            int turn = Integer.parseInt(input[i].substring(1));
            int restTurn = turn % 100;
            int fullTurns = (turn - restTurn) / 100;
            count += fullTurns;
            if (restTurn != 0) {
                boolean L = input[i].charAt(0) == 'L';
                if (L) {
                    restTurn *= -1;
                }
                int curPos = pos;
                pos = (pos + restTurn + 100) % 100;
                if (pos == 0 || (curPos != 0 &&((L && pos > curPos) || (!L && pos < curPos)))) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
