package year2019.code;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Question17 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2019/input/Question17.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
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

    }
}
