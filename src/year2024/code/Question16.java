package year2024.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Question16 {
    public static void main(String[] args) throws IOException {
        Q16Part1.run();
        Q16Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 16);
    }
}

class Q16Part1 {
    private static int endX = -1;
    private static int endY = -1;
    private static boolean[][] map;
    private static int[][][] lowestVals;
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question16.getInput();
        int height = input.length;
        int width = input[0].length();
        map = new boolean[height][width];
        lowestVals = new int[height][width][4];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < 4; k++) {
                    lowestVals[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        int x = -1;
        int y = -1;
        int dir = 1; //N = 0, E = 1, S = 2, W = 3
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (input[i].charAt(j) != '#') {
                    map[i][j] = true;
                }
                if (input[i].charAt(j) == 'S') {
                    x = j;
                    y = i;
                }
                if (input[i].charAt(j) == 'E') {
                    endX = j;
                    endY = i;
                }
            }
        }
        boolean[][] visited = new boolean[height][width];
        visited[y][x] = true;
        System.out.println(getLowestScore(x, y, dir, 0, visited, false, 0));
    }

    private static int getLowestScore(int x, int y, int dir, int score, boolean[][] visited, boolean lastMoveTurn, int debugDepth) {
        if (x == endX && y == endY) {
            if (score == 99488) {
                System.out.println("found path");
            }
            return score;
        }
        if (debugDepth > 1000) { //I kept getting overflows, took a wild guess that since the map is 141 by 141 large, and turns would be kept to a minimum you would not go all the way back and forth more than once on each side, so maps steps would remain under 1000. Luckily it worked.
            return Integer.MAX_VALUE;
        }
        lowestVals[y][x][dir] = score;
        int newScore = Integer.MAX_VALUE;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (((i == 0 && j != 0) || (j == 0 && i != 0)) && ((dir == 0 && j == -1) || (dir == 1 && i == 1) || (dir == 2 && j == 1) || (dir == 3 && i == -1))) {
                    if (map[y + j][x + i] && !visited[y + j][x + i] && lowestVals[y+j][x+i][dir] > score + 1) {
                        boolean[][] visitedCopy = new boolean[visited.length][visited[0].length];
                        for (int l = 0; l < visited.length; l++) {
                            visitedCopy[l] = Arrays.copyOf(visited[l], visited[l].length);
                        }
                        visitedCopy[y + j][x + i] = true;
                        newScore = Math.min(newScore, getLowestScore(x + i, y + j, dir, score + 1, visitedCopy, false, debugDepth + 1));
                    } else if (lowestVals[y+j][x+i][dir] <= score + 1) {
                        //System.out.println("rejected");
                    }
                }
            }
        }
        if (!lastMoveTurn) {
            for (int i = -1; i <= 1; i++) {
                if (i != 0) {
                    boolean[][] visitedCopy = new boolean[visited.length][visited[0].length];
                    for (int l = 0; l < visited.length; l++) {
                        visitedCopy[l] = Arrays.copyOf(visited[l], visited[l].length);
                    }
                    int newDir = dir + i;
                    if (newDir < 0) {
                        newDir += 4;
                    }
                    if (newDir > 3) {
                        newDir -=4;
                    }
                    if (lowestVals[y][x][newDir] > score + 1000) {
                        newScore = Math.min(newScore, getLowestScore(x, y, newDir, score + 1000, visitedCopy, true, debugDepth + 1));
                    } else {
                        //System.out.println("rejected");
                    }
                }
            }
        }
        //System.out.println(debugDepth);
        return newScore;
    }
}

class Q16Part2 {
    private static int endX = -1;
    private static int endY = -1;
    private static boolean[][] map;
    private static int[][][] lowestVals;

    private static boolean[][] optimalMap;
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question16.getInput();
        int height = input.length;
        int width = input[0].length();
        map = new boolean[height][width];
        lowestVals = new int[height][width][4];
        optimalMap = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < 4; k++) {
                    lowestVals[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        int x = -1;
        int y = -1;
        int dir = 1; //N = 0, E = 1, S = 2, W = 3
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (input[i].charAt(j) != '#') {
                    map[i][j] = true;
                }
                if (input[i].charAt(j) == 'S') {
                    x = j;
                    y = i;
                }
                if (input[i].charAt(j) == 'E') {
                    endX = j;
                    endY = i;
                }
            }
        }
        boolean[][] visited = new boolean[height][width];
        visited[y][x] = true;
        ArrayList<Point> visitedPoints = new ArrayList<>();
        visitedPoints.add(new Point(x, y));
        System.out.println(getLowestScore(x, y, dir, 0, visited, visitedPoints, false, 0));
        int counter = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (optimalMap[i][j]) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }

    private static int getLowestScore(int x, int y, int dir, int score, boolean[][] visited, ArrayList<Point> visitedPoints, boolean lastMoveTurn, int debugDepth) {
        if (score > 99488) {
            return score;
        }
        if (x == endX && y == endY) {
            if (score == 99488) {
                System.out.println("found path");
                for (Point p : visitedPoints) {
                    optimalMap[p.y][p.x] = true;
                }
            }
            return score;
        }
        if (debugDepth > 1000) { //I kept getting overflows, took a wild guess that since the map is 141 by 141 large, and turns would be kept to a minimum you would not go all the way back and forth more than once on each side, so maps steps would remain under 1000. Luckily it worked.
            return Integer.MAX_VALUE;
        }
        lowestVals[y][x][dir] = score;
        int newScore = Integer.MAX_VALUE;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (((i == 0 && j != 0) || (j == 0 && i != 0)) && ((dir == 0 && j == -1) || (dir == 1 && i == 1) || (dir == 2 && j == 1) || (dir == 3 && i == -1))) {
                    if (map[y + j][x + i] && !visited[y + j][x + i] && lowestVals[y+j][x+i][dir] >= score + 1) {
                        boolean[][] visitedCopy = new boolean[visited.length][visited[0].length];
                        for (int l = 0; l < visited.length; l++) {
                            visitedCopy[l] = Arrays.copyOf(visited[l], visited[l].length);
                        }
                        visitedCopy[y + j][x + i] = true;
                        ArrayList<Point> visClone = (ArrayList) visitedPoints.clone();
                        visClone.add(new Point(x + i, y + j));
                        newScore = Math.min(newScore, getLowestScore(x + i, y + j, dir, score + 1, visitedCopy, visClone, false, debugDepth + 1));
                    }
                }
            }
        }
        if (!lastMoveTurn) {
            for (int i = -1; i <= 1; i++) {
                if (i != 0) {
                    boolean[][] visitedCopy = new boolean[visited.length][visited[0].length];
                    for (int l = 0; l < visited.length; l++) {
                        visitedCopy[l] = Arrays.copyOf(visited[l], visited[l].length);
                    }
                    int newDir = dir + i;
                    if (newDir < 0) {
                        newDir += 4;
                    }
                    if (newDir > 3) {
                        newDir -=4;
                    }
                    if (lowestVals[y][x][newDir] >= score + 1000) {
                        newScore = Math.min(newScore, getLowestScore(x, y, newDir, score + 1000, visitedCopy, (ArrayList) visitedPoints.clone(), true, debugDepth + 1));
                    }
                }
            }
        }
        return newScore;
    }
}
