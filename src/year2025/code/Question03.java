package year2025.code;

import helpers.Helper;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question03 {
    public static void main(String[] args) throws IOException {
        Q03Part1.run();
        Q03Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2025, 3);
    }
}

class Q03Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question03.getInput();
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            String s = input[i];
            int firstIndex = -1;
            int secondIndex = -1;
            for (int d = 9; d > 0; d--) {
                Pattern pattern = Pattern.compile(String.valueOf(d));
                Matcher matcher = pattern.matcher(s.substring(firstIndex + 1));
                if (matcher.find()) {
                    if (firstIndex == -1) {
                        if (matcher.start() < s.length() - 1) {
                            firstIndex = matcher.start();
                            if (secondIndex == -1) {
                                if (matcher.find()) {
                                    secondIndex = matcher.start();
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            secondIndex = matcher.start();
                        }
                    }
                    else {
                        secondIndex = firstIndex + 1 + matcher.start();
                        break;
                    }
                }
            }
            count += Integer.parseInt(s.substring(firstIndex, firstIndex + 1) + s.substring(secondIndex, secondIndex + 1));
        }
        System.out.println(count);
    }
}

class Q03Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question03.getInput();
        long count = 0;
        for (int i = 0; i < input.length; i++) {
            int[] digits = Helper.StringToIntArray(input[i]);
            int[] jolt = new int[12];
            for (int dindex = 0; dindex < 12; dindex++) {
                jolt[dindex] = digits[digits.length - 12 + dindex];
            }
            for (int dindex = digits.length - 13; dindex >= 0; dindex--) {
                if (digits[dindex] >= jolt[0]) {
                    int toInsert = digits[dindex];
                    for (int rm = 0; rm < 12; rm++) {
                        int cur = jolt[rm];
                        if (cur <= toInsert) {
                            jolt[rm] = toInsert;
                            toInsert = cur;
                        } else {
                            break;
                        }
                    }
                }
            }
            count += Long.parseLong(Arrays.stream(jolt).mapToObj(String::valueOf).reduce("", String::concat));
        }
        System.out.println(count);
    }
}
