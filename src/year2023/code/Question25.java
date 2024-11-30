package year2023.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Question25 {
    public static void main(String[] args) throws IOException {
        Q25Part1.run();
        Q25Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 25);
    }
}

class Q25Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question25.getInput();
        ArrayList<String[]> wires = new ArrayList<>();
        HashMap<String, ArrayList<String>> connectionsToOtherComponents = new HashMap<>();
        for (String line : input) {
            String[] lineParts = line.split(": ");
            String from = lineParts[0];
            String[] destinations = lineParts[1].split(" ");
            for (String dest : destinations) {
                if (!(from.equals("gbc") && dest.equals("hxr")) && !(from.equals("tmt") && dest.equals("pnz")) && !(from.equals("xkz") && dest.equals("mvv"))) {
                    wires.add(new String[]{from, dest});
                    ArrayList<String> connectionsOfFrom = new ArrayList<>();
                    ArrayList<String> connectionsOfDest = new ArrayList<>();
                    if (connectionsToOtherComponents.containsKey(from)) {
                        connectionsOfFrom = connectionsToOtherComponents.get(from);
                    }
                    if (connectionsToOtherComponents.containsKey(dest)) {
                        connectionsOfDest = connectionsToOtherComponents.get(dest);
                    }
                    connectionsOfFrom.add(dest);
                    connectionsOfDest.add(from);
                    connectionsToOtherComponents.put(from, connectionsOfFrom);
                    connectionsToOtherComponents.put(dest, connectionsOfDest);
                }
            }
        }
        int componentCounter = 0;
        HashSet<String> visitedComponents = new HashSet<>();
        String startComponent = "mvv";
        visitedComponents.add(startComponent);
        ArrayList<String> componentQueue = new ArrayList<>();
        componentQueue.add(startComponent);
        while (!componentQueue.isEmpty()) {
            String currentComponent = componentQueue.remove(0);
            componentCounter++;
            ArrayList<String> wiresForCurComponent = connectionsToOtherComponents.get(currentComponent);
            for (String nextComponent : wiresForCurComponent) {
                if (!visitedComponents.contains(nextComponent)) {
                    componentQueue.add(nextComponent);
                    visitedComponents.add(nextComponent);
                }
            }
        }
        System.out.println(componentCounter * (connectionsToOtherComponents.keySet().size() - componentCounter));
        /*for (int i = wires.size() - 1; i >= 0; i--) {
            //System.out.println(wires.get(i)[0] + " -- " + wires.get(i)[1] + " [tooltip=\"{from} to {to}\"]");
            if (wires.get(i)[0].equals("gbc") && wires.get(i)[1].equals("hxr") || wires.get(i)[0].equals("tmt") && wires.get(i)[1].equals("pnz") || wires.get(i)[0].equals("xkz") && wires.get(i)[1].equals("mvv")) {
                wires.remove(i);
            }
        }*/
        /*wireLoop: for (int i = 0; i < wires.size() - 2; i++) {
            for (int j = i + 1; j < wires.size() - 1; j++) {
                for (int k = j + 1; k < wires.size(); k++) {
                    String[] wire1 = wires.get(i);
                    String[] wire2 = wires.get(j);
                    String[] wire3 = wires.get(k);
                    int componentCounter = 0;
                    HashSet<String> visitedComponents = new HashSet<>();
                    String startComponent = wires.get(0)[0];
                    visitedComponents.add(startComponent);
                    ArrayList<String> componentQueue = new ArrayList<>();
                    componentQueue.add(startComponent);
                    while (!componentQueue.isEmpty()) {
                        String currentComponent = componentQueue.remove(0);
                        componentCounter++;
                        ArrayList<String> wiresForCurComponent = connectionsToOtherComponents.get(currentComponent);
                        for (String nextComponent : wiresForCurComponent) {
                            if (!visitedComponents.contains(nextComponent) &&
                            !((wire1[0].equals(currentComponent) && wire1[1].equals(nextComponent)) || (wire1[0].equals(nextComponent) && wire1[1].equals(currentComponent))) &&
                            !((wire2[0].equals(currentComponent) && wire2[1].equals(nextComponent)) || (wire2[0].equals(nextComponent) && wire2[1].equals(currentComponent))) &&
                            !((wire3[0].equals(currentComponent) && wire3[1].equals(nextComponent)) || (wire3[0].equals(nextComponent) && wire3[1].equals(currentComponent)))) {
                                componentQueue.add(nextComponent);
                                visitedComponents.add(nextComponent);
                            }
                        }
                    }
                    if (componentCounter < connectionsToOtherComponents.keySet().size()) {
                        System.out.println(componentCounter * (connectionsToOtherComponents.keySet().size() - componentCounter));
                        break wireLoop;
                    }
                }
            }
        }*/
    }
}

class Q25Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question25.getInput();
    }
}
