package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question19 {
    public static void main(String[] args) throws IOException {
        Q19Part1.run();
        Q19Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 19);
    }
}

class Q19Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question19.getInput();
        int minutes = 24;
        int[][] costs = new int[input.length][];
        for (int i = 0; i < input.length; i++) {
            String[] line = input[i].split(" ");
            int[] lineVals = new int[6];
            lineVals[0] = Integer.parseInt(line[6]);
            lineVals[1] = Integer.parseInt(line[12]);
            lineVals[2] = Integer.parseInt(line[18]);
            lineVals[3] = Integer.parseInt(line[21]);
            lineVals[4] = Integer.parseInt(line[27]);
            lineVals[5] = Integer.parseInt(line[30]);
            costs[i] = lineVals;
        }
        int[] bestValues = new int[input.length];
        //create triangular number sequence
        for (int i = 0; i < input.length; i++) {
            System.out.println("Line: " + i);
            HashMap<String, Integer> posByTime = new HashMap<>();
            ArrayList<int[]> stateQueue = new ArrayList<>();
            int[] initState = new int[] {0, 1, 0, 0, 0, 0, 0, 0, 0};
            posByTime.put("0,1,0,0,0,0,0,0", 0);
            stateQueue.add(initState);
            int maxOreNeeded = Math.max(costs[i][1], Math.max(costs[i][2], costs[i][4]));
            int maxClayNeeded = costs[i][3];
            int maxObsNeeded = costs[i][5];
            while (!stateQueue.isEmpty()) {
                int[] currentState = stateQueue.remove(0);
                int ore = currentState[0];
                int orePS = currentState[1];
                int clay = currentState[2];
                int clayPS = currentState[3];
                int obs = currentState[4];
                int obsPS = currentState[5];
                int geode = currentState[6];
                if (geode > 9) {
                    //System.out.println("something went wrong");
                }
                int geodePS = currentState[7];
                int time = currentState[8];
                String key = ore + "," + orePS + "," + clay + "," + clayPS + "," + obs + "," + obsPS + "," + geode + "," + geodePS;
                int maxExtraGeodesPossibleAtTime = 0;
                for (int m = 0; m < minutes - time; m++) {
                    maxExtraGeodesPossibleAtTime += m;
                }
                if (!posByTime.containsKey(key) || posByTime.get(key) <= time &&
                    geode + geodePS * (minutes - time) + maxExtraGeodesPossibleAtTime > bestValues[i]) {
                    posByTime.put(key, time);
                    if (time < minutes) {
                        boolean hasEnoughOreRobots = orePS >= maxOreNeeded;
                        boolean hasEnoughClayRobots = clayPS >= maxClayNeeded;
                        boolean hasEnoughObsRobots = obsPS >= maxObsNeeded;
                        int minutesToBuildNextGeodeRobot = obsPS > 0 ? Math.max((int) Math.ceil((costs[i][5] - obs) / (double) obsPS), Math.max((int) Math.ceil((costs[i][4] - ore) / (double) orePS), 0)) : minutes + 1;
                        int minutesToBuildNextObsRobot = clayPS > 0 ? Math.max((int) Math.ceil((costs[i][3] - clay) / (double) clayPS), Math.max((int) Math.ceil((costs[i][2] - ore) / (double) orePS), 0)) : minutes + 1;
                        int minutesToBuildNextClayRobot = Math.max((int) Math.ceil((costs[i][1] - ore) / (double) orePS), 0);
                        int minutesToBuildNextOreRobot = Math.max((int) Math.ceil((costs[i][0] - ore) / (double) orePS), 0);
                        //branch on what you will build next
                        if (minutesToBuildNextGeodeRobot + 1 <= (minutes - time)) {
                            //branch build geodeRobot
                            int newOre = ore - costs[i][4] + (minutesToBuildNextGeodeRobot + 1) * orePS;
                            int newOrePS = orePS;
                            int newClay = clay + (minutesToBuildNextGeodeRobot + 1) * clayPS;
                            int newClayPS = clayPS;
                            int newObs = obs - costs[i][5] + (minutesToBuildNextGeodeRobot + 1) * obsPS;
                            int newObsPS = obsPS;
                            int newGeode = geode + (minutesToBuildNextGeodeRobot + 1) * geodePS;
                            int newGeodePS = geodePS + 1;
                            int newTime = time + minutesToBuildNextGeodeRobot + 1;
                            stateQueue.add(new int[]{
                                    newOre,
                                    newOrePS,
                                    newClay,
                                    newClayPS,
                                    newObs,
                                    newObsPS,
                                    newGeode,
                                    newGeodePS,
                                    newTime});
                        }
                        if (!hasEnoughObsRobots && minutesToBuildNextObsRobot + 1 <= (minutes - time)) {
                            //branch build obsidianRobot
                            int newOre = ore - costs[i][2] + (minutesToBuildNextObsRobot + 1) * orePS;
                            int newOrePS = orePS;
                            int newClay = clay - costs[i][3] + (minutesToBuildNextObsRobot + 1) * clayPS;
                            int newClayPS = clayPS;
                            int newObs = obs + (minutesToBuildNextObsRobot + 1) * obsPS;
                            int newObsPS = obsPS + 1;
                            int newGeode = geode + (minutesToBuildNextObsRobot + 1) * geodePS;
                            int newGeodePS = geodePS;
                            int newTime = time + minutesToBuildNextObsRobot + 1;
                            stateQueue.add(new int[]{
                                    newOre,
                                    newOrePS,
                                    newClay,
                                    newClayPS,
                                    newObs,
                                    newObsPS,
                                    newGeode,
                                    newGeodePS,
                                    newTime});
                        }
                        if (!hasEnoughClayRobots && minutesToBuildNextClayRobot + 1 <= (minutes - time)) {
                            //branch build clayRobot
                            int newOre = ore - costs[i][1] + (minutesToBuildNextClayRobot + 1) * orePS;
                            int newOrePS = orePS;
                            int newClay = clay + (minutesToBuildNextClayRobot + 1) * clayPS;
                            int newClayPS = clayPS + 1;
                            int newObs = obs + (minutesToBuildNextClayRobot + 1) * obsPS;
                            int newObsPS = obsPS;
                            int newGeode = geode + (minutesToBuildNextClayRobot + 1) * geodePS;
                            int newGeodePS = geodePS;
                            int newTime = time + minutesToBuildNextClayRobot + 1;
                            stateQueue.add(new int[]{
                                    newOre,
                                    newOrePS,
                                    newClay,
                                    newClayPS,
                                    newObs,
                                    newObsPS,
                                    newGeode,
                                    newGeodePS,
                                    newTime});
                        }
                        if (!hasEnoughOreRobots && minutesToBuildNextOreRobot + 1 <= (minutes - time)) {
                            //branch build oreRobot
                            int newOre = ore - costs[i][0] + (minutesToBuildNextOreRobot + 1) * orePS;
                            int newOrePS = orePS + 1;
                            int newClay = clay + (minutesToBuildNextOreRobot + 1) * clayPS;
                            int newClayPS = clayPS;
                            int newObs = obs + (minutesToBuildNextOreRobot + 1) * obsPS;
                            int newObsPS = obsPS;
                            int newGeode = geode + (minutesToBuildNextOreRobot + 1) * geodePS;
                            int newGeodePS = geodePS;
                            int newTime = time + minutesToBuildNextOreRobot + 1;
                            stateQueue.add(new int[]{
                                    newOre,
                                    newOrePS,
                                    newClay,
                                    newClayPS,
                                    newObs,
                                    newObsPS,
                                    newGeode,
                                    newGeodePS,
                                    newTime});
                        }
                        if ((hasEnoughOreRobots || minutesToBuildNextOreRobot + 1 > (minutes - time)) &&
                            (hasEnoughClayRobots || minutesToBuildNextClayRobot + 1 > (minutes - time)) &&
                            (hasEnoughObsRobots || minutesToBuildNextObsRobot + 1 > (minutes - time)) &&
                            (minutesToBuildNextGeodeRobot + 1 > (minutes - time))) {
                            int newOre = ore + (minutes - time) * orePS;
                            int newOrePS = orePS;
                            int newClay = clay + (minutes - time) * clayPS;
                            int newClayPS = clayPS;
                            int newObs = obs + (minutes - time) * obsPS;
                            int newObsPS = obsPS;
                            int newGeode = geode + (minutes - time) * geodePS;
                            int newGeodePS = geodePS;
                            int newTime = minutes;
                            stateQueue.add(new int[]{
                                    newOre,
                                    newOrePS,
                                    newClay,
                                    newClayPS,
                                    newObs,
                                    newObsPS,
                                    newGeode,
                                    newGeodePS,
                                    newTime});
                        }
                    } else {
                        bestValues[i] = Math.max(bestValues[i], geode);
                    }

                }
            }
        }
        int bestTotal = 0;
        for (int i = 0; i < bestValues.length; i++) {
            bestTotal += bestValues[i] * (i+1);
        }
        System.out.println("bestTotal:");
        System.out.println(bestTotal);
    }
}

class Q19Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question19.getInput();
        int minutes = 32;
        int[][] costs = new int[input.length][];
        for (int i = 0; i < input.length; i++) {
            String[] line = input[i].split(" ");
            int[] lineVals = new int[6];
            lineVals[0] = Integer.parseInt(line[6]);
            lineVals[1] = Integer.parseInt(line[12]);
            lineVals[2] = Integer.parseInt(line[18]);
            lineVals[3] = Integer.parseInt(line[21]);
            lineVals[4] = Integer.parseInt(line[27]);
            lineVals[5] = Integer.parseInt(line[30]);
            costs[i] = lineVals;
        }
        int[] bestValues = new int[input.length];
        for (int i = 0; i < 3; i++) {
            System.out.println("Line: " + i);
            HashMap<String, Integer> posByTime = new HashMap<>();
            ArrayList<int[]> stateQueue = new ArrayList<>();
            int[] initState = new int[] {0, 1, 0, 0, 0, 0, 0, 0, 0};
            posByTime.put("0,1,0,0,0,0,0,0", 0);
            stateQueue.add(initState);
            int maxOreNeeded = Math.max(costs[i][1], Math.max(costs[i][2], costs[i][4]));
            int maxClayNeeded = costs[i][3];
            int maxObsNeeded = costs[i][5];
            while (!stateQueue.isEmpty()) {
                int[] currentState = stateQueue.remove(0);
                int ore = currentState[0];
                int orePS = currentState[1];
                int clay = currentState[2];
                int clayPS = currentState[3];
                int obs = currentState[4];
                int obsPS = currentState[5];
                int geode = currentState[6];
                if (geode > 9) {
                    //System.out.println("something went wrong");
                }
                int geodePS = currentState[7];
                int time = currentState[8];
                String key = ore + "," + orePS + "," + clay + "," + clayPS + "," + obs + "," + obsPS + "," + geode + "," + geodePS;
                int maxExtraGeodesPossibleAtTime = 0;
                for (int m = 0; m < minutes - time; m++) {
                    maxExtraGeodesPossibleAtTime += m;
                }
                if (!posByTime.containsKey(key) || posByTime.get(key) <= time &&
                        geode + geodePS * (minutes - time) + maxExtraGeodesPossibleAtTime > bestValues[i]) {
                    posByTime.put(key, time);
                    if (time < minutes) {
                        boolean hasEnoughOreRobots = orePS >= maxOreNeeded;
                        boolean hasEnoughClayRobots = clayPS >= maxClayNeeded;
                        boolean hasEnoughObsRobots = obsPS >= maxObsNeeded;
                        int minutesToBuildNextGeodeRobot = obsPS > 0 ? Math.max((int) Math.ceil((costs[i][5] - obs) / (double) obsPS), Math.max((int) Math.ceil((costs[i][4] - ore) / (double) orePS), 0)) : minutes + 1;
                        int minutesToBuildNextObsRobot = clayPS > 0 ? Math.max((int) Math.ceil((costs[i][3] - clay) / (double) clayPS), Math.max((int) Math.ceil((costs[i][2] - ore) / (double) orePS), 0)) : minutes + 1;
                        int minutesToBuildNextClayRobot = Math.max((int) Math.ceil((costs[i][1] - ore) / (double) orePS), 0);
                        int minutesToBuildNextOreRobot = Math.max((int) Math.ceil((costs[i][0] - ore) / (double) orePS), 0);
                        boolean canBuildGeoRobotNow = minutesToBuildNextGeodeRobot == 0; //assumption that building a geo robot if possible rightaway is always optimal
                        //branch on what you will build next
                        if (minutesToBuildNextGeodeRobot + 1 <= (minutes - time)) {
                            //branch build geodeRobot
                            int newOre = ore - costs[i][4] + (minutesToBuildNextGeodeRobot + 1) * orePS;
                            int newOrePS = orePS;
                            int newClay = clay + (minutesToBuildNextGeodeRobot + 1) * clayPS;
                            int newClayPS = clayPS;
                            int newObs = obs - costs[i][5] + (minutesToBuildNextGeodeRobot + 1) * obsPS;
                            int newObsPS = obsPS;
                            int newGeode = geode + (minutesToBuildNextGeodeRobot + 1) * geodePS;
                            int newGeodePS = geodePS + 1;
                            int newTime = time + minutesToBuildNextGeodeRobot + 1;
                            stateQueue.add(new int[]{
                                    newOre,
                                    newOrePS,
                                    newClay,
                                    newClayPS,
                                    newObs,
                                    newObsPS,
                                    newGeode,
                                    newGeodePS,
                                    newTime});
                        }
                        if (!canBuildGeoRobotNow && !hasEnoughObsRobots && minutesToBuildNextObsRobot + 1 <= (minutes - time)) {
                            //branch build obsidianRobot
                            int newOre = ore - costs[i][2] + (minutesToBuildNextObsRobot + 1) * orePS;
                            int newOrePS = orePS;
                            int newClay = clay - costs[i][3] + (minutesToBuildNextObsRobot + 1) * clayPS;
                            int newClayPS = clayPS;
                            int newObs = obs + (minutesToBuildNextObsRobot + 1) * obsPS;
                            int newObsPS = obsPS + 1;
                            int newGeode = geode + (minutesToBuildNextObsRobot + 1) * geodePS;
                            int newGeodePS = geodePS;
                            int newTime = time + minutesToBuildNextObsRobot + 1;
                            stateQueue.add(new int[]{
                                    newOre,
                                    newOrePS,
                                    newClay,
                                    newClayPS,
                                    newObs,
                                    newObsPS,
                                    newGeode,
                                    newGeodePS,
                                    newTime});
                        }
                        if (!canBuildGeoRobotNow && !hasEnoughClayRobots && minutesToBuildNextClayRobot + 1 <= (minutes - time)) {
                            //branch build clayRobot
                            int newOre = ore - costs[i][1] + (minutesToBuildNextClayRobot + 1) * orePS;
                            int newOrePS = orePS;
                            int newClay = clay + (minutesToBuildNextClayRobot + 1) * clayPS;
                            int newClayPS = clayPS + 1;
                            int newObs = obs + (minutesToBuildNextClayRobot + 1) * obsPS;
                            int newObsPS = obsPS;
                            int newGeode = geode + (minutesToBuildNextClayRobot + 1) * geodePS;
                            int newGeodePS = geodePS;
                            int newTime = time + minutesToBuildNextClayRobot + 1;
                            stateQueue.add(new int[]{
                                    newOre,
                                    newOrePS,
                                    newClay,
                                    newClayPS,
                                    newObs,
                                    newObsPS,
                                    newGeode,
                                    newGeodePS,
                                    newTime});
                        }
                        if (!canBuildGeoRobotNow && !hasEnoughOreRobots && minutesToBuildNextOreRobot + 1 <= (minutes - time)) {
                            //branch build oreRobot
                            int newOre = ore - costs[i][0] + (minutesToBuildNextOreRobot + 1) * orePS;
                            int newOrePS = orePS + 1;
                            int newClay = clay + (minutesToBuildNextOreRobot + 1) * clayPS;
                            int newClayPS = clayPS;
                            int newObs = obs + (minutesToBuildNextOreRobot + 1) * obsPS;
                            int newObsPS = obsPS;
                            int newGeode = geode + (minutesToBuildNextOreRobot + 1) * geodePS;
                            int newGeodePS = geodePS;
                            int newTime = time + minutesToBuildNextOreRobot + 1;
                            stateQueue.add(new int[]{
                                    newOre,
                                    newOrePS,
                                    newClay,
                                    newClayPS,
                                    newObs,
                                    newObsPS,
                                    newGeode,
                                    newGeodePS,
                                    newTime});
                        }
                        if (!canBuildGeoRobotNow &&
                                (hasEnoughOreRobots || minutesToBuildNextOreRobot + 1 > (minutes - time)) &&
                                (hasEnoughClayRobots || minutesToBuildNextClayRobot + 1 > (minutes - time)) &&
                                (hasEnoughObsRobots || minutesToBuildNextObsRobot + 1 > (minutes - time))) {
                            int newOre = ore + (minutes - time) * orePS;
                            int newOrePS = orePS;
                            int newClay = clay + (minutes - time) * clayPS;
                            int newClayPS = clayPS;
                            int newObs = obs + (minutes - time) * obsPS;
                            int newObsPS = obsPS;
                            int newGeode = geode + (minutes - time) * geodePS;
                            int newGeodePS = geodePS;
                            int newTime = minutes;
                            stateQueue.add(new int[]{
                                    newOre,
                                    newOrePS,
                                    newClay,
                                    newClayPS,
                                    newObs,
                                    newObsPS,
                                    newGeode,
                                    newGeodePS,
                                    newTime});
                        }
                    } else {
                        bestValues[i] = Math.max(bestValues[i], geode);
                    }

                }
            }
        }
        int bestTotal = 1;
        for (int i = 0; i < 3; i++) {
            bestTotal *= bestValues[i];
        }
        System.out.println("bestTotal:");
        System.out.println(bestTotal);
    }
}
