package year2023.code;

import helpers.Helper;

import java.io.IOException;

public class Question06 {
    public static void main(String[] args) throws IOException {
        Q06Part1.run();
        Q06Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 6);
    }
}

class Q06Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question06.getInput();
        int[] times = new int[4];
        int[] distances = new int[4];
        for (int i = 0; i < 4; i++) {
            times[i] = Integer.parseInt(input[0].split(" +")[i+1]);
            distances[i] = Integer.parseInt(input[1].split(" +")[i+1]);
        }
        int count = 1;
        int countQuadratic = 1;
        for (int i = 0; i < 4; i++) {
            int countCur = 0;
            for (int a = 0; a <= times[i]; a++) {
                int s = a * (times[i] - a);
                if (s > distances[i]) {
                    countCur++;
                }
            }
            count *= countCur;
            countQuadratic *= ((long) Math.floor((times[i] + Math.sqrt(Math.pow(times[i], 2) - 4 * distances[i])) /2) + 1 - (long) Math.ceil((times[i] - Math.sqrt(Math.pow(times[i], 2) - 4 * distances[i])) /2));
        }
        System.out.println(count);
        System.out.println(countQuadratic);
    }
}

class Q06Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question06.getInput();
        long time;
        long distance;
        String[] timeParts = input[0].split(" +");
        time = Long.parseLong(timeParts[1] + timeParts[2] + timeParts[3] + timeParts[4]);
        String[] distanceParts = input[1].split(" +");
        distance = Long.parseLong(distanceParts[1] + distanceParts[2] + distanceParts[3] + distanceParts[4]);
        long count = 0;
        for (long a = 0; a <= time; a++) {
            long s = a * (time - a);
            if (s > distance) {
                count++;
            }
        }
        System.out.println(count);
        System.out.println(((long) Math.floor((time + Math.sqrt(Math.pow(time, 2) - 4 * distance)) /2) + 1 - (long) Math.ceil((time - Math.sqrt(Math.pow(time, 2) - 4 * distance)) /2)));
    }
}
