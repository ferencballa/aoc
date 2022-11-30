package year2019.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question15 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2019, 15);
        //System.out.println("Part 1:");
        //part1(input);
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
        HashMap<Integer, HashMap<Integer, Point>> locationsWithBacktrack = new HashMap();
        HashMap<Integer, Point> locationsWithBacktrack0Row = new HashMap<>();
        locationsWithBacktrack0Row.put(0, new Point(-2, -2));
        locationsWithBacktrack.put(0, locationsWithBacktrack0Row);
        ArrayList<Point> locationsToBeExplored = new ArrayList<>();
        Point currentPointToBeExplored = new Point(0, 0);
        int currentDirectionToBeExplored = 1;
        boolean stepBackAfterExploring = false;
        int stepBackAfterExploringDirection = -1;
        boolean backtracking = false;
        boolean travelingToPoint = false;
        ArrayList<Integer> commandsToGetToPoint = new ArrayList<>();
        boolean finishFound = false;
        Point currentTile = new Point(0, 0);
        Point finish = new Point();
        //keep in mind, positive is right and down (right for x, down for y)
        while (values.customGet(curPos) - Math.floorDiv(values.customGet(curPos), 100) * 100 != 99 && !finishFound) {
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
                long inputValue = -1;
                if (stepBackAfterExploring) {
                    inputValue = stepBackAfterExploringDirection;
                    int currentTileX = currentTile.x;
                    int currentTileY = currentTile.y;
                    if (stepBackAfterExploringDirection == 1) {
                        currentTileY--;
                    } else if (stepBackAfterExploringDirection == 2) {
                        currentTileY++;
                    } else if (stepBackAfterExploringDirection == 3) {
                        currentTileX--;
                    } else if (stepBackAfterExploringDirection == 4) {
                        currentTileX++;
                    }
                    currentTile = new Point(currentTileX, currentTileY);
                }
                else {
                    if (backtracking) {
                        int currentX = currentTile.x;
                        int currentY = currentTile.y;
                        Point nextTile = locationsWithBacktrack.get(currentX).get(currentY);
                        int nextTileX = nextTile.x;
                        int nextTileY = nextTile.y;
                        if (nextTileY < currentY) {
                            inputValue = 1;
                        } else if (nextTileY > currentY) {
                            inputValue = 2;
                        } else if (nextTileX < currentX) {
                            inputValue = 3;
                        } else if (nextTileX > currentX) {
                            inputValue = 4;
                        }
                        currentTile = new Point(nextTile.x, nextTile.y);
                    } else if (travelingToPoint) {
                        int currentX = currentTile.x;
                        int currentY = currentTile.y;
                        int nextTileX = currentX;
                        int nextTileY = currentY;
                        inputValue = commandsToGetToPoint.remove(0);
                        if (inputValue == 1) {
                            nextTileY--;
                        } else if (inputValue == 2) {
                            nextTileY++;
                        } else if (inputValue == 3) {
                            nextTileX--;
                        } else if (inputValue == 4) {
                            nextTileX++;
                        }
                        currentTile = new Point(nextTileX, nextTileY);
                    } else {
                        inputValue = currentDirectionToBeExplored;
                    }
                }
                values.put(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0), inputValue);
                curPos += 2;
            } else if (op == 4) {
                long mode1 = Math.floorDiv(curValue, 100);
                long val1;
                if (mode1 == 1) {
                    val1 = values.customGet(curPos + 1);
                } else {
                    val1 = values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                }
                if (stepBackAfterExploring) {
                    stepBackAfterExploring = false;
                } else {
                    int currentX = currentTile.x;
                    int currentY = currentTile.y;
                    //can only occur when exploring (unless backtracking or traveling to is broken)
                    if (val1 == 0) {
                        int wallX = currentX;
                        int wallY = currentY;
                        if (currentDirectionToBeExplored == 1) {
                            wallY--;
                        } else if (currentDirectionToBeExplored == 2) {
                            wallY++;
                        } else if (currentDirectionToBeExplored == 3) {
                            wallX--;
                        } else if (currentDirectionToBeExplored == 4) {
                            wallX++;
                        }
                        HashMap<Integer, Point> locationsWithBacktrackRow = locationsWithBacktrack.get(wallX);
                        if (locationsWithBacktrackRow == null) {
                            locationsWithBacktrack.put(wallX, new HashMap<>());
                            locationsWithBacktrackRow = locationsWithBacktrack.get(wallX);
                        }
                        locationsWithBacktrackRow.put(wallY, new Point(Integer.MAX_VALUE, Integer.MAX_VALUE));
                        currentDirectionToBeExplored++;
                        if (currentDirectionToBeExplored == 5) {
                            currentDirectionToBeExplored = 1;
                            backtracking = true;
                            //edgecase: exploring first point
                            if (currentX == 0 && currentY == 0) {
                                backtracking = false;
                                currentPointToBeExplored = locationsToBeExplored.remove(0);
                                int goalX = currentPointToBeExplored.x;
                                int goalY = currentPointToBeExplored.y;
                                while (!(goalX == 0 && goalY == 0)) {
                                    Point pointBacktravelingTo = locationsWithBacktrack.get(goalX).get(goalY);
                                    int pointBackTravelingToX = pointBacktravelingTo.x;
                                    int pointBackTravelingToY = pointBacktravelingTo.y;
                                    if (goalY > pointBackTravelingToY) {
                                        commandsToGetToPoint.add(1);
                                    } else if (goalY < pointBackTravelingToY) {
                                        commandsToGetToPoint.add(2);
                                    } else if (goalX > pointBackTravelingToX) {
                                        commandsToGetToPoint.add(3);
                                    } else if (goalX < pointBackTravelingToX) {
                                        commandsToGetToPoint.add(4);
                                    }
                                    goalX = pointBackTravelingToX;
                                    goalY = pointBackTravelingToY;
                                }
                                travelingToPoint = true;
                            }
                        }
                    } else if (val1 == 1) {
                        if (backtracking) {
                            if (currentX == 0 && currentY == 0) {
                                backtracking = false;
                                currentPointToBeExplored = locationsToBeExplored.remove(0);
                                int goalX = currentPointToBeExplored.x;
                                int goalY = currentPointToBeExplored.y;
                                while (!(goalX == 0 && goalY == 0)) {
                                    Point pointBacktravelingTo = locationsWithBacktrack.get(goalX).get(goalY);
                                    int pointBackTravelingToX = pointBacktravelingTo.x;
                                    int pointBackTravelingToY = pointBacktravelingTo.y;
                                    if (goalY < pointBackTravelingToY) {
                                        commandsToGetToPoint.add(0, 1);
                                    } else if (goalY > pointBackTravelingToY) {
                                        commandsToGetToPoint.add(0, 2);
                                    } else if (goalX < pointBackTravelingToX) {
                                        commandsToGetToPoint.add(0, 3);
                                    } else if (goalX > pointBackTravelingToX) {
                                        commandsToGetToPoint.add(0, 4);
                                    }
                                    goalX = pointBackTravelingToX;
                                    goalY = pointBackTravelingToY;
                                }
                                travelingToPoint = true;
                            } else {
                                //do nothing, backtracking is still ongoing, handled in input section
                            }
                        } else if (travelingToPoint) {
                            if (currentX == currentPointToBeExplored.x && currentY == currentPointToBeExplored.y) {
                                travelingToPoint = false;
                            } else {
                                //do nothing, traveling to point is still ongoing, handled in input section
                            }
                        } else { //exploring section
                            int openingX = currentX;
                            int openingY = currentY;
                            if (currentDirectionToBeExplored == 1) {
                                openingY--;
                            } else if (currentDirectionToBeExplored == 2) {
                                openingY++;
                            } else if (currentDirectionToBeExplored == 3) {
                                openingX--;
                            } else if (currentDirectionToBeExplored == 4) {
                                openingX++;
                            }
                            HashMap<Integer, Point> locationsWithBacktrackRow = locationsWithBacktrack.get(openingX);
                            if (locationsWithBacktrackRow == null) {
                                locationsWithBacktrack.put(openingX, new HashMap<>());
                                locationsWithBacktrackRow = locationsWithBacktrack.get(openingX);
                            }
                            if (locationsWithBacktrackRow.get(openingY) == null) {
                                locationsWithBacktrackRow.put(openingY, new Point(currentX, currentY));
                                locationsToBeExplored.add(new Point(openingX, openingY));
                            }
                            currentTile = new Point(openingX, openingY);
                            currentDirectionToBeExplored++;
                            if (currentDirectionToBeExplored == 5) {
                                currentDirectionToBeExplored = 1;
                                backtracking = true;
                            } else {
                                stepBackAfterExploring = true;
                                if (currentDirectionToBeExplored == 2) {
                                    stepBackAfterExploringDirection = 2;
                                } else if (currentDirectionToBeExplored == 3) {
                                    stepBackAfterExploringDirection = 1;
                                } else if (currentDirectionToBeExplored == 4) {
                                    stepBackAfterExploringDirection = 4;
                                }
                            }
                        }
                    } else if (val1 == 2) {
                        finishFound = true;
                        int finishX = currentX;
                        int finishY = currentY;
                        if (currentDirectionToBeExplored == 1) {
                            finishY--;
                        } else if (currentDirectionToBeExplored == 2) {
                            finishY++;
                        } else if (currentDirectionToBeExplored == 3) {
                            finishX--;
                        } else if (currentDirectionToBeExplored == 4) {
                            finishX++;
                        }
                        HashMap<Integer, Point> locationsWithBacktrackRow = locationsWithBacktrack.get(finishX);
                        if (locationsWithBacktrackRow == null) {
                            locationsWithBacktrack.put(finishX, new HashMap<>());
                            locationsWithBacktrackRow = locationsWithBacktrack.get(finishX);
                        }
                        locationsWithBacktrackRow.put(finishY, new Point(currentX, currentY));
                        finish = new Point(finishX, finishY);
                    }
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
        int steps = 0;
        int currentX = finish.x;
        int currentY = finish.y;
        while (!(currentX == 0 && currentY == 0)) {
            Point nextPoint = locationsWithBacktrack.get(currentX).get(currentY);
            currentX = nextPoint.x;
            currentY = nextPoint.y;
            steps++;
        }
        System.out.println(steps);
    }

    private static void part2(String[] input) {
        String[] stringValues = input[0].split(",");
        CustomHashMap<Long, Long> values = new CustomHashMap<>();
        for (int i = 0; i < stringValues.length; i++) {
            values.put((long) i, Long.parseLong(stringValues[i]));
        }
        long curPos = 0;
        long offset = 0;
        HashMap<Integer, HashMap<Integer, Point>> locationsWithBacktrack = new HashMap();
        HashMap<Integer, Point> locationsWithBacktrack0Row = new HashMap<>();
        locationsWithBacktrack0Row.put(0, new Point(-2, -2));
        locationsWithBacktrack.put(0, locationsWithBacktrack0Row);
        ArrayList<Point> locationsToBeExplored = new ArrayList<>();
        Point currentPointToBeExplored = new Point(0, 0);
        int currentDirectionToBeExplored = 1;
        boolean stepBackAfterExploring = false;
        int stepBackAfterExploringDirection = -1;
        boolean backtracking = false;
        boolean travelingToPoint = false;
        ArrayList<Integer> commandsToGetToPoint = new ArrayList<>();
        boolean finishFound = false;
        boolean exploredEverything = false;
        Point currentTile = new Point(0, 0);
        Point finish = new Point();
        //keep in mind, positive is right and down (right for x, down for y)
        while (values.customGet(curPos) - Math.floorDiv(values.customGet(curPos), 100) * 100 != 99 && !exploredEverything) {
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
                long inputValue = -1;
                if (stepBackAfterExploring) {
                    inputValue = stepBackAfterExploringDirection;
                    int currentTileX = currentTile.x;
                    int currentTileY = currentTile.y;
                    if (stepBackAfterExploringDirection == 1) {
                        currentTileY--;
                    } else if (stepBackAfterExploringDirection == 2) {
                        currentTileY++;
                    } else if (stepBackAfterExploringDirection == 3) {
                        currentTileX--;
                    } else if (stepBackAfterExploringDirection == 4) {
                        currentTileX++;
                    }
                    currentTile = new Point(currentTileX, currentTileY);
                }
                else {
                    if (backtracking) {
                        int currentX = currentTile.x;
                        int currentY = currentTile.y;
                        Point nextTile = locationsWithBacktrack.get(currentX).get(currentY);
                        int nextTileX = nextTile.x;
                        int nextTileY = nextTile.y;
                        if (nextTileY < currentY) {
                            inputValue = 1;
                        } else if (nextTileY > currentY) {
                            inputValue = 2;
                        } else if (nextTileX < currentX) {
                            inputValue = 3;
                        } else if (nextTileX > currentX) {
                            inputValue = 4;
                        }
                        currentTile = new Point(nextTile.x, nextTile.y);
                    } else if (travelingToPoint) {
                        int currentX = currentTile.x;
                        int currentY = currentTile.y;
                        int nextTileX = currentX;
                        int nextTileY = currentY;
                        inputValue = commandsToGetToPoint.remove(0);
                        if (inputValue == 1) {
                            nextTileY--;
                        } else if (inputValue == 2) {
                            nextTileY++;
                        } else if (inputValue == 3) {
                            nextTileX--;
                        } else if (inputValue == 4) {
                            nextTileX++;
                        }
                        currentTile = new Point(nextTileX, nextTileY);
                    } else {
                        inputValue = currentDirectionToBeExplored;
                    }
                }
                values.put(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0), inputValue);
                curPos += 2;
            } else if (op == 4) {
                long mode1 = Math.floorDiv(curValue, 100);
                long val1;
                if (mode1 == 1) {
                    val1 = values.customGet(curPos + 1);
                } else {
                    val1 = values.customGet(values.customGet(curPos + 1) + (mode1 == 2 ? offset : 0));
                }
                if (stepBackAfterExploring) {
                    stepBackAfterExploring = false;
                } else {
                    int currentX = currentTile.x;
                    int currentY = currentTile.y;
                    //can only occur when exploring (unless backtracking or traveling to is broken)
                    if (val1 == 0) {
                        int wallX = currentX;
                        int wallY = currentY;
                        if (currentDirectionToBeExplored == 1) {
                            wallY--;
                        } else if (currentDirectionToBeExplored == 2) {
                            wallY++;
                        } else if (currentDirectionToBeExplored == 3) {
                            wallX--;
                        } else if (currentDirectionToBeExplored == 4) {
                            wallX++;
                        }
                        HashMap<Integer, Point> locationsWithBacktrackRow = locationsWithBacktrack.get(wallX);
                        if (locationsWithBacktrackRow == null) {
                            locationsWithBacktrack.put(wallX, new HashMap<>());
                            locationsWithBacktrackRow = locationsWithBacktrack.get(wallX);
                        }
                        locationsWithBacktrackRow.put(wallY, new Point(Integer.MAX_VALUE, Integer.MAX_VALUE));
                        currentDirectionToBeExplored++;
                        if (currentDirectionToBeExplored == 5) {
                            currentDirectionToBeExplored = 1;
                            backtracking = true;
                            //edgecase: exploring first point
                            if (currentX == 0 && currentY == 0) {
                                backtracking = false;
                                currentPointToBeExplored = locationsToBeExplored.remove(0);
                                int goalX = currentPointToBeExplored.x;
                                int goalY = currentPointToBeExplored.y;
                                while (!(goalX == 0 && goalY == 0)) {
                                    Point pointBacktravelingTo = locationsWithBacktrack.get(goalX).get(goalY);
                                    int pointBackTravelingToX = pointBacktravelingTo.x;
                                    int pointBackTravelingToY = pointBacktravelingTo.y;
                                    if (goalY > pointBackTravelingToY) {
                                        commandsToGetToPoint.add(1);
                                    } else if (goalY < pointBackTravelingToY) {
                                        commandsToGetToPoint.add(2);
                                    } else if (goalX > pointBackTravelingToX) {
                                        commandsToGetToPoint.add(3);
                                    } else if (goalX < pointBackTravelingToX) {
                                        commandsToGetToPoint.add(4);
                                    }
                                    goalX = pointBackTravelingToX;
                                    goalY = pointBackTravelingToY;
                                }
                                travelingToPoint = true;
                            }
                        }
                    } else if (val1 == 1) {
                        if (backtracking) {
                            if (currentX == 0 && currentY == 0) {
                                backtracking = false;
                                if (locationsToBeExplored.isEmpty()) {
                                    exploredEverything = true;
                                } else {
                                    currentPointToBeExplored = locationsToBeExplored.remove(0);
                                    int goalX = currentPointToBeExplored.x;
                                    int goalY = currentPointToBeExplored.y;
                                    while (!(goalX == 0 && goalY == 0)) {
                                        Point pointBacktravelingTo = locationsWithBacktrack.get(goalX).get(goalY);
                                        int pointBackTravelingToX = pointBacktravelingTo.x;
                                        int pointBackTravelingToY = pointBacktravelingTo.y;
                                        if (goalY < pointBackTravelingToY) {
                                            commandsToGetToPoint.add(0, 1);
                                        } else if (goalY > pointBackTravelingToY) {
                                            commandsToGetToPoint.add(0, 2);
                                        } else if (goalX < pointBackTravelingToX) {
                                            commandsToGetToPoint.add(0, 3);
                                        } else if (goalX > pointBackTravelingToX) {
                                            commandsToGetToPoint.add(0, 4);
                                        }
                                        goalX = pointBackTravelingToX;
                                        goalY = pointBackTravelingToY;
                                    }
                                    travelingToPoint = true;
                                }
                            } else {
                                //do nothing, backtracking is still ongoing, handled in input section
                            }
                        } else if (travelingToPoint) {
                            if (currentX == currentPointToBeExplored.x && currentY == currentPointToBeExplored.y) {
                                travelingToPoint = false;
                            } else {
                                //do nothing, traveling to point is still ongoing, handled in input section
                            }
                        } else { //exploring section
                            int openingX = currentX;
                            int openingY = currentY;
                            if (currentDirectionToBeExplored == 1) {
                                openingY--;
                            } else if (currentDirectionToBeExplored == 2) {
                                openingY++;
                            } else if (currentDirectionToBeExplored == 3) {
                                openingX--;
                            } else if (currentDirectionToBeExplored == 4) {
                                openingX++;
                            }
                            HashMap<Integer, Point> locationsWithBacktrackRow = locationsWithBacktrack.get(openingX);
                            if (locationsWithBacktrackRow == null) {
                                locationsWithBacktrack.put(openingX, new HashMap<>());
                                locationsWithBacktrackRow = locationsWithBacktrack.get(openingX);
                            }
                            if (locationsWithBacktrackRow.get(openingY) == null) {
                                locationsWithBacktrackRow.put(openingY, new Point(currentX, currentY));
                                locationsToBeExplored.add(new Point(openingX, openingY));
                            }
                            currentTile = new Point(openingX, openingY);
                            currentDirectionToBeExplored++;
                            if (currentDirectionToBeExplored == 5) {
                                currentDirectionToBeExplored = 1;
                                backtracking = true;
                            } else {
                                stepBackAfterExploring = true;
                                if (currentDirectionToBeExplored == 2) {
                                    stepBackAfterExploringDirection = 2;
                                } else if (currentDirectionToBeExplored == 3) {
                                    stepBackAfterExploringDirection = 1;
                                } else if (currentDirectionToBeExplored == 4) {
                                    stepBackAfterExploringDirection = 4;
                                }
                            }
                        }
                    } else if (val1 == 2) {
                        if (!finishFound) {
                            finishFound = true;
                            int finishX = currentX;
                            int finishY = currentY;
                            if (currentDirectionToBeExplored == 1) {
                                finishY--;
                            } else if (currentDirectionToBeExplored == 2) {
                                finishY++;
                            } else if (currentDirectionToBeExplored == 3) {
                                finishX--;
                            } else if (currentDirectionToBeExplored == 4) {
                                finishX++;
                            }
                            finish = new Point(finishX, finishY);
                        }
                        if (backtracking) {
                            if (currentX == 0 && currentY == 0) {
                                backtracking = false;
                                currentPointToBeExplored = locationsToBeExplored.remove(0);
                                int goalX = currentPointToBeExplored.x;
                                int goalY = currentPointToBeExplored.y;
                                while (!(goalX == 0 && goalY == 0)) {
                                    Point pointBacktravelingTo = locationsWithBacktrack.get(goalX).get(goalY);
                                    int pointBackTravelingToX = pointBacktravelingTo.x;
                                    int pointBackTravelingToY = pointBacktravelingTo.y;
                                    if (goalY < pointBackTravelingToY) {
                                        commandsToGetToPoint.add(0, 1);
                                    } else if (goalY > pointBackTravelingToY) {
                                        commandsToGetToPoint.add(0, 2);
                                    } else if (goalX < pointBackTravelingToX) {
                                        commandsToGetToPoint.add(0, 3);
                                    } else if (goalX > pointBackTravelingToX) {
                                        commandsToGetToPoint.add(0, 4);
                                    }
                                    goalX = pointBackTravelingToX;
                                    goalY = pointBackTravelingToY;
                                }
                                travelingToPoint = true;
                            } else {
                                //do nothing, backtracking is still ongoing, handled in input section
                            }
                        } else if (travelingToPoint) {
                            if (currentX == currentPointToBeExplored.x && currentY == currentPointToBeExplored.y) {
                                travelingToPoint = false;
                            } else {
                                //do nothing, traveling to point is still ongoing, handled in input section
                            }
                        } else { //exploring section
                            int openingX = currentX;
                            int openingY = currentY;
                            if (currentDirectionToBeExplored == 1) {
                                openingY--;
                            } else if (currentDirectionToBeExplored == 2) {
                                openingY++;
                            } else if (currentDirectionToBeExplored == 3) {
                                openingX--;
                            } else if (currentDirectionToBeExplored == 4) {
                                openingX++;
                            }
                            HashMap<Integer, Point> locationsWithBacktrackRow = locationsWithBacktrack.get(openingX);
                            if (locationsWithBacktrackRow == null) {
                                locationsWithBacktrack.put(openingX, new HashMap<>());
                                locationsWithBacktrackRow = locationsWithBacktrack.get(openingX);
                            }
                            if (locationsWithBacktrackRow.get(openingY) == null) {
                                locationsWithBacktrackRow.put(openingY, new Point(currentX, currentY));
                                locationsToBeExplored.add(new Point(openingX, openingY));
                            }
                            currentTile = new Point(openingX, openingY);
                            currentDirectionToBeExplored++;
                            if (currentDirectionToBeExplored == 5) {
                                currentDirectionToBeExplored = 1;
                                backtracking = true;
                            } else {
                                stepBackAfterExploring = true;
                                if (currentDirectionToBeExplored == 2) {
                                    stepBackAfterExploringDirection = 2;
                                } else if (currentDirectionToBeExplored == 3) {
                                    stepBackAfterExploringDirection = 1;
                                } else if (currentDirectionToBeExplored == 4) {
                                    stepBackAfterExploringDirection = 4;
                                }
                            }
                        }
                    }
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
        int startX = finish.x;
        int startY = finish.y;
        //1662 spaces to be explored
        ArrayList<Point> spacesOnEdge = new ArrayList<>();
        spacesOnEdge.add(new Point(startX, startY));
        HashMap<Integer, HashMap<Integer, Integer>> distances = new HashMap<>();
        HashMap<Integer, Integer> initH = new HashMap<>();
        initH.put(startY, 0);
        distances.put(startX, initH);
        while (!spacesOnEdge.isEmpty()) {
            Point curP = spacesOnEdge.remove(0);
            int curPX = curP.x;
            int curPY = curP.y;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (Math.abs(i) + Math.abs(j) == 1) {
                        int nextPX = curPX + i;
                        int nextPY = curPY + j;
                        if (locationsWithBacktrack.get(nextPX) != null && locationsWithBacktrack.get(nextPX).get(nextPY) != null && locationsWithBacktrack.get(nextPX).get(nextPY).x != Integer.MAX_VALUE) {
                            HashMap<Integer, Integer> curRow = distances.get(nextPX);
                            if (curRow == null) {
                                distances.put(nextPX, new HashMap<>());
                                curRow = distances.get(nextPX);
                            }
                            if (curRow.get(nextPY) == null) {
                                curRow.put(nextPY, distances.get(curPX).get(curPY) + 1);
                                spacesOnEdge.add(new Point(nextPX, nextPY));
                            }
                        }
                    }
                }
            }
        }
        int biggestDistance = 0;
        for (Integer rowKey : distances.keySet()) {
            for (Integer key : distances.get(rowKey).keySet()) {
                biggestDistance = Math.max(biggestDistance, distances.get(rowKey).get(key));
            }
        }
        System.out.println(biggestDistance);
    }
}
