package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question21 {
    public static void main(String[] args) throws IOException {
        Q21Part1.run();
        Q21Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 21);
    }
}

class Q21Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question21.getInput();
        HashMap<String, Long> vals = new HashMap<>();
        ArrayList<YellingMonkey> toBeHandledMonkeys = new ArrayList<>();
        for (String inp : input) {
            String[] lineParts = inp.split(" ");
            if (lineParts.length == 2) {
                vals.put(lineParts[0].substring(0, lineParts[0].length() - 1), (long) Integer.parseInt(lineParts[1]));
            } else {
                toBeHandledMonkeys.add(new YellingMonkey(inp));
            }
        }
        boolean rootDone = false;
        while (!rootDone) {
            for (YellingMonkey monkey : toBeHandledMonkeys) {
                if (!monkey.done) {
                    String[] lineParts = monkey.val.split(" ");
                    if (vals.containsKey(lineParts[1]) && vals.containsKey(lineParts[3])) {
                        long newVal;
                        if (lineParts[2].equals("+")) {
                            newVal = vals.get(lineParts[1]) + vals.get(lineParts[3]);
                        } else if (lineParts[2].equals("-")) {
                            newVal = vals.get(lineParts[1]) - vals.get(lineParts[3]);
                        } else if (lineParts[2].equals("*")) {
                            newVal = vals.get(lineParts[1]) * vals.get(lineParts[3]);
                        } else {
                            newVal = vals.get(lineParts[1]) / vals.get(lineParts[3]);
                        }
                        vals.put(lineParts[0].substring(0, lineParts[0].length() - 1), newVal);
                        if (lineParts[0].substring(0, lineParts[0].length() - 1).equals("root")) {
                            rootDone = true;
                        }
                    }
                }
            }
        }
        System.out.println(vals.get("root"));
    }
}

class YellingMonkey {
    boolean done;
    String val;

    public YellingMonkey(String v) {
        val = v;
        done = false;
    }
}

class Q21Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question21.getInput();
        HashMap<String, Long> valsStart = new HashMap<>();
        ArrayList<YellingMonkey> toBeHandledMonkeys = new ArrayList<>();
        for (String inp : input) {
            String[] lineParts = inp.split(" ");
            if (lineParts.length == 2) {
                valsStart.put(lineParts[0].substring(0, lineParts[0].length() - 1), (long) Integer.parseInt(lineParts[1]));
            } else {
                toBeHandledMonkeys.add(new YellingMonkey(inp));
            }
        }
        boolean rootEqual = false;
        //long yourInput = 9999999999999L;
        //long yourInput = 3782852515584L;
        long yourInput = 3782852515583L;
        //long yourInput = 3782852515582L;
        long prevHigh = yourInput;
        long prevLow = 0;
        boolean tooHigh = false;
        boolean tooLow = false;
        while (!rootEqual) {
            System.out.println(yourInput);
            if (tooHigh) {
                prevLow = yourInput;
                yourInput = (yourInput + prevHigh)/2;
            }
            if (tooLow) {
                prevHigh = yourInput;
                yourInput = (yourInput + prevLow) / 2;
            }
            tooHigh = false;
            tooLow = false;
            if (yourInput % 100 == 0) {
                System.out.println(yourInput);
            }
            HashMap<String, Long> vals = new HashMap<>(valsStart);
            vals.put("humn", yourInput);
            boolean rootDone = false;
            while (!rootDone) {
                for (YellingMonkey monkey : toBeHandledMonkeys) {
                    if (!monkey.done) {
                        //monkey.done = true;
                        String[] lineParts = monkey.val.split(" ");
                        if (vals.containsKey(lineParts[1]) && vals.containsKey(lineParts[3])) {
                            if (!lineParts[0].substring(0, lineParts[0].length() - 1).equals("root")) {
                                long newVal;
                                if (lineParts[2].equals("+")) {
                                    newVal = vals.get(lineParts[1]) + vals.get(lineParts[3]);
                                } else if (lineParts[2].equals("-")) {
                                    newVal = vals.get(lineParts[1]) - vals.get(lineParts[3]);
                                } else if (lineParts[2].equals("*")) {
                                    newVal = vals.get(lineParts[1]) * vals.get(lineParts[3]);
                                } else {
                                    newVal = vals.get(lineParts[1]) / vals.get(lineParts[3]);
                                }
                                vals.put(lineParts[0].substring(0, lineParts[0].length() - 1), newVal);
                            } else {
                                rootDone = true;
                                if (vals.get(lineParts[1]) > vals.get(lineParts[3])) {
                                    tooHigh = true;
                                } else if (vals.get(lineParts[1]) < vals.get(lineParts[3])) {
                                    tooLow = true;
                                } else {
                                    rootEqual = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(yourInput);
    }
}
