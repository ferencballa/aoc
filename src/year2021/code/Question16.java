package year2021.code;

import helpers.Helper;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

public class Question16 {
    private static int countVersions = 0;
    private static int[] input;

    public static void main(String[] args) throws IOException {
        String[] in = Files.readAllLines(Path.of("src/year2021/input/Question16.txt")).toArray(new String[0]);
        String binary = new BigInteger(in[0], 16).toString(2);
        input = Helper.StringArrayToInt((new String(new char[in[0].length() * 4 - binary.length()]).replace('\0', '0') + binary).split(""));
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }

    private static void part1() {
        countVersions += input[0] * 4 + input[1] * 2 + input[2];
        int outerType = input[3] * 4 + input[4] * 2 + input[5];
        if (outerType == 4) {
            unpackLiteral(6);
        } else {
            unpackOperator(6, outerType);
        }
        System.out.println(countVersions);
    }

    private static void part2() {
        int outerType = input[3] * 4 + input[4] * 2 + input[5];
        long total;
        if (outerType == 4) {
            total = (long)unpackLiteral(6).y;
        } else {
            total = (long)unpackOperator(6, outerType).y;
        }
        System.out.println(total);

    }

    private static Point2D.Double unpackLiteral(int index) {
        ArrayList<Integer> literalBits = new ArrayList<>();
        while (input[index] == 1) {
            literalBits.add(input[index + 1]);
            literalBits.add(input[index + 2]);
            literalBits.add(input[index + 3]);
            literalBits.add(input[index + 4]);
            index += 5;
        }
        literalBits.add(input[index + 1]);
        literalBits.add(input[index + 2]);
        literalBits.add(input[index + 3]);
        literalBits.add(input[index + 4]);
        index += 5;
        long value = 0;
        int size = literalBits.size();
        for (int i = 0; i < size; i++) {
            value += literalBits.get(i) * Math.pow(2, size - 1 - i);
        }
        return new Point2D.Double(index, value);
    }

    private static Point2D.Double unpackOperator(int index, int type) {
        ArrayList<Double> subValues = new ArrayList<>();
        if (input[index] == 0) {
            index++;
            int length = 0;
            for (int i = 0; i < 15; i++) {
                length += input[index] * Math.pow(2, 14 - i);
                index++;
            }
            int startIndex = index;
            while (index < startIndex + length) {
                countVersions += input[index] * 4 + input[index + 1] * 2 + input[index + 2];
                int outerType = input[index + 3] * 4 + input[index + 4] * 2 + input[index + 5];
                index += 6;
                if (outerType == 4) {
                    Point2D.Double res = unpackLiteral(index);
                    index = (int)res.x;
                    subValues.add(res.y);
                } else {
                    Point2D.Double res = unpackOperator(index, outerType);
                    index = (int)res.x;
                    subValues.add(res.y);
                }
            }
            if (index > startIndex + length) {
                System.out.println("Subs went on too long");
            }
        } else {
            index++;
            int numOfSubs = 0;
            for (int i = 0; i < 11; i++) {
                numOfSubs += input[index] * Math.pow(2, 10 - i);
                index++;
            }
            for (int i = 0; i < numOfSubs; i++) {
                countVersions += input[index] * 4 + input[index + 1] * 2 + input[index + 2];
                int outerType = input[index + 3] * 4 + input[index + 4] * 2 + input[index + 5];
                index += 6;
                if (outerType == 4) {
                    Point2D.Double res = unpackLiteral(index);
                    index = (int)res.x;
                    subValues.add(res.y);
                } else {
                    Point2D.Double res = unpackOperator(index, outerType);
                    index = (int)res.x;
                    subValues.add(res.y);
                }
            }
        }
        switch (type) {
            case 0:
                long sum = 0;
                for (Double subValue : subValues) {
                    sum += subValue;
                }
                return new Point2D.Double(index, sum);
            case 1:
                long product = 1;
                for (Double subValue : subValues) {
                    product *= subValue;
                }
                return new Point2D.Double(index, product);
            case 2:
                return new Point2D.Double(index, Collections.min(subValues));
            case 3:
                return new Point2D.Double(index, Collections.max(subValues));
            case 5:
                return new Point2D.Double(index, subValues.get(0) > subValues.get(1) ? 1 : 0);
            case 6:
                return new Point2D.Double(index, subValues.get(0) < subValues.get(1) ? 1 : 0);
            case 7:
                return new Point2D.Double(index, subValues.get(0).equals(subValues.get(1)) ? 1 : 0);
        }
        System.out.println("Unrecognized type");
        return new Point2D.Double(index, -1);
    }
}
