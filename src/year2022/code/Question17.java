package year2022.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;

public class Question17 {
    public static void main(String[] args) throws IOException {
        Q17Part1.run();
        Q17Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 17);
    }
}

class Q17Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question17.getInput();
        HashSet<Point> grid = new HashSet<>();
        int highestPoint = 0;
        int inputIndex = 0;
        for (int i = 0; i < 2022; i++) {
            /*System.out.print("\n");
            System.out.print("\n");
            for (int y = 10; y >= 0; y--) {
                for (int x = 0; x <= 6; x++) {
                    if (grid.contains(new Point(x, y))) {
                        System.out.print("#");
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.print("\n");
            }*/
            HashSet<Point> block = new HashSet<>();
            if (i % 5 == 0) {
                block.add(new Point(2, highestPoint + 3));
                block.add(new Point(3, highestPoint + 3));
                block.add(new Point(4, highestPoint + 3));
                block.add(new Point(5, highestPoint + 3));
            } else if (i % 5 == 1) {
                block.add(new Point(2, highestPoint + 4));
                block.add(new Point(3, highestPoint + 4));
                block.add(new Point(4, highestPoint + 4));
                block.add(new Point(3, highestPoint + 3));
                block.add(new Point(3, highestPoint + 5));
            } else if (i % 5 == 2) {
                block.add(new Point(2, highestPoint + 3));
                block.add(new Point(3, highestPoint + 3));
                block.add(new Point(4, highestPoint + 3));
                block.add(new Point(4, highestPoint + 4));
                block.add(new Point(4, highestPoint + 5));
            } else if (i % 5 == 3) {
                block.add(new Point(2, highestPoint + 3));
                block.add(new Point(2, highestPoint + 4));
                block.add(new Point(2, highestPoint + 5));
                block.add(new Point(2, highestPoint + 6));
            } else {
                block.add(new Point(2, highestPoint + 3));
                block.add(new Point(2, highestPoint + 4));
                block.add(new Point(3, highestPoint + 3));
                block.add(new Point(3, highestPoint + 4));
            }
            boolean stopped = false;
            while (!stopped) {
                char move = input[0].charAt(inputIndex);
                inputIndex++;
                inputIndex %= input[0].length();
                if (move == '<') {
                    boolean spacesFree = true;
                    for (Point p : block) {
                        if (p.x - 1 < 0 || grid.contains(new Point(p.x - 1, p.y))) {
                            spacesFree = false;
                        }
                    }
                    if (spacesFree) {
                        for (Point p : block) {
                            p.x--;
                        }
                    }
                } else {
                    boolean spacesFree = true;
                    for (Point p : block) {
                        if (p.x + 1 > 6 || grid.contains(new Point(p.x + 1, p.y))) {
                            spacesFree = false;
                        }
                    }
                    if (spacesFree) {
                        for (Point p : block) {
                            p.x++;
                        }
                    }
                }boolean spacesBelowFree = true;
                for (Point p : block) {
                    if (p.y - 1 < 0 || grid.contains(new Point(p.x, p.y - 1))) {
                        spacesBelowFree = false;
                    }
                }
                if (spacesBelowFree) {
                    for (Point p : block) {
                        p.y--;
                    }
                } else {
                    stopped = true;
                    for (Point p : block) {
                        grid.add(p);
                        highestPoint = Math.max(p.y + 1, highestPoint);
                    }
                }
            }
        }
        System.out.println(highestPoint);
    }
}

class LongPoint {
    long x;
    long y;

    public LongPoint(long x, long y) {
        this.x = x;
        this.y = y;
    }
}

class Q17Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    /*
    run a while, print results for every block dropped
    copy and paste prints in a new file, and try to manually find the cycle (shouldn't be too hard, for me it was a few thousand in, and a little over a thousand long
    find the start of the cycle (I believe it does not have to be the first time it cycles, just any start of a cycle, as long as you note this starting point)
    check what the highestPoint is at the start of the cycle
    check the highestPoint at the start of the next cycle, and just in case at the start of the next few cycles
    if the highestPoint rises consistently each cycle (which it should, else something went wrong), take that as the cycle height increase
    take the 1_000_000_000_000, remove the startcycle
    take the modulo of the remaining steps, this is what has to be added at the end of cycling
    remove this modulo, and divide the 1tril minus startcycle minus modulo by the cyclelength, this is the number of cycles
    multiply numOfCycles by cycle height increase, and add the height at the start of the cycle
    if remainder of modulo wasn't 0 earlier, check height difference between cycle start, and cycle start + remainder, and add this to the value above, and voila
    included is a jpeg with values calculated this way during the run as a (very very bad) example
    */
    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question17.getInput();
        HashSet<String> grid = new HashSet<>();
        long highestPoint = 0L;
        int a = 0;
        int inputIndex = 0;
        for (long i = 0; i < 1_000_000_000_000L; i++) {
            System.out.println(inputIndex);
            if (i % 3290 == 0) {
                //System.out.println("test");
                a++;
            }
            /*if (i > 0 && i % 5 == 0 && inputIndex % input[0].length() == 0) {
                if (grid.contains("x0y" + highestPoint) &&
                    grid.contains("x1y" + highestPoint) &&
                    grid.contains("x2y" + highestPoint) &&
                    grid.contains("x3y" + highestPoint) &&
                    grid.contains("x4y" + highestPoint) &&
                    grid.contains("x5y" + highestPoint) &&
                    grid.contains("x6y" + highestPoint)
                ) {
                    long cycleLength = i;
                    long numOfCyclesLeft = Math.floorDiv(1_000_000_000_000L, cycleLength) - 1;
                    i += cycleLength * numOfCyclesLeft;
                }
            }*/
            HashSet<LongPoint> block = new HashSet<>();
            if (i % 5 == 0) {
                block.add(new LongPoint(2, highestPoint + 3));
                block.add(new LongPoint(3, highestPoint + 3));
                block.add(new LongPoint(4, highestPoint + 3));
                block.add(new LongPoint(5, highestPoint + 3));
            } else if (i % 5 == 1) {
                block.add(new LongPoint(2, highestPoint + 4));
                block.add(new LongPoint(3, highestPoint + 4));
                block.add(new LongPoint(4, highestPoint + 4));
                block.add(new LongPoint(3, highestPoint + 3));
                block.add(new LongPoint(3, highestPoint + 5));
            } else if (i % 5 == 2) {
                block.add(new LongPoint(2, highestPoint + 3));
                block.add(new LongPoint(3, highestPoint + 3));
                block.add(new LongPoint(4, highestPoint + 3));
                block.add(new LongPoint(4, highestPoint + 4));
                block.add(new LongPoint(4, highestPoint + 5));
            } else if (i % 5 == 3) {
                block.add(new LongPoint(2, highestPoint + 3));
                block.add(new LongPoint(2, highestPoint + 4));
                block.add(new LongPoint(2, highestPoint + 5));
                block.add(new LongPoint(2, highestPoint + 6));
            } else {
                block.add(new LongPoint(2, highestPoint + 3));
                block.add(new LongPoint(2, highestPoint + 4));
                block.add(new LongPoint(3, highestPoint + 3));
                block.add(new LongPoint(3, highestPoint + 4));
            }
            boolean stopped = false;
            while (!stopped) {
                char move = input[0].charAt(inputIndex);
                inputIndex++;
                inputIndex %= input[0].length();
                if (move == '<') {
                    boolean spacesFree = true;
                    for (LongPoint p : block) {
                        String s = "x" + (p.x - 1) + "y" + p.y;
                        if (p.x - 1 < 0 || grid.contains(s)) {
                            spacesFree = false;
                            break;
                        }
                    }
                    if (spacesFree) {
                        for (LongPoint p : block) {
                            p.x--;
                        }
                    }
                } else {
                    boolean spacesFree = true;
                    for (LongPoint p : block) {
                        String s = "x" + (p.x + 1) + "y" + p.y;
                        if (p.x + 1 > 6 || grid.contains(s)) {
                            spacesFree = false;
                            break;
                        }
                    }
                    if (spacesFree) {
                        for (LongPoint p : block) {
                            p.x++;
                        }
                    }
                }boolean spacesBelowFree = true;
                for (LongPoint p : block) {
                    String s = "x" + p.x + "y" + (p.y - 1);
                    if (p.y - 1 < 0 || grid.contains(s)) {
                        spacesBelowFree = false;
                        break;
                    }
                }
                if (spacesBelowFree) {
                    for (LongPoint p : block) {
                        p.y--;
                    }
                } else {
                    stopped = true;
                    for (LongPoint p : block) {
                        grid.add("x" + p.x + "y" + p.y);
                        highestPoint = Math.max(p.y + 1, highestPoint);
                    }
                }
            }
        }
        System.out.println(highestPoint);
    }
}
