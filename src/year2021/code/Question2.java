package year2021.code;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Question2 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2021/input/Question2.txt")).toArray(new String[0]);
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
