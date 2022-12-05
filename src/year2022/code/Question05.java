package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;

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
        int l = 0;
        while (input[l].charAt(1) != '1') {
            l++;
        }
        int numOfCols = (int) Math.ceil((double) input[l].length() / 4);
        ArrayList<String>[] stacks = new ArrayList[numOfCols];
        for (int col = 0; col < numOfCols; col++) {
            stacks[col] = new ArrayList<>();
        }
        for (int row = 0; row < l; row++) {
            for (int pointInCol = 0; pointInCol < numOfCols; pointInCol++) {
                if (pointInCol < Math.ceil((double) input[row].length() / 4)) {
                    if (input[row].charAt(pointInCol * 4 + 1) != ' ') {
                        stacks[pointInCol].add(input[row].substring(pointInCol * 4 + 1, pointInCol * 4 + 2));
                    }
                }
            }
        }
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
        int l = 0;
        while (input[l].charAt(1) != '1') {
            l++;
        }
        int numOfCols = (int) Math.ceil((double) input[l].length() / 4);
        ArrayList<String>[] stacks = new ArrayList[numOfCols];
        for (int col = 0; col < numOfCols; col++) {
            stacks[col] = new ArrayList<>();
        }
        for (int row = 0; row < l; row++) {
            for (int pointInCol = 0; pointInCol < numOfCols; pointInCol++) {
                if (pointInCol < Math.ceil((double) input[row].length() / 4)) {
                    if (input[row].charAt(pointInCol * 4 + 1) != ' ') {
                        stacks[pointInCol].add(input[row].substring(pointInCol * 4 + 1, pointInCol * 4 + 2));
                    }
                }
            }
        }
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
