package year2023.code;

import helpers.Helper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Question19 {
    public static void main(String[] args) throws IOException {
        Q19Part1.run();
        Q19Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 19);
    }
}

class Q19Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question19.getInput();
        HashMap<String, ArrayList<String>> workFlows = new HashMap<>();
        ArrayList<int[]> parts = new ArrayList<>();
        int inp = 0;
        while (inp < input.length && !input[inp].equals("")) {
            String[] flowParts = input[inp].split("\\{");
            String[] removeBrace = flowParts[1].split("}");
            String[] flowSteps = removeBrace[0].split(",");
            ArrayList<String> steps = new ArrayList<>(Arrays.asList(flowSteps));
            workFlows.put(flowParts[0], steps);
            inp++;
        }
        inp++;
        while (inp < input.length) {
            String line = input[inp];
            String removedBracket = line.substring(1, line.length() - 1);
            String[] partParts = removedBracket.split(",");
            int[] partVals = new int[4];
            for (int i = 0; i < 4; i++) {
                partVals[i] = Integer.parseInt(partParts[i].split("=")[1]);
            }
            parts.add(partVals);
            inp++;
        }
        HashMap<String, Integer> mapLettersToIndex = new HashMap<>();
        mapLettersToIndex.put("x", 0);
        mapLettersToIndex.put("m", 1);
        mapLettersToIndex.put("a", 2);
        mapLettersToIndex.put("s", 3);
        ArrayList<int[]> doneParts = new ArrayList<>();
        for (int[] part : parts) {
            String curFlow = "in";
            cycle: while (true) {
                ArrayList<String> workFlow = workFlows.get(curFlow);
                boolean conditionTrue = false;
                for (int i = 0; i < workFlow.size() - 1; i++) {
                    String[] conditionAndTarget = workFlow.get(i).split(":");
                    if (conditionAndTarget[0].contains("<")) {
                        String[] partAndValue = conditionAndTarget[0].split("<");
                        int valueToCompareAgainst = Integer.parseInt(partAndValue[1]);
                        if (part[mapLettersToIndex.get(partAndValue[0])] < valueToCompareAgainst) {
                            conditionTrue = true;
                        }
                    } else {
                        String[] partAndValue = conditionAndTarget[0].split(">");
                        int valueToCompareAgainst = Integer.parseInt(partAndValue[1]);
                        if (part[mapLettersToIndex.get(partAndValue[0])] > valueToCompareAgainst) {
                            conditionTrue = true;
                        }
                    }
                    if (conditionTrue) {
                        if (conditionAndTarget[1].equals("A")) {
                            doneParts.add(part);
                            break cycle;
                        } else if (conditionAndTarget[1].equals("R")) {
                            break cycle;
                        } else {
                            curFlow = conditionAndTarget[1];
                            break;
                        }
                    }
                }
                if (!conditionTrue) {
                    if (workFlow.get(workFlow.size() - 1).equals("A")) {
                        doneParts.add(part);
                        break cycle;
                    } else if (workFlow.get(workFlow.size() - 1).equals("R")) {
                        break cycle;
                    } else {
                        curFlow = workFlow.get(workFlow.size() - 1);
                    }
                }
            }
        }
        int count = 0;
        for (int[] part : doneParts) {
            for (int val : part) {
                count += val;
            }
        }
        System.out.println(count);
    }
}

class Q19Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question19.getInput();
        HashMap<String, ArrayList<String>> workFlows = new HashMap<>();
        int inp = 0;
        while (inp < input.length && !input[inp].equals("")) {
            String[] flowParts = input[inp].split("\\{");
            String[] removeBrace = flowParts[1].split("}");
            String[] flowSteps = removeBrace[0].split(",");
            ArrayList<String> steps = new ArrayList<>(Arrays.asList(flowSteps));
            workFlows.put(flowParts[0], steps);
            inp++;
        }
        HashMap<String, Integer> mapLettersToIndex = new HashMap<>();
        mapLettersToIndex.put("x", 0);
        mapLettersToIndex.put("m", 1);
        mapLettersToIndex.put("a", 2);
        mapLettersToIndex.put("s", 3);
        ArrayList<Pair<String, Point[]>> possibilitiesQueue = new ArrayList<>();
        Point[] startRanges = new Point[4];
        for (int i = 0; i < 4; i++) {
            startRanges[i] = new Point(1, 4000);
        }
        possibilitiesQueue.add(new ImmutablePair<>("in", startRanges));
        ArrayList<Point[]> successRanges = new ArrayList<>();
        long rangeProduct = 0;
        long rejectedProduct = 0;
        while(!possibilitiesQueue.isEmpty()) {
            Pair<String, Point[]> possibility = possibilitiesQueue.remove(0);
            Point[] partRanges = possibility.getRight();
            ArrayList<String> workFlow = workFlows.get(possibility.getLeft());
            boolean rangeEmpty = false;
            for (int i = 0; i < workFlow.size() - 1; i++) {
                String[] conditionAndTarget = workFlow.get(i).split(":");
                String nextFlow = conditionAndTarget[1];
                if (conditionAndTarget[0].contains("<")) {
                    String[] partAndValue = conditionAndTarget[0].split("<");
                    int valueToCompareAgainst = Integer.parseInt(partAndValue[1]);
                    Point range = partRanges[mapLettersToIndex.get(partAndValue[0])];
                    if (range.x < valueToCompareAgainst) {
                        Point[] newPartRanges = partRanges.clone();
                        newPartRanges[mapLettersToIndex.get(partAndValue[0])] = new Point(range.x, Math.min(range.y, valueToCompareAgainst - 1));
                        if (nextFlow.equals("A")) {
                            rangeProduct += ((long) (newPartRanges[0].y - newPartRanges[0].x + 1)) *
                                            ((long) (newPartRanges[1].y - newPartRanges[1].x + 1)) *
                                            ((long) (newPartRanges[2].y - newPartRanges[2].x + 1)) *
                                            ((long) (newPartRanges[3].y - newPartRanges[3].x + 1));
                        } else if (!nextFlow.equals("R")) {
                            possibilitiesQueue.add(new ImmutablePair<>(nextFlow, newPartRanges));
                        } else {
                            rejectedProduct += ((long) (newPartRanges[0].y - newPartRanges[0].x + 1)) *
                                    ((long) (newPartRanges[1].y - newPartRanges[1].x + 1)) *
                                    ((long) (newPartRanges[2].y - newPartRanges[2].x + 1)) *
                                    ((long) (newPartRanges[3].y - newPartRanges[3].x + 1));
                        }
                        partRanges[mapLettersToIndex.get(partAndValue[0])] = new Point(valueToCompareAgainst, range.y);
                    }
                    if (partRanges[mapLettersToIndex.get(partAndValue[0])].x > partRanges[mapLettersToIndex.get(partAndValue[0])].y) {
                        rangeEmpty = true;
                        break;
                    }
                } else {
                    String[] partAndValue = conditionAndTarget[0].split(">");
                    int valueToCompareAgainst = Integer.parseInt(partAndValue[1]);
                    Point range = partRanges[mapLettersToIndex.get(partAndValue[0])];
                    if (range.y > valueToCompareAgainst) {
                        Point[] newPartRanges = partRanges.clone();
                        newPartRanges[mapLettersToIndex.get(partAndValue[0])] = new Point(Math.max(range.x, valueToCompareAgainst + 1), range.y);
                        if (nextFlow.equals("A")) {
                            rangeProduct += ((long) (newPartRanges[0].y - newPartRanges[0].x + 1)) *
                                    ((long) (newPartRanges[1].y - newPartRanges[1].x + 1)) *
                                    ((long) (newPartRanges[2].y - newPartRanges[2].x + 1)) *
                                    ((long) (newPartRanges[3].y - newPartRanges[3].x + 1));
                        } else if (!nextFlow.equals("R")) {
                            possibilitiesQueue.add(new ImmutablePair<>(nextFlow, newPartRanges));
                        } else {
                            rejectedProduct += ((long) (newPartRanges[0].y - newPartRanges[0].x + 1)) *
                                    ((long) (newPartRanges[1].y - newPartRanges[1].x + 1)) *
                                    ((long) (newPartRanges[2].y - newPartRanges[2].x + 1)) *
                                    ((long) (newPartRanges[3].y - newPartRanges[3].x + 1));
                        }
                        partRanges[mapLettersToIndex.get(partAndValue[0])] = new Point(range.x, valueToCompareAgainst);
                    }
                    if (partRanges[mapLettersToIndex.get(partAndValue[0])].x > partRanges[mapLettersToIndex.get(partAndValue[0])].y) {
                        rangeEmpty = true;
                        break;
                    }
                }
            }
            if (!rangeEmpty) {
                String nextFlow = workFlow.get(workFlow.size() - 1);
                if (nextFlow.equals("A")) {
                    rangeProduct += ((long) (partRanges[0].y - partRanges[0].x + 1)) *
                            ((long) (partRanges[1].y - partRanges[1].x + 1)) *
                            ((long) (partRanges[2].y - partRanges[2].x + 1)) *
                            ((long) (partRanges[3].y - partRanges[3].x + 1));
                } else if (!nextFlow.equals("R")) {
                    possibilitiesQueue.add(new ImmutablePair<>(nextFlow, partRanges));
                } else {
                    rejectedProduct += ((long) (partRanges[0].y - partRanges[0].x + 1)) *
                            ((long) (partRanges[1].y - partRanges[1].x + 1)) *
                            ((long) (partRanges[2].y - partRanges[2].x + 1)) *
                            ((long) (partRanges[3].y - partRanges[3].x + 1));
                }
            }
        }
        System.out.println(rangeProduct);
    }
}
