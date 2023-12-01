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
            while (!(s.charAt(i) <= 57 && s.charAt(i) >= 48)
            && !s.substring(i, Math.min(i + 3, s.length())).contains("one")
                    && !s.substring(i, Math.min(i + 3, s.length())).contains("two")
                    && !s.substring(i, Math.min(i + 5, s.length())).contains("three")
                    && !s.substring(i, Math.min(i + 4, s.length())).contains("four")
                    && !s.substring(i, Math.min(i + 4, s.length())).contains("five")
                    && !s.substring(i, Math.min(i + 3, s.length())).contains("six")
                    && !s.substring(i, Math.min(i + 5, s.length())).contains("seven")
                    && !s.substring(i, Math.min(i + 5, s.length())).contains("eight")
                    && !s.substring(i, Math.min(i + 4, s.length())).contains("nine")) {
                i++;
            }
            if (s.substring(i, Math.min(i + 3, s.length())).contains("one")) {
                count += 10;
            } else if (s.substring(i, Math.min(i + 3, s.length())).contains("two")) {
                count += 20;
            } else if (s.substring(i, Math.min(i + 5, s.length())).contains("three")) {
                count += 30;
            } else if (s.substring(i, Math.min(i + 4, s.length())).contains("four")) {
                count += 40;
            } else if (s.substring(i, Math.min(i + 4, s.length())).contains("five")) {
                count += 50;
            } else if (s.substring(i, Math.min(i + 3, s.length())).contains("six")) {
                count += 60;
            } else if (s.substring(i, Math.min(i + 5, s.length())).contains("seven")) {
                count += 70;
            } else if (s.substring(i, Math.min(i + 5, s.length())).contains("eight")) {
                count += 80;
            } else if (s.substring(i, Math.min(i + 4, s.length())).contains("nine")) {
                count += 90;
            } else {
                count += (s.charAt(i) - 48) * 10;
            }
            i = s.length() - 1;
            while (!(s.charAt(i) <= 57 && s.charAt(i) >= 48)
                    && !s.substring(i, Math.min(i + 3, s.length())).contains("one")
                    && !s.substring(i, Math.min(i + 3, s.length())).contains("two")
                    && !s.substring(i, Math.min(i + 5, s.length())).contains("three")
                    && !s.substring(i, Math.min(i + 4, s.length())).contains("four")
                    && !s.substring(i, Math.min(i + 4, s.length())).contains("five")
                    && !s.substring(i, Math.min(i + 3, s.length())).contains("six")
                    && !s.substring(i, Math.min(i + 5, s.length())).contains("seven")
                    && !s.substring(i, Math.min(i + 5, s.length())).contains("eight")
                    && !s.substring(i, Math.min(i + 4, s.length())).contains("nine")) {
                i--;
            }
            if (s.substring(i, Math.min(i + 3, s.length())).contains("one")) {
                count += 1;
            } else if (s.substring(i, Math.min(i + 3, s.length())).contains("two")) {
                count += 2;
            } else if (s.substring(i, Math.min(i + 5, s.length())).contains("three")) {
                count += 3;
            } else if (s.substring(i, Math.min(i + 4, s.length())).contains("four")) {
                count += 4;
            } else if (s.substring(i, Math.min(i + 4, s.length())).contains("five")) {
                count += 5;
            } else if (s.substring(i, Math.min(i + 3, s.length())).contains("six")) {
                count += 6;
            } else if (s.substring(i, Math.min(i + 5, s.length())).contains("seven")) {
                count += 7;
            } else if (s.substring(i, Math.min(i + 5, s.length())).contains("eight")) {
                count += 8;
            } else if (s.substring(i, Math.min(i + 4, s.length())).contains("nine")) {
                count += 9;
            } else {
                count += (s.charAt(i) - 48);
            }
        }
        System.out.println(count);
    }
}
