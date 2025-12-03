package year2025.code;

import helpers.Helper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question02 {
    public static void main(String[] args) throws IOException {
        Q02Part1.run();
        Q02Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2025, 2);
    }
}

class Q02Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question02.getInput();
        String[] ranges = input[0].split(",");
        long count = 0;
        for (int i = 0; i < ranges.length; i++) {
            String[] parts = ranges[i].split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            for (long l = start; l <= end; l++) {
                String lString = String.valueOf(l);
                if (lString.length() %2 == 0) {
                    int halfLength = lString.length() / 2;
                    long halfMod = (long) Math.pow(10, halfLength);
                    long rightHalf = l % halfMod;
                    long leftHalf = (l - rightHalf) / halfMod;
                    if (leftHalf == rightHalf) {
                        count+= l;
                    }
                }
            }
        }
        System.out.println(count);
    }
}

class Q02Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question02.getInput();
        String[] ranges = input[0].split(",");
        long count = 0;
        for (int i = 0; i < ranges.length; i++) {
            String[] parts = ranges[i].split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            for (long l = start; l <= end; l++) {
                String lString = String.valueOf(l);
                int stringLength = lString.length();
                for (int sl = 1; sl <= Math.floorDiv(stringLength, 2); sl++) {
                    Pattern pattern = Pattern.compile(lString.substring(0, sl));
                    Matcher matcher = pattern.matcher(lString);
                    int matches = (int) matcher.results().count();
                    if (matches * sl == stringLength) {
                        count += l;
                        break;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
