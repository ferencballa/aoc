package year2022.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Question24 {
    public static void main(String[] args) throws IOException {
        Point p = new Point(0, 0);
        HashSet<Point> hs = new HashSet<>();
        hs.add(p);
        Point anotherP = new Point(0, 0);
        System.out.println(hs.contains(anotherP));
        PointAtTime pat = new PointAtTime(new Point(0, 0), 0);
        HashSet<PointAtTime> hspat = new HashSet<>();
        hspat.add(pat);
        PointAtTime anotherPat = new PointAtTime(new Point(0, 0), 0);
        System.out.println(hspat.contains(anotherPat));
        System.out.println(p == anotherP);
        System.out.println(p.equals(anotherP));
        System.out.println(pat == anotherPat);
        System.out.println(pat.equals(anotherPat));

        //Q24Part1.run();
        //Q24Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 24);
    }
}

class Q24Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question24.getInput();
        int inputWidth = input[0].length();
        int inputHeight = input.length;
        HashMap<Integer, HashSet<Point>> occupiedPointPerTime = new HashMap<>();
        int maxTimeAttempt = 1000;
        HashSet<Point> walls = new HashSet<>();
        for (int i = 0; i < input.length; i++) {
            walls.add(new Point(0, i));
            walls.add(new Point(input[0].length() - 1, i));
        }
        for (int i = 0; i < input[0].length(); i++) {
            if (i != 1) {
                walls.add(new Point(i, 0));
            }
            if (i != input[0].length() - 2) {
                walls.add(new Point(i, input.length - 1));
            }
        }
        occupiedPointPerTime.put(0, walls);
        HashSet<Blizzard> blizzards = new HashSet<>();
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                if (input[y].charAt(x) == '>') {
                    blizzards.add(new Blizzard(new Point(x, y), 0));
                } else if (input[y].charAt(x) == 'v') {
                    blizzards.add(new Blizzard(new Point(x, y), 1));
                } else if (input[y].charAt(x) == '<') {
                    blizzards.add(new Blizzard(new Point(x, y), 2));
                } else if (input[y].charAt(x) == '^') {
                    blizzards.add(new Blizzard(new Point(x, y), 3));
                }
            }
        }
        int forceOff = 0;
        for (int i = 1; i < maxTimeAttempt; i++) {
            HashSet<Point> occupiedPoint = new HashSet<>(walls);
            if (i > forceOff) {
                occupiedPoint.add(new Point(1, 0)); //after x minutes, force it off the starting point
            }
            for (Blizzard blizzard : blizzards) {
                int x = blizzard.location.x;
                int y = blizzard.location.y;
                if (blizzard.dir == 0) {
                    x++;
                    if (x > inputWidth - 2) {
                        x = 1;
                    }
                    blizzard.location.x = x;
                    occupiedPoint.add(new Point(x, y));
                } else if (blizzard.dir == 1) {
                    y++;
                    if (y > inputHeight - 2) {
                        y = 1;
                    }
                    blizzard.location.y = y;
                    occupiedPoint.add(new Point(x, y));
                } else if (blizzard.dir == 2) {
                    x--;
                    if (x < 1) {
                        x = inputWidth - 2;
                    }
                    blizzard.location.x = x;
                    occupiedPoint.add(new Point(x, y));
                } else {
                    y--;
                    if (y < 1) {
                        y = inputHeight - 2;
                    }
                    blizzard.location.y = y;
                    occupiedPoint.add(new Point(x, y));
                }
            }
            occupiedPointPerTime.put(i, occupiedPoint);
        }
        //BFS
        HashMap<Integer, HashSet<Point>> visited = new HashMap<>();
        for (int i = 0; i < maxTimeAttempt; i++) {
            visited.put(i, new HashSet<>());
        }
        ArrayList<PointAtTime> queue = new ArrayList<>();
        PointAtTime start = new PointAtTime(new Point(1, 0), 0);
        queue.add(start);
        boolean goalFound = false;
        int minMoves = -1;
        int debugTime = 0;
        while (!queue.isEmpty() && !goalFound) {
            PointAtTime cur = queue.remove(0);
            if (cur.time < maxTimeAttempt - 1) {
                if (cur.time > debugTime) {
                    debugTime = cur.time;
                    System.out.println(debugTime);
                }
                ArrayList<Point> possibleMoves = new ArrayList<>();
                possibleMoves.add(new Point(cur.location.x, cur.location.y));
                possibleMoves.add(new Point(cur.location.x + 1, cur.location.y));
                possibleMoves.add(new Point(cur.location.x - 1, cur.location.y));
                possibleMoves.add(new Point(cur.location.x, cur.location.y + 1));
                possibleMoves.add(new Point(cur.location.x, cur.location.y - 1));
                for (Point p : possibleMoves) {
                    if (p.x == inputWidth - 2 && p.y == inputHeight - 1) {
                        goalFound = true;
                        minMoves = cur.time + 1;
                        break;
                    } else {
                        if (p.y >= 0 && !visited.get(cur.time + 1).contains(p) &&
                                !occupiedPointPerTime.get(cur.time + 1).contains(p)) {
                            visited.get(cur.time + 1).add(p);
                            queue.add(new PointAtTime(p, cur.time + 1));
                        }
                    }
                }
            }
        }
        System.out.println(minMoves);
    }
}

class Blizzard {
    Point location;
    int dir;

    Blizzard(Point l, int d) {
        location = l;
        dir = d;
    }
}

class PointAtTime {
    Point location;
    int time;

    PointAtTime(Point l, int t) {
        location = l;
        time = t;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PointAtTime)) {
            return false;
        }
        PointAtTime p = (PointAtTime) o;
        return this.time == p.time && this.location.equals(p.location);
    }
}

class Q24Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question24.getInput();
        int inputWidth = input[0].length();
        int inputHeight = input.length;
        HashMap<Integer, HashSet<Point>> occupiedPointPerTime = new HashMap<>();
        int maxTimeAttempt = 1000;
        HashSet<Point> walls = new HashSet<>();
        for (int i = 0; i < input.length; i++) {
            walls.add(new Point(0, i));
            walls.add(new Point(input[0].length() - 1, i));
        }
        for (int i = 0; i < input[0].length(); i++) {
            if (i != 1) {
                walls.add(new Point(i, 0));
            }
            if (i != input[0].length() - 2) {
                walls.add(new Point(i, input.length - 1));
            }
        }
        occupiedPointPerTime.put(0, walls);
        HashSet<Blizzard> blizzards = new HashSet<>();
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                if (input[y].charAt(x) == '>') {
                    blizzards.add(new Blizzard(new Point(x, y), 0));
                } else if (input[y].charAt(x) == 'v') {
                    blizzards.add(new Blizzard(new Point(x, y), 1));
                } else if (input[y].charAt(x) == '<') {
                    blizzards.add(new Blizzard(new Point(x, y), 2));
                } else if (input[y].charAt(x) == '^') {
                    blizzards.add(new Blizzard(new Point(x, y), 3));
                }
            }
        }
        int forceOff = 0;
        for (int i = 1; i < maxTimeAttempt; i++) {
            HashSet<Point> occupiedPoint = new HashSet<>(walls);
            for (Blizzard blizzard : blizzards) {
                int x = blizzard.location.x;
                int y = blizzard.location.y;
                if (blizzard.dir == 0) {
                    x++;
                    if (x > inputWidth - 2) {
                        x = 1;
                    }
                    blizzard.location.x = x;
                    occupiedPoint.add(new Point(x, y));
                } else if (blizzard.dir == 1) {
                    y++;
                    if (y > inputHeight - 2) {
                        y = 1;
                    }
                    blizzard.location.y = y;
                    occupiedPoint.add(new Point(x, y));
                } else if (blizzard.dir == 2) {
                    x--;
                    if (x < 1) {
                        x = inputWidth - 2;
                    }
                    blizzard.location.x = x;
                    occupiedPoint.add(new Point(x, y));
                } else {
                    y--;
                    if (y < 1) {
                        y = inputHeight - 2;
                    }
                    blizzard.location.y = y;
                    occupiedPoint.add(new Point(x, y));
                }
            }
            occupiedPointPerTime.put(i, occupiedPoint);
        }
        //BFS
        HashMap<Integer, HashSet<Point>> visited = new HashMap<>();
        for (int i = 0; i < maxTimeAttempt; i++) {
            visited.put(i, new HashSet<>());
        }
        ArrayList<PointAtTime> queue = new ArrayList<>();
        PointAtTime start = new PointAtTime(new Point(1, 0), 0);
        queue.add(start);
        boolean goalFound = false;
        int minMoves = -1;
        while (!queue.isEmpty() && !goalFound) {
            PointAtTime cur = queue.remove(0);
            if (cur.time < maxTimeAttempt - 1) {
                ArrayList<Point> possibleMoves = new ArrayList<>();
                possibleMoves.add(new Point(cur.location.x, cur.location.y));
                possibleMoves.add(new Point(cur.location.x + 1, cur.location.y));
                possibleMoves.add(new Point(cur.location.x - 1, cur.location.y));
                possibleMoves.add(new Point(cur.location.x, cur.location.y + 1));
                possibleMoves.add(new Point(cur.location.x, cur.location.y - 1));
                for (Point p : possibleMoves) {
                    if (p.x == inputWidth - 2 && p.y == inputHeight - 1) {
                        goalFound = true;
                        minMoves = cur.time + 1;
                        break;
                    } else {
                        if (p.y >= 0 && !visited.get(cur.time + 1).contains(p) &&
                                !occupiedPointPerTime.get(cur.time + 1).contains(p)) {
                            visited.get(cur.time + 1).add(p);
                            queue.add(new PointAtTime(p, cur.time + 1));
                        }
                    }
                }
            }
        }
        System.out.println(minMoves);
        queue = new ArrayList<>();
        start = new PointAtTime(new Point(inputWidth - 2, inputHeight - 1), minMoves);
        queue.add(start);
        goalFound = false;
        minMoves = -1;
        while (!queue.isEmpty() && !goalFound) {
            PointAtTime cur = queue.remove(0);
            if (cur.time < maxTimeAttempt - 1) {
                ArrayList<Point> possibleMoves = new ArrayList<>();
                possibleMoves.add(new Point(cur.location.x, cur.location.y));
                possibleMoves.add(new Point(cur.location.x + 1, cur.location.y));
                possibleMoves.add(new Point(cur.location.x - 1, cur.location.y));
                possibleMoves.add(new Point(cur.location.x, cur.location.y + 1));
                possibleMoves.add(new Point(cur.location.x, cur.location.y - 1));
                for (Point p : possibleMoves) {
                    if (p.x == 1 && p.y == 0) {
                        goalFound = true;
                        minMoves = cur.time + 1;
                        break;
                    } else {
                        if (p.y >= 0 && !visited.get(cur.time + 1).contains(p) &&
                                !occupiedPointPerTime.get(cur.time + 1).contains(p)) {
                            visited.get(cur.time + 1).add(p);
                            queue.add(new PointAtTime(p, cur.time + 1));
                        }
                    }
                }
            }
        }
        System.out.println(minMoves);
        queue = new ArrayList<>();
        start = new PointAtTime(new Point(1, 0), minMoves);
        queue.add(start);
        goalFound = false;
        minMoves = -1;
        while (!queue.isEmpty() && !goalFound) {
            PointAtTime cur = queue.remove(0);
            if (cur.time < maxTimeAttempt - 1) {
                ArrayList<Point> possibleMoves = new ArrayList<>();
                possibleMoves.add(new Point(cur.location.x, cur.location.y));
                possibleMoves.add(new Point(cur.location.x + 1, cur.location.y));
                possibleMoves.add(new Point(cur.location.x - 1, cur.location.y));
                possibleMoves.add(new Point(cur.location.x, cur.location.y + 1));
                possibleMoves.add(new Point(cur.location.x, cur.location.y - 1));
                for (Point p : possibleMoves) {
                    if (p.x == inputWidth - 2 && p.y == inputHeight - 1) {
                        goalFound = true;
                        minMoves = cur.time + 1;
                        break;
                    } else {
                        if (p.y >= 0 && !visited.get(cur.time + 1).contains(p) &&
                                !occupiedPointPerTime.get(cur.time + 1).contains(p)) {
                            visited.get(cur.time + 1).add(p);
                            queue.add(new PointAtTime(p, cur.time + 1));
                        }
                    }
                }
            }
        }
        System.out.println(minMoves);
    }
}
