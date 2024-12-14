package year2024.code;

import helpers.Helper;

import java.io.IOException;

public class Question14 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Q14Part1.run();
        Q14Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 14);
    }
}

class Q14Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question14.getInput();
        int width = 101;
        int height = 103;
        int steps = 100;
        int[][] map = new int[width][height];
        for (String line : input) {
            String[] lineParts = line.split(" ");
            int[] posParts = Helper.StringArrayToIntArray(lineParts[0].split("=")[1].split(","));
            int[] velParts = Helper.StringArrayToIntArray(lineParts[1].split("=")[1].split(","));
            int newX = (posParts[0] + velParts[0] * steps) % width;
            if (newX < 0) {
                newX += width;
            }
            int newY = (posParts[1] + velParts[1] * steps) % height;
            if (newY < 0) {
                newY += height;
            }
            map[newX][newY]++;
        }
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        for (int x = 0; x < (width - 1) / 2; x++) {
            for (int y = 0; y < (height - 1) / 2; y++) {
                count1 += map[x][y];
                count2 += map[x + (width - 1) / 2 + 1][y];
                count3 += map[x][y + (height - 1) / 2 + 1];
                count4 += map[x + (width - 1) / 2 + 1][y + (height - 1) / 2 + 1];
            }
        }
        System.out.println(count1 * count2 * count3 * count4);
    }
}

class Q14Part2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        run();
    }

    static void run() throws IOException, InterruptedException {
        System.out.println("Part 2:");
        String[] input = Question14.getInput();
        int width = 101;
        int height = 103;
        int steps = 100;
        int[][] positions = new int[input.length][];
        int[][] velocities = new int[input.length][];
        for (int i = 0; i < input.length; i++) {
            String line = input[i];
            String[] lineParts = line.split(" ");
            int[] posParts = Helper.StringArrayToIntArray(lineParts[0].split("=")[1].split(","));
            int[] velParts = Helper.StringArrayToIntArray(lineParts[1].split("=")[1].split(","));
            positions[i] = posParts;
            velocities[i] = velParts;
        }
        int counter = 0;
        while(true) { //manually stop program when I see something resembling a tree in the console
            counter++;
            boolean[][] map = new boolean[width][height];
            for (int i = 0; i < positions.length; i++) {
                int newX = (positions[i][0] + velocities[i][0]) % width;
                if (newX < 0) {
                    newX += width;
                }
                int newY = (positions[i][1] + velocities[i][1]) % height;
                if (newY < 0) {
                    newY += height;
                }
                positions[i][0] = newX;
                positions[i][1] = newY;
                map[newX][newY] = true;
            }
            boolean candidate = false;
            for (int y = 0; y < height; y++) {
                int inALine = 0;
                for (int x = 0; x < width; x++) {
                    if (map[x][y]) {
                        inALine++;
                        if (inALine > 7) { //no idea what the image looks like, but a vertical line of 8 or longer in the trunk seems like a good start to look
                            candidate = true;
                        }
                    } else {
                        inALine = 0;
                    }
                }
            }
            if (candidate) {
                System.out.println(counter);
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        if (map[x][y]) {
                            System.out.print("█");
                        } else {
                            System.out.print(" ");
                        }
                    }
                    System.out.print("\n");
                }
            }
        }
    }
}
