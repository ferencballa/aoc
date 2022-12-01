package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Question1 {
    public static void main(String[] args) throws IOException {
        Q1Part1.run();
        Q1Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 1);
    }
}

class Q1Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question1.getInput();
        int mostV = 0;
        int curTot = 0;
        for (int i = 0; i < input.length; i++) {
            String currentValue = input[i];
            if (currentValue.equals("")) {
                if (curTot > mostV) {
                    mostV = curTot;
                }
                curTot = 0;
            } else {
                curTot += Integer.parseInt(currentValue);
            }
        }
        System.out.println(mostV);
    }
}

class Q1Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question1.getInput();
        ArrayList<Integer> cals = new ArrayList<>();
        int curTot = 0;
        for (int i = 0; i < input.length; i++) {
            String currentValue = input[i];
            if (currentValue.equals("")) {
                cals.add(curTot);
                curTot = 0;
            } else {
                curTot += Integer.parseInt(currentValue);
            }
        }
        Collections.sort(cals);
        Collections.reverse(cals);
        System.out.println(cals.get(0) + cals.get(1) + cals.get(2));
    }
}
