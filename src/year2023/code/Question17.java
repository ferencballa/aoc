package year2023.code;

import helpers.Helper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question17 {
    public static void main(String[] args) throws IOException {
        Q17Part1.run();
        Q17Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 17);
    }
}

class Q17Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question17.getInput();
        int[][] map = new int[input[0].length()][];
        for (int i = 0; i < map.length; i++) {
            map[i] = new int[input.length];
        }
        for (int i = 0; i < input.length; i++) {
            String[] line = input[i].split("");
            for (int j = 0; j < line.length; j++) {
                map[j][i] = Integer.parseInt(line[j]);
            }
        }
        HashMap<Point, HashMap<Point, Integer>> stepsToReach = new HashMap<>();
        /*
        dirs:
        0 = up
        1 = right
        2 = down
        3 = left
         */
        ArrayList<Pair<Point, Point>> pointsWithStepsInDirection = new ArrayList<>();
        pointsWithStepsInDirection.add(new ImmutablePair<>(new Point(0, 0), new Point(1, 0)));
        pointsWithStepsInDirection.add(new ImmutablePair<>(new Point(0, 0), new Point(2, 0)));
        HashMap<Point, Integer> startPoint = new HashMap<>();
        startPoint.put(new Point(1, 0), 0);
        startPoint.put(new Point(2, 0), 0);
        stepsToReach.put(new Point(0, 0), startPoint);
        while (!pointsWithStepsInDirection.isEmpty()) {
            int lowestCount = Integer.MAX_VALUE;
            if (stepsToReach.containsKey(new Point(map.length - 1, map[0].length - 1))) {
                HashMap<Point, Integer> endPoint = stepsToReach.get(new Point(map.length - 1, map[0].length - 1));
                for (Point key : endPoint.keySet()) {
                    lowestCount = Integer.min(lowestCount, endPoint.get(key));
                }
            }
            System.out.println(pointsWithStepsInDirection.size());
            Pair<Point, Point> currentPointAndDirection = pointsWithStepsInDirection.remove(0);
            int currentDistance = stepsToReach.get(currentPointAndDirection.getLeft()).get(currentPointAndDirection.getRight());
            if (currentPointAndDirection.getRight().x == 0) {
                if (currentPointAndDirection.getLeft().x > 0) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x - 1, currentPointAndDirection.getLeft().y), new Point(3, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().x < map.length - 1) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x + 1, currentPointAndDirection.getLeft().y), new Point(1, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().y > 0 && currentPointAndDirection.getRight().y < 3) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x, currentPointAndDirection.getLeft().y - 1), new Point(0, currentPointAndDirection.getRight().y + 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
            } else if (currentPointAndDirection.getRight().x == 1) {
                if (currentPointAndDirection.getLeft().y > 0) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x, currentPointAndDirection.getLeft().y - 1), new Point(0, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().y < map[0].length - 1) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x, currentPointAndDirection.getLeft().y + 1), new Point(2, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().x < map.length - 1 && currentPointAndDirection.getRight().y < 3) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x + 1, currentPointAndDirection.getLeft().y), new Point(1, currentPointAndDirection.getRight().y + 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
            } else if (currentPointAndDirection.getRight().x == 2) {
                if (currentPointAndDirection.getLeft().x > 0) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x - 1, currentPointAndDirection.getLeft().y), new Point(3, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().x < map.length - 1) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x + 1, currentPointAndDirection.getLeft().y), new Point(1, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().y < map[0].length - 1 && currentPointAndDirection.getRight().y < 3) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x, currentPointAndDirection.getLeft().y + 1), new Point(2, currentPointAndDirection.getRight().y + 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
            } else if (currentPointAndDirection.getRight().x == 3) {
                if (currentPointAndDirection.getLeft().y > 0) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x, currentPointAndDirection.getLeft().y - 1), new Point(0, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().y < map[0].length - 1) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x, currentPointAndDirection.getLeft().y + 1), new Point(2, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().x > 0 && currentPointAndDirection.getRight().y < 3) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x - 1, currentPointAndDirection.getLeft().y), new Point(3, currentPointAndDirection.getRight().y + 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
            }
        }
        int lowestCount = Integer.MAX_VALUE;
        HashMap<Point, Integer> endPoint = stepsToReach.get(new Point(map.length - 1, map[0].length - 1));
        for (Point key : endPoint.keySet()) {
            lowestCount = Integer.min(lowestCount, endPoint.get(key));
        }
        System.out.println(lowestCount);
    }

    private static void checkHashMapsAndPossiblyPutPointsInPlace(int[][] map, HashMap<Point, HashMap<Point, Integer>> stepsToReach, ArrayList<Pair<Point, Point>> pointsWithStepsInDirection, int currentDistance, Pair<Point, Point> newPointAndDirection, int lowestCount) {
        int newDistance = currentDistance + map[newPointAndDirection.getLeft().x][newPointAndDirection.getLeft().y];
        if (newDistance < lowestCount) {
            if (stepsToReach.containsKey(newPointAndDirection.getLeft())) {
                HashMap<Point, Integer> currentDistanceForDirection = stepsToReach.get(newPointAndDirection.getLeft());
                if (currentDistanceForDirection.containsKey(newPointAndDirection.getRight())) {
                    int oldDistance = currentDistanceForDirection.get(newPointAndDirection.getRight());
                    if (newDistance < oldDistance) {
                        putNewPointInPlace(map, stepsToReach, pointsWithStepsInDirection, newPointAndDirection, newDistance, currentDistanceForDirection);
                    }
                } else {
                    putNewPointInPlace(map, stepsToReach, pointsWithStepsInDirection, newPointAndDirection, newDistance, currentDistanceForDirection);
                }
            } else {
                HashMap<Point, Integer> newPoint = new HashMap<>();
                putNewPointInPlace(map, stepsToReach, pointsWithStepsInDirection, newPointAndDirection, newDistance, newPoint);
            }
        }
    }

    private static void putNewPointInPlace(int[][] map, HashMap<Point, HashMap<Point, Integer>> stepsToReach, ArrayList<Pair<Point, Point>> pointsWithStepsInDirection, Pair<Point, Point> newPointAndDirection, int newDistance, HashMap<Point, Integer> currentDistanceForDirection) {
        currentDistanceForDirection.put(newPointAndDirection.getRight(), newDistance);
        stepsToReach.put(newPointAndDirection.getLeft(), currentDistanceForDirection);
        if (newPointAndDirection.getLeft().x != map.length - 1 || newPointAndDirection.getLeft().y != map[0].length - 1) {
            int insertPoint = 0;
            while (newDistance < pointsWithStepsInDirection.size() - 1 && newDistance > stepsToReach.get(pointsWithStepsInDirection.get(insertPoint).getLeft()).get(pointsWithStepsInDirection.get(insertPoint).getRight())) {
                insertPoint++;
            }
            pointsWithStepsInDirection.add(insertPoint, newPointAndDirection);
        }
    }
}

class Q17Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question17.getInput();
        int[][] map = new int[input[0].length()][];
        for (int i = 0; i < map.length; i++) {
            map[i] = new int[input.length];
        }
        for (int i = 0; i < input.length; i++) {
            String[] line = input[i].split("");
            for (int j = 0; j < line.length; j++) {
                map[j][i] = Integer.parseInt(line[j]);
            }
        }
        HashMap<Point, HashMap<Point, Integer>> stepsToReach = new HashMap<>();
        /*
        dirs:
        0 = up
        1 = right
        2 = down
        3 = left
         */
        ArrayList<Pair<Point, Point>> pointsWithStepsInDirection = new ArrayList<>();
        pointsWithStepsInDirection.add(new ImmutablePair<>(new Point(0, 0), new Point(1, 0)));
        pointsWithStepsInDirection.add(new ImmutablePair<>(new Point(0, 0), new Point(2, 0)));
        HashMap<Point, Integer> startPoint = new HashMap<>();
        startPoint.put(new Point(1, 0), 0);
        startPoint.put(new Point(2, 0), 0);
        stepsToReach.put(new Point(0, 0), startPoint);
        while (!pointsWithStepsInDirection.isEmpty()) {
            int lowestCount = Integer.MAX_VALUE;
            if (stepsToReach.containsKey(new Point(map.length - 1, map[0].length - 1))) {
                HashMap<Point, Integer> endPoint = stepsToReach.get(new Point(map.length - 1, map[0].length - 1));
                for (Point key : endPoint.keySet()) {
                    lowestCount = Integer.min(lowestCount, endPoint.get(key));
                }
            }
            System.out.println(pointsWithStepsInDirection.size());
            Pair<Point, Point> currentPointAndDirection = pointsWithStepsInDirection.remove(0);
            int currentDistance = stepsToReach.get(currentPointAndDirection.getLeft()).get(currentPointAndDirection.getRight());
            if (currentPointAndDirection.getRight().x == 0) {
                if (currentPointAndDirection.getLeft().x > 0 && currentPointAndDirection.getRight().y >= 4) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x - 1, currentPointAndDirection.getLeft().y), new Point(3, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().x < map.length - 1 && currentPointAndDirection.getRight().y >= 4) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x + 1, currentPointAndDirection.getLeft().y), new Point(1, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().y > 0 && currentPointAndDirection.getRight().y < 10) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x, currentPointAndDirection.getLeft().y - 1), new Point(0, currentPointAndDirection.getRight().y + 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
            } else if (currentPointAndDirection.getRight().x == 1) {
                if (currentPointAndDirection.getLeft().y > 0 && currentPointAndDirection.getRight().y >= 4) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x, currentPointAndDirection.getLeft().y - 1), new Point(0, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().y < map[0].length - 1 && currentPointAndDirection.getRight().y >= 4) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x, currentPointAndDirection.getLeft().y + 1), new Point(2, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().x < map.length - 1 && currentPointAndDirection.getRight().y < 10) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x + 1, currentPointAndDirection.getLeft().y), new Point(1, currentPointAndDirection.getRight().y + 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
            } else if (currentPointAndDirection.getRight().x == 2) {
                if (currentPointAndDirection.getLeft().x > 0 && currentPointAndDirection.getRight().y >= 4) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x - 1, currentPointAndDirection.getLeft().y), new Point(3, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().x < map.length - 1 && currentPointAndDirection.getRight().y >= 4) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x + 1, currentPointAndDirection.getLeft().y), new Point(1, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().y < map[0].length - 1 && currentPointAndDirection.getRight().y < 10) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x, currentPointAndDirection.getLeft().y + 1), new Point(2, currentPointAndDirection.getRight().y + 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
            } else if (currentPointAndDirection.getRight().x == 3) {
                if (currentPointAndDirection.getLeft().y > 0 && currentPointAndDirection.getRight().y >= 4) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x, currentPointAndDirection.getLeft().y - 1), new Point(0, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().y < map[0].length - 1 && currentPointAndDirection.getRight().y >= 4) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x, currentPointAndDirection.getLeft().y + 1), new Point(2, 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
                if (currentPointAndDirection.getLeft().x > 0 && currentPointAndDirection.getRight().y < 10) {
                    Pair<Point, Point> newPointAndDirection = new ImmutablePair<>(new Point(currentPointAndDirection.getLeft().x - 1, currentPointAndDirection.getLeft().y), new Point(3, currentPointAndDirection.getRight().y + 1));
                    checkHashMapsAndPossiblyPutPointsInPlace(map, stepsToReach, pointsWithStepsInDirection, currentDistance, newPointAndDirection, lowestCount);
                }
            }
        }
        int lowestCount = Integer.MAX_VALUE;
        HashMap<Point, Integer> endPoint = stepsToReach.get(new Point(map.length - 1, map[0].length - 1));
        for (Point key : endPoint.keySet()) {
            lowestCount = Integer.min(lowestCount, endPoint.get(key));
        }
        System.out.println(lowestCount);
    }

    private static void checkHashMapsAndPossiblyPutPointsInPlace(int[][] map, HashMap<Point, HashMap<Point, Integer>> stepsToReach, ArrayList<Pair<Point, Point>> pointsWithStepsInDirection, int currentDistance, Pair<Point, Point> newPointAndDirection, int lowestCount) {
        int newDistance = currentDistance + map[newPointAndDirection.getLeft().x][newPointAndDirection.getLeft().y];
        if (newDistance < lowestCount) {
            if (stepsToReach.containsKey(newPointAndDirection.getLeft())) {
                HashMap<Point, Integer> currentDistanceForDirection = stepsToReach.get(newPointAndDirection.getLeft());
                if (currentDistanceForDirection.containsKey(newPointAndDirection.getRight())) {
                    int oldDistance = currentDistanceForDirection.get(newPointAndDirection.getRight());
                    if (newDistance < oldDistance) {
                        putNewPointInPlace(map, stepsToReach, pointsWithStepsInDirection, newPointAndDirection, newDistance, currentDistanceForDirection);
                    }
                } else {
                    putNewPointInPlace(map, stepsToReach, pointsWithStepsInDirection, newPointAndDirection, newDistance, currentDistanceForDirection);
                }
            } else {
                HashMap<Point, Integer> newPoint = new HashMap<>();
                putNewPointInPlace(map, stepsToReach, pointsWithStepsInDirection, newPointAndDirection, newDistance, newPoint);
            }
        }
    }

    private static void putNewPointInPlace(int[][] map, HashMap<Point, HashMap<Point, Integer>> stepsToReach, ArrayList<Pair<Point, Point>> pointsWithStepsInDirection, Pair<Point, Point> newPointAndDirection, int newDistance, HashMap<Point, Integer> currentDistanceForDirection) {
        if (newPointAndDirection.getLeft().x != map.length - 1 || newPointAndDirection.getLeft().y != map[0].length - 1) {
            currentDistanceForDirection.put(newPointAndDirection.getRight(), newDistance);
            stepsToReach.put(newPointAndDirection.getLeft(), currentDistanceForDirection);
            int insertPoint = 0;
            while (newDistance < pointsWithStepsInDirection.size() - 1 && newDistance > stepsToReach.get(pointsWithStepsInDirection.get(insertPoint).getLeft()).get(pointsWithStepsInDirection.get(insertPoint).getRight())) {
                insertPoint++;
            }
            pointsWithStepsInDirection.add(insertPoint, newPointAndDirection);
        } else {
            if (newPointAndDirection.getRight().y >= 4) {
                currentDistanceForDirection.put(newPointAndDirection.getRight(), newDistance);
                stepsToReach.put(newPointAndDirection.getLeft(), currentDistanceForDirection);
            }
        }
    }
}
