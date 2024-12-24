package year2024.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Question24 {
    public static void main(String[] args) throws IOException {
        Q24Part1.run();
        Q24Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 24);
    }
}

class Q24Part1 {

    private static HashMap<String, Boolean> values = new HashMap<>();
    private static HashMap<String, String[]> operations = new HashMap<>();

    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question24.getInput();
        ArrayList<String> zValues = new ArrayList<>();
        int inputIndex = 0;
        while (!input[inputIndex].equals("")) {
            String[] parts = input[inputIndex].split(": ");
            values.put(parts[0], parts[1].equals("1"));
            inputIndex++;
        }
        inputIndex++;
        while (inputIndex < input.length) {
            String[] parts = input[inputIndex].split(" -> ");
            operations.put(parts[1], parts[0].split(" "));
            if (parts[1].startsWith("z")) {
                zValues.add(parts[1]);
            }
            inputIndex++;
        }
        Collections.sort(zValues);
        long outputVal = 0;
        for (int i = 0; i < zValues.size(); i++) {
            if (getValue(zValues.get(i))) {
                outputVal += Math.pow(2, i);
            }
        }
        System.out.println(outputVal);
    }

    private static boolean getValue(String s) {
        if (values.containsKey(s)) {
            return values.get(s);
        }
        String[] operation = operations.get(s);
        boolean val1 = getValue(operation[0]);
        boolean val2 = getValue(operation[2]);
        boolean result = false;
        if (operation[1].equals("AND")) {
            result = val1 && val2;
        } else if (operation[1].equals("OR")) {
            result = val1 || val2;
        } else {
            result = val1 ^ val2;
        }
        values.put(s, result);
        return result;
    }
}

class Q24Part2 {

    private static HashMap<String, Boolean> values = new HashMap<>();
    private static HashMap<String, String[]> operations = new HashMap<>();
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question24.getInput();
        ArrayList<String> zValues = new ArrayList<>();
        ArrayList<String> outputWires = new ArrayList<>();
        int inputIndex = 0;
        while (!input[inputIndex].equals("")) {
            String[] parts = input[inputIndex].split(": ");
            values.put(parts[0], parts[1].equals("1"));
            inputIndex++;
        }
        inputIndex++;
        while (inputIndex < input.length) {
            String[] parts = input[inputIndex].split(" -> ");
            operations.put(parts[1], parts[0].split(" "));
            if (parts[1].startsWith("z")) {
                zValues.add(parts[1]);
            }
            outputWires.add(parts[1]);
            inputIndex++;
        }
        Collections.sort(zValues);
        for (int i = 0; i < zValues.size(); i++) {
            System.out.println(zValues.get(i) + " = " + getValue(zValues.get(i)));
        }
    }

    private static String getValue(String s) {
        if (values.containsKey(s)) {
            return s;
        }
        String[] operation = operations.get(s);
        String val1 = getValue(operation[0]);
        String val2 = getValue(operation[2]);
        return "(" + val1 + " " + operation[1] + " " + val2 + ")";
        //solved in notepad: from top to bottom check if values are correct or need to be swapped.
        //z00 = x00 ^ y00
        //z_n = (x_n ^ y_n) ^ ((x_n-1 & y_n-1) | ((n-2)) where n-2 = ((x_n-2 & y_n-2) | ((n-3)), etc.
        //call the parts of z_n: a_n and b_n, where a_n the xor part, and b_n the recursive part.
        //then start simplifying recursively from the top, it should end up looking like:
        //z00 = a00
        //z01 = (b01 XOR a01)
        //z02 = (b02 XOR a02)
        //z03 = (b03 XOR a03)
        //z04 = (b04 XOR a04)
        //z05 = (b05 XOR a05)
        //z06 = (b06 XOR a06)
        //z07 = (b07 XOR a07)
        //z08 = (a08 XOR b08)
        //z09 = (b09 XOR a09)
        //etc. if a value doesn't match, search lower where the missing piece is, and swap it
    }
}
