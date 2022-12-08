package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.HashMap;

public class Question08 {
    public static void main(String[] args) throws IOException {
        Q08Part1.run();
        Q08Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 8);
    }
}

class Q08Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question08.getInput();
        int[][] inp = new int[input.length][];
        for (int i = 0; i < input.length; i++) {
            inp[i] = Helper.StringArrayToInt(input[i].split(""));
        }
        int visible = 0;
        HashMap<Integer, HashMap<Integer, Boolean>> visiblePoints = new HashMap<>();
        for (int i = 0; i < inp.length; i++) {
            int maxHeightSoFar = -1;
            for (int j = 0; j < inp[0].length; j++) {
                if (inp[i][j] > maxHeightSoFar) {
                    maxHeightSoFar = inp[i][j];
                    HashMap<Integer, Boolean> row = visiblePoints.get(i);
                    if (row == null) {
                        visiblePoints.put(i, new HashMap<>());
                        row = visiblePoints.get(i);
                    }
                    Boolean exists = row.get(j);
                    if (exists == null) {
                        row.put(j, true);
                        visible++;
                    }
                }
            }
        }
        for (int i = 0; i < inp.length; i++) {
            int maxHeightSoFar = -1;
            for (int j = inp[0].length - 1; j >= 0; j--) {
                if (inp[i][j] > maxHeightSoFar) {
                    maxHeightSoFar = inp[i][j];
                    HashMap<Integer, Boolean> row = visiblePoints.get(i);
                    if (row == null) {
                        visiblePoints.put(i, new HashMap<>());
                        row = visiblePoints.get(i);
                    }
                    Boolean exists = row.get(j);
                    if (exists == null) {
                        row.put(j, true);
                        visible++;
                    }
                }
            }
        }
        for (int j = 0; j < inp[0].length; j++) {
            int maxHeightSoFar = -1;
            for (int i = 0; i < inp.length; i++) {
                if (inp[i][j] > maxHeightSoFar) {
                    maxHeightSoFar = inp[i][j];
                    HashMap<Integer, Boolean> row = visiblePoints.get(i);
                    if (row == null) {
                        visiblePoints.put(i, new HashMap<>());
                        row = visiblePoints.get(i);
                    }
                    Boolean exists = row.get(j);
                    if (exists == null) {
                        row.put(j, true);
                        visible++;
                    }
                }
            }
        }
        for (int j = 0; j < inp[0].length; j++) {
            int maxHeightSoFar = -1;
            for (int i = inp.length - 1; i >= 0; i--) {
                if (inp[i][j] > maxHeightSoFar) {
                    maxHeightSoFar = inp[i][j];
                    HashMap<Integer, Boolean> row = visiblePoints.get(i);
                    if (row == null) {
                        visiblePoints.put(i, new HashMap<>());
                        row = visiblePoints.get(i);
                    }
                    Boolean exists = row.get(j);
                    if (exists == null) {
                        row.put(j, true);
                        visible++;
                    }
                }
            }
        }
        System.out.println(visible);
    }
}

class Q08Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question08.getInput();
        int[][] inp = new int[input.length][];
        for (int i = 0; i < input.length; i++) {
            inp[i] = Helper.StringArrayToInt(input[i].split(""));
        }
        int bestScenicScore = 0;
        for (int i = 0; i < inp.length; i++) {
            for (int j = 0; j < inp[0].length; j++) {
                int scenic1 = 0;
                int prevHeight = inp[i][j];
                for (int ni = i-1; ni >= 0; ni--) {
                    scenic1++;
                    if (prevHeight <= inp[ni][j]) {
                        break;
                    }
                }
                int scenic2 = 0;
                prevHeight = inp[i][j];
                for (int ni = i+1; ni <inp.length; ni++) {
                    scenic2++;
                    if (prevHeight <= inp[ni][j]) {
                        break;
                    }
                }
                int scenic3 = 0;
                prevHeight = inp[i][j];
                for (int nj = j-1; nj >= 0; nj--) {
                    scenic3++;
                    if (prevHeight <= inp[i][nj]) {
                        break;
                    }
                }
                int scenic4 = 0;
                prevHeight = inp[i][j];
                for (int nj = j+1; nj < inp[0].length; nj++) {
                    scenic4++;
                    if (prevHeight <= inp[i][nj]) {
                        break;
                    }
                }
                bestScenicScore = Math.max(bestScenicScore, scenic1 * scenic2 * scenic3 * scenic4);
            }
        }
        System.out.println(bestScenicScore);
    }
}
