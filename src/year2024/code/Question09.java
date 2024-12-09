package year2024.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Question09 {
    public static void main(String[] args) throws IOException {
        Q09Part1.run();
        Q09Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 9);
    }
}

class Q09Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question09.getInput();
        int[] vals = Helper.StringArrayToIntArray(input[0].split(""));
        int totalLength = Arrays.stream(vals).reduce(0, Integer::sum);
        int[] idPerBlock = new int[totalLength];
        Arrays.fill(idPerBlock, -1);
        int fillIndex = 0;
        for (int i = 0; i < vals.length; i++) {
            int id = -1;
            if (i % 2 == 0) {
                id = i / 2;
            }
            for (int j = 0; j < vals[i]; j++) {
                idPerBlock[fillIndex] = id;
                fillIndex++;
            }
        }
        int startIndex = 0;
        int endIndex = idPerBlock.length - 1;
        while (startIndex < endIndex) {
            if (idPerBlock[endIndex] == -1) {
                endIndex--;
            } else if (idPerBlock[startIndex] != -1) {
                startIndex++;
            } else {
                idPerBlock[startIndex] = idPerBlock[endIndex];
                idPerBlock[endIndex] = -1;
                startIndex++;
                endIndex--;
            }
        }
        long count = 0;
        for (int i = 0; i < idPerBlock.length; i++) {
            if (idPerBlock[i] == -1) {
                break;
            }
            count += i * (long) idPerBlock[i];
        }
        System.out.println(count);

        /*for (int j : idPerBlock) {
            if (j == -1) {
                System.out.print(".");
            } else {
                System.out.print(j);
            }
        }*/
    }
}

class Q09Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question09.getInput();
        int[] vals = Helper.StringArrayToIntArray(input[0].split(""));
        int totalLength = Arrays.stream(vals).reduce(0, Integer::sum);
        int[] idPerBlock = new int[totalLength];
        Arrays.fill(idPerBlock, -1);
        int fillIndex = 0;
        ArrayList<Integer> startIndexesOfHoles = new ArrayList<>();
        HashMap<Integer, Integer> spaceForStartingIndexes = new HashMap<>();
        for (int i = 0; i < vals.length; i++) {
            int id = -1;
            if (i % 2 == 0) {
                id = i / 2;
            } else {
                if (vals[i] > 0) {
                    startIndexesOfHoles.add(fillIndex);
                    spaceForStartingIndexes.put(fillIndex, vals[i]);
                }
            }
            for (int j = 0; j < vals[i]; j++) {
                idPerBlock[fillIndex] = id;
                fillIndex++;
            }
        }
        int startIndex = 0;
        int endIndex = idPerBlock.length - 1;
        int idCurrentlyMoving = -1;
        int countMoving = 0;
        while (startIndex < endIndex) {
            if (idPerBlock[endIndex] == -1 && idCurrentlyMoving == -1) {
                endIndex--;
            } else if (idPerBlock[startIndex] != -1) {
                startIndex++;
            } else {
                if (idCurrentlyMoving == idPerBlock[endIndex]) {
                    countMoving++;
                    endIndex--;
                } else {
                    if (countMoving > 0) {
                        for (int i = 0; i < startIndexesOfHoles.size(); i++) {
                            Integer startIndexOfHole = startIndexesOfHoles.get(i);
                            if (startIndexOfHole < endIndex) {
                                if (spaceForStartingIndexes.get(startIndexOfHole) >= countMoving) {
                                    for (int cIndex = 0; cIndex < countMoving; cIndex++) {
                                        idPerBlock[startIndexOfHole + cIndex] = idCurrentlyMoving;
                                        idPerBlock[endIndex + cIndex + 1] = -1;
                                    }
                                    if (spaceForStartingIndexes.get(startIndexOfHole) > countMoving) {
                                        spaceForStartingIndexes.put(startIndexOfHole + countMoving, spaceForStartingIndexes.get(startIndexOfHole) - countMoving);
                                        startIndexesOfHoles.set(i, startIndexOfHole + countMoving);
                                    } else {
                                        startIndexesOfHoles.remove(startIndexOfHole); //this is the object, not the index, so it should remove the correct object instead of at the wrong index
                                    }
                                    spaceForStartingIndexes.remove(startIndexOfHole);
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    idCurrentlyMoving = idPerBlock[endIndex];
                    countMoving = 0;
                    if (idCurrentlyMoving != -1) {
                        countMoving++;
                    }
                    endIndex--;
                }
            }
        }
        long count = 0;
        for (int i = 0; i < idPerBlock.length; i++) {
            if (idPerBlock[i] != -1) {
                count += i * (long) idPerBlock[i];
            }
        }
        System.out.println(count);

        for (int j : idPerBlock) {
            if (j == -1) {
                System.out.print(".");
            } else {
                System.out.print(j);
            }
        }
    }
}
