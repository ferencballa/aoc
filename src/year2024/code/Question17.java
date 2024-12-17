package year2024.code;

import helpers.Helper;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Question17 {
    public static void main(String[] args) throws IOException {
        Q17Part1.run();
        Q17Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 17);
    }
}

class Q17Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question17.getInput();
        long A = Long.parseLong(input[0].split(" ")[2]);
        long B = Long.parseLong(input[1].split(" ")[2]);
        long C = Long.parseLong(input[2].split(" ")[2]);
        int[] instructions = Helper.StringArrayToIntArray(input[4].split(" ")[1].split(","));
        int pointer = 0;
        ArrayList<Long> output = new ArrayList<>();
        while (pointer < instructions.length) {
            int opcode = instructions[pointer];
            int operand = instructions[pointer+1];
            long comboOperand = operand;
            if (operand == 4) {
                comboOperand = A;
            } else if (operand == 5) {
                comboOperand = B;
            } else if (operand == 6) {
                    comboOperand = C;
            }
            switch (opcode) {
                case 0:
                    A = (long) Math.floor(A / Math.pow(2, comboOperand));
                    break;
                case 1:
                    B = B ^ operand;
                    break;
                case 2:
                    B = comboOperand % 8;
                    break;
                case 3:
                    pointer = A == 0 ? pointer : operand - 2;
                    break;
                case 4:
                    B = B ^ C;
                    break;
                case 5:
                    output.add(comboOperand % 8);
                    break;
                case 6:
                    B = (long) Math.floor(A / Math.pow(2, comboOperand));
                    break;
                case 7:
                    C = (long) Math.floor(A / Math.pow(2, comboOperand));
                    break;
            }
            pointer += 2;
        }
        System.out.println(output.stream().map(Object::toString).collect(Collectors.joining(",")));
    }
}

class Q17Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question17.getInput();
        int[] instructions = Helper.StringArrayToIntArray(input[4].split(" ")[1].split(","));
        ArrayList<Pair<Integer, Long>> options = new ArrayList<>();
        options.add(Pair.of(0, 0L));
        ArrayList<Long> solutions = new ArrayList<>();
        while (!options.isEmpty()) {
            Pair<Integer, Long> option = options.remove(0);
            int instr = instructions[instructions.length - 1 - option.getLeft()];
            for (int dA = 0; dA < 8; dA++) {
                long A = option.getRight() * 8 + dA;
                if (instr == ((A % 8) ^ 5 ^ 6 ^ ((long) Math.floor(A / Math.pow(2, ((A % 8) ^ 5))))) % 8) {
                    if (option.getLeft() == instructions.length - 1) {
                        solutions.add(A);
                    } else {
                        options.add(Pair.of(option.getLeft() + 1, A));
                    }
                }
            }
        }
        solutions.sort(null);
        System.out.println(solutions.get(0));
    }
}
