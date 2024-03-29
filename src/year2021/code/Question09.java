package year2021.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Question09 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2021, 9);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        int[][] map = new int[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length(); j++) {
                map[i][j] = Integer.parseInt(input[i].substring(j, j+1));
            }
        }
        int risks = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length(); j++) {
                if ((i == 0 || map[i-1][j] > map[i][j]) && (i == input.length - 1 || map[i+1][j] > map[i][j]) && (j == 0 || map[i][j-1] > map[i][j]) && (j == input[0].length() - 1 || map[i][j+1] > map[i][j])) {
                    risks += map[i][j] + 1;
                }
            }
        }
        System.out.println(risks);
    }

    private static void part2(String[] input) {
        int[][] map = new int[input.length][input[0].length()];
        boolean[][] addedToCheckForInBasin = new boolean[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length(); j++) {
                map[i][j] = Integer.parseInt(input[i].substring(j, j+1));
            }
        }
        ArrayList<Integer> basins = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length(); j++) {
                if ((i == 0 || map[i-1][j] > map[i][j]) && (i == input.length - 1 || map[i+1][j] > map[i][j]) && (j == 0 || map[i][j-1] > map[i][j]) && (j == input[0].length() - 1 || map[i][j+1] > map[i][j])) {
                    int basinSize = 1;
                    addedToCheckForInBasin[i][j] = true;
                    ArrayList<Coordinate> adjacent = new ArrayList<>();
                    if (i > 0) {
                        adjacent.add(new Coordinate(i - 1, j));
                        addedToCheckForInBasin[i-1][j] = true;
                    }
                    if (i < input.length - 1) {
                        adjacent.add(new Coordinate(i + 1, j));
                        addedToCheckForInBasin[i+1][j] = true;
                    }
                    if (j > 0) {
                        adjacent.add(new Coordinate(i, j - 1));
                        addedToCheckForInBasin[i][j-1] = true;
                    }
                    if (j < input[0].length() - 1) {
                        adjacent.add(new Coordinate(i, j + 1));
                        addedToCheckForInBasin[i][j+1] = true;
                    }
                    while (adjacent.size() > 0) {
                        Coordinate cur = adjacent.remove(0);
                        int x = cur.i;
                        int y = cur.j;
                        if (map[x][y] != 9) {
                            basinSize++;
                            if (x > 0 && !addedToCheckForInBasin[x - 1][y]) {
                                adjacent.add(new Coordinate(x - 1, y));
                                addedToCheckForInBasin[x - 1][y] = true;
                            }
                            if (x < input.length - 1 && !addedToCheckForInBasin[x + 1][y]) {
                                adjacent.add(new Coordinate(x + 1, y));
                                addedToCheckForInBasin[x + 1][y] = true;
                            }
                            if (y > 0 && !addedToCheckForInBasin[x][y - 1]) {
                                adjacent.add(new Coordinate(x, y - 1));
                                addedToCheckForInBasin[x][y - 1] = true;
                            }
                            if (y < input[0].length() - 1 && !addedToCheckForInBasin[x][y + 1]) {
                                adjacent.add(new Coordinate(x, y + 1));
                                addedToCheckForInBasin[x][y + 1] = true;
                            }
                        }
                    }
                    basins.add(basinSize);
                }
            }
        }
        Collections.sort(basins);
        System.out.println(basins.get(basins.size()-1) * basins.get(basins.size()-2) * basins.get(basins.size()-3));
    }
}

class Coordinate {
    int i;
    int j;

    public Coordinate(int iNew, int jNew) {
        i = iNew;
        j = jNew;
    }
}
