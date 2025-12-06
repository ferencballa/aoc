package year2025.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;

public class Question05 {
    public static void main(String[] args) throws IOException {
        Q05Part1.run();
        Q05Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2025, 5);
    }
}

class Q05Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question05.getInput();
        int inputIndex = 0;
        ArrayList<long[]> ranges = new ArrayList<>();
        while (!input[inputIndex].equals("")) {
            ranges.add(Helper.StringArrayToLongArray(input[inputIndex].split("-")));
            inputIndex++;
        }
        inputIndex++;
        ArrayList<Long> ids = new ArrayList<>();
        while (inputIndex < input.length) {
            ids.add(Long.parseLong(input[inputIndex]));
            inputIndex++;
        }
        int count = 0;
        for (Long id : ids) {
            for (long[] range : ranges) {
                if (id >= range[0] && id <= range[1]) {
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }
}

class Q05Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question05.getInput();
        int inputIndex = 0;
        ArrayList<long[]> ranges = new ArrayList<>();
        while (!input[inputIndex].equals("")) {
            ranges.add(Helper.StringArrayToLongArray(input[inputIndex].split("-")));
            inputIndex++;
        }
        long count = 0;
        count += ranges.get(0)[1] - ranges.get(0)[0] + 1;
        for (int i = 1; i < ranges.size(); i++) {
            ArrayList<long[]> subRanges = new ArrayList<>();
            subRanges.add(ranges.get(i).clone());
            for (int j = 0; j < i; j++) {
                ArrayList<long[]> newSubRanges = new ArrayList<>();
                long fullLow = ranges.get(j)[0];
                long fullHigh = ranges.get(j)[1];
                for (int sr = 0; sr < subRanges.size(); sr++) {
                    long subLow = subRanges.get(sr)[0];
                    long subHigh = subRanges.get(sr)[1];
                    if (subHigh < fullLow || subLow > fullHigh) {
                        newSubRanges.add(subRanges.get(sr).clone());
                    } else if (subLow < fullLow && subHigh > fullHigh) {
                        long[] lower = new long[2];
                        long[] higher = new long[2];
                        lower[0] = subLow;
                        lower[1] = fullLow - 1;
                        higher[0] = fullHigh + 1;
                        higher[1] = subHigh;
                        newSubRanges.add(lower);
                        newSubRanges.add(higher);
                    } else if (subLow < fullLow) {
                        long[] lower = new long[2];
                        lower[0] = subLow;
                        lower[1] = fullLow - 1;
                        newSubRanges.add(lower);
                    } else if (subHigh > fullHigh) {
                        long[] higher = new long[2];
                        higher[0] = fullHigh + 1;
                        higher[1] = subHigh;
                        newSubRanges.add(higher);
                    }
                }
                subRanges = newSubRanges;
            }
            for (long[] subRange : subRanges) {
                count += subRange[1] - subRange[0] + 1;
            }
        }
        System.out.println(count);
    }
}
