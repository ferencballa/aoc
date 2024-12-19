package year2024.code;

import helpers.Helper;

import java.io.IOException;
import java.util.HashMap;

public class Question19 {
    public static void main(String[] args) throws IOException {
        Q19Part1.run();
        Q19Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 19);
    }
}

class Q19Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    private static String[] towels = new String[0];

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question19.getInput();
        towels = input[0].split(", ");
        int count = 0;
        for (int i = 2; i < input.length; i++) {
            String s = input[i];
            if (matchFound(s)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean matchFound(String s) {
        if (s.length() == 0) {
            return true;
        }
        for (String t : towels) {
            if (s.startsWith(t)) {
                if (matchFound(s.substring(t.length()))) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Q19Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    private static String[] towels = new String[0];
    private static HashMap<String, Long> memory = new HashMap<>();

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question19.getInput();
        towels = input[0].split(", ");
        long count = 0;
        for (int i = 2; i < input.length; i++) {
            String s = input[i];
            count += matchesFound(s);
        }
        System.out.println(count);
    }

    private static long matchesFound(String s) {
        if (memory.containsKey(s)) {
            return memory.get(s);
        }
        if (s.length() == 0) {
            return 1;
        }
        long count = 0;
        for (String t : towels) {
            if (s.startsWith(t)) {
                count += matchesFound(s.substring(t.length()));
            }
        }
        memory.put(s, count);
        return count;
    }
}
