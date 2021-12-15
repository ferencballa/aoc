package year2021.code;
import helpers.Helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Question15 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2021/input/Question15.txt")).toArray(new String[0]);
        int[][] distances = new int[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            distances[i] = Helper.StringArrayToInt(input[i].split(""));
        }
        System.out.println("Part 1:");
        part1(distances);
        System.out.println("Part 2:");
        part2(distances);
    }

    private static void part1(int[][] distances) {
        HashMap<String, ArrayList<String>> neighbours = new HashMap<>();
        HashMap<String, String> shortestPrev = new HashMap<>();
        shortestPrev.put("0,0", "0,0:0");
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[0].length; j++) {
                if (i != 0 || j != 0) {
                    shortestPrev.put(i + "," + j, "NaN,NaN:" + Integer.MAX_VALUE);
                }
                ArrayList<String> neighboursOfPoint = new ArrayList<>();
                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        if ((di == 0 && dj != 0) || (di != 0 && dj == 0)) {
                            int newI = i + di;
                            int newJ = j + dj;
                            if (newI >= 0 && newI < distances.length && newJ >= 0 && newJ < distances[0].length) {
                                neighboursOfPoint.add(newI + "," + newJ + ":" + distances[newI][newJ]);
                            }
                        }
                    }
                }
                neighbours.put(i + "," + j, neighboursOfPoint);
            }
        }
        HashSet<String> checked = new HashSet<>();
        checked.add("0,0");
        ArrayList<String> unchecked = new ArrayList<>();
        unchecked.add("1,0");
        unchecked.add("0,1");
        shortestPrev.put("1,0", "0,0:" + distances[1][0]);
        shortestPrev.put("0,1", "0,0:" + distances[0][1]);
        while (!unchecked.isEmpty()) {
            unchecked.sort((str1, str2) -> {
                int d1 = Integer.parseInt(shortestPrev.get(str1).split(":")[1]);
                int d2 = Integer.parseInt(shortestPrev.get(str2).split(":")[1]);
                return d1 - d2;
            });
            String cur = unchecked.remove(0);
            for (String neighbour : neighbours.get(cur)) {
                String[] neighbourFields = neighbour.split(":");
                if (!checked.contains(neighbourFields[0])) {
                    int newDistance = Integer.parseInt(shortestPrev.get(cur).split(":")[1]) + Integer.parseInt(neighbourFields[1]);
                    if (Integer.parseInt(shortestPrev.get(neighbourFields[0]).split(":")[1]) > newDistance) {
                        shortestPrev.put(neighbourFields[0], cur + ":" + newDistance);
                    }
                    if (!unchecked.contains(neighbourFields[0])) {
                        unchecked.add(neighbourFields[0]);
                    }
                }
            }
            checked.add(cur);
            //System.out.println(distances.length * distances[0].length - checked.size());
        }
        int maxX = distances.length - 1;
        int maxY = distances[0].length - 1;
        System.out.println(shortestPrev.get(maxX + "," + maxY).split(":")[1]);
    }

    private static void part2(int[][] distancesSmall) {
        int[][] distances = new int[distancesSmall.length * 5][distancesSmall[0].length * 5];
        for (int i = 0; i < distancesSmall.length; i++) {
            for (int j = 0; j < distancesSmall[0].length; j++) {
                for (int di = 0; di < 5; di++) {
                    for (int dj = 0; dj < 5; dj++) {
                        int newDistance = distancesSmall[i][j] + di + dj;
                        while (newDistance > 9) {
                            newDistance -= 9;
                        }
                        distances[i + di * distancesSmall.length][j + dj * distancesSmall[0].length] = newDistance;
                    }
                }
            }
        }
        part1(distances);
    }
}
