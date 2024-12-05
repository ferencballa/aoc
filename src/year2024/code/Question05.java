package year2024.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question05 {
    public static void main(String[] args) throws IOException {
        Q05Part1.run();
        Q05Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 5);
    }
}

class Q05Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question05.getInput();
        ArrayList<int[]> pages = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> befores = new HashMap<>();
        for (String s : input) {
            if (s.matches("\\d+\\|\\d+")) {
                String[] line = s.split("\\|");
                int first = Integer.parseInt(line[0]);
                int second = Integer.parseInt(line[1]);
                ArrayList<Integer> vals = new ArrayList<>();
                if (befores.containsKey(second)) {
                    vals = befores.get(second);
                }
                vals.add(first);
                befores.put(second, vals);
            } else if (s.matches("\\d+(,\\d+)*")) {
                String[] line = s.split(",");
                pages.add(Helper.StringArrayToIntArray(line));
            }
        }
        int count = 0;
        for (int[] page : pages) {
            boolean mistake = false;
            pageCheck:
            for (int i = 0; i < page.length - 1; i++) {
                if (befores.containsKey(page[i])) {
                    ArrayList<Integer> rules = befores.get(page[i]);
                    for (int j = i + 1; j < page.length; j++) {
                        if (rules.contains(page[j])) {
                            mistake = true;
                            break pageCheck;
                        }
                    }
                }
            }
            if (!mistake) {
                count += page[(page.length - 1) / 2];
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
        ArrayList<int[]> pages = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> befores = new HashMap<>();
        for (String s : input) {
            if (s.matches("\\d+\\|\\d+")) {
                String[] line = s.split("\\|");
                int first = Integer.parseInt(line[0]);
                int second = Integer.parseInt(line[1]);
                ArrayList<Integer> vals = new ArrayList<>();
                if (befores.containsKey(second)) {
                    vals = befores.get(second);
                }
                vals.add(first);
                befores.put(second, vals);
            } else if (s.matches("\\d+(,\\d+)*")) {
                String[] line = s.split(",");
                pages.add(Helper.StringArrayToIntArray(line));
            }
        }
        int count = 0;
        for (int[] page : pages) {
            boolean mistake = false;
            pageCheck: for (int i = 0; i < page.length - 1; i++) {
                if (befores.containsKey(page[i])) {
                    ArrayList<Integer> rules = befores.get(page[i]);
                    for (int j = i + 1; j < page.length; j++) {
                        if (rules.contains(page[j])) {
                            mistake = true;
                            break pageCheck;
                        }
                    }
                }
            }
            if (mistake) {
                ArrayList<Integer> toBePlacedVals = new ArrayList<>();
                for (int v : page) {
                    toBePlacedVals.add(v);
                }
                ArrayList<Integer> newOrder = new ArrayList<>();
                while (!toBePlacedVals.isEmpty()) {
                    if (toBePlacedVals.size() == 1) {
                        newOrder.add(toBePlacedVals.remove(0));
                    } else {
                        searchNextVal:
                        for (int i = 0; i < toBePlacedVals.size(); i++) {
                            if (!befores.containsKey(toBePlacedVals.get(i))) {
                                newOrder.add(toBePlacedVals.remove(i));
                                break searchNextVal;
                            } else {
                                ArrayList<Integer> rules = befores.get(toBePlacedVals.get(i));
                                boolean valid = true;
                                for (int j = 0; j < toBePlacedVals.size(); j++) {
                                    if (j != i) {
                                        if (rules.contains(toBePlacedVals.get(j))) {
                                            valid = false;
                                        }
                                    }
                                }
                                if (valid) {
                                    newOrder.add(toBePlacedVals.remove(i));
                                    break searchNextVal;
                                }
                            }
                        }
                    }
                }
                count += newOrder.get((newOrder.size() - 1)/2);
            }
        }
        System.out.println(count);
    }
}
