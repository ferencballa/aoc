package year2021.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

public class Question16 {
    private static int countVersions = 0;
    private static int[] input;

    public static void main(String[] args) throws IOException {
        String[] in = Files.readAllLines(Path.of("src/year2021/input/Question16.txt")).toArray(new String[0]);
        String[] inp = in[0].split("");
        input = new int[inp.length * 4];
        for (int i = 0; i < inp.length; i++) {
            int one = 0;
            int two = 0;
            int three = 0;
            int four = 0;
            switch(inp[i]) {
                case "F" :
                    one = 1;
                case "7":
                    two = 1;
                case "3":
                    three = 1;
                case "1":
                    four = 1;
                    break;
                case "E":
                    one = 1;
                case "6":
                    two = 1;
                case "2":
                    three = 1;
                    break;
                case "D":
                    one = 1;
                case "5":
                    two = 1;
                    four = 1;
                    break;
                case "C":
                    one = 1;
                case "4":
                    two = 1;
                    break;
                case "B":
                    one = 1;
                    three = 1;
                    four = 1;
                    break;
                case "A":
                    one = 1;
                    three = 1;
                    break;
                case "9":
                    one = 1;
                    four = 1;
                    break;
                case "8":
                    one = 1;
                    break;
            }
            input[i*4] = one;
            input[i*4+1] = two;
            input[i*4+2] = three;
            input[i*4+3] = four;
        }
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
            total = unpackLiteral(6).value;
        } else {
            total = unpackOperator(6, outerType).value;
        }
        System.out.println(total);

    }

    private static IndexAndValue unpackLiteral(int index) {
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
        return new IndexAndValue(index, value);
    }

    private static IndexAndValue unpackOperator(int index, int type) {
        ArrayList<Long> subValues = new ArrayList<>();
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
                    IndexAndValue res = unpackLiteral(index);
                    index = res.index;
                    subValues.add(res.value);
                } else {
                    IndexAndValue res = unpackOperator(index, outerType);
                    index = res.index;
                    subValues.add(res.value);
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
                    IndexAndValue res = unpackLiteral(index);
                    index = res.index;
                    subValues.add(res.value);
                } else {
                    IndexAndValue res = unpackOperator(index, outerType);
                    index = res.index;
                    subValues.add(res.value);
                }
            }
        }
        switch (type) {
            case 0:
                long sum = 0;
                for (Long subValue : subValues) {
                    sum += subValue;
                }
                return new IndexAndValue(index, sum);
            case 1:
                long product = 1;
                for (Long subValue : subValues) {
                    product *= subValue;
                }
                return new IndexAndValue(index, product);
            case 2:
                return new IndexAndValue(index, Collections.min(subValues));
            case 3:
                return new IndexAndValue(index, Collections.max(subValues));
            case 5:
                return new IndexAndValue(index, subValues.get(0) > subValues.get(1) ? 1 : 0);
            case 6:
                return new IndexAndValue(index, subValues.get(0) < subValues.get(1) ? 1 : 0);
            case 7:
                return new IndexAndValue(index, subValues.get(0).equals(subValues.get(1)) ? 1 : 0);
        }
        System.out.println("Unrecognized type");
        return new IndexAndValue(index, -1);
    }
}

class IndexAndValue {
    int index;
    long value;

    public IndexAndValue(int i, long v) {
        index = i;
        value = v;
    }
}
