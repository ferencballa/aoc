package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

public class Question11 {
    public static void main(String[] args) throws IOException {
        Q11Part1.run();
        Q11Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 11);
    }
}

class Q11Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question11.getInput();
        ArrayList<Monkey> monkeys = new ArrayList<>();
        for (int i = 0; i < input.length; i+= 7) {
            String[] itemParts = input[i+1].split(": ");
            String[] items = itemParts[1].trim().split(", ");
            int[] itemNumsInt = Helper.StringArrayToInt(items);
            long[] itemNums = new long[itemNumsInt.length];
            for (int in =0; in < itemNums.length; in++) {
                itemNums[in] = (long) itemNumsInt[in];
            }
            ArrayList<Long> itemArray = new ArrayList<>();
            for (long it : itemNums) {
                itemArray.add(it);
            }
            String[] opLineParts = input[i+2].split(" = ");
            String[] opParts = opLineParts[1].trim().split(" ");
            Function<Long, Long> func;
            if (opParts[0].equals("old")) {
                if (opParts[1].equals("+")) {
                    if (opParts[2].equals("old")) {
                        func = x -> x + x;
                    } else {
                        func = x -> x + Long.parseLong(opParts[2]);
                    }
                } else {
                    if (opParts[2].equals("old")) {
                        func = x -> x * x;
                    } else {
                        func = x -> x * Long.parseLong(opParts[2]);
                    }
                }
            } else {
                if (opParts[1].equals("+")) {
                    if (opParts[2].equals("old")) {
                        func = x -> Long.parseLong(opParts[0]) + x;
                    } else {
                        func = x -> Long.parseLong(opParts[0]) + Long.parseLong(opParts[2]);
                    }
                } else {
                    if (opParts[2].equals("old")) {
                        func = x -> Long.parseLong(opParts[0]) * x;
                    } else {
                        func = x -> Long.parseLong(opParts[0]) * Long.parseLong(opParts[2]);
                    }
                }
            }
            int div = Integer.parseInt(input[i+3].trim().split(" ")[3]);
            int iTrue = Integer.parseInt(input[i+4].trim().split(" ")[5]);
            int iFalse = Integer.parseInt(input[i+5].trim().split(" ")[5]);
            monkeys.add(new Monkey(itemArray, func, div, iTrue, iFalse));
        }
        for (int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeys) {
                while (!monkey.items.isEmpty()) {
                    monkey.itemsInspected++;
                    long worryLevel = monkey.items.remove(0);
                    worryLevel = monkey.operation.apply(worryLevel);
                    worryLevel = Math.floorDiv(worryLevel, 3);
                    if (worryLevel % monkey.divisibleBy == 0) {
                        monkeys.get(monkey.indexTrue).items.add(worryLevel);
                    } else {
                        monkeys.get(monkey.indexFalse).items.add(worryLevel);
                    }
                }
            }
        }
        int high1 = Integer.MIN_VALUE;
        int high2 = Integer.MIN_VALUE;
        for (Monkey monkey : monkeys) {
            if (monkey.itemsInspected >= high1) {
                high2 = high1;
                high1 = monkey.itemsInspected;
            } else if (monkey.itemsInspected > high2) {
                high2 = monkey.itemsInspected;
            }
        }
        System.out.println(high1 * high2);
    }
}

class Q11Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question11.getInput();
        ArrayList<Monkey> monkeys = new ArrayList<>();
        for (int i = 0; i < input.length; i+= 7) {
            String[] itemParts = input[i+1].split(": ");
            String[] items = itemParts[1].trim().split(", ");
            int[] itemNumsInt = Helper.StringArrayToInt(items);
            long[] itemNums = new long[itemNumsInt.length];
            for (int in =0; in < itemNums.length; in++) {
                itemNums[in] = itemNumsInt[in];
            }
            ArrayList<Long> itemArray = new ArrayList<>();
            for (long it : itemNums) {
                itemArray.add(it);
            }
            String[] opLineParts = input[i+2].split(" = ");
            String[] opParts = opLineParts[1].trim().split(" ");
            Function<Long, Long> func;
            if (opParts[0].equals("old")) {
                if (opParts[1].equals("+")) {
                    if (opParts[2].equals("old")) {
                        func = x -> x + x;
                    } else {
                        func = x -> x + Long.parseLong(opParts[2]);
                    }
                } else {
                    if (opParts[2].equals("old")) {
                        func = x -> x * x;
                    } else {
                        func = x -> x * Long.parseLong(opParts[2]);
                    }
                }
            } else {
                if (opParts[1].equals("+")) {
                    if (opParts[2].equals("old")) {
                        func = x -> Long.parseLong(opParts[0]) + x;
                    } else {
                        func = x -> Long.parseLong(opParts[0]) + Long.parseLong(opParts[2]);
                    }
                } else {
                    if (opParts[2].equals("old")) {
                        func = x -> Long.parseLong(opParts[0]) * x;
                    } else {
                        func = x -> Long.parseLong(opParts[0]) * Long.parseLong(opParts[2]);
                    }
                }
            }
            int div = Integer.parseInt(input[i+3].trim().split(" ")[3]);
            int iTrue = Integer.parseInt(input[i+4].trim().split(" ")[5]);
            int iFalse = Integer.parseInt(input[i+5].trim().split(" ")[5]);
            monkeys.add(new Monkey(itemArray, func, div, iTrue, iFalse));
        }
        long divisorForAllMonkeys = 1;
        for (Monkey monkey : monkeys) {
            divisorForAllMonkeys *= monkey.divisibleBy;
        }
        for (int i = 0; i < 10000; i++) {
            for (Monkey monkey : monkeys) {
                while (!monkey.items.isEmpty()) {
                    monkey.itemsInspected++;
                    long worryLevel = monkey.items.remove(0);
                    worryLevel = monkey.operation.apply(worryLevel);
                    worryLevel %= divisorForAllMonkeys;
                    if (worryLevel % monkey.divisibleBy == 0) {
                        monkeys.get(monkey.indexTrue).items.add(worryLevel);
                    } else {
                        monkeys.get(monkey.indexFalse).items.add(worryLevel);
                    }
                }
            }
        }
        long high1 = Integer.MIN_VALUE;
        long high2 = Integer.MIN_VALUE;
        for (Monkey monkey : monkeys) {
            if (monkey.itemsInspected >= high1) {
                high2 = high1;
                high1 = monkey.itemsInspected;
            } else if (monkey.itemsInspected > high2) {
                high2 = monkey.itemsInspected;
            }
        }
        System.out.println(high1 * high2);
    }
}

class Monkey {
    ArrayList<Long> items;
    Function<Long, Long> operation;
    int divisibleBy;
    int indexTrue;
    int indexFalse;
    int itemsInspected;


    public Monkey(ArrayList<Long> items, Function<Long, Long> op, int div, int iTrue, int iFalse) {
        this.items = items;
        operation = op;
        divisibleBy = div;
        indexTrue = iTrue;
        indexFalse = iFalse;
        itemsInspected = 0;
    }
}
