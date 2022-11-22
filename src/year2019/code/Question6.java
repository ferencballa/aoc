package year2019.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Question6 {
    private static final HashMap<String, ArrayList<String>> children = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2019, 6);
        for (String inp : input) {
            String[] objs = inp.split("\\)");
            if (children.containsKey(objs[0])) {
                children.get(objs[0]).add(objs[1]);
            } else {
                children.put(objs[0], new ArrayList<>(List.of(objs[1])));
            }
        }
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }

    private static void part1() {
        System.out.println(countOrbiting("COM", 1));
    }

    private static void part2() {
        Point distances = shortestDistance("COM");
        System.out.println(distances.x + distances.y);
    }

    private static int countOrbiting(String cur, int distance) {
        if (!children.containsKey(cur)) {
            return 0;
        }
        int count = 0;
        for (String s: children.get(cur)) {
            count += distance + countOrbiting(s, distance + 1);
        }
        return count;
    }

    private static Point shortestDistance(String cur) {
        if (!children.containsKey(cur)) {
            return new Point(-1, -1);
        }
        ArrayList<Point> distances = new ArrayList<>();
        int x = -1;
        int y = -1;
        for (String s: children.get(cur)) {
            if (s.equals("YOU")) {
                x = 0;
            } else if (s.equals("SAN")) {
                y = 0;
            } else {
                Point ret = shortestDistance(s);
                if (ret.x != -1 && ret.y != -1) {
                    x = ret.x;
                    y = ret.y;
                } else if (ret.x != -1) {
                    x = ret.x + 1;
                } else if (ret.y != -1) {
                    y = ret.y + 1;
                }
            }
        }
        return new Point(x, y);
    }
}
