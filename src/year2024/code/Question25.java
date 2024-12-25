package year2024.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;

public class Question25 {
    public static void main(String[] args) throws IOException {
        Q25Part1.run();
        Q25Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 25);
    }
}

class Q25Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question25.getInput();
        int heightCounter = 0;
        ArrayList<int[]> keys = new ArrayList<>();
        ArrayList<int[]> locks = new ArrayList<>();
        boolean[][] curIn = new boolean[7][5];
        for (String s : input) {
            if (heightCounter == 7) {
                heightCounter = 0;
            } else {
                for (int i = 0; i < 5; i++) {
                    if (s.charAt(i) == '#') {
                        curIn[heightCounter][i] = true;
                    }
                }
                heightCounter++;
                if (heightCounter == 7) {
                    if (curIn[0][0]) {
                        int[] lock = new int[5];
                        for (int i = 0; i < 5; i++) {
                            int lockHeight = 0;
                            for (int j = 0; j < 5; j++) {
                                if (curIn[j + 1][i]) {
                                    lockHeight++;
                                } else {
                                    break;
                                }
                            }
                            lock[i] = lockHeight;
                        }
                        locks.add(lock);
                    } else {
                        int[] key = new int[5];
                        for (int i = 0; i < 5; i++) {
                            int keyHeight = 0;
                            for (int j = 0; j < 5; j++) {
                                if (curIn[5 - j][i]) {
                                    keyHeight++;
                                } else {
                                    break;
                                }
                            }
                            key[i] = keyHeight;
                        }
                        keys.add(key);
                    }
                    curIn = new boolean[7][5];
                }
            }
        }
        int counter = 0;
        for (int[] lock : locks) {
            for (int[] key : keys) {
                boolean fit = true;
                for (int i = 0; i < 5; i++) {
                    if (lock[i] + key[i] > 5) {
                        fit = false;
                        break;
                    }
                }
                if (fit) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }
}

class Q25Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question25.getInput();
    }
}
