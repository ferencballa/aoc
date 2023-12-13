package year2023.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;

public class Question13 {
    public static void main(String[] args) throws IOException {
        Q13Part1.run();
        Q13Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 13);
    }
}

class Q13Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question13.getInput();
        int start = 0;
        int end = 0;
        boolean done = false;
        ArrayList<boolean[][]> maps = new ArrayList<>();
        while (!done) {
            if (end < input.length && !input[end].equals("")) {
                end++;
            } else {
                boolean[][] map = new boolean[input[start].length()][];
                int length = end - start;
                for (int x = 0; x < map.length; x++) {
                    map[x] = new boolean[length];
                    for (int y = 0; y < map[x].length; y++) {
                        if (input[start + y].charAt(x) == '#') {
                            map[x][y] = true;
                        } else {
                            map[x][y] = false;
                        }
                    }
                }
                maps.add(map);
                if (end == input.length) {
                    done = true;
                } else {
                    end++;
                    start = end;
                }
            }
        }
        int colsLeft = 0;
        int rowsUp = 0;
        for (int mapIndex = 0; mapIndex < maps.size(); mapIndex++) {
            boolean[][] map = maps.get(mapIndex);
            boolean colMirrored = false;
            for (int col = 1; col < map.length; col++) {
                boolean mirrored = true;
                mapOuter: for (int y = 0; y < map[0].length; y++) {
                    for (int x = 0; x < col; x++) {
                        if (col + x < map.length) {
                            if (map[col - x - 1][y] != map[col + x][y]) {
                                mirrored = false;
                                break mapOuter;
                            }
                        }
                    }
                }
                if (mirrored) {
                    colsLeft += col;
                    colMirrored = true;
                    break;
                }
            }
            boolean rowMirrored = false;
            if (!colMirrored) {
                for (int row = 1; row < map[0].length; row++) {
                    boolean mirrored = true;
                    mapOuter: for (int x = 0; x < map.length; x++) {
                        for (int y = 0; y < row; y++) {
                            if (row + y < map[0].length) {
                                if (map[x][row - y - 1] != map[x][row + y]) {
                                    mirrored = false;
                                    break mapOuter;
                                }
                            }
                        }
                    }
                    if (mirrored) {
                        rowsUp += row;
                        rowMirrored = true;
                        break;
                    }
                }
            }
            if (!colMirrored && !rowMirrored) {
                System.out.println(mapIndex);
            }
        }
        System.out.println(colsLeft + rowsUp * 100);
    }
}

class Q13Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question13.getInput();
        int start = 0;
        int end = 0;
        boolean done = false;
        ArrayList<boolean[][]> maps = new ArrayList<>();
        while (!done) {
            if (end < input.length && !input[end].equals("")) {
                end++;
            } else {
                boolean[][] map = new boolean[input[start].length()][];
                int length = end - start;
                for (int x = 0; x < map.length; x++) {
                    map[x] = new boolean[length];
                    for (int y = 0; y < map[x].length; y++) {
                        if (input[start + y].charAt(x) == '#') {
                            map[x][y] = true;
                        } else {
                            map[x][y] = false;
                        }
                    }
                }
                maps.add(map);
                if (end == input.length) {
                    done = true;
                } else {
                    end++;
                    start = end;
                }
            }
        }
        int colsLeft = 0;
        int rowsUp = 0;
        for (int mapIndex = 0; mapIndex < maps.size(); mapIndex++) {
            boolean[][] map = maps.get(mapIndex);
            boolean colMirrored = false;
            int colMirror = -1;
            for (int col = 1; col < map.length; col++) {
                boolean mirrored = true;
                mapOuter: for (int y = 0; y < map[0].length; y++) {
                    for (int x = 0; x < col; x++) {
                        if (col + x < map.length) {
                            if (map[col - x - 1][y] != map[col + x][y]) {
                                mirrored = false;
                                break mapOuter;
                            }
                        }
                    }
                }
                if (mirrored) {
                    colMirror = col;
                    colMirrored = true;
                    break;
                }
            }
            boolean rowMirrored = false;
            int rowMirror = -1;
            if (!colMirrored) {
                for (int row = 1; row < map[0].length; row++) {
                    boolean mirrored = true;
                    mapOuter: for (int x = 0; x < map.length; x++) {
                        for (int y = 0; y < row; y++) {
                            if (row + y < map[0].length) {
                                if (map[x][row - y - 1] != map[x][row + y]) {
                                    mirrored = false;
                                    break mapOuter;
                                }
                            }
                        }
                    }
                    if (mirrored) {
                        rowMirror = row;
                        rowMirrored = true;
                        break;
                    }
                }
            }
            smudge: for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    boolean[][] newMap = new boolean[map.length][];
                    for (int x = 0; x < map.length; x++) {
                        newMap[x] = new boolean[map[0].length];
                        for (int y = 0; y < map[0].length; y++) {
                            if (i == x && j == y) {
                                newMap[x][y] = !map[x][y];
                            } else {
                                newMap[x][y] = map[x][y];
                            }
                        }
                    }
                    boolean newColMirrored = false;
                    for (int col = 1; col < newMap.length; col++) {
                        boolean mirrored = true;
                        mapOuter: for (int y = 0; y < newMap[0].length; y++) {
                            for (int x = 0; x < col; x++) {
                                if (col + x < newMap.length) {
                                    if (newMap[col - x - 1][y] != newMap[col + x][y]) {
                                        mirrored = false;
                                        break mapOuter;
                                    }
                                }
                            }
                        }
                        if (mirrored && col != colMirror) {
                            colsLeft += col;
                            newColMirrored = true;
                            break smudge;
                        }
                    }
                    boolean newRowMirrored = false;
                    if (!newColMirrored) {
                        for (int row = 1; row < newMap[0].length; row++) {
                            boolean mirrored = true;
                            mapOuter: for (int x = 0; x < newMap.length; x++) {
                                for (int y = 0; y < row; y++) {
                                    if (row + y < newMap[0].length) {
                                        if (newMap[x][row - y - 1] != newMap[x][row + y]) {
                                            mirrored = false;
                                            break mapOuter;
                                        }
                                    }
                                }
                            }
                            if (mirrored && row != rowMirror) {
                                rowsUp += row;
                                newRowMirrored = true;
                                break smudge;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(colsLeft + rowsUp * 100);
    }
}