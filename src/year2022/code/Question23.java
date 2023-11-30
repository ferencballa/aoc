package year2022.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Question23 {
    public static void main(String[] args) throws IOException {
        Q23Part1.run();
        Q23Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 23);
    }
}

class Q23Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question23.getInput();
        HashSet<Point> occupied = new HashSet<>();
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                if (input[y].charAt(x) == '#') {
                    occupied.add(new Point(x, y));
                }
            }
        }
        ArrayList<String> directionsOrder = new ArrayList<>();
        directionsOrder.add("N");
        directionsOrder.add("S");
        directionsOrder.add("W");
        directionsOrder.add("E");
        HashMap<Point, Point> nextMoves;
        for (int i = 0; i < 10; i++) {
            nextMoves = new HashMap<>();
            for (int yp = -2; yp < 10; yp++) {
                for (int xp = -3; xp < 11; xp++) {
                    if (occupied.contains(new Point(xp, yp))) {
                        System.out.print("#");
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.print("\n");
            }
            System.out.print("\n");
            System.out.print("\n");
            for (Point elf : occupied) {
                int x = elf.x;
                int y = elf.y;
                if (occupied.contains(new Point(x - 1, y - 1)) ||
                    occupied.contains(new Point(x - 1, y)) ||
                    occupied.contains(new Point(x - 1, y + 1)) ||
                    occupied.contains(new Point(x, y - 1)) ||
                    occupied.contains(new Point(x, y + 1)) ||
                    occupied.contains(new Point(x + 1, y - 1)) ||
                    occupied.contains(new Point(x + 1, y)) ||
                    occupied.contains(new Point(x + 1, y + 1))
                ) {
                    for (int d = 0; d < 4; d++) {
                        String dir = directionsOrder.get(d);
                        if (dir.equals("N")) {
                            if (!occupied.contains(new Point(x - 1, y - 1)) && !occupied.contains(new Point(x, y - 1)) && !occupied.contains(new Point(x + 1, y - 1))) {
                                nextMoves.put(new Point(x, y), new Point(x, y - 1));
                                break;
                            }
                        } else if (dir.equals("S")) {
                            if (!occupied.contains(new Point(x - 1, y + 1)) && !occupied.contains(new Point(x, y + 1)) && !occupied.contains(new Point(x + 1, y + 1))) {
                                nextMoves.put(new Point(x, y), new Point(x, y + 1));
                                break;
                            }
                        } else if (dir.equals("W")) {
                            if (!occupied.contains(new Point(x - 1, y - 1)) && !occupied.contains(new Point(x - 1, y)) && !occupied.contains(new Point(x - 1, y + 1))) {
                                nextMoves.put(new Point(x, y), new Point(x - 1, y));
                                break;
                            }
                        } else {
                            if (!occupied.contains(new Point(x + 1, y - 1)) && !occupied.contains(new Point(x + 1, y)) && !occupied.contains(new Point(x + 1, y + 1))) {
                                nextMoves.put(new Point(x, y), new Point(x + 1, y));
                                break;
                            }
                        }
                        if (d == 3) {
                            nextMoves.put(new Point(x, y), null);
                        }
                    }
                } else {
                    nextMoves.put(new Point(x, y), null);
                }
            }
            occupied = new HashSet<>();
            for (Point p : nextMoves.keySet()) {
                if (nextMoves.get(p) == null) {
                    occupied.add(p);
                } else {
                    boolean movePossible = true;
                    for (Point otherP : nextMoves.keySet()) {
                        if (!p.equals(otherP) && nextMoves.get(p).equals(nextMoves.get(otherP))) {
                            movePossible = false;
                        }
                    }
                    if (movePossible) {
                        occupied.add(nextMoves.get(p));
                    } else {
                        occupied.add(p);
                    }
                }
            }
            String dir = directionsOrder.remove(0);
            directionsOrder.add(dir);
        }
        int lowXBound = Integer.MAX_VALUE;
        int highXBound = Integer.MIN_VALUE;
        int lowYBound = Integer.MAX_VALUE;
        int highYBound = Integer.MIN_VALUE;
        for (Point p : occupied) {
            lowXBound = Math.min(lowXBound, p.x);
            highXBound = Math.max(highXBound, p.x);
            lowYBound = Math.min(lowYBound, p.y);
            highYBound = Math.max(highYBound, p.y);
        }
        System.out.println((highXBound + 1 - lowXBound) * (highYBound + 1 - lowYBound) - occupied.size());
    }
}

class Q23Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question23.getInput();
        HashSet<Point> occupied = new HashSet<>();
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                if (input[y].charAt(x) == '#') {
                    occupied.add(new Point(x, y));
                }
            }
        }
        ArrayList<String> directionsOrder = new ArrayList<>();
        directionsOrder.add("N");
        directionsOrder.add("S");
        directionsOrder.add("W");
        directionsOrder.add("E");
        HashMap<Point, Point> nextMoves;
        int moves = 0;
        boolean spacedOut = false;
        while (!spacedOut) {
            moves++;
            nextMoves = new HashMap<>();
            /*for (int yp = -2; yp < 10; yp++) {
                for (int xp = -3; xp < 11; xp++) {
                    if (occupied.contains(new Point(xp, yp))) {
                        System.out.print("#");
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.print("\n");
            }
            System.out.print("\n");
            System.out.print("\n");*/
            for (Point elf : occupied) {
                int x = elf.x;
                int y = elf.y;
                if (occupied.contains(new Point(x - 1, y - 1)) ||
                        occupied.contains(new Point(x - 1, y)) ||
                        occupied.contains(new Point(x - 1, y + 1)) ||
                        occupied.contains(new Point(x, y - 1)) ||
                        occupied.contains(new Point(x, y + 1)) ||
                        occupied.contains(new Point(x + 1, y - 1)) ||
                        occupied.contains(new Point(x + 1, y)) ||
                        occupied.contains(new Point(x + 1, y + 1))
                ) {
                    for (int d = 0; d < 4; d++) {
                        String dir = directionsOrder.get(d);
                        if (dir.equals("N")) {
                            if (!occupied.contains(new Point(x - 1, y - 1)) && !occupied.contains(new Point(x, y - 1)) && !occupied.contains(new Point(x + 1, y - 1))) {
                                nextMoves.put(new Point(x, y), new Point(x, y - 1));
                                break;
                            }
                        } else if (dir.equals("S")) {
                            if (!occupied.contains(new Point(x - 1, y + 1)) && !occupied.contains(new Point(x, y + 1)) && !occupied.contains(new Point(x + 1, y + 1))) {
                                nextMoves.put(new Point(x, y), new Point(x, y + 1));
                                break;
                            }
                        } else if (dir.equals("W")) {
                            if (!occupied.contains(new Point(x - 1, y - 1)) && !occupied.contains(new Point(x - 1, y)) && !occupied.contains(new Point(x - 1, y + 1))) {
                                nextMoves.put(new Point(x, y), new Point(x - 1, y));
                                break;
                            }
                        } else {
                            if (!occupied.contains(new Point(x + 1, y - 1)) && !occupied.contains(new Point(x + 1, y)) && !occupied.contains(new Point(x + 1, y + 1))) {
                                nextMoves.put(new Point(x, y), new Point(x + 1, y));
                                break;
                            }
                        }
                        if (d == 3) {
                            nextMoves.put(new Point(x, y), null);
                        }
                    }
                } else {
                    nextMoves.put(new Point(x, y), null);
                }
            }
            occupied = new HashSet<>();
            for (Point p : nextMoves.keySet()) {
                if (nextMoves.get(p) == null) {
                    occupied.add(p);
                } else {
                    boolean movePossible = true;
                    for (Point otherP : nextMoves.keySet()) {
                        if (!p.equals(otherP) && nextMoves.get(p).equals(nextMoves.get(otherP))) {
                            movePossible = false;
                        }
                    }
                    if (movePossible) {
                        occupied.add(nextMoves.get(p));
                    } else {
                        occupied.add(p);
                    }
                }
            }
            String dir = directionsOrder.remove(0);
            directionsOrder.add(dir);
            spacedOut = true;
            for (Point p : occupied) {
                int x = p.x;
                int y = p.y;
                if (occupied.contains(new Point(x - 1, y - 1)) ||
                        occupied.contains(new Point(x - 1, y)) ||
                        occupied.contains(new Point(x - 1, y + 1)) ||
                        occupied.contains(new Point(x, y - 1)) ||
                        occupied.contains(new Point(x, y + 1)) ||
                        occupied.contains(new Point(x + 1, y - 1)) ||
                        occupied.contains(new Point(x + 1, y)) ||
                        occupied.contains(new Point(x + 1, y + 1))
                ) {
                    spacedOut = false;
                }
            }
        }
        System.out.println(moves + 1);
    }
}
