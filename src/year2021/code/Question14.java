package year2021.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Question14 {
    private static final HashMap<String, HashMap<String, Long>> countsPerLayer = new HashMap<>();
    private static final HashMap<String, String> inserts = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2021, 14);
        for (int i = 2; i < input.length; i++) {
            String[] inpParts = input[i].split(" -> ");
            inserts.put(inpParts[0], inpParts[1]);
        }
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        ArrayList<String> cur = new ArrayList<>(Arrays.asList(input[0].split("")));
        for (int n = 0; n < 10; n++) {
            int curInitialLength = cur.size();
            int insertCount = 0;
            for (int i = 0; i < curInitialLength - 1; i++) {
                String str1 = cur.get(i + insertCount);
                String str2 = cur.get(i + 1 + insertCount);
                if (inserts.containsKey(str1 + str2)) {
                    cur.add(i + 1 + insertCount, inserts.get(str1 + str2));
                    insertCount++;
                }
            }
        }
        HashMap<String, Integer> counts = new HashMap<>();
        for (String str : cur) {
            if (counts.containsKey(str)) {
                counts.put(str, counts.get(str) + 1);
            } else {
                counts.put(str, 1);
            }
        }
        int lowestC = Integer.MAX_VALUE;
        int highestC = 0;
        for (int val : counts.values()) {
            if (val > highestC) {
                highestC = val;
            }
            if (val < lowestC) {
                lowestC = val;
            }
        }
        System.out.println(highestC - lowestC);
    }

    private static void part2(String[] input) {
        ArrayList<String> cur = new ArrayList<>(Arrays.asList(input[0].split("")));
        HashMap<String, Long> totalCounts = new HashMap<>();
        for (int i = 0; i < cur.size() - 1; i++) {
            String str1 = cur.get(i);
            if (totalCounts.containsKey(str1)) {
                totalCounts.put(str1, totalCounts.get(str1) + 1);
            } else {
                totalCounts.put(str1, 1L);
            }
            String str2 = cur.get(i + 1);
            if (!countsPerLayer.containsKey("40" + str1 + str2)) {
                addCounts(str1, str2, 40);
            }
            HashMap<String, Long> curMap = countsPerLayer.get("40" + str1 + str2);
            for (String s : curMap.keySet()) {
                if (totalCounts.containsKey(s)) {
                    totalCounts.put(s, totalCounts.get(s) + curMap.get(s));
                } else {
                    totalCounts.put(s, curMap.get(s));
                }
            }
        }
        String lastStr = cur.get(cur.size() - 1);
        if (totalCounts.containsKey(lastStr)) {
            totalCounts.put(lastStr, totalCounts.get(lastStr) + 1);
        } else {
            totalCounts.put(lastStr, 1L);
        }
        long lowestC = Long.MAX_VALUE;
        long highestC = 0;
        for (long val : totalCounts.values()) {
            if (val > highestC) {
                highestC = val;
            }
            if (val < lowestC) {
                lowestC = val;
            }
        }
        System.out.println(highestC - lowestC);
    }

    private static void addCounts(String str1, String str2, int depthToGo) {
        if (!countsPerLayer.containsKey(depthToGo + str1 + str2)) {
            if (inserts.containsKey(str1 + str2)) {
                String inb = inserts.get(str1 + str2);
                HashMap<String, Long> counts = new HashMap<>();
                counts.put(inb, 1L);
                if (depthToGo > 1) {
                    int nextDepth = depthToGo - 1;
                    if (!countsPerLayer.containsKey(nextDepth + str1 + inb)) {
                        addCounts(str1, inb, nextDepth);
                    }
                    HashMap<String, Long> curMap1 = countsPerLayer.get(nextDepth + str1 + inb);
                    for (String s : curMap1.keySet()) {
                        if (counts.containsKey(s)) {
                            counts.put(s, counts.get(s) + curMap1.get(s));
                        } else {
                            counts.put(s, curMap1.get(s));
                        }
                    }
                    if (!countsPerLayer.containsKey(nextDepth + inb + str2)) {
                        addCounts(inb, str2, nextDepth);
                    }
                    HashMap<String, Long> curMap2 = countsPerLayer.get(nextDepth + inb + str2);
                    for (String s : curMap2.keySet()) {
                        if (counts.containsKey(s)) {
                            counts.put(s, counts.get(s) + curMap2.get(s));
                        } else {
                            counts.put(s, curMap2.get(s));
                        }
                    }
                }
                countsPerLayer.put(depthToGo + str1 + str2, counts);
            } else {
                countsPerLayer.put(depthToGo + str1 + str2, new HashMap<>());
            }
        }
    }
}
