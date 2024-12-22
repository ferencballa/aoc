package year2024.code;

import helpers.Helper;

import java.io.IOException;
import java.util.HashMap;

public class Question22 {
    public static void main(String[] args) throws IOException {
        Q22Part1.run();
        Q22Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 22);
    }
}

class Q22Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question22.getInput();
        int times = 2000;
        long count = 0;
        for (String s : input) {
            long val = Long.parseLong(s);
            for (int i = 0; i < times; i++) {
                long mix = val * 64;
                val ^= mix;
                val %= 16777216;
                mix = Math.floorDiv(val, 32);
                val ^= mix;
                val %= 16777216;
                mix = val * 2048;
                val ^= mix;
                val %= 16777216;
            }
            count += val;
        }
        System.out.println(count);
    }
}

class Q22Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question22.getInput();
        int times = 2000;
        HashMap<String, Long> map = new HashMap<>();
        for (String s : input) {
            HashMap<String, Long> mapForSeller = new HashMap<>();
            long val = Long.parseLong(s);
            long valPrev = val;
            val = doIteration(val);
            long diff1 = Long.parseLong(Long.toString(val).substring(Long.toString(val).length() - 1)) - Long.parseLong(Long.toString(valPrev).substring(Long.toString(valPrev).length() - 1));
            valPrev = val;
            val = doIteration(val);
            long diff2 = Long.parseLong(Long.toString(val).substring(Long.toString(val).length() - 1)) - Long.parseLong(Long.toString(valPrev).substring(Long.toString(valPrev).length() - 1));
            valPrev = val;
            val = doIteration(val);
            long diff3 = Long.parseLong(Long.toString(val).substring(Long.toString(val).length() - 1)) - Long.parseLong(Long.toString(valPrev).substring(Long.toString(valPrev).length() - 1));
            for (int i = 3; i < times; i++) {
                valPrev = val;
                val = doIteration(val);
                long diff4 = Long.parseLong(Long.toString(val).substring(Long.toString(val).length() - 1)) - Long.parseLong(Long.toString(valPrev).substring(Long.toString(valPrev).length() - 1));
                String key = Long.toString(diff1) + diff2 + diff3 + diff4;
                if (!mapForSeller.containsKey(key)) {
                    mapForSeller.put(key, Long.parseLong(Long.toString(val).substring(Long.toString(val).length() - 1)));
                }
                diff1 = diff2;
                diff2 = diff3;
                diff3 = diff4;
            }
            for (String key : mapForSeller.keySet()) {
                map.put(key, (map.containsKey(key) ? map.get(key) : 0) + mapForSeller.get(key));
            }
        }
        long maxVal = 0;
        for (String key : map.keySet()) {
            maxVal = Math.max(maxVal, map.get(key));
        }
        System.out.println(maxVal);
    }

    private static long doIteration(Long val) {
        long mix = val * 64;
        val ^= mix;
        val %= 16777216;
        mix = Math.floorDiv(val, 32);
        val ^= mix;
        val %= 16777216;
        mix = val * 2048;
        val ^= mix;
        val %= 16777216;
        return val;
    }
}
