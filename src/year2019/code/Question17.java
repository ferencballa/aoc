package year2019.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;

public class Question17 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2019, 17);
        //System.out.println("Part 1:");
        //part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        ArrayList<ArrayList<String>> mapPoints = new ArrayList<>();
        ArrayList<String> firstLayer = new ArrayList<>();
        mapPoints.add(firstLayer);
        int mapLayer = 0;
        String[] stringValues = input[0].split(",");
        CustomHashMap<Long, Long> values = new CustomHashMap<>();
        for (int i = 0; i < stringValues.length; i++) {
            values.put((long) i, Long.parseLong(stringValues[i]));
        }
        long curPos = 0;
        long offset = 0;
        while (values.customGet(curPos) - Math.floorDiv(values.customGet(curPos), 100) * 100 != 99) {
            long curValue = values.customGet(curPos);
            long op = curValue - Math.floorDiv(curValue, 100) * 100;
            if (op == 1) {
                long mode3 = Math.floorDiv(curValue, 10000);
                long mode2 = Math.floorDiv(curValue - mode3 * 10000, 1000);
                long mode1 = Math.floorDiv(curValue - mode3 * 10000 - mode2 * 1000, 100);
                long val1 = mode1 == 1 ? values.customGet(curPos + 1) : values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                long val2 = mode2 == 1 ? values.customGet(curPos + 2) : values.customGet(values.customGet(curPos + 2) + (mode2 == 2 ? offset : 0));
                values.put(values.customGet(curPos + 3) + (mode3 == 2 ? offset : 0), val1 + val2);
                curPos += 4;
            } else if (op == 2) {
                long mode3 = Math.floorDiv(curValue, 10000);
                long mode2 = Math.floorDiv(curValue - mode3 * 10000, 1000);
                long mode1 = Math.floorDiv(curValue - mode3 * 10000 - mode2 * 1000, 100);
                long val1 = mode1 == 1 ? values.customGet(curPos + 1) : values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                long val2 = mode2 == 1 ? values.customGet(curPos + 2) : values.customGet(values.customGet(curPos + 2) + (mode2 == 2 ? offset : 0));
                values.put(values.customGet(curPos + 3) + (mode3 == 2 ? offset : 0), val1 * val2);
                curPos += 4;
            } else if (op == 3) {
                long mode1 = Math.floorDiv(curValue, 100);
                values.put(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0), 2L);
                curPos += 2;
            } else if (op == 4) {
                long mode1 = Math.floorDiv(curValue, 100);
                long val1;
                if (mode1 == 1) {
                    val1 = values.customGet(curPos + 1);
                } else {
                    val1 = values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                }
                if (val1 == 35) {
                    mapPoints.get(mapLayer).add("#");
                } else if (val1 == 46) {
                    mapPoints.get(mapLayer).add(".");
                } else if (val1 == 10) {
                    mapPoints.add(new ArrayList<>());
                    mapLayer++;
                }
                curPos += 2;
            } else if (op == 5) {
                long mode2 = Math.floorDiv(curValue, 1000);
                long mode1 = Math.floorDiv(curValue - mode2 * 1000, 100);
                long val1 = mode1 == 1 ? values.customGet(curPos + 1) : values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                long val2 = mode2 == 1 ? values.customGet(curPos + 2) : values.customGet(values.customGet(curPos + 2) + (mode2 == 2 ? offset : 0));
                if (val1 != 0) {
                    curPos = val2;
                } else {
                    curPos += 3;
                }
            } else if (op == 6) {
                long mode2 = Math.floorDiv(curValue, 1000);
                long mode1 = Math.floorDiv(curValue - mode2 * 1000, 100);
                long val1 = mode1 == 1 ? values.customGet(curPos + 1) : values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                long val2 = mode2 == 1 ? values.customGet(curPos + 2) : values.customGet(values.customGet(curPos + 2) + (mode2 == 2 ? offset : 0));
                if (val1 == 0) {
                    curPos = val2;
                } else {
                    curPos += 3;
                }
            } else if (op == 7) {
                long mode3 = Math.floorDiv(curValue, 10000);
                long mode2 = Math.floorDiv(curValue - mode3 * 10000, 1000);
                long mode1 = Math.floorDiv(curValue - mode3 * 10000 - mode2 * 1000, 100);
                long val1 = mode1 == 1 ? values.customGet(curPos + 1) : values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                long val2 = mode2 == 1 ? values.customGet(curPos + 2) : values.customGet(values.customGet(curPos + 2) + (mode2 == 2 ? offset : 0));
                if (val1 < val2) {
                    values.put(values.customGet(curPos + 3) + (mode3 == 2 ? offset : 0), 1L);
                } else {
                    values.put(values.customGet(curPos + 3) + (mode3 == 2 ? offset : 0), 0L);
                }
                curPos += 4;
            } else if (op == 8) {
                long mode3 = Math.floorDiv(curValue, 10000);
                long mode2 = Math.floorDiv(curValue - mode3 * 10000, 1000);
                long mode1 = Math.floorDiv(curValue - mode3 * 10000 - mode2 * 1000, 100);
                long val1 = mode1 == 1 ? values.customGet(curPos + 1) : values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                long val2 = mode2 == 1 ? values.customGet(curPos + 2) : values.customGet(values.customGet(curPos + 2) + (mode2 == 2 ? offset : 0));
                if (val1 == val2) {
                    values.put(values.customGet(curPos + 3) + (mode3 == 2 ? offset : 0), 1L);
                } else {
                    values.put(values.customGet(curPos + 3) + (mode3 == 2 ? offset : 0), 0L);
                }
                curPos += 4;
            } else if (op == 9) {
                long mode1 = Math.floorDiv(curValue, 100);
                long val1;
                if (mode1 == 1) {
                    val1 = values.customGet(curPos + 1);
                } else {
                    val1 = values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                }
                offset += val1;
                curPos += 2;
            } else {
                System.out.println("Something went wrong");
            }
        }
        int sum = 0;
        for (int i = 1; i < mapPoints.size() - 3; i++) {
            for (int j = 1; j < mapPoints.get(i).size() - 1; j++) {
                if (mapPoints.get(i).get(j).equals("#")) {
                    if (
                            mapPoints.get(i+1).get(j).equals("#") &&
                            mapPoints.get(i-1).get(j).equals("#") &&
                            mapPoints.get(i).get(j+1).equals("#") &&
                            mapPoints.get(i).get(j-1).equals("#")
                    ) {
                        sum += i * j;
                    }
                }
            }
        }
        System.out.println(sum);
    }

    private static void part2(String[] input) {
        ArrayList<ArrayList<String>> mapPoints = new ArrayList<>();
        ArrayList<String> firstLayer = new ArrayList<>();
        mapPoints.add(firstLayer);
        int mapLayer = 0;
        String[] stringValues = input[0].split(",");
        CustomHashMap<Long, Long> values = new CustomHashMap<>();
        for (int i = 0; i < stringValues.length; i++) {
            values.put((long) i, Long.parseLong(stringValues[i]));
        }
        long curPos = 0;
        long offset = 0;
        String solutionInput = "65,44,66,44,66,44,67,44,67,44,65,44,65,44,66,44,66,44,67,10,76,44,49,50,44,82,44,52,44,82,44,52,10,82,44,49,50,44,82,44,52,44,76,44,49,50,10,82,44,49,50,44,82,44,52,44,76,44,54,44,76,44,56,44,76,44,56,10,110,10";
        String[] inputValStrings = solutionInput.split(",");
        long[] inputVals = new long[inputValStrings.length];
        for (int i = 0; i < inputVals.length; i++) {
            inputVals[i] = Long.parseLong(inputValStrings[i]);
        }
        int inputCounter = 0;
        while (values.customGet(curPos) - Math.floorDiv(values.customGet(curPos), 100) * 100 != 99) {
            long curValue = values.customGet(curPos);
            long op = curValue - Math.floorDiv(curValue, 100) * 100;
            if (op == 1) {
                long mode3 = Math.floorDiv(curValue, 10000);
                long mode2 = Math.floorDiv(curValue - mode3 * 10000, 1000);
                long mode1 = Math.floorDiv(curValue - mode3 * 10000 - mode2 * 1000, 100);
                long val1 = mode1 == 1 ? values.customGet(curPos + 1) : values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                long val2 = mode2 == 1 ? values.customGet(curPos + 2) : values.customGet(values.customGet(curPos + 2) + (mode2 == 2 ? offset : 0));
                values.put(values.customGet(curPos + 3) + (mode3 == 2 ? offset : 0), val1 + val2);
                curPos += 4;
            } else if (op == 2) {
                long mode3 = Math.floorDiv(curValue, 10000);
                long mode2 = Math.floorDiv(curValue - mode3 * 10000, 1000);
                long mode1 = Math.floorDiv(curValue - mode3 * 10000 - mode2 * 1000, 100);
                long val1 = mode1 == 1 ? values.customGet(curPos + 1) : values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                long val2 = mode2 == 1 ? values.customGet(curPos + 2) : values.customGet(values.customGet(curPos + 2) + (mode2 == 2 ? offset : 0));
                values.put(values.customGet(curPos + 3) + (mode3 == 2 ? offset : 0), val1 * val2);
                curPos += 4;
            } else if (op == 3) {
                long mode1 = Math.floorDiv(curValue, 100);
                values.put(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0), inputVals[inputCounter]);
                inputCounter++;
                curPos += 2;
            } else if (op == 4) {
                long mode1 = Math.floorDiv(curValue, 100);
                long val1;
                if (mode1 == 1) {
                    val1 = values.customGet(curPos + 1);
                } else {
                    val1 = values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                }
                if (val1 == 35) {
                    mapPoints.get(mapLayer).add("#");
                } else if (val1 == 46) {
                    mapPoints.get(mapLayer).add(".");
                } else if (val1 == 60) {
                    mapPoints.get(mapLayer).add("<");
                } else if (val1 == 62) {
                    mapPoints.get(mapLayer).add(">");
                } else if (val1 == 94) {
                    mapPoints.get(mapLayer).add("^");
                } else if (val1 == 118) {
                    mapPoints.get(mapLayer).add("v");
                } else if (val1 == 10) {
                    mapPoints.add(new ArrayList<>());
                    mapLayer++;
                } else {
                    System.out.println(val1);
                }
                curPos += 2;
            } else if (op == 5) {
                long mode2 = Math.floorDiv(curValue, 1000);
                long mode1 = Math.floorDiv(curValue - mode2 * 1000, 100);
                long val1 = mode1 == 1 ? values.customGet(curPos + 1) : values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                long val2 = mode2 == 1 ? values.customGet(curPos + 2) : values.customGet(values.customGet(curPos + 2) + (mode2 == 2 ? offset : 0));
                if (val1 != 0) {
                    curPos = val2;
                } else {
                    curPos += 3;
                }
            } else if (op == 6) {
                long mode2 = Math.floorDiv(curValue, 1000);
                long mode1 = Math.floorDiv(curValue - mode2 * 1000, 100);
                long val1 = mode1 == 1 ? values.customGet(curPos + 1) : values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                long val2 = mode2 == 1 ? values.customGet(curPos + 2) : values.customGet(values.customGet(curPos + 2) + (mode2 == 2 ? offset : 0));
                if (val1 == 0) {
                    curPos = val2;
                } else {
                    curPos += 3;
                }
            } else if (op == 7) {
                long mode3 = Math.floorDiv(curValue, 10000);
                long mode2 = Math.floorDiv(curValue - mode3 * 10000, 1000);
                long mode1 = Math.floorDiv(curValue - mode3 * 10000 - mode2 * 1000, 100);
                long val1 = mode1 == 1 ? values.customGet(curPos + 1) : values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                long val2 = mode2 == 1 ? values.customGet(curPos + 2) : values.customGet(values.customGet(curPos + 2) + (mode2 == 2 ? offset : 0));
                if (val1 < val2) {
                    values.put(values.customGet(curPos + 3) + (mode3 == 2 ? offset : 0), 1L);
                } else {
                    values.put(values.customGet(curPos + 3) + (mode3 == 2 ? offset : 0), 0L);
                }
                curPos += 4;
            } else if (op == 8) {
                long mode3 = Math.floorDiv(curValue, 10000);
                long mode2 = Math.floorDiv(curValue - mode3 * 10000, 1000);
                long mode1 = Math.floorDiv(curValue - mode3 * 10000 - mode2 * 1000, 100);
                long val1 = mode1 == 1 ? values.customGet(curPos + 1) : values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                long val2 = mode2 == 1 ? values.customGet(curPos + 2) : values.customGet(values.customGet(curPos + 2) + (mode2 == 2 ? offset : 0));
                if (val1 == val2) {
                    values.put(values.customGet(curPos + 3) + (mode3 == 2 ? offset : 0), 1L);
                } else {
                    values.put(values.customGet(curPos + 3) + (mode3 == 2 ? offset : 0), 0L);
                }
                curPos += 4;
            } else if (op == 9) {
                long mode1 = Math.floorDiv(curValue, 100);
                long val1;
                if (mode1 == 1) {
                    val1 = values.customGet(curPos + 1);
                } else {
                    val1 = values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                }
                offset += val1;
                curPos += 2;
            } else {
                System.out.println("Something went wrong");
            }
        }
        mapPoints.remove(43);
        mapPoints.remove(42);
        mapPoints.remove(41);
        mapPoints.remove(40);
        mapPoints.remove(39);
        for (int i = 0; i < mapPoints.size(); i++) {
            for (int j = 0; j < mapPoints.get(i).size(); j++) {
                System.out.print(mapPoints.get(i).get(j));
            }
            System.out.print("\n");
        }
        int curI = -1;
        int curJ = -1;
        outer: for (int i = 0; i < mapPoints.size(); i++) {
            for (int j = 0; j < mapPoints.get(i).size(); j++) {
                if (mapPoints.get(i).get(j).equals("^")) {
                    curI = i;
                    curJ = j;
                    break outer;
                }
            }
        }
        boolean nextStepAvailable = true;
        ArrayList<String> instructions = new ArrayList<>();
        /*
        dirs:
        0 = up
        1 = right
        2 = down
        3 = left
         */
        instructions.add("L");
        int dir = 3;
        int stepsInDirection = 0;
        while(nextStepAvailable) {
            if (dir == 0) {
                if (curI > 0 && mapPoints.get(curI - 1).get(curJ).equals("#")) {
                    stepsInDirection++;
                    curI--;
                } else {
                    instructions.add(String.valueOf(stepsInDirection));
                    stepsInDirection = 0;
                    if (curJ > 0 && mapPoints.get(curI).get(curJ - 1).equals("#")) {
                        dir = 3;
                        instructions.add("L");
                    } else if (curJ < mapPoints.get(curI).size() - 1 && mapPoints.get(curI).get(curJ + 1).equals("#")) {
                        dir = 1;
                        instructions.add("R");
                    } else {
                        nextStepAvailable = false;
                    }
                }
            } else
            if (dir == 1) {
                if (curJ < mapPoints.get(curI).size() - 1 && mapPoints.get(curI).get(curJ + 1).equals("#")) {
                    stepsInDirection++;
                    curJ++;
                } else {
                    instructions.add(String.valueOf(stepsInDirection));
                    stepsInDirection = 0;
                    if (curI > 0 && mapPoints.get(curI - 1).get(curJ).equals("#")) {
                        dir = 0;
                        instructions.add("L");
                    } else if (curI < mapPoints.size() - 1 && mapPoints.get(curI + 1).get(curJ).equals("#")) {
                        dir = 2;
                        instructions.add("R");
                    } else {
                        nextStepAvailable = false;
                    }
                }
            } else
            if (dir == 2) {
                if (curI < mapPoints.size() - 1 && mapPoints.get(curI + 1).get(curJ).equals("#")) {
                    stepsInDirection++;
                    curI++;
                } else {
                    instructions.add(String.valueOf(stepsInDirection));
                    stepsInDirection = 0;
                    if (curJ > 0 && mapPoints.get(curI).get(curJ - 1).equals("#")) {
                        dir = 3;
                        instructions.add("R");
                    } else if (curJ < mapPoints.get(curI).size() - 1 && mapPoints.get(curI).get(curJ + 1).equals("#")) {
                        dir = 1;
                        instructions.add("L");
                    } else {
                        nextStepAvailable = false;
                    }
                }
            }
            else if (dir == 3) {
                if (curJ > 0 && mapPoints.get(curI).get(curJ - 1).equals("#")) {
                    stepsInDirection++;
                    curJ--;
                } else {
                    instructions.add(String.valueOf(stepsInDirection));
                    stepsInDirection = 0;
                    if (curI > 0 && mapPoints.get(curI - 1).get(curJ).equals("#")) {
                        dir = 0;
                        instructions.add("R");
                    } else if (curI < mapPoints.size() - 1 && mapPoints.get(curI + 1).get(curJ).equals("#")) {
                        dir = 2;
                        instructions.add("L");
                    } else {
                        nextStepAvailable = false;
                    }
                }
            }
        }
        System.out.print(instructions.toString());
    }
}
