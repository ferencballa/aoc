package year2024.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question03 {
    public static void main(String[] args) throws IOException {
        Q03Part1.run();
        Q03Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 3);
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
        for (String s : input) {
            List<String> allMatches = new ArrayList<>();
            Matcher m = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)").matcher(s);
            while(m.find()) {
                allMatches.add(m.group());
            }
            for (String match : allMatches) {
                String subs = match.substring(4, match.length()-1);
                String[] parts = subs.split(",");
                count += Integer.parseInt(parts[0]) * Integer.parseInt(parts[1]);
            }
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
        int count = 0;
        boolean enabled = true;
        for (String s : input) {
            Pattern stringPattern = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\(\\d{1,3},\\d{1,3}\\)");
            Matcher m = stringPattern.matcher(s);
            while (m.find()) {
                String command = m.group();
                if (command.startsWith("mul") && enabled) {
                    String subs = command.substring(4, command.length()-1);
                    String[] parts = subs.split(",");
                    count += Integer.parseInt(parts[0]) * Integer.parseInt(parts[1]);
                } else if (command.equals("do()")) {
                    enabled = true;
                } else {
                    enabled = false;
                }
            }
        }
        System.out.println(count);
    }
}
