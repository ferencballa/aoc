package year2024.code;

import helpers.Algorithms;
import helpers.Helper;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question08 {
    public static void main(String[] args) throws IOException {
        Q08Part1.run();
        Q08Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 8);
    }
}

class Q08Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question08.getInput();
        int height = input.length;
        int width = input[0].length();
        HashMap<Character, ArrayList<Pair<Integer, Integer>>> locationsPerChar = new HashMap<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char curChar = input[i].charAt(j);
                if (curChar != '.') {
                    ArrayList<Pair<Integer, Integer>> locationsForChar = new ArrayList<>();
                    if (locationsPerChar.containsKey(curChar)) {
                        locationsForChar = locationsPerChar.get(curChar);
                    }
                    locationsForChar.add(Pair.of(j, i));
                    locationsPerChar.put(curChar, locationsForChar);
                }
            }
        }
        boolean[][] antinodeLocations = new boolean[height][width];
        for (Character key : locationsPerChar.keySet()) {
            ArrayList<Pair<Integer, Integer>> locationsForChar = locationsPerChar.get(key);
            for (int i = 0; i < locationsForChar.size() - 1; i++) {
                for (int j = i + 1; j < locationsForChar.size(); j++) {
                    int x1 = locationsForChar.get(i).getLeft();
                    int y1 = locationsForChar.get(i).getRight();
                    int x2 = locationsForChar.get(j).getLeft();
                    int y2 = locationsForChar.get(j).getRight();
                    int diffX = x1 - x2;
                    int diffY = y1 - y2;
                    int nx1 = x1 + diffX;
                    int ny1 = y1 + diffY;
                    int nx2 = x2 - diffX;
                    int ny2 = y2 - diffY;
                    if (nx1 >= 0 && nx1 < width && ny1 >= 0 && ny1 < height) {
                        antinodeLocations[ny1][nx1] = true;
                    }
                    if (nx2 >= 0 && nx2 < width && ny2 >= 0 && ny2 < height) {
                        antinodeLocations[ny2][nx2] = true;
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (antinodeLocations[i][j]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}

class Q08Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question08.getInput();
        int height = input.length;
        int width = input[0].length();
        HashMap<Character, ArrayList<Pair<Integer, Integer>>> locationsPerChar = new HashMap<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char curChar = input[i].charAt(j);
                if (curChar != '.') {
                    ArrayList<Pair<Integer, Integer>> locationsForChar = new ArrayList<>();
                    if (locationsPerChar.containsKey(curChar)) {
                        locationsForChar = locationsPerChar.get(curChar);
                    }
                    locationsForChar.add(Pair.of(j, i));
                    locationsPerChar.put(curChar, locationsForChar);
                }
            }
        }
        boolean[][] antinodeLocations = new boolean[height][width];
        for (Character key : locationsPerChar.keySet()) {
            ArrayList<Pair<Integer, Integer>> locationsForChar = locationsPerChar.get(key);
            for (int i = 0; i < locationsForChar.size() - 1; i++) {
                for (int j = i + 1; j < locationsForChar.size(); j++) {
                    int x1 = locationsForChar.get(i).getLeft();
                    int y1 = locationsForChar.get(i).getRight();
                    int x2 = locationsForChar.get(j).getLeft();
                    int y2 = locationsForChar.get(j).getRight();
                    int diffX = x1 - x2;
                    int diffY = y1 - y2;
                    int gcd = Algorithms.euclidAlg(Math.abs(diffX), Math.abs(diffY));
                    diffX = diffX / gcd;
                    diffY = diffY / gcd;
                    antinodeLocations[y1][x1] = true;
                    int mult = 1;
                    while (x1 + diffX * mult >= 0 && x1 + diffX * mult < width && y1 + diffY * mult >= 0 && y1 + diffY * mult < height) {
                        antinodeLocations[y1 + diffY * mult][x1 + diffX * mult] = true;
                        mult ++;
                    }
                    mult = -1;
                    while (x1 + diffX * mult >= 0 && x1 + diffX * mult < width && y1 + diffY * mult >= 0 && y1 + diffY * mult < height) {
                        antinodeLocations[y1 + diffY * mult][x1 + diffX * mult] = true;
                        mult --;
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (antinodeLocations[i][j]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
