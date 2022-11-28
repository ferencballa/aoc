package year2019.code;

import helpers.Helper;

import java.io.IOException;
import java.util.HashMap;

public class Question12 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2019, 12);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        int[][] positions = new int[4][3];
        for (int p = 0; p < 4; p++) {
            String[] coors = input[p].split(", ");
            positions[p][0] = Integer.parseInt(coors[0].split("=")[1]);
            positions[p][1] = Integer.parseInt(coors[1].split("=")[1]);
            String zWithEnd = coors[2].split("=")[1];
            positions[p][2] = Integer.parseInt(zWithEnd.substring(0, zWithEnd.length()-1));
        }
        int[][] velocities = new int[4][3];
        for (int step = 0; step < 1000; step++) {
            for (int objectMoon = 0; objectMoon < 4; objectMoon++) {
                for (int otherMoon = 0; otherMoon < 4; otherMoon++) {
                    if (objectMoon != otherMoon) {
                        for (int axis = 0; axis < 3; axis++) {
                            if (positions[objectMoon][axis] > positions[otherMoon][axis]) {
                                velocities[objectMoon][axis]--;
                            } else if (positions[objectMoon][axis] < positions[otherMoon][axis]){
                                velocities[objectMoon][axis]++;
                            }
                        }
                    }
                }
            }
            for (int moon = 0; moon < 4; moon++) {
                for (int axis = 0; axis < 3; axis++) {
                    positions[moon][axis] += velocities[moon][axis];
                }
            }
        }
        int[][] energies = new int[4][2];
        for (int moon = 0; moon < 4; moon++) {
            for (int axis = 0; axis < 3; axis++) {
                energies[moon][0] += Math.abs(positions[moon][axis]);
                energies[moon][1] += Math.abs(velocities[moon][axis]);
            }
        }
        System.out.println(energies[0][0]*energies[0][1] + energies[1][0]*energies[1][1] + energies[2][0]*energies[2][1] + energies[3][0]*energies[3][1]);
    }

    private static void part2(String[] input) {
        int[][] positions = new int[4][3];
        for (int p = 0; p < 4; p++) {
            String[] coors = input[p].split(", ");
            positions[p][0] = Integer.parseInt(coors[0].split("=")[1]);
            positions[p][1] = Integer.parseInt(coors[1].split("=")[1]);
            String zWithEnd = coors[2].split("=")[1];
            positions[p][2] = Integer.parseInt(zWithEnd.substring(0, zWithEnd.length()-1));
        }
        int[][] velocities = new int[4][3];
        int attemptSize = 10000000; //value is present just in case an error occurs which would cause an infinite loop
        HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>>>>>> xMap = new HashMap<>();
        HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>>>>>> yMap = new HashMap<>();
        HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>>>>>> zMap = new HashMap<>();
        boolean xRepeated = false;
        boolean yRepeated = false;
        boolean zRepeated = false;
        int xCycle = 0;
        int yCycle = 0;
        int zCycle = 0;
        int xStartCycle = 0;
        int yStartCycle = 0;
        int zStartCycle = 0;
        attempt:
        for (int step = 0; step < attemptSize; step++) {
            HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>>>>> xMap1 = xMap.get(positions[0][0]);
            if (xMap1 == null) {
                xMap.put(positions[0][0], new HashMap<>());
                xMap1 = xMap.get(positions[0][0]);
            }
            HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>>>> xMap2 = xMap1.get(positions[1][0]);
            if (xMap2 == null) {
                xMap1.put(positions[1][0], new HashMap<>());
                xMap2 = xMap1.get(positions[1][0]);
            }
            HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>>> xMap3 = xMap2.get(positions[2][0]);
            if (xMap3 == null) {
                xMap2.put(positions[2][0], new HashMap<>());
                xMap3 = xMap2.get(positions[2][0]);
            }
            HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>> xMap4 = xMap3.get(positions[3][0]);
            if (xMap4 == null) {
                xMap3.put(positions[3][0], new HashMap<>());
                xMap4 = xMap3.get(positions[3][0]);
            }
            HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>> xMap5 = xMap4.get(velocities[0][0]);
            if (xMap5 == null) {
                xMap4.put(velocities[0][0], new HashMap<>());
                xMap5 = xMap4.get(velocities[0][0]);
            }
            HashMap<Integer, HashMap<Integer, Integer>> xMap6 = xMap5.get(velocities[1][0]);
            if (xMap6 == null) {
                xMap5.put(velocities[1][0], new HashMap<>());
                xMap6 = xMap5.get(velocities[1][0]);
            }
            HashMap<Integer, Integer> xMap7 = xMap6.get(velocities[2][0]);
            if (xMap7 == null) {
                xMap6.put(velocities[2][0], new HashMap<>());
                xMap7 = xMap6.get(velocities[2][0]);
            }
            Integer xVisited = xMap7.get(velocities[3][0]);
            if (xVisited == null) {
                xMap7.put(velocities[3][0], step);
            } else {
                if (!xRepeated) {
                    xRepeated = true;
                    xCycle = step - xVisited;
                    xStartCycle = xVisited;
                    if (yRepeated && zRepeated) {
                        break attempt;
                    }
                }
            }
            HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>>>>> yMap1 = yMap.get(positions[0][1]);
            if (yMap1 == null) {
                yMap.put(positions[0][1], new HashMap<>());
                yMap1 = yMap.get(positions[0][1]);
            }
            HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>>>> yMap2 = yMap1.get(positions[1][1]);
            if (yMap2 == null) {
                yMap1.put(positions[1][1], new HashMap<>());
                yMap2 = yMap1.get(positions[1][1]);
            }
            HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>>> yMap3 = yMap2.get(positions[2][1]);
            if (yMap3 == null) {
                yMap2.put(positions[2][1], new HashMap<>());
                yMap3 = yMap2.get(positions[2][1]);
            }
            HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>> yMap4 = yMap3.get(positions[3][1]);
            if (yMap4 == null) {
                yMap3.put(positions[3][1], new HashMap<>());
                yMap4 = yMap3.get(positions[3][1]);
            }
            HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>> yMap5 = yMap4.get(velocities[0][1]);
            if (yMap5 == null) {
                yMap4.put(velocities[0][1], new HashMap<>());
                yMap5 = yMap4.get(velocities[0][1]);
            }
            HashMap<Integer, HashMap<Integer, Integer>> yMap6 = yMap5.get(velocities[1][1]);
            if (yMap6 == null) {
                yMap5.put(velocities[1][1], new HashMap<>());
                yMap6 = yMap5.get(velocities[1][1]);
            }
            HashMap<Integer, Integer> yMap7 = yMap6.get(velocities[2][1]);
            if (yMap7 == null) {
                yMap6.put(velocities[2][1], new HashMap<>());
                yMap7 = yMap6.get(velocities[2][1]);
            }
            Integer yVisited = yMap7.get(velocities[3][1]);
            if (yVisited == null) {
                yMap7.put(velocities[3][1], step);
            } else {
                if (!yRepeated) {
                    yRepeated = true;
                    yCycle = step - yVisited;
                    yStartCycle = yVisited;
                    if (xRepeated && zRepeated) {
                        System.out.println("Attempt broken at: " + step);
                        break attempt;
                    }
                }
            }
            HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>>>>> zMap1 = zMap.get(positions[0][2]);
            if (zMap1 == null) {
                zMap.put(positions[0][2], new HashMap<>());
                zMap1 = zMap.get(positions[0][2]);
            }
            HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>>>> zMap2 = zMap1.get(positions[1][2]);
            if (zMap2 == null) {
                zMap1.put(positions[1][2], new HashMap<>());
                zMap2 = zMap1.get(positions[1][2]);
            }
            HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>>> zMap3 = zMap2.get(positions[2][2]);
            if (zMap3 == null) {
                zMap2.put(positions[2][2], new HashMap<>());
                zMap3 = zMap2.get(positions[2][2]);
            }
            HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>> zMap4 = zMap3.get(positions[3][2]);
            if (zMap4 == null) {
                zMap3.put(positions[3][2], new HashMap<>());
                zMap4 = zMap3.get(positions[3][2]);
            }
            HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>> zMap5 = zMap4.get(velocities[0][2]);
            if (zMap5 == null) {
                zMap4.put(velocities[0][2], new HashMap<>());
                zMap5 = zMap4.get(velocities[0][2]);
            }
            HashMap<Integer, HashMap<Integer, Integer>> zMap6 = zMap5.get(velocities[1][2]);
            if (zMap6 == null) {
                zMap5.put(velocities[1][2], new HashMap<>());
                zMap6 = zMap5.get(velocities[1][2]);
            }
            HashMap<Integer, Integer> zMap7 = zMap6.get(velocities[2][2]);
            if (zMap7 == null) {
                zMap6.put(velocities[2][2], new HashMap<>());
                zMap7 = zMap6.get(velocities[2][2]);
            }
            Integer zVisited = zMap7.get(velocities[3][2]);
            if (zVisited == null) {
                zMap7.put(velocities[3][2], step);
            } else {
                if (!zRepeated) {
                    zRepeated = true;
                    zCycle = step - zVisited;
                    zStartCycle = zVisited;
                    if (xRepeated && yRepeated) {
                        System.out.println("Attempt broken at: " + step);
                        break attempt;
                    }
                }
            }
            for (int objectMoon = 0; objectMoon < 4; objectMoon++) {
                for (int otherMoon = 0; otherMoon < 4; otherMoon++) {
                    if (objectMoon != otherMoon) {
                        for (int axis = 0; axis < 3; axis++) {
                            if (positions[objectMoon][axis] > positions[otherMoon][axis]) {
                                velocities[objectMoon][axis]--;
                            } else if (positions[objectMoon][axis] < positions[otherMoon][axis]){
                                velocities[objectMoon][axis]++;
                            }
                        }
                    }
                }
            }
            for (int moon = 0; moon < 4; moon++) {
                for (int axis = 0; axis < 3; axis++) {
                    positions[moon][axis] += velocities[moon][axis];
                }
            }
        }
        HashMap<Integer, Integer> xFactors = factorize(xCycle);
        HashMap<Integer, Integer> yFactors = factorize(yCycle);
        HashMap<Integer, Integer> zFactors = factorize(zCycle);
        HashMap<Integer, Integer> mostUniqueFactors = xFactors;
        for (Integer factor : yFactors.keySet()) {
            Integer alreadyPresentFactorAmount = mostUniqueFactors.get(factor);
            if (alreadyPresentFactorAmount == null) {
                mostUniqueFactors.put(factor, yFactors.get(factor));
            } else {
                Integer newFactorAmount = yFactors.get(factor);
                if (newFactorAmount > alreadyPresentFactorAmount) {
                    mostUniqueFactors.put(factor, newFactorAmount);
                }
            }
        }
        for (Integer factor : zFactors.keySet()) {
            Integer alreadyPresentFactorAmount = mostUniqueFactors.get(factor);
            if (alreadyPresentFactorAmount == null) {
                mostUniqueFactors.put(factor, zFactors.get(factor));
            } else {
                Integer newFactorAmount = zFactors.get(factor);
                if (newFactorAmount > alreadyPresentFactorAmount) {
                    mostUniqueFactors.put(factor, newFactorAmount);
                }
            }
        }
        long cycle = 1;
        for (Integer factor : mostUniqueFactors.keySet()) {
            cycle *= Math.pow(factor, mostUniqueFactors.get(factor));
        }
        System.out.println(Math.max(Math.max(xStartCycle, yStartCycle), zStartCycle) + cycle);
    }

    private static HashMap<Integer, Integer> factorize(int value) {
        HashMap<Integer, Integer> factors = new HashMap<>();
        for (int i = 2; i <= value; i++) {
            while (value % i == 0) {
                value = value/i;
                if (factors.get(i) == null) {
                    factors.put(i, 0);
                }
                factors.put(i, factors.get(i) + 1);
            }
        }
        return factors;
    }
}
