package year2022.code;

import helpers.Helper;

import java.io.IOException;

public class Question10 {
    public static void main(String[] args) throws IOException {
        Q10Part1.run();
        Q10Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 10);
    }
}

class Q10Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question10.getInput();
        int signalStrength = 0;
        boolean wait = false;
        int inputIndex = 0;
        int x = 1;
        int bufferingAddition = 0;
        for (int cycle = 1; cycle <= 220; cycle++) {
            if ((cycle + 20) % 40 == 0) {
                signalStrength += x * cycle;
            }
            if (!wait) {
                if (input[inputIndex].equals("noop")) {
                    inputIndex++;
                } else {
                    wait = true;
                    String[] command = input[inputIndex].split(" ");
                    bufferingAddition = Integer.parseInt(command[1]);
                    inputIndex++;
                }
            } else {
                wait = false;
                x += bufferingAddition;
            }
        }
        System.out.println(signalStrength);
    }
}

class Q10Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question10.getInput();
        String[] output = new String[240];
        int signalStrength = 0;
        boolean wait = false;
        int inputIndex = 0;
        int x = 1;
        int bufferingAddition = 0;
        for (int cycle = 0; cycle < 240; cycle++) {
            if (x == cycle % 40 || x - 1 == cycle % 40 || x + 1 == cycle % 40) {
                output[cycle] = "#";
            } else {
                output[cycle] = ".";
            }
            if (!wait) {
                if (input[inputIndex].equals("noop")) {
                    inputIndex++;
                } else {
                    wait = true;
                    String[] command = input[inputIndex].split(" ");
                    bufferingAddition = Integer.parseInt(command[1]);
                    inputIndex++;
                }
            } else {
                wait = false;
                x += bufferingAddition;
            }
        }
        for (int i = 0; i < 240; i++) {
            System.out.print(output[i]);
            if ((i + 1) % 40 == 0) {
                System.out.print("\n");
            }
        }
    }
}
