package year2021.code;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Question18 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2021/input/Question18.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        ArrayList<FishNumber> fishNumbers = new ArrayList<>();
        for (String inp : input) {
            String[] line = inp.split("");
            ArrayList<FishNumber> fishQueue = new ArrayList<>();
            FishNumber f = null;
            for (String c : line) {
                switch (c) {
                    case "[":
                        fishQueue.add(new FishNumber());
                        break;
                    case "]":
                        f = fishQueue.remove(fishQueue.size() - 1);
                        if (fishQueue.size() > 0) {
                            FishNumber curFishNumber = fishQueue.get(fishQueue.size() - 1);
                            if (curFishNumber.leftNumber != -1 || curFishNumber.leftFishNumber != null) {
                                curFishNumber.rightFishNumber = f;
                            } else {
                                curFishNumber.leftFishNumber = f;
                            }
                        }
                        break;
                    case "0":
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                    case "9":
                        FishNumber curFishNumber = fishQueue.get(fishQueue.size() - 1);
                        if (curFishNumber.leftNumber != -1 || curFishNumber.leftFishNumber != null) {
                            curFishNumber.rightNumber = Integer.parseInt(c);
                        } else {
                            curFishNumber.leftNumber = Integer.parseInt(c);
                        }
                }
            }
            fishNumbers.add(f);
        }
        while (fishNumbers.size() > 1) {
            FishNumber fishNumber1 = fishNumbers.remove(0);
            FishNumber fishNumber2 = fishNumbers.remove(0);
            FishNumber newFishNumber = new FishNumber();
            newFishNumber.leftFishNumber = fishNumber1;
            newFishNumber.rightFishNumber = fishNumber2;
            fishNumbers.add(0, reduceFishNumber(newFishNumber));
        }
        System.out.println(fishNumbers.get(0).getMagnitude());
    }

    private static void part2(String[] input) {
        ArrayList<FishNumber> fishNumbers = new ArrayList<>();
        for (String inp : input) {
            String[] line = inp.split("");
            ArrayList<FishNumber> fishQueue = new ArrayList<>();
            FishNumber f = null;
            for (String c : line) {
                switch (c) {
                    case "[":
                        fishQueue.add(new FishNumber());
                        break;
                    case "]":
                        f = fishQueue.remove(fishQueue.size() - 1);
                        if (fishQueue.size() > 0) {
                            FishNumber curFishNumber = fishQueue.get(fishQueue.size() - 1);
                            if (curFishNumber.leftNumber != -1 || curFishNumber.leftFishNumber != null) {
                                curFishNumber.rightFishNumber = f;
                            } else {
                                curFishNumber.leftFishNumber = f;
                            }
                        }
                        break;
                    case "0":
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                    case "9":
                        FishNumber curFishNumber = fishQueue.get(fishQueue.size() - 1);
                        if (curFishNumber.leftNumber != -1 || curFishNumber.leftFishNumber != null) {
                            curFishNumber.rightNumber = Integer.parseInt(c);
                        } else {
                            curFishNumber.leftNumber = Integer.parseInt(c);
                        }
                }
            }
            fishNumbers.add(f);
        }
        int largest = 0;
        for (int i = 0; i < fishNumbers.size(); i++) {
            for (int j = 0; j < fishNumbers.size(); j++) {
                if (i != j) {
                    FishNumber fishNumber1 = fishNumbers.get(i).getCopy();
                    FishNumber fishNumber2 = fishNumbers.get(j).getCopy();
                    FishNumber newFishNumber = new FishNumber();
                    newFishNumber.leftFishNumber = fishNumber1;
                    newFishNumber.rightFishNumber = fishNumber2;
                    largest = Math.max(largest, reduceFishNumber(newFishNumber).getMagnitude());
                }
            }
        }
        System.out.println(largest);
    }

    private static FishNumber reduceFishNumber(FishNumber fishNumber) {
        boolean actionTaken  = true;
        while (actionTaken) {
            actionTaken = false;
            if (tryExplode(fishNumber, 0) != null) {
                actionTaken = true;
            } else {
                if (trySplit(fishNumber)) {
                    actionTaken = true;
                }
            }
        }
        return fishNumber;
    }

    private static Point tryExplode(FishNumber fishNumber, int depth) {
        Point exploded = null;
        if (depth == 4) {
            return new Point(fishNumber.leftNumber, fishNumber.rightNumber);
        } else {
            if (fishNumber.leftFishNumber != null) {
                exploded = tryExplode(fishNumber.leftFishNumber, depth + 1);
                if (exploded != null) {
                    if (depth == 3) {
                        fishNumber.leftFishNumber = null;
                        fishNumber.leftNumber = 0;
                    }
                    if (exploded.y != -1) {
                        if (fishNumber.rightFishNumber == null) {
                            fishNumber.rightNumber += exploded.y;
                            exploded.y = -1;
                        } else {
                            FishNumber curFish = fishNumber.rightFishNumber;
                            while (curFish.leftFishNumber != null) {
                                curFish = curFish.leftFishNumber;
                            }
                            curFish.leftNumber += exploded.y;
                            exploded.y = -1;
                        }
                    }
                }
            }
            if (exploded == null && fishNumber.rightFishNumber != null) {
                exploded = tryExplode(fishNumber.rightFishNumber, depth + 1);
                if (exploded != null) {
                    if (depth == 3) {
                        fishNumber.rightFishNumber = null;
                        fishNumber.rightNumber = 0;
                    }
                    if (exploded.x != -1) {
                        if (fishNumber.leftFishNumber == null) {
                            fishNumber.leftNumber += exploded.x;
                            exploded.x = -1;
                        } else {
                            FishNumber curFish = fishNumber.leftFishNumber;
                            while (curFish.rightFishNumber != null) {
                                curFish = curFish.rightFishNumber;
                            }
                            curFish.rightNumber += exploded.x;
                            exploded.x = -1;
                        }
                    }
                }
            }
        }
        return exploded;
    }

    private static boolean trySplit(FishNumber fishNumber) {
        boolean split = false;
        if (fishNumber.leftFishNumber == null) {
            if (fishNumber.leftNumber >= 10) {
                split = true;
                FishNumber f = new FishNumber();
                f.leftNumber = Math.floorDiv(fishNumber.leftNumber, 2);
                f.rightNumber = f.leftNumber + (fishNumber.leftNumber % 2);
                fishNumber.leftFishNumber = f;
                fishNumber.leftNumber = -1;
            }
        } else {
            split = trySplit(fishNumber.leftFishNumber);
        }
        if (!split) {
            if (fishNumber.rightFishNumber == null) {
                if (fishNumber.rightNumber >= 10) {
                    split = true;
                    FishNumber f = new FishNumber();
                    f.leftNumber = Math.floorDiv(fishNumber.rightNumber, 2);
                    f.rightNumber = f.leftNumber + (fishNumber.rightNumber % 2);
                    fishNumber.rightFishNumber = f;
                    fishNumber.rightNumber = -1;
                }
            } else {
                split = trySplit(fishNumber.rightFishNumber);
            }
        }
        return split;
    }
}

class FishNumber{
    int leftNumber  = -1;
    int rightNumber = -1;
    FishNumber leftFishNumber = null;
    FishNumber rightFishNumber = null;

    Object getLeftNum() {
        if (leftNumber == -1) {
            return leftFishNumber;
        } else {
            return leftNumber;
        }
    }

    Object getRightNum() {
        if (leftNumber == -1) {
            return rightFishNumber;
        } else {
            return rightNumber;
        }
    }

    int getMagnitude() {
        int leftValue;
        int rightValue;
        if (leftFishNumber == null) {
            leftValue = leftNumber;
        } else {
            leftValue = leftFishNumber.getMagnitude();
        }
        if (rightFishNumber == null) {
            rightValue = rightNumber;
        } else {
            rightValue = rightFishNumber.getMagnitude();
        }
        return leftValue * 3 + rightValue * 2;
    }

    FishNumber getCopy() {
        FishNumber ret = new FishNumber();
        ret.leftNumber = leftNumber;
        ret.rightNumber = rightNumber;
        ret.leftFishNumber = leftFishNumber == null ? null : leftFishNumber.getCopy();
        ret.rightFishNumber = rightFishNumber == null ? null : rightFishNumber.getCopy();
        return ret;
    }
}
