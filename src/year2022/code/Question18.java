package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Question18 {
    public static void main(String[] args) throws IOException {
        Q18Part1.run();
        Q18Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 18);
    }
}

class Q18Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question18.getInput();
        HashSet<String> cubes = new HashSet<>(Arrays.asList(input));
        int surfArea = 0;
        for (String cube : input) {
            int[] coors = Helper.StringArrayToInt(cube.split(","));
            if (!cubes.contains((coors[0] - 1) + "," + coors[1] + "," + coors[2])) {
                surfArea++;
            }
            if (!cubes.contains((coors[0] + 1) + "," + coors[1] + "," + coors[2])) {
                surfArea++;
            }
            if (!cubes.contains(coors[0] + "," + (coors[1] - 1) + "," + coors[2])) {
                surfArea++;
            }
            if (!cubes.contains(coors[0] + "," + (coors[1] + 1) + "," + coors[2])) {
                surfArea++;
            }
            if (!cubes.contains(coors[0] + "," + coors[1] + "," + (coors[2] - 1))) {
                surfArea++;
            }
            if (!cubes.contains(coors[0] + "," + coors[1] + "," + (coors[2] + 1))) {
                surfArea++;
            }
        }
        System.out.println(surfArea);
    }
}

class Q18Part2 {
    static int highestX = Integer.MIN_VALUE;
    static int lowestX = Integer.MAX_VALUE;
    static int highestY = Integer.MIN_VALUE;
    static int lowestY = Integer.MAX_VALUE;
    static int highestZ = Integer.MIN_VALUE;
    static int lowestZ = Integer.MAX_VALUE;
    static HashSet<String> cubes = new HashSet<>();

    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question18.getInput();
        for (String cube : input) {
            cubes.add(cube);
            int[] coors = Helper.StringArrayToInt(cube.split(","));
            highestX = Math.max(highestX, coors[0]);
            lowestX = Math.min(lowestX, coors[0]);
            highestY = Math.max(highestY, coors[1]);
            lowestY = Math.min(lowestY, coors[1]);
            highestZ = Math.max(highestZ, coors[2]);
            lowestZ = Math.min(lowestZ, coors[2]);
        }
        int surfArea = 0;
        for (String cube : input) {
            int[] inpCoors = Helper.StringArrayToInt(cube.split(","));
            ArrayList<String> startCoors = new ArrayList<>();
            startCoors.add((inpCoors[0] - 1) + "," + inpCoors[1] + "," + inpCoors[2]);
            startCoors.add((inpCoors[0] + 1) + "," + inpCoors[1] + "," + inpCoors[2]);
            startCoors.add(inpCoors[0] + "," + (inpCoors[1] - 1) + "," + inpCoors[2]);
            startCoors.add(inpCoors[0] + "," + (inpCoors[1] + 1) + "," + inpCoors[2]);
            startCoors.add(inpCoors[0] + "," + inpCoors[1] + "," + (inpCoors[2] - 1));
            startCoors.add(inpCoors[0] + "," + inpCoors[1] + "," + (inpCoors[2] + 1));
            for (String startCoor : startCoors) {
                if (hasWayOutDfs(startCoor, new HashSet<String>())) {
                    surfArea++;
                }
            }
        }
        System.out.println(surfArea);
    }

    private static boolean hasWayOutDfs(String pos, HashSet<String> visited) {
        if (cubes.contains(pos) || visited.contains(pos)) {
            return false;
        } else {
            visited.add(pos);
            int[] coors = Helper.StringArrayToInt(pos.split(","));
            if (coors[0] >= highestX || coors[0] <= lowestX || coors[1] >= highestY || coors[1] <= lowestY || coors[2] >= highestZ || coors[2] <= lowestZ) {
                return true;
            } else {
                return hasWayOutDfs((coors[0] - 1) + "," + coors[1] + "," + coors[2], visited) ||
                        hasWayOutDfs((coors[0] + 1) + "," + coors[1] + "," + coors[2], visited) ||
                        hasWayOutDfs(coors[0] + "," + (coors[1] - 1) + "," + coors[2], visited) ||
                        hasWayOutDfs(coors[0] + "," + (coors[1] + 1) + "," + coors[2], visited) ||
                        hasWayOutDfs(coors[0] + "," + coors[1] + "," + (coors[2] - 1), visited) ||
                        hasWayOutDfs(coors[0] + "," + coors[1] + "," + (coors[2] + 1), visited);
            }
        }
    }
}
