package year2021.code;

import helpers.Helper;

import java.io.IOException;

public class Question17 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2021, 17);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        String[] parts = input[0].split(" ");
        String[] xParts = parts[2].split(",")[0].split("=");
        String[] yParts = parts[3].split("=");
        int xLow = Integer.parseInt(xParts[1].split("\\.\\.")[0]);
        int xHigh = Integer.parseInt(xParts[1].split("\\.\\.")[1]);
        int yLow = Integer.parseInt(yParts[1].split("\\.\\.")[0]);
        int yHigh = Integer.parseInt(yParts[1].split("\\.\\.")[1]);
        int highestReachedY = 0;
        for (int dx = 0; dx <= xHigh; dx++) {
            for (int dy = 0; dy <= 200; dy++) {
                int curX = 0;
                int curY = 0;
                int curDx = dx;
                int curDy = dy;
                int curHighestReachedY = 0;
                while (curX + curDx <= xHigh && curY + curDy >= yLow) {
                    curX += curDx;
                    curY += curDy;
                    curHighestReachedY = Math.max(curHighestReachedY, curY);
                    if (curDx > 0) {
                        curDx--;
                    } else if (curDx < 0) {
                        curDx++;
                    }
                    curDy--;
                }
                if (curX >= xLow && curY <= yHigh) {
                    highestReachedY = Math.max(highestReachedY, curHighestReachedY);
                }
            }
        }
        System.out.println(highestReachedY);
    }

    private static void part2(String[] input) {
        String[] parts = input[0].split(" ");
        String[] xParts = parts[2].split(",")[0].split("=");
        String[] yParts = parts[3].split("=");
        int xLow = Integer.parseInt(xParts[1].split("\\.\\.")[0]);
        int xHigh = Integer.parseInt(xParts[1].split("\\.\\.")[1]);
        int yLow = Integer.parseInt(yParts[1].split("\\.\\.")[0]);
        int yHigh = Integer.parseInt(yParts[1].split("\\.\\.")[1]);
        int countReached = 0;
        for (int dx = 0; dx <= xHigh; dx++) {
            for (int dy = yLow; dy <= 200; dy++) {
                int curX = 0;
                int curY = 0;
                int curDx = dx;
                int curDy = dy;
                while (curX + curDx <= xHigh && curY + curDy >= yLow) {
                    curX += curDx;
                    curY += curDy;
                    if (curDx > 0) {
                        curDx--;
                    } else if (curDx < 0) {
                        curDx++;
                    }
                    curDy--;
                }
                if (curX >= xLow && curY <= yHigh) {
                    countReached++;
                }
            }
        }
        System.out.println(countReached);
    }
}
