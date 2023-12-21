package year2023.code;

import helpers.Helper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Question21 {
    public static void main(String[] args) throws IOException {
        Q21Part1.run();
        Q21Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 21);
    }
}

class Q21Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question21.getInput();
        boolean[][] map = new boolean[input[0].length()][];
        boolean[][] visited = new boolean[input[0].length()][];
        int[][] steps = new int[input[0].length()][];
        for (int i = 0; i < map.length; i++) {
            map[i] = new boolean[input.length];
            visited[i] = new boolean[input.length];
            steps[i] = new int[input.length];
        }
        ArrayList<Pair<Point, Integer>> possibleSteps = new ArrayList<>();
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                if (input[y].charAt(x) == '.' || input[y].charAt(x) == 'S') {
                    map[x][y] = true;
                }
                if (input[y].charAt(x) == 'S') {
                    possibleSteps.add(new ImmutablePair<>(new Point(x, y), 0));
                }
            }
        }
        while (!possibleSteps.isEmpty()) {
            Pair<Point, Integer> step = possibleSteps.remove(0);
            Point stepCoors = step.getLeft();
            int x = stepCoors.x;
            int y = stepCoors.y;
            int numOfSteps = step.getRight();
            if (x >= 0 && x < map.length && y >= 0 && y < map[x].length && map[x][y] && !visited[x][y] && numOfSteps <= 64) {
                visited[x][y] = true;
                steps[x][y] = numOfSteps;
                possibleSteps.add(new ImmutablePair<>(new Point(x, y+1), numOfSteps + 1));
                possibleSteps.add(new ImmutablePair<>(new Point(x, y-1), numOfSteps + 1));
                possibleSteps.add(new ImmutablePair<>(new Point(x+1, y), numOfSteps + 1));
                possibleSteps.add(new ImmutablePair<>(new Point(x-1, y), numOfSteps + 1));
            }
        }
        int count = 0;
        for (int x = 0; x < steps.length; x++) {
            for (int y = 0; y < steps[x].length; y++) {
                if (visited[x][y] && steps[x][y] %2 == 0) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}

class Q21Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question21.getInput();
        boolean[][] mapSmall = new boolean[input[0].length()][];
        boolean[][] visitedSmall = new boolean[input[0].length()][];
        int[][] stepsSmall = new int[input[0].length()][];
        for (int i = 0; i < mapSmall.length; i++) {
            mapSmall[i] = new boolean[input.length];
            visitedSmall[i] = new boolean[input.length];
            stepsSmall[i] = new int[input.length];
        }
        int offSetEachSide = 4;
        int magnification = 1 + 2 * offSetEachSide;
        ArrayList<Pair<Point, Integer>> possibleSteps = new ArrayList<>();
        Point startPoint = new Point(-1, -1);
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                if (input[y].charAt(x) == '.' || input[y].charAt(x) == 'S') {
                    mapSmall[x][y] = true;
                }
                if (input[y].charAt(x) == 'S') {
                    startPoint = new Point(x + offSetEachSide * mapSmall.length, y + offSetEachSide * mapSmall.length);
                }
            }
        }
        boolean[][] map = new boolean[mapSmall.length * magnification][];
        boolean[][] visited = new boolean[visitedSmall.length * magnification][];
        int[][] steps = new int[stepsSmall.length * magnification][];
        for (int i = 0; i < map.length; i++) {
            map[i] = new boolean[map.length];
            visited[i] = new boolean[map.length];
            steps[i] = new int[map.length];
        }
        for (int i = 0; i < magnification; i++) {
            for (int j = 0; j < magnification; j++) {
                for (int x = 0; x < mapSmall.length; x++) {
                    for (int y = 0; y < mapSmall.length; y++) {
                        map[x + i * mapSmall.length][y + j * mapSmall.length] = mapSmall[x][y];
                        visited[x + i * mapSmall.length][y + j * mapSmall.length] = visitedSmall[x][y];
                        steps[x + i * mapSmall.length][y + j * mapSmall.length] = stepsSmall[x][y];
                    }
                }
            }
        }
        int width = mapSmall.length;
        int middleToEdge = (mapSmall.length - 1) /2;
        int[] dataPoints = {middleToEdge, middleToEdge + width, middleToEdge + 2 * width};
        ArrayList<Point> resultPoints = new ArrayList<>();
        for (int dataPoint : dataPoints) {
            possibleSteps.add(new ImmutablePair<>(startPoint, 0));
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    visited[i][j] = false;
                    steps[i][j] = 0;
                }
            }
            visited[startPoint.x][startPoint.y] = true;
            while (!possibleSteps.isEmpty()) {
                Pair<Point, Integer> step = possibleSteps.remove(0);
                Point stepCoors = step.getLeft();
                int x = stepCoors.x;
                int y = stepCoors.y;
                int numOfSteps = step.getRight();
                if (!(x >= 0 && x < map.length && y >= 0 && y < map[x].length)) {
                    System.out.println("Out of bounds");
                    break;
                }
                if (y < map[x].length && map[x][y] && numOfSteps <= dataPoint) {
                    if (!visited[x][y+1]) {
                        visited[x][y + 1] = true;
                        possibleSteps.add(new ImmutablePair<>(new Point(x, y + 1), numOfSteps + 1));
                    }
                    if (!visited[x][y-1]) {
                        visited[x][y - 1] = true;
                        possibleSteps.add(new ImmutablePair<>(new Point(x, y - 1), numOfSteps + 1));
                    }
                    if (!visited[x+1][y]) {
                        visited[x + 1][y] = true;
                        possibleSteps.add(new ImmutablePair<>(new Point(x + 1, y), numOfSteps + 1));
                    }
                    if (!visited[x-1][y]) {
                        visited[x - 1][y] = true;
                        possibleSteps.add(new ImmutablePair<>(new Point(x - 1, y), numOfSteps + 1));
                    }
                    steps[x][y] = numOfSteps;
                }
            }
            int count = (dataPoint + 1) % 2;
            for (int x = 0; x < steps.length; x++) {
                for (int y = 0; y < steps[x].length; y++) {
                    if (visited[x][y] && steps[x][y] != 0 && steps[x][y] % 2 == dataPoint % 2) {
                        count++;
                    }
                }
            }
            resultPoints.add(new Point(dataPoint, count));
        }
        /*
        y = ax^2 + bx + c

        0,y1
        1,y2
        2,y3

        y1 = a*0 + b*0 + c
        y1 = c
        c = y1
        y2 = a*1 + b*1 + c
        y2 = a + b + y1
        y2 - y1 = a + b
        y2 - y1 - b = a
        y3 = a*4 + b*2 + c
        y3 = 4a + 2b + y1
        y3 - y1 = 4a + 2b
        y3 - y1 = 4 (y2 - y1 - b) + 2b
        y3 - y1 = 4y2 - 4y1 - 2b
        2b = 4y2 - y3 - 3y1
        b = 2y2 - 0.5y3 - 1.5y1
        a = y2 - y1 - 2y2 + 0.5y3 + 1.5y1
        a = 0.5y1 - y2 + 0.5y3
         */
        double y1 = resultPoints.get(0).y;
        double y2 = resultPoints.get(1).y;
        double y3 = resultPoints.get(2).y;
        double a = 0.5 * y1 - y2 + 0.5 * y3;
        double b = 2 * y2 - 0.5 * y3 - 1.5 * y1;
        double c = y1;
        double stepsToReach = 26501365;
        double iterations = (stepsToReach - middleToEdge) / width;
        System.out.printf("%.0f", ((long) a * iterations * iterations + b * iterations + c));
    }
}
