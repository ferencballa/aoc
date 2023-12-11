package year2023.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Question11 {
    public static void main(String[] args) throws IOException {
        Q11Part1.run();
        Q11Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 11);
    }
}

class Q11Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question11.getInput();
        char[][] notExpandedCoordinates = new char[input[0].length()][];
        for (int i = 0; i < input[0].length(); i++) {
            notExpandedCoordinates[i] = new char[input.length];
        }
        ArrayList<Integer> emptyRowNumbers = new ArrayList<>();
        for (int y = 0; y < input.length; y++) {
            boolean rowIsEmpty = true;
            for (int x = 0; x < input[0].length(); x++) {
                notExpandedCoordinates[x][y] = input[y].charAt(x);
                if (notExpandedCoordinates[x][y] == '#') {
                    rowIsEmpty = false;
                }
            }
            if (rowIsEmpty) {
                emptyRowNumbers.add(y);
            }
        }
        ArrayList<Integer> emptyColNumbers = new ArrayList<>();
        for (int x = 0; x < input[0].length(); x++) {
            boolean colIsEmpty = true;
            for (int y = 0; y < input.length; y++) {
                if (notExpandedCoordinates[x][y] == '#') {
                    colIsEmpty = false;
                    break;
                }
            }
            if (colIsEmpty) {
                emptyColNumbers.add(x);
            }
        }
        int newWidth = notExpandedCoordinates.length + emptyColNumbers.size();
        int newHeight = notExpandedCoordinates[0].length + emptyRowNumbers.size();
        char[][] coordinates = new char[newWidth][];
        for (int i = 0; i < newWidth; i++) {
            coordinates[i] = new char[newHeight];
        }
        int emptyColCounter = 0;
        for (int x = 0; x < coordinates.length; x++) {
            if (emptyColCounter < emptyColNumbers.size() && emptyColNumbers.get(emptyColCounter) == x - emptyColCounter) {
                Arrays.fill(coordinates[x], '.');
                emptyColCounter++;
            } else {
                int emptyRowCounter = 0;
                for (int y = 0; y < coordinates[x].length; y++) {
                    if (emptyRowCounter < emptyRowNumbers.size() && emptyRowNumbers.get(emptyRowCounter) == y - emptyRowCounter) {
                        coordinates[x][y] = '.';
                        emptyRowCounter++;
                    } else {
                        coordinates[x][y] = notExpandedCoordinates[x - emptyColCounter][y - emptyRowCounter];
                    }
                }
            }
        }
        ArrayList<Point> galaxies = new ArrayList<>();
        for (int x = 0; x < coordinates.length; x++) {
            for (int y = 0; y < coordinates[x].length; y++) {
                if (coordinates[x][y] == '#') {
                    galaxies.add(new Point(x, y));
                }
            }
        }
        int count = 0;
        for (int i = 0; i < galaxies.size() - 1; i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                count += Math.abs(galaxies.get(i).x - galaxies.get(j).x) + Math.abs(galaxies.get(i).y - galaxies.get(j).y);
            }
        }
        System.out.println(count);
    }
}

class Q11Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question11.getInput();
        char[][] notExpandedCoordinates = new char[input[0].length()][];
        for (int i = 0; i < input[0].length(); i++) {
            notExpandedCoordinates[i] = new char[input.length];
        }
        ArrayList<Integer> emptyRowNumbers = new ArrayList<>();
        for (int y = 0; y < input.length; y++) {
            boolean rowIsEmpty = true;
            for (int x = 0; x < input[0].length(); x++) {
                notExpandedCoordinates[x][y] = input[y].charAt(x);
                if (notExpandedCoordinates[x][y] == '#') {
                    rowIsEmpty = false;
                }
            }
            if (rowIsEmpty) {
                emptyRowNumbers.add(y);
            }
        }
        ArrayList<Integer> emptyColNumbers = new ArrayList<>();
        for (int x = 0; x < input[0].length(); x++) {
            boolean colIsEmpty = true;
            for (int y = 0; y < input.length; y++) {
                if (notExpandedCoordinates[x][y] == '#') {
                    colIsEmpty = false;
                    break;
                }
            }
            if (colIsEmpty) {
                emptyColNumbers.add(x);
            }
        }
        ArrayList<Point> galaxies = new ArrayList<>();
        for (int x = 0; x < notExpandedCoordinates.length; x++) {
            for (int y = 0; y < notExpandedCoordinates[x].length; y++) {
                if (notExpandedCoordinates[x][y] == '#') {
                    galaxies.add(new Point(x, y));
                }
            }
        }
        long count = 0;
        for (int i = 0; i < galaxies.size() - 1; i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                Point p1 = galaxies.get(i);
                Point p2 = galaxies.get(j);
                int x1 = Math.min(p1.x, p2.x);
                int x2 = Math.max(p1.x, p2.x);
                int xEmpty = 0;
                for (Integer emptyColNumber : emptyColNumbers) {
                    if (emptyColNumber > x1 && emptyColNumber < x2) {
                        xEmpty++;
                    }
                }
                int y1 = Math.min(p1.y, p2.y);
                int y2 = Math.max(p1.y, p2.y);
                int yEmpty = 0;
                for (Integer emptyRowNumber : emptyRowNumbers) {
                    if (emptyRowNumber > y1 && emptyRowNumber < y2) {
                        yEmpty++;
                    }
                }
                count += Math.abs(galaxies.get(i).x - galaxies.get(j).x) + Math.abs(galaxies.get(i).y - galaxies.get(j).y) + xEmpty * 999999L + yEmpty * 999999L;
            }
        }
        System.out.println(count);
    }
}
