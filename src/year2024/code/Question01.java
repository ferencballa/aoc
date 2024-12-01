package year2024.code;

import helpers.Helper;

import java.io.IOException;
import java.util.Arrays;

public class Question01 {
    public static void main(String[] args) throws IOException {
        Q01Part1.run();
        Q01Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 1);
    }
}

class Q01Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question01.getInput();
        int[] list1 = new int[input.length];
        int[] list2 = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            String s = input[i];
            String[] parts = s.split("   ");
            list1[i] = Integer.parseInt(parts[0]);
            list2[i] = Integer.parseInt(parts[1]);
        }
        list1 = Arrays.stream(list1).sorted().toArray();
        list2 = Arrays.stream(list2).sorted().toArray();
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += Math.abs(list1[i] - list2[i]);
        }
        System.out.println(sum);
    }
}

class Q01Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question01.getInput();
        int[] list1 = new int[input.length];
        int[] list2 = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            String s = input[i];
            String[] parts = s.split("   ");
            list1[i] = Integer.parseInt(parts[0]);
            list2[i] = Integer.parseInt(parts[1]);
        }
        list1 = Arrays.stream(list1).sorted().toArray();
        list2 = Arrays.stream(list2).sorted().toArray();
        int sum = 0;
        int index2 = 0;
        for (int i = 0; i < input.length; i++) {
            int count = 0;
            while (index2 < input.length && list2[index2] <= list1[i]) {
                if (list2[index2] == list1[i]) {
                    count++;
                }
                index2++;
            }
            sum += list1[i] * count;
        }
        System.out.println(sum);
    }
}
