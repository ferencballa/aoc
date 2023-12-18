package year2023.code;

import helpers.Helper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Question18 {
    public static void main(String[] args) throws IOException {
        Q18Part1.run();
        Q18Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 18);
    }
}

class Q18Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question18.getInput();
        int maxX = 0;
        int minX = 0;
        int maxY = 0;
        int minY = 0;
        int curX = 0;
        int curY = 0;
        for (String line : input) {
            String[] lineParts = line.split(" ");
            int distance = Integer.parseInt(lineParts[1]);
            if (lineParts[0].equals("U")) {
                curY -= distance;
            } else if (lineParts[0].equals("D")) {
                curY += distance;
            } else  if (lineParts[0].equals("L")) {
                curX -= distance;
            } else {
                curX += distance;
            }
            minX = Math.min(minX, curX);
            maxX = Math.max(maxX, curX);
            minY = Math.min(minY, curY);
            maxY = Math.max(maxY, curY);
        }
        boolean[][] map = new boolean[maxX - minX + 1][];
        for (int x = 0; x < map.length; x++) {
            map[x] = new boolean[maxY - minY + 1];
        }
        curX = -minX;
        curY = -minY;
        map[curX][curY] = true;
        ArrayList<Point> corners = new ArrayList<>();
        corners.add(new Point(curX, curY));
        for (String line : input) {
            String[] lineParts = line.split(" ");
            int distance = Integer.parseInt(lineParts[1]);
            if (lineParts[0].equals("U")) {
                for (int y = curY - 1; y >= curY - distance; y--) {
                    map[curX][y] = true;
                }
                curY -= distance;
            } else if (lineParts[0].equals("D")) {
                for (int y = curY + 1; y <= curY + distance; y++) {
                    map[curX][y] = true;
                }
                curY += distance;
            } else  if (lineParts[0].equals("L")) {
                for (int x = curX - 1; x >= curX - distance; x--) {
                    map[x][curY] = true;
                }
                curX -= distance;
            } else {
                for (int x = curX + 1; x <= curX + distance; x++) {
                    map[x][curY] = true;
                }
                curX += distance;
            }
            corners.add(new Point(curX, curY));
        }
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                if (map[x][y]) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
        System.out.println();
        System.out.println();
        ArrayList<Point> pointsToVisit = new ArrayList<>();
        int lowestYForXZero = map[0].length;
        for (Point corner : corners) {
            if (corner.x == 0) {
                lowestYForXZero = Math.min(lowestYForXZero, corner.y);
            }
        }
        pointsToVisit.add(new Point(1, lowestYForXZero + 1));
        map[1][lowestYForXZero] = true;
        while (!pointsToVisit.isEmpty()) {
            Point p = pointsToVisit.remove(0);
            if (p.x > 0 && !map[p.x - 1][p.y]) {
                pointsToVisit.add(new Point(p.x - 1, p.y));
                map[p.x - 1][p.y] = true;
            }
            if (p.x < map.length - 1 && !map[p.x + 1][p.y]) {
                pointsToVisit.add(new Point(p.x + 1, p.y));
                map[p.x + 1][p.y] = true;
            }
            if (p.y > 0 && !map[p.x][p.y - 1]) {
                pointsToVisit.add(new Point(p.x, p.y - 1));
                map[p.x][p.y - 1] = true;
            }
            if (p.y < map[0].length - 1&& !map[p.x][p.y + 1]) {
                pointsToVisit.add(new Point(p.x, p.y + 1));
                map[p.x][p.y + 1] = true;
            }
        }
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                if (map[x][y]) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
        int count = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}

class Q18Part2 {

    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question18.getInput();
        int maxX = 0;
        int minX = 0;
        int maxY = 0;
        int minY = 0;
        int curX = 0;
        int curY = 0;
        for (String line : input) {
            String[] lineParts = line.split(" ");
            String[] c = lineParts[2].split("");
            int distance = 0;
            for (int i = 2; i < 7; i++) {
                int val = 0;
                if (c[i].equals("a")) {
                    val = 10;
                } else if (c[i].equals("b")) {
                    val = 11;
                } else if (c[i].equals("c")) {
                    val = 12;
                } else if (c[i].equals("d")) {
                    val = 13;
                } else if (c[i].equals("e")) {
                    val = 14;
                } else if (c[i].equals("f")) {
                    val = 15;
                } else {
                    val = Integer.parseInt(c[i]);
                }
                distance += val * Math.pow(16, 6 - i);
            }
            if (c[7].equals("3")) {
                curY -= distance;
            } else if (c[7].equals("1")) {
                curY += distance;
            } else  if (c[7].equals("2")) {
                curX -= distance;
            } else {
                curX += distance;
            }
            minX = Math.min(minX, curX);
            maxX = Math.max(maxX, curX);
            minY = Math.min(minY, curY);
            maxY = Math.max(maxY, curY);
        }
        /*boolean[][] map = new boolean[maxX - minX + 1][];
        for (int x = 0; x < map.length; x++) {
            map[x] = new boolean[maxY - minY + 1];
        }*/
        curX = -minX;
        curY = -minY;
        //map[curX][curY] = true;
        ArrayList<Point> corners = new ArrayList<>();
        corners.add(new Point(curX, curY));
        for (String line : input) {
            String[] lineParts = line.split(" ");
            String[] c = lineParts[2].split("");
            int distance = 0;
            for (int i = 2; i < 7; i++) {
                int val = 0;
                if (c[i].equals("a")) {
                    val = 10;
                } else if (c[i].equals("b")) {
                    val = 11;
                } else if (c[i].equals("c")) {
                    val = 12;
                } else if (c[i].equals("d")) {
                    val = 13;
                } else if (c[i].equals("e")) {
                    val = 14;
                } else if (c[i].equals("f")) {
                    val = 15;
                } else {
                    val = Integer.parseInt(c[i]);
                }
                distance += val * Math.pow(16, 6 - i);
            }
            if (c[7].equals("3")) {
                curY -= distance;
            } else if (c[7].equals("1")) {
                curY += distance;
            } else  if (c[7].equals("2")) {
                curX -= distance;
            } else {
                curX += distance;
            }
            corners.add(new Point(curX, curY));
        }
        int lowestYForXZero = Integer.MAX_VALUE;
        int indexOfLowestY = -1;
        for (int i = 0; i < corners.size(); i++) {
            Point corner = corners.get(i);
            if (corner.x == 0) {
                if (corner.y < lowestYForXZero) {
                    lowestYForXZero = corner.y;
                    indexOfLowestY = i;
                }
            }
        }
        for (int i = 0; i < indexOfLowestY; i++) {
            corners.add(corners.remove(0));
        }
        int rotation = 1;
        if (corners.get(1).x == 0) {
            rotation = -1;
        }
        ArrayList<Pair<Double, Double>> doubleCorners = new ArrayList<>();
        doubleCorners.add(new ImmutablePair<>(corners.get(0).x - 0.5, corners.get(0).y - 0.5));
        for (int i = 1; i < corners.size(); i++) {
            double x0 = corners.get(i - 1).x;
            double y0 = corners.get(i - 1).y;
            double x1 = corners.get(i).x;
            double y1 = corners.get(i).y;
            double x2 = corners.get(0).x;
            double y2 = corners.get(0).y;
            if (i < corners.size() - 1) {
                x2 = corners.get(i + 1).x;
                y2 = corners.get(i + 1).y;
            }
            if (y1 == y2) {
                if (x1 < x2) {
                    y1 -= 0.5 * rotation;
                } else {
                    y1 += 0.5 * rotation;
                }
                if (y0 > y1) {
                    x1 -= 0.5 * rotation;
                } else {
                    x1 += 0.5 * rotation;
                }
            } else {
                if (y1 < y2) {
                    x1 += 0.5 * rotation;
                } else {
                    x1 -= 0.5 * rotation;
                }
                if (x0 > x1) {
                    y1 += 0.5 * rotation;
                } else {
                    y1 -= 0.5 * rotation;
                }
            }
            doubleCorners.add(new ImmutablePair<>(x1, y1));
        }
        double count = 0;
        /*doubleCorners = new ArrayList<>();
        doubleCorners.add(new ImmutablePair<>(-0.5,0.5));
        doubleCorners.add(new ImmutablePair<>(3.5,0.5));
        doubleCorners.add(new ImmutablePair<>(3.5,4.5));
        doubleCorners.add(new ImmutablePair<>(-0.5,4.5));*/
        for (int i = 0; i < doubleCorners.size() - 1; i++) {
            Pair<Double, Double> p1 = doubleCorners.get(i);
            Pair<Double, Double> p2 = doubleCorners.get(i+1);
            count += p1.getLeft() * p2.getRight() - p2.getLeft() * p1.getRight();
        }
        Pair<Double, Double> finalP1 = doubleCorners.get(doubleCorners.size() - 1);
        Pair<Double, Double> finalP2 = doubleCorners.get(0);
        count += finalP1.getLeft() * finalP2.getRight() - finalP2.getLeft() * finalP1.getRight();
        System.out.println(count /2.0);
        /*for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                if (map[x][y]) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
        System.out.println();
        System.out.println();
        ArrayList<Point> pointsToVisit = new ArrayList<>();
        int lowestYForXZero = map[0].length;
        for (Point corner : corners) {
            if (corner.x == 0) {
                lowestYForXZero = Math.min(lowestYForXZero, corner.y);
            }
        }
        pointsToVisit.add(new Point(1, lowestYForXZero + 1));
        map[1][lowestYForXZero] = true;
        while (!pointsToVisit.isEmpty()) {
            Point p = pointsToVisit.remove(0);
            if (p.x > 0 && !map[p.x - 1][p.y]) {
                pointsToVisit.add(new Point(p.x - 1, p.y));
                map[p.x - 1][p.y] = true;
            }
            if (p.x < map.length - 1 && !map[p.x + 1][p.y]) {
                pointsToVisit.add(new Point(p.x + 1, p.y));
                map[p.x + 1][p.y] = true;
            }
            if (p.y > 0 && !map[p.x][p.y - 1]) {
                pointsToVisit.add(new Point(p.x, p.y - 1));
                map[p.x][p.y - 1] = true;
            }
            if (p.y < map[0].length - 1&& !map[p.x][p.y + 1]) {
                pointsToVisit.add(new Point(p.x, p.y + 1));
                map[p.x][p.y + 1] = true;
            }
        }
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                if (map[x][y]) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
        int count = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y]) {
                    count++;
                }
            }
        }
        System.out.println(count);*/
    }
}
