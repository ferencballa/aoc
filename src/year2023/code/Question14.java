package year2023.code;

import helpers.Helper;

import java.io.IOException;
import java.util.HashMap;

public class Question14 {
    public static void main(String[] args) throws IOException {
        Q14Part1.run();
        Q14Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 14);
    }
}

class Q14Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question14.getInput();
        char[][] map = new char[input[0].length()][];
        for (int i = 0; i < map.length; i++) {
            map[i] = new char[input.length];
            for (int j = 0; j < input.length; j++) {
                map[i][j] = input[j].charAt(i);
            }
        }
        long count = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] == 'O') {
                    int newYPos = y;
                    while (newYPos > 0 && map[x][newYPos - 1] == '.') {
                        newYPos--;
                    }
                    map[x][y] = '.';
                    map[x][newYPos] = 'O';
                    count += map[x].length - newYPos;
                }
            }
        }
        System.out.println(count);
    }
}

class Q14Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question14.getInput();
        char[][] map = new char[input[0].length()][];
        for (int i = 0; i < map.length; i++) {
            map[i] = new char[input.length];
            for (int j = 0; j < input.length; j++) {
                map[i][j] = input[j].charAt(i);
            }
        }
        HashMap<String, Integer> mapPositions = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                stringBuilder.append(map[x][y]);
            }
        }
        /*for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                System.out.print(map[x][y]);
            }
            System.out.print("\n");
        }*/
        String mapRepresentedAsString = stringBuilder.toString();
        mapPositions.put(mapRepresentedAsString, 0);
        int count = 0;
        int oldCount = -1;
        int countDuplicates = 0;
        boolean duplicateFound = false;
        while (!duplicateFound) {
            if (count % 1000 == 0) {
                System.out.println(count);
            }
            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[x].length; y++) {
                    if (map[x][y] == 'O') {
                        int newYPos = y;
                        while (newYPos > 0 && map[x][newYPos - 1] == '.') {
                            newYPos--;
                        }
                        map[x][y] = '.';
                        map[x][newYPos] = 'O';
                    }
                }
            }
            for (int y = 0; y < map[0].length; y++) {
                for (int x = 0; x < map.length; x++) {
                    if (map[x][y] == 'O') {
                        int newXPos = x;
                        while (newXPos > 0 && map[newXPos - 1][y] == '.') {
                            newXPos--;
                        }
                        map[x][y] = '.';
                        map[newXPos][y] = 'O';
                    }
                }
            }
            for (int x = 0; x < map.length; x++) {
                for (int y = map[x].length - 1; y >= 0; y--) {
                    if (map[x][y] == 'O') {
                        int newYPos = y;
                        while (newYPos < map[x].length - 1 && map[x][newYPos + 1] == '.') {
                            newYPos++;
                        }
                        map[x][y] = '.';
                        map[x][newYPos] = 'O';
                    }
                }
            }
            for (int y = 0; y < map[0].length; y++) {
                for (int x = map.length - 1; x >= 0; x--) {
                    if (map[x][y] == 'O') {
                        int newXPos = x;
                        while (newXPos < map.length - 1 && map[newXPos + 1][y] == '.') {
                            newXPos++;
                        }
                        map[x][y] = '.';
                        map[newXPos][y] = 'O';
                    }
                }
            }
            stringBuilder = new StringBuilder();
            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[x].length; y++) {
                    stringBuilder.append(map[x][y]);
                }
            }
            mapRepresentedAsString = stringBuilder.toString();
            count++;
            /*for (int y = 0; y < map[0].length; y++) {
                for (int x = 0; x < map.length; x++) {
                    System.out.print(map[x][y]);
                }
                System.out.print("\n");
            }*/
            System.out.println();
            if (mapPositions.containsKey(mapRepresentedAsString)) {
                oldCount = mapPositions.get(mapRepresentedAsString);
                System.out.println("Old count: " + mapPositions.get(mapRepresentedAsString));
                System.out.println("New count: " + count);
                duplicateFound = true;
            } else {
                mapPositions.put(mapRepresentedAsString, count);
            }
        }
        int totalCycles = 1000000000;
        totalCycles -= oldCount;
        totalCycles %= (count - oldCount);
        for (int i = 0; i < totalCycles; i++) {
            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[x].length; y++) {
                    if (map[x][y] == 'O') {
                        int newYPos = y;
                        while (newYPos > 0 && map[x][newYPos - 1] == '.') {
                            newYPos--;
                        }
                        map[x][y] = '.';
                        map[x][newYPos] = 'O';
                    }
                }
            }
            for (int y = 0; y < map[0].length; y++) {
                for (int x = 0; x < map.length; x++) {
                    if (map[x][y] == 'O') {
                        int newXPos = x;
                        while (newXPos > 0 && map[newXPos - 1][y] == '.') {
                            newXPos--;
                        }
                        map[x][y] = '.';
                        map[newXPos][y] = 'O';
                    }
                }
            }
            for (int x = 0; x < map.length; x++) {
                for (int y = map[x].length - 1; y >= 0; y--) {
                    if (map[x][y] == 'O') {
                        int newYPos = y;
                        while (newYPos < map[x].length - 1 && map[x][newYPos + 1] == '.') {
                            newYPos++;
                        }
                        map[x][y] = '.';
                        map[x][newYPos] = 'O';
                    }
                }
            }
            for (int y = 0; y < map[0].length; y++) {
                for (int x = map.length - 1; x >= 0; x--) {
                    if (map[x][y] == 'O') {
                        int newXPos = x;
                        while (newXPos < map.length - 1 && map[newXPos + 1][y] == '.') {
                            newXPos++;
                        }
                        map[x][y] = '.';
                        map[newXPos][y] = 'O';
                    }
                }
            }
        }
        long countWeight = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] == 'O') {
                    countWeight += map[x].length - y;
                }
            }
        }
        System.out.println(countWeight);
    }
}
