package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Question16 {
    public static void main(String[] args) throws IOException {
        Q16Part1.run();
        Q16Part2.run();
        Q16Part2.randomBruteForce();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 16);
    }
}

class Q16Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question16.getInput();
        HashMap<String, Valve> valvesByName = new HashMap<>();
        for (String line : input) {
            String[] mainLineParts = line.trim().split("; tunnels? leads? to valves? ");
            String[] valveParts = mainLineParts[0].split(" ");
            String name = valveParts[1];;
            int rate = Integer.parseInt(valveParts[4].split("=")[1]);
            String[] neighbourNames = mainLineParts[1].split(", ");
            ArrayList<ValveWithDistance> neighbours = new ArrayList<>();
            for (String neighbourName : neighbourNames) {
                neighbours.add(new ValveWithDistance(neighbourName, 1));
            }
            Valve v = new Valve(rate, neighbours);
            valvesByName.put(name, v);
        }
        ArrayList<String> removalList = new ArrayList<>();
        for (String key : valvesByName.keySet()) {
            if (valvesByName.get(key).flow == 0) {
                ArrayList<ValveWithDistance> neighboursWithDistances = valvesByName.get(key).neighboursWithDistance;
                for (int i = 0; i < neighboursWithDistances.size() - 1; i++) {
                    for (int j = i + 1; j < neighboursWithDistances.size(); j++) {
                        ValveWithDistance vwd1 = neighboursWithDistances.get(i);
                        ValveWithDistance vwd2 = neighboursWithDistances.get(j);
                        int distance = vwd1.distance + vwd2.distance;
                        Valve v1 = valvesByName.get(vwd1.valveName);
                        Valve v2 = valvesByName.get(vwd2.valveName);
                        boolean valveAlreadyNeighbour = false;
                        int neighbourIndex = -1;
                        for (int v = 0; v < v1.neighboursWithDistance.size(); v++) {
                            if (v1.neighboursWithDistance.get(v).valveName.equals(vwd2.valveName)) {
                                valveAlreadyNeighbour = true;
                                neighbourIndex = v;
                            }
                        }
                        if (valveAlreadyNeighbour) {
                            if (v1.neighboursWithDistance.get(neighbourIndex).distance > distance) {
                                v1.neighboursWithDistance.get(neighbourIndex).distance = distance;
                                for (int v = 0; v < v2.neighboursWithDistance.size(); v++) {
                                    if (v2.neighboursWithDistance.get(v).valveName.equals(vwd1.valveName)) {
                                        v2.neighboursWithDistance.get(v).distance = distance;
                                    }
                                }
                            }
                        } else {
                            v1.neighboursWithDistance.add(new ValveWithDistance(vwd2.valveName, distance));
                            v2.neighboursWithDistance.add(new ValveWithDistance(vwd1.valveName, distance));
                        }
                        for (int v = 0; v < v1.neighboursWithDistance.size(); v++) {
                            if (v1.neighboursWithDistance.get(v).valveName.equals(key)) {
                                v1.neighboursWithDistance.remove(v);
                                break;
                            }
                        }
                        for (int v = 0; v < v2.neighboursWithDistance.size(); v++) {
                            if (v2.neighboursWithDistance.get(v).valveName.equals(key)) {
                                v2.neighboursWithDistance.remove(v);
                                break;
                            }
                        }
                    }
                }
                if (!key.equals("AA")) {
                    removalList.add(key);
                } else {
                    Valve startValve = valvesByName.get(key);
                    HashSet<String> visited = new HashSet<>();
                    visited.add(key);
                    ArrayList<String> valvesToCheck = new ArrayList<>();
                    for (ValveWithDistance vwd : startValve.neighboursWithDistance) {
                        valvesToCheck.add(vwd.valveName);
                        visited.add(vwd.valveName);
                    }
                    while (!valvesToCheck.isEmpty()) {
                        String currentValveName = valvesToCheck.remove(0);
                        if (valvesByName.get(currentValveName).flow == 0) {
                            int index = -1;
                            for (int i = 0; i < startValve.neighboursWithDistance.size(); i++) {
                                if (startValve.neighboursWithDistance.get(i).valveName.equals(currentValveName)) {
                                    index = i;
                                }
                            }
                            for (ValveWithDistance vwd : valvesByName.get(currentValveName).neighboursWithDistance) {
                                //Big assumption here: from the start there won't be: a->b, b->c, but also a->c
                                if (!visited.contains(vwd.valveName)) {
                                    valvesToCheck.add(vwd.valveName);
                                    startValve.neighboursWithDistance.add(new ValveWithDistance(vwd.valveName, startValve.neighboursWithDistance.get(index).distance + vwd.distance));
                                    visited.add(vwd.valveName);
                                }
                            }
                            startValve.neighboursWithDistance.remove(index);
                        }
                    }
                }
            }
        }
        for (String key : removalList) {
            valvesByName.remove(key);
        }
        int maxPressure = 0;
        ArrayList<TimeCoor> possiblePositionsInTime = new ArrayList<>();
        possiblePositionsInTime.add(new TimeCoor("AA", 0, 0, 0, new HashSet<>()));
        HashMap<String, HashMap<HashSet<String>, Integer>> positionsWithValvesOpenedSets = new HashMap<>();
        while (!possiblePositionsInTime.isEmpty()) {
            TimeCoor currentTimeCoor = possiblePositionsInTime.remove(0);
            //System.out.println(currentTimeCoor.atTime);
            if (currentTimeCoor.atTime == 30) {
                maxPressure = Math.max(maxPressure, currentTimeCoor.released);
            } else if (currentTimeCoor.atTime > 30) {
                System.out.println("this shouldn't happen");
            } else {
                boolean stepTaken = false;
                for (ValveWithDistance vwd : valvesByName.get(currentTimeCoor.atValve).neighboursWithDistance) {
                    if (vwd.distance + currentTimeCoor.atTime <= 30) {
                        HashMap<HashSet<String>, Integer> hashSetsForThisPosition = positionsWithValvesOpenedSets.get(vwd.valveName);
                        //takeStep and calc meanwhile released pressure, and add it to arraylist
                        int released1 = currentTimeCoor.released + vwd.distance * currentTimeCoor.releasingPerSecond;
                        int rps1 = currentTimeCoor.releasingPerSecond;
                        HashSet<String> openedValves1 = new HashSet<>(currentTimeCoor.openedValves);
                        int nextTime1 = currentTimeCoor.atTime + vwd.distance;
                        int released2 = currentTimeCoor.released + vwd.distance * currentTimeCoor.releasingPerSecond;
                        int rps2 = currentTimeCoor.releasingPerSecond;
                        HashSet<String> openedValves2 = new HashSet<>(currentTimeCoor.openedValves);
                        int nextTime2 = currentTimeCoor.atTime + vwd.distance;
                        boolean openedAValve = false;
                        if (!openedValves1.contains(vwd.valveName) && vwd.distance + currentTimeCoor.atTime <= 29) {
                            rps2 += valvesByName.get(vwd.valveName).flow;
                            openedValves2.add(vwd.valveName);
                            nextTime2++;
                            released2 += currentTimeCoor.releasingPerSecond;
                            openedAValve = true;
                        }
                        if (vwd.distance < 1) {
                            System.out.println("This shouldn't happen");
                        }
                        if (hashSetsForThisPosition == null || !hashSetsForThisPosition.containsKey(openedValves1) || hashSetsForThisPosition.get(openedValves1) < released1) {
                            possiblePositionsInTime.add(new TimeCoor(vwd.valveName, nextTime1, rps1, released1, openedValves1));
                            stepTaken = true;
                            if (hashSetsForThisPosition == null) {
                                hashSetsForThisPosition = new HashMap<>();
                            }
                            hashSetsForThisPosition.put(openedValves1, released1);
                            positionsWithValvesOpenedSets.put(vwd.valveName, hashSetsForThisPosition);
                        }
                        if (openedAValve && (!hashSetsForThisPosition.containsKey(openedValves2) || hashSetsForThisPosition.get(openedValves2) < released2)) {
                            possiblePositionsInTime.add(new TimeCoor(vwd.valveName, nextTime2, rps2, released2, openedValves2));
                            stepTaken = true;
                            hashSetsForThisPosition.put(openedValves2, released2);
                            positionsWithValvesOpenedSets.put(vwd.valveName, hashSetsForThisPosition);
                        }
                    }
                }
                if (!stepTaken) { //no valves can be reached in time anymore, stand around and wait for release to finish
                    int finalReleased = currentTimeCoor.released + (30 - currentTimeCoor.atTime) * currentTimeCoor.releasingPerSecond;
                    possiblePositionsInTime.add(new TimeCoor(currentTimeCoor.atValve, 30, currentTimeCoor.releasingPerSecond, finalReleased, currentTimeCoor.openedValves));
                }
            }
        }
        System.out.println(maxPressure);
    }
}

class TimeCoor {
    String atValve;
    int atTime;
    int releasingPerSecond;
    int released;
    HashSet<String> openedValves;

    public TimeCoor(String v, int t, int rps, int r, HashSet<String> o) {
        atValve = v;
        atTime = t;
        releasingPerSecond = rps;
        released = r;
        openedValves = o;
    }
}

class TimeCoor2Pos {
    String atValve1;
    String atValve2;
    int distanceToValve1Left;
    int distanceToValve2Left;
    String valve1WaitingForRelease;
    String valve2WaitingForRelease;
    int atTime;
    int releasingPerSecond;
    int released;
    HashSet<String> openedValves;

    public TimeCoor2Pos(String v1, String v2, int d1, int d2, String vr1, String vr2, int t, int rps, int r, HashSet<String> o) {
        atValve1 = v1;
        atValve2 = v2;
        distanceToValve1Left = d1;
        distanceToValve2Left = d2;
        valve1WaitingForRelease = vr1;
        valve2WaitingForRelease = vr2;
        atTime = t;
        releasingPerSecond = rps;
        released = r;
        openedValves = o;
    }
}

class Valve {
    int flow;
    ArrayList<ValveWithDistance> neighboursWithDistance;

    public Valve(int f, ArrayList<ValveWithDistance> n) {
        flow = f;
        neighboursWithDistance = n;
    }
}

class ValveWithDistance {
    String valveName;
    int distance;

    public ValveWithDistance(String v, int d) {
        valveName = v;
        distance = d;
    }
}

class Q16Part2 {
    public static void main(String[] args) throws IOException {
        //run();
        randomBruteForce();
    }

    static void randomBruteForce() throws IOException {
        Random r = new Random();
        System.out.println("Part 2 bruteForce:");
        String[] input = Question16.getInput();
        HashMap<String, Valve> valvesByName = new HashMap<>();
        for (String line : input) {
            String[] mainLineParts = line.trim().split("; tunnels? leads? to valves? ");
            String[] valveParts = mainLineParts[0].split(" ");
            String name = valveParts[1];;
            int rate = Integer.parseInt(valveParts[4].split("=")[1]);
            String[] neighbourNames = mainLineParts[1].split(", ");
            ArrayList<ValveWithDistance> neighbours = new ArrayList<>();
            for (String neighbourName : neighbourNames) {
                neighbours.add(new ValveWithDistance(neighbourName, 1));
            }
            Valve v = new Valve(rate, neighbours);
            valvesByName.put(name, v);
        }
        int maxFlow = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (i % 10000000 == 0) {
                System.out.println(i);
            }
            String[] prev = {"AA", "AA"};
            String[] cur = {"AA", "AA"};
            HashSet<String> openedValves = new HashSet<>();
            int flow = 0;
            for (int steps = 26; steps >= 0; steps--) {
                for (int person = 0; person < 2; person++) {
                    Valve valve = valvesByName.get(cur[person]);
                    if (valve.flow != 0 && !openedValves.contains(cur[person]) && r.nextInt(100) > 15) {
                        openedValves.add(cur[person]);
                        flow += Math.max(0, steps-1) * valve.flow;
                    } else {
                        int previousMoveIndexInCurrentNeighbours = -1;
                        ArrayList<ValveWithDistance> neighbours = new ArrayList<>(valve.neighboursWithDistance);
                        for (int v = 0; v <  neighbours.size(); v++) {
                            if ( neighbours.get(v).valveName.equals(prev[person])) {
                                previousMoveIndexInCurrentNeighbours = v;
                            }
                        }
                        if (previousMoveIndexInCurrentNeighbours != -1 &&  neighbours.size() > 1 && r.nextInt(100) > 5) {
                             neighbours.remove(previousMoveIndexInCurrentNeighbours);
                        }
                        int currentPerson1IndexInCurrentNeighbours = -1;
                        for (int v = 0; v <  neighbours.size(); v++) {
                            if ( neighbours.get(v).valveName.equals(cur[0])){
                                currentPerson1IndexInCurrentNeighbours = v;
                            }
                        }
                        if (person == 1 && currentPerson1IndexInCurrentNeighbours != -1 &&  neighbours.size() > 1 && r.nextInt(100) > 20) {
                             neighbours.remove(currentPerson1IndexInCurrentNeighbours);
                        }
                        prev[person] = cur[person];
                        cur[person] =  neighbours.get(r.nextInt( neighbours.size())).valveName;
                    }
                }
            }
            if (flow > maxFlow) {
                maxFlow = flow;
                System.out.println(maxFlow);
            }
        }
        System.out.println("Final max flow:");
        System.out.println(maxFlow);
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question16.getInput();
        HashMap<String, Valve> valvesByName = new HashMap<>();
        for (String line : input) {
            String[] mainLineParts = line.trim().split("; tunnels? leads? to valves? ");
            String[] valveParts = mainLineParts[0].split(" ");
            String name = valveParts[1];;
            int rate = Integer.parseInt(valveParts[4].split("=")[1]);
            String[] neighbourNames = mainLineParts[1].split(", ");
            ArrayList<ValveWithDistance> neighbours = new ArrayList<>();
            for (String neighbourName : neighbourNames) {
                neighbours.add(new ValveWithDistance(neighbourName, 1));
            }
            Valve v = new Valve(rate, neighbours);
            valvesByName.put(name, v);
        }
        ArrayList<String> removalList = new ArrayList<>();
        for (String key : valvesByName.keySet()) {
            if (valvesByName.get(key).flow == 0) {
                ArrayList<ValveWithDistance> neighboursWithDistances = valvesByName.get(key).neighboursWithDistance;
                for (int i = 0; i < neighboursWithDistances.size() - 1; i++) {
                    for (int j = i + 1; j < neighboursWithDistances.size(); j++) {
                        ValveWithDistance vwd1 = neighboursWithDistances.get(i);
                        ValveWithDistance vwd2 = neighboursWithDistances.get(j);
                        int distance = vwd1.distance + vwd2.distance;
                        Valve v1 = valvesByName.get(vwd1.valveName);
                        Valve v2 = valvesByName.get(vwd2.valveName);
                        boolean valveAlreadyNeighbour = false;
                        int neighbourIndex = -1;
                        for (int v = 0; v < v1.neighboursWithDistance.size(); v++) {
                            if (v1.neighboursWithDistance.get(v).valveName.equals(vwd2.valveName)) {
                                valveAlreadyNeighbour = true;
                                neighbourIndex = v;
                            }
                        }
                        if (valveAlreadyNeighbour) {
                            if (v1.neighboursWithDistance.get(neighbourIndex).distance > distance) {
                                v1.neighboursWithDistance.get(neighbourIndex).distance = distance;
                                for (int v = 0; v < v2.neighboursWithDistance.size(); v++) {
                                    if (v2.neighboursWithDistance.get(v).valveName.equals(vwd1.valveName)) {
                                        v2.neighboursWithDistance.get(v).distance = distance;
                                    }
                                }
                            }
                        } else {
                            v1.neighboursWithDistance.add(new ValveWithDistance(vwd2.valveName, distance));
                            v2.neighboursWithDistance.add(new ValveWithDistance(vwd1.valveName, distance));
                        }
                        for (int v = 0; v < v1.neighboursWithDistance.size(); v++) {
                            if (v1.neighboursWithDistance.get(v).valveName.equals(key)) {
                                v1.neighboursWithDistance.remove(v);
                                break;
                            }
                        }
                        for (int v = 0; v < v2.neighboursWithDistance.size(); v++) {
                            if (v2.neighboursWithDistance.get(v).valveName.equals(key)) {
                                v2.neighboursWithDistance.remove(v);
                                break;
                            }
                        }
                    }
                }
                if (!key.equals("AA")) {
                    removalList.add(key);
                } else {
                    Valve startValve = valvesByName.get(key);
                    HashSet<String> visited = new HashSet<>();
                    visited.add(key);
                    ArrayList<String> valvesToCheck = new ArrayList<>();
                    for (ValveWithDistance vwd : startValve.neighboursWithDistance) {
                        valvesToCheck.add(vwd.valveName);
                        visited.add(vwd.valveName);
                    }
                    while (!valvesToCheck.isEmpty()) {
                        String currentValveName = valvesToCheck.remove(0);
                        if (valvesByName.get(currentValveName).flow == 0) {
                            int index = -1;
                            for (int i = 0; i < startValve.neighboursWithDistance.size(); i++) {
                                if (startValve.neighboursWithDistance.get(i).valveName.equals(currentValveName)) {
                                    index = i;
                                }
                            }
                            for (ValveWithDistance vwd : valvesByName.get(currentValveName).neighboursWithDistance) {
                                //Big assumption here: from the start there won't be: a->b, b->c, but also a->c
                                if (!visited.contains(vwd.valveName)) {
                                    valvesToCheck.add(vwd.valveName);
                                    startValve.neighboursWithDistance.add(new ValveWithDistance(vwd.valveName, startValve.neighboursWithDistance.get(index).distance + vwd.distance));
                                    visited.add(vwd.valveName);
                                }
                            }
                            startValve.neighboursWithDistance.remove(index);
                        }
                    }
                }
            }
        }
        for (String key : removalList) {
            valvesByName.remove(key);
        }
        int maxPressure = 0;
        ArrayList<TimeCoor2Pos> possiblePositionsInTime = new ArrayList<>();
        possiblePositionsInTime.add(new TimeCoor2Pos("AA", "AA", 0, 0, "", "", 0, 0, 0, new HashSet<>()));
        HashMap<String, HashMap<HashSet<String>, Integer>> positionsWithValvesOpenedSets = new HashMap<>();
        while (!possiblePositionsInTime.isEmpty()) {
            TimeCoor2Pos currentTimeCoor = possiblePositionsInTime.remove(0);
            //System.out.println(currentTimeCoor.atTime);
            if (currentTimeCoor.atTime == 26) {
                maxPressure = Math.max(maxPressure, currentTimeCoor.released);
            } else if (currentTimeCoor.atTime > 26) {
                System.out.println("this shouldn't happen");
            } else {
                boolean stepTaken1 = false;
                boolean stepTaken2 = false;
                if (currentTimeCoor.distanceToValve1Left == 0 && currentTimeCoor.distanceToValve2Left == 0) {
                    boolean v1StepPossible = false;
                    for (ValveWithDistance vwd1 : valvesByName.get(currentTimeCoor.atValve1).neighboursWithDistance) {
                        if (vwd1.distance + currentTimeCoor.atTime <= 26) {
                            v1StepPossible = true;
                            boolean v2StepPossible = false;
                            for (ValveWithDistance vwd2 : valvesByName.get(currentTimeCoor.atValve2).neighboursWithDistance) {
                                if (vwd2.distance + currentTimeCoor.atTime <= 26) {
                                    v2StepPossible = true;
                                    String compoundString = vwd1.valveName.compareTo(vwd2.valveName) > 0 ? (vwd1.valveName + vwd2.valveName) : (vwd2.valveName + vwd1.valveName);
                                    HashMap<HashSet<String>, Integer> hashSetsForThisPosition = positionsWithValvesOpenedSets.get(compoundString);
                                    int distanceThisStep1 = Math.min(vwd1.distance, vwd2.distance);
                                    int released1 = currentTimeCoor.released + distanceThisStep1 * currentTimeCoor.releasingPerSecond;
                                    int rps1 = currentTimeCoor.releasingPerSecond;
                                    HashSet<String> openedValves1 = new HashSet<>(currentTimeCoor.openedValves);
                                    int nextTime1 = currentTimeCoor.atTime + distanceThisStep1;
                                    int distanceThisStep2 = Math.min(vwd1.distance + 1, vwd2.distance);
                                    int released2 = currentTimeCoor.released + distanceThisStep2 * currentTimeCoor.releasingPerSecond;
                                    int rps2 = currentTimeCoor.releasingPerSecond;
                                    HashSet<String> openedValves2 = new HashSet<>(currentTimeCoor.openedValves);
                                    int nextTime2 = currentTimeCoor.atTime + distanceThisStep2;
                                    boolean openedAValve1 = false;
                                    int distanceThisStep3 = Math.min(vwd1.distance, vwd2.distance + 1);
                                    int released3 = currentTimeCoor.released + distanceThisStep3 * currentTimeCoor.releasingPerSecond;
                                    int rps3 = currentTimeCoor.releasingPerSecond;
                                    HashSet<String> openedValves3 = new HashSet<>(currentTimeCoor.openedValves);
                                    int nextTime3 = currentTimeCoor.atTime + distanceThisStep3;
                                    boolean openedAValve2 = false;
                                    int distanceThisStep4 = Math.min(vwd1.distance + 1, vwd2.distance + 1);
                                    int released4 = currentTimeCoor.released + distanceThisStep4 * currentTimeCoor.releasingPerSecond;
                                    int rps4 = currentTimeCoor.releasingPerSecond;
                                    HashSet<String> openedValves4 = new HashSet<>(currentTimeCoor.openedValves);
                                    int nextTime4 = currentTimeCoor.atTime + distanceThisStep4;
                                    boolean openedAValve3 = false;
                                    if (!openedValves1.contains(vwd1.valveName) && vwd1.distance + currentTimeCoor.atTime <= 25) {
                                        if (vwd1.distance + 1 <= vwd2.distance) {
                                            rps2 += valvesByName.get(vwd1.valveName).flow;
                                            openedValves2.add(vwd1.valveName);
                                        }
                                        openedAValve1 = true;
                                        if (!vwd1.valveName.equals(vwd2.valveName) && !openedValves1.contains(vwd2.valveName) && vwd2.distance + currentTimeCoor.atTime <= 25) {
                                            openedValves3 = new HashSet<>(openedValves1);
                                            if (vwd2.distance <= vwd1.distance) {
                                                rps3 += valvesByName.get(vwd2.valveName).flow+ valvesByName.get(vwd1.valveName).flow;
                                                openedValves3.add(vwd2.valveName);
                                            }
                                            openedAValve2 = true;
                                        }
                                    }
                                    if (!openedValves1.contains(vwd2.valveName) && vwd2.distance + currentTimeCoor.atTime <= 25) {
                                        if (vwd2.distance + 1 <= vwd1.distance) {
                                            rps4 += valvesByName.get(vwd2.valveName).flow;
                                            openedValves4.add(vwd2.valveName);
                                        }
                                        openedAValve3 = true;
                                    }
                                    if (hashSetsForThisPosition == null || !hashSetsForThisPosition.containsKey(openedValves1) || hashSetsForThisPosition.get(openedValves1) < released1) {
                                        String vr1 = vwd1.distance - distanceThisStep1 == 0 ? "" : vwd1.valveName;
                                        String vr2 = vwd2.distance - distanceThisStep1 == 0 ? "" : vwd2.valveName;
                                        possiblePositionsInTime.add(new TimeCoor2Pos(vwd1.valveName, vwd2.valveName, vwd1.distance - distanceThisStep1, vwd2.distance - distanceThisStep1, vr1, vr2, nextTime1, rps1, released1, openedValves1));
                                        stepTaken1 = true;
                                        stepTaken2 = true;
                                        if (hashSetsForThisPosition == null) {
                                            hashSetsForThisPosition = new HashMap<>();
                                        }
                                        hashSetsForThisPosition.put(openedValves1, released1);
                                        positionsWithValvesOpenedSets.put(compoundString, hashSetsForThisPosition);
                                    }
                                    if (openedAValve1 && (!hashSetsForThisPosition.containsKey(openedValves2) || hashSetsForThisPosition.get(openedValves2) < released2)) {
                                        String vr1 = vwd1.distance - distanceThisStep2 == 0 ? "" : vwd1.valveName;
                                        String vr2 = vwd2.distance - distanceThisStep2 == 0 ? "" : vwd2.valveName;
                                        possiblePositionsInTime.add(new TimeCoor2Pos(vwd1.valveName, vwd2.valveName, vwd1.distance - distanceThisStep2, vwd2.distance - distanceThisStep2, vr1, vr2, nextTime2, rps2, released2, openedValves2));
                                        stepTaken1 = true;
                                        stepTaken2 = true;
                                        hashSetsForThisPosition.put(openedValves2, released2);
                                        positionsWithValvesOpenedSets.put(compoundString, hashSetsForThisPosition);
                                    }
                                    if (openedAValve2 && (!hashSetsForThisPosition.containsKey(openedValves3) || hashSetsForThisPosition.get(openedValves3) < released3)) {
                                        String vr1 = vwd1.distance - distanceThisStep3 == 0 ? "" : vwd1.valveName;
                                        String vr2 = vwd2.distance - distanceThisStep3 == 0 ? "" : vwd2.valveName;
                                        possiblePositionsInTime.add(new TimeCoor2Pos(vwd1.valveName, vwd2.valveName, vwd1.distance - distanceThisStep3, vwd2.distance - distanceThisStep3, vr1, vr2, nextTime3, rps3, released3, openedValves3));
                                        stepTaken1 = true;
                                        stepTaken2 = true;
                                        hashSetsForThisPosition.put(openedValves3, released3);
                                        positionsWithValvesOpenedSets.put(compoundString, hashSetsForThisPosition);
                                    }
                                    if (openedAValve3 && (!hashSetsForThisPosition.containsKey(openedValves4) || hashSetsForThisPosition.get(openedValves4) < released4)) {
                                        String vr1 = vwd1.distance - distanceThisStep4 == 0 ? "" : vwd1.valveName;
                                        String vr2 = vwd2.distance - distanceThisStep4 == 0 ? "" : vwd2.valveName;
                                        possiblePositionsInTime.add(new TimeCoor2Pos(vwd1.valveName, vwd2.valveName, vwd1.distance - distanceThisStep4, vwd2.distance - distanceThisStep4, vr1, vr2, nextTime4, rps4, released4, openedValves4));
                                        stepTaken1 = true;
                                        stepTaken2 = true;
                                        hashSetsForThisPosition.put(openedValves4, released4);
                                        positionsWithValvesOpenedSets.put(compoundString, hashSetsForThisPosition);
                                    }
                                }
                            }
                            if (!v2StepPossible) {
                                String compoundString = vwd1.valveName.compareTo(currentTimeCoor.atValve2) > 0 ? (vwd1.valveName + currentTimeCoor.atValve2) : (currentTimeCoor.atValve2 + vwd1.valveName);
                                HashMap<HashSet<String>, Integer> hashSetsForThisPosition = positionsWithValvesOpenedSets.get(compoundString);
                                int distanceThisStep = vwd1.distance;
                                int released1 = currentTimeCoor.released + distanceThisStep * currentTimeCoor.releasingPerSecond;
                                int rps1 = currentTimeCoor.releasingPerSecond;
                                HashSet<String> openedValves1 = new HashSet<>(currentTimeCoor.openedValves);
                                int nextTime1 = currentTimeCoor.atTime + distanceThisStep;
                                int released2 = currentTimeCoor.released + distanceThisStep * currentTimeCoor.releasingPerSecond;
                                int rps2 = currentTimeCoor.releasingPerSecond;
                                HashSet<String> openedValves2 = new HashSet<>(currentTimeCoor.openedValves);
                                int nextTime2 = currentTimeCoor.atTime + distanceThisStep;
                                boolean openedAValve1 = false;
                                if (!openedValves1.contains(vwd1.valveName) && vwd1.distance + currentTimeCoor.atTime <= 25) {
                                    rps2 += valvesByName.get(vwd1.valveName).flow;
                                    openedValves2.add(vwd1.valveName);
                                    nextTime2++;
                                    released2 += currentTimeCoor.releasingPerSecond;
                                    openedAValve1 = true;
                                }
                                if (hashSetsForThisPosition == null || !hashSetsForThisPosition.containsKey(openedValves1) || hashSetsForThisPosition.get(openedValves1) < released1) {
                                    possiblePositionsInTime.add(new TimeCoor2Pos(vwd1.valveName, currentTimeCoor.atValve2, 0, 0, "", "", nextTime1, rps1, released1, openedValves1));
                                    stepTaken1 = true;
                                    stepTaken2 = true;
                                    if (hashSetsForThisPosition == null) {
                                        hashSetsForThisPosition = new HashMap<>();
                                    }
                                    hashSetsForThisPosition.put(openedValves1, released1);
                                    positionsWithValvesOpenedSets.put(compoundString, hashSetsForThisPosition);
                                }
                                if (openedAValve1 && (!hashSetsForThisPosition.containsKey(openedValves2) || hashSetsForThisPosition.get(openedValves2) < released2)) {
                                    possiblePositionsInTime.add(new TimeCoor2Pos(vwd1.valveName, currentTimeCoor.atValve2, 0, 0, "", "", nextTime2, rps2, released2, openedValves2));
                                    stepTaken1 = true;
                                    stepTaken2 = true;
                                    hashSetsForThisPosition.put(openedValves2, released2);
                                    positionsWithValvesOpenedSets.put(compoundString, hashSetsForThisPosition);
                                }
                            }
                        }
                    }
                    if (!v1StepPossible) {
                        for (ValveWithDistance vwd2 : valvesByName.get(currentTimeCoor.atValve2).neighboursWithDistance) {
                            if (vwd2.distance + currentTimeCoor.atTime <= 26) {
                                String compoundString = currentTimeCoor.atValve1.compareTo(vwd2.valveName) > 0 ? (currentTimeCoor.atValve1 + vwd2.valveName) : (vwd2.valveName + currentTimeCoor.atValve1);
                                HashMap<HashSet<String>, Integer> hashSetsForThisPosition = positionsWithValvesOpenedSets.get(compoundString);
                                int distanceThisStep = vwd2.distance;
                                int released1 = currentTimeCoor.released + distanceThisStep * currentTimeCoor.releasingPerSecond;
                                int rps1 = currentTimeCoor.releasingPerSecond;
                                HashSet<String> openedValves1 = new HashSet<>(currentTimeCoor.openedValves);
                                int nextTime1 = currentTimeCoor.atTime + distanceThisStep;
                                int released2 = currentTimeCoor.released + distanceThisStep * currentTimeCoor.releasingPerSecond;
                                int rps2 = currentTimeCoor.releasingPerSecond;
                                HashSet<String> openedValves2 = new HashSet<>(currentTimeCoor.openedValves);
                                int nextTime2 = currentTimeCoor.atTime + distanceThisStep;
                                boolean openedAValve1 = false;
                                if (!openedValves1.contains(vwd2.valveName) && vwd2.distance + currentTimeCoor.atTime <= 25) {
                                    rps2 += valvesByName.get(vwd2.valveName).flow;
                                    openedValves2.add(vwd2.valveName);
                                    nextTime2++;
                                    released2 += currentTimeCoor.releasingPerSecond;
                                    openedAValve1 = true;
                                }
                                if (hashSetsForThisPosition == null || !hashSetsForThisPosition.containsKey(openedValves1) || hashSetsForThisPosition.get(openedValves1) < released1) {
                                    possiblePositionsInTime.add(new TimeCoor2Pos(currentTimeCoor.atValve1, vwd2.valveName, 0, 0, "", "", nextTime1, rps1, released1, openedValves1));
                                    stepTaken1 = true;
                                    stepTaken2 = true;
                                    if (hashSetsForThisPosition == null) {
                                        hashSetsForThisPosition = new HashMap<>();
                                    }
                                    hashSetsForThisPosition.put(openedValves1, released1);
                                    positionsWithValvesOpenedSets.put(compoundString, hashSetsForThisPosition);
                                }
                                if (openedAValve1 && (!hashSetsForThisPosition.containsKey(openedValves2) || hashSetsForThisPosition.get(openedValves2) < released2)) {
                                    possiblePositionsInTime.add(new TimeCoor2Pos(currentTimeCoor.atValve1, vwd2.valveName, 0, 0, "", "", nextTime2, rps2, released2, openedValves2));
                                    stepTaken1 = true;
                                    stepTaken2 = true;
                                    hashSetsForThisPosition.put(openedValves2, released2);
                                    positionsWithValvesOpenedSets.put(compoundString, hashSetsForThisPosition);
                                }
                            }
                        }
                    }
                } else if (currentTimeCoor.distanceToValve1Left == 0) {
                    //valve 1: find new valve. if distance is smaller than distancetovalve2left, step to new valve. else step to, or overstep (stopping at valve2distanceleft, creating a new valve1distanceleft
                    //if no step is possible, stay in place until other is done
                    boolean v1StepPossible = false;
                    for (ValveWithDistance vwd1 : valvesByName.get(currentTimeCoor.atValve1).neighboursWithDistance) {
                        if (vwd1.distance + currentTimeCoor.atTime <= 26) {
                            v1StepPossible = true;
                            String compoundString = vwd1.valveName.compareTo(currentTimeCoor.atValve2) > 0 ? (vwd1.valveName + currentTimeCoor.atValve2) : (currentTimeCoor.atValve2 + vwd1.valveName);
                            HashMap<HashSet<String>, Integer> hashSetsForThisPosition = positionsWithValvesOpenedSets.get(compoundString);
                            int distanceThisStep1 = Math.min(vwd1.distance, currentTimeCoor.distanceToValve2Left);
                            int released1 = currentTimeCoor.released + distanceThisStep1 * currentTimeCoor.releasingPerSecond;
                            int rps1 = currentTimeCoor.releasingPerSecond;
                            HashSet<String> openedValves1 = new HashSet<>(currentTimeCoor.openedValves);
                            int nextTime1 = currentTimeCoor.atTime + distanceThisStep1;
                            if (distanceThisStep1 == currentTimeCoor.distanceToValve2Left) {
                                rps1 += valvesByName.get(currentTimeCoor.atValve2).flow;
                                openedValves1.add(currentTimeCoor.atValve2);
                            }
                            int released2 = currentTimeCoor.released + distanceThisStep1 * currentTimeCoor.releasingPerSecond;
                            int rps2 = currentTimeCoor.releasingPerSecond;
                            HashSet<String> openedValves2 = new HashSet<>(currentTimeCoor.openedValves);
                            int nextTime2 = currentTimeCoor.atTime + distanceThisStep1;
                            int distanceThisStep2 = distanceThisStep1;
                            boolean openedAValve1 = false;
                            if (!openedValves1.contains(vwd1.valveName) && vwd1.distance + currentTimeCoor.atTime <= 25) {
                                rps2 += valvesByName.get(vwd1.valveName).flow;
                                openedValves2.add(vwd1.valveName);
                                if (distanceThisStep1 < currentTimeCoor.distanceToValve2Left) {
                                    nextTime2++;
                                    distanceThisStep2++;
                                }
                                released2 += currentTimeCoor.releasingPerSecond;
                                if (distanceThisStep2 == currentTimeCoor.distanceToValve2Left) {
                                    rps2 += valvesByName.get(currentTimeCoor.atValve2).flow;
                                    openedValves2.add(currentTimeCoor.atValve2);
                                }
                                openedAValve1 = true;
                            }
                            if (hashSetsForThisPosition == null || !hashSetsForThisPosition.containsKey(openedValves1) || hashSetsForThisPosition.get(openedValves1) < released1) {
                                String vr1 = vwd1.distance - distanceThisStep1 == 0 ? "" : vwd1.valveName;
                                String vr2 = currentTimeCoor.distanceToValve2Left - distanceThisStep1 == 0 ? "" : currentTimeCoor.atValve2;
                                possiblePositionsInTime.add(new TimeCoor2Pos(vwd1.valveName, currentTimeCoor.atValve2, vwd1.distance - distanceThisStep1, currentTimeCoor.distanceToValve2Left - distanceThisStep1, vr1, vr2, nextTime1, rps1, released1, openedValves1));
                                stepTaken1 = true;
                                if (hashSetsForThisPosition == null) {
                                    hashSetsForThisPosition = new HashMap<>();
                                }
                                hashSetsForThisPosition.put(openedValves1, released1);
                                positionsWithValvesOpenedSets.put(compoundString, hashSetsForThisPosition);
                            }
                            if (openedAValve1 && (!hashSetsForThisPosition.containsKey(openedValves2) || hashSetsForThisPosition.get(openedValves2) < released2)) {
                                String vr1 = vwd1.distance - distanceThisStep2 == 0 ? "" : vwd1.valveName;
                                String vr2 = currentTimeCoor.distanceToValve2Left - distanceThisStep2 == 0 ? "" : currentTimeCoor.atValve2;
                                possiblePositionsInTime.add(new TimeCoor2Pos(vwd1.valveName, currentTimeCoor.atValve2, vwd1.distance - distanceThisStep2, currentTimeCoor.distanceToValve2Left - distanceThisStep2, vr1, vr2, nextTime2, rps2, released2, openedValves2));
                                stepTaken1 = true;
                                hashSetsForThisPosition.put(openedValves2, released2);
                                positionsWithValvesOpenedSets.put(compoundString, hashSetsForThisPosition);
                            }
                        }
                    }
                    if (!v1StepPossible) {
                        int releasedWhileWaiting = currentTimeCoor.released + currentTimeCoor.distanceToValve2Left * currentTimeCoor.releasingPerSecond;
                        int rps = currentTimeCoor.releasingPerSecond + valvesByName.get(currentTimeCoor.atValve2).flow;
                        String compoundString = currentTimeCoor.atValve1.compareTo(currentTimeCoor.atValve2) > 0 ? (currentTimeCoor.atValve1 + currentTimeCoor.atValve2) : (currentTimeCoor.atValve2 + currentTimeCoor.atValve1);
                        HashMap<HashSet<String>, Integer> hashSetsForThisPosition = positionsWithValvesOpenedSets.get(compoundString);
                        if (hashSetsForThisPosition == null) {
                            hashSetsForThisPosition = new HashMap<>();
                        }
                        HashSet<String> openedValves = new HashSet<>(currentTimeCoor.openedValves);
                        if (!hashSetsForThisPosition.containsKey(openedValves) || hashSetsForThisPosition.get(openedValves) < releasedWhileWaiting) {
                            hashSetsForThisPosition.put(openedValves, releasedWhileWaiting);
                            openedValves.add(currentTimeCoor.atValve2);
                            hashSetsForThisPosition.put(openedValves, releasedWhileWaiting);
                            positionsWithValvesOpenedSets.put(compoundString, hashSetsForThisPosition);
                            possiblePositionsInTime.add(new TimeCoor2Pos(currentTimeCoor.atValve1, currentTimeCoor.atValve2, 0, 0, "", "", currentTimeCoor.atTime + currentTimeCoor.distanceToValve2Left, rps, releasedWhileWaiting, currentTimeCoor.openedValves));
                        }
                    }
                } else {
                    //same as above but for valve2
                    boolean v2StepPossible = false;
                    for (ValveWithDistance vwd2 : valvesByName.get(currentTimeCoor.atValve2).neighboursWithDistance) {
                        if (vwd2.distance + currentTimeCoor.atTime <= 26) {
                            v2StepPossible = true;
                            String compoundString = vwd2.valveName.compareTo(currentTimeCoor.atValve1) > 0 ? (vwd2.valveName + currentTimeCoor.atValve1) : (currentTimeCoor.atValve1 + vwd2.valveName);
                            HashMap<HashSet<String>, Integer> hashSetsForThisPosition = positionsWithValvesOpenedSets.get(compoundString);
                            int distanceThisStep1 = Math.min(vwd2.distance, currentTimeCoor.distanceToValve1Left);
                            int released1 = currentTimeCoor.released + distanceThisStep1 * currentTimeCoor.releasingPerSecond;
                            int rps1 = currentTimeCoor.releasingPerSecond;
                            HashSet<String> openedValves1 = new HashSet<>(currentTimeCoor.openedValves);
                            int nextTime1 = currentTimeCoor.atTime + distanceThisStep1;
                            if (distanceThisStep1 == currentTimeCoor.distanceToValve1Left) {
                                rps1 += valvesByName.get(currentTimeCoor.atValve1).flow;
                                openedValves1.add(currentTimeCoor.atValve1);
                            }
                            int released2 = currentTimeCoor.released + distanceThisStep1 * currentTimeCoor.releasingPerSecond;
                            int rps2 = currentTimeCoor.releasingPerSecond;
                            HashSet<String> openedValves2 = new HashSet<>(currentTimeCoor.openedValves);
                            int nextTime2 = currentTimeCoor.atTime + distanceThisStep1;
                            int distanceThisStep2 = distanceThisStep1;
                            boolean openedAValve1 = false;
                            if (!openedValves1.contains(vwd2.valveName) && vwd2.distance + currentTimeCoor.atTime <= 25) {
                                rps2 += valvesByName.get(vwd2.valveName).flow;
                                openedValves2.add(vwd2.valveName);
                                if (distanceThisStep1 < currentTimeCoor.distanceToValve1Left) {
                                    nextTime2++;
                                    distanceThisStep2++;
                                }
                                released2 += currentTimeCoor.releasingPerSecond;
                                if (distanceThisStep2 == currentTimeCoor.distanceToValve1Left) {
                                    rps2 += valvesByName.get(currentTimeCoor.atValve1).flow;
                                    openedValves2.add(currentTimeCoor.atValve1);
                                }
                                openedAValve1 = true;
                            }
                            if (hashSetsForThisPosition == null || !hashSetsForThisPosition.containsKey(openedValves1) || hashSetsForThisPosition.get(openedValves1) < released1) {
                                String vr1 = currentTimeCoor.distanceToValve1Left - distanceThisStep1 == 0 ? "" : currentTimeCoor.atValve1;
                                String vr2 = vwd2.distance - distanceThisStep1 == 0 ? "" : vwd2.valveName;
                                possiblePositionsInTime.add(new TimeCoor2Pos(currentTimeCoor.atValve1, vwd2.valveName, currentTimeCoor.distanceToValve1Left - distanceThisStep1, vwd2.distance - distanceThisStep1, vr1, vr2, nextTime1, rps1, released1, openedValves1));
                                stepTaken2 = true;
                                if (hashSetsForThisPosition == null) {
                                    hashSetsForThisPosition = new HashMap<>();
                                }
                                hashSetsForThisPosition.put(openedValves1, released1);
                                positionsWithValvesOpenedSets.put(compoundString, hashSetsForThisPosition);
                            }
                            if (openedAValve1 && (!hashSetsForThisPosition.containsKey(openedValves2) || hashSetsForThisPosition.get(openedValves2) < released2)) {
                                String vr1 = currentTimeCoor.distanceToValve1Left - distanceThisStep2 == 0 ? "" : currentTimeCoor.atValve1;
                                String vr2 = vwd2.distance - distanceThisStep2 == 0 ? "" : vwd2.valveName;
                                possiblePositionsInTime.add(new TimeCoor2Pos(currentTimeCoor.atValve1, vwd2.valveName, currentTimeCoor.distanceToValve1Left - distanceThisStep2, vwd2.distance - distanceThisStep2, vr1, vr2, nextTime2, rps2, released2, openedValves2));
                                stepTaken2 = true;
                                hashSetsForThisPosition.put(openedValves2, released2);
                                positionsWithValvesOpenedSets.put(compoundString, hashSetsForThisPosition);
                            }
                        }
                    }
                    if (!v2StepPossible) {
                        int releasedWhileWaiting = currentTimeCoor.released + currentTimeCoor.distanceToValve1Left * currentTimeCoor.releasingPerSecond;
                        int rps = currentTimeCoor.releasingPerSecond + valvesByName.get(currentTimeCoor.atValve1).flow;
                        String compoundString = currentTimeCoor.atValve1.compareTo(currentTimeCoor.atValve2) > 0 ? (currentTimeCoor.atValve1 + currentTimeCoor.atValve2) : (currentTimeCoor.atValve2 + currentTimeCoor.atValve1);
                        HashMap<HashSet<String>, Integer> hashSetsForThisPosition = positionsWithValvesOpenedSets.get(compoundString);
                        if (hashSetsForThisPosition == null) {
                            hashSetsForThisPosition = new HashMap<>();
                        }
                        HashSet<String> openedValves = new HashSet<>(currentTimeCoor.openedValves);
                        if (!hashSetsForThisPosition.containsKey(openedValves) || hashSetsForThisPosition.get(openedValves) < releasedWhileWaiting) {
                            hashSetsForThisPosition.put(openedValves, releasedWhileWaiting);
                            openedValves.add(currentTimeCoor.atValve1);
                            hashSetsForThisPosition.put(openedValves, releasedWhileWaiting);
                            positionsWithValvesOpenedSets.put(compoundString, hashSetsForThisPosition);
                            possiblePositionsInTime.add(new TimeCoor2Pos(currentTimeCoor.atValve1, currentTimeCoor.atValve2, 0, 0, "", "", currentTimeCoor.atTime + currentTimeCoor.distanceToValve1Left, rps, releasedWhileWaiting, currentTimeCoor.openedValves));
                        }
                    }
                }
                if (!stepTaken1 && !stepTaken2) { //no valves can be reached in time anymore, stand around and wait for release to finish
                    int finalReleased = currentTimeCoor.released + (26 - currentTimeCoor.atTime) * currentTimeCoor.releasingPerSecond;
                    possiblePositionsInTime.add(new TimeCoor2Pos(currentTimeCoor.atValve1, currentTimeCoor.atValve2, 0, 0, "", "", 26, currentTimeCoor.releasingPerSecond, finalReleased, currentTimeCoor.openedValves));
                }
            }
        }
        System.out.println(maxPressure);
    }
}
