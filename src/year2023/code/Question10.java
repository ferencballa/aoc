package year2023.code;

import helpers.Helper;

import java.io.IOException;

public class Question10 {
    public static void main(String[] args) throws IOException {
        Q10Part1.run();
        Q10Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 10);
    }
}

class Q10Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question10.getInput();
        char[][] grid = new char[input[0].length()][];
        for (int i = 0; i < input[0].length(); i++) {
            grid[i] = new char[input.length];
        }
        int startX = -1;
        int startY = -1;
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                grid[x][y] = input[y].charAt(x);
                if (grid[x][y] == 'S') {
                    startX = x;
                    startY = y;
                }
            }
        }
        int steps = 1;
        int curX = -1;
        int curY = -1;
        /*
        0 = from up
        1 = from right
        2 = from down
        3 = from left
         */
        int origin = -1;
        if (grid[startX][startY-1] == '|' || grid[startX][startY-1] == '7' || grid[startX][startY-1] == 'F') {
            curX = startX;
            curY = startY - 1;
            origin = 2;
        } else if (grid[startX+1][startY] == '-' || grid[startX+1][startY] == 'J' || grid[startX+1][startY] == '7') {
            curX = startX + 1;
            curY = startY;
            origin = 3;
        } else if (grid[startX][startY+1] == '|' || grid[startX][startY+1] == 'L' || grid[startX][startY+1] == 'J') {
            curX = startX;
            curY = startY + 1;
            origin = 0;
        } else if (grid[startX-1][startY] == '-' || grid[startX-1][startY] == 'L' || grid[startX-1][startY] == 'F') {
            curX = startX;
            curY = startY - 1;
            origin = 1;
        }
        while (!(curX == startX && curY == startY)) {
            steps++;
            char curChar = grid[curX][curY];
            if (curChar == '|') {
                if (origin == 0) {
                    curY++;
                } else {
                    curY--;
                }
            } else if (curChar == '-') {
                if (origin == 3) {
                    curX++;
                } else {
                    curX--;
                }
            } else if (curChar == 'L') {
                if (origin == 0) {
                    curX++;
                    origin = 3;
                } else {
                    curY--;
                    origin = 2;
                }
            } else if (curChar == 'J') {
                if (origin == 0) {
                    curX--;
                    origin = 1;
                } else {
                    curY--;
                    origin = 2;
                }
            } else if (curChar == '7') {
                if (origin == 2) {
                    curX--;
                    origin = 1;
                } else {
                    curY++;
                    origin = 0;
                }
            } else if (curChar == 'F') {
                if (origin == 2) {
                    curX++;
                    origin = 3;
                } else {
                    curY++;
                    origin = 0;
                }
            } else {
                System.out.println("Something went wrong on x: " + curX + ", y: " + curY);
                break;
            }
        }
        System.out.println(steps /2);
    }
}

class Q10Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question10.getInput();
        char[][] grid = new char[input[0].length()][];
        for (int i = 0; i < input[0].length(); i++) {
            grid[i] = new char[input.length];
        }
        int startX = -1;
        int startY = -1;
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                grid[x][y] = input[y].charAt(x);
                if (grid[x][y] == 'S') {
                    startX = x;
                    startY = y;
                }
            }
        }
        int curX = -1;
        int curY = -1;
        /*
        0 = from up
        1 = from right
        2 = from down
        3 = from left
         */
        int origin = -1;
        if (grid[startX][startY-1] == '|' || grid[startX][startY-1] == '7' || grid[startX][startY-1] == 'F') {
            curX = startX;
            curY = startY - 1;
            origin = 2;
            if (grid[startX+1][startY] == '-' || grid[startX+1][startY] == 'J' || grid[startX+1][startY] == '7') {
                grid[startX][startY] = 'L';
            } else if (grid[startX][startY+1] == '|' || grid[startX][startY+1] == 'L' || grid[startX][startY+1] == 'J') {
                grid[startX][startY] = '|';
            } else if (grid[startX-1][startY] == '-' || grid[startX-1][startY] == 'L' || grid[startX-1][startY] == 'F') {
                grid[startX][startY] = 'J';
            }
        } else if (grid[startX+1][startY] == '-' || grid[startX+1][startY] == 'J' || grid[startX+1][startY] == '7') {
            curX = startX + 1;
            curY = startY;
            origin = 3;
            if (grid[startX][startY-1] == '|' || grid[startX][startY-1] == '7' || grid[startX][startY-1] == 'F') {
                grid[startX][startY] = 'L';
            } else if (grid[startX][startY+1] == '|' || grid[startX][startY+1] == 'L' || grid[startX][startY+1] == 'J') {
                grid[startX][startY] = 'F';
            } else if (grid[startX-1][startY] == '-' || grid[startX-1][startY] == 'L' || grid[startX-1][startY] == 'F') {
                grid[startX][startY] = '-';
            }
        } else if (grid[startX][startY+1] == '|' || grid[startX][startY+1] == 'L' || grid[startX][startY+1] == 'J') {
            curX = startX;
            curY = startY + 1;
            origin = 0;
            if (grid[startX][startY-1] == '|' || grid[startX][startY-1] == '7' || grid[startX][startY-1] == 'F') {
                grid[startX][startY] = '|';
            } else if (grid[startX+1][startY] == '-' || grid[startX+1][startY] == 'J' || grid[startX+1][startY] == '7') {
                grid[startX][startY] = 'F';
            } else if (grid[startX-1][startY] == '-' || grid[startX-1][startY] == 'L' || grid[startX-1][startY] == 'F') {
                grid[startX][startY] = '7';
            }
        } else if (grid[startX-1][startY] == '-' || grid[startX-1][startY] == 'L' || grid[startX-1][startY] == 'F') {
            curX = startX;
            curY = startY - 1;
            origin = 1;
            if (grid[startX][startY-1] == '|' || grid[startX][startY-1] == '7' || grid[startX][startY-1] == 'F') {
                grid[startX][startY] = 'J';
            } else if (grid[startX+1][startY] == '-' || grid[startX+1][startY] == 'J' || grid[startX+1][startY] == '7') {
                grid[startX][startY] = '-';
            } else if (grid[startX][startY+1] == '|' || grid[startX][startY+1] == 'L' || grid[startX][startY+1] == 'J') {
                grid[startX][startY] = '7';
            }
        }
        boolean[][] secondGrid = new boolean[input[0].length()][];
        for (int i = 0; i < input[0].length(); i++) {
            secondGrid[i] = new boolean[input.length];
        }
        secondGrid[startX][startY] = true;
        while (!(curX == startX && curY == startY)) {
            secondGrid[curX][curY] = true;
            char curChar = grid[curX][curY];
            if (curChar == '|') {
                if (origin == 0) {
                    curY++;
                } else {
                    curY--;
                }
            } else if (curChar == '-') {
                if (origin == 3) {
                    curX++;
                } else {
                    curX--;
                }
            } else if (curChar == 'L') {
                if (origin == 0) {
                    curX++;
                    origin = 3;
                } else {
                    curY--;
                    origin = 2;
                }
            } else if (curChar == 'J') {
                if (origin == 0) {
                    curX--;
                    origin = 1;
                } else {
                    curY--;
                    origin = 2;
                }
            } else if (curChar == '7') {
                if (origin == 2) {
                    curX--;
                    origin = 1;
                } else {
                    curY++;
                    origin = 0;
                }
            } else if (curChar == 'F') {
                if (origin == 2) {
                    curX++;
                    origin = 3;
                } else {
                    curY++;
                    origin = 0;
                }
            } else {
                System.out.println("Something went wrong on x: " + curX + ", y: " + curY);
                break;
            }
        }
        char[][] gridOnlyLine = new char[input[0].length()][];
        for (int i = 0; i < input[0].length(); i++) {
            gridOnlyLine[i] = new char[input.length];
        }
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                if (secondGrid[x][y]) {
                    gridOnlyLine[x][y] = grid[x][y];
                } else {
                    gridOnlyLine[x][y] = '.';
                }
            }
        }
        int count = 0;
        for (int y = 0; y < input.length; y++) {
            boolean inside = false;
            boolean prevF = false;
            for (int x = 0; x < input[y].length(); x++) {
                if (gridOnlyLine[x][y] != '-') {
                    if (gridOnlyLine[x][y] == 'F') {
                        inside = !inside;
                        prevF = true;
                    } else {
                        if (gridOnlyLine[x][y] == '.' && inside) {
                            count++;
                        } else if (gridOnlyLine[x][y] == '|') {
                            inside = !inside;
                        } else if (gridOnlyLine[x][y] == 'L') {
                            inside = !inside;
                        } else if (gridOnlyLine[x][y] == '7' && prevF) {
                            inside = !inside;
                        } else if (gridOnlyLine[x][y] == 'J' && !prevF) {
                            inside = !inside;
                        }
                        prevF = false;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
