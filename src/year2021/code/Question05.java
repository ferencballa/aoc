package year2021.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;

public class Question05 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2021, 5);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        ArrayList<Line> lines = new ArrayList<>();
        int maxX = 0;
        int maxY = 0;
        for (String inp : input) {
            Line line = new Line(inp);
            if (line.x1 == line.x2 || line.y1 == line.y2) {
                lines.add(line);
                if (line.x1 + 1 > maxX) {
                    maxX = line.x1 + 1;
                }
                if (line.x2 + 1> maxX) {
                    maxX = line.x2 + 1;
                }
                if (line.y1 + 1> maxY) {
                    maxY = line.y1 + 1;
                }
                if (line.y2 + 1> maxY) {
                    maxY = line.y2 + 1;
                }
            }
        }
        int[][] sea = new int[maxX][maxY];
        for (Line line : lines) {
            if (line.x1 == line.x2) {
                for (int i = line.y1; i <= line.y2; i++) {
                    sea[line.x1][i]++;
                }
            } else {
                for (int i = line.x1; i <= line.x2; i++) {
                    sea[i][line.y1]++;
                }
            }
        }
        int overlapCount = 0;
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j <maxY; j++) {
                if (sea[i][j] > 1) {
                    overlapCount++;
                }
            }
        }
        System.out.println(overlapCount);
    }

    private static void part2(String[] input) {

        ArrayList<Line> lines = new ArrayList<>();
        int maxX = 0;
        int maxY = 0;
        for (String inp : input) {
            Line line = new Line(inp);
            lines.add(line);
            if (line.x1 + 1 > maxX) {
                maxX = line.x1 + 1;
            }
            if (line.x2 + 1 > maxX) {
                maxX = line.x2 + 1;
            }
            if (line.y1 + 1 > maxY) {
                maxY = line.y1 + 1;
            }
            if (line.y2 + 1 > maxY) {
                maxY = line.y2 + 1;
            }
        }
        int[][] sea = new int[maxX][maxY];
        for (Line line : lines) {
            if (line.x1 == line.x2) {
                for (int i = line.y1; i <= line.y2; i++) {
                    sea[line.x1][i]++;
                }
            } else if (line.y1 == line.y2){
                for (int i = line.x1; i <= line.x2; i++) {
                    sea[i][line.y1]++;
                }
            } else {
                for (int i = 0; i <= Math.abs(line.x2 - line.x1); i++) {
                    if (line.x1 < line.x2 && line.y1 < line.y2) {
                        sea[line.x1 + i][line.y1 + i]++;
                    } else if (line.x1 > line.x2 && line.y1 > line.y2) {
                        sea[line.x1 - i][line.y1 - i]++;
                    } else if (line.x1 < line.x2) {
                        sea[line.x1 + i][line.y1 - i]++;
                    } else {
                        sea[line.x1 - i][line.y1 + i]++;
                    }
                }
            }
        }
        int overlapCount = 0;
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j <maxY; j++) {
                if (sea[i][j] > 1) {
                    overlapCount++;
                }
            }
        }
        System.out.println(overlapCount);
    }
}

class Line {
    int x1;
    int y1;
    int x2;
    int y2;

    public Line(String s) {
        String[] points = s.split(" -> ");
        String[] point1 = points[0].split(",");
        String[] point2 = points[1].split(",");
        x1 = Integer.parseInt(point1[0]);
        y1 = Integer.parseInt(point1[1]);
        x2 = Integer.parseInt(point2[0]);
        y2 = Integer.parseInt(point2[1]);
        normalise();
    }

    public void normalise() {
        if (x1 == x2 || y1 == y2) {
            if (x1 > x2) {
                int temp = x1;
                x1 = x2;
                x2 = temp;
            }
            if (y1 > y2) {
                int temp = y1;
                y1 = y2;
                y2 = temp;
            }
        }
    }
}
