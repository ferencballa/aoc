package year2021.code;
import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Question15 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2021, 15);
        int[][] distances = new int[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            distances[i] = Helper.StringArrayToIntArray(input[i].split(""));
        }
        System.out.println("Part 1:");
        part1(distances);
        System.out.println("Part 2:");
        part2(distances);
    }

    private static void part1(int[][] distances) {
        HashMap<String, ArrayList<PointWithDistance>> neighbours = new HashMap<>();
        HashMap<String, PointWithDistance> shortestPrev = new HashMap<>();
        shortestPrev.put("0,0", new PointWithDistance("0,0", 0));
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[0].length; j++) {
                if (i != 0 || j != 0) {
                    shortestPrev.put(i + "," + j, new PointWithDistance("NaN,NaN", Integer.MAX_VALUE));
                }
                ArrayList<PointWithDistance> neighboursOfPoint = new ArrayList<>();
                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        if ((di == 0 && dj != 0) || (di != 0 && dj == 0)) {
                            int newI = i + di;
                            int newJ = j + dj;
                            if (newI >= 0 && newI < distances.length && newJ >= 0 && newJ < distances[0].length) {
                                neighboursOfPoint.add(new PointWithDistance(newI + "," + newJ, distances[newI][newJ]));
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
        HashSet<String> uncheckedHash = new HashSet<>();
        if (distances[1][0] > distances[0][1]) {
            unchecked.add("0,1");
            unchecked.add("1,0");
        } else {
            unchecked.add("1,0");
            unchecked.add("0,1");
        }
        uncheckedHash.add("0,1");
        uncheckedHash.add("1,0");
        shortestPrev.put("1,0", new PointWithDistance("0,0", distances[1][0]));
        shortestPrev.put("0,1", new PointWithDistance("0,0", distances[0][1]));
        while (!unchecked.isEmpty()) {
            String cur = unchecked.remove(0);
            uncheckedHash.remove(cur);
            for (PointWithDistance neighbour : neighbours.get(cur)) {
                if (!checked.contains(neighbour.point)) {
                    int newDistance = shortestPrev.get(cur).distance + neighbour.distance;
                    if (shortestPrev.get(neighbour.point).distance > newDistance) {
                        shortestPrev.put(neighbour.point, new PointWithDistance(cur, newDistance));
                    }
                    if (!uncheckedHash.contains(neighbour.point)) {
                        if (unchecked.isEmpty()) {
                            unchecked.add(neighbour.point);
                        } else {
                            int d1 = shortestPrev.get(neighbour.point).distance;
                            int i = 0;
                            int uncheckedSize = unchecked.size();
                            while (i < uncheckedSize && d1 > shortestPrev.get(unchecked.get(i)).distance) {
                                i++;
                            }
                            unchecked.add(i, neighbour.point);
                        }
                        uncheckedHash.add(neighbour.point);
                    }
                }
            }
            checked.add(cur);
            //System.out.println(distances.length * distances[0].length - checked.size());
        }
        int maxX = distances.length - 1;
        int maxY = distances[0].length - 1;
        System.out.println(shortestPrev.get(maxX + "," + maxY).distance);
    }

    private static void part2(int[][] distancesSmall) {
        int factor = 5;
        int[][] distances = new int[distancesSmall.length * factor][distancesSmall[0].length * factor];
        for (int i = 0; i < distancesSmall.length; i++) {
            for (int j = 0; j < distancesSmall[0].length; j++) {
                for (int di = 0; di < factor; di++) {
                    for (int dj = 0; dj < factor; dj++) {
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

class PointWithDistance {
    String point;
    int distance;

    PointWithDistance(String p, int d) {
        point = p;
        distance = d;
    }
}
