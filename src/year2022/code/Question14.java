package year2022.code;

import helpers.Helper;

import java.io.IOException;

public class Question14 {
    public static void main(String[] args) throws IOException {
        Q14Part1.run();
        Q14Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 14);
    }
}

class Q14Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question14.getInput();
        int smallestX = Integer.MAX_VALUE;
        int largestX = Integer.MIN_VALUE;
        int largestY = Integer.MIN_VALUE;
        for (String s : input) {
            String[] coors = s.split(" -> ");
            for (String coor : coors) {
                String[] coorParts = coor.split(",");
                smallestX = Math.min(smallestX, Integer.parseInt(coorParts[0]));
                largestX = Math.max(largestX, Integer.parseInt(coorParts[0]));
                largestY = Math.max(largestY, Integer.parseInt(coorParts[1]));
            }
        }
        int offsetX = smallestX;
        int[][] grid = new int[largestX + 1 - smallestX][];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = new int[largestY + 1];
        }
        for (String s : input) {
            String[] coors = s.split(" -> ");
            for (int i = 0; i < coors.length - 1; i++) {
                String[] coorParts1 = coors[i].split(",");
                String[] coorParts2 = coors[i+1].split(",");
                int x1 = Integer.parseInt(coorParts1[0]);
                int y1 = Integer.parseInt(coorParts1[1]);
                int x2 = Integer.parseInt(coorParts2[0]);
                int y2 = Integer.parseInt(coorParts2[1]);
                if (x1 < x2 || x1 > x2) {
                    if (x1 < x2) {
                        for (int x = x1; x <= x2; x++) {
                            grid[x - offsetX][y1] = 1;
                        }
                    } else {
                        for (int x = x1; x >= x2; x--) {
                            grid[x - offsetX][y1] = 1;
                        }
                    }
                } else {
                    if (y1 < y2) {
                        for (int y = y1; y <= y2; y++) {
                            grid[x1 - offsetX][y] = 1;
                        }
                    } else {
                        for (int y = y1; y >= y2; y--) {
                            grid[x1 - offsetX][y] = 1;
                        }
                    }
                }
            }
        }
        boolean sandOutOfBounds = false;
        int sandX = -1;
        int sandY = -1;
        boolean sandStoppedWithinBounds = true;
        while (!sandOutOfBounds) {
            if (sandStoppedWithinBounds) {
                sandX = 500;
                sandY = 0;
                grid[sandX - offsetX][sandY] = 2;
                sandStoppedWithinBounds = false;
            } else {
                if (sandY + 1 == grid[0].length) {
                    grid[sandX - offsetX][sandY] = 0;
                    sandOutOfBounds = true;
                } else if (grid[sandX - offsetX][sandY + 1] == 0) {
                    grid[sandX - offsetX][sandY] = 0;
                    grid[sandX - offsetX][sandY + 1] = 2;
                    sandY++;
                } else {
                    if (sandX - offsetX - 1 < 0) {
                        grid[sandX - offsetX][sandY] = 0;
                        sandOutOfBounds = true;
                    } else if (grid[sandX - offsetX - 1][sandY + 1] == 0) {
                        grid[sandX - offsetX][sandY] = 0;
                        grid[sandX - offsetX - 1][sandY + 1] = 2;
                        sandX--;
                        sandY++;
                    } else {
                        if (sandX - offsetX + 1 >= grid.length) {
                            grid[sandX - offsetX][sandY] = 0;
                            sandOutOfBounds = true;
                        } else if (grid[sandX - offsetX + 1][sandY + 1] == 0) {
                            grid[sandX - offsetX][sandY] = 0;
                            grid[sandX - offsetX + 1][sandY + 1] = 2;
                            sandX++;
                            sandY++;
                        } else {
                            sandStoppedWithinBounds = true;
                        }
                    }
                }
            }
        }
        int countSand = 0;
        for (int[] row : grid) {
            for (int val : row) {
                if (val == 2) {
                    countSand++;
                }
            }
        }
        System.out.println(countSand);
    }
}

class Q14Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question14.getInput();
        int smallestX = Integer.MAX_VALUE;
        int largestX = Integer.MIN_VALUE;
        int largestY = Integer.MIN_VALUE;
        for (String s : input) {
            String[] coors = s.split(" -> ");
            for (String coor : coors) {
                String[] coorParts = coor.split(",");
                smallestX = Math.min(smallestX, Integer.parseInt(coorParts[0]));
                largestX = Math.max(largestX, Integer.parseInt(coorParts[0]));
                largestY = Math.max(largestY, Integer.parseInt(coorParts[1]));
            }
        }
        largestY += 2;
        smallestX -= largestY;
        largestX += largestY;
        int offsetX = smallestX;
        int[][] grid = new int[largestX + 1 - smallestX][];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = new int[largestY + 1];
            grid[i][largestY] = 1;
        }
        for (String s : input) {
            String[] coors = s.split(" -> ");
            for (int i = 0; i < coors.length - 1; i++) {
                String[] coorParts1 = coors[i].split(",");
                String[] coorParts2 = coors[i+1].split(",");
                int x1 = Integer.parseInt(coorParts1[0]);
                int y1 = Integer.parseInt(coorParts1[1]);
                int x2 = Integer.parseInt(coorParts2[0]);
                int y2 = Integer.parseInt(coorParts2[1]);
                if (x1 < x2 || x1 > x2) {
                    if (x1 < x2) {
                        for (int x = x1; x <= x2; x++) {
                            grid[x - offsetX][y1] = 1;
                        }
                    } else {
                        for (int x = x1; x >= x2; x--) {
                            grid[x - offsetX][y1] = 1;
                        }
                    }
                } else {
                    if (y1 < y2) {
                        for (int y = y1; y <= y2; y++) {
                            grid[x1 - offsetX][y] = 1;
                        }
                    } else {
                        for (int y = y1; y >= y2; y--) {
                            grid[x1 - offsetX][y] = 1;
                        }
                    }
                }
            }
        }
        int sandX = -1;
        int sandY = -1;
        boolean sandStopped = true;
        boolean plugged = false;
        while (!plugged) {
            if (sandStopped) {
                sandX = 500;
                sandY = 0;
                grid[sandX - offsetX][sandY] = 2;
                sandStopped = false;
            } else {
                if (grid[sandX - offsetX][sandY + 1] == 0) {
                    grid[sandX - offsetX][sandY] = 0;
                    grid[sandX - offsetX][sandY + 1] = 2;
                    sandY++;
                } else {
                    if (grid[sandX - offsetX - 1][sandY + 1] == 0) {
                        grid[sandX - offsetX][sandY] = 0;
                        grid[sandX - offsetX - 1][sandY + 1] = 2;
                        sandX--;
                        sandY++;
                    } else {
                        if (grid[sandX - offsetX + 1][sandY + 1] == 0) {
                            grid[sandX - offsetX][sandY] = 0;
                            grid[sandX - offsetX + 1][sandY + 1] = 2;
                            sandX++;
                            sandY++;
                        } else {
                            sandStopped = true;
                            if (sandX == 500 && sandY == 0) {
                                plugged = true;
                            }
                        }
                    }
                }
            }
        }
        int countSand = 0;
        for (int[] row : grid) {
            for (int val : row) {
                if (val == 2) {
                    countSand++;
                }
            }
        }
        System.out.println(countSand);
    }
}
