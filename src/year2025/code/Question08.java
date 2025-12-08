package year2025.code;

import helpers.Helper;

import java.io.IOException;
import java.util.*;

public class Question08 {
    public static void main(String[] args) throws IOException {
        Q08Part1.run();
        Q08Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2025, 8);
    }
}

class Q08Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question08.getInput();
        HashMap<String, Double> distancePerPair = new HashMap<>();
        ArrayList<String> pairs = new ArrayList<>();
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                int[] nodeI = Helper.StringArrayToIntArray(input[i].split(","));
                int[] nodeJ = Helper.StringArrayToIntArray(input[j].split(","));
                distancePerPair.put(i + "," + j, Math.sqrt(Math.pow(nodeI[0] - nodeJ[0], 2) + Math.pow(nodeI[1] - nodeJ[1], 2) + Math.pow(nodeI[2] - nodeJ[2], 2)));
                pairs.add(i + "," + j);
            }
        }
        pairs.sort(Comparator.comparing(distancePerPair::get));
        HashMap<Integer, Integer> groupNumberForNodeNumber = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> nodeNumbersForGroupNumber = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            groupNumberForNodeNumber.put(i, i);
            HashSet<Integer> group = new HashSet<>();
            group.add(i);
            nodeNumbersForGroupNumber.put(i, group);
        }
        for (int c = 0; c < 1000; c++) {
            String pairString = pairs.get(c);
            int[] pair = Helper.StringArrayToIntArray(pairString.split(","));
            int groupNumberForNode1 = groupNumberForNodeNumber.get(pair[0]);
            int groupNumberForNode2 = groupNumberForNodeNumber.get(pair[1]);
            if (groupNumberForNode1 != groupNumberForNode2) {
                HashSet<Integer> nodeNumbersForGroup1 = nodeNumbersForGroupNumber.get(groupNumberForNode1);
                HashSet<Integer> nodeNumbersForGroup2 = nodeNumbersForGroupNumber.get(groupNumberForNode2);
                for (int nodeNumberInGroup2 : nodeNumbersForGroup2) {
                    groupNumberForNodeNumber.put(nodeNumberInGroup2, groupNumberForNode1);
                    nodeNumbersForGroup1.add(nodeNumberInGroup2);
                }
                nodeNumbersForGroupNumber.remove(groupNumberForNode2);
            }
        }
        ArrayList<Integer> groupSizes = new ArrayList<>();
        for (int key : nodeNumbersForGroupNumber.keySet()) {
            groupSizes.add(nodeNumbersForGroupNumber.get(key).size());
        }
        Collections.sort(groupSizes);
        Collections.reverse(groupSizes);
        System.out.println(groupSizes.get(0) * groupSizes.get(1) * groupSizes.get(2));
    }
}

class Q08Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question08.getInput();
        HashMap<String, Double> distancePerPair = new HashMap<>();
        ArrayList<String> pairs = new ArrayList<>();
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                int[] nodeI = Helper.StringArrayToIntArray(input[i].split(","));
                int[] nodeJ = Helper.StringArrayToIntArray(input[j].split(","));
                distancePerPair.put(i + "," + j, Math.sqrt(Math.pow(nodeI[0] - nodeJ[0], 2) + Math.pow(nodeI[1] - nodeJ[1], 2) + Math.pow(nodeI[2] - nodeJ[2], 2)));
                pairs.add(i + "," + j);
            }
        }
        pairs.sort(Comparator.comparing(distancePerPair::get));
        HashMap<Integer, Integer> groupNumberForNodeNumber = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> nodeNumbersForGroupNumber = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            groupNumberForNodeNumber.put(i, i);
            HashSet<Integer> group = new HashSet<>();
            group.add(i);
            nodeNumbersForGroupNumber.put(i, group);
        }
        for (int c = 0; c < pairs.size(); c++) {
            String pairString = pairs.get(c);
            int[] pair = Helper.StringArrayToIntArray(pairString.split(","));
            int groupNumberForNode1 = groupNumberForNodeNumber.get(pair[0]);
            int groupNumberForNode2 = groupNumberForNodeNumber.get(pair[1]);
            if (groupNumberForNode1 != groupNumberForNode2) {
                HashSet<Integer> nodeNumbersForGroup1 = nodeNumbersForGroupNumber.get(groupNumberForNode1);
                HashSet<Integer> nodeNumbersForGroup2 = nodeNumbersForGroupNumber.get(groupNumberForNode2);
                for (int nodeNumberInGroup2 : nodeNumbersForGroup2) {
                    groupNumberForNodeNumber.put(nodeNumberInGroup2, groupNumberForNode1);
                    nodeNumbersForGroup1.add(nodeNumberInGroup2);
                }
                nodeNumbersForGroupNumber.remove(groupNumberForNode2);
                if (nodeNumbersForGroupNumber.keySet().size() == 1) {
                    System.out.println(Integer.parseInt(input[pair[0]].split(",")[0]) * Integer.parseInt(input[pair[1]].split(",")[0]));
                    break;
                }
            }
        }
    }
}
