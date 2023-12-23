package year2023.code;

import helpers.Helper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question23 {
    public static void main(String[] args) throws IOException {
        Q23Part1.run();
        Q23Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 23);
    }
}

class Q23Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question23.getInput();
        char[][] map = new char[input[0].length()][];
        for (int i = 0; i < map.length; i++) {
            map[i] = new char[input.length];
        }
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                map[x][y] = input[y].charAt(x);
            }
        }
        int longestRoute = 0;
        ArrayList<Pair<Point, boolean[][]>> posAndVisitedQueue = new ArrayList<>();
        HashMap<Pair<Point, boolean[][]>, Integer> distanceForPosAndVisisted = new HashMap<>();
        boolean[][] startVisited = new boolean[map.length][];
        for (int i = 0; i < startVisited.length; i++) {
            startVisited[i] = new boolean[map[i].length];
        }
        startVisited[1][0] = true;
        Pair<Point, boolean[][]> startPosAndVisited = new ImmutablePair<>(new Point(1, 0), startVisited);
        posAndVisitedQueue.add(startPosAndVisited);
        distanceForPosAndVisisted.put(startPosAndVisited, 0);
        //debug list:
        ArrayList<Integer> routeLengths = new ArrayList<>();
        while (!posAndVisitedQueue.isEmpty()) {
            Pair<Point, boolean[][]> posAndVisited = posAndVisitedQueue.remove(0);
            Point coors = posAndVisited.getLeft();
            int x = coors.x;
            int y = coors.y;
            boolean[][] visited = posAndVisited.getRight();
            int distance = distanceForPosAndVisisted.get(posAndVisited);
            boolean noSplit = true;
            boolean noPath = false;
            boolean reachedFinish = false;
            while (noSplit && !noPath && !reachedFinish) {
                distance++;
                ArrayList<Pair<Point, boolean[][]>> nextPosAndVisitedList = new ArrayList<>();
                if (y > 0 && !visited[x][y-1] && (map[x][y-1]=='.' || map[x][y-1]=='^')) {
                    boolean[][] newVisited = new boolean[visited.length][];
                    for (int i = 0; i < newVisited.length; i++) {
                        newVisited[i] = visited[i].clone();
                    }
                    newVisited[x][y-1] = true;
                    nextPosAndVisitedList.add(new ImmutablePair<>(new Point(x, y-1), newVisited));
                }
                if (x < map.length - 1 && !visited[x+1][y] && (map[x+1][y]=='.' || map[x+1][y]=='>')) {
                    boolean[][] newVisited = new boolean[visited.length][];
                    for (int i = 0; i < newVisited.length; i++) {
                        newVisited[i] = visited[i].clone();
                    }
                    newVisited[x+1][y] = true;
                    nextPosAndVisitedList.add(new ImmutablePair<>(new Point(x+1, y), newVisited));
                }
                if (y < map[0].length - 1 && !visited[x][y+1] && (map[x][y+1]=='.' || map[x][y+1]=='v')) {
                    boolean[][] newVisited = new boolean[visited.length][];
                    for (int i = 0; i < newVisited.length; i++) {
                        newVisited[i] = visited[i].clone();
                    }
                    newVisited[x][y+1] = true;
                    nextPosAndVisitedList.add(new ImmutablePair<>(new Point(x, y+1), newVisited));
                }
                if (x > 0 && !visited[x-1][y] && (map[x-1][y]=='.' || map[x-1][y]=='<')) {
                    boolean[][] newVisited = new boolean[visited.length][];
                    for (int i = 0; i < newVisited.length; i++) {
                        newVisited[i] = visited[i].clone();
                    }
                    newVisited[x-1][y] = true;
                    nextPosAndVisitedList.add(new ImmutablePair<>(new Point(x-1, y), newVisited));
                }
                if (nextPosAndVisitedList.size() == 0) {
                    noPath = true;
                } else if (nextPosAndVisitedList.size() > 1) {
                    noSplit = false;
                    for (Pair<Point, boolean[][]> nextPosAndVisited : nextPosAndVisitedList) {
                        posAndVisitedQueue.add(nextPosAndVisited);
                        distanceForPosAndVisisted.put(nextPosAndVisited, distance);
                    }
                } else if (nextPosAndVisitedList.get(0).getLeft().x == map.length - 2 && nextPosAndVisitedList.get(0).getLeft().y == map[0].length - 1) {
                    reachedFinish = true;
                    longestRoute = Math.max(longestRoute, distance);
                    routeLengths.add(distance);
                } else {
                    visited = nextPosAndVisitedList.get(0).getRight();
                    x = nextPosAndVisitedList.get(0).getLeft().x;
                    y = nextPosAndVisitedList.get(0).getLeft().y;
                }
            }
        }
        System.out.println(routeLengths);
        System.out.println(longestRoute);
    }
}

class Q23Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question23.getInput();
        char[][] map = new char[input[0].length()][];
        for (int i = 0; i < map.length; i++) {
            map[i] = new char[input.length];
        }
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                map[x][y] = input[y].charAt(x);
            }
        }
        ArrayList<Point> nodes = new ArrayList<>();
        nodes.add(new Point(1, 0));
        nodes.add(new Point(map.length - 2, map[0].length - 1));
        for (int x = 1; x < map.length - 1; x++) {
            for (int y = 1; y < map.length - 1; y++) {
                int walls = 0;
                if (map[x-1][y] == '#') {
                    walls++;
                }
                if (map[x][y+1] == '#') {
                    walls++;
                }
                if (map[x+1][y] == '#') {
                    walls++;
                }
                if (map[x][y-1] == '#') {
                    walls++;
                }
                if (walls != 2) {
                    nodes.add(new Point(x, y));
                }
            }
        }
        HashMap<Point, ArrayList<Pair<Point, Integer>>> pointsWithAdjacentNodes = new HashMap<>();
        for (Point node : nodes) {
            ArrayList<Point> startDirections = new ArrayList<>();
            if (map[node.x-1][node.y] != '#') {
                startDirections.add(new Point(node.x-1, node.y));
            }
            if (node.y < map[0].length - 1 && map[node.x][node.y+1] != '#') {
                startDirections.add(new Point(node.x, node.y+1));
            }
            if (map[node.x+1][node.y] != '#') {
                startDirections.add(new Point(node.x+1, node.y));
            }
            if (node.y > 0 && map[node.x][node.y-1] != '#') {
                startDirections.add(new Point(node.x, node.y-1));
            }
            ArrayList<Pair<Point, Integer>> adjacentNodes = new ArrayList<>();
            for (Point coor : startDirections) {
                Point prev = new Point(node.x, node.y);
                int distance = 0;
                while (true) {
                    distance++;
                    if ((coor.x == 1 && coor.y == 0) || (coor.x == map.length - 2 && coor.y == map[0].length - 1)) {
                        break;
                    }
                    ArrayList<Point> possibilities = new ArrayList<>();
                    if (!(coor.x-1 == prev.x && coor.y == prev.y) && map[coor.x-1][coor.y] != '#') {
                        possibilities.add(new Point(coor.x-1, coor.y));
                    }
                    if (!(coor.x == prev.x && coor.y+1 == prev.y) && map[coor.x][coor.y+1] != '#') {
                        possibilities.add(new Point(coor.x, coor.y+1));
                    }
                    if (!(coor.x+1 == prev.x && coor.y == prev.y) && map[coor.x+1][coor.y] != '#') {
                        possibilities.add(new Point(coor.x+1, coor.y));
                    }
                    if (!(coor.x == prev.x && coor.y-1 == prev.y) && map[coor.x][coor.y-1] != '#') {
                        possibilities.add(new Point(coor.x, coor.y-1));
                    }
                    if (possibilities.size() > 1) {
                        break;
                    } else {
                        prev = new Point(coor.x, coor.y);
                        coor = new Point(possibilities.get(0).x, possibilities.get(0).y);
                    }
                }
                adjacentNodes.add(new ImmutablePair<>(coor, distance));
            }
            pointsWithAdjacentNodes.put(node, adjacentNodes);
        }
        ArrayList<Point> startVisited = new ArrayList<>();
        startVisited.add(new Point(1, 0));
        System.out.println(searchForEnd(new Point(1, 0), 0, pointsWithAdjacentNodes, startVisited, map.length - 2, map[0].length - 1));
    }

    private static int searchForEnd(Point currentNode, int currentDistance, HashMap<Point, ArrayList<Pair<Point, Integer>>> pointsWithAdjacentNodes, ArrayList<Point> visitedNodes, int finalX, int finalY) {
        if (currentNode.x == finalX && currentNode.y == finalY) {
            return currentDistance;
        } else {
            ArrayList<Pair<Point, Integer>> adjacentNodes = pointsWithAdjacentNodes.get(currentNode);
            int longestDistance = 0;
            for (Pair<Point, Integer> nodeWithDistance : adjacentNodes) {
                if (!visitedNodes.contains(nodeWithDistance.getLeft())) {
                    ArrayList<Point> newVisitedNodes = new ArrayList<>(visitedNodes);
                    newVisitedNodes.add(nodeWithDistance.getLeft());
                    longestDistance = Math.max(longestDistance, searchForEnd(nodeWithDistance.getLeft(), currentDistance + nodeWithDistance.getRight(), pointsWithAdjacentNodes, newVisitedNodes, finalX, finalY));
                }
            }
            return longestDistance;
        }
    }
}
