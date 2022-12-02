package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.Arrays;

public class Question2 {
    public static void main(String[] args) throws IOException {
        Q2Part1.run();
        Q2Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 2);
    }
}

class Q2Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question2.getInput();
        int total = 0;
        for (int i = 0; i < input.length; i++) {
            String[] line = input[i].split(" ");
            if (line[1].equals("X")) {
                total += 1;
                if (line[0].equals("A")) {
                    total += 3;
                }
                if (line[0].equals("C")) {
                    total += 6;
                }
            }
            if (line[1].equals("Y")) {
                total += 2;
                if (line[0].equals("B")) {
                    total += 3;
                }
                if (line[0].equals("A")) {
                    total += 6;
                }
            }
            if (line[1].equals("Z")) {
                total += 3;
                if (line[0].equals("C")) {
                    total += 3;
                }
                if (line[0].equals("B")) {
                    total += 6;
                }
            }
        }
        System.out.println(total);
        System.out.println("1 line function:");
        singleLineSolution(input);
    }

    private static void singleLineSolution(String[] input) {
        System.out.println(
                Arrays.stream(input)
                    .map(line -> line.split(" ")[1].equals("X") && line.split(" ")[0].equals("A") ? 4 :
                            line.split(" ")[1].equals("X") && line.split(" ")[0].equals("B") ? 1 :
                            line.split(" ")[1].equals("X") && line.split(" ")[0].equals("C") ? 7 :
                            line.split(" ")[1].equals("Y") && line.split(" ")[0].equals("A") ? 8 :
                            line.split(" ")[1].equals("Y") && line.split(" ")[0].equals("B") ? 5 :
                            line.split(" ")[1].equals("Y") && line.split(" ")[0].equals("C") ? 2 :
                            line.split(" ")[1].equals("Z") && line.split(" ")[0].equals("A") ? 3 :
                            line.split(" ")[1].equals("Z") && line.split(" ")[0].equals("B") ? 9 : 6)
                    .reduce(0, Integer::sum)
        );
    }
}

class Q2Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question2.getInput();
        int total = 0;
        for (int i = 0; i < input.length; i++) {
            String[] line = input[i].split(" ");
            if (line[1].equals("X")) {
                total += 0;
                if (line[0].equals("A")) {
                    total += 3;
                }
                if (line[0].equals("B")) {
                    total += 1;
                }
                if (line[0].equals("C")) {
                    total += 2;
                }
            }
            if (line[1].equals("Y")) {
                total += 3;
                if (line[0].equals("A")) {
                    total += 1;
                }
                if (line[0].equals("B")) {
                    total += 2;
                }
                if (line[0].equals("C")) {
                    total += 3;
                }
            }
            if (line[1].equals("Z")) {
                total += 6;
                if (line[0].equals("A")) {
                    total += 2;
                }
                if (line[0].equals("B")) {
                    total += 3;
                }
                if (line[0].equals("C")) {
                    total += 1;
                }
            }
        }
        System.out.println(total);
        System.out.println("1 line function:");
        singleLineSolution(input);
    }

    private static void singleLineSolution(String[] input) {
        System.out.println(
                Arrays.stream(input)
                    .map(line -> line.split(" ")[1].equals("X") && line.split(" ")[0].equals("A") ? 3 :
                        line.split(" ")[1].equals("X") && line.split(" ")[0].equals("B") ? 1 :
                        line.split(" ")[1].equals("X") && line.split(" ")[0].equals("C") ? 2 :
                        line.split(" ")[1].equals("Y") && line.split(" ")[0].equals("A") ? 4 :
                        line.split(" ")[1].equals("Y") && line.split(" ")[0].equals("B") ? 5 :
                        line.split(" ")[1].equals("Y") && line.split(" ")[0].equals("C") ? 6 :
                        line.split(" ")[1].equals("Z") && line.split(" ")[0].equals("A") ? 8 :
                        line.split(" ")[1].equals("Z") && line.split(" ")[0].equals("B") ? 9 : 7)
                    .reduce(0, Integer::sum)
        );
    }
}
