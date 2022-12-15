package year2022.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.*;

public class Question15 {
    public static void main(String[] args) throws IOException {
        Q15Part1.run();
        Q15Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 15);
    }
}

class Q15Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question15.getInput();
        int[][] coors = Arrays.stream(input).map(inp -> {
            String[] parts = inp.split(" ");
            int[] vals = new int[4];
            vals[0] = Integer.parseInt(parts[2].split("=")[1].substring(0, parts[2].split("=")[1].length() - 1));
            vals[1] = Integer.parseInt(parts[3].split("=")[1].substring(0, parts[3].split("=")[1].length() - 1));
            vals[2] = Integer.parseInt(parts[8].split("=")[1].substring(0, parts[8].split("=")[1].length() - 1));
            vals[3] = Integer.parseInt(parts[9].split("=")[1]);
            return vals;
        }).toArray(int[][]::new);
        int testY = 2000000;
        HashSet<Integer> scannedX = new HashSet<>();
        for (int[] lineCoors : coors) {
            int distanceToBeacon = Math.abs(lineCoors[0] - lineCoors[2]) + Math.abs(lineCoors[1] - lineCoors[3]);
            int distanceToTestLine = Math.abs(lineCoors[1] - testY);
            if (distanceToTestLine <= distanceToBeacon) {
                for (int i = distanceToTestLine - distanceToBeacon; i <= distanceToBeacon - distanceToTestLine; i++) {
                    scannedX.add(lineCoors[0] + i);
                }
            }
        }
        for (int[] lineCoors : coors) {
            if (lineCoors[3] == testY) {
                scannedX.remove(lineCoors[2]);
            }
        }
        System.out.println(scannedX.size());
    }
}

class Q15Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question15.getInput();
        int[][] coors = Arrays.stream(input).map(inp -> {
            String[] parts = inp.split(" ");
            int[] vals = new int[4];
            vals[0] = Integer.parseInt(parts[2].split("=")[1].substring(0, parts[2].split("=")[1].length() - 1));
            vals[1] = Integer.parseInt(parts[3].split("=")[1].substring(0, parts[3].split("=")[1].length() - 1));
            vals[2] = Integer.parseInt(parts[8].split("=")[1].substring(0, parts[8].split("=")[1].length() - 1));
            vals[3] = Integer.parseInt(parts[9].split("=")[1]);
            return vals;
        }).toArray(int[][]::new);
        int testVal = 4000000;
        ArrayList<Point>[] rangeListPerY = new ArrayList[testVal + 1];
        for (int i = 0; i <= testVal; i++) {
            rangeListPerY[i] = new ArrayList<>();
        }
        for (int[] lineCoors : coors) {
            int distanceToBeacon = Math.abs(lineCoors[0] - lineCoors[2]) + Math.abs(lineCoors[1] - lineCoors[3]);
            for (int y = -distanceToBeacon; y <= distanceToBeacon; y++) {
                if (lineCoors[1] + y >= 0 && lineCoors[1] + y <= testVal) {
                    int x1 = Math.max(lineCoors[0] + Math.abs(y) - distanceToBeacon, 0);
                    int x2 = Math.min(lineCoors[0] + distanceToBeacon - Math.abs(y), testVal);
                    rangeListPerY[lineCoors[1] + y].add(new Point(x1, x2));
                }
            }
        }
        for (int y = 0; y <= testVal; y++) {
            ArrayList<Point> ranges = rangeListPerY[y];
            ranges.sort((p1, p2) -> {
                if (p1.x == p2.x) {
                    return Integer.compare(p1.y, p2.y);
                } else {
                    return Integer.compare(p1.x, p2.x);
                }
            });
            for (int r = 0; r < ranges.size() - 1; r++) {
                if (ranges.get(r).y >= ranges.get(r+1).y) {
                    ranges.remove(r + 1);
                    r--;
                }
            }
            if (ranges.get(0).x != 0) {
                System.out.println("x: " + 0 + ", y: " + y + ", solution: " + (y));
            }
            for (int r = 0; r < ranges.size() - 1; r++) {
                if (ranges.get(r).y < ranges.get(r+1).x - 1) {
                    System.out.println("x: " + (ranges.get(r).y + 1) + ", y: " + y + ", solution: " + ((long) (ranges.get(r).y + 1) * (long) 4000000 + (long) y));
                }
            }
            boolean rowLastOnScan = false;
            for (Point range : ranges) {
                if (range.y == testVal) {
                    rowLastOnScan = true;
                    break;
                }
            }
            if (!rowLastOnScan) {
                System.out.println("x: " + testVal + ", y: " + y + ", solution: " + ((long) testVal * (long) 4000000 + (long) y));
            }
        }
    }
}
