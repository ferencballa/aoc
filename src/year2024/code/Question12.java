package year2024.code;

import helpers.Helper;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Question12 {
    public static void main(String[] args) throws IOException {
        Q12Part1.run();
        Q12Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 12);
    }
}

class Q12Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question12.getInput();
        String[][] map = new String[input.length][];
        for (int i = 0; i < input.length; i++) {
            map[i] = input[i].split("");
        }
        boolean[][] visited = new boolean[input.length][input[0].length()];
        int count = 0;
        for (int si = 0; si < map.length; si++) {
            for (int sj = 0; sj < map[0].length; sj++) {
                if (!visited[si][sj]) {
                    visited[si][sj] = true;
                    String cur = map[si][sj];
                    boolean[][] justNowVisited = new boolean[input.length][input[0].length()];
                    justNowVisited[si][sj] = true;
                    int area = 1;
                    int perimeter = 0;
                    ArrayList<Point> neighbours = new ArrayList<>();
                    neighbours.add(new Point(si - 1, sj));
                    neighbours.add(new Point(si + 1, sj));
                    neighbours.add(new Point(si, sj - 1));
                    neighbours.add(new Point(si, sj + 1));
                    while (!neighbours.isEmpty()) {
                        Point neighbour = neighbours.remove(0);
                        if (neighbour.x < 0 || neighbour.x == input.length || neighbour.y < 0 || neighbour.y == input[0].length()) {
                            perimeter++;
                        } else {
                            if (!justNowVisited[neighbour.x][neighbour.y]) {
                                if (!cur.equals(map[neighbour.x][neighbour.y])) {
                                    perimeter++;
                                } else {
                                    area++;
                                    visited[neighbour.x][neighbour.y] = true;
                                    justNowVisited[neighbour.x][neighbour.y] = true;
                                    neighbours.add(new Point(neighbour.x - 1, neighbour.y));
                                    neighbours.add(new Point(neighbour.x + 1, neighbour.y));
                                    neighbours.add(new Point(neighbour.x, neighbour.y - 1));
                                    neighbours.add(new Point(neighbour.x, neighbour.y + 1));
                                }
                            }
                        }
                    }
                    count += area * perimeter;
                }
            }
        }
        System.out.println(count);
    }
}

class Q12Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question12.getInput();
        String[][] map = new String[input.length][];
        for (int i = 0; i < input.length; i++) {
            map[i] = input[i].split("");
        }
        boolean[][] visited = new boolean[input.length][input[0].length()];
        int count = 0;
        for (int si = 0; si < map.length; si++) {
            for (int sj = 0; sj < map[0].length; sj++) {
                if (!visited[si][sj]) {
                    visited[si][sj] = true;
                    String cur = map[si][sj];
                    boolean[][] justNowVisited = new boolean[input.length][input[0].length()];
                    justNowVisited[si][sj] = true;
                    int area = 1;
                    ArrayList<ArrayList<HashSet<Integer>>> sides = new ArrayList<>();
                    for (int h = 0; h < map.length; h++) {
                        ArrayList<HashSet<Integer>> ar = new ArrayList<>();
                        for (int w = 0; w < map[0].length; w++) {
                            ar.add(new HashSet<>());
                        }
                        sides.add(ar);
                    }
                    ArrayList<Pair<Point, Integer>> neighbours = new ArrayList<>();
                    neighbours.add(Pair.of(new Point(si - 1, sj), 0));
                    neighbours.add(Pair.of(new Point(si + 1, sj), 1));
                    neighbours.add(Pair.of(new Point(si, sj - 1), 2));
                    neighbours.add(Pair.of(new Point(si, sj + 1), 3));
                    while (!neighbours.isEmpty()) {
                        Pair<Point, Integer> pair = neighbours.remove(0);
                        Point neighbour = pair.getLeft();
                        if (neighbour.x < 0 || neighbour.x == input.length || neighbour.y < 0 || neighbour.y == input[0].length()) {
                            if (pair.getRight() == 0) {
                                sides.get(neighbour.x + 1).get(neighbour.y).add(0);
                            } else if (pair.getRight() == 1) {
                                sides.get(neighbour.x - 1).get(neighbour.y).add(1);
                            } else if (pair.getRight() == 2) {
                                sides.get(neighbour.x).get(neighbour.y + 1).add(2);
                            } else {
                                sides.get(neighbour.x).get(neighbour.y - 1).add(3);
                            }
                        } else {
                            if (!justNowVisited[neighbour.x][neighbour.y]) {
                                if (!cur.equals(map[neighbour.x][neighbour.y])) {
                                    if (pair.getRight() == 0) {
                                        sides.get(neighbour.x + 1).get(neighbour.y).add(0);
                                    } else if (pair.getRight() == 1) {
                                        sides.get(neighbour.x - 1).get(neighbour.y).add(1);
                                    } else if (pair.getRight() == 2) {
                                        sides.get(neighbour.x).get(neighbour.y + 1).add(2);
                                    } else {
                                        sides.get(neighbour.x).get(neighbour.y - 1).add(3);
                                    }
                                } else {
                                    area++;
                                    visited[neighbour.x][neighbour.y] = true;
                                    justNowVisited[neighbour.x][neighbour.y] = true;
                                    neighbours.add(Pair.of(new Point(neighbour.x - 1, neighbour.y), 0));
                                    neighbours.add(Pair.of(new Point(neighbour.x + 1, neighbour.y), 1));
                                    neighbours.add(Pair.of(new Point(neighbour.x, neighbour.y - 1), 2));
                                    neighbours.add(Pair.of(new Point(neighbour.x, neighbour.y + 1), 3));
                                }
                            }
                        }
                    }
                    int sideCount = 0;
                    boolean onSide1 = false;
                    boolean onSide2 = false;
                    for (int i = 0; i < map.length; i++) {
                        for (int j = 0; j < map[0].length; j++) {
                            if (sides.get(i).get(j).contains(0)) {
                                onSide1 = true;
                                if (j == map[0].length - 1) {
                                    sideCount++;
                                    onSide1 = false;
                                }
                            } else {
                                if (onSide1) {
                                    sideCount++;
                                }
                                onSide1 = false;
                            }
                            if (sides.get(i).get(j).contains(1)) {
                                onSide2 = true;
                                if (j == map[0].length - 1) {
                                    sideCount++;
                                    onSide2 = false;
                                }
                            } else {
                                if (onSide2) {
                                    sideCount++;
                                }
                                onSide2 = false;
                            }
                        }
                    }
                    onSide1 = false;
                    onSide2 = false;
                    for (int j = 0; j < map[0].length; j++) {
                        for (int i = 0; i < map.length; i++) {
                            if (sides.get(i).get(j).contains(2)) {
                                onSide1 = true;
                                if (i == map.length - 1) {
                                    sideCount++;
                                    onSide1 = false;
                                }
                            } else {
                                if (onSide1) {
                                    sideCount++;
                                }
                                onSide1 = false;
                            }
                            if (sides.get(i).get(j).contains(3)) {
                                onSide2 = true;
                                if (i == map.length - 1) {
                                    sideCount++;
                                    onSide2 = false;
                                }
                            } else {
                                if (onSide2) {
                                    sideCount++;
                                }
                                onSide2 = false;
                            }
                        }
                    }
                    count += area * sideCount;
                }
            }
        }
        System.out.println(count);
    }
}
