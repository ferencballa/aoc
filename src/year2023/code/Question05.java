package year2023.code;

import helpers.Helper;

import java.io.IOException;

public class Question05 {
    public static void main(String[] args) throws IOException {
        Q05Part1.run();
        Q05Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 5);
    }
}

class Q05Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question05.getInput();
        long[] seeds = new long[1];
        long[][][] maps = new long[7][][];
        String[] seedParts = input[0].split(" ");
        seeds = new long[seedParts.length - 1];
        for (int j = 0; j < seedParts.length - 1; j++) {
            seeds[j] = Long.parseLong(seedParts[j + 1]);
        }
        int i = 3;
        int map = 0;
        while (i < input.length) {
            int end = i;
            while (end < input.length && input[end].length() > 0) {
                end++;
            }
            maps[map] = new long[end - i][];
            for (int m = i; m < end; m++) {
                String[] lineParts = input[m].split(" ");
                maps[map][m - i] = new long[3];
                maps[map][m - i][0] = Long.parseLong(lineParts[0]);
                maps[map][m - i][1] = Long.parseLong(lineParts[1]);
                maps[map][m - i][2] = Long.parseLong(lineParts[2]);
            }
            i = end;
            i++;
            i++;
            map++;
        }
        long lowestLocation = Long.MAX_VALUE;
        for (int s = 0; s < seeds.length; s++) {
            long seed = seeds[s];
            for (int m = 0; m < 7; m++) {
                boolean mappingFound = false;
                for (int mapLines = 0; mapLines < maps[m].length; mapLines++) {
                    if (!mappingFound && seed >= maps[m][mapLines][1] && seed < maps[m][mapLines][1] + maps[m][mapLines][2]) {
                        seed += maps[m][mapLines][0] - maps[m][mapLines][1];
                        mappingFound = true;
                    }
                }
            }
            lowestLocation = Math.min(lowestLocation, seed);
        }
        System.out.println(lowestLocation);
    }
}

class Q05Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question05.getInput();
        long[] seeds = new long[1];
        long[][][] maps = new long[7][][];
        String[] seedParts = input[0].split(" ");
        seeds = new long[seedParts.length - 1];
        for (int j = 0; j < seedParts.length - 1; j++) {
            seeds[j] = Long.parseLong(seedParts[j + 1]);
        }
        int i = 3;
        int map = 0;
        while (i < input.length) {
            int end = i;
            while (end < input.length && input[end].length() > 0) {
                end++;
            }
            maps[map] = new long[end - i][];
            for (int m = i; m < end; m++) {
                String[] lineParts = input[m].split(" ");
                maps[map][m - i] = new long[3];
                maps[map][m - i][0] = Long.parseLong(lineParts[0]);
                maps[map][m - i][1] = Long.parseLong(lineParts[1]);
                maps[map][m - i][2] = Long.parseLong(lineParts[2]);
            }
            i = end;
            i++;
            i++;
            map++;
        }
        long lowestLocation = Long.MAX_VALUE;
        for (int s = 0; s < seeds.length; s+=2) {
            System.out.println(s);
            for (long startSeed = seeds[s]; startSeed < seeds[s] + seeds[s+1]; startSeed++) {
                long seed = startSeed;
                for (int m = 0; m < 7; m++) {
                    boolean mappingFound = false;
                    for (int mapLines = 0; mapLines < maps[m].length; mapLines++) {
                        if (!mappingFound && seed >= maps[m][mapLines][1] && seed < maps[m][mapLines][1] + maps[m][mapLines][2]) {
                            seed += maps[m][mapLines][0] - maps[m][mapLines][1];
                            mappingFound = true;
                        }
                    }
                }
                lowestLocation = Math.min(lowestLocation, seed);
            }
        }
        System.out.println(lowestLocation);
    }
}
