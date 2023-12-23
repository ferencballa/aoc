package year2023.code;

import helpers.Helper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Question22 {
    public static void main(String[] args) throws IOException {
        Q22Part1.run();
        Q22Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 22);
    }
}

class Q22Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question22.getInput();
        int highestX = 0;
        int highestY = 0;
        int highestZ = 0;
        ArrayList<Integer> orderedBrickIDs = new ArrayList<>();
        HashMap<Integer, Pair<int[], int[]>> bricks = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            String line = input[i];
            String[] endpoint = line.split("~");
            String[] firstPoint = endpoint[0].split(",");
            String[] secondPoint = endpoint[1].split(",");
            int[] firstCoors = new int[3];
            int[] secondCoors = new int[3];
            firstCoors[0] = Integer.parseInt(firstPoint[0]);
            firstCoors[1] = Integer.parseInt(firstPoint[1]);
            firstCoors[2] = Integer.parseInt(firstPoint[2]);
            secondCoors[0] = Integer.parseInt(secondPoint[0]);
            secondCoors[1] = Integer.parseInt(secondPoint[1]);
            secondCoors[2] = Integer.parseInt(secondPoint[2]);
            highestX = Math.max(highestX, secondCoors[0]);
            highestY = Math.max(highestY, secondCoors[1]);
            highestZ = Math.max(highestZ, secondCoors[2]);
            int posBrick = 0;
            while (posBrick < orderedBrickIDs.size() && firstCoors[2] > bricks.get(orderedBrickIDs.get(posBrick)).getLeft()[2]) {
                posBrick++;
            }
            orderedBrickIDs.add(posBrick, i + 1);
            bricks.put(i + 1, new ImmutablePair<>(firstCoors, secondCoors));
        }
        int[][][] map = new int[highestX + 1][][];
        for (int x = 0; x < map.length; x++) {
            map[x] = new int[highestY + 1][];
            for (int y = 0; y < map[x].length; y++) {
                map[x][y] = new int[highestZ + 1];
            }
        }
        for (int i = 0; i < orderedBrickIDs.size(); i++) {
            int[] firstCoors = bricks.get(orderedBrickIDs.get(i)).getLeft();
            int[] secondCoors = bricks.get(orderedBrickIDs.get(i)).getRight();
            for (int x = firstCoors[0]; x <= secondCoors[0]; x++) {
                for (int y = firstCoors[1]; y <= secondCoors[1]; y++) {
                    for (int z = firstCoors[2]; z <= secondCoors[2]; z++) {
                        map[x][y][z] = orderedBrickIDs.get(i);
                    }
                }
            }
        }
        for (int i = 0; i < orderedBrickIDs.size(); i++) {
            int brickID = orderedBrickIDs.remove(i);
            Pair<int[], int[]> brick = bricks.get(brickID);
            int[] firstCoors = brick.getLeft();
            int[] secondCoors = brick.getRight();
            goDown: while (true) {
                if (firstCoors[2] <= 1) {
                    break;
                }
                for (int x = firstCoors[0]; x <= secondCoors[0]; x++) {
                    for (int y = firstCoors[1]; y <= secondCoors[1]; y++) {
                        if (map[x][y][firstCoors[2] - 1] != 0) {
                            break goDown;
                        }
                    }
                }
                for (int x = firstCoors[0]; x <= secondCoors[0]; x++) {
                    for (int y = firstCoors[1]; y <= secondCoors[1]; y++) {
                        if (x == -1 || y == -1 || firstCoors[2] - 1 == -1) {
                            System.out.println("what");
                        }
                        map[x][y][firstCoors[2] - 1] = brickID;
                        map[x][y][secondCoors[2]] = 0;
                    }
                }
                firstCoors[2] = firstCoors[2] - 1;
                secondCoors[2] = secondCoors[2] - 1;
            }
            bricks.put(brickID, new ImmutablePair<>(firstCoors, secondCoors));
            orderedBrickIDs.add(i, brickID);
        }
        int count = 0;
        for (Integer brickID : orderedBrickIDs) {
            Pair<int[], int[]> brick = bricks.get(brickID);
            int[] firstCoors = brick.getLeft();
            int[] secondCoors = brick.getRight();
            ArrayList<Integer> idsOfBricksItIsHolding = new ArrayList<>();
            for (int x = firstCoors[0]; x <= secondCoors[0]; x++) {
                for (int y = firstCoors[1]; y <= secondCoors[1]; y++) {
                    if (map[x][y][secondCoors[2] + 1] != 0 && !idsOfBricksItIsHolding.contains(map[x][y][secondCoors[2] + 1])) {
                        idsOfBricksItIsHolding.add(map[x][y][secondCoors[2] + 1]);
                    }
                }
            }
            boolean allBricksAreHeldBySomethingElse = true;
            for (Integer heldBrickID : idsOfBricksItIsHolding) {
                Pair<int[], int[]> heldBrick = bricks.get(heldBrickID);
                int[] heldFirstCoors = heldBrick.getLeft();
                int[] heldSecondCoors = heldBrick.getRight();
                boolean thisBrickHeldBySomethingElse = false;
                brickHeld: for (int x = heldFirstCoors[0]; x <= heldSecondCoors[0]; x++) {
                    for (int y = heldFirstCoors[1]; y <= heldSecondCoors[1]; y++) {
                        if (map[x][y][heldFirstCoors[2] - 1] != 0 && map[x][y][heldFirstCoors[2] - 1] != brickID) {
                            thisBrickHeldBySomethingElse = true;
                            break brickHeld;
                        }
                    }
                }
                if (!thisBrickHeldBySomethingElse) {
                    allBricksAreHeldBySomethingElse = false;
                    break;
                }
            }
            if (allBricksAreHeldBySomethingElse) {
                count++;
            }
        }
        System.out.println(count);
    }
}

class Q22Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question22.getInput();
        int highestX = 0;
        int highestY = 0;
        int highestZ = 0;
        ArrayList<Integer> orderedBrickIDs = new ArrayList<>();
        HashMap<Integer, Pair<int[], int[]>> bricks = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            String line = input[i];
            String[] endpoint = line.split("~");
            String[] firstPoint = endpoint[0].split(",");
            String[] secondPoint = endpoint[1].split(",");
            int[] firstCoors = new int[3];
            int[] secondCoors = new int[3];
            firstCoors[0] = Integer.parseInt(firstPoint[0]);
            firstCoors[1] = Integer.parseInt(firstPoint[1]);
            firstCoors[2] = Integer.parseInt(firstPoint[2]);
            secondCoors[0] = Integer.parseInt(secondPoint[0]);
            secondCoors[1] = Integer.parseInt(secondPoint[1]);
            secondCoors[2] = Integer.parseInt(secondPoint[2]);
            highestX = Math.max(highestX, secondCoors[0]);
            highestY = Math.max(highestY, secondCoors[1]);
            highestZ = Math.max(highestZ, secondCoors[2]);
            int posBrick = 0;
            while (posBrick < orderedBrickIDs.size() && firstCoors[2] > bricks.get(orderedBrickIDs.get(posBrick)).getLeft()[2]) {
                posBrick++;
            }
            orderedBrickIDs.add(posBrick, i + 1);
            bricks.put(i + 1, new ImmutablePair<>(firstCoors, secondCoors));
        }
        int[][][] map = new int[highestX + 1][][];
        for (int x = 0; x < map.length; x++) {
            map[x] = new int[highestY + 1][];
            for (int y = 0; y < map[x].length; y++) {
                map[x][y] = new int[highestZ + 1];
            }
        }
        for (int i = 0; i < orderedBrickIDs.size(); i++) {
            int[] firstCoors = bricks.get(orderedBrickIDs.get(i)).getLeft();
            int[] secondCoors = bricks.get(orderedBrickIDs.get(i)).getRight();
            for (int x = firstCoors[0]; x <= secondCoors[0]; x++) {
                for (int y = firstCoors[1]; y <= secondCoors[1]; y++) {
                    for (int z = firstCoors[2]; z <= secondCoors[2]; z++) {
                        map[x][y][z] = orderedBrickIDs.get(i);
                    }
                }
            }
        }
        for (int i = 0; i < orderedBrickIDs.size(); i++) {
            int brickID = orderedBrickIDs.remove(i);
            Pair<int[], int[]> brick = bricks.get(brickID);
            int[] firstCoors = brick.getLeft();
            int[] secondCoors = brick.getRight();
            goDown: while (true) {
                if (firstCoors[2] <= 1) {
                    break;
                }
                for (int x = firstCoors[0]; x <= secondCoors[0]; x++) {
                    for (int y = firstCoors[1]; y <= secondCoors[1]; y++) {
                        if (map[x][y][firstCoors[2] - 1] != 0) {
                            break goDown;
                        }
                    }
                }
                for (int x = firstCoors[0]; x <= secondCoors[0]; x++) {
                    for (int y = firstCoors[1]; y <= secondCoors[1]; y++) {
                        if (x == -1 || y == -1 || firstCoors[2] - 1 == -1) {
                            System.out.println("what");
                        }
                        map[x][y][firstCoors[2] - 1] = brickID;
                        map[x][y][secondCoors[2]] = 0;
                    }
                }
                firstCoors[2] = firstCoors[2] - 1;
                secondCoors[2] = secondCoors[2] - 1;
            }
            bricks.put(brickID, new ImmutablePair<>(firstCoors, secondCoors));
            orderedBrickIDs.add(i, brickID);
        }
        int totalFall = 0;
        for (Integer startBrickID : orderedBrickIDs) {
            ArrayList<Integer> brickIDsToCheck = new ArrayList<>();
            ArrayList<Integer> fallenBrickIDs = new ArrayList<>();
            fallenBrickIDs.add(0);
            brickIDsToCheck.add(startBrickID);
            while (!brickIDsToCheck.isEmpty()) {
                Integer brickID = brickIDsToCheck.remove(0);
                Pair<int[], int[]> brick = bricks.get(brickID);
                int[] firstCoors = brick.getLeft();
                int[] secondCoors = brick.getRight();
                boolean canFall = true;
                canFallLoop: for (int x = firstCoors[0]; x <= secondCoors[0]; x++) {
                    for (int y = firstCoors[1]; y <= secondCoors[1]; y++) {
                        if (!fallenBrickIDs.contains(map[x][y][firstCoors[2] - 1])) {
                            canFall = false;
                            break canFallLoop;
                        }
                    }
                }
                if (canFall || Objects.equals(startBrickID, brickID)) {
                    fallenBrickIDs.add(brickID);
                    if (!Objects.equals(startBrickID, brickID)) {
                        totalFall++;
                    }
                    ArrayList<Integer> idsOfBricksItIsHolding = new ArrayList<>();
                    for (int x = firstCoors[0]; x <= secondCoors[0]; x++) {
                        for (int y = firstCoors[1]; y <= secondCoors[1]; y++) {
                            if (map[x][y][secondCoors[2] + 1] != 0 && !idsOfBricksItIsHolding.contains(map[x][y][secondCoors[2] + 1])) {
                                idsOfBricksItIsHolding.add(map[x][y][secondCoors[2] + 1]);
                            }
                        }
                    }
                    for (Integer brickToAddToCheckID : idsOfBricksItIsHolding) {
                        if (!brickIDsToCheck.contains(brickToAddToCheckID)) {
                            int checkAddIndex = 0;
                            while (checkAddIndex < brickIDsToCheck.size() && bricks.get(brickToAddToCheckID).getLeft()[2] > bricks.get(brickIDsToCheck.get(checkAddIndex)).getLeft()[2]) {
                                checkAddIndex++;
                            }
                            brickIDsToCheck.add(checkAddIndex, brickToAddToCheckID);
                        }
                    }
                }
            }
        }
        System.out.println(totalFall);
    }
}
