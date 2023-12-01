package year2023.code;

import helpers.Helper;

import java.io.IOException;

public class Question01 {
    public static void main(String[] args) throws IOException {
        Q01Part1.run();
        Q01Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 1);
    }
}

class Q01Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question01.getInput();
        int count = 0;
        for (String s : input) {
            int i = 0;
            while (s.charAt(i) > 57 || s.charAt(i) < 48) {
                i++;
            }
            count += (s.charAt(i) - 48) * 10;
            i = s.length() - 1;
            while (s.charAt(i) > 57 || s.charAt(i) < 48) {
                i--;
            }
            count += (s.charAt(i) - 48);
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
        int count = 0;
        for (String s : input) {
            int i = 0;
            while(true) {
                int found = checkForNum(s, i, 10);
                if (found != -1) {
                    count += found; break;
                }
                i++;
            }
            i = s.length() - 1;
            while(true) {
                int found = checkForNum(s, i, 1);
                if (found != -1) {
                    count += found; break;
                }
                i--;
            }
        }
        System.out.println(count);
    }

    private static int checkForNum(String s, int i, int factor) {
        if (ssOrEndEq(s, i, 3, "one")) {
            return factor;
        } else if (ssOrEndEq(s, i, 3, "two")) {
            return 2 * factor;
        } else if (ssOrEndEq(s, i, 5, "three")) {
            return 3 * factor;
        } else if (ssOrEndEq(s, i, 4, "four")) {
            return 4 * factor;
        } else if (ssOrEndEq(s, i, 4, "five")) {
            return 5 * factor;
        } else if (ssOrEndEq(s, i, 3, "six")) {
            return 6 * factor;
        } else if (ssOrEndEq(s, i, 5, "seven")) {
            return 7 * factor;
        } else if (ssOrEndEq(s, i, 5, "eight")) {
            return 8 * factor;
        } else if (ssOrEndEq(s, i, 4, "nine")) {
            return 9 * factor;
        } else if (s.charAt(i) <= 57 && s.charAt(i) >= 48) {
            return (s.charAt(i) - 48) * factor;
        }
        return -1;
    }

    //also known as substringOrUntilEndEqualsString
    private static boolean ssOrEndEq(String s, int i, int l, String comp) {
        return s.substring(i, Math.min(i + l, s.length())).equals(comp);
    }
}
