package year2025.code;

import helpers.Helper;

import java.io.IOException;

public class Question12 {
    public static void main(String[] args) throws IOException {
        Q12Part1.run();
        Q12Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2025, 12);
    }
}

class Q12Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question12.getInput();
        int[] size = new int[6];
        size[0] = 7;
        size[1] = 7;
        size[2] = 7;
        size[3] = 5;
        size[4] = 7;
        size[5] = 6;
        int easilyFit = 0;
        int definitelyWontFit = 0;
        int mightFit = 0;
        for (int i = 30; i < input.length; i++) {
            String[] parts = input[i].split(" ");
            int[] areaParts = Helper.StringArrayToIntArray(parts[0].substring(0, parts[0].length() - 1).split("x"));
            int area = areaParts[0] * areaParts[1];
            int minCount = 0;
            int numberOfItems = 0;
            for (int j = 1; j < parts.length; j++) {
                minCount += Integer.parseInt(parts[j]) * size[j-1];
                numberOfItems += Integer.parseInt(parts[j]);
            }
            if (numberOfItems < Math.floorDiv(areaParts[0], 3) * Math.floorDiv(areaParts[1], 3)) {
                easilyFit++;
            } else if (minCount > area) {
                definitelyWontFit++;
            } else {
                mightFit++;
            }
        }
        System.out.println("Will definitely fit: " + easilyFit);
        System.out.println("Will definitely not fit: " + definitelyWontFit);
        System.out.println("Number of items to check manually: " + mightFit);
    }
}

class Q12Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question12.getInput();
    }
}
