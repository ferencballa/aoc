package year2025.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question11 {
    public static void main(String[] args) throws IOException {
        Q11Part1.run();
        Q11Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2025, 11);
    }
}

class Q11Part1 {

    private static HashMap<String, ArrayList<String>> outsPerNode = new HashMap<>();

    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question11.getInput();
        for (int i = 0; i < input.length; i++) {
            String[] vals = input[i].split(" ");
            ArrayList<String> outsForCur = new ArrayList<>();
            for (int v = 1; v < vals.length; v++) {
                outsForCur.add(vals[v]);
            }
            outsPerNode.put(vals[0].substring(0, vals[0].length() - 1), outsForCur);
        }
        System.out.println(countPathsToOut("you", new ArrayList<>()));
    }

    private static int countPathsToOut(String cur, ArrayList<String> prevNodes) {
        if (cur.equals("out")) {
            return 1;
        }
        int pathsToOut = 0;
        ArrayList<String> nextNodes = outsPerNode.get(cur);
        for (String node : nextNodes) {
            boolean visited = false;
            for (String prevNode : prevNodes) {
                if (node.equals(prevNode) || node.equals(cur)) {
                    visited = true;
                    break;
                }
            }
            if (!visited) {
                ArrayList<String> newPrevNodes = (ArrayList<String>) prevNodes.clone();
                newPrevNodes.add(cur);
                pathsToOut += countPathsToOut(node, newPrevNodes);
            }
        }
        return pathsToOut;
    }
}

class Q11Part2 {

    private static HashMap<String, ArrayList<String>> outsPerNode = new HashMap<>();

    private static HashMap<String, long[]> outsFurtherDown = new HashMap<>();

    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question11.getInput();
        for (int i = 0; i < input.length; i++) {
            String[] vals = input[i].split(" ");
            ArrayList<String> outsForCur = new ArrayList<>();
            for (int v = 1; v < vals.length; v++) {
                outsForCur.add(vals[v]);
            }
            outsPerNode.put(vals[0].substring(0, vals[0].length() - 1), outsForCur);
        }
        System.out.println(countPathsToOut("svr", new ArrayList<>())[0]);
    }

    private static long[] countPathsToOut(String cur, ArrayList<String> prevNodes) {
        if (cur.equals("out")) {
            long[] ret = new long[4];
            ret[3] = 1;
            return ret;
        }
        long[] pathsToOut = new long[4];
        ArrayList<String> nextNodes = outsPerNode.get(cur);
        for (String node : nextNodes) {
            boolean visited = false;
            for (String prevNode : prevNodes) {
                if (node.equals(prevNode) || node.equals(cur)) {
                    visited = true;
                    break;
                }
            }
            if (!visited) {
                if (outsFurtherDown.containsKey(node)) {
                    pathsToOut[0] += outsFurtherDown.get(node)[0];
                    pathsToOut[1] += outsFurtherDown.get(node)[1];
                    pathsToOut[2] += outsFurtherDown.get(node)[2];
                    pathsToOut[3] += outsFurtherDown.get(node)[3];
                } else {
                    ArrayList<String> newPrevNodes = (ArrayList<String>) prevNodes.clone();
                    newPrevNodes.add(cur);
                    long[] p = countPathsToOut(node, newPrevNodes);
                    pathsToOut[0] += p[0];
                    pathsToOut[1] += p[1];
                    pathsToOut[2] += p[2];
                    pathsToOut[3] += p[3];
                    outsFurtherDown.put(node, p);
                }
            }
        }
        if (cur.equals("dac")) {
            pathsToOut[1] += pathsToOut[3];
            pathsToOut[0] += pathsToOut[2];
            pathsToOut[3] = 0;
            pathsToOut[2] = 0;
        }
        if (cur.equals("fft")) {
            pathsToOut[2] += pathsToOut[3];
            pathsToOut[0] += pathsToOut[1];
            pathsToOut[3] = 0;
            pathsToOut[1] = 0;
        }
        return pathsToOut;
    }
}
