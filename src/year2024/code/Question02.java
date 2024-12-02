package year2024.code;

import helpers.Helper;

import java.io.IOException;

public class Question02 {
    public static void main(String[] args) throws IOException {
        Q02Part1.run();
        Q02Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 2);
    }
}

class Q02Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question02.getInput();
        int count = 0;
        for (String s : input) {
            String[] vals = s.split(" ");
            boolean increasing = true;
            boolean decreasing = true;
            int index = 0;
            while ((increasing || decreasing) && index < vals.length - 1) {
                int val1 = Integer.parseInt(vals[index]);
                int val2 = Integer.parseInt(vals[index+1]);
                if (val1 == val2 || Math.abs(val1 - val2) > 3) {
                    increasing = false;
                    decreasing = false;
                }
                if (val1 > val2) {
                    increasing = false;
                }
                if (val1 < val2) {
                    decreasing = false;
                }
                index++;
            }
            if (increasing || decreasing) {
                count++;
            }
        }
        System.out.println(count);
    }
}

class Q02Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question02.getInput();
        int count = 0;
        for (String s : input) {
            String[] valsOriginal = s.split(" ");
            boolean increasingAtAll = false;
            boolean decreasingAtAll = false;
            for (int i = 0; i <= valsOriginal.length; i++) {
                String[] vals;
                if (i < valsOriginal.length) {
                    vals = new String[valsOriginal.length - 1];
                    for (int ind = 0; ind < i; ind++) {
                        vals[ind] = valsOriginal[ind];
                    }
                    for (int ind = i+1; ind < valsOriginal.length; ind++) {
                        vals[ind-1] = valsOriginal[ind];
                    }
                } else {
                    vals = valsOriginal;
                }
                boolean increasing = true;
                boolean decreasing = true;
                int index = 0;
                while ((increasing || decreasing) && index < vals.length - 1) {
                    int val1 = Integer.parseInt(vals[index]);
                    int val2 = Integer.parseInt(vals[index + 1]);
                    if (val1 == val2 || Math.abs(val1 - val2) > 3) {
                        increasing = false;
                        decreasing = false;
                    }
                    if (val1 > val2) {
                        increasing = false;
                    }
                    if (val1 < val2) {
                        decreasing = false;
                    }
                    index++;
                }
                increasingAtAll = increasingAtAll || increasing;
                decreasingAtAll = decreasingAtAll || decreasing;
            }
            if (increasingAtAll || decreasingAtAll) {
                count++;
            }
        }
        System.out.println(count);
    }
}
