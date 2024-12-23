package year2024.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Question23 {
    public static void main(String[] args) throws IOException {
        Q23Part1.run();
        Q23Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 23);
    }
}

class Q23Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question23.getInput();
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            String[] parts1 = input[i].split("-");
            for (int j = i + 1; j < input.length; j++) {
                String[] parts2 = input[j].split("-");
                int p1Index = -1;
                int p2Index = -1;
                if (parts1[0].equals(parts2[0])) {
                    p1Index = 1;
                    p2Index = 1;
                } else if (parts1[0].equals(parts2[1])) {
                    p1Index = 1;
                    p2Index = 0;
                } else if (parts1[1].equals(parts2[0])) {
                    p1Index = 0;
                    p2Index = 1;
                } else if (parts1[1].equals(parts2[1])) {
                    p1Index = 0;
                    p2Index = 0;
                }
                if (p1Index != -1) {
                    for (int k = j + 1; k < input.length; k++) {
                        String[] parts3 = input[k].split("-");
                        if ((parts3[0].equals(parts1[p1Index]) && parts3[1].equals(parts2[p2Index])) || (parts3[1].equals(parts1[p1Index]) && parts3[0].equals(parts2[p2Index]))) {
                            if (parts1[p1Index].startsWith("t") || parts2[p2Index].startsWith("t") || parts1[Math.abs(1 - p1Index)].startsWith("t")) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}

class Q23Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    private static HashMap<String, ArrayList<String>> connectionsForPc = new HashMap<>();
    private static HashMap<String, ArrayList<String>> largestConnectionForSet = new HashMap<>();

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question23.getInput();
        for (String s : input) {
            String[] parts = s.split("-");
            if (connectionsForPc.containsKey(parts[0])) {
                ArrayList<String> list = connectionsForPc.get(parts[0]);
                list.add(parts[1]);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(parts[1]);
                connectionsForPc.put(parts[0], list);
            }
            if (connectionsForPc.containsKey(parts[1])) {
                ArrayList<String> list = connectionsForPc.get(parts[1]);
                list.add(parts[0]);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(parts[0]);
                connectionsForPc.put(parts[1], list);
            }
        }
        int largestGroupSize = 0;
        ArrayList<String> largestGroup = new ArrayList<>();
        for (String key : connectionsForPc.keySet()) {
            ArrayList<String> neighbours = connectionsForPc.get(key);
            Collections.sort(neighbours);
        }
        for (String key : connectionsForPc.keySet()) {
            ArrayList<String> neighbours = connectionsForPc.get(key);
            ArrayList<String> curLargestGroup = new ArrayList<>(getLargestGroup(key, neighbours));
            if (curLargestGroup.size() + 1 > largestGroupSize) {
                largestGroupSize = curLargestGroup.size() + 1;
                curLargestGroup.add(key);
                largestGroup = curLargestGroup;
            }
        }
        Collections.sort(largestGroup);
        System.out.println(String.join(",", largestGroup));
    }

    private static ArrayList<String> getLargestGroup(String key, ArrayList<String> neighboursToCheck) {
        ArrayList<String> neighbours = new ArrayList<>();
        ArrayList<String> neighboursOfKey = connectionsForPc.get(key);
        for (String neighbourToCheck : neighboursToCheck) {
            if (neighboursOfKey.contains(neighbourToCheck)) {
                neighbours.add(neighbourToCheck);
            }
        }
        ArrayList<String> newNeighbours = new ArrayList<>();
        if (largestConnectionForSet.containsKey(String.join(",", neighbours))) {
            newNeighbours.addAll(largestConnectionForSet.get(String.join(",", neighbours)));
            return newNeighbours;
        }
        if (neighbours.size() == 1) {
            newNeighbours.add(neighbours.get(0));
        } else if (neighbours.size() > 1) {
            int largestGroupSize = 0;
            ArrayList<String> largestGroup = new ArrayList<>();
            for (int i = 0; i < neighbours.size(); i++) {
                ArrayList<String> neighboursWithoutNewKey = new ArrayList<>();
                for (int j = 0; j < neighbours.size(); j++) {
                    if (i != j) {
                        neighboursWithoutNewKey.add(neighbours.get(j));
                    }
                }
                ArrayList<String> curLargestGroup = new ArrayList<>(getLargestGroup(neighbours.get(i), neighboursWithoutNewKey));
                if (curLargestGroup.size() + 1 > largestGroupSize) {
                    largestGroupSize = curLargestGroup.size() + 1;
                    curLargestGroup.add(neighbours.get(i));
                    largestGroup = curLargestGroup;
                }
            }
            newNeighbours.addAll(largestGroup);
        }
        largestConnectionForSet.put(String.join(",", neighbours), newNeighbours);
        return newNeighbours;
    }
}
