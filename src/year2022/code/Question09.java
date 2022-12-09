package year2022.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Question09 {
    public static void main(String[] args) throws IOException {
        Q09Part1.run();
        runForLength(2);
        Q09Part2.run();
        runForLength(10);
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 9);
    }

    private static void runForLength(int l) throws IOException {
        String[] input = getInput();
        Point[] knots = new Point[l];
        for (int i = 0; i < l; i++)
            knots[i] = new Point(0, 0);
        HashSet<Point> set = new HashSet<>();
        set.add(new Point(0, 0));
        for (String s : input)
            for (int r = 0; r < Integer.parseInt(s.split(" ")[1]); r++) {
                switch (s.split(" ")[0]) {
                    case "U": knots[0].y--; break;
                    case "D": knots[0].y++; break;
                    case "L": knots[0].x--; break;
                    case "R": knots[0].x++; break;
                }
                for (int k = 0; k < l-1; k++)
                    if (knots[k].y == knots[k + 1].y + 2) {
                        knots[k + 1].y++;
                        knots[k + 1].x += Integer.compare(knots[k].x, knots[k + 1].x);
                    } else if (knots[k].y == knots[k + 1].y - 2) {
                        knots[k + 1].y--;
                        knots[k + 1].x += Integer.compare(knots[k].x, knots[k + 1].x);
                    } else if (knots[k].x == knots[k + 1].x + 2) {
                        knots[k + 1].x++;
                        knots[k + 1].y += Integer.compare(knots[k].y, knots[k + 1].y);
                    } else if (knots[k].x == knots[k + 1].x - 2) {
                        knots[k + 1].x--;
                        knots[k + 1].y += Integer.compare(knots[k].y, knots[k + 1].y);
                    }
                set.add(new Point(knots[l - 1].x, knots[l - 1].y));
            }
        System.out.println(set.size());
    }
}

class Q09Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question09.getInput();
        int hX = 0;
        int hY = 0;
        int tX = 0;
        int tY = 0;
        HashMap<Integer, HashMap<Integer, Boolean>> map = new HashMap<>();
        HashMap<Integer, Boolean> initRow = new HashMap<>();
        initRow.put(0, true);
        map.put(0, initRow);
        for (int i = 0; i < input.length; i++) {
            String[] command = input[i].split(" ");
            int repetition = Integer.parseInt(command[1]);
            String direction = command[0];
            for (int r = 0; r < repetition; r++) {
                if (direction.equals("U")) {
                    hY--;
                } else if (direction.equals("D")) {
                    hY++;
                } else if (direction.equals("L")) {
                    hX--;
                } else if (direction.equals("R")) {
                    hX++;
                }
                if (hX == tX && hY == tY + 2) {
                    tY++;
                } else if (hX == tX && hY == tY - 2) {
                    tY--;
                } else if (hY == tY && hX == tX + 2) {
                    tX++;
                } else if (hY == tY && hX == tX - 2) {
                    tX--;
                } else if ((hX == tX + 1 && hY == tY + 2)||(hX == tX + 2 && hY == tY + 1)) {
                    tX++;
                    tY++;
                } else if ((hX == tX - 1 && hY == tY - 2)||(hX == tX - 2 && hY == tY - 1)) {
                    tX--;
                    tY--;
                } else if ((hX == tX + 1 && hY == tY - 2)||(hX == tX + 2 && hY == tY - 1)) {
                    tX++;
                    tY--;
                } else if ((hX == tX - 1 && hY == tY + 2)||(hX == tX - 2 && hY == tY + 1)) {
                    tX --;
                    tY++;
                }
                HashMap<Integer, Boolean> row = map.get(tX);
                if (row == null) {
                    map.put(tX, new HashMap<>());
                    row = map.get(tX);
                }
                row.put(tY, true);
            }
        }
        int visited = 0;
        for (Integer rowKey : map.keySet()) {
            HashMap<Integer, Boolean> row = map.get(rowKey);
            visited += row.size();
        }
        System.out.println(visited);
    }
}

class Q09Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question09.getInput();
        Point[] knots = new Point[10];
        for (int i = 0; i < 10; i++) {
            knots[i] = new Point(0, 0);
        }
        HashMap<Integer, HashMap<Integer, Boolean>> map = new HashMap<>();
        HashMap<Integer, Boolean> initRow = new HashMap<>();
        initRow.put(0, true);
        map.put(0, initRow);
        for (int i = 0; i < input.length; i++) {
            String[] command = input[i].split(" ");
            int repetition = Integer.parseInt(command[1]);
            String direction = command[0];
            for (int r = 0; r < repetition; r++) {
                if (direction.equals("U")) {
                    knots[0].y--;
                } else if (direction.equals("D")) {
                    knots[0].y++;
                } else if (direction.equals("L")) {
                    knots[0].x--;
                } else if (direction.equals("R")) {
                    knots[0].x++;
                }
                for (int k = 0; k < 9; k++) {
                    int curX = knots[k].x;
                    int curY = knots[k].y;
                    int nextX = knots[k+1].x;
                    int nextY = knots[k+1].y;
                    if (curX == nextX && curY == nextY + 2) {
                        nextY++;
                    } else if (curX == nextX && curY == nextY - 2) {
                        nextY--;
                    } else if (curY == nextY && curX == nextX + 2) {
                        nextX++;
                    } else if (curY == nextY && curX == nextX - 2) {
                        nextX--;
                    } else if ((curX == nextX + 1 && curY == nextY + 2) || (curX == nextX + 2 && curY == nextY + 1) || (curX == nextX + 2 && curY == nextY + 2)) {
                        nextX++;
                        nextY++;
                    } else if ((curX == nextX - 1 && curY == nextY - 2) || (curX == nextX - 2 && curY == nextY - 1) || (curX == nextX - 2 && curY == nextY - 2)) {
                        nextX--;
                        nextY--;
                    } else if ((curX == nextX + 1 && curY == nextY - 2) || (curX == nextX + 2 && curY == nextY - 1) || (curX == nextX + 2 && curY == nextY - 2)) {
                        nextX++;
                        nextY--;
                    } else if ((curX == nextX - 1 && curY == nextY + 2) || (curX == nextX - 2 && curY == nextY + 1) || (curX == nextX - 2 && curY == nextY + 2)) {
                        nextX--;
                        nextY++;
                    }
                    knots[k+1].x = nextX;
                    knots[k+1].y = nextY;
                    if (k == 8) {
                        HashMap<Integer, Boolean> row = map.get(nextX);
                        if (row == null) {
                            map.put(nextX, new HashMap<>());
                            row = map.get(nextX);
                        }
                        row.put(nextY, true);
                    }
                }
            }
        }
        int visited = 0;
        for (Integer rowKey : map.keySet()) {
            HashMap<Integer, Boolean> row = map.get(rowKey);
            visited += row.size();
        }
        System.out.println(visited);
    }
}
