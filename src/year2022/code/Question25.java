package year2022.code;

import helpers.Helper;

import java.io.IOException;

public class Question25 {
    public static void main(String[] args) throws IOException {
        Q25Part1.run();
        Q25Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 25);
    }
}

class Q25Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question25.getInput();
        long total = 0;
        for (String inp : input) {
            for (int i = inp.length() - 1; i >= 0; i--) {
                int val = 2;
                if (inp.charAt(i) == '1') {
                    val = 1;
                } else if (inp.charAt(i) == '0') {
                    val = 0;
                } else if (inp.charAt(i) == '-') {
                    val = -1;
                } else if (inp.charAt(i) == '=') {
                    val = -2;
                }
                total += Math.pow(5, inp.length() - 1 - i) * val;
            }
        }
        int pow = 0;
        String output = "";
        while (total > 0) {
            if (total - Math.pow(5, pow) * -2 == 0 || (total - Math.pow(5, pow) * -2) % Math.pow(5, pow + 1) == 0) {
                total -= Math.pow(5, pow) * -2;
                output = "=" + output;
            } else if (total - Math.pow(5, pow) * -1 == 0 || (total - Math.pow(5, pow) * -1) % Math.pow(5, pow + 1) == 0) {
                total -= Math.pow(5, pow) * -1;
                output = "-" + output;
            } else if (total - Math.pow(5, pow) * 0 == 0 || (total - Math.pow(5, pow) * 0) % Math.pow(5, pow + 1) == 0) {
                total -= Math.pow(5, pow) * 0;
                output = "0" + output;
            } else if (total - Math.pow(5, pow) * 1 == 0 || (total - Math.pow(5, pow) * 1) % Math.pow(5, pow + 1) == 0) {
                total -= Math.pow(5, pow) * 1;
                output = "1" + output;
            } else if (total - Math.pow(5, pow) * 2 == 0 || (total - Math.pow(5, pow) * 2) % Math.pow(5, pow + 1) == 0) {
                total -= Math.pow(5, pow) * 2;
                output = "2" + output;
            } else {
                System.out.println("this shouldn't happen");
            }
            pow++;
        }
        System.out.println(output);
    }
}

class Q25Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question25.getInput();
    }
}
