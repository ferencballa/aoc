package year2021.code;

import helpers.Helper;

import java.io.IOException;

public class Question02 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2021, 2);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        int hor = 0;
        int dep = 0;
        for (String inp : input) {
            String[] inputParts = inp.split(" ");
            switch (inputParts[0]) {
                case "down":
                    dep += Integer.parseInt(inputParts[1]);
                    break;
                case "up":
                    dep -= Integer.parseInt(inputParts[1]);
                    break;
                case "forward":
                    hor += Integer.parseInt(inputParts[1]);
                    break;
            }
        }
        System.out.println(hor * dep);
    }

    private static void part2(String[] input) {
        int hor = 0;
        int dep = 0;
        int aim = 0;
        for (String inp : input) {
            String[] inputParts = inp.split(" ");
            switch (inputParts[0]) {
                case "down":
                    aim += Integer.parseInt(inputParts[1]);
                    break;
                case "up":
                    aim -= Integer.parseInt(inputParts[1]);
                    break;
                case "forward":
                    hor += Integer.parseInt(inputParts[1]);
                    dep += Integer.parseInt(inputParts[1]) * aim;
                    break;
            }
        }
        System.out.println(hor * dep);
    }
}
