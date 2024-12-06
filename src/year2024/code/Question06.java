package year2024.code;

import helpers.Helper;

import java.io.IOException;

public class Question06 {
    public static void main(String[] args) throws IOException {
        Q06Part1.run();
        Q06Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 6);
    }
}

class Q06Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question06.getInput();
        //0 = UP
        //1 = RIGHT
        //2 = DOWN
        //3 = LEFT
        int x = 0;
        int y = 0;
        int dir = 0;
        int height = input.length;
        int width = input[0].length();
        boolean[][] visited = new boolean[height][];
        boolean[][] map = new boolean[height][];
        for (int i = 0; i < height; i++) {
            visited[i] = new boolean[width];
            map[i] = new boolean[width];
            for (int j = 0; j < width; j++) {
                visited[i][j] = false;
                if (input[i].charAt(j) == '#') {
                    map[i][j] = false;
                } else {
                    map[i][j] = true;
                    if (input[i].charAt(j) == '^') {
                        x = j;
                        y = i;
                        visited[i][j] = true;
                    }
                }
            }
        }
        while (x != 0 && x != width - 1 && y != 0 && y != height - 1) {
            if (dir == 0) {
                if (map[y-1][x]) {
                    y--;
                } else {
                    dir = 1;
                }
            } else if (dir == 1) {
                if (map[y][x+1]) {
                    x++;
                } else {
                    dir = 2;
                }
            } else if (dir == 2) {
                if (map[y+1][x]) {
                    y++;
                } else {
                    dir = 3;
                }
            } else {
                if (map[y][x-1]) {
                    x--;
                } else {
                    dir = 0;
                }
            }
            visited[y][x] = true;
        }
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (visited[i][j]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}

class Q06Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question06.getInput();
        //0 = UP
        //1 = RIGHT
        //2 = DOWN
        //3 = LEFT
        int startX = 0;
        int startY = 0;
        int startDir = 0;
        int height = input.length;
        int width = input[0].length();
        boolean[][] originalPath = new boolean[height][];
        boolean[][] map = new boolean[height][];
        for (int i = 0; i < height; i++) {
            originalPath[i] = new boolean[width];
            map[i] = new boolean[width];
            for (int j = 0; j < width; j++) {
                originalPath[i][j] = false;
                if (input[i].charAt(j) == '#') {
                    map[i][j] = false;
                } else {
                    map[i][j] = true;
                    if (input[i].charAt(j) == '^') {
                        startX = j;
                        startY = i;
                        originalPath[i][j] = true;
                    }
                }
            }
        }
        int sx = startX;
        int sy = startY;
        int sdir = startDir;
        while (sx != 0 && sx != width - 1 && sy != 0 && sy != height - 1) {
            if (sdir == 0) {
                if (map[sy-1][sx]) {
                    sy--;
                } else {
                    sdir = 1;
                }
            } else if (sdir == 1) {
                if (map[sy][sx+1]) {
                    sx++;
                } else {
                    sdir = 2;
                }
            } else if (sdir == 2) {
                if (map[sy+1][sx]) {
                    sy++;
                } else {
                    sdir = 3;
                }
            } else {
                if (map[sy][sx-1]) {
                    sx--;
                } else {
                    sdir = 0;
                }
            }
            originalPath[sy][sx] = true;
        }
        originalPath[startY][startX] = false;
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (originalPath[i][j]) {
                    map[i][j] = false;
                    int x = startX;
                    int y = startY;
                    int dir = startDir;
                    boolean[][][] visited = new boolean[height][width][4];
                    walk: while (x != 0 && x != width - 1 && y != 0 && y != height - 1) {
                        if (dir == 0) {
                            if (map[y-1][x]) {
                                y--;
                            } else {
                                dir = 1;
                            }
                        } else if (dir == 1) {
                            if (map[y][x+1]) {
                                x++;
                            } else {
                                dir = 2;
                            }
                        } else if (dir == 2) {
                            if (map[y+1][x]) {
                                y++;
                            } else {
                                dir = 3;
                            }
                        } else {
                            if (map[y][x-1]) {
                                x--;
                            } else {
                                dir = 0;
                            }
                        }
                        if (visited[y][x][dir]) {
                            count ++;
                            break walk;
                        } else {
                            visited[y][x][dir] = true;
                        }
                    }
                    map[i][j] = true;
                }
            }
        }
        System.out.println(count);
    }
}
