package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Question05 {
    public static void main(String[] args) throws IOException {
        Q05Part1.run();
        Q05Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 5);
    }
}

class Q05Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question05.getInput();
        ArrayList<String>[] stacks = new ArrayList[9];
        String[] stack0 = {"B", "S", "J", "Z", "V", "D", "G"};
        ArrayList<String> arrList0 = new ArrayList<>(Arrays.asList(stack0));
        stacks[0] = arrList0;
        String[] stack1 = {"P", "V", "G", "M", "S", "Z"};
        ArrayList<String> arrList1 = new ArrayList<>(Arrays.asList(stack1));
        stacks[1] = arrList1;
        String[] stack2 = {"F", "Q", "T", "W", "S", "B", "L", "C"};
        ArrayList<String> arrList2 = new ArrayList<>(Arrays.asList(stack2));
        stacks[2] = arrList2;
        String[] stack3 = {"Q", "V", "R", "M", "W", "G", "J", "H"};
        ArrayList<String> arrList3 = new ArrayList<>(Arrays.asList(stack3));
        stacks[3] = arrList3;
        String[] stack4 = {"D", "M", "F", "N", "S", "L", "C"};
        ArrayList<String> arrList4 = new ArrayList<>(Arrays.asList(stack4));
        stacks[4] = arrList4;
        String[] stack5 = {"D", "C", "G", "R"};
        ArrayList<String> arrList5 = new ArrayList<>(Arrays.asList(stack5));
        stacks[5] = arrList5;
        String[] stack6 = {"Q", "S", "D", "J", "R", "T", "G", "H"};
        ArrayList<String> arrList6 = new ArrayList<>(Arrays.asList(stack6));
        stacks[6] = arrList6;
        String[] stack7 = {"V", "F", "P"};
        ArrayList<String> arrList7 = new ArrayList<>(Arrays.asList(stack7));
        stacks[7] = arrList7;
        String[] stack8 = {"J", "T", "S", "R", "D"};
        ArrayList<String> arrList8 = new ArrayList<>(Arrays.asList(stack8));
        stacks[8] = arrList8;
        for (int i = 10; i < input.length; i++) {
            String[] parts = input[i].split(" ");
            int amount = Integer.parseInt(parts[1]);
            int start = Integer.parseInt(parts[3]) - 1;
            int finish = Integer.parseInt(parts[5]) - 1;
            for (int a = 0; a < amount; a++) {
                stacks[finish].add(0, stacks[start].remove(0));
            }
        }
        System.out.print(stacks[0].get(0));
        System.out.print(stacks[1].get(0));
        System.out.print(stacks[2].get(0));
        System.out.print(stacks[3].get(0));
        System.out.print(stacks[4].get(0));
        System.out.print(stacks[5].get(0));
        System.out.print(stacks[6].get(0));
        System.out.print(stacks[7].get(0));
        System.out.print(stacks[8].get(0));
        System.out.println("\n");
    }
}

class Q05Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question05.getInput();
        ArrayList<String>[] stacks = new ArrayList[9];
        String[] stack0 = {"B", "S", "J", "Z", "V", "D", "G"};
        ArrayList<String> arrList0 = new ArrayList<>(Arrays.asList(stack0));
        stacks[0] = arrList0;
        String[] stack1 = {"P", "V", "G", "M", "S", "Z"};
        ArrayList<String> arrList1 = new ArrayList<>(Arrays.asList(stack1));
        stacks[1] = arrList1;
        String[] stack2 = {"F", "Q", "T", "W", "S", "B", "L", "C"};
        ArrayList<String> arrList2 = new ArrayList<>(Arrays.asList(stack2));
        stacks[2] = arrList2;
        String[] stack3 = {"Q", "V", "R", "M", "W", "G", "J", "H"};
        ArrayList<String> arrList3 = new ArrayList<>(Arrays.asList(stack3));
        stacks[3] = arrList3;
        String[] stack4 = {"D", "M", "F", "N", "S", "L", "C"};
        ArrayList<String> arrList4 = new ArrayList<>(Arrays.asList(stack4));
        stacks[4] = arrList4;
        String[] stack5 = {"D", "C", "G", "R"};
        ArrayList<String> arrList5 = new ArrayList<>(Arrays.asList(stack5));
        stacks[5] = arrList5;
        String[] stack6 = {"Q", "S", "D", "J", "R", "T", "G", "H"};
        ArrayList<String> arrList6 = new ArrayList<>(Arrays.asList(stack6));
        stacks[6] = arrList6;
        String[] stack7 = {"V", "F", "P"};
        ArrayList<String> arrList7 = new ArrayList<>(Arrays.asList(stack7));
        stacks[7] = arrList7;
        String[] stack8 = {"J", "T", "S", "R", "D"};
        ArrayList<String> arrList8 = new ArrayList<>(Arrays.asList(stack8));
        stacks[8] = arrList8;
        for (int i = 10; i < input.length; i++) {
            String[] parts = input[i].split(" ");
            int amount = Integer.parseInt(parts[1]);
            int start = Integer.parseInt(parts[3]) - 1;
            int finish = Integer.parseInt(parts[5]) - 1;
            ArrayList<String> temp = new ArrayList<>();
            for (int a = 0; a < amount; a++) {
                temp.add(0, stacks[start].remove(0));
            }
            for (int a = 0; a < amount; a++) {
                stacks[finish].add(0, temp.remove(0));
            }
        }
        System.out.print(stacks[0].get(0));
        System.out.print(stacks[1].get(0));
        System.out.print(stacks[2].get(0));
        System.out.print(stacks[3].get(0));
        System.out.print(stacks[4].get(0));
        System.out.print(stacks[5].get(0));
        System.out.print(stacks[6].get(0));
        System.out.print(stacks[7].get(0));
        System.out.print(stacks[8].get(0));
        System.out.println("\n");
    }
}
