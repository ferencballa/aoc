package year2023.code;

import helpers.Helper;

import java.io.IOException;
import java.util.HashSet;

public class Question04 {
    public static void main(String[] args) throws IOException {
        Q04Part1.run();
        Q04Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 4);
    }
}

class Q04Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question04.getInput();
        int count = 0;
        for (String s : input) {
            String[] parts = s.split(" \\| ");
            HashSet<String> winning = new HashSet<>();
            String[] firstParts = parts[0].split(" ");
            for (int i = 2; i < firstParts.length; i++) {
                if (!firstParts[i].equals("")) {
                    winning.add(firstParts[i]);
                }
            }
            String[] secondParts = parts[1].split(" ");
            int good = 0;
            for (String n : secondParts) {
                if (winning.contains(n)) {
                    if (good == 0) {
                        good = 1;
                    } else {
                        good *= 2;
                    }
                }
            }
            count += good;
        }
        System.out.println(count);
    }
}

class Q04Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question04.getInput();
        int[] cards = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            cards[i] = 1;
        }
        for (int i = 0; i < input.length; i++) {
            String s = input[i];
            String[] parts = s.split(" \\| ");
            HashSet<String> winning = new HashSet<>();
            String[] firstParts = parts[0].split(" ");
            for (int p = 2; p < firstParts.length; p++) {
                if (!firstParts[p].equals("")) {
                    winning.add(firstParts[p]);
                }
            }
            String[] secondParts = parts[1].split(" ");
            int good = 0;
            for (String n : secondParts) {
                if (winning.contains(n)) {
                    good++;
                }
            }
            for (int j = i + 1; j <= i + good && j < input.length; j++) {
                cards[j] += cards[i];
            }
        }
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            count += cards[i];
        }
        System.out.println(count);
    }
}
