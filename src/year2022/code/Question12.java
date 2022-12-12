package year2022.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Question12 {
    public static void main(String[] args) throws IOException {
        Q12Part1.run();
        Q12Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 12);
    }
}

class Q12Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question12.getInput();
        CustomPoint[][] grid = new CustomPoint[input.length][];
        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;
        for (int i = 0; i < input.length; i++) {
            grid[i] = new CustomPoint[input[i].length()];
            for (int j = 0; j < input[i].length(); j++) {
                if (input[i].charAt(j) == 'S') {
                    grid[i][j] = new CustomPoint(i, j, 0);
                    startX = i;
                    startY = j;
                } else if (input[i].charAt(j) == 'E') {
                    grid[i][j] = new CustomPoint(i, j, 25);
                    endX = i;
                    endY = j;
                } else {
                    grid[i][j] = new CustomPoint(i, j, input[i].charAt(j) - 97);
                }
            }
        }
        ArrayList<Point> toExplorePoints = new ArrayList<>();
        toExplorePoints.add(new Point(startX, startY));
        explore:
        while (!toExplorePoints.isEmpty()) {
            Point curPoint = toExplorePoints.remove(0);
            for (int di = -1; di <= 1; di++) {
                for (int dj = -1; dj <= 1; dj++) {
                    if ((Math.abs(di) + Math.abs(dj) == 1) && (curPoint.x + di >= 0) && (curPoint.x + di < grid.length) && (curPoint.y + dj >= 0) && (curPoint.y + dj < grid[0].length)) {
                        if (grid[curPoint.x + di][curPoint.y + dj].previous == null) {
                            if (grid[curPoint.x + di][curPoint.y + dj].height <= grid[curPoint.x][curPoint.y].height + 1) {
                                grid[curPoint.x + di][curPoint.y + dj].previous = new Point(curPoint.x, curPoint.y);
                                toExplorePoints.add(new Point(curPoint.x + di, curPoint.y + dj));
                                if (curPoint.x + di == endX && curPoint.y + dj == endY) {
                                    break explore;
                                }
                            }
                        }
                    }
                }
            }
        }
        int steps = 0;
        int curX = endX;
        int curY = endY;
        while (!(curX == startX && curY == startY)) {
            steps++;
            CustomPoint curPoint = grid[curX][curY];
            curX = curPoint.previous.x;
            curY = curPoint.previous.y;
        }
        System.out.println(steps);
    }
}

class CustomPoint {
    int x;
    int y;
    int height;
    Point previous;

    public CustomPoint(int inX, int inY, int h) {
        x = inX;
        y = inY;
        height = h;
        previous = null;
    }
}

class Q12Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question12.getInput();
        CustomPoint[][] startGrid = new CustomPoint[input.length][];
        int endX = -1;
        int endY = -1;
        ArrayList<Point> startCoors = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            startGrid[i] = new CustomPoint[input[i].length()];
            for (int j = 0; j < input[i].length(); j++) {
                if (input[i].charAt(j) == 'S') {
                    startGrid[i][j] = new CustomPoint(i, j, 0);
                    startCoors.add(new Point(i, j));
                } else if (input[i].charAt(j) == 'E') {
                    startGrid[i][j] = new CustomPoint(i, j, 25);
                    endX = i;
                    endY = j;
                } else {
                    if (input[i].charAt(j) == 'a') {
                        startCoors.add(new Point(i, j));
                    }
                    startGrid[i][j] = new CustomPoint(i, j, input[i].charAt(j) - 97);
                }
            }
        }
        int shortestRoute = Integer.MAX_VALUE;
        for (Point startP : startCoors) {
            CustomPoint[][] grid = new CustomPoint[startGrid.length][];
            for (int i = 0; i < startGrid.length; i++) {
                grid[i] = new CustomPoint[startGrid[i].length];
                for (int j = 0; j < startGrid[0].length; j++) {
                    grid[i][j] = new CustomPoint(startGrid[i][j].x, startGrid[i][j].y, startGrid[i][j].height);
                }
            }
            int startX = startP.x;
            int startY = startP.y;
            ArrayList<Point> toExplorePoints = new ArrayList<>();
            toExplorePoints.add(new Point(startX, startY));
            boolean endFound = false;
            explore:
            while (!toExplorePoints.isEmpty()) {
                Point curPoint = toExplorePoints.remove(0);
                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        if ((Math.abs(di) + Math.abs(dj) == 1) && (curPoint.x + di >= 0) && (curPoint.x + di < grid.length) && (curPoint.y + dj >= 0) && (curPoint.y + dj < grid[0].length)) {
                            if (grid[curPoint.x + di][curPoint.y + dj].previous == null) {
                                if (grid[curPoint.x + di][curPoint.y + dj].height <= grid[curPoint.x][curPoint.y].height + 1) {
                                    grid[curPoint.x + di][curPoint.y + dj].previous = new Point(curPoint.x, curPoint.y);
                                    toExplorePoints.add(new Point(curPoint.x + di, curPoint.y + dj));
                                    if (curPoint.x + di == endX && curPoint.y + dj == endY) {
                                        endFound = true;
                                        break explore;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (endFound) {
                int steps = 0;
                int curX = endX;
                int curY = endY;
                while (!(curX == startX && curY == startY)) {
                    steps++;
                    CustomPoint curPoint = grid[curX][curY];
                    curX = curPoint.previous.x;
                    curY = curPoint.previous.y;
                }
                shortestRoute = Math.min(shortestRoute, steps);
            }
        }
        System.out.println(shortestRoute);
    }
}
