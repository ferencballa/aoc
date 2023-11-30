package year2022.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class Question22 {
    public static void main(String[] args) throws IOException {
        Q22Part1.run();
        Q22Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 22);
    }
}

class Q22Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question22.getInput();
        int maxWidth = 0;
        int height = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i].length() == 0) {
                break;
            } else {
                maxWidth = Math.max(maxWidth, input[i].length());
                height = i;
            }
        }
        maxWidth--;
        HashMap<Point, Boolean> map = new HashMap<>();
        int startX = Integer.MAX_VALUE;
        for (int i = 0; i <= height; i++) {
            for (int j = 0; j <= maxWidth; j++) {
                if (j < input[i].length()) {
                    if (input[i].charAt(j) == '.') {
                        map.put(new Point(j, i), true);
                        if (i == 0) {
                            startX = Math.min(startX, j);
                        }
                    } else if (input[i].charAt(j) == '#') {
                        map.put(new Point(j, i), false);
                    }
                }
            }
        }
        int commandIndex = 0;
        String commandInputs = input[height+2];
        int dir = 0;
        int x = startX;
        int y = 0;
        while (commandIndex < commandInputs.length()) {
            StringBuilder numberBuilder = new StringBuilder();
            while (commandIndex < commandInputs.length() && commandInputs.charAt(commandIndex) >= 48 && commandInputs.charAt(commandIndex) <= 57) {
                numberBuilder.append(commandInputs.charAt(commandIndex));
                commandIndex++;
            }
            int steps = Integer.parseInt(numberBuilder.toString());
            for (int i = 0; i < steps; i++) {
                if (dir == 0) {
                    if (map.get(new Point(x + 1, y)) == null) {
                        int nextStart = 0;
                        while (map.get(new Point(nextStart, y)) == null) {
                            nextStart++;
                        }
                        if (!map.get(new Point(nextStart, y))) {
                            break;
                        } else {
                            x = nextStart;
                        }
                    } else {
                        if (!map.get(new Point(x + 1, y))) {
                            break;
                        } else {
                            x++;
                        }
                    }
                } else if (dir == 1) {
                    if (map.get(new Point(x, y + 1)) == null) {
                        int nextStart = 0;
                        while (map.get(new Point(x, nextStart)) == null) {
                            nextStart++;
                        }
                        if (!map.get(new Point(x, nextStart))) {
                            break;
                        } else {
                            y = nextStart;
                        }
                    } else {
                        if (!map.get(new Point(x, y + 1))) {
                            break;
                        } else {
                            y++;
                        }
                    }
                } else if (dir == 2) {
                    if (map.get(new Point(x - 1, y)) == null) {
                        int nextStart = maxWidth;
                        while (map.get(new Point(nextStart, y)) == null) {
                            nextStart--;
                        }
                        if (!map.get(new Point(nextStart, y))) {
                            break;
                        } else {
                            x = nextStart;
                        }
                    } else {
                        if (!map.get(new Point(x - 1, y))) {
                            break;
                        } else {
                            x--;
                        }
                    }
                } else {
                    if (map.get(new Point(x, y - 1)) == null) {
                        int nextStart = height;
                        while (map.get(new Point(x, nextStart)) == null) {
                            nextStart--;
                        }
                        if (!map.get(new Point(x, nextStart))) {
                            break;
                        } else {
                            y = nextStart;
                        }
                    } else {
                        if (!map.get(new Point(x, y - 1))) {
                            break;
                        } else {
                            y--;
                        }
                    }
                }

            }
            if (commandIndex < commandInputs.length() && commandInputs.charAt(commandIndex) == 'L') {
                dir--;
                if (dir == -1) {
                    dir =3;
                }
            } else if (commandIndex < commandInputs.length()) {
                dir++;
                if (dir == 4) {
                    dir = 0;
                }
            }
            commandIndex++;
        }
        System.out.println(1000 * (y + 1) + 4 * (x + 1) + dir);
    }
}

class Q22Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question22.getInput();
        int maxWidth = 0;
        int height = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i].length() == 0) {
                break;
            } else {
                maxWidth = Math.max(maxWidth, input[i].length());
                height = i;
            }
        }
        maxWidth--;
        HashMap<Point, Boolean> map = new HashMap<>();
        int startX = Integer.MAX_VALUE;
        for (int i = 0; i <= height; i++) {
            for (int j = 0; j <= maxWidth; j++) {
                if (j < input[i].length()) {
                    if (input[i].charAt(j) == '.') {
                        map.put(new Point(j, i), true);
                        if (i == 0) {
                            startX = Math.min(startX, j);
                        }
                    } else if (input[i].charAt(j) == '#') {
                        map.put(new Point(j, i), false);
                    }
                }
            }
        }
        int commandIndex = 0;
        String commandInputs = input[height+2];
        int dir = 0;
        int x = startX;
        int y = 0;
        while (commandIndex < commandInputs.length()) {
            StringBuilder numberBuilder = new StringBuilder();
            while (commandIndex < commandInputs.length() && commandInputs.charAt(commandIndex) >= 48 && commandInputs.charAt(commandIndex) <= 57) {
                numberBuilder.append(commandInputs.charAt(commandIndex));
                commandIndex++;
            }
            int steps = Integer.parseInt(numberBuilder.toString());
            for (int i = 0; i < steps; i++) {
                if (dir == 0) {
                    if (map.get(new Point(x + 1, y)) == null) {
                        int nextX = -1;
                        int nextY = -1;
                        int newDir = -1;
                        if (y < 50) {
                            nextX = 99;
                            nextY = 149 - y;
                            newDir = 2;
                        } else if (y < 100) {
                            nextX = y + 50;
                            nextY = 49;
                            newDir = 3;
                        } else if (y < 150) {
                            nextX = 149;
                            nextY = 149 - y;
                            newDir = 2;
                        } else {
                            nextX = y - 100;
                            nextY = 149;
                            newDir = 3;

                        }
                        if (!map.get(new Point(nextX, nextY))) {
                            break;
                        } else {
                            x = nextX;
                            y = nextY;
                            dir = newDir;
                        }
                    } else {
                        if (!map.get(new Point(x + 1, y))) {
                            break;
                        } else {
                            x++;
                        }
                    }
                } else if (dir == 1) {
                    if (map.get(new Point(x, y + 1)) == null) {
                        int nextX = -1;
                        int nextY = -1;
                        int newDir = -1;
                        if (x < 50) {
                            nextX = x + 100;
                            nextY = 0;
                            newDir = 1;
                        } else if (x < 100) {
                            nextX = 49;
                            nextY = x + 100;
                            newDir = 2;
                        } else {
                            nextX = 99;
                            nextY = x - 50;
                            newDir = 2;
                        }
                        if (!map.get(new Point(nextX, nextY))) {
                            break;
                        } else {
                            x = nextX;
                            y = nextY;
                            dir = newDir;
                        }
                    } else {
                        if (!map.get(new Point(x, y + 1))) {
                            break;
                        } else {
                            y++;
                        }
                    }
                } else if (dir == 2) {
                    if (map.get(new Point(x - 1, y)) == null) {
                        int nextX = -1;
                        int nextY = -1;
                        int newDir = -1;
                        if (y < 50) {
                            nextX = 0;
                            nextY = 149 - y;
                            newDir = 0;
                        } else if (y < 100) {
                            nextX = y - 50;
                            nextY = 100;
                            newDir = 1;
                        } else if (y < 150) {
                            nextX = 50;
                            nextY = 149 - y;
                            newDir = 0;
                        } else {
                            nextX = y - 100;
                            nextY = 0;
                            newDir = 1;
                        }
                        if (!map.get(new Point(nextX, nextY))) {
                            break;
                        } else {
                            x = nextX;
                            y = nextY;
                            dir = newDir;
                        }
                    } else {
                        if (!map.get(new Point(x - 1, y))) {
                            break;
                        } else {
                            x--;
                        }
                    }
                } else {
                    if (map.get(new Point(x, y - 1)) == null) {
                        int nextX = -1;
                        int nextY = -1;
                        int newDir = -1;
                        if (x < 50) {
                            nextX = 50;
                            nextY = x + 50;
                            newDir = 0;
                        } else if (x < 100) {
                            nextX = 0;
                            nextY = x + 100;
                            newDir = 0;
                        } else {
                            nextX = x - 100;
                            nextY = 199;
                            newDir = 3;
                        }
                        if (!map.get(new Point(nextX, nextY))) {
                            break;
                        } else {
                            x = nextX;
                            y = nextY;
                            dir = newDir;
                        }
                    } else {
                        if (!map.get(new Point(x, y - 1))) {
                            break;
                        } else {
                            y--;
                        }
                    }
                }

            }
            if (commandIndex < commandInputs.length() && commandInputs.charAt(commandIndex) == 'L') {
                dir--;
                if (dir == -1) {
                    dir =3;
                }
            } else if (commandIndex < commandInputs.length()) {
                dir++;
                if (dir == 4) {
                    dir = 0;
                }
            }
            commandIndex++;
        }
        System.out.println(1000 * (y + 1) + 4 * (x + 1) + dir);
    }
}
