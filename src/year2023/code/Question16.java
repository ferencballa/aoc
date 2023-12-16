package year2023.code;

import helpers.Helper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Question16 {
    public static void main(String[] args) throws IOException {
        Q16Part1.run();
        Q16Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 16);
    }
}

class Q16Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question16.getInput();
        char[][] map = new char[input[0].length()][];
        for (int i = 0; i < map.length; i++) {
            map[i] = new char[input.length];
        }
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[0].length(); x++) {
                map[x][y] = input[y].charAt(x);
            }
        }
        /*
        dirs
        0 = up
        1 = right
        2 = down
        3 = left
         */
        boolean[][][] incomingDirOnMapReached = new boolean[map.length][][];
        for (int i = 0; i < map.length; i++) {
            boolean[][] col = new boolean[map[0].length][];
            for (int j = 0; j < map[0].length; j++) {
                col[j] = new boolean[4];
            }
            incomingDirOnMapReached[i] = col;
        }
        ArrayList<Pair<Point, Integer>> rays = new ArrayList<>();
        rays.add(new ImmutablePair<>(new Point(-1, 0), 1));
        while (!rays.isEmpty()) {
            Pair<Point, Integer> ray = rays.remove(0);
            int x = ray.getLeft().x;
            int y = ray.getLeft().y;
            int dir = ray.getRight();
            if (dir == 0) {
                if (y > 0 && !incomingDirOnMapReached[x][y-1][0]) {
                    incomingDirOnMapReached[x][y-1][0] = true;
                    char mapPoint = map[x][y-1];
                    if (mapPoint == '.') {
                        rays.add(new ImmutablePair<>(new Point(x, y-1), 0));
                    } else if (mapPoint == '|') {
                        rays.add(new ImmutablePair<>(new Point(x, y-1), 0));
                    } else if (mapPoint == '/') {
                        rays.add(new ImmutablePair<>(new Point(x, y-1), 1));
                    } else if (mapPoint == '\\') {
                        rays.add(new ImmutablePair<>(new Point(x, y-1), 3));
                    } else if (mapPoint == '-') {
                        rays.add(new ImmutablePair<>(new Point(x, y-1), 1));
                        rays.add(new ImmutablePair<>(new Point(x, y-1), 3));
                    }
                }
            } else if (dir == 1) {
                if (x < map.length - 1 && !incomingDirOnMapReached[x+1][y][1]) {
                    incomingDirOnMapReached[x+1][y][1] = true;
                    char mapPoint = map[x+1][y];
                    if (mapPoint == '.') {
                        rays.add(new ImmutablePair<>(new Point(x+1, y), 1));
                    } else if (mapPoint == '-') {
                        rays.add(new ImmutablePair<>(new Point(x+1, y), 1));
                    } else if (mapPoint == '/') {
                        rays.add(new ImmutablePair<>(new Point(x+1, y), 0));
                    } else if (mapPoint == '\\') {
                        rays.add(new ImmutablePair<>(new Point(x+1, y), 2));
                    } else if (mapPoint == '|') {
                        rays.add(new ImmutablePair<>(new Point(x+1, y), 2));
                        rays.add(new ImmutablePair<>(new Point(x+1, y), 0));
                    }
                }
            } else if (dir == 2) {
                if (y < map[0].length - 1 && !incomingDirOnMapReached[x][y+1][2]) {
                    incomingDirOnMapReached[x][y+1][2] = true;
                    char mapPoint = map[x][y+1];
                    if (mapPoint == '.') {
                        rays.add(new ImmutablePair<>(new Point(x, y+1), 2));
                    } else if (mapPoint == '|') {
                        rays.add(new ImmutablePair<>(new Point(x, y+1), 2));
                    } else if (mapPoint == '/') {
                        rays.add(new ImmutablePair<>(new Point(x, y+1), 3));
                    } else if (mapPoint == '\\') {
                        rays.add(new ImmutablePair<>(new Point(x, y+1), 1));
                    } else if (mapPoint == '-') {
                        rays.add(new ImmutablePair<>(new Point(x, y+1), 1));
                        rays.add(new ImmutablePair<>(new Point(x, y+1), 3));
                    }
                }
            } else if (dir == 3) {
                if (x > 0 && !incomingDirOnMapReached[x-1][y][3]) {
                    incomingDirOnMapReached[x-1][y][3] = true;
                    char mapPoint = map[x-1][y];
                    if (mapPoint == '.') {
                        rays.add(new ImmutablePair<>(new Point(x-1, y), 3));
                    } else if (mapPoint == '-') {
                        rays.add(new ImmutablePair<>(new Point(x-1, y), 3));
                    } else if (mapPoint == '/') {
                        rays.add(new ImmutablePair<>(new Point(x-1, y), 2));
                    } else if (mapPoint == '\\') {
                        rays.add(new ImmutablePair<>(new Point(x-1, y), 0));
                    } else if (mapPoint == '|') {
                        rays.add(new ImmutablePair<>(new Point(x-1, y), 2));
                        rays.add(new ImmutablePair<>(new Point(x-1, y), 0));
                    }
                }
            }
        }
        int count = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                for (int d = 0; d < 4; d++) {
                    if (incomingDirOnMapReached[x][y][d]) {
                        count++;
                        break;
                    }
                }
            }
        }
        System.out.println(count);
    }
}

class Q16Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question16.getInput();
        char[][] map = new char[input[0].length()][];
        for (int i = 0; i < map.length; i++) {
            map[i] = new char[input.length];
        }
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[0].length(); x++) {
                map[x][y] = input[y].charAt(x);
            }
        }
        /*
        dirs
        0 = up
        1 = right
        2 = down
        3 = left
         */
        ArrayList<Pair<Point, Integer>> startRays = new ArrayList<>();
        for (int x = 0; x < map.length; x++) {
            startRays.add(new ImmutablePair<>(new Point(x, -1), 2));
            startRays.add(new ImmutablePair<>(new Point(x, map[0].length), 0));
        }
        for (int y = 0; y < map[0].length; y++) {
            startRays.add(new ImmutablePair<>(new Point(-1, y), 1));
            startRays.add(new ImmutablePair<>(new Point(map.length, y), 3));
        }
        int maxCount = 0;
        for (Pair<Point, Integer> startRay : startRays) {
            boolean[][][] incomingDirOnMapReached = new boolean[map.length][][];
            for (int i = 0; i < map.length; i++) {
                boolean[][] col = new boolean[map[0].length][];
                for (int j = 0; j < map[0].length; j++) {
                    col[j] = new boolean[4];
                }
                incomingDirOnMapReached[i] = col;
            }
            ArrayList<Pair<Point, Integer>> rays = new ArrayList<>();
            rays.add(startRay);
            while (!rays.isEmpty()) {
                Pair<Point, Integer> ray = rays.remove(0);
                int x = ray.getLeft().x;
                int y = ray.getLeft().y;
                int dir = ray.getRight();
                if (dir == 0) {
                    if (y > 0 && !incomingDirOnMapReached[x][y - 1][0]) {
                        incomingDirOnMapReached[x][y - 1][0] = true;
                        char mapPoint = map[x][y - 1];
                        if (mapPoint == '.') {
                            rays.add(new ImmutablePair<>(new Point(x, y - 1), 0));
                        } else if (mapPoint == '|') {
                            rays.add(new ImmutablePair<>(new Point(x, y - 1), 0));
                        } else if (mapPoint == '/') {
                            rays.add(new ImmutablePair<>(new Point(x, y - 1), 1));
                        } else if (mapPoint == '\\') {
                            rays.add(new ImmutablePair<>(new Point(x, y - 1), 3));
                        } else if (mapPoint == '-') {
                            rays.add(new ImmutablePair<>(new Point(x, y - 1), 1));
                            rays.add(new ImmutablePair<>(new Point(x, y - 1), 3));
                        }
                    }
                } else if (dir == 1) {
                    if (x < map.length - 1 && !incomingDirOnMapReached[x + 1][y][1]) {
                        incomingDirOnMapReached[x + 1][y][1] = true;
                        char mapPoint = map[x + 1][y];
                        if (mapPoint == '.') {
                            rays.add(new ImmutablePair<>(new Point(x + 1, y), 1));
                        } else if (mapPoint == '-') {
                            rays.add(new ImmutablePair<>(new Point(x + 1, y), 1));
                        } else if (mapPoint == '/') {
                            rays.add(new ImmutablePair<>(new Point(x + 1, y), 0));
                        } else if (mapPoint == '\\') {
                            rays.add(new ImmutablePair<>(new Point(x + 1, y), 2));
                        } else if (mapPoint == '|') {
                            rays.add(new ImmutablePair<>(new Point(x + 1, y), 2));
                            rays.add(new ImmutablePair<>(new Point(x + 1, y), 0));
                        }
                    }
                } else if (dir == 2) {
                    if (y < map[0].length - 1 && !incomingDirOnMapReached[x][y + 1][2]) {
                        incomingDirOnMapReached[x][y + 1][2] = true;
                        char mapPoint = map[x][y + 1];
                        if (mapPoint == '.') {
                            rays.add(new ImmutablePair<>(new Point(x, y + 1), 2));
                        } else if (mapPoint == '|') {
                            rays.add(new ImmutablePair<>(new Point(x, y + 1), 2));
                        } else if (mapPoint == '/') {
                            rays.add(new ImmutablePair<>(new Point(x, y + 1), 3));
                        } else if (mapPoint == '\\') {
                            rays.add(new ImmutablePair<>(new Point(x, y + 1), 1));
                        } else if (mapPoint == '-') {
                            rays.add(new ImmutablePair<>(new Point(x, y + 1), 1));
                            rays.add(new ImmutablePair<>(new Point(x, y + 1), 3));
                        }
                    }
                } else if (dir == 3) {
                    if (x > 0 && !incomingDirOnMapReached[x - 1][y][3]) {
                        incomingDirOnMapReached[x - 1][y][3] = true;
                        char mapPoint = map[x - 1][y];
                        if (mapPoint == '.') {
                            rays.add(new ImmutablePair<>(new Point(x - 1, y), 3));
                        } else if (mapPoint == '-') {
                            rays.add(new ImmutablePair<>(new Point(x - 1, y), 3));
                        } else if (mapPoint == '/') {
                            rays.add(new ImmutablePair<>(new Point(x - 1, y), 2));
                        } else if (mapPoint == '\\') {
                            rays.add(new ImmutablePair<>(new Point(x - 1, y), 0));
                        } else if (mapPoint == '|') {
                            rays.add(new ImmutablePair<>(new Point(x - 1, y), 2));
                            rays.add(new ImmutablePair<>(new Point(x - 1, y), 0));
                        }
                    }
                }
            }
            int count = 0;
            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[0].length; y++) {
                    for (int d = 0; d < 4; d++) {
                        if (incomingDirOnMapReached[x][y][d]) {
                            count++;
                            break;
                        }
                    }
                }
            }
            maxCount = Math.max(count, maxCount);
        }
        System.out.println(maxCount);
    }
}
