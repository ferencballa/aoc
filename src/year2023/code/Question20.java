package year2023.code;

import helpers.Helper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question20 {
    public static void main(String[] args) throws IOException {
        Q20Part1.run();
        Q20Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 20);
    }
}

class Q20Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question20.getInput();
        String[] startModules = new String[0];
        HashMap<String, Pair<Pair<String, HashMap<String, Boolean>>, String[]>> modules = new HashMap<>();
        for (String line : input) {
            if (line.startsWith("broadcaster")) {
                startModules = line.split(" -> ")[1].split(", ");
            } else {
                String[] parts = line.split(" -> ");
                Pair<Pair<String, HashMap<String, Boolean>>, String[]> module = new ImmutablePair<>(new ImmutablePair<>(parts[0].substring(0, 1), new HashMap<>()), parts[1].split(", "));
                if (module.getLeft().getLeft().equals("%")) {
                    module.getLeft().getRight().put("value", false);
                }
                modules.put(parts[0].substring(1), module);
            }
        }
        for (String inputModule : modules.keySet()) {
            for (String receivingModuleName : modules.get(inputModule).getRight()) {
                if (modules.containsKey(receivingModuleName)) {
                    Pair<Pair<String, HashMap<String, Boolean>>, String[]> receivingModule = modules.get(receivingModuleName);
                    if (receivingModule.getLeft().getLeft().equals("&")) {
                        receivingModule.getLeft().getRight().put(inputModule, false);
                        modules.put(receivingModuleName, receivingModule);
                    }
                }
            }
        }
        int totalLowCount = 0;
        int totalHighCount = 0;
        for (int i = 0; i < 1000; i++) {
            int lowCount = 1 + startModules.length;
            int highCount = 0;
            ArrayList<Pair<String, Pair<Boolean, String>>> pulses = new ArrayList<>();
            for (String startModule : startModules) {
                pulses.add(new ImmutablePair<>("broadcaster", new ImmutablePair<>(false, startModule)));
            }
            while (!pulses.isEmpty()) {
                Pair<String, Pair<Boolean, String>> pulse = pulses.remove(0);
                Pair<Pair<String, HashMap<String, Boolean>>, String[]> module = modules.get(pulse.getRight().getRight());
                if (module.getLeft().getLeft().equals("%")) {
                    if (!pulse.getRight().getLeft()) {
                        module.getLeft().getRight().put("value", !module.getLeft().getRight().get("value"));
                        modules.put(pulse.getRight().getRight(), module);
                        for (String nextModule : module.getRight()) {
                            if (module.getLeft().getRight().get("value")) {
                                highCount++;
                            } else {
                                lowCount++;
                            }
                            if (modules.containsKey(nextModule)) {
                                pulses.add(new ImmutablePair<>(pulse.getRight().getRight(), new ImmutablePair<>(module.getLeft().getRight().get("value"), nextModule)));
                            }
                        }
                    }
                } else {
                    module.getLeft().getRight().put(pulse.getLeft(), pulse.getRight().getLeft());
                    modules.put(pulse.getRight().getRight(), module);
                    boolean allOn = true;
                    for (String key : module.getLeft().getRight().keySet()) {
                        if (!module.getLeft().getRight().get(key)) {
                            allOn = false;
                            break;
                        }
                    }
                    for (String nextModule : module.getRight()) {
                        if (!allOn) {
                            highCount++;
                        } else {
                            lowCount++;
                        }
                        if (modules.containsKey(nextModule)) {
                            pulses.add(new ImmutablePair<>(pulse.getRight().getRight(), new ImmutablePair<>(!allOn, nextModule)));
                        }
                    }
                }
            }
            totalLowCount += lowCount;
            totalHighCount += highCount;
        }
        System.out.println(((long) totalLowCount) * ((long)totalHighCount));
    }
}

class Q20Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question20.getInput();
    }
}
