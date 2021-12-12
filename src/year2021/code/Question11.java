package year2021.code;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Question11 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2021/input/Question11.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        int[][] octopuses = new int[10][10];
        for (int i = 0; i < 10; i++) {
            String[] line = input[i].split("");
            for (int j = 0; j < 10; j++) {
                octopuses[i][j] = Integer.parseInt(line[j]);
            }
        }
        int countF = 0;
        for (int step = 0; step < 100; step++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    octopuses[i][j]++;
                }
            }
            boolean[][] flashed = new boolean[10][10];
            boolean flash = true;
            while (flash) {
                flash = false;
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (octopuses[i][j] > 9 && !flashed[i][j]) {
                            flashed[i][j] = true;
                            flash = true;
                            countF++;
                            for (int di = -1; di <= 1; di++) {
                                for (int dj = -1; dj <= 1; dj++) {
                                    if (di != 0 || dj != 0) {
                                        if (i + di >= 0 && i + di < 10 && j + dj >= 0 && j + dj < 10) {
                                            octopuses[i+di][j+dj]++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (flashed[i][j]) {
                        octopuses[i][j] = 0;
                    }
                }
            }
        }
        System.out.println(countF);
    }

    private static void part2(String[] input) {
        int[][] octopuses = new int[10][10];
        for (int i = 0; i < 10; i++) {
            String[] line = input[i].split("");
            for (int j = 0; j < 10; j++) {
                octopuses[i][j] = Integer.parseInt(line[j]);
            }
        }
        int countF = 0;
        boolean synced = false;
        while(!synced) {
            countF++;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    octopuses[i][j]++;
                }
            }
            boolean[][] flashed = new boolean[10][10];
            boolean flash = true;
            while (flash) {
                flash = false;
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (octopuses[i][j] > 9 && !flashed[i][j]) {
                            flashed[i][j] = true;
                            flash = true;
                            for (int di = -1; di <= 1; di++) {
                                for (int dj = -1; dj <= 1; dj++) {
                                    if (di != 0 || dj != 0) {
                                        if (i + di >= 0 && i + di < 10 && j + dj >= 0 && j + dj < 10) {
                                            octopuses[i+di][j+dj]++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int countFlashed = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (flashed[i][j]) {
                        countFlashed++;
                        octopuses[i][j] = 0;
                    }
                }
            }
            if (countFlashed == 100) {
                synced = true;
            }
        }
        System.out.println(countF);
    }
}
