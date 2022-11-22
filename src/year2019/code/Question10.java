package year2019.code;

import helpers.Helper;

import java.io.IOException;
import java.util.HashMap;

public class Question10 {
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

    private static void part2(String[] input) {
        //find best station
        //run again, but only for best station:
        //when a visible asteroid is found: create gradient di/dj (for di = 0: 0, for dj = 0, Integer.maxInt), and an arraylist of points. put asteroid (as point) in array
        //with help of stepsize, extend beyond stepSize * factor, until out of bounds is reached.
        //each found asteroid on the way is added to the array.
        //when done add arraylist to arraylist of arraylists, in correct position. position is determined by:
        //first prio is to 0 -> negative biggest, with j negative, postive biggest -> 0, with j positive, 0 -> negative biggest, with j positive, positive biggest, with j negative.
        //create new arraylist which will contain order of asteroids destroyed
        //loop over prio arraylist. for each element remove first element, and put in order arraylist. If the arraylist from which an element was removed becomes empty, remove the arraylist from the prio arraylist.
        //get 200th element of order arraylist
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
