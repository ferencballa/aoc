package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Question9 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/input/Question9.txt")).toArray(new String[0]);
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
        //boolean[][] basinCheckDone = new boolean[input.length][input[0].length()];
        //boolean[][] inBasin = new boolean[input.length][input[0].length()];
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
                    /*addedToCheckForInBasin[i][j] = true;
                    inBasin[i][j] = true;
                    basinCheckDone[i][j] = true;
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
                        boolean returnToQueue = false;
                        boolean allSmallerNeighboursInBasin = true;
                        if (x > 0 && map[x - 1][y] < map[x][y] && !inBasin[x - 1][y]) {
                            if (!basinCheckDone[x - 1][y]) {
                                returnToQueue = true;
                            }
                            allSmallerNeighboursInBasin = false;
                        }
                        if (x < input.length - 1 && map[x + 1][y] < map[x][y] && !inBasin[x + 1][y]) {
                            if (!basinCheckDone[x + 1][y]) {
                                returnToQueue = true;
                            }
                            allSmallerNeighboursInBasin = false;
                        }
                        if (y > 0 && map[x][y - 1] < map[x][y] && !inBasin[x][y - 1]) {
                            if (!basinCheckDone[x][y - 1]) {
                                returnToQueue = true;
                            }
                            allSmallerNeighboursInBasin = false;
                        }
                        if (y < input[0].length() - 1 && map[x][y + 1] < map[x][y] && !inBasin[x][y + 1]) {
                            if (!basinCheckDone[x][y + 1]) {
                                returnToQueue = true;
                            }
                            allSmallerNeighboursInBasin = false;
                        }
                        if (returnToQueue) {
                            adjacent.add(cur);
                        } else {
                            basinCheckDone[x][y] = true;
                            if (allSmallerNeighboursInBasin && map[x][y] != 9) {
                                basinSize++;
                                inBasin[x][y] = true;
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
                    }*/
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
