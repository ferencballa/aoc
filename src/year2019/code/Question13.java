package year2019.code;

import helpers.Helper;

import java.io.IOException;
import java.util.HashMap;

public class Question13 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String[] input = Helper.getInputForYearAndTask(2019, 13);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        String[] stringValues = input[0].split(",");
        CustomHashMap<Long, Long> values = new CustomHashMap<>();
        for (int i = 0; i < stringValues.length; i++) {
            values.put((long) i, Long.parseLong(stringValues[i]));
        }
        long curPos = 0;
        long offset = 0;
        HashMap<Long, HashMap<Long, Long>> tiles = new HashMap<>();
        long tileX = -1;
        long tileY = -1;
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
                System.out.println("Input has been asked, this shouldn't happen here yet...");
            } else if (op == 4) {
                long mode1 = Math.floorDiv(curValue, 100);
                long val1;
                if (mode1 == 1) {
                    val1 = values.customGet(curPos + 1);
                } else {
                    val1 = values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                }
                if (tileX == -1) {
                    tileX = val1;
                } else if (tileY == -1) {
                    tileY = val1;
                } else {
                    HashMap<Long, Long> row = tiles.get(tileX);
                    if (row == null) {
                        tiles.put(tileX, new HashMap<>());
                        row = tiles.get(tileX);
                    }
                    row.put(tileY, val1);
                    tileX = -1;
                    tileY = -1;
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
        int countBlocks = 0;
        for (Long rowKeys : tiles.keySet()) {
            HashMap<Long, Long> row = tiles.get(rowKeys);
            for (Long key : row.keySet()) {
                if (row.get(key) == 2L) {
                    countBlocks++;
                }
            }
        }
        System.out.println(countBlocks);
    }

    //Be careful, with all the drawing and timeouts that are now commented out it takes over half an hour
    private static void part2(String[] input) throws InterruptedException {
        String[] stringValues = input[0].split(",");
        CustomHashMap<Long, Long> values = new CustomHashMap<>();
        for (int i = 0; i < stringValues.length; i++) {
            values.put((long) i, Long.parseLong(stringValues[i]));
        }
        long curPos = 0;
        long offset = 0;
        int[][] tiles = new int[20][42];
        int tileX = -2;
        int tileY = -2;
        values.put(0L, 2L);
        boolean firstInputAsked = false;
        int ballX = -1;
        int paddleX = -1;
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
                firstInputAsked = true;
                long mode1 = Math.floorDiv(curValue, 100);
                /*Scanner sc = new Scanner(System.in);
                boolean succes = false;
                long inVal = 0L;
                System.out.println("Input keypress:");
                while (!succes) {
                    String in = sc.nextLine();
                    if (in.equals("a")) {
                        inVal = -1;
                        succes = true;
                    } else if (in.equals("s")) {
                        inVal = 0;
                        succes = true;
                    } else if (in.equals("d")) {
                        inVal = 1;
                        succes = true;
                    } else {
                        System.out.println("Incorrect input, try again");
                    }
                }*/
                long inVal = 0L;
                if (ballX < paddleX) {
                    inVal = -1;
                }
                if (ballX > paddleX) {
                    inVal = 1L;
                }
                values.put(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0), inVal);
                curPos += 2;
                //TimeUnit.MILLISECONDS.sleep(300);
            } else if (op == 4) {
                long mode1 = Math.floorDiv(curValue, 100);
                long val1;
                if (mode1 == 1) {
                    val1 = values.customGet(curPos + 1);
                } else {
                    val1 = values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                }
                if (tileX == -2) {
                    tileX = (int) val1;
                } else if (tileY == -2) {
                    tileY = (int) val1;
                } else {
                    if (tileX == -1 && tileY == 0) {
                        System.out.println("Score: " + val1);
                    } else {
                        tiles[tileY][tileX] = (int) val1;
                        if (val1 == 3L) {
                            paddleX = tileX;
                        }
                        if (val1 == 4L) {
                            ballX = tileX;
                        }
                    }
                    tileX = -2;
                    tileY = -2;
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
            /*if (firstInputAsked) {
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");
                for (int y = 0; y < 20; y++) {
                    for (int x = 0; x < 42; x++) {
                        int tile = tiles[y][x];
                        if (tile == 0)
                            System.out.print(".");
                        else if (tile == 1)
                            System.out.print("+");
                        else if (tile == 2)
                            System.out.print("X");
                        else if (tile == 3)
                            System.out.print("_");
                        else if (tile == 4)
                            System.out.print("O");

                    }
                    System.out.print("\n");
                }
            }*/
        }
    }
}
