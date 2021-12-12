package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Question12 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/input/Question12.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        int countPaths = 0;
        HashMap<String, ArrayList<String>> connections = new HashMap<>();
        for (String inp : input) {
            String[] caves = inp.split("-");
            ArrayList<String> connectsTo1 = new ArrayList<>();
            if (connections.containsKey(caves[0])) {
                connectsTo1 = connections.get(caves[0]);
            }
            connectsTo1.add(caves[1]);
            connections.put(caves[0], connectsTo1);
            ArrayList<String> connectsTo2 = new ArrayList<>();
            if (connections.containsKey(caves[1])) {
                connectsTo2 = connections.get(caves[1]);
            }
            connectsTo2.add(caves[0]);
            connections.put(caves[1], connectsTo2);
        }
        ArrayList<PathProgress> queue = new ArrayList<>();
        HashSet<String> startVisited = new HashSet<>();
        startVisited.add("start");
        queue.add(new PathProgress("start", startVisited));
        while (!queue.isEmpty()) {
            PathProgress currentPathProgress = queue.remove(0);
            String curPos = currentPathProgress.currentPosition;
            if (curPos.equals("end")) {
                countPaths++;
            } else {
                ArrayList<String> curConnections = connections.get(currentPathProgress.currentPosition);
                for (String con : curConnections) {
                    if (!currentPathProgress.visited.contains(con) || !Character.isLowerCase(con.charAt(0))) {
                        HashSet<String> newVisited = new HashSet<>(currentPathProgress.visited);
                        if (Character.isLowerCase(con.charAt(0))) {
                            newVisited.add(con);
                        }
                        queue.add(new PathProgress(con, newVisited));
                    }
                }
            }
        }
        System.out.println(countPaths);
    }

    private static void part2(String[] input) {
        int countPaths = 0;
        HashSet<String> donePaths = new HashSet<>();
        HashMap<String, ArrayList<String>> connections = new HashMap<>();
        for (String inp : input) {
            String[] caves = inp.split("-");
            ArrayList<String> connectsTo1 = new ArrayList<>();
            if (connections.containsKey(caves[0])) {
                connectsTo1 = connections.get(caves[0]);
            }
            connectsTo1.add(caves[1]);
            connections.put(caves[0], connectsTo1);
            ArrayList<String> connectsTo2 = new ArrayList<>();
            if (connections.containsKey(caves[1])) {
                connectsTo2 = connections.get(caves[1]);
            }
            connectsTo2.add(caves[0]);
            connections.put(caves[1], connectsTo2);
        }
        ArrayList<PathProgress> queue = new ArrayList<>();
        HashSet<String> startVisited = new HashSet<>();
        startVisited.add("start");
        queue.add(new PathProgress("start", startVisited, false));
        while (!queue.isEmpty()) {
            PathProgress currentPathProgress = queue.remove(0);
            String curPos = currentPathProgress.currentPosition;
            if (curPos.equals("end")) {
                if (!donePaths.contains(currentPathProgress.pathSoFar)) { // prevents double identical paths which are a result of the twice split
                    countPaths++;
                    donePaths.add(currentPathProgress.pathSoFar);
                }
            } else {
                ArrayList<String> curConnections = connections.get(currentPathProgress.currentPosition);
                for (String con : curConnections) {
                    if (!currentPathProgress.visited.contains(con) || !Character.isLowerCase(con.charAt(0))) {
                        HashSet<String> newVisited = new HashSet<>(currentPathProgress.visited);
                        if (Character.isLowerCase(con.charAt(0))) {
                            if (currentPathProgress.twice || con.equals("end")) {
                                newVisited.add(con);
                                queue.add(new PathProgress(con, newVisited, currentPathProgress.twice, currentPathProgress.pathSoFar.concat("-" + con)));
                            } else {
                                HashSet<String> newVisited2 = new HashSet<>(newVisited);
                                newVisited.add(con);
                                queue.add(new PathProgress(con, newVisited, false, currentPathProgress.pathSoFar.concat("-" + con)));
                                queue.add(new PathProgress(con, newVisited2, true, currentPathProgress.pathSoFar.concat("-" + con)));
                            }
                        } else {
                            queue.add(new PathProgress(con, newVisited, currentPathProgress.twice, currentPathProgress.pathSoFar.concat("-" + con)));
                        }
                    }
                }
            }
        }
        System.out.println(countPaths);
    }
}

class PathProgress {
    String currentPosition;
    HashSet<String> visited;
    boolean twice;
    String pathSoFar = "start";

    public PathProgress(String c, HashSet<String> v) {
        currentPosition = c;
        visited = v;
    }

    public PathProgress(String c, HashSet<String> v, boolean t) {
        currentPosition = c;
        visited = v;
        twice = t;
    }

    public PathProgress(String c, HashSet<String> v, boolean t, String d) {
        currentPosition = c;
        visited = v;
        twice = t;
        pathSoFar = d;
    }
}
