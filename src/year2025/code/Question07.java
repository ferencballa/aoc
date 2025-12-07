package year2025.code;

import helpers.Helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Question07 {
    public static void main(String[] args) throws IOException {
        Q07Part1.run();
        Q07Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2025, 7);
    }
}

class Q07Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question07.getInput();
        int startX = 0;
        while (input[0].charAt(startX) != 'S') {
            startX++;
        }
        HashSet<Integer> xPositions = new HashSet<>();
        xPositions.add(startX);
        int yPos = 2;
        int count = 0;
        while (yPos < input.length) {
            HashSet<Integer> newXPositions = new HashSet<>();
            for (Integer x : xPositions) {
                if (input[yPos].charAt(x) == '^') {
                    count++;
                    newXPositions.add(x-1);
                    newXPositions.add(x+1);
                } else {
                    newXPositions.add(x);
                }
            }
            xPositions = newXPositions;
            yPos+= 2;
        }
        System.out.println(count);
    }
}

class Q07Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question07.getInput();
        int startX = 0;
        while (input[0].charAt(startX) != 'S') {
            startX++;
        }
        HashMap<Integer, Long> xPositions = new HashMap<>();
        xPositions.put(startX, 1L);
        int yPos = 2;
        while (yPos < input.length) {
            HashMap<Integer, Long> newXPositions = new HashMap<>();
            for (Integer x : xPositions.keySet()) {
                long curVal = xPositions.get(x);
                if (input[yPos].charAt(x) == '^') {
                    if (newXPositions.containsKey(x-1)) {
                        newXPositions.put(x-1, newXPositions.get(x-1) + curVal);
                    } else {
                        newXPositions.put(x-1, curVal);
                    }
                    if (newXPositions.containsKey(x+1)) {
                        newXPositions.put(x+1, newXPositions.get(x+1) + curVal);
                    } else {
                        newXPositions.put(x+1, curVal);
                    }
                } else {
                    if (newXPositions.containsKey(x)) {
                        newXPositions.put(x, newXPositions.get(x) + curVal);
                    } else {
                        newXPositions.put(x, curVal);
                    }
                }
            }
            xPositions = newXPositions;
            yPos+= 2;
        }
        long count = 0;
        for (Integer x : xPositions.keySet()) {
            count += xPositions.get(x);
        }
        System.out.println(count);
    }
}