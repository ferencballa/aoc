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
            Matcher m = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)").matcher(s);
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
            int index = 0;
            int mul = 0;
            boolean mulComma = false;
            String mul1 = "";
            String mul2 = "";
            int ena = 0;
            int dis = 0;
            while (index < s.length()) {
                if (s.charAt(index) == 'm') {
                    mul = 1;
                    mulComma = false;
                    mul1 = "";
                    mul2 = "";
                    ena = 0;
                    dis = 0;
                } else if (s.charAt(index) == 'u' && mul == 1) {
                    mul = 2;
                } else if (s.charAt(index) == 'l' && mul == 2) {
                    mul = 3;
                } else if (s.charAt(index) == '(' && mul == 3) {
                    mul = 4;
                } else if (s.charAt(index) >= 48 && s.charAt(index) <= 57 && mul == 4 && mul1.length() < 3 && !mulComma) {
                    mul1 = mul1 + s.charAt(index);
                } else if (s.charAt(index) == ',' && !mulComma && mul1.length() > 0) {
                    mulComma = true;
                } else if (s.charAt(index) >= 48 && s.charAt(index) <= 57 && mulComma && mul2.length() < 3) {
                    mul2 = mul2 + s.charAt(index);
                } else if (s.charAt(index) == ')' && mul2.length() > 0) {
                    if (enabled) {
                        count += Integer.parseInt(mul1) * Integer.parseInt(mul2);
                    }
                    mul = 0;
                    mulComma = false;
                    mul1 = "";
                    mul2 = "";
                } else if (s.charAt(index) == 'd') {
                    mul = 0;
                    mulComma = false;
                    mul1 = "";
                    mul2 = "";
                    ena = 1;
                    dis = 1;
                } else if (s.charAt(index) == 'o' && ena == 1) {
                    ena = 2;
                    dis = 2;
                } else if (s.charAt(index) == '(' && ena == 2) {
                    ena = 3;
                    dis = 0;
                } else if (s.charAt(index) == ')' && ena == 3) {
                    enabled = true;
                    ena = 0;
                } else if (s.charAt(index) == 'n' && ena == 2) {
                    ena = 0;
                    dis = 3;
                } else if (s.charAt(index) == '\'' && dis == 3) {
                    dis = 4;
                } else if (s.charAt(index) == 't' && dis == 4) {
                    dis = 5;
                } else if (s.charAt(index) == '(' && dis == 5) {
                    dis = 6;
                } else if (s.charAt(index) == ')' && dis == 6) {
                    enabled = false;
                    dis = 0;
                } else {
                    mul = 0;
                    mulComma = false;
                    mul1 = "";
                    mul2 = "";
                    ena = 0;
                    dis = 0;
                }
                index++;
            }
        }
        System.out.println(count);
    }
}
