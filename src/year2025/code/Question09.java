package year2025.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Question09 {
    public static void main(String[] args) throws IOException {
        Q09Part1.run();
        Q09Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2025, 9);
    }
}

class Q09Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question09.getInput();
        long largestArea = 0;
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                long[] corner1 = Helper.StringArrayToLongArray(input[i].split(","));
                long[] corner2 = Helper.StringArrayToLongArray(input[j].split(","));
                largestArea = Math.max(largestArea, (Math.abs(corner2[0] - corner1[0]) + 1) * (Math.abs(corner2[1] - corner1[1]) + 1));
            }
        }
        System.out.println(largestArea);
    }
}

class Q09Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    private static int calculateRotation(Point point1, Point point2, Point point3) {
        if (point1.x == point2.x) {
            if (point1.y < point2.y) {
                if (point3.x > point2.x) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                if (point3.x > point2.x) {
                    return 1;
                } else {
                    return -1;
                }
            }
        } else {
            if (point1.x < point2.x) {
                if (point3.y < point2.y) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                if (point3.y < point2.y) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question09.getInput();
        Point[] points = new Point[input.length];
        int rotation = 0;
        int gridXSize = 0;
        int gridYSize = 0;
        ArrayList<Integer> xCoors = new ArrayList<>();
        ArrayList<Integer> yCoors = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            points[i] = Helper.StringToPoint(input[i], ",");
            gridXSize = Math.max(gridXSize, points[i].x + 1);
            gridYSize = Math.max(gridYSize, points[i].y + 1);
            xCoors.add(points[i].x);
            yCoors.add(points[i].y);
            if (i > 1) {
                rotation += calculateRotation(points[i-2], points[i-1], points[i]);
            }
        }
        Collections.sort(xCoors);
        Collections.sort(yCoors);
        ArrayList<Point> missingX = new ArrayList<>();
        ArrayList<Point> missingY = new ArrayList<>();
        for (int i = 0; i < xCoors.size() - 1; i++) {
            int v1 = xCoors.get(i);
            int v2 = xCoors.get(i+1);
            if (v2-v1 > 3) {
                missingX.add(new Point(v1 + 2, v2 - 2));
            }
        }
        for (int i = 0; i < yCoors.size() - 1; i++) {
            int v1 = yCoors.get(i);
            int v2 = yCoors.get(i+1);
            if (v2-v1 > 3) {
                missingY.add(new Point(v1 + 2, v2 - 2));
            }
        }
        gridXSize -= missingX.stream().map(p -> p.y - p.x + 1).reduce(0, Integer::sum);
        gridYSize -= missingY.stream().map(p -> p.y - p.x + 1).reduce(0, Integer::sum);
        rotation += calculateRotation(points[input.length-2], points[input.length-1], points[0]);
        rotation += calculateRotation(points[input.length-1], points[0], points[1]);
        boolean[][] grid = new boolean[gridXSize][gridYSize];
        Point[] scaledPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            int x = points[i].x;
            ArrayList<Point> toSubtract = new ArrayList<>();
            for (int rm = 0; rm < missingX.size(); rm++) {
                if (missingX.get(rm).x > x) {
                    break;
                }
                toSubtract.add(missingX.get(rm));
            }
            x -= toSubtract.stream().map(p -> p.y - p.x + 1).reduce(0, Integer::sum);
            int y = points[i].y;
            toSubtract = new ArrayList<>();
            for (int rm = 0; rm < missingY.size(); rm++) {
                if (missingY.get(rm).x > y) {
                    break;
                }
                toSubtract.add(missingY.get(rm));
            }
            y -= toSubtract.stream().map(p -> p.y - p.x + 1).reduce(0, Integer::sum);
            scaledPoints[i] = new Point(x, y);
        }
        for (int i = 1; i < input.length; i++) {
            if (scaledPoints[i].x == scaledPoints[i - 1].x) {
                int low = Math.min(scaledPoints[i].y, scaledPoints[i - 1].y);
                int high = Math.max(scaledPoints[i].y, scaledPoints[i - 1].y);
                int curXCoor = scaledPoints[i].x;
                for (int c = low; c <= high; c++) {
                    grid[curXCoor][c] = true;
                }
            } else {
                int low = Math.min(scaledPoints[i].x, scaledPoints[i - 1].x);
                int high = Math.max(scaledPoints[i].x, scaledPoints[i - 1].x);
                int curYCoor = scaledPoints[i].y;
                for (int c = low; c <= high; c++) {
                    grid[c][curYCoor] = true;
                }
            }
        }
        if (scaledPoints[0].x == scaledPoints[scaledPoints.length - 1].x) {
            int low = Math.min(scaledPoints[0].y, scaledPoints[scaledPoints.length - 1].y);
            int high = Math.max(scaledPoints[0].y, scaledPoints[scaledPoints.length - 1].y);
            int curXCoor = scaledPoints[0].x;
            for (int c = low; c <= high; c++) {
                grid[curXCoor][c] = true;
            }
        } else {
            int low = Math.min(scaledPoints[0].x, scaledPoints[scaledPoints.length - 1].x);
            int high = Math.max(scaledPoints[0].x, scaledPoints[scaledPoints.length - 1].x);
            int curYCoor = scaledPoints[0].y;
            for (int c = low; c <= high; c++) {
                grid[c][curYCoor] = true;
            }
        }
        int x0 = scaledPoints[0].x;
        int y0 = scaledPoints[0].y;
        int x1 = scaledPoints[1].x;
        int y1 = scaledPoints[1].y;
        int startX;
        int startY;
        if (x0 == x1) {
            if (y0 > y1) {
                startY = y1 + 1;
                if (rotation > 0) {
                    startX = x0 + 1;
                } else {
                    startX = x0 - 1;
                }
            } else {
                startY = y1 - 1;
                if (rotation > 0) {
                    startX = x0 - 1;
                } else {
                    startX = x0 + 1;
                }
            }
        } else {
            if (x0 > x1) {
                startX = x1 + 1;
                if (rotation > 0) {
                    startY = y0 + 1;
                } else {
                    startY = y0 - 1;
                }
            } else {
                startX = x1 - 1;
                if (rotation > 0) {
                    startY = y0 - 1;
                } else {
                    startY = y0 + 1;
                }
            }
        }
        ArrayList<Point> pointsInFill = new ArrayList<>();
        pointsInFill.add(new Point(startX, startY));
        while (!pointsInFill.isEmpty()) {
            Point p = pointsInFill.remove(0);
            if (p.x > 0) {
                if (!grid[p.x-1][p.y]) {
                    grid[p.x-1][p.y] = true;
                    pointsInFill.add(new Point(p.x-1, p.y));
                }
            }
            if (p.y > 0) {
                if (!grid[p.x][p.y-1]) {
                    grid[p.x][p.y-1] = true;
                    pointsInFill.add(new Point(p.x, p.y-1));
                }
            }
            if (p.x < gridXSize-1) {
                if (!grid[p.x+1][p.y]) {
                    grid[p.x+1][p.y] = true;
                    pointsInFill.add(new Point(p.x+1, p.y));
                }
            }
            if (p.y < gridYSize - 1) {
                if (!grid[p.x][p.y+1]) {
                    grid[p.x][p.y+1] = true;
                    pointsInFill.add(new Point(p.x, p.y+1));
                }
            }
        }
        long maxArea = 0;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (!(scaledPoints[i].x == scaledPoints[j].x || scaledPoints[i].y == scaledPoints[j].y)) {
                    int minRealX = Math.min(points[i].x, points[j].x);
                    int maxRealX = Math.max(points[i].x, points[j].x);
                    int minRealY = Math.min(points[i].y, points[j].y);
                    int maxRealY = Math.max(points[i].y, points[j].y);
                    int minX = Math.min(scaledPoints[i].x, scaledPoints[j].x);
                    int maxX = Math.max(scaledPoints[i].x, scaledPoints[j].x);
                    int minY = Math.min(scaledPoints[i].y, scaledPoints[j].y);
                    int maxY = Math.max(scaledPoints[i].y, scaledPoints[j].y);
                    boolean areaBroken = false;
                    areaLoop:
                    for (int k = minX; k <= maxX; k++) {
                        for (int l = minY; l <= maxY; l++) {
                            if (!grid[k][l]) {
                                areaBroken = true;
                                break areaLoop;
                            }
                        }
                    }
                    if (!areaBroken) {
                        maxArea = Math.max(maxArea, ((long) (maxRealX - minRealX + 1)) * ((long) (maxRealY - minRealY + 1)));
                    }
                }
            }
        }
        System.out.println(maxArea);








        // Original attempt with math I gave up on below. I feel like I was pretty close, but the math was giving me a headache.

        /*long[][] points = new long[input.length][];
        int rotation = 0;
        for (int i = 0; i < input.length; i++) {
            points[i] = Helper.StringArrayToLongArray(input[i].split(","));
            if (i > 1) {
                rotation += calculateRotation(points[i-2], points[i-1], points[i]);
            }
        }
        rotation += calculateRotation(points[input.length-2], points[input.length-1], points[0]);
        rotation += calculateRotation(points[input.length-1], points[0], points[1]);
        long largestArea = 0;
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                int firstCorner = i;
                int secondCorner = j;
                long[] corner1 = points[firstCorner];
                long[] corner2 = points[secondCorner];
                // if before1 is left, after must be right or visa versa. is before1 is up, after right must be down or visa versa.
                // if true loop to check if true, break when false. if true: valid area. loop: all other corners, if they are between the two corners, false
                // also check if any corner with its neighbour runs from top to bottom through the area
                if (corner1[0] > corner2[0]) {
                    long[] dummy = corner1;
                    corner1 = corner2;
                    corner2 = dummy;
                    int dummyCorner = firstCorner;
                    firstCorner = secondCorner;
                    secondCorner = dummyCorner;
                }
                long[] prev1 = points[(firstCorner - 1 + input.length) % input.length];
                long[] next1 = points[(firstCorner + 1) % input.length];
                long[] prev2 = points[(firstCorner - 1 + input.length) % input.length];
                long[] next2 = points[(firstCorner + 1) % input.length];
                if (corner1[1] < corner2[1]) {
                    if ((prev1[0] == corner1[0] && (rotation > 0 && prev1[1] > corner1[1] && next1[0] > corner1[0] && prev2[0] < corner2[0] && next2[1] < corner2[1])) ||
                            (prev1[0] != corner1[0] && (rotation < 0 && next1[1] > corner1[1] && prev1[0] > corner1[0] && next2[0] < corner2[0] && prev2[1] < corner2[1]))) {
                        boolean nothingBetweenPoints = true;
                        for (int k = 0; k < input.length; k++) {
                            long[] pointK = points[k];
                            if (pointK[0] >= corner1[0] && pointK[1] >= corner1[1] && pointK[0] <= corner2[0] && pointK[1] <= corner2[1]) {
                                nothingBetweenPoints = false;
                                break;
                            }
                            long[] pointKPrev = points[(k - 1 + input.length) % input.length];
                            long[] pointKNext = points[(k + 1) % input.length];
                            //check if they run through the area
                            if (pointK[0] == pointKPrev[0]) {
                                if (pointK[0] >= corner1[0] && pointK[0] <= corner2[0]) {
                                    if ((pointK[1] < corner1[1] && pointKPrev[1] > corner2[1]) || (pointK[1] > corner1[1] && pointKPrev[1] < corner2[1])) {
                                        nothingBetweenPoints = false;
                                        break;
                                    }
                                }
                                if (pointK[1] >= corner1[1] && pointK[1] <= corner2[1]) {
                                    if ((pointK[0] < corner1[0] && pointKNext[0] > corner2[0]) || (pointK[0] > corner1[0] && pointKNext[0] < corner2[0])) {
                                        nothingBetweenPoints = false;
                                        break;
                                    }
                                }
                            } else {
                                if (pointK[0] >= corner1[0] && pointK[0] <= corner2[0]) {
                                    if ((pointK[1] < corner1[1] && pointKNext[1] > corner2[1]) || (pointK[1] > corner1[1] && pointKNext[1] < corner2[1])) {
                                        nothingBetweenPoints = false;
                                        break;
                                    }
                                }
                                if (pointK[1] >= corner1[1] && pointK[1] <= corner2[1]) {
                                    if ((pointK[0] < corner1[0] && pointKPrev[0] > corner2[0]) || (pointK[0] > corner1[0] && pointKPrev[0] < corner2[0])) {
                                        nothingBetweenPoints = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if (nothingBetweenPoints) {
                            largestArea = Math.max(largestArea, (Math.abs(corner2[0] - corner1[0]) + 1) * (Math.abs(corner2[1] - corner1[1]) + 1));
                        }
                    }
                } else {
                }
            }
        }
        System.out.println(largestArea);*/
    }
}
