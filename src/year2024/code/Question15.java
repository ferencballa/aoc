package year2024.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Question15 {
    public static void main(String[] args) throws IOException {
        Q15Part1.run();
        Q15Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 15);
    }
}

class Q15Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question15.getInput();
        int height = 0;
        int width = input[0].length();
        int x = -1;
        int y = -1;
        while(!input[height].isEmpty()) {
            height++;
        }
        String[][] map = new String[height][width];
        for (int i = 0; i < height; i++) {
            map[i] = input[i].split("");
            if (input[i].indexOf('@') != -1) {
                x = input[i].indexOf('@');
                y = i;
                map[y][x] = ".";
            }
        }
        ArrayList<String> instructions = new ArrayList<>();
        for (int i = height + 1; i < input.length; i++) {
            for (String s : input[i].split("")) {
                instructions.add(s);
            }
        }
        for (String inst : instructions) {
//            for (int i = 0; i < height; i++) {
//                for (int j = 0; j < width; j++) {
//                    if (i == y && j == x) {
//                        System.out.print("@");
//                    } else {
//                        System.out.print(map[i][j]);
//                    }
//                }
//                System.out.print("\n");
//            }
//            System.out.println(inst);
            int dx = 0;
            int dy = 0;
            if (inst.equals("<")) {
                dx = -1;
            } else if (inst.equals(">")) {
                dx = 1;
            } else if (inst.equals("^")) {
                dy = -1;
            } else {
                dy = 1;
            }
            if (map[y + dy][x + dx].equals(".")) {
                x += dx;
                y += dy;
            } else {
                if (map[y + dy][x + dx].equals("O")) {
                    int countBoxes = 1;
                    while (map[y + (1 + countBoxes) * dy][x + (1 + countBoxes) * dx].equals("O")) {
                        countBoxes++;
                    }
                    if (map[y + (1 + countBoxes) * dy][x + (1 + countBoxes) * dx].equals(".")) {
                        map[y + (1 + countBoxes) * dy][x + (1 + countBoxes) * dx] = "O";
                        map[y + dy][x + dx] = ".";
                        x += dx;
                        y += dy;
                    }
                }
            }
        }
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                if (i == y && j == x) {
//                    System.out.print("@");
//                } else {
//                    System.out.print(map[i][j]);
//                }
//            }
//            System.out.print("\n");
//        }
        int count = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (map[j][i].equals("O")) {
                    count += i + 100 * j;
                }
            }
        }
        System.out.println(count);
    }
}

class Q15Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question15.getInput();
        int height = 0;
        int width = input[0].length() * 2;
        int x = -1;
        int y = -1;
        while(!input[height].isEmpty()) {
            height++;
        }
        String[][] map = new String[height][width];
        for (int i = 0; i < height; i++) {
            String s = input[i];
            s = s.replace("#", "##");
            s = s.replace("O", "[]");
            s = s.replace(".", "..");
            s = s.replace("@", "@.");
            map[i] = s.split("");
            if (s.indexOf('@') != -1) {
                x = s.indexOf('@');
                y = i;
                map[y][x] = ".";
            }
        }
        ArrayList<String> instructions = new ArrayList<>();
        for (int i = height + 1; i < input.length; i++) {
            for (String s : input[i].split("")) {
                instructions.add(s);
            }
        }
        for (String inst : instructions) {
//            for (int j = 0; j < height; j++) {
//                for (int i = 0; i < width; i++) {
//                    if (i == x && j == y) {
//                        System.out.print("@");
//                    } else {
//                        System.out.print(map[j][i]);
//                    }
//                }
//                System.out.print("\n");
//            }
//            System.out.println(inst);
            int dx = 0;
            int dy = 0;
            if (inst.equals("<")) {
                dx = -1;
            } else if (inst.equals(">")) {
                dx = 1;
            } else if (inst.equals("^")) {
                dy = -1;
            } else {
                dy = 1;
            }
            if (map[y + dy][x + dx].equals(".")) {
                x += dx;
                y += dy;
            } else {
                if (!map[y + dy][x + dx].equals("#")) {
                    if (inst.equals("<") || inst.equals(">")) {
                        int countBoxes = 1;
                        while (!(map[y][x + (1 + countBoxes * 2) * dx].equals("#") || map[y][x + (1 + countBoxes * 2) * dx].equals("."))) {
                            countBoxes++;
                        }
                        if (map[y][x + (1 + countBoxes * 2) * dx].equals(".")) {
                            for (int d = 1 + countBoxes * 2; d > 1; d--) {
                                map[y][x + d * dx] = map[y][x + (d-1) * dx];
                                map[y][x + (d-1) * dx] = ".";
                            }
                            x += dx;
                        }
                    } else {
                        ArrayList<HashSet<Integer>> indexToMovePerRow = new ArrayList<>();
                        HashSet<Integer> hsStart = new HashSet<>();
                        hsStart.add(x);
                        indexToMovePerRow.add(hsStart);
                        int d = 1;
                        tryToMove: while (true) {
                            HashSet<Integer> hsCur = indexToMovePerRow.get(indexToMovePerRow.size() - 1);
                            HashSet<Integer> otherHalves = new HashSet<>();
                            for (Integer boxHalfX : hsCur) {
                                if (map[y + d * dy][boxHalfX].equals("[")) {
                                    otherHalves.add(boxHalfX + 1);
                                } else {
                                    otherHalves.add(boxHalfX - 1);
                                }
                            }
                            for (Integer otherHalf : otherHalves) {
                                hsCur.add(otherHalf);
                            }
                            boolean done = true;
                            for (Integer xCoor : hsCur) {
                                if (!map[y + (d + 1) * dy][xCoor].equals(".")) {
                                    done = false;
                                    break;
                                }
                            }
                            if (done) {
                                //alles schuiven
                                for (int i = indexToMovePerRow.size() - 1; i >= 0; i--) {
                                    HashSet<Integer> hs = indexToMovePerRow.get(i);
                                    for (Integer xCoor : hs) {
                                        map[y + (i + 2) * dy][xCoor] = map[y + (i + 1) * dy][xCoor];
                                        map[y + (i + 1) * dy][xCoor] = ".";
                                    }
                                }
                                y += dy;
                                break tryToMove;
                            } else {
                                HashSet<Integer> hsNext = new HashSet<>();
                                for (Integer xCoor : hsCur) {
                                    if (map[y + (d + 1) * dy][xCoor].equals("#")) {
                                        break tryToMove;
                                    } else if (!map[y + (d + 1) * dy][xCoor].equals(".")) {
                                        hsNext.add(xCoor);
                                    }
                                }
                                indexToMovePerRow.add(hsNext);
                            }
                            d++;
                        }
                    }
                }
            }
        }
//        for (int j = 0; j < height; j++) {
//            for (int i = 0; i < width; i++) {
//                if (i == x && j == y) {
//                    System.out.print("@");
//                } else {
//                    System.out.print(map[j][i]);
//                }
//            }
//            System.out.print("\n");
//        }
        int count = 0;
        /*for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (map[j][i].equals("[")) {
                    if (j < height/2) {
                        count += 100 * j;
                    } else {
                        count += (height - 1 - j) * 100;
                    }
                    if (i < width / 2) {
                        count += i;
                    } else {
                        count += width - 2 - i;
                    }
                }
            }
        }*/ //wording of problem was weird. "For these larger boxes, distances are measured from the edge of the map to the closest edge of the box in question." -> that's what the loop above does. but apparently they still meant to count only from the top and left..
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (map[j][i].equals("[")) {
                    count += i + 100 * j;
                }
            }
        }
        System.out.println(count);
    }
}
