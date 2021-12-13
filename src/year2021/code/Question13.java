package year2021.code;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Question13 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2021/input/Question13.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        ArrayList<Point> points = new ArrayList<>();
        int maxX = 0;
        int maxY = 0;
        for (String inp : input) {
            if (inp.length() > 0) {
                if (!inp.startsWith("fold")) {
                    String[] inpParts = inp.split(",");
                    Point p = new Point(Integer.parseInt(inpParts[0]), Integer.parseInt(inpParts[1]));
                    maxX = Math.max(maxX, p.x + 1);
                    maxY = Math.max(maxY, p.y + 1);
                    points.add(p);
                }
            }
        }
        boolean[][] paper = new boolean[maxX][maxY];
        for (Point p : points) {
            paper[p.x][p.y] = true;
        }
        boolean[][] newPaper = new boolean[655][maxY];
        for (int x = 0; x < 655; x++) {
            System.arraycopy(paper[x], 0, newPaper[x], 0, maxY);
        }
        for (int x = 655 + 1; x < maxX; x++) {
            int distFromEdge = x - (655 + 1);
            int newX = 655 - 1 - distFromEdge;
            for (int y = 0; y < maxY; y++) {
                newPaper[newX][y] = paper[x][y] || newPaper[newX][y];
            }
        }
        int count = 0;
        for (boolean[] booleans : newPaper) {
            for (int y = 0; y < newPaper[0].length; y++) {
                if (booleans[y]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void part2(String[] input) {
        ArrayList<Point> points = new ArrayList<>();
        ArrayList<String> instructions = new ArrayList<>();
        int maxX = 0;
        int maxY = 0;
        for (String inp : input) {
            if (inp.length() > 0) {
                if (inp.startsWith("fold")) {
                    instructions.add(inp);
                } else {
                    String[] inpParts = inp.split(",");
                    Point p = new Point(Integer.parseInt(inpParts[0]), Integer.parseInt(inpParts[1]));
                    maxX = Math.max(maxX, p.x + 1);
                    maxY = Math.max(maxY, p.y + 1);
                    points.add(p);
                }
            }
        }
        boolean[][] paper = new boolean[maxX][maxY];
        for (Point p : points) {
            paper[p.x][p.y] = true;
        }
        for (String instr : instructions) {
            String[] instructionParts = instr.split(" ")[2].split("=");
            int splitVal = Integer.parseInt(instructionParts[1]);
            if (instructionParts[0].equals("x")) {
                boolean[][] newPaper = new boolean[splitVal][paper[0].length];
                for (int x = 0; x < splitVal; x++) {
                    System.arraycopy(paper[x], 0, newPaper[x], 0, paper[0].length);
                }
                for (int x = splitVal + 1; x < paper.length; x++) {
                    int distFromEdge = x - (splitVal + 1);
                    int newX = splitVal - 1 - distFromEdge;
                    for (int y = 0; y < paper[0].length; y++) {
                        newPaper[newX][y] = paper[x][y] || newPaper[newX][y];
                    }
                }
                paper = newPaper;
            } else {
                boolean[][] newPaper = new boolean[paper.length][splitVal];
                for (int x = 0; x < paper.length; x++) {
                    System.arraycopy(paper[x], 0, newPaper[x], 0, splitVal);
                }
                for (int x = 0; x < paper.length; x++) {
                    for (int y = splitVal + 1; y < paper[0].length; y++) {
                        int distFromEdge = y - (splitVal + 1);
                        int newY = splitVal - 1 - distFromEdge;
                        newPaper[x][newY] = paper[x][y] || newPaper[x][newY];
                    }
                }
                paper = newPaper;
            }
        }
        for (int y = 0; y < paper[0].length; y++) {
            for (boolean[] booleans : paper) {
                if (booleans[y]) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
