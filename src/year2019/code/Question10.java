package year2019.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Question10 {
    public static int bestXPart2 = -1;
    public static int bestYPart2 = -1;
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2019, 10);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        HashMap<Integer, HashMap<Integer, Integer>> highestCommonFactor = new HashMap<>();
        boolean[][] map = new boolean[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            String[] line = input[i].split("");
            for (int j = 0; j < line.length; j++) {
                map[i][j] = line[j].equals("#");
            }
        }
        int bestCount = 0;
        for (int i1 = 0; i1 < map.length; i1++) {
            for (int j1 = 0; j1 < map[0].length; j1++) {
                if (map[i1][j1]) {
                    int count = 0;
                    for (int i2 = 0; i2 < map.length; i2++) {
                        for (int j2 = 0; j2 < map[0].length; j2++) {
                            if (map[i2][j2] && !(i1 == i2 && j1 == j2)) {
                                int id = Math.abs(i1 - i2);
                                int jd = Math.abs(j1 - j2);
                                int firstD = Math.max(id, jd);
                                int secondD = Math.min(id, jd);
                                int factor;
                                if (highestCommonFactor.containsKey(firstD)) {
                                    if (highestCommonFactor.get(firstD).containsKey(secondD)) {
                                        factor = highestCommonFactor.get(firstD).get(secondD);
                                    } else {
                                        factor = findHighestCommonFactor(firstD, secondD);
                                        highestCommonFactor.get(firstD).put(secondD, factor);
                                    }
                                } else {
                                    HashMap<Integer, Integer> highestCommonFactorSecond = new HashMap<>();
                                    factor = findHighestCommonFactor(firstD, secondD);
                                    highestCommonFactorSecond.put(secondD, factor);
                                    highestCommonFactor.put(firstD, highestCommonFactorSecond);
                                }
                                int iStepSize = id / factor;
                                int jStepSize = jd / factor;
                                if (i1 > i2) {
                                    iStepSize *= -1;
                                }
                                if (j1 > j2) {
                                    jStepSize *= -1;
                                }
                                boolean pathClear = true;
                                for (int step = 1; step < factor; step++) {
                                    if (map[i1 + step * iStepSize][j1 + step * jStepSize]) {
                                        pathClear = false;
                                        break;
                                    }
                                }
                                if (pathClear) {
                                    count++;
                                }
                            }
                        }
                    }
                    bestCount = Math.max(bestCount, count);
                }
            }
        }
        System.out.println(bestCount);
    }

    //It seems I messed x and y up in part1. I had to account for this in the comparator, and in printing the solution.
    //The algorithm is working, and you do get the correct solution, but if you'll try to read the code, keep in mind
    //that until the printing of the solution the axes are flipped
    private static void part2(String[] input) {
        HashMap<Integer, HashMap<Integer, Integer>> highestCommonFactor = new HashMap<>();
        boolean[][] map = new boolean[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            String[] line = input[i].split("");
            for (int j = 0; j < line.length; j++) {
                map[i][j] = line[j].equals("#");
            }
        }
        int bestCount = 0;
        for (int i1 = 0; i1 < map.length; i1++) {
            for (int j1 = 0; j1 < map[0].length; j1++) {
                if (map[i1][j1]) {
                    int count = 0;
                    for (int i2 = 0; i2 < map.length; i2++) {
                        for (int j2 = 0; j2 < map[0].length; j2++) {
                            if (map[i2][j2] && !(i1 == i2 && j1 == j2)) {
                                int id = Math.abs(i1 - i2);
                                int jd = Math.abs(j1 - j2);
                                int firstD = Math.max(id, jd);
                                int secondD = Math.min(id, jd);
                                int factor;
                                if (highestCommonFactor.containsKey(firstD)) {
                                    if (highestCommonFactor.get(firstD).containsKey(secondD)) {
                                        factor = highestCommonFactor.get(firstD).get(secondD);
                                    } else {
                                        factor = findHighestCommonFactor(firstD, secondD);
                                        highestCommonFactor.get(firstD).put(secondD, factor);
                                    }
                                } else {
                                    HashMap<Integer, Integer> highestCommonFactorSecond = new HashMap<>();
                                    factor = findHighestCommonFactor(firstD, secondD);
                                    highestCommonFactorSecond.put(secondD, factor);
                                    highestCommonFactor.put(firstD, highestCommonFactorSecond);
                                }
                                int iStepSize = id / factor;
                                int jStepSize = jd / factor;
                                if (i1 > i2) {
                                    iStepSize *= -1;
                                }
                                if (j1 > j2) {
                                    jStepSize *= -1;
                                }
                                boolean pathClear = true;
                                for (int step = 1; step < factor; step++) {
                                    if (map[i1 + step * iStepSize][j1 + step * jStepSize]) {
                                        pathClear = false;
                                        break;
                                    }
                                }
                                if (pathClear) {
                                    count++;
                                }
                            }
                        }
                    }
                    if (count > bestCount) {
                        bestCount = count;
                        bestXPart2 = i1;
                        bestYPart2 = j1;
                    }
                }
            }
        }
        ArrayList<ArrayList<Point>> gradientList = new ArrayList<>();
        for (int i2 = 0; i2 < map.length; i2++) {
            for (int j2 = 0; j2 < map[0].length; j2++) {
                if (map[i2][j2] && !(bestXPart2 == i2 && bestYPart2 == j2)) {
                    int id = Math.abs(bestXPart2 - i2);
                    int jd = Math.abs(bestYPart2 - j2);
                    int firstD = Math.max(id, jd);
                    int secondD = Math.min(id, jd);
                    int factor;
                    if (highestCommonFactor.containsKey(firstD)) {
                        if (highestCommonFactor.get(firstD).containsKey(secondD)) {
                            factor = highestCommonFactor.get(firstD).get(secondD);
                        } else {
                            factor = findHighestCommonFactor(firstD, secondD);
                            highestCommonFactor.get(firstD).put(secondD, factor);
                        }
                    } else {
                        HashMap<Integer, Integer> highestCommonFactorSecond = new HashMap<>();
                        factor = findHighestCommonFactor(firstD, secondD);
                        highestCommonFactorSecond.put(secondD, factor);
                        highestCommonFactor.put(firstD, highestCommonFactorSecond);
                    }
                    int iStepSize = id / factor;
                    int jStepSize = jd / factor;
                    if (bestXPart2 > i2) {
                        iStepSize *= -1;
                    }
                    if (bestYPart2 > j2) {
                        jStepSize *= -1;
                    }
                    boolean pathClear = true;
                    for (int step = 1; step < factor; step++) {
                        if (map[bestXPart2 + step * iStepSize][bestYPart2 + step * jStepSize]) {
                            pathClear = false;
                            break;
                        }
                    }
                    if (pathClear) {
                        ArrayList<Point> pointsOnALine = new ArrayList<>();
                        int curX = i2;
                        int curY = j2;
                        while (curX >= 0 && curX < map.length && curY >= 0 && curY < map[0].length) {
                            if (map[curX][curY]) {
                                pointsOnALine.add(new Point(curX, curY));
                            }
                            curX += iStepSize;
                            curY += jStepSize;
                        }
                        gradientList.add(pointsOnALine);
                    }
                }
            }
        }
        gradientList.sort(new CustomComparator());
        int destroyed = 0;
        Point lastDestroyedPoint;
        destroy200:
        while (true) {
            for (ArrayList<Point> pointsOnALine : gradientList) {
                lastDestroyedPoint = pointsOnALine.remove(0);
                destroyed++;
                if (destroyed == 200) {
                    break destroy200;
                }
            }
            for (int i = 0; i < gradientList.size(); i++) {
                if (gradientList.get(i).isEmpty()) {
                    gradientList.remove(i);
                    i--;
                }
            }
        }
        int solution = lastDestroyedPoint.x + lastDestroyedPoint.y * 100;
        System.out.println(solution);
    }

    private static int findHighestCommonFactor(int big, int small) {
        int biggest = 1;
        for (int i = 2; i <= big; i++) {
            if (big % i == 0 && small % i == 0) {
                biggest = i;
            }
        }
        return biggest;
    }
}

class CustomComparator implements Comparator<ArrayList<Point>> {
    @Override
    public int compare(ArrayList<Point> list1, ArrayList<Point> list2) {
        double dx1 = list1.get(0).x - Question10.bestXPart2;
        double dy1 = list1.get(0).y - Question10.bestYPart2;
        double dx2 = list2.get(0).x - Question10.bestXPart2;
        double dy2 = list2.get(0).y - Question10.bestYPart2;
        if (dy1 == 0 && dx1 < 0) {
            return -1;
        }
        if (dy2 == 0 && dx2 < 0) {
            return 1;
        }
        if (dy1 > 0 && dx1 < 0) {
            if (dy2 > 0 && dx2 < 0) {
                if (dy1/dx1 > dy2/dx2) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return -1;
            }
        }
        if (dy2 > 0 && dx2 < 0) {
            return 1;
        }
        if (dy1 > 0 && dx1 == 0) {
            return -1;
        }
        if (dy2 > 0 && dx2 == 0) {
            return 1;
        }
        if (dy1 > 0 && dx1 > 0) {
            if (dy2 > 0 && dx2 > 0) {
                if (dy1/dx1 > dy2/dx2) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return -1;
            }
        }
        if (dy2 > 0 && dx2 > 0) {
            return 1;
        }
        if (dy1 == 0 && dx1 > 0) {
            return -1;
        }
        if (dy2 == 0 && dx2 > 0) {
            return 1;
        }
        if (dy1 < 0 && dx1 > 0) {
            if (dy2 < 0 && dx2 > 0) {
                if (dy1/dx1 > dy2/dx2) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return -1;
            }
        }
        if (dy2 < 0 && dx2 > 0) {
            return 1;
        }
        if (dy1 < 0 && dx1 == 0) {
            return -1;
        }
        if (dy2 < 0 && dx2 == 0) {
            return 1;
        }
        if (dy1 < 0 && dx1 < 0) {
            if (dy2 < 0 && dx2 < 0) {
                if (dy1/dx1 > dy2/dx2) {
                    return -1;
                } else {
                    return 1;
                }
            }
            else {
                return -1;
            }
        }
        if (dy2 < 0 && dx2 < 0) {
            return 1;
        }
        System.out.println("This state shouldn't be reached");
        return 0;
    }
}
