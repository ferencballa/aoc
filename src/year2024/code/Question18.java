package year2024.code;

import helpers.Helper;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Question18 {
    public static void main(String[] args) throws IOException {
        Q18Part1.run();
        Q18Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 18);
    }
}

class Q18Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question18.getInput();
        boolean[][] map = new boolean[71][71];
        boolean[][] seen = new boolean[71][71];
        for (int i = 0; i < 1024; i++) {
            int[] coors = Helper.StringArrayToIntArray(input[i].split(","));
            map[coors[1]][coors[0]] = true;
        }
        ArrayList<Pair<Pair<Integer, Integer>, boolean[][]>> positions = new ArrayList<>();
        boolean[][] startVisited = new boolean[71][71];
        startVisited[0][0] = true;
        positions.add(Pair.of(Pair.of(0, 0), startVisited));
        search: while (!positions.isEmpty()) {
            Pair<Pair<Integer, Integer>, boolean[][]> position = positions.remove(0);
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if ((i == 0 && j != 0) || (i != 0 && j == 0)) {
                        int nx = position.getLeft().getLeft() + i;
                        int ny = position.getLeft().getRight() + j;
                        boolean[][] visitedCopy = new boolean[71][71];
                        for (int l = 0; l < 71; l++) {
                            visitedCopy[l] = Arrays.copyOf(position.getRight()[l], 71);
                        }
                        if (nx >= 0 && nx < 71 && ny >= 0 && ny < 71 && !map[ny][nx] && !seen[ny][nx]) {
                            if (nx == 70 && ny == 70) {
                                int count = 0;
                                for (boolean[] col : visitedCopy) {
                                    for (boolean point : col) {
                                        if (point) {
                                            count++;
                                        }
                                    }
                                }
                                System.out.println(count);
                                break search;
                            } else {
                                visitedCopy[ny][nx] = true;
                                seen[ny][nx] = true;
                                positions.add(Pair.of(Pair.of(nx, ny), visitedCopy));
                            }
                        }
                    }
                }
            }
        }
    }
}

class Q18Part2 {

    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question18.getInput();
        boolean[][] map = new boolean[71][71];
        for (int i = 0; i < 1024; i++) {
            int[] coors = Helper.StringArrayToIntArray(input[i].split(","));
            map[coors[1]][coors[0]] = true;
        }
        for (int in = 1024; in < input.length; in++) {
            System.out.println(in);
            int[] coors = Helper.StringArrayToIntArray(input[in].split(","));
            map[coors[1]][coors[0]] = true;
            boolean[][] seen = new boolean[71][71];
            seen[0][0] = true;
            ArrayList<Point> positions = new ArrayList<>();
            positions.add(new Point(0, 0));
            boolean pathFound = false;
            search: while (!positions.isEmpty()) {
                Point position = positions.remove(0);
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if ((i == 0 && j != 0) || (i != 0 && j == 0)) {
                            int nx = position.x + i;
                            int ny = position.y + j;
                            if (nx >= 0 && nx < 71 && ny >= 0 && ny < 71 && !map[ny][nx] && !seen[ny][nx]) {
                                if (nx == 70 && ny == 70) {
                                    pathFound = true;
                                    break search;
                                } else {
                                    seen[ny][nx] = true;
                                    positions.add(new Point(nx, ny));
                                }
                            }
                        }
                    }
                }
            }
            if (!pathFound) {
                System.out.println(coors[0] + "," + coors[1]);
                break;
            }
        }
    }
}
