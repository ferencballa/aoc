package year2021.code;
import helpers.Helper;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class Question16 {
    private static int countVersions = 0;
    private static int[] input;

    public static void main(String[] args) throws IOException {
        String[] in = Helper.getInputForYearAndTask(2021, 16);
        String binary = new BigInteger(in[0], 16).toString(2);
        input = Helper.StringArrayToIntArray((new String(new char[in[0].length() * 4 - binary.length()]).replace('\0', '0') + binary).split(""));
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }

    private static void part1() {
        countVersions += input[0] * 4 + input[1] * 2 + input[2];
        int outerType = input[3] * 4 + input[4] * 2 + input[5];
        unpack(6, outerType);
        System.out.println(countVersions);
    }

    private static void part2() {
        int outerType = input[3] * 4 + input[4] * 2 + input[5];
        long total;
        total = (long) unpack(6, outerType).y;
        System.out.println(total);
    }

    private static Point2D.Double unpack(int index, int type) {
        if (type == 4) {
            ArrayList<Integer> literalBits = new ArrayList<>();
            do {
                literalBits.add(input[index + 1]);
                literalBits.add(input[index + 2]);
                literalBits.add(input[index + 3]);
                literalBits.add(input[index + 4]);
                index += 5;
            } while (input[index - 5] == 1);
            long value = 0;
            int size = literalBits.size();
            for (int i = 0; i < size; i++) {
                value += literalBits.get(i) * Math.pow(2, size - 1 - i);
            }
            return new Point2D.Double(index, value);
        } else {
            ArrayList<Double> subValues = new ArrayList<>();
            index++;
            if (input[index - 1] == 0) {
                int length = 0;
                for (int i = 0; i < 15; i++, index++) {
                    length += input[index] * Math.pow(2, 14 - i);
                }
                int startIndex = index;
                while (index < startIndex + length) {
                    countVersions += input[index] * 4 + input[index + 1] * 2 + input[index + 2];
                    int outerType = input[index + 3] * 4 + input[index + 4] * 2 + input[index + 5];
                    Point2D.Double res = unpack(index + 6, outerType);
                    index = (int) res.x;
                    subValues.add(res.y);
                }
            } else {
                int numOfSubs = 0;
                for (int i = 0; i < 11; i++, index++) {
                    numOfSubs += input[index] * Math.pow(2, 10 - i);
                }
                for (int i = 0; i < numOfSubs; i++) {
                    countVersions += input[index] * 4 + input[index + 1] * 2 + input[index + 2];
                    int outerType = input[index + 3] * 4 + input[index + 4] * 2 + input[index + 5];
                    Point2D.Double res = unpack(index + 6, outerType);
                    index = (int) res.x;
                    subValues.add(res.y);
                }
            }
            switch (type) {
                case 0:
                    return new Point2D.Double(index, subValues.stream().reduce(0.0, Double::sum));
                case 1:
                    return new Point2D.Double(index, subValues.stream().reduce(1.0, (a, b) -> a * b));
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
        }
        return new Point2D.Double(-1, -1);
    }
}
