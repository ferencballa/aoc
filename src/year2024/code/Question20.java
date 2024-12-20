package year2024.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;

public class Question20 {
    public static void main(String[] args) throws IOException {
        Q20Part1.run();
        Q20Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 20);
    }
}

class Q20Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question20.getInput();
        Point start = new Point();
        Point end = new Point();
        int height = input.length;
        int width = input[0].length();
        int[][] map = new int[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch(input[i].charAt(j)) {
                    case '#':
                        map[j][i] = -1;
                        break;
                    case 'S':
                        start = new Point(j, i);
                        break;
                    case 'E':
                        end = new Point(j, i);
                }
            }
        }
        Point curPos = new Point(start.x, start.y);
        int steps = 1;
        while(true) {
            searchCoors: for (int dx = -1; dx <=1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if ((dx == 0 && dy != 0) || (dx != 0 && dy == 0)) {
                        if (map[curPos.x + dx][curPos.y + dy] == 0 && !(curPos.x + dx == start.x && curPos.y + dy == start.y)) {
                            curPos.x += dx;
                            curPos.y += dy;
                            map[curPos.x][curPos.y] = steps;
                            steps++;
                            break searchCoors;
                        }
                    }
                }
            }
            if (curPos.x == end.x && curPos.y == end.y) {
                break;
            }
        }
        int count = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < width; y++) {
                if (map[x][y] >= 0) {
                    for (int dx = -1; dx <=1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            if ((dx == 0 && dy != 0) || (dx != 0 && dy == 0)) {
                                int nx = x + dx * 2;
                                int ny = y + dy * 2;
                                if (nx >= 0 && nx < width && ny >= 0 && ny < height && map[nx][ny] != -1) {
                                    if (map[nx][ny] - map[x][y] >= 102) {
                                        count++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}

class Q20Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question20.getInput();
        Point start = new Point();
        Point end = new Point();
        int height = input.length;
        int width = input[0].length();
        int[][] map = new int[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch(input[i].charAt(j)) {
                    case '#':
                        map[j][i] = -1;
                        break;
                    case 'S':
                        start = new Point(j, i);
                        break;
                    case 'E':
                        end = new Point(j, i);
                }
            }
        }
        Point curPos = new Point(start.x, start.y);
        int steps = 1;
        while(true) {
            searchCoors: for (int dx = -1; dx <=1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if ((dx == 0 && dy != 0) || (dx != 0 && dy == 0)) {
                        if (map[curPos.x + dx][curPos.y + dy] == 0 && !(curPos.x + dx == start.x && curPos.y + dy == start.y)) {
                            curPos.x += dx;
                            curPos.y += dy;
                            map[curPos.x][curPos.y] = steps;
                            steps++;
                            break searchCoors;
                        }
                    }
                }
            }
            if (curPos.x == end.x && curPos.y == end.y) {
                break;
            }
        }
        int count = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < width; y++) {
                if (map[x][y] >= 0) {
                    for(int dx = -20; dx <= 20; dx++) {
                        for (int dy = -20; dy <= 20; dy++) {
                            if (Math.abs(dx) + Math.abs(dy) <= 20) {
                                int nx = x + dx;
                                int ny = y + dy;
                                if (nx >= 0 && nx < width && ny >= 0 && ny < height && map[nx][ny] != -1) {
                                    if (map[nx][ny] - map[x][y] >= 100 + Math.abs(dx) + Math.abs(dy)) {
                                        count++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}
