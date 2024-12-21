package year2024.code;

import helpers.Helper;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question21 {
    public static void main(String[] args) throws IOException {
        Q21Part1.run();
        Q21Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 21);
    }
}

class Q21Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    private static HashMap<String, Integer> shortestSequence = new HashMap<>();
    private static HashMap<String, Point> numCoors = new HashMap<>();
    private static HashMap<String, Point> dirCoors = new HashMap<>();

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question21.getInput();
        int count = 0;
        numCoors.put("A", new Point(2, 3));
        numCoors.put("0", new Point(1, 3));
        numCoors.put("1", new Point(0, 2));
        numCoors.put("2", new Point(1, 2));
        numCoors.put("3", new Point(2, 2));
        numCoors.put("4", new Point(0, 1));
        numCoors.put("5", new Point(1, 1));
        numCoors.put("6", new Point(2, 1));
        numCoors.put("7", new Point(0, 0));
        numCoors.put("8", new Point(1, 0));
        numCoors.put("9", new Point(2, 0));
        dirCoors.put(">", new Point(2, 1));
        dirCoors.put("v", new Point(1, 1));
        dirCoors.put("<", new Point(0, 1));
        dirCoors.put("^", new Point(1, 0));
        dirCoors.put("A", new Point(2, 0));
        for (String code : input) {
            int shortestSequence = 0;
            String startDial = "A";
            for (int i = 0; i < code.length(); i++) {
                String targetDial = code.substring(i, i + 1);
                shortestSequence += findShortestSequenceNumpad(startDial, targetDial);
                startDial = targetDial;
            }
            count += Integer.parseInt(code.substring(0, 3)) * shortestSequence;
        }
        System.out.println(count);
    }

    private static int findShortestSequenceNumpad(String startDial, String targetDial) {
        if (shortestSequence.containsKey(startDial + targetDial)) {
            return shortestSequence.get(startDial + targetDial);
        }
        Point startPos = numCoors.get(startDial);
        Point targetPos = numCoors.get(targetDial);
        ArrayList<Pair<Point, ArrayList<String>>> codeSequences = new ArrayList<>();
        codeSequences.add(Pair.of(startPos, new ArrayList<>()));
        ArrayList<Pair<Point, ArrayList<String>>> codeSequencesAtGoal = new ArrayList<>();
        while (!codeSequences.isEmpty()) {
            Pair<Point, ArrayList<String>> codeSequence = codeSequences.remove(0);
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if ((dx == 0 && dy != 0) || (dx != 0 && dy == 0)) {
                        ArrayList<String> newSequence = new ArrayList<>(codeSequence.getRight());
                        int nx = codeSequence.getLeft().x + dx;
                        int ny = codeSequence.getLeft().y + dy;
                        if (nx >= 0 && nx < 3 && ny >= 0 && ny < 4 && !(nx == 0 && ny == 3)) {
                            String newChar = dx == 1 ? ">" : dx == -1 ? "<" : dy == 1 ? "v" : "^";
                            newSequence.add(newChar);
                            if (nx == targetPos.x && ny == targetPos.y) {
                                newSequence.add("A");
                                codeSequencesAtGoal.add(Pair.of(new Point(nx, ny), newSequence));
                            } else {
                                if (codeSequencesAtGoal.isEmpty()) {
                                    codeSequences.add(Pair.of(new Point(nx, ny), newSequence));
                                }
                            }
                        }
                    }
                }
            }
        }
        int shortestSequenceLength = Integer.MAX_VALUE;
        for (Pair<Point, ArrayList<String>> codeSequence : codeSequencesAtGoal) {
            String startDialDirpad = "A";
            int pathLength = 0;
            for (String targetDialDirpad : codeSequence.getRight()) {
                pathLength += findShortestSequenceDirpad(startDialDirpad, targetDialDirpad, 0);
                startDialDirpad = targetDialDirpad;
            }
            shortestSequenceLength = Math.min(shortestSequenceLength, pathLength);
        }
        shortestSequence.put(startDial + targetDial, shortestSequenceLength);
        return shortestSequenceLength;
    }

    private static int findShortestSequenceDirpad(String startDial, String targetDial, int depth) {
        if (startDial.equals(targetDial)) {
            return 1;
        }
        if (shortestSequence.containsKey(depth + startDial + targetDial)) {
            return shortestSequence.get(depth + startDial + targetDial);
        }
        Point startPos = dirCoors.get(startDial);
        Point targetPos = dirCoors.get(targetDial);
        ArrayList<Pair<Point, ArrayList<String>>> codeSequences = new ArrayList<>();
        codeSequences.add(Pair.of(startPos, new ArrayList<>()));
        ArrayList<Pair<Point, ArrayList<String>>> codeSequencesAtGoal = new ArrayList<>();
        while (!codeSequences.isEmpty()) {
            Pair<Point, ArrayList<String>> codeSequence = codeSequences.remove(0);
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if ((dx == 0 && dy != 0) || (dx != 0 && dy == 0)) {
                        ArrayList<String> newSequence = new ArrayList<>(codeSequence.getRight());
                        int nx = codeSequence.getLeft().x + dx;
                        int ny = codeSequence.getLeft().y + dy;
                        if (nx >= 0 && nx < 3 && ny >= 0 && ny < 2 && !(nx == 0 && ny == 0)) {
                            String newChar = dx == 1 ? ">" : dx == -1 ? "<" : dy == 1 ? "v" : "^";
                            newSequence.add(newChar);
                            if (nx == targetPos.x && ny == targetPos.y) {
                                newSequence.add("A");
                                codeSequencesAtGoal.add(Pair.of(new Point(nx, ny), newSequence));
                            } else {
                                if (codeSequencesAtGoal.isEmpty()) {
                                    codeSequences.add(Pair.of(new Point(nx, ny), newSequence));
                                }
                            }
                        }
                    }
                }
            }
        }
        int shortestSequenceLength = Integer.MAX_VALUE;
        if (depth == 0) {
            for (Pair<Point, ArrayList<String>> codeSequence : codeSequencesAtGoal) {
                String startDialDirpad = "A";
                int pathLength = 0;
                for (String targetDialDirpad : codeSequence.getRight()) {
                    pathLength += findShortestSequenceDirpad(startDialDirpad, targetDialDirpad, 1);
                    startDialDirpad = targetDialDirpad;
                }
                shortestSequenceLength = Math.min(shortestSequenceLength, pathLength);
            }
        } else {
            shortestSequenceLength = codeSequencesAtGoal.get(0).getRight().size();
        }
        shortestSequence.put(depth + startDial + targetDial, shortestSequenceLength);
        return shortestSequenceLength;
    }
}

class Q21Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    private static HashMap<String, Long> shortestSequence = new HashMap<>();
    private static HashMap<String, Point> numCoors = new HashMap<>();
    private static HashMap<String, Point> dirCoors = new HashMap<>();

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question21.getInput();
        long count = 0;
        numCoors.put("A", new Point(2, 3));
        numCoors.put("0", new Point(1, 3));
        numCoors.put("1", new Point(0, 2));
        numCoors.put("2", new Point(1, 2));
        numCoors.put("3", new Point(2, 2));
        numCoors.put("4", new Point(0, 1));
        numCoors.put("5", new Point(1, 1));
        numCoors.put("6", new Point(2, 1));
        numCoors.put("7", new Point(0, 0));
        numCoors.put("8", new Point(1, 0));
        numCoors.put("9", new Point(2, 0));
        dirCoors.put(">", new Point(2, 1));
        dirCoors.put("v", new Point(1, 1));
        dirCoors.put("<", new Point(0, 1));
        dirCoors.put("^", new Point(1, 0));
        dirCoors.put("A", new Point(2, 0));
        for (String code : input) {
            long shortestSequence = 0;
            String startDial = "A";
            for (int i = 0; i < code.length(); i++) {
                String targetDial = code.substring(i, i + 1);
                shortestSequence += findShortestSequenceNumpad(startDial, targetDial);
                startDial = targetDial;
            }
            count += Long.parseLong(code.substring(0, 3)) * shortestSequence;
        }
        System.out.println(count);
    }

    private static long findShortestSequenceNumpad(String startDial, String targetDial) {
        if (shortestSequence.containsKey(startDial + targetDial)) {
            return shortestSequence.get(startDial + targetDial);
        }
        Point startPos = numCoors.get(startDial);
        Point targetPos = numCoors.get(targetDial);
        ArrayList<Pair<Point, ArrayList<String>>> codeSequences = new ArrayList<>();
        codeSequences.add(Pair.of(startPos, new ArrayList<>()));
        ArrayList<Pair<Point, ArrayList<String>>> codeSequencesAtGoal = new ArrayList<>();
        while (!codeSequences.isEmpty()) {
            Pair<Point, ArrayList<String>> codeSequence = codeSequences.remove(0);
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if ((dx == 0 && dy != 0) || (dx != 0 && dy == 0)) {
                        ArrayList<String> newSequence = new ArrayList<>(codeSequence.getRight());
                        int nx = codeSequence.getLeft().x + dx;
                        int ny = codeSequence.getLeft().y + dy;
                        if (nx >= 0 && nx < 3 && ny >= 0 && ny < 4 && !(nx == 0 && ny == 3)) {
                            String newChar = dx == 1 ? ">" : dx == -1 ? "<" : dy == 1 ? "v" : "^";
                            newSequence.add(newChar);
                            if (nx == targetPos.x && ny == targetPos.y) {
                                newSequence.add("A");
                                codeSequencesAtGoal.add(Pair.of(new Point(nx, ny), newSequence));
                            } else {
                                if (codeSequencesAtGoal.isEmpty()) {
                                    codeSequences.add(Pair.of(new Point(nx, ny), newSequence));
                                }
                            }
                        }
                    }
                }
            }
        }
        long shortestSequenceLength = Long.MAX_VALUE;
        for (Pair<Point, ArrayList<String>> codeSequence : codeSequencesAtGoal) {
            String startDialDirpad = "A";
            long pathLength = 0;
            for (String targetDialDirpad : codeSequence.getRight()) {
                pathLength += findShortestSequenceDirpad(startDialDirpad, targetDialDirpad, 0);
                startDialDirpad = targetDialDirpad;
            }
            shortestSequenceLength = Math.min(shortestSequenceLength, pathLength);
        }
        shortestSequence.put(startDial + targetDial, shortestSequenceLength);
        return shortestSequenceLength;
    }

    private static long findShortestSequenceDirpad(String startDial, String targetDial, int depth) {
        if (startDial.equals(targetDial)) {
            return 1;
        }
        if (shortestSequence.containsKey(depth + startDial + targetDial)) {
            return shortestSequence.get(depth + startDial + targetDial);
        }
        Point startPos = dirCoors.get(startDial);
        Point targetPos = dirCoors.get(targetDial);
        ArrayList<Pair<Point, ArrayList<String>>> codeSequences = new ArrayList<>();
        codeSequences.add(Pair.of(startPos, new ArrayList<>()));
        ArrayList<Pair<Point, ArrayList<String>>> codeSequencesAtGoal = new ArrayList<>();
        while (!codeSequences.isEmpty()) {
            Pair<Point, ArrayList<String>> codeSequence = codeSequences.remove(0);
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if ((dx == 0 && dy != 0) || (dx != 0 && dy == 0)) {
                        ArrayList<String> newSequence = new ArrayList<>(codeSequence.getRight());
                        int nx = codeSequence.getLeft().x + dx;
                        int ny = codeSequence.getLeft().y + dy;
                        if (nx >= 0 && nx < 3 && ny >= 0 && ny < 2 && !(nx == 0 && ny == 0)) {
                            String newChar = dx == 1 ? ">" : dx == -1 ? "<" : dy == 1 ? "v" : "^";
                            newSequence.add(newChar);
                            if (nx == targetPos.x && ny == targetPos.y) {
                                newSequence.add("A");
                                codeSequencesAtGoal.add(Pair.of(new Point(nx, ny), newSequence));
                            } else {
                                if (codeSequencesAtGoal.isEmpty()) {
                                    codeSequences.add(Pair.of(new Point(nx, ny), newSequence));
                                }
                            }
                        }
                    }
                }
            }
        }
        long shortestSequenceLength = Long.MAX_VALUE;
        if (depth != 24) {
            for (Pair<Point, ArrayList<String>> codeSequence : codeSequencesAtGoal) {
                String startDialDirpad = "A";
                long pathLength = 0;
                for (String targetDialDirpad : codeSequence.getRight()) {
                    pathLength += findShortestSequenceDirpad(startDialDirpad, targetDialDirpad, depth + 1);
                    startDialDirpad = targetDialDirpad;
                }
                shortestSequenceLength = Math.min(shortestSequenceLength, pathLength);
            }
        } else {
            shortestSequenceLength = codeSequencesAtGoal.get(0).getRight().size();
        }
        shortestSequence.put(depth + startDial + targetDial, shortestSequenceLength);
        return shortestSequenceLength;
    }
}