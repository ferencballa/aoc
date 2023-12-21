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
        /*String pointString = "50,2148),(100,8690),(150,19370),(200,34831),(250,54119),(300,77770),(350,106229),(400,138362),(450,174872),(500,216540),(550,261791),(600,312217),(650,365887),(700,423800),(750,487364),(800,553972),(850,625268),(900,701764),(950,781035),(1000,866516),(1050,954620),(1100,1046766),(1150,1145567),(1200,1246086),(1250,1354122),(1300,1463392),(1350,1577487),(1400,1697864),(1450,1820046),(1500,1946802),(1550,2080621),(1600,2216270),(1650,2358654),(1700,2502509),(1750,2650692),(1800,2806456),(1850,2963116),(1900,3126463),(1950,3293265),(2000,3462847";
        String[] pointStrings = pointString.split("\\),\\(");
        Point[] points = new Point[pointStrings.length];
        for (int i = 0; i < points.length; i++) {
            String[] pointParts = pointStrings[i].split(",");
            points[i] = new Point(Integer.parseInt(pointParts[0]), Integer.parseInt(pointParts[1]));
        }
        for (Point point : points) {
            double diff = 0.866042 * point.x * point.x - 0.292172 * point.x + 74.7766 - point.y;
            System.out.println(diff);
        }*/
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
        int offSetEachSide = 20;
        //int offSetEachSide = 100;
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
        //int[] dataPoints = {middleToEdge + 4 * width};
        //int[] dataPoints = {537};
        //int[] dataPoints = {6, 10, 50, 100, 500};
        //int[] dataPoints = {1,3,5};
        //int[] dataPoints = {100, 200, 300, 400, 500, 600, 700, 800};
        ArrayList<Point> resultPoints = new ArrayList<>();
        String output = "plot points[";
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
            output += "(" + dataPoint + "," + count + "),";
            resultPoints.add(new Point(dataPoint, count));
        }
        System.out.println(output.substring(0, output.length() - 1) + "]");
    }
}
