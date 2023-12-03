package year2023.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question03 {
    public static void main(String[] args) throws IOException {
        Q03Part1.run();
        Q03Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 3);
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
        for (int lineNumber = 0; lineNumber < input.length; lineNumber++) {
            String s = input[lineNumber];
            int i = 0;
            while (i < s.length()) {
                if (s.charAt(i) > 57 || s.charAt(i) < 48) {
                    i++;
                } else {
                    int startOfNumber = i;
                    while (i < s.length() && s.charAt(i) <= 57 && s.charAt(i) >= 48) {
                        i++;
                    }
                    boolean isPartNumber = false;
                    if (lineNumber != 0) {
                        if (startOfNumber != 0) {
                            isPartNumber = isPartNumber || isPartNumber(input, startOfNumber - 1, lineNumber - 1);
                        }
                        for (int z = startOfNumber; z < i; z++) {
                            isPartNumber = isPartNumber || isPartNumber(input, z, lineNumber - 1);
                        }
                        if (i < s.length()) {
                            isPartNumber = isPartNumber || isPartNumber(input, i, lineNumber - 1);
                        }
                    }
                    if (startOfNumber != 0) {
                        isPartNumber = isPartNumber || isPartNumber(input, startOfNumber - 1, lineNumber);
                    }
                    if (i < s.length()) {
                        isPartNumber = isPartNumber || isPartNumber(input, i, lineNumber);
                    }
                    if (lineNumber != s.length() - 1) {
                        if (startOfNumber != 0) {
                            isPartNumber = isPartNumber || isPartNumber(input, startOfNumber - 1, lineNumber + 1);
                        }
                        for (int z = startOfNumber; z < i; z++) {
                            isPartNumber = isPartNumber || isPartNumber(input, z, lineNumber + 1);
                        }
                        if (i < s.length()) {
                            isPartNumber = isPartNumber || isPartNumber(input, i, lineNumber + 1);
                        }
                    }
                    if (isPartNumber) {
                        count += Integer.parseInt(s.substring(startOfNumber, i));
                    }
                }
            }
        }
        System.out.println(count);
    }

    private static boolean isPartNumber(String[] input, int x, int y) {
        return input[y].charAt(x) != '.' && (input[y].charAt(x) > 57 || input[y].charAt(x) < 48);
    }
}

class Q03Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question03.getInput();
        HashMap<Point, ArrayList<Point>> adjacentNumbers = new HashMap<>();
        HashMap<Point, Integer> valuesForStartingPoints = new HashMap<>();
        for (int lineNumber = 0; lineNumber < input.length; lineNumber++) {
            String s = input[lineNumber];
            int i = 0;
            while (i < s.length()) {
                if (s.charAt(i) > 57 || s.charAt(i) < 48) {
                    i++;
                } else {
                    int startOfNumber = i;
                    while (i < s.length() && s.charAt(i) <= 57 && s.charAt(i) >= 48) {
                        i++;
                    }
                    if (lineNumber != 0) {
                        if (startOfNumber != 0) {
                            if (isPartNumber(input, startOfNumber - 1, lineNumber - 1)) {
                                Point curP = new Point(startOfNumber - 1, lineNumber - 1);
                                addNumberIfNotAlreadyPresent(adjacentNumbers, curP, startOfNumber, lineNumber);
                            }
                        }
                        for (int z = startOfNumber; z < i; z++) {
                            if (isPartNumber(input, z, lineNumber - 1)) {
                                Point curP = new Point(z, lineNumber - 1);
                                addNumberIfNotAlreadyPresent(adjacentNumbers, curP, startOfNumber, lineNumber);
                            }
                        }
                        if (i < s.length()) {
                            if (isPartNumber(input, i, lineNumber - 1)) {
                                Point curP = new Point(i, lineNumber - 1);
                                addNumberIfNotAlreadyPresent(adjacentNumbers, curP, startOfNumber, lineNumber);
                            }
                        }
                    }
                    if (startOfNumber != 0) {
                        if (isPartNumber(input, startOfNumber - 1, lineNumber)) {
                            Point curP = new Point(startOfNumber - 1, lineNumber);
                            addNumberIfNotAlreadyPresent(adjacentNumbers, curP, startOfNumber, lineNumber);
                        }
                    }
                    if (i < s.length()) {
                        if (isPartNumber(input, i, lineNumber)) {
                            Point curP = new Point(i, lineNumber);
                            addNumberIfNotAlreadyPresent(adjacentNumbers, curP, startOfNumber, lineNumber);
                        }
                    }
                    if (lineNumber != s.length() - 1) {
                        if (startOfNumber != 0) {
                            if (isPartNumber(input, startOfNumber - 1, lineNumber + 1)) {
                                Point curP = new Point(startOfNumber - 1, lineNumber + 1);
                                addNumberIfNotAlreadyPresent(adjacentNumbers, curP, startOfNumber, lineNumber);
                            }
                        }
                        for (int z = startOfNumber; z < i; z++) {
                            if (isPartNumber(input, z, lineNumber + 1)) {
                                Point curP = new Point(z, lineNumber + 1);
                                addNumberIfNotAlreadyPresent(adjacentNumbers, curP, startOfNumber, lineNumber);
                            }
                        }
                        if (i < s.length()) {
                            if (isPartNumber(input, i, lineNumber + 1)) {
                                Point curP = new Point(i, lineNumber + 1);
                                addNumberIfNotAlreadyPresent(adjacentNumbers, curP, startOfNumber, lineNumber);
                            }
                        }
                    }
                    valuesForStartingPoints.put(new Point(startOfNumber, lineNumber), Integer.parseInt(s.substring(startOfNumber, i)));
                }
            }
        }
        int count = 0;
        for (Point p : adjacentNumbers.keySet()) {
            ArrayList<Point> points = adjacentNumbers.get(p);
            if (points.size() == 2) {
                count += valuesForStartingPoints.get(points.get(0)) * valuesForStartingPoints.get(points.get(1));
            }
        }
        System.out.println(count);
    }

    private static boolean isPartNumber(String[] input, int x, int y) {
        return input[y].charAt(x) != '.' && (input[y].charAt(x) > 57 || input[y].charAt(x) < 48);
    }

    private static void addNumberIfNotAlreadyPresent(HashMap<Point, ArrayList<Point>> adjacentNumbers, Point curP, int startOfNumber, int lineNumber) {
        ArrayList<Point> numbersForThisPoint = adjacentNumbers.computeIfAbsent(curP, k -> new ArrayList<>());
        boolean numberIsPresent = false;
        for (Point p : numbersForThisPoint) {
            if (p.x == startOfNumber && p.y == lineNumber) {
                numberIsPresent = true;
                break;
            }
        }
        if (!numberIsPresent) {
            numbersForThisPoint.add(new Point(startOfNumber, lineNumber));
        }
    }
}
