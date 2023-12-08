package year2023.code;

import helpers.Helper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question08 {
    public static void main(String[] args) throws IOException {
        Q08Part1.run();
        Q08Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 8);
    }
}

class Q08Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question08.getInput();
        String[] instructions = input[0].split("");
        HashMap<String, Pair<String, String>> nodes = new HashMap<>();
        for (int i = 2; i < input.length; i++) {
            String[] parts = input[i].split(" = \\(");
            String[] nextNodes = parts[1].split(", ");
            String[] rightNodesParts = nextNodes[1].split("\\)");
            nodes.put(parts[0], new ImmutablePair<>(nextNodes[0], rightNodesParts[0]));
        }
        String start = "AAA";
        String finish = "ZZZ";
        String cur = start;
        long steps = 0;
        int currentInstruction = 0;
        while (true) {
            steps++;
            Pair<String, String> node = nodes.get(cur);
            if (instructions[currentInstruction].equals("L")) {
                if (node.getLeft().equals(finish)) {
                    break;
                } else {
                    cur = node.getLeft();
                }
            } else {
                if (node.getRight().equals(finish)) {
                    break;
                } else {
                    cur = node.getRight();
                }
            }
            currentInstruction++;
            if (currentInstruction == instructions.length) {
                currentInstruction = 0;
            }
        }
        System.out.println(steps);
    }
}

class Q08Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question08.getInput();
        String[] instructions = input[0].split("");
        HashMap<String, Pair<String, String>> nodes = new HashMap<>();
        ArrayList<String> curs = new ArrayList<>();
        ArrayList<String> finishes = new ArrayList<>();
        for (int i = 2; i < input.length; i++) {
            String[] parts = input[i].split(" = \\(");
            String[] nextNodes = parts[1].split(", ");
            String[] rightNodesParts = nextNodes[1].split("\\)");
            nodes.put(parts[0], new ImmutablePair<>(nextNodes[0], rightNodesParts[0]));
            if (parts[0].endsWith("A")) {
                curs.add(parts[0]);
            }
            if (parts[0].endsWith("Z")) {
                finishes.add(parts[0]);
            }
        }
        String[] cursArray = new String[curs.size()];
        for (int i = 0; i < curs.size(); i++) {
            cursArray[i] = curs.get(i);
        }
        long steps = 0;
        int currentInstruction = 0;
        boolean done = false;
        long[] firstDoneSteps = new long[cursArray.length];
        while (!done) {
            steps++;
            done = true;
            for (int i = 0; i < cursArray.length; i++) {
                String cur = cursArray[i];
                Pair<String, String> node = nodes.get(cur);
                if (instructions[currentInstruction].equals("L")) {
                    cur = node.getLeft();
                    cursArray[i] = cur;
                    if (!finishes.contains(node.getLeft())) {
                        if (firstDoneSteps[i] == 0) {
                            done = false;
                        }
                    } else {
                        if (firstDoneSteps[i] == 0) {
                            firstDoneSteps[i] = steps;
                        }
                    }
                } else {
                    cur = node.getRight();
                    cursArray[i] = cur;
                    if (!finishes.contains(node.getRight())) {
                        if (firstDoneSteps[i] == 0) {
                            done = false;
                        }
                    } else {
                        if (firstDoneSteps[i] == 0) {
                            firstDoneSteps[i] = steps;
                        }
                    }
                }
            }
            currentInstruction++;
            if (currentInstruction == instructions.length) {
                currentInstruction = 0;
            }
        }
        long finalVal = firstDoneSteps[0];
        for (int i = 1; i < firstDoneSteps.length; i++) {
            long gcd = greatestCommonDenominator(finalVal, firstDoneSteps[i]);
            finalVal /= gcd;
            finalVal *= firstDoneSteps[i];
        }
        System.out.println(finalVal);
    }

    private static long greatestCommonDenominator(long a, long b) {
        if (b == 0) {
            return a;
        }
        return greatestCommonDenominator(b, a % b);
    }
}
