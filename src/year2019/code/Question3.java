package year2019.code;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Question3 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2019/input/Question3.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        String[] firstWireDirs = input[0].split(",");
        String[] secondWireDirs = input[1].split(",");
        Point location1 = new Point(0, 0);
        int biggestX1 = 0;
        int smallestX1 = 0;
        int biggestY1 = 0;
        int smallestY1 = 0;
        for (String dir : firstWireDirs) {
            int delta = Integer.parseInt(dir.substring(1));
            if (dir.charAt(0) == 'U') {
                location1 = new Point(location1.x, location1.y + delta);
                if (location1.y > biggestY1) {
                    biggestY1 = location1.y;
                }
            } else if (dir.charAt(0) == 'D') {
                location1 = new Point(location1.x, location1.y - delta);
                if (location1.y < smallestY1) {
                    smallestY1 = location1.y;
                }
            } else if (dir.charAt(0) == 'R') {
                location1 = new Point(location1.x + delta, location1.y);
                if (location1.x > biggestX1) {
                    biggestX1 = location1.x;
                }
            } else if (dir.charAt(0) == 'L') {
                location1 = new Point(location1.x - delta, location1.y);
                if (location1.x < smallestX1) {
                    smallestX1 = location1.x;
                }
            }
        }
        Point location2 = new Point(0, 0);
        int biggestX2 = 0;
        int smallestX2 = 0;
        int biggestY2 = 0;
        int smallestY2 = 0;
        for (String dir : secondWireDirs) {
            int delta = Integer.parseInt(dir.substring(1));
            if (dir.charAt(0) == 'U') {
                location2 = new Point(location2.x, location2.y + delta);
                if (location2.y > biggestY2) {
                    biggestY2 = location2.y;
                }
            } else if (dir.charAt(0) == 'D') {
                location2 = new Point(location2.x, location2.y - delta);
                if (location2.y < smallestY2) {
                    smallestY2 = location2.y;
                }
            } else if (dir.charAt(0) == 'R') {
                location2 = new Point(location2.x + delta, location2.y);
                if (location2.x > biggestX2) {
                    biggestX2 = location2.x;
                }
            } else if (dir.charAt(0) == 'L') {
                location2 = new Point(location2.x - delta, location2.y);
                if (location2.x < smallestX2) {
                    smallestX2 = location2.x;
                }
            }
        }
        int negDeltaX = Math.min(smallestX1, smallestX2);
        int posDeltaX = Math.max(biggestX1, biggestX2);
        int negDeltaY = Math.min(smallestY1, smallestY2);
        int posDeltaY = Math.max(biggestY1, biggestY2);
        int startX = Math.abs(negDeltaX);
        int startY = Math.abs(negDeltaY);
        boolean[][] board1 = new boolean[posDeltaX + 1 - negDeltaX][posDeltaY + 1 - negDeltaY];
        location1 = new Point(startX, startY);
        for (String dir : firstWireDirs) {
            int delta = Integer.parseInt(dir.substring(1));
            if (dir.charAt(0) == 'U') {
                for (int i = 1; i <= delta; i++) {
                    board1[location1.x][location1.y + i] = true;
                }
                location1 = new Point(location1.x, location1.y + delta);
            } else if (dir.charAt(0) == 'D') {
                for (int i = 1; i <= delta; i++) {
                    board1[location1.x][location1.y - i] = true;
                }
                location1 = new Point(location1.x, location1.y - delta);
            } else if (dir.charAt(0) == 'R') {
                for (int i = 1; i <= delta; i++) {
                    board1[location1.x + i][location1.y] = true;
                }
                location1 = new Point(location1.x + delta, location1.y);
            } else if (dir.charAt(0) == 'L') {
                for (int i = 1; i <= delta; i++) {
                    board1[location1.x - i][location1.y] = true;
                }
                location1 = new Point(location1.x - delta, location1.y);
            }
        }
        boolean[][] board2 = new boolean[posDeltaX + 1 - negDeltaX][posDeltaY + 1 - negDeltaY];
        location2 = new Point(startX, startY);
        for (String dir : secondWireDirs) {
            int delta = Integer.parseInt(dir.substring(1));
            if (dir.charAt(0) == 'U') {
                for (int i = 1; i <= delta; i++) {
                    board2[location2.x][location2.y + i] = true;
                }
                location2 = new Point(location2.x, location2.y + delta);
            } else if (dir.charAt(0) == 'D') {
                for (int i = 1; i <= delta; i++) {
                    board2[location2.x][location2.y - i] = true;
                }
                location2 = new Point(location2.x, location2.y - delta);
            } else if (dir.charAt(0) == 'R') {
                for (int i = 1; i <= delta; i++) {
                    board2[location2.x + i][location2.y] = true;
                }
                location2 = new Point(location2.x + delta, location2.y);
            } else if (dir.charAt(0) == 'L') {
                for (int i = 1; i <= delta; i++) {
                    board2[location2.x - i][location2.y] = true;
                }
                location2 = new Point(location2.x - delta, location2.y);
            }
        }
        int dist = 1;
        distanceLoop:
        while (dist < Integer.MAX_VALUE) {
            for (int x = 0; x <= dist; x++) {
                for (int y = 0; y <= dist - x; y++) {
                    if (
                            (startX + x < board1.length && startY + y < board1[0].length && board1[startX + x][startY + y] && board2[startX + x][startY + y]) ||
                            (startX + x < board1.length && startY - y > 0 && board1[startX + x][startY - y] && board2[startX + x][startY - y]) ||
                            (startX - x > 0 && startY + y < board1[0].length && board1[startX - x][startY + y] && board2[startX - x][startY + y]) ||
                            (startX - x > 0 && startY - y > 0 && board1[startX - x][startY - y] && board2[startX - x][startY - y])
                    ) {
                        break distanceLoop;
                    }
                }
            }
            dist++;
        }
        System.out.println(dist);
    }

    private static void part2(String[] input) {
        String[] firstWireDirs = input[0].split(",");
        String[] secondWireDirs = input[1].split(",");
        Point location1 = new Point(0, 0);
        int biggestX1 = 0;
        int smallestX1 = 0;
        int biggestY1 = 0;
        int smallestY1 = 0;
        for (String dir : firstWireDirs) {
            int delta = Integer.parseInt(dir.substring(1));
            if (dir.charAt(0) == 'U') {
                location1 = new Point(location1.x, location1.y + delta);
                if (location1.y > biggestY1) {
                    biggestY1 = location1.y;
                }
            } else if (dir.charAt(0) == 'D') {
                location1 = new Point(location1.x, location1.y - delta);
                if (location1.y < smallestY1) {
                    smallestY1 = location1.y;
                }
            } else if (dir.charAt(0) == 'R') {
                location1 = new Point(location1.x + delta, location1.y);
                if (location1.x > biggestX1) {
                    biggestX1 = location1.x;
                }
            } else if (dir.charAt(0) == 'L') {
                location1 = new Point(location1.x - delta, location1.y);
                if (location1.x < smallestX1) {
                    smallestX1 = location1.x;
                }
            }
        }
        Point location2 = new Point(0, 0);
        int biggestX2 = 0;
        int smallestX2 = 0;
        int biggestY2 = 0;
        int smallestY2 = 0;
        for (String dir : secondWireDirs) {
            int delta = Integer.parseInt(dir.substring(1));
            if (dir.charAt(0) == 'U') {
                location2 = new Point(location2.x, location2.y + delta);
                if (location2.y > biggestY2) {
                    biggestY2 = location2.y;
                }
            } else if (dir.charAt(0) == 'D') {
                location2 = new Point(location2.x, location2.y - delta);
                if (location2.y < smallestY2) {
                    smallestY2 = location2.y;
                }
            } else if (dir.charAt(0) == 'R') {
                location2 = new Point(location2.x + delta, location2.y);
                if (location2.x > biggestX2) {
                    biggestX2 = location2.x;
                }
            } else if (dir.charAt(0) == 'L') {
                location2 = new Point(location2.x - delta, location2.y);
                if (location2.x < smallestX2) {
                    smallestX2 = location2.x;
                }
            }
        }
        int negDeltaX = Math.min(smallestX1, smallestX2);
        int posDeltaX = Math.max(biggestX1, biggestX2);
        int negDeltaY = Math.min(smallestY1, smallestY2);
        int posDeltaY = Math.max(biggestY1, biggestY2);
        int startX = Math.abs(negDeltaX);
        int startY = Math.abs(negDeltaY);
        int[][] board1 = new int[posDeltaX + 1 - negDeltaX][posDeltaY + 1 - negDeltaY];
        location1 = new Point(startX, startY);
        int dist1 = 0;
        for (String dir : firstWireDirs) {
            int delta = Integer.parseInt(dir.substring(1));
            if (dir.charAt(0) == 'U') {
                for (int i = 1; i <= delta; i++) {
                    if (board1[location1.x][location1.y + i] == 0) {
                        board1[location1.x][location1.y + i] = dist1 + i;
                    }
                }
                location1 = new Point(location1.x, location1.y + delta);
            } else if (dir.charAt(0) == 'D') {
                for (int i = 1; i <= delta; i++) {
                    if (board1[location1.x][location1.y - i] == 0) {
                        board1[location1.x][location1.y - i] = dist1 + i;
                    }
                }
                location1 = new Point(location1.x, location1.y - delta);
            } else if (dir.charAt(0) == 'R') {
                for (int i = 1; i <= delta; i++) {
                    if (board1[location1.x + i][location1.y] == 0) {
                        board1[location1.x + i][location1.y] = dist1 + i;
                    }
                }
                location1 = new Point(location1.x + delta, location1.y);
            } else if (dir.charAt(0) == 'L') {
                for (int i = 1; i <= delta; i++) {
                    if (board1[location1.x - i][location1.y] == 0) {
                        board1[location1.x - i][location1.y] = dist1 + i;
                    }
                }
                location1 = new Point(location1.x - delta, location1.y);
            }
            dist1 += delta;
        }
        int[][] board2 = new int[posDeltaX + 1 - negDeltaX][posDeltaY + 1 - negDeltaY];
        location2 = new Point(startX, startY);
        int dist2 = 0;
        for (String dir : secondWireDirs) {
            int delta = Integer.parseInt(dir.substring(1));
            if (dir.charAt(0) == 'U') {
                for (int i = 1; i <= delta; i++) {
                    if (board2[location2.x][location2.y + i] == 0) {
                        board2[location2.x][location2.y + i] = dist2 + i;
                    }
                }
                location2 = new Point(location2.x, location2.y + delta);
            } else if (dir.charAt(0) == 'D') {
                for (int i = 1; i <= delta; i++) {
                    if (board2[location2.x][location2.y - i] == 0) {
                        board2[location2.x][location2.y - i] = dist2 + i;
                    }
                }
                location2 = new Point(location2.x, location2.y - delta);
            } else if (dir.charAt(0) == 'R') {
                for (int i = 1; i <= delta; i++) {
                    if (board2[location2.x + i][location2.y] == 0) {
                        board2[location2.x + i][location2.y] = dist2 + i;
                    }
                }
                location2 = new Point(location2.x + delta, location2.y);
            } else if (dir.charAt(0) == 'L') {
                for (int i = 1; i <= delta; i++) {
                    if (board2[location2.x - i][location2.y] == 0) {
                        board2[location2.x - i][location2.y] = dist2 + i;
                    }
                }
                location2 = new Point(location2.x - delta, location2.y);
            }
            dist2 += delta;
        }
        int dist = Integer.MAX_VALUE;
        for (int i = 0; i < board1.length; i++) {
            for (int j = 0; j < board1[0].length; j++) {
                if (board1[i][j] != 0 && board2[i][j] != 0 && board1[i][j] + board2[i][j] < dist) {
                    dist = board1[i][j] + board2[i][j];
                }
            }
        }
        System.out.println(dist);
    }
}
