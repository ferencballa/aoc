package year2023.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Question12 {
    public static void main(String[] args) throws IOException {
        Q12Part1.run();
        Q12Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 12);
    }
}

class Q12Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question12.getInput();
        System.out.println(input.length);
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            System.out.println(i);
            String[] parts = input[i].split(" ");
            String springs = parts[0].replaceAll("\\.+", ".");
            String[] lengths = parts[1].split(",");
            Integer[] brokenLengths = Arrays.stream(lengths).map(Integer::parseInt).toArray(Integer[]::new);
            int[] minLengthToGo = new int[brokenLengths.length];
            for (int m = minLengthToGo.length - 1; m >= 0; m--) {
                minLengthToGo[m] = brokenLengths[m];
                if (m != minLengthToGo.length - 1) {
                    minLengthToGo[m] += minLengthToGo[m+1] + 1;
                }
            }
            ArrayList<Integer> startPositionsNextBrokenSet = new ArrayList<>();
            for (int s = 0; s <= springs.length() - minLengthToGo[0]; s++) {
                if (springs.substring(s, s + brokenLengths[0]).matches("[?#]+") && !springs.substring(0, s).contains("#")) {
                    for (int rem = s + brokenLengths[0] + 1; rem <= springs.length() - minLengthToGo[0] + brokenLengths[0] + 1; rem++) {
                        if (!springs.substring(s + brokenLengths[0], rem).contains("#")) {
                            startPositionsNextBrokenSet.add(rem);
                        }
                    }
                }
            }
            for (int l = 1; l < brokenLengths.length; l++) {
                ArrayList<Integer> newStartPositionsNextBrokenSet = new ArrayList<>();
                for (int startPosition : startPositionsNextBrokenSet) {
                    if (startPosition + minLengthToGo[l] <= springs.length()) {
                        if (springs.substring(startPosition, startPosition + brokenLengths[l]).matches("[?#]+")) {
                            if (l == brokenLengths.length - 1) {
                                if (!springs.substring(startPosition + brokenLengths[l]).contains("#")) {
                                    newStartPositionsNextBrokenSet.add(0);
                                }
                            } else {
                                for (int rem = startPosition + brokenLengths[l] + 1; rem <= springs.length() - minLengthToGo[l] + brokenLengths[l] + 1; rem++) {
                                    if (!springs.substring(startPosition + brokenLengths[l], rem).contains("#")) {
                                        newStartPositionsNextBrokenSet.add(rem);
                                    }
                                }
                            }
                        }
                    }
                }
                startPositionsNextBrokenSet = newStartPositionsNextBrokenSet;
            }
            System.out.println("count: " + startPositionsNextBrokenSet.size());
            count += startPositionsNextBrokenSet.size();
        }
        System.out.println("total count: " + count);
    }
}

class Q12Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question12.getInput();
        System.out.println(input.length);
        long count = 0;
        for (int i = 0; i < input.length; i++) {
            System.out.println(i);
            String[] parts = input[i].split(" ");
            String springs = parts[0].replaceAll("\\.+", ".");
            springs = springs.replaceAll("(.+)", "$1?$1?$1?$1?$1");
            parts[1] = parts[1].replaceAll("(.+)", "$1,$1,$1,$1,$1");
            String[] lengths = parts[1].split(",");
            Integer[] brokenLengths = Arrays.stream(lengths).map(Integer::parseInt).toArray(Integer[]::new);
            int[] minLengthToGo = new int[brokenLengths.length];
            for (int m = minLengthToGo.length - 1; m >= 0; m--) {
                minLengthToGo[m] = brokenLengths[m];
                if (m != minLengthToGo.length - 1) {
                    minLengthToGo[m] += minLengthToGo[m+1] + 1;
                }
            }
            ArrayList<Integer> startPositionsNextBrokenSet = new ArrayList<>();
            HashMap<Integer, Long> timesPerIndex = new HashMap<>();
            for (int s = 0; s <= springs.length() - minLengthToGo[0]; s++) {
                if (springs.substring(s, s + brokenLengths[0]).matches("[?#]+") && !springs.substring(0, s).contains("#")) {
                    for (int rem = s + brokenLengths[0] + 1; rem <= springs.length() - minLengthToGo[0] + brokenLengths[0] + 1; rem++) {
                        if (!springs.substring(s + brokenLengths[0], rem).contains("#")) {
                            if (timesPerIndex.containsKey(rem)) {
                                timesPerIndex.put(rem, timesPerIndex.get(rem) + 1);
                            } else {
                                timesPerIndex.put(rem, 1L);
                                startPositionsNextBrokenSet.add(rem);
                            }
                        }
                    }
                }
            }
            for (int l = 1; l < brokenLengths.length; l++) {
                ArrayList<Integer> newStartPositionsNextBrokenSet = new ArrayList<>();
                HashMap<Integer, Long> newTimesPerIndex = new HashMap<>();
                for (int startPosition : startPositionsNextBrokenSet) {
                    long multiplier = timesPerIndex.get(startPosition);
                    if (startPosition + minLengthToGo[l] <= springs.length()) {
                        if (springs.substring(startPosition, startPosition + brokenLengths[l]).matches("[?#]+")) {
                            if (l == brokenLengths.length - 1) {
                                if (!springs.substring(startPosition + brokenLengths[l]).contains("#")) {
                                    count+= multiplier;
                                }
                            } else {
                                for (int rem = startPosition + brokenLengths[l] + 1; rem <= springs.length() - minLengthToGo[l] + brokenLengths[l] + 1; rem++) {
                                    if (!springs.substring(startPosition + brokenLengths[l], rem).contains("#")) {
                                        if (newTimesPerIndex.containsKey(rem)) {
                                            newTimesPerIndex.put(rem, newTimesPerIndex.get(rem) + multiplier);
                                        } else {
                                            newTimesPerIndex.put(rem, multiplier);
                                            newStartPositionsNextBrokenSet.add(rem);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                startPositionsNextBrokenSet = newStartPositionsNextBrokenSet;
                timesPerIndex = newTimesPerIndex;
            }
            System.out.println("count: " + count);
        }
        System.out.println("total count: " + count);
    }
}
