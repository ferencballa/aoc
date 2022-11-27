package year2019.code;

import helpers.Helper;

import java.io.IOException;
import java.util.HashMap;

public class Question11 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2019, 11);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        String[] stringValues = input[0].split(",");
        CustomHashMap<Long, Long> values = new CustomHashMap<>();
        HashMap<Long, HashMap<Long, Boolean>> panels = new HashMap<>();
        for (int i = 0; i < stringValues.length; i++) {
            values.put((long) i, Long.parseLong(stringValues[i]));
        }
        long curPos = 0;
        long offset = 0;
        long xPosRobot = 0;
        long yPosRobot = 0;
        Orientation orientationRobot = new Orientation();
        Long output1 = null;
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
                long curPanelValue = 0L;
                if (getPanelValue(panels, xPosRobot, yPosRobot)) {
                    curPanelValue = 1L;
                }
                values.put(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0), curPanelValue);
                curPos += 2;
            } else if (op == 4) {
                long mode1 = Math.floorDiv(curValue, 100);
                long output;
                if (mode1 == 1) {
                    output = values.customGet(curPos + 1);
                } else {
                    output = values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                }
                if (output1 == null) {
                    output1 = output;
                } else {
                    putPanelValue(panels, xPosRobot, yPosRobot, output1);
                    orientationRobot.changeOrientation(output);
                    if (orientationRobot.value == Orientation.Orientations.UP)
                        yPosRobot--;
                    else if (orientationRobot.value == Orientation.Orientations.RIGHT)
                        xPosRobot++;
                    else if (orientationRobot.value == Orientation.Orientations.DOWN)
                        yPosRobot++;
                    else
                        xPosRobot--;
                    output1 = null;
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
        int countChangedPanels = 0;
        for (Long keyX : panels.keySet()) {
            HashMap<Long, Boolean> row = panels.get(keyX);
            for (Long keyY : row.keySet()) {
                countChangedPanels++;
            }
        }
        System.out.println(countChangedPanels);
    }

    private static void part2(String[] input) {
        String[] stringValues = input[0].split(",");
        CustomHashMap<Long, Long> values = new CustomHashMap<>();
        HashMap<Long, HashMap<Long, Boolean>> panels = new HashMap<>();
        HashMap<Long, Boolean> row0 = new HashMap<>();
        row0.put(0L, true);
        panels.put(0L, row0);
        for (int i = 0; i < stringValues.length; i++) {
            values.put((long) i, Long.parseLong(stringValues[i]));
        }
        long curPos = 0;
        long offset = 0;
        long xPosRobot = 0;
        long yPosRobot = 0;
        Orientation orientationRobot = new Orientation();
        Long output1 = null;
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
                long curPanelValue = 0L;
                if (getPanelValue(panels, xPosRobot, yPosRobot)) {
                    curPanelValue = 1L;
                }
                values.put(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0), curPanelValue);
                curPos += 2;
            } else if (op == 4) {
                long mode1 = Math.floorDiv(curValue, 100);
                long output;
                if (mode1 == 1) {
                    output = values.customGet(curPos + 1);
                } else {
                    output = values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                }
                if (output1 == null) {
                    output1 = output;
                } else {
                    putPanelValue(panels, xPosRobot, yPosRobot, output1);
                    orientationRobot.changeOrientation(output);
                    if (orientationRobot.value == Orientation.Orientations.UP)
                        yPosRobot--;
                    else if (orientationRobot.value == Orientation.Orientations.RIGHT)
                        xPosRobot++;
                    else if (orientationRobot.value == Orientation.Orientations.DOWN)
                        yPosRobot++;
                    else
                        xPosRobot--;
                    output1 = null;
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
        int highestX = Integer.MIN_VALUE;
        int lowestX = Integer.MAX_VALUE;
        int highestY = Integer.MIN_VALUE;
        int lowestY = Integer.MAX_VALUE;
        for (Long keyX : panels.keySet()) {
            if (keyX > highestX) {
                highestX = keyX.intValue();
            }
            if (keyX < lowestX) {
                lowestX = keyX.intValue();
            }
            HashMap<Long, Boolean> row = panels.get(keyX);
            for (Long keyY : row.keySet()) {
                if (keyY > highestY) {
                    highestY = keyY.intValue();
                }
                if (keyY < lowestY) {
                    lowestY = keyY.intValue();
                }
            }
        }
        for (int y = lowestY; y <= highestY; y++) {
            for (int x = lowestX; x <= highestX; x++) {
                boolean white = panels.get((long)x) != null && panels.get((long)x).get((long)y) != null && panels.get((long)x).get((long)y);
                if (white) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
    }

    private static boolean getPanelValue(HashMap<Long, HashMap<Long, Boolean>> panels, long x, long y) {
        HashMap<Long, Boolean> row = panels.get(x);
        if (row == null) {
            return false;
        }
        Boolean panel = row.get(y);
        if (panel == null) {
            return false;
        }
        return panel;
    }

    private static void putPanelValue(HashMap<Long, HashMap<Long, Boolean>> panels, long x, long y, Long value) {
        HashMap<Long, Boolean> row = panels.get(x);
        if(row == null) {
            row = new HashMap<>();
            panels.put(x, row);
        }
        row.put(y, value == 1L);
    }
}

class Orientation {
    enum Orientations{
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    public Orientations value = Orientations.UP;

    public void changeOrientation(Long input) {
        if (input == 0) {
            if (value == Orientations.UP)
                value = Orientations.LEFT;
            else if (value == Orientations.RIGHT)
                value = Orientations.UP;
            else if (value == Orientations.DOWN)
                value = Orientations.RIGHT;
            else
                value = Orientations.DOWN;
        } else {
            if (value == Orientations.UP)
                value = Orientations.RIGHT;
            else if (value == Orientations.RIGHT)
                value = Orientations.DOWN;
            else if (value == Orientations.DOWN)
                value = Orientations.LEFT;
            else
                value = Orientations.UP;
        }
    }
}
