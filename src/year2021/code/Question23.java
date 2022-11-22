package year2021.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question23 {
    private static HashMap<String, Integer> posCost;
    private static ArrayList<SmallBurrow> positionsS;
    private static ArrayList<LargeBurrow> positionsL;

    private static SmallBurrow step1;
    private static SmallBurrow step2;
    private static SmallBurrow step3;
    private static SmallBurrow step4;
    private static SmallBurrow step5;
    private static SmallBurrow step6;
    private static SmallBurrow step7;
    private static SmallBurrow step8;
    private static SmallBurrow step9;
    private static SmallBurrow endPos;

    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2021, 23);
        //System.out.println("Part 1:");
        //part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        posCost = new HashMap<>();
        endPos = new SmallBurrow(1, 1, 2, 2, 3, 3, 4, 4);
        String endString = endPos.toString();
        positionsS = new ArrayList<>();
        SmallBurrow startPos = new SmallBurrow(1, 3, 4, 4, 3, 2, 1, 2);
        positionsS.add(startPos);
        posCost.put(startPos.toString(), 0);
        //debugP positions:
        step1 = startPos.clone();
        step1.cOne = -1;
        step1.firstMid = 2;
        step2 = step1.clone();
        step2.bOne = -1;
        step2.cOne = 3;
        step3 = step2.clone();
        step3.bTwo = -1;
        step3.secondMid = 4;
        step4 = step3.clone();
        step4.firstMid = -1;
        step4.bTwo = 2;
        step5 = step4.clone();
        step5.aOne = -1;
        step5.bOne = 2;
        step6 = step5.clone();
        step6.dOne = -1;
        step6.thirdMid = 4;
        step7 = step6.clone();
        step7.dTwo = -1;
        step7.rightIn = 1;
        step8 = step7.clone();
        step8.thirdMid = -1;
        step8.dTwo = 4;
        step9 = step8.clone();
        step9.secondMid = -1;
        step9.dOne = 4;
        //end debug positions
        while (positionsS.size() > 0) {
            SmallBurrow cur = positionsS.remove(0);
            if (cur.toString().equals(startPos.toString())) {
                System.out.println("start");
            }
            if (cur.toString().equals(step1.toString())) {
                System.out.println("step1, cost should be: 40, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(step2.toString())) {
                System.out.println("step2, cost should be: 440, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(step3.toString())) {
                System.out.println("step3, cost should be: 3440, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(step4.toString())) {
                System.out.println("step4, cost should be: 3470, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(step5.toString())) {
                System.out.println("step5, cost should be: 3510, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(step6.toString())) {
                System.out.println("step6, cost should be: 5510, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(step7.toString())) {
                System.out.println("step7, cost should be: 5513, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(step8.toString())) {
                System.out.println("step8, cost should be: 8513, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(step9.toString())) {
                System.out.println("step9, cost should be: 12513, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(endString)) {
                System.out.println("end, cost should be: 12521, cost is: " + posCost.get(cur.toString()));
            }
            int curCost = posCost.get(cur.toString());
            if (!cur.toString().equals(endString)) {
                if (cur.leftOut != -1) { //moves from leftout
                    if (cur.leftOut == 1) { //moves for 1 from leftout
                        if (cur.leftIn == -1 && cur.aOne == -1) {
                            if (cur.aTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftOut = -1;
                                newPos.aTwo = 1;
                                int newCost = curCost + 4;
                                handleCostS(newPos, newCost);
                            } else if (cur.aTwo == 1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftOut = -1;
                                newPos.aOne = 1;
                                int newCost = curCost + 3;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.leftOut == 2) { //moves for 2 from leftout
                        if (cur.leftIn == -1 && cur.firstMid == -1 && cur.bOne == -1) {
                            if (cur.bTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftOut = -1;
                                newPos.bTwo = 2;
                                int newCost = curCost + 60;
                                handleCostS(newPos, newCost);
                            } else if (cur.bTwo == 2) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftOut = -1;
                                newPos.bOne = 2;
                                int newCost = curCost + 50;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.leftOut == 3) { //moves for 3 from leftout
                        if (cur.leftIn == -1 && cur.firstMid == -1 && cur.secondMid == -1 && cur.cOne == -1) {
                            if (cur.cTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftOut = -1;
                                newPos.cTwo = 3;
                                int newCost = curCost + 800;
                                handleCostS(newPos, newCost);
                            } else if (cur.cTwo == 3) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftOut = -1;
                                newPos.cOne = 3;
                                int newCost = curCost + 700;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.leftOut == 4) { //moves for 4 from leftout
                        if (cur.leftIn == -1 && cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1 && cur.dOne == -1) {
                            if (cur.dTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftOut = -1;
                                newPos.dTwo = 4;
                                int newCost = curCost + 10000;
                                handleCostS(newPos, newCost);
                            } else if (cur.dTwo == 4) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftOut = -1;
                                newPos.dOne = 4;
                                int newCost = curCost + 9000;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                }
                if (cur.leftIn != -1) { //moves from leftIn
                    if (cur.leftIn == 1) { //moves for 1 from leftIn
                        if (cur.aOne == -1) {
                            if (cur.aTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftIn = -1;
                                newPos.aTwo = 1;
                                int newCost = curCost + 3;
                                handleCostS(newPos, newCost);
                            } else if (cur.aTwo == 1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftIn = -1;
                                newPos.aOne = 1;
                                int newCost = curCost + 2;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.leftIn == 2) { //moves for 2 from leftIn
                        if (cur.firstMid == -1 && cur.bOne == -1) {
                            if (cur.bTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftIn = -1;
                                newPos.bTwo = 2;
                                int newCost = curCost + 50;
                                handleCostS(newPos, newCost);
                            } else if (cur.bTwo == 2) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftIn = -1;
                                newPos.bOne = 2;
                                int newCost = curCost + 40;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.leftIn == 3) { //moves for 3 from leftIn
                        if (cur.firstMid == -1 && cur.secondMid == -1 && cur.cOne == -1) {
                            if (cur.cTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftIn = -1;
                                newPos.cTwo = 3;
                                int newCost = curCost + 700;
                                handleCostS(newPos, newCost);
                            } else if (cur.cTwo == 3) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftIn = -1;
                                newPos.cOne = 3;
                                int newCost = curCost + 600;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.leftIn == 4) { //moves for 4 from leftIn
                        if (cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1 && cur.dOne == -1) {
                            if (cur.dTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftIn = -1;
                                newPos.dTwo = 4;
                                int newCost = curCost + 9000;
                                handleCostS(newPos, newCost);
                            } else if (cur.dTwo == 4) {
                                SmallBurrow newPos = cur.clone();
                                newPos.leftIn = -1;
                                newPos.dOne = 4;
                                int newCost = curCost + 8000;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                }
                if (cur.rightOut != -1) { //moves from rightOut
                    if (cur.rightOut == 1) { //moves for 1 from rightOut
                        if (cur.rightIn == -1 && cur.thirdMid == -1 && cur.secondMid == -1 && cur.firstMid == -1 && cur.aOne == -1) {
                            if (cur.aTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightOut = -1;
                                newPos.aTwo = 1;
                                int newCost = curCost + 10;
                                handleCostS(newPos, newCost);
                            } else if (cur.aTwo == 1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightOut = -1;
                                newPos.aOne = 1;
                                int newCost = curCost + 9;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.rightOut == 2) { //moves for 2 from rightOut
                        if (cur.rightIn == -1 && cur.thirdMid == -1 && cur.secondMid == -1 && cur.bOne == -1) {
                            if (cur.bTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightOut = -1;
                                newPos.bTwo = 2;
                                int newCost = curCost + 80;
                                handleCostS(newPos, newCost);
                            } else if (cur.bTwo == 2) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightOut = -1;
                                newPos.bOne = 2;
                                int newCost = curCost + 70;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.rightOut == 3) { //moves for 3 from rightOut
                        if (cur.rightIn == -1 && cur.thirdMid == -1 && cur.cOne == -1) {
                            if (cur.cTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightOut = -1;
                                newPos.cTwo = 3;
                                int newCost = curCost + 600;
                                handleCostS(newPos, newCost);
                            } else if (cur.cTwo == 3) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightOut = -1;
                                newPos.cOne = 3;
                                int newCost = curCost + 500;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.rightOut == 4) { //moves for 4 from rightOut
                        if (cur.rightIn == -1 && cur.dOne == -1) {
                            if (cur.dTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightOut = -1;
                                newPos.dTwo = 4;
                                int newCost = curCost + 4000;
                                handleCostS(newPos, newCost);
                            } else if (cur.dTwo == 4) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightOut = -1;
                                newPos.dOne = 4;
                                int newCost = curCost + 3000;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                }
                if (cur.rightIn != -1) { //moves from rightIn
                    if (cur.rightIn == 1) { //moves for 1 from rightIn
                        if (cur.thirdMid == -1 && cur.secondMid == -1 && cur.firstMid == -1 && cur.aOne == -1) {
                            if (cur.aTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightIn = -1;
                                newPos.aTwo = 1;
                                int newCost = curCost + 9;
                                handleCostS(newPos, newCost);
                            } else if (cur.aTwo == 1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightIn = -1;
                                newPos.aOne = 1;
                                int newCost = curCost + 8;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.rightIn == 2) { //moves for 2 from rightIn
                        if (cur.thirdMid == -1 && cur.secondMid == -1 && cur.bOne == -1) {
                            if (cur.bTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightIn = -1;
                                newPos.bTwo = 2;
                                int newCost = curCost + 70;
                                handleCostS(newPos, newCost);
                            } else if (cur.bTwo == 2) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightIn = -1;
                                newPos.bOne = 2;
                                int newCost = curCost + 60;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.rightIn == 3) { //moves for 3 from rightIn
                        if (cur.thirdMid == -1 && cur.cOne == -1) {
                            if (cur.cTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightIn = -1;
                                newPos.cTwo = 3;
                                int newCost = curCost + 500;
                                handleCostS(newPos, newCost);
                            } else if (cur.cTwo == 3) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightIn = -1;
                                newPos.cOne = 3;
                                int newCost = curCost + 400;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.rightIn == 4) { //moves for 4 from rightIn
                        if (cur.dOne == -1) {
                            if (cur.dTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightIn = -1;
                                newPos.dTwo = 4;
                                int newCost = curCost + 3000;
                                handleCostS(newPos, newCost);
                            } else if (cur.dTwo == 4) {
                                SmallBurrow newPos = cur.clone();
                                newPos.rightIn = -1;
                                newPos.dOne = 4;
                                int newCost = curCost + 2000;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                }
                if (cur.firstMid != -1) { //moves from firstMid
                    if (cur.firstMid == 1) { //moves for 1 from firstMid
                        if (cur.aOne == -1) {
                            if (cur.aTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.firstMid = -1;
                                newPos.aTwo = 1;
                                int newCost = curCost + 3;
                                handleCostS(newPos, newCost);
                            } else if (cur.aTwo == 1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.firstMid = -1;
                                newPos.aOne = 1;
                                int newCost = curCost + 2;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.firstMid == 2) { //moves for 2 from firstMid
                        if (cur.bOne == -1) {
                            if (cur.bTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.firstMid = -1;
                                newPos.bTwo = 2;
                                int newCost = curCost + 30;
                                handleCostS(newPos, newCost);
                            } else if (cur.bTwo == 2) {
                                SmallBurrow newPos = cur.clone();
                                newPos.firstMid = -1;
                                newPos.bOne = 2;
                                int newCost = curCost + 20;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.firstMid == 3) { //moves for 3 from firstMid
                        if (cur.secondMid == -1 && cur.cOne == -1) {
                            if (cur.cTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.firstMid = -1;
                                newPos.cTwo = 3;
                                int newCost = curCost + 500;
                                handleCostS(newPos, newCost);
                            } else if (cur.cTwo == 3) {
                                SmallBurrow newPos = cur.clone();
                                newPos.firstMid = -1;
                                newPos.cOne = 3;
                                int newCost = curCost + 400;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.firstMid == 4) { //moves for 4 from firstMid
                        if (cur.secondMid == -1 && cur.thirdMid == -1 && cur.dOne == -1) {
                            if (cur.dTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.firstMid = -1;
                                newPos.dTwo = 4;
                                int newCost = curCost + 7000;
                                handleCostS(newPos, newCost);
                            } else if (cur.dTwo == 4) {
                                SmallBurrow newPos = cur.clone();
                                newPos.firstMid = -1;
                                newPos.dOne = 4;
                                int newCost = curCost + 6000;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                }
                if (cur.thirdMid != -1) { //moves from thirdMid
                    if (cur.thirdMid == 1) { //moves for 1 from thirdMid
                        if (cur.secondMid == -1 && cur.firstMid == -1 && cur.aOne == -1) {
                            if (cur.aTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.thirdMid = -1;
                                newPos.aTwo = 1;
                                int newCost = curCost + 7;
                                handleCostS(newPos, newCost);
                            } else if (cur.aTwo == 1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.thirdMid = -1;
                                newPos.aOne = 1;
                                int newCost = curCost + 6;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.thirdMid == 2) { //moves for 2 from thirdMid
                        if (cur.secondMid == -1 && cur.bOne == -1) {
                            if (cur.bTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.thirdMid = -1;
                                newPos.bTwo = 2;
                                int newCost = curCost + 50;
                                handleCostS(newPos, newCost);
                            } else if (cur.bTwo == 2) {
                                SmallBurrow newPos = cur.clone();
                                newPos.thirdMid = -1;
                                newPos.bOne = 2;
                                int newCost = curCost + 40;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.thirdMid == 3) { //moves for 3 from thirdMid
                        if (cur.cOne == -1) {
                            if (cur.cTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.thirdMid = -1;
                                newPos.cTwo = 3;
                                int newCost = curCost + 300;
                                handleCostS(newPos, newCost);
                            } else if (cur.cTwo == 3) {
                                SmallBurrow newPos = cur.clone();
                                newPos.thirdMid = -1;
                                newPos.cOne = 3;
                                int newCost = curCost + 200;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.thirdMid == 4) { //moves for 4 from thirdMid
                        if (cur.dOne == -1) {
                            if (cur.dTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.thirdMid = -1;
                                newPos.dTwo = 4;
                                int newCost = curCost + 3000;
                                handleCostS(newPos, newCost);
                            } else if (cur.dTwo == 4) {
                                SmallBurrow newPos = cur.clone();
                                newPos.thirdMid = -1;
                                newPos.dOne = 4;
                                int newCost = curCost + 2000;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                }
                if (cur.secondMid != -1) { //moves from secondMid
                    if (cur.secondMid == 1) { //moves for 1 from secondMid
                        if (cur.firstMid == -1 && cur.aOne == -1) {
                            if (cur.aTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.secondMid = -1;
                                newPos.aTwo = 1;
                                int newCost = curCost + 5;
                                handleCostS(newPos, newCost);
                            } else if (cur.aTwo == 1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.secondMid = -1;
                                newPos.aOne = 1;
                                int newCost = curCost + 4;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.secondMid == 2) { //moves for 2 from secondMid
                        if (cur.bOne == -1) {
                            if (cur.bTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.secondMid = -1;
                                newPos.bTwo = 2;
                                int newCost = curCost + 30;
                                handleCostS(newPos, newCost);
                            } else if (cur.bTwo == 2) {
                                SmallBurrow newPos = cur.clone();
                                newPos.secondMid = -1;
                                newPos.bOne = 2;
                                int newCost = curCost + 20;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.secondMid == 3) { //moves for 3 from secondMid
                        if (cur.cOne == -1) {
                            if (cur.cTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.secondMid = -1;
                                newPos.cTwo = 3;
                                int newCost = curCost + 300;
                                handleCostS(newPos, newCost);
                            } else if (cur.cTwo == 3) {
                                SmallBurrow newPos = cur.clone();
                                newPos.secondMid = -1;
                                newPos.cOne = 3;
                                int newCost = curCost + 200;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                    if (cur.secondMid == 4) { //moves for 4 from secondMid
                        if (cur.thirdMid == -1 && cur.dOne == -1) {
                            if (cur.dTwo == -1) {
                                SmallBurrow newPos = cur.clone();
                                newPos.secondMid = -1;
                                newPos.dTwo = 4;
                                int newCost = curCost + 5000;
                                handleCostS(newPos, newCost);
                            } else if (cur.dTwo == 4) {
                                SmallBurrow newPos = cur.clone();
                                newPos.secondMid = -1;
                                newPos.dOne = 4;
                                int newCost = curCost + 4000;
                                handleCostS(newPos, newCost);
                            }
                        }
                    }
                }
                for (int depthFromTop = 0; depthFromTop < 2; depthFromTop++) {
                    if ((depthFromTop == 0 && cur.aOne != -1 && (cur.aOne != 1 || cur.aTwo != 1)) || (depthFromTop == 1 && cur.aOne == -1 && cur.aTwo != -1 && cur.aTwo != 1)) { //moves from a1 & a2
                        int compVal;
                        if (depthFromTop == 0) {
                            compVal = cur.aOne;
                        } else {
                            compVal = cur.aTwo;
                        }
                        int factor = 1;
                        if (compVal == 2) {
                            factor = 10;
                        } else if (compVal == 3) {
                            factor = 100;
                        } else if (compVal == 4) {
                            factor = 1000;
                        }
                        if (cur.leftIn == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.aOne = -1;
                            } else {
                                newPos.aTwo = -1;
                            }
                            newPos.leftIn = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.leftIn == -1 && cur.leftOut == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.aOne = -1;
                            } else {
                                newPos.aTwo = -1;
                            }
                            newPos.leftOut = compVal;
                            int newCost = curCost + (3 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.firstMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.aOne = -1;
                            } else {
                                newPos.aTwo = -1;
                            }
                            newPos.firstMid = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.firstMid == -1 && cur.secondMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.aOne = -1;
                            } else {
                                newPos.aTwo = -1;
                            }
                            newPos.secondMid = compVal;
                            int newCost = curCost + (4 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.aOne = -1;
                            } else {
                                newPos.aTwo = -1;
                            }
                            newPos.thirdMid = compVal;
                            int newCost = curCost + (6 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1 && cur.rightIn == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.aOne = -1;
                            } else {
                                newPos.aTwo = -1;
                            }
                            newPos.rightIn = compVal;
                            int newCost = curCost + (8 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1 && cur.rightIn == -1 && cur.rightOut == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.aOne = -1;
                            } else {
                                newPos.aTwo = -1;
                            }
                            newPos.rightOut = compVal;
                            int newCost = curCost + (9 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (compVal == 2) { //moves for specifically 2 from a1 & a2
                            if (cur.firstMid == -1 && cur.bOne == -1) {
                                if (cur.bTwo == -1) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.aOne = -1;
                                    } else {
                                        newPos.aTwo = -1;
                                    }
                                    newPos.bTwo = compVal;
                                    int newCost = curCost + (5 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                } else if (cur.bTwo == compVal) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.aOne = -1;
                                    } else {
                                        newPos.aTwo = -1;
                                    }
                                    newPos.bOne = compVal;
                                    int newCost = curCost + (4 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 3) { //moves for specifically 3 from a1 & a2
                            if (cur.firstMid == -1 && cur.secondMid == -1 && cur.cOne == -1) {
                                if (cur.cTwo == -1) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.aOne = -1;
                                    } else {
                                        newPos.aTwo = -1;
                                    }
                                    newPos.cTwo = compVal;
                                    int newCost = curCost + (7 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                } else if (cur.cTwo == compVal) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.aOne = -1;
                                    } else {
                                        newPos.aTwo = -1;
                                    }
                                    newPos.cOne = compVal;
                                    int newCost = curCost + (6 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 4) { //moves for specifically 4 from a1 & a2
                            if (cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1 && cur.dOne == -1) {
                                if (cur.dTwo == -1) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.aOne = -1;
                                    } else {
                                        newPos.aTwo = -1;
                                    }
                                    newPos.dTwo = compVal;
                                    int newCost = curCost + (9 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                } else if (cur.dTwo == compVal) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.aOne = -1;
                                    } else {
                                        newPos.aTwo = -1;
                                    }
                                    newPos.dOne = compVal;
                                    int newCost = curCost + (8 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                }
                            }
                        }
                    }
                }
                for (int depthFromTop = 0; depthFromTop < 2; depthFromTop++) {
                    if ((depthFromTop == 0 && cur.bOne != -1 && (cur.bOne != 2 || cur.bTwo != 2)) || (depthFromTop == 1 && cur.bOne == -1 && cur.bTwo != -1 && cur.bTwo != 2)) { //moves from b1 & b2
                        int compVal;
                        if (depthFromTop == 0) {
                            compVal = cur.bOne;
                        } else {
                            compVal = cur.bTwo;
                        }
                        int factor = 1;
                        if (compVal == 2) {
                            factor = 10;
                        } else if (compVal == 3) {
                            factor = 100;
                        } else if (compVal == 4) {
                            factor = 1000;
                        }
                        if (cur.leftIn == -1 && cur.firstMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.bOne = -1;
                            } else {
                                newPos.bTwo = -1;
                            }
                            newPos.leftIn = compVal;
                            int newCost = curCost + (4 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.leftIn == -1 && cur.leftOut == -1 && cur.firstMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.bOne = -1;
                            } else {
                                newPos.bTwo = -1;
                            }
                            newPos.leftOut = compVal;
                            int newCost = curCost + (5 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.firstMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.bOne = -1;
                            } else {
                                newPos.bTwo = -1;
                            }
                            newPos.firstMid = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.secondMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.bOne = -1;
                            } else {
                                newPos.bTwo = -1;
                            }
                            newPos.secondMid = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.secondMid == -1 && cur.thirdMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.bOne = -1;
                            } else {
                                newPos.bTwo = -1;
                            }
                            newPos.thirdMid = compVal;
                            int newCost = curCost + (4 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.secondMid == -1 && cur.thirdMid == -1 && cur.rightIn == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.bOne = -1;
                            } else {
                                newPos.bTwo = -1;
                            }
                            newPos.rightIn = compVal;
                            int newCost = curCost + (6 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.secondMid == -1 && cur.thirdMid == -1 && cur.rightIn == -1 && cur.rightOut == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.bOne = -1;
                            } else {
                                newPos.bTwo = -1;
                            }
                            newPos.rightOut = compVal;
                            int newCost = curCost + (7 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (compVal == 1) { //moves for specifically 1 from b1 & b2
                            if (cur.firstMid == -1 && cur.aOne == -1) {
                                if (cur.aTwo == -1) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.bOne = -1;
                                    } else {
                                        newPos.bTwo = -1;
                                    }
                                    newPos.aTwo = compVal;
                                    int newCost = curCost + (5 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                } else if (cur.aTwo == compVal) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.bOne = -1;
                                    } else {
                                        newPos.bTwo = -1;
                                    }
                                    newPos.aOne = compVal;
                                    int newCost = curCost + (4 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 3) { //moves for specifically 3 from b1 & b2
                            if (cur.secondMid == -1 && cur.cOne == -1) {
                                if (cur.cTwo == -1) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.bOne = -1;
                                    } else {
                                        newPos.bTwo = -1;
                                    }
                                    newPos.cTwo = compVal;
                                    int newCost = curCost + (5 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                } else if (cur.cTwo == compVal) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.bOne = -1;
                                    } else {
                                        newPos.bTwo = -1;
                                    }
                                    newPos.cOne = compVal;
                                    int newCost = curCost + (4 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 4) { //moves for specifically 4 from b1 & b2
                            if (cur.secondMid == -1 && cur.thirdMid == -1 && cur.dOne == -1) {
                                if (cur.dTwo == -1) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.bOne = -1;
                                    } else {
                                        newPos.bTwo = -1;
                                    }
                                    newPos.dTwo = compVal;
                                    int newCost = curCost + (7 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                } else if (cur.dTwo == compVal) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.bOne = -1;
                                    } else {
                                        newPos.bTwo = -1;
                                    }
                                    newPos.dOne = compVal;
                                    int newCost = curCost + (6 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                }
                            }
                        }
                    }
                }
                for (int depthFromTop = 0; depthFromTop < 2; depthFromTop++) {
                    if ((depthFromTop == 0 && cur.cOne != -1 && (cur.cOne != 3 || cur.cTwo != 3)) || (depthFromTop == 1 && cur.cOne == -1 && cur.cTwo != -1 && cur.cTwo != 3)) { //moves from c1 & c2
                        int compVal;
                        if (depthFromTop == 0) {
                            compVal = cur.cOne;
                        } else {
                            compVal = cur.cTwo;
                        }
                        int factor = 1;
                        if (compVal == 2) {
                            factor = 10;
                        } else if (compVal == 3) {
                            factor = 100;
                        } else if (compVal == 4) {
                            factor = 1000;
                        }
                        if (cur.leftIn == -1 && cur.firstMid == -1 && cur.secondMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.cOne = -1;
                            } else {
                                newPos.cTwo = -1;
                            }
                            newPos.leftIn = compVal;
                            int newCost = curCost + (6 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.leftIn == -1 && cur.leftOut == -1 && cur.firstMid == -1 && cur.secondMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.cOne = -1;
                            } else {
                                newPos.cTwo = -1;
                            }
                            newPos.leftOut = compVal;
                            int newCost = curCost + (7 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.firstMid == -1 && cur.secondMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.cOne = -1;
                            } else {
                                newPos.cTwo = -1;
                            }
                            newPos.firstMid = compVal;
                            int newCost = curCost + (4 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.secondMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.cOne = -1;
                            } else {
                                newPos.cTwo = -1;
                            }
                            newPos.secondMid = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.thirdMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.cOne = -1;
                            } else {
                                newPos.cTwo = -1;
                            }
                            newPos.thirdMid = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.thirdMid == -1 && cur.rightIn == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.cOne = -1;
                            } else {
                                newPos.cTwo = -1;
                            }
                            newPos.rightIn = compVal;
                            int newCost = curCost + (4 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.thirdMid == -1 && cur.rightIn == -1 && cur.rightOut == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.cOne = -1;
                            } else {
                                newPos.cTwo = -1;
                            }
                            newPos.rightOut = compVal;
                            int newCost = curCost + (5 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (compVal == 1) { //moves for specifically 1 from c1 & c2
                            if (cur.firstMid == -1 && cur.secondMid == -1 && cur.aOne == -1) {
                                if (cur.aTwo == -1) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.cOne = -1;
                                    } else {
                                        newPos.cTwo = -1;
                                    }
                                    newPos.aTwo = compVal;
                                    int newCost = curCost + (7 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                } else if (cur.aTwo == compVal) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.cOne = -1;
                                    } else {
                                        newPos.cTwo = -1;
                                    }
                                    newPos.aOne = compVal;
                                    int newCost = curCost + (6 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 2) { //moves for specifically 2 from c1 & c2
                            if (cur.secondMid == -1 && cur.bOne == -1) {
                                if (cur.bTwo == -1) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.cOne = -1;
                                    } else {
                                        newPos.cTwo = -1;
                                    }
                                    newPos.bTwo = compVal;
                                    int newCost = curCost + (5 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                } else if (cur.bTwo == compVal) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.cOne = -1;
                                    } else {
                                        newPos.cTwo = -1;
                                    }
                                    newPos.bOne = compVal;
                                    int newCost = curCost + (4 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 4) { //moves for specifically 4 from c1 & c2
                            if (cur.thirdMid == -1 && cur.dOne == -1) {
                                if (cur.dTwo == -1) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.cOne = -1;
                                    } else {
                                        newPos.cTwo = -1;
                                    }
                                    newPos.dTwo = compVal;
                                    int newCost = curCost + (5 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                } else if (cur.dTwo == compVal) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.cOne = -1;
                                    } else {
                                        newPos.cTwo = -1;
                                    }
                                    newPos.dOne = compVal;
                                    int newCost = curCost + (4 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                }
                            }
                        }
                    }
                }
                for (int depthFromTop = 0; depthFromTop < 2; depthFromTop++) {
                    if ((depthFromTop == 0 && cur.dOne != -1 && (cur.dOne != 4 || cur.dTwo != 4)) || (depthFromTop == 1 && cur.dOne == -1 && cur.dTwo != -1 && cur.dTwo != 4)) { //moves from d1 & d2
                        int compVal;
                        if (depthFromTop == 0) {
                            compVal = cur.dOne;
                        } else {
                            compVal = cur.dTwo;
                        }
                        int factor = 1;
                        if (compVal == 2) {
                            factor = 10;
                        } else if (compVal == 3) {
                            factor = 100;
                        } else if (compVal == 4) {
                            factor = 1000;
                        }
                        if (cur.leftIn == -1 && cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.dOne = -1;
                            } else {
                                newPos.dTwo = -1;
                            }
                            newPos.leftIn = compVal;
                            int newCost = curCost + (8 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.leftIn == -1 && cur.leftOut == -1 && cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.dOne = -1;
                            } else {
                                newPos.dTwo = -1;
                            }
                            newPos.leftOut = compVal;
                            int newCost = curCost + (9 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.dOne = -1;
                            } else {
                                newPos.dTwo = -1;
                            }
                            newPos.firstMid = compVal;
                            int newCost = curCost + (6 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.secondMid == -1 && cur.thirdMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.dOne = -1;
                            } else {
                                newPos.dTwo = -1;
                            }
                            newPos.secondMid = compVal;
                            int newCost = curCost + (4 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.thirdMid == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.dOne = -1;
                            } else {
                                newPos.dTwo = -1;
                            }
                            newPos.thirdMid = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.rightIn == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.dOne = -1;
                            } else {
                                newPos.dTwo = -1;
                            }
                            newPos.rightIn = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (cur.rightIn == -1 && cur.rightOut == -1) {
                            SmallBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.dOne = -1;
                            } else {
                                newPos.dTwo = -1;
                            }
                            newPos.rightOut = compVal;
                            int newCost = curCost + (3 + depthFromTop) * factor;
                            handleCostS(newPos, newCost);
                        }
                        if (compVal == 1) { //moves for specifically 1 from d1 & d2
                            if (cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1 && cur.aOne == -1) {
                                if (cur.aTwo == -1) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.dOne = -1;
                                    } else {
                                        newPos.dTwo = -1;
                                    }
                                    newPos.aTwo = compVal;
                                    int newCost = curCost + (9 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                } else if (cur.aTwo == compVal) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.dOne = -1;
                                    } else {
                                        newPos.dTwo = -1;
                                    }
                                    newPos.aOne = compVal;
                                    int newCost = curCost + (8 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 2) { //moves for specifically 2 from d1 & d2
                            if (cur.secondMid == -1 && cur.thirdMid == -1 && cur.bOne == -1) {
                                if (cur.bTwo == -1) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.dOne = -1;
                                    } else {
                                        newPos.dTwo = -1;
                                    }
                                    newPos.bTwo = compVal;
                                    int newCost = curCost + (7 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                } else if (cur.bTwo == compVal) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.dOne = -1;
                                    } else {
                                        newPos.dTwo = -1;
                                    }
                                    newPos.bOne = compVal;
                                    int newCost = curCost + (6 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 3) { //moves for specifically 3 from d1 & d2
                            if (cur.thirdMid == -1 && cur.cOne == -1) {
                                if (cur.cTwo == -1) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.dOne = -1;
                                    } else {
                                        newPos.dTwo = -1;
                                    }
                                    newPos.cTwo = compVal;
                                    int newCost = curCost + (5 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                } else if (cur.cTwo == compVal) {
                                    SmallBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.dOne = -1;
                                    } else {
                                        newPos.dTwo = -1;
                                    }
                                    newPos.cOne = compVal;
                                    int newCost = curCost + (4 + depthFromTop) * factor;
                                    handleCostS(newPos, newCost);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(posCost.get(endString));
    }

    private static void part2(String[] input) {
        posCost = new HashMap<>();
        LargeBurrow endPosL = new LargeBurrow(1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4);
        String endString = endPosL.toString();
        positionsL = new ArrayList<>();
        LargeBurrow startPos = new LargeBurrow(1, 4, 4, 3, 4, 3, 2, 4, 3, 2, 1, 2, 1, 1, 3, 2);
        //LargeBurrow startPos = new LargeBurrow(2, 4, 4, 1, 3, 3, 2, 4, 2, 2, 1, 3, 4, 1, 3, 1); //testinput
        positionsL.add(startPos);
        posCost.put(startPos.toString(), 0);
        //debugP positions:
        LargeBurrow stepL1 = startPos.clone(); //3000
        stepL1.dOne = -1;
        stepL1.rightOut = 4;
        LargeBurrow stepL2 = stepL1.clone(); //3010
        stepL2.dTwo = -1;
        stepL2.leftOut = 1;
        LargeBurrow stepL3 = stepL2.clone(); //3050
        stepL3.cOne = -1;
        stepL3.rightIn = 2;
        LargeBurrow stepL4 = stepL3.clone(); //3080
        stepL4.cTwo = -1;
        stepL4.thirdMid = 2;
        LargeBurrow stepL5 = stepL4.clone(); //3088
        stepL5.cThree = -1;
        stepL5.leftIn = 1;
        LargeBurrow stepL6 = stepL5.clone(); //3688
        stepL6.bOne = -1;
        stepL6.cThree = 3;
        LargeBurrow stepL7 = stepL6.clone(); //4288
        stepL7.bTwo = -1;
        stepL7.cTwo = 3;
        LargeBurrow stepL8 = stepL7.clone(); //4328
        stepL8.bThree = -1;
        stepL8.secondMid = 2;
        LargeBurrow stepL9 = stepL8.clone(); //9328
        stepL9.bFour = -1;
        stepL9.firstMid = 4;
        LargeBurrow stepL10 = stepL9.clone(); //9378
        stepL10.secondMid = -1;
        stepL10.bFour = 2;
        LargeBurrow stepL11 = stepL10.clone(); //9438
        stepL11.thirdMid = -1;
        stepL11.bThree = 2;
        LargeBurrow stepL12 = stepL11.clone(); //9508
        stepL12.rightIn = -1;
        stepL12.bTwo = 2;
        LargeBurrow stepL13 = stepL12.clone(); //10108
        stepL13.dThree = -1;
        stepL13.cOne = 3;
        LargeBurrow stepL14 = stepL13.clone(); //10113
        stepL14.dFour = -1;
        stepL14.rightIn = 1;
        LargeBurrow stepL15 = stepL14.clone(); //19113
        stepL15.firstMid = -1;
        stepL15.dFour = 4;
        LargeBurrow stepL16 = stepL15.clone(); //19153
        stepL16.aOne = -1;
        stepL16.bOne = 2;
        LargeBurrow stepL17 = stepL16.clone(); //30153
        stepL17.aTwo = -1;
        stepL17.dThree = 4;
        LargeBurrow stepL18 = stepL17.clone(); //34153
        stepL18.aThree = -1;
        stepL18.firstMid = 4;
        LargeBurrow stepL19 = stepL18.clone(); //34157
        stepL19.leftIn = -1;
        stepL19.aThree = 1;
        LargeBurrow stepL20 = stepL19.clone(); //34161
        stepL20.leftOut = -1;
        stepL20.aTwo = 1;
        LargeBurrow stepL21 = stepL20.clone(); //41161
        stepL21.firstMid = -1;
        stepL21.dTwo = 4;
        LargeBurrow stepL22 = stepL21.clone(); //41169
        stepL22.rightIn = -1;
        stepL22.aOne = 1;
        LargeBurrow stepL23 = stepL22.clone(); //44169
        stepL23.rightOut = -1;
        stepL23.dOne = 4;
        //end =
        //end debug positions
        while (positionsL.size() > 0) {
            LargeBurrow cur = positionsL.remove(0);
            if (cur.toString().equals(startPos.toString())) {
                System.out.println("start");
            }
            if (cur.toString().equals(stepL1.toString())) {
                System.out.println("step1, cost should be: 3000, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL2.toString())) {
                System.out.println("step2, cost should be: 3010, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL3.toString())) {
                System.out.println("step3, cost should be: 3050, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL4.toString())) {
                System.out.println("step4, cost should be: 3080, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL5.toString())) {
                System.out.println("step5, cost should be: 3088, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL6.toString())) {
                System.out.println("step6, cost should be: 3688, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL7.toString())) {
                System.out.println("step7, cost should be: 4288, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL8.toString())) {
                System.out.println("step8, cost should be: 4328, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL9.toString())) {
                System.out.println("step9, cost should be: 9328, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL10.toString())) {
                System.out.println("step10, cost should be: 9378, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL11.toString())) {
                System.out.println("step11, cost should be: 9438, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL12.toString())) {
                System.out.println("step12, cost should be: 9508, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL13.toString())) {
                System.out.println("step13, cost should be: 10108, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL14.toString())) {
                System.out.println("step14, cost should be: 10113, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL15.toString())) {
                System.out.println("step15, cost should be: 19113, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL16.toString())) {
                System.out.println("step16, cost should be: 19153, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL17.toString())) {
                System.out.println("step17, cost should be: 30153, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL18.toString())) {
                System.out.println("step18, cost should be: 34153, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL19.toString())) {
                System.out.println("step19, cost should be: 34157, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL20.toString())) {
                System.out.println("step20, cost should be: 34161, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL21.toString())) {
                System.out.println("step21, cost should be: 41161, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL22.toString())) {
                System.out.println("step22, cost should be: 41169, cost is: " + posCost.get(cur.toString()));
            }
            if (cur.toString().equals(stepL23.toString())) {
                System.out.println("step23, cost should be: 44169, cost is: " + posCost.get(cur.toString()));
            }
            int curCost = posCost.get(cur.toString());
            if (!cur.toString().equals(endString)) {
                if (cur.leftOut != -1) { //moves from leftout
                    if (cur.leftOut == 1) { //moves for 1 from leftout
                        if (cur.leftIn == -1 && cur.aOne == -1) {
                            if (cur.aTwo == -1) {
                                if (cur.aThree == -1) {
                                    if (cur.aFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftOut = -1;
                                        newPos.aFour = 1;
                                        int newCost = curCost + 6;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.aFour == 1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftOut = -1;
                                        newPos.aThree = 1;
                                        int newCost = curCost + 5;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.aThree == 1 && cur.aFour == 1) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.leftOut = -1;
                                    newPos.aTwo = 1;
                                    int newCost = curCost + 4;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.aTwo == 1 && cur.aThree == 1 && cur.aFour == 1) {
                                LargeBurrow newPos = cur.clone();
                                newPos.leftOut = -1;
                                newPos.aOne = 1;
                                int newCost = curCost + 3;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.leftOut == 2) { //moves for 2 from leftout
                        if (cur.leftIn == -1 && cur.firstMid == -1 && cur.bOne == -1) {
                            if (cur.bTwo == -1) {
                                if (cur.bThree == -1) {
                                    if (cur.bFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftOut = -1;
                                        newPos.bFour = 2;
                                        int newCost = curCost + 80;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.bFour == 2) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftOut = -1;
                                        newPos.bThree = 2;
                                        int newCost = curCost + 70;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.bThree == 2 && cur.bFour == 2) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.leftOut = -1;
                                    newPos.bTwo = 2;
                                    int newCost = curCost + 60;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.bTwo == 2 && cur.bThree == 2 && cur.bFour == 2) {
                                LargeBurrow newPos = cur.clone();
                                newPos.leftOut = -1;
                                newPos.bOne = 2;
                                int newCost = curCost + 50;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.leftOut == 3) { //moves for 3 from leftout
                        if (cur.leftIn == -1 && cur.firstMid == -1 && cur.secondMid == -1 && cur.cOne == -1) {
                            if (cur.cTwo == -1) {
                                if (cur.cThree == -1) {
                                    if (cur.cFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftOut = -1;
                                        newPos.cFour = 3;
                                        int newCost = curCost + 1000;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.cFour == 3) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftOut = -1;
                                        newPos.cThree = 3;
                                        int newCost = curCost + 900;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.cThree == 3 && cur.cFour == 3) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.leftOut = -1;
                                    newPos.cTwo = 3;
                                    int newCost = curCost + 800;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.cTwo == 3 && cur.cThree == 3 && cur.cFour == 3) {
                                LargeBurrow newPos = cur.clone();
                                newPos.leftOut = -1;
                                newPos.cOne = 3;
                                int newCost = curCost + 700;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.leftOut == 4) { //moves for 4 from leftout
                        if (cur.leftIn == -1 && cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1 && cur.dOne == -1) {
                            if (cur.dTwo == -1) {
                                if (cur.dThree == -1) {
                                    if (cur.dFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftOut = -1;
                                        newPos.dFour = 4;
                                        int newCost = curCost + 12000;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.dFour == 4) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftOut = -1;
                                        newPos.dThree = 4;
                                        int newCost = curCost + 11000;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.dThree == 4 && cur.dFour == 4) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.leftOut = -1;
                                    newPos.dTwo = 4;
                                    int newCost = curCost + 10000;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.dTwo == 4 && cur.dThree == 4 && cur.dFour == 4) {
                                LargeBurrow newPos = cur.clone();
                                newPos.leftOut = -1;
                                newPos.dOne = 4;
                                int newCost = curCost + 9000;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                }
                if (cur.leftIn != -1) { //moves from leftIn
                    if (cur.leftIn == 1) { //moves for 1 from leftIn
                        if (cur.aOne == -1) {
                            if (cur.aTwo == -1) {
                                if (cur.aThree == -1) {
                                    if (cur.aFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftIn = -1;
                                        newPos.aFour = 1;
                                        int newCost = curCost + 5;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.aFour == 1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftIn = -1;
                                        newPos.aThree = 1;
                                        int newCost = curCost + 4;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.aThree == 1 && cur.aFour == 1) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.leftIn = -1;
                                    newPos.aTwo = 1;
                                    int newCost = curCost + 3;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.aTwo == 1 && cur.aThree == 1 && cur.aFour == 1) {
                                LargeBurrow newPos = cur.clone();
                                newPos.leftIn = -1;
                                newPos.aOne = 1;
                                int newCost = curCost + 2;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.leftIn == 2) { //moves for 2 from leftIn
                        if (cur.firstMid == -1 && cur.bOne == -1) {
                            if (cur.bTwo == -1) {
                                if (cur.bThree == -1) {
                                    if (cur.bFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftIn = -1;
                                        newPos.bFour = 2;
                                        int newCost = curCost + 70;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.bFour == 2) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftIn = -1;
                                        newPos.bThree = 2;
                                        int newCost = curCost + 60;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.bThree == 2 && cur.bFour == 2) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.leftIn = -1;
                                    newPos.bTwo = 2;
                                    int newCost = curCost + 50;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.bTwo == 2 && cur.bThree == 2 && cur.bFour == 2) {
                                LargeBurrow newPos = cur.clone();
                                newPos.leftIn = -1;
                                newPos.bOne = 2;
                                int newCost = curCost + 40;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.leftIn == 3) { //moves for 3 from leftIn
                        if (cur.firstMid == -1 && cur.secondMid == -1 && cur.cOne == -1) {
                            if (cur.cTwo == -1) {
                                if (cur.cThree == -1) {
                                    if (cur.cFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftIn = -1;
                                        newPos.cFour = 3;
                                        int newCost = curCost + 900;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.cFour == 3) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftIn = -1;
                                        newPos.cThree = 3;
                                        int newCost = curCost + 800;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.cThree == 3 && cur.cFour == 3) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.leftIn = -1;
                                    newPos.cTwo = 3;
                                    int newCost = curCost + 700;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.cTwo == 3 && cur.cThree == 3 && cur.cFour == 3) {
                                LargeBurrow newPos = cur.clone();
                                newPos.leftIn = -1;
                                newPos.cOne = 3;
                                int newCost = curCost + 600;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.leftIn == 4) { //moves for 4 from leftIn
                        if (cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1 && cur.dOne == -1) {
                            if (cur.dTwo == -1) {
                                if (cur.dThree == -1) {
                                    if (cur.dFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftIn = -1;
                                        newPos.dFour = 4;
                                        int newCost = curCost + 11000;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.dFour == 4) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.leftIn = -1;
                                        newPos.dThree = 4;
                                        int newCost = curCost + 10000;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.dThree == 4 && cur.dFour == 4) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.leftIn = -1;
                                    newPos.dTwo = 4;
                                    int newCost = curCost + 9000;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.dTwo == 4 && cur.dThree == 4 && cur.dFour == 4) {
                                LargeBurrow newPos = cur.clone();
                                newPos.leftIn = -1;
                                newPos.dOne = 4;
                                int newCost = curCost + 8000;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                }
                if (cur.rightOut != -1) { //moves from rightOut
                    if (cur.rightOut == 1) { //moves for 1 from rightOut
                        if (cur.rightIn == -1 && cur.thirdMid == -1 && cur.secondMid == -1 && cur.firstMid == -1 && cur.aOne == -1) {
                            if (cur.aTwo == -1) {
                                if (cur.aThree == -1) {
                                    if (cur.aFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightOut = -1;
                                        newPos.aFour = 1;
                                        int newCost = curCost + 12;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.aFour == 1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightOut = -1;
                                        newPos.aThree = 1;
                                        int newCost = curCost + 11;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.aThree == 1 && cur.aFour == 1) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.rightOut = -1;
                                    newPos.aTwo = 1;
                                    int newCost = curCost + 10;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.aTwo == 1 && cur.aThree == 1 && cur.aFour == 1) {
                                LargeBurrow newPos = cur.clone();
                                newPos.rightOut = -1;
                                newPos.aOne = 1;
                                int newCost = curCost + 9;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.rightOut == 2) { //moves for 2 from rightOut
                        if (cur.rightIn == -1 && cur.thirdMid == -1 && cur.secondMid == -1 && cur.bOne == -1) {
                            if (cur.bTwo == -1) {
                                if (cur.bThree == -1) {
                                    if (cur.bFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightOut = -1;
                                        newPos.bFour = 2;
                                        int newCost = curCost + 100;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.bFour == 2) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightOut = -1;
                                        newPos.bThree = 2;
                                        int newCost = curCost + 90;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.bThree == 2 && cur.bFour == 2) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.rightOut = -1;
                                    newPos.bTwo = 2;
                                    int newCost = curCost + 80;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.bTwo == 2 && cur.bThree == 2 && cur.bFour == 2) {
                                LargeBurrow newPos = cur.clone();
                                newPos.rightOut = -1;
                                newPos.bOne = 2;
                                int newCost = curCost + 70;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.rightOut == 3) { //moves for 3 from rightOut
                        if (cur.rightIn == -1 && cur.thirdMid == -1 && cur.cOne == -1) {
                            if (cur.cTwo == -1) {
                                if (cur.cThree == -1) {
                                    if (cur.cFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightOut = -1;
                                        newPos.cFour = 3;
                                        int newCost = curCost + 800;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.cFour == 3) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightOut = -1;
                                        newPos.cThree = 3;
                                        int newCost = curCost + 700;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.cThree == 3 && cur.cFour == 3) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.rightOut = -1;
                                    newPos.cTwo = 3;
                                    int newCost = curCost + 600;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.cTwo == 3 && cur.cThree == 3 && cur.cFour == 3) {
                                LargeBurrow newPos = cur.clone();
                                newPos.rightOut = -1;
                                newPos.cOne = 3;
                                int newCost = curCost + 500;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.rightOut == 4) { //moves for 4 from rightOut
                        if (cur.rightIn == -1 && cur.dOne == -1) {
                            if (cur.dTwo == -1) {
                                if (cur.dThree == -1) {
                                    if (cur.dFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightOut = -1;
                                        newPos.dFour = 4;
                                        int newCost = curCost + 6000;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.dFour == 4) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightOut = -1;
                                        newPos.dThree = 4;
                                        int newCost = curCost + 5000;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.dThree == 4 && cur.dFour == 4) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.rightOut = -1;
                                    newPos.dTwo = 4;
                                    int newCost = curCost + 4000;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.dTwo == 4 && cur.dThree == 4 && cur.dFour == 4) {
                                LargeBurrow newPos = cur.clone();
                                newPos.rightOut = -1;
                                newPos.dOne = 4;
                                int newCost = curCost + 3000;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                }
                if (cur.rightIn != -1) { //moves from rightIn
                    if (cur.rightIn == 1) { //moves for 1 from rightIn
                        if (cur.thirdMid == -1 && cur.secondMid == -1 && cur.firstMid == -1 && cur.aOne == -1) {
                            if (cur.aTwo == -1) {
                                if (cur.aThree == -1) {
                                    if (cur.aFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightIn = -1;
                                        newPos.aFour = 1;
                                        int newCost = curCost + 11;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.aFour == 1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightIn = -1;
                                        newPos.aThree = 1;
                                        int newCost = curCost + 10;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.aThree == 1 && cur.aFour == 1) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.rightIn = -1;
                                    newPos.aTwo = 1;
                                    int newCost = curCost + 9;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.aTwo == 1 && cur.aThree == 1 && cur.aFour == 1) {
                                LargeBurrow newPos = cur.clone();
                                newPos.rightIn = -1;
                                newPos.aOne = 1;
                                int newCost = curCost + 8;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.rightIn == 2) { //moves for 2 from rightIn
                        if (cur.thirdMid == -1 && cur.secondMid == -1 && cur.bOne == -1) {
                            if (cur.bTwo == -1) {
                                if (cur.bThree == -1) {
                                    if (cur.bFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightIn = -1;
                                        newPos.bFour = 2;
                                        int newCost = curCost + 90;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.bFour == 2) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightIn = -1;
                                        newPos.bThree = 2;
                                        int newCost = curCost + 80;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.bThree == 2 && cur.bFour == 2) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.rightIn = -1;
                                    newPos.bTwo = 2;
                                    int newCost = curCost + 70;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.bTwo == 2 && cur.bThree == 2 && cur.bFour == 2) {
                                LargeBurrow newPos = cur.clone();
                                newPos.rightIn = -1;
                                newPos.bOne = 2;
                                int newCost = curCost + 60;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.rightIn == 3) { //moves for 3 from rightIn
                        if (cur.thirdMid == -1 && cur.cOne == -1) {
                            if (cur.cTwo == -1) {
                                if (cur.cThree == -1) {
                                    if (cur.cFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightIn = -1;
                                        newPos.cFour = 3;
                                        int newCost = curCost + 700;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.cFour == 3) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightIn = -1;
                                        newPos.cThree = 3;
                                        int newCost = curCost + 600;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.cThree == 3 && cur.cFour == 3) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.rightIn = -1;
                                    newPos.cTwo = 3;
                                    int newCost = curCost + 500;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.cTwo == 3 && cur.cThree == 3 && cur.cFour == 3) {
                                LargeBurrow newPos = cur.clone();
                                newPos.rightIn = -1;
                                newPos.cOne = 3;
                                int newCost = curCost + 400;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.rightIn == 4) { //moves for 4 from rightIn
                        if (cur.dOne == -1) {
                            if (cur.dTwo == -1) {
                                if (cur.dThree == -1) {
                                    if (cur.dFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightIn = -1;
                                        newPos.dFour = 4;
                                        int newCost = curCost + 5000;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.dFour == 4) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.rightIn = -1;
                                        newPos.dThree = 4;
                                        int newCost = curCost + 4000;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.dThree == 4 && cur.dFour == 4) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.rightIn = -1;
                                    newPos.dTwo = 4;
                                    int newCost = curCost + 3000;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.dTwo == 4 && cur.dThree == 4 && cur.dFour == 4) {
                                LargeBurrow newPos = cur.clone();
                                newPos.rightIn = -1;
                                newPos.dOne = 4;
                                int newCost = curCost + 2000;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                }
                if (cur.firstMid != -1) { //moves from firstMid
                    if (cur.firstMid == 1) { //moves for 1 from firstMid
                        if (cur.aOne == -1) {
                            if (cur.aTwo == -1) {
                                if (cur.aThree == -1) {
                                    if (cur.aFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.firstMid = -1;
                                        newPos.aFour = 1;
                                        int newCost = curCost + 5;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.aFour == 1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.firstMid = -1;
                                        newPos.aThree = 1;
                                        int newCost = curCost + 4;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.aThree == 1 && cur.aFour == 1) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.firstMid = -1;
                                    newPos.aTwo = 1;
                                    int newCost = curCost + 3;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.aTwo == 1 && cur.aThree == 1 && cur.aFour == 1) {
                                LargeBurrow newPos = cur.clone();
                                newPos.firstMid = -1;
                                newPos.aOne = 1;
                                int newCost = curCost + 2;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.firstMid == 2) { //moves for 2 from firstMid
                        if (cur.bOne == -1) {
                            if (cur.bTwo == -1) {
                                if (cur.bThree == -1) {
                                    if (cur.bFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.firstMid = -1;
                                        newPos.bFour = 2;
                                        int newCost = curCost + 50;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.bFour == 2) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.firstMid = -1;
                                        newPos.bThree = 2;
                                        int newCost = curCost + 40;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.bThree == 2 && cur.bFour == 2) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.firstMid = -1;
                                    newPos.bTwo = 2;
                                    int newCost = curCost + 30;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.bTwo == 2 && cur.bThree == 2 && cur.bFour == 2) {
                                LargeBurrow newPos = cur.clone();
                                newPos.firstMid = -1;
                                newPos.bOne = 2;
                                int newCost = curCost + 20;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.firstMid == 3) { //moves for 3 from firstMid
                        if (cur.secondMid == -1 && cur.cOne == -1) {
                            if (cur.cTwo == -1) {
                                if (cur.cThree == -1) {
                                    if (cur.cFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.firstMid = -1;
                                        newPos.cFour = 3;
                                        int newCost = curCost + 700;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.cFour == 3) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.firstMid = -1;
                                        newPos.cThree = 3;
                                        int newCost = curCost + 600;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.cThree == 3 && cur.cFour == 3) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.firstMid = -1;
                                    newPos.cTwo = 3;
                                    int newCost = curCost + 500;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.cTwo == 3 && cur.cThree == 3 && cur.cFour == 3) {
                                LargeBurrow newPos = cur.clone();
                                newPos.firstMid = -1;
                                newPos.cOne = 3;
                                int newCost = curCost + 400;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.firstMid == 4) { //moves for 4 from firstMid
                        if (cur.secondMid == -1 && cur.thirdMid == -1 && cur.dOne == -1) {
                            if (cur.dTwo == -1) {
                                if (cur.dThree == -1) {
                                    if (cur.dFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.firstMid = -1;
                                        newPos.dFour = 4;
                                        int newCost = curCost + 9000;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.dFour == 4) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.firstMid = -1;
                                        newPos.dThree = 4;
                                        int newCost = curCost + 8000;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.dThree == 4 && cur.dFour == 4) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.firstMid = -1;
                                    newPos.dTwo = 4;
                                    int newCost = curCost + 7000;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.dTwo == 4 && cur.dThree == 4 && cur.dFour == 4) {
                                LargeBurrow newPos = cur.clone();
                                newPos.firstMid = -1;
                                newPos.dOne = 4;
                                int newCost = curCost + 6000;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                }
                if (cur.thirdMid != -1) { //moves from thirdMid
                    if (cur.thirdMid == 1) { //moves for 1 from thirdMid
                        if (cur.secondMid == -1 && cur.firstMid == -1 && cur.aOne == -1) {
                            if (cur.aTwo == -1) {
                                if (cur.aThree == -1) {
                                    if (cur.aFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.thirdMid = -1;
                                        newPos.aFour = 1;
                                        int newCost = curCost + 9;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.aFour == 1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.thirdMid = -1;
                                        newPos.aThree = 1;
                                        int newCost = curCost + 8;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.aThree == 1 && cur.aFour == 1) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.thirdMid = -1;
                                    newPos.aTwo = 1;
                                    int newCost = curCost + 7;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.aTwo == 1 && cur.aThree == 1 && cur.aFour == 1) {
                                LargeBurrow newPos = cur.clone();
                                newPos.thirdMid = -1;
                                newPos.aOne = 1;
                                int newCost = curCost + 6;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.thirdMid == 2) { //moves for 2 from thirdMid
                        if (cur.secondMid == -1 && cur.bOne == -1) {
                            if (cur.bTwo == -1) {
                                if (cur.bThree == -1) {
                                    if (cur.bFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.thirdMid = -1;
                                        newPos.bFour = 2;
                                        int newCost = curCost + 70;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.bFour == 2) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.thirdMid = -1;
                                        newPos.bThree = 2;
                                        int newCost = curCost + 60;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.bThree == 2 && cur.bFour == 2) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.thirdMid = -1;
                                    newPos.bTwo = 2;
                                    int newCost = curCost + 50;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.bTwo == 2 && cur.bThree == 2 && cur.bFour == 2) {
                                LargeBurrow newPos = cur.clone();
                                newPos.thirdMid = -1;
                                newPos.bOne = 2;
                                int newCost = curCost + 40;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.thirdMid == 3) { //moves for 3 from thirdMid
                        if (cur.cOne == -1) {
                            if (cur.cTwo == -1) {
                                if (cur.cThree == -1) {
                                    if (cur.cFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.thirdMid = -1;
                                        newPos.cFour = 3;
                                        int newCost = curCost + 500;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.cFour == 3) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.thirdMid = -1;
                                        newPos.cThree = 3;
                                        int newCost = curCost + 400;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.cThree == 3 && cur.cFour == 3) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.thirdMid = -1;
                                    newPos.cTwo = 3;
                                    int newCost = curCost + 300;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.cTwo == 3 && cur.cThree == 3 && cur.cFour == 3) {
                                LargeBurrow newPos = cur.clone();
                                newPos.thirdMid = -1;
                                newPos.cOne = 3;
                                int newCost = curCost + 200;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.thirdMid == 4) { //moves for 4 from thirdMid
                        if (cur.dOne == -1) {
                            if (cur.dTwo == -1) {
                                if (cur.dThree == -1) {
                                    if (cur.dFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.thirdMid = -1;
                                        newPos.dFour = 4;
                                        int newCost = curCost + 5000;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.dFour == 4) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.thirdMid = -1;
                                        newPos.dThree = 4;
                                        int newCost = curCost + 4000;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.dThree == 4 && cur.dFour == 4) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.thirdMid = -1;
                                    newPos.dTwo = 4;
                                    int newCost = curCost + 3000;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.dTwo == 4 && cur.dThree == 4 && cur.dFour == 4) {
                                LargeBurrow newPos = cur.clone();
                                newPos.thirdMid = -1;
                                newPos.dOne = 4;
                                int newCost = curCost + 2000;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                }
                if (cur.secondMid != -1) { //moves from secondMid
                    if (cur.secondMid == 1) { //moves for 1 from secondMid
                        if (cur.firstMid == -1 && cur.aOne == -1) {
                            if (cur.aTwo == -1) {
                                if (cur.aThree == -1) {
                                    if (cur.aFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.secondMid = -1;
                                        newPos.aFour = 1;
                                        int newCost = curCost + 7;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.aFour == 1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.secondMid = -1;
                                        newPos.aThree = 1;
                                        int newCost = curCost + 6;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.aThree == 1 && cur.aFour == 1) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.secondMid = -1;
                                    newPos.aTwo = 1;
                                    int newCost = curCost + 5;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.aTwo == 1 && cur.aThree == 1 && cur.aFour == 1) {
                                LargeBurrow newPos = cur.clone();
                                newPos.secondMid = -1;
                                newPos.aOne = 1;
                                int newCost = curCost + 4;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.secondMid == 2) { //moves for 2 from secondMid
                        if (cur.bOne == -1) {
                            if (cur.bTwo == -1) {
                                if (cur.bThree == -1) {
                                    if (cur.bFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.secondMid = -1;
                                        newPos.bFour = 2;
                                        int newCost = curCost + 50;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.bFour == 2) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.secondMid = -1;
                                        newPos.bThree = 2;
                                        int newCost = curCost + 40;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.bThree == 2 && cur.bFour == 2) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.secondMid = -1;
                                    newPos.bTwo = 2;
                                    int newCost = curCost + 30;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.bTwo == 2 && cur.bThree == 2 && cur.bFour == 2) {
                                LargeBurrow newPos = cur.clone();
                                newPos.secondMid = -1;
                                newPos.bOne = 2;
                                int newCost = curCost + 20;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.secondMid == 3) { //moves for 3 from secondMid
                        if (cur.cOne == -1) {
                            if (cur.cTwo == -1) {
                                if (cur.cThree == -1) {
                                    if (cur.cFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.secondMid = -1;
                                        newPos.cFour = 3;
                                        int newCost = curCost + 500;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.cFour == 3) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.secondMid = -1;
                                        newPos.cThree = 3;
                                        int newCost = curCost + 400;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.cThree == 3 && cur.cFour == 3) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.secondMid = -1;
                                    newPos.cTwo = 3;
                                    int newCost = curCost + 300;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.cTwo == 3 && cur.cThree == 3 && cur.cFour == 3) {
                                LargeBurrow newPos = cur.clone();
                                newPos.secondMid = -1;
                                newPos.cOne = 3;
                                int newCost = curCost + 200;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                    if (cur.secondMid == 4) { //moves for 4 from secondMid
                        if (cur.thirdMid == -1 && cur.dOne == -1) {
                            if (cur.dTwo == -1) {
                                if (cur.dThree == -1) {
                                    if (cur.dFour == -1) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.secondMid = -1;
                                        newPos.dFour = 4;
                                        int newCost = curCost + 7000;
                                        handleCostL(newPos, newCost);
                                    } else if (cur.dFour == 4) {
                                        LargeBurrow newPos = cur.clone();
                                        newPos.secondMid = -1;
                                        newPos.dThree = 4;
                                        int newCost = curCost + 6000;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.dThree == 4 && cur.dFour == 4) {
                                    LargeBurrow newPos = cur.clone();
                                    newPos.secondMid = -1;
                                    newPos.dTwo = 4;
                                    int newCost = curCost + 5000;
                                    handleCostL(newPos, newCost);
                                }
                            } else if (cur.dTwo == 4 && cur.dThree == 4 && cur.dFour == 4) {
                                LargeBurrow newPos = cur.clone();
                                newPos.secondMid = -1;
                                newPos.dOne = 4;
                                int newCost = curCost + 4000;
                                handleCostL(newPos, newCost);
                            }
                        }
                    }
                }
                for (int depthFromTop = 0; depthFromTop < 4; depthFromTop++) {
                    if ((depthFromTop == 0 && cur.aOne != -1 && !(cur.aOne == 1 && cur.aTwo == 1 && cur.aThree == 1 && cur.aFour == 1)) //moves from a1 & a2
                            || (depthFromTop == 1 && cur.aOne == -1 && cur.aTwo != -1 && !(cur.aTwo == 1 && cur.aThree == 1 && cur.aFour == 1))
                            || (depthFromTop == 2 && cur.aOne == -1 && cur.aTwo == -1 && cur.aThree != -1 && !(cur.aThree == 1 && cur.aFour == 1))
                            || (depthFromTop == 3 && cur.aOne == -1 && cur.aTwo == -1 && cur.aThree == -1 && cur.aFour != 1)) {
                        int compVal;
                        if (depthFromTop == 0) {
                            compVal = cur.aOne;
                        } else if (depthFromTop == 1) {
                            compVal = cur.aTwo;
                        } else if (depthFromTop == 2) {
                            compVal = cur.aThree;
                        } else {
                            compVal = cur.aFour;
                        }
                        int factor = 1;
                        if (compVal == 2) {
                            factor = 10;
                        } else if (compVal == 3) {
                            factor = 100;
                        } else if (compVal == 4) {
                            factor = 1000;
                        }
                        if (cur.leftIn == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.aOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.aTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.aThree = -1;
                            } else {
                                newPos.aFour = -1;
                            }
                            newPos.leftIn = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.leftIn == -1 && cur.leftOut == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.aOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.aTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.aThree = -1;
                            } else {
                                newPos.aFour = -1;
                            }
                            newPos.leftOut = compVal;
                            int newCost = curCost + (3 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.firstMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.aOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.aTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.aThree = -1;
                            } else {
                                newPos.aFour = -1;
                            }
                            newPos.firstMid = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.firstMid == -1 && cur.secondMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.aOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.aTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.aThree = -1;
                            } else {
                                newPos.aFour = -1;
                            }
                            newPos.secondMid = compVal;
                            int newCost = curCost + (4 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.aOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.aTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.aThree = -1;
                            } else {
                                newPos.aFour = -1;
                            }
                            newPos.thirdMid = compVal;
                            int newCost = curCost + (6 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1 && cur.rightIn == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.aOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.aTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.aThree = -1;
                            } else {
                                newPos.aFour = -1;
                            }
                            newPos.rightIn = compVal;
                            int newCost = curCost + (8 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1 && cur.rightIn == -1 && cur.rightOut == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.aOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.aTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.aThree = -1;
                            } else {
                                newPos.aFour = -1;
                            }
                            newPos.rightOut = compVal;
                            int newCost = curCost + (9 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (compVal == 2) { //moves for specifically 2 from a1 & a2
                            if (cur.firstMid == -1 && cur.bOne == -1) {
                                if (cur.bTwo == -1) {
                                    if (cur.bThree == -1) {
                                        if (cur.bFour == -1) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.aOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.aTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.aThree = -1;
                                            } else {
                                                newPos.aFour = -1;
                                            }
                                            newPos.bFour = compVal;
                                            int newCost = curCost + (7 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        } else if (cur.bFour == compVal) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.aOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.aTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.aThree = -1;
                                            } else {
                                                newPos.aFour = -1;
                                            }
                                            newPos.bThree = compVal;
                                            int newCost = curCost + (6 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        }
                                    } else if (cur.bThree == compVal && cur.bFour == compVal) {
                                        LargeBurrow newPos = cur.clone();
                                        if (depthFromTop == 0) {
                                            newPos.aOne = -1;
                                        } else if (depthFromTop == 1) {
                                            newPos.aTwo = -1;
                                        } else if (depthFromTop == 2) {
                                            newPos.aThree = -1;
                                        } else {
                                            newPos.aFour = -1;
                                        }
                                        newPos.bTwo = compVal;
                                        int newCost = curCost + (5 + depthFromTop) * factor;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.bTwo == compVal && cur.bThree == compVal && cur.bFour == compVal) {
                                    LargeBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.aOne = -1;
                                    } else if (depthFromTop == 1) {
                                        newPos.aTwo = -1;
                                    } else if (depthFromTop == 2) {
                                        newPos.aThree = -1;
                                    } else {
                                        newPos.aFour = -1;
                                    }
                                    newPos.bOne = compVal;
                                    int newCost = curCost + (4 + depthFromTop) * factor;
                                    handleCostL(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 3) { //moves for specifically 3 from a1 & a2
                            if (cur.firstMid == -1 && cur.secondMid == -1 && cur.cOne == -1) {
                                if (cur.cTwo == -1) {
                                    if (cur.cThree == -1) {
                                        if (cur.cFour == -1) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.aOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.aTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.aThree = -1;
                                            } else {
                                                newPos.aFour = -1;
                                            }
                                            newPos.cFour = compVal;
                                            int newCost = curCost + (9 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        } else if (cur.cFour == compVal) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.aOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.aTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.aThree = -1;
                                            } else {
                                                newPos.aFour = -1;
                                            }
                                            newPos.cThree = compVal;
                                            int newCost = curCost + (8 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        }
                                    } else if (cur.cThree == compVal && cur.cFour == compVal) {
                                        LargeBurrow newPos = cur.clone();
                                        if (depthFromTop == 0) {
                                            newPos.aOne = -1;
                                        } else if (depthFromTop == 1) {
                                            newPos.aTwo = -1;
                                        } else if (depthFromTop == 2) {
                                            newPos.aThree = -1;
                                        } else {
                                            newPos.aFour = -1;
                                        }
                                        newPos.cTwo = compVal;
                                        int newCost = curCost + (7 + depthFromTop) * factor;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.cTwo == compVal && cur.cThree == compVal && cur.cFour == compVal) {
                                    LargeBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.aOne = -1;
                                    } else if (depthFromTop == 1) {
                                        newPos.aTwo = -1;
                                    } else if (depthFromTop == 2) {
                                        newPos.aThree = -1;
                                    } else {
                                        newPos.aFour = -1;
                                    }
                                    newPos.cOne = compVal;
                                    int newCost = curCost + (6 + depthFromTop) * factor;
                                    handleCostL(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 4) { //moves for specifically 4 from a1 & a2
                            if (cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1 && cur.dOne == -1) {
                                if (cur.dTwo == -1) {
                                    if (cur.dThree == -1) {
                                        if (cur.dFour == -1) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.aOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.aTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.aThree = -1;
                                            } else {
                                                newPos.aFour = -1;
                                            }
                                            newPos.dFour = compVal;
                                            int newCost = curCost + (11 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        } else if (cur.dFour == compVal) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.aOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.aTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.aThree = -1;
                                            } else {
                                                newPos.aFour = -1;
                                            }
                                            newPos.dThree = compVal;
                                            int newCost = curCost + (10 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        }
                                    } else if (cur.dThree == compVal && cur.dFour == compVal) {
                                        LargeBurrow newPos = cur.clone();
                                        if (depthFromTop == 0) {
                                            newPos.aOne = -1;
                                        } else if (depthFromTop == 1) {
                                            newPos.aTwo = -1;
                                        } else if (depthFromTop == 2) {
                                            newPos.aThree = -1;
                                        } else {
                                            newPos.aFour = -1;
                                        }
                                        newPos.dTwo = compVal;
                                        int newCost = curCost + (9 + depthFromTop) * factor;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.dTwo == compVal && cur.dThree == compVal && cur.dFour == compVal) {
                                    LargeBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.aOne = -1;
                                    } else if (depthFromTop == 1) {
                                        newPos.aTwo = -1;
                                    } else if (depthFromTop == 2) {
                                        newPos.aThree = -1;
                                    } else {
                                        newPos.aFour = -1;
                                    }
                                    newPos.dOne = compVal;
                                    int newCost = curCost + (8 + depthFromTop) * factor;
                                    handleCostL(newPos, newCost);
                                }
                            }
                        }
                    }
                }
                for (int depthFromTop = 0; depthFromTop < 4; depthFromTop++) {
                    if ((depthFromTop == 0 && cur.bOne != -1 && !(cur.bOne == 2 && cur.bTwo == 2 && cur.bThree == 2 && cur.bFour == 2)) //moves from b1 & b2
                            || (depthFromTop == 1 && cur.bOne == -1 && cur.bTwo != -1 && !(cur.bTwo == 2 && cur.bThree == 2 && cur.bFour == 2))
                            || (depthFromTop == 2 && cur.bOne == -1 && cur.bTwo == -1 && cur.bThree != -1 && !(cur.bThree == 2 && cur.bFour == 2))
                            || (depthFromTop == 3 && cur.bOne == -1 && cur.bTwo == -1 && cur.bThree == -1 && cur.bFour != 2)) {
                        int compVal;
                        if (depthFromTop == 0) {
                            compVal = cur.bOne;
                        } else if (depthFromTop == 1) {
                            compVal = cur.bTwo;
                        } else if (depthFromTop == 2) {
                            compVal = cur.bThree;
                        } else {
                            compVal = cur.bFour;
                        }
                        int factor = 1;
                        if (compVal == 2) {
                            factor = 10;
                        } else if (compVal == 3) {
                            factor = 100;
                        } else if (compVal == 4) {
                            factor = 1000;
                        }
                        if (cur.leftIn == -1 && cur.firstMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.bOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.bTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.bThree = -1;
                            } else {
                                newPos.bFour = -1;
                            }
                            newPos.leftIn = compVal;
                            int newCost = curCost + (4 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.leftIn == -1 && cur.leftOut == -1 && cur.firstMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.bOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.bTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.bThree = -1;
                            } else {
                                newPos.bFour = -1;
                            }
                            newPos.leftOut = compVal;
                            int newCost = curCost + (5 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.firstMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.bOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.bTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.bThree = -1;
                            } else {
                                newPos.bFour = -1;
                            }
                            newPos.firstMid = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.secondMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.bOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.bTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.bThree = -1;
                            } else {
                                newPos.bFour = -1;
                            }
                            newPos.secondMid = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.secondMid == -1 && cur.thirdMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.bOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.bTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.bThree = -1;
                            } else {
                                newPos.bFour = -1;
                            }
                            newPos.thirdMid = compVal;
                            int newCost = curCost + (4 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.secondMid == -1 && cur.thirdMid == -1 && cur.rightIn == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.bOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.bTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.bThree = -1;
                            } else {
                                newPos.bFour = -1;
                            }
                            newPos.rightIn = compVal;
                            int newCost = curCost + (6 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.secondMid == -1 && cur.thirdMid == -1 && cur.rightIn == -1 && cur.rightOut == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.bOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.bTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.bThree = -1;
                            } else {
                                newPos.bFour = -1;
                            }
                            newPos.rightOut = compVal;
                            int newCost = curCost + (7 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (compVal == 1) { //moves for specifically 1 from b1 & b2
                            if (cur.firstMid == -1 && cur.aOne == -1) {
                                if (cur.aTwo == -1) {
                                    if (cur.aThree == -1) {
                                        if (cur.aFour == -1) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.bOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.bTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.bThree = -1;
                                            } else {
                                                newPos.bFour = -1;
                                            }
                                            newPos.aFour = compVal;
                                            int newCost = curCost + (7 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        } else if (cur.aFour == compVal) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.bOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.bTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.bThree = -1;
                                            } else {
                                                newPos.bFour = -1;
                                            }
                                            newPos.aThree = compVal;
                                            int newCost = curCost + (6 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        }
                                    } else if (cur.aThree == compVal && cur.aFour == compVal) {
                                        LargeBurrow newPos = cur.clone();
                                        if (depthFromTop == 0) {
                                            newPos.bOne = -1;
                                        } else if (depthFromTop == 1) {
                                            newPos.bTwo = -1;
                                        } else if (depthFromTop == 2) {
                                            newPos.bThree = -1;
                                        } else {
                                            newPos.bFour = -1;
                                        }
                                        newPos.aTwo = compVal;
                                        int newCost = curCost + (5 + depthFromTop) * factor;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.aTwo == compVal && cur.aThree == compVal && cur.aFour == compVal) {
                                    LargeBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.bOne = -1;
                                    } else if (depthFromTop == 1) {
                                        newPos.bTwo = -1;
                                    } else if (depthFromTop == 2) {
                                        newPos.bThree = -1;
                                    } else {
                                        newPos.bFour = -1;
                                    }
                                    newPos.aOne = compVal;
                                    int newCost = curCost + (4 + depthFromTop) * factor;
                                    handleCostL(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 3) { //moves for specifically 3 from b1 & b2
                            if (cur.secondMid == -1 && cur.cOne == -1) {
                                if (cur.cTwo == -1) {
                                    if (cur.cThree == -1) {
                                        if (cur.cFour == -1) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.bOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.bTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.bThree = -1;
                                            } else {
                                                newPos.bFour = -1;
                                            }
                                            newPos.cFour = compVal;
                                            int newCost = curCost + (7 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        } else if (cur.cFour == compVal) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.bOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.bTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.bThree = -1;
                                            } else {
                                                newPos.bFour = -1;
                                            }
                                            newPos.cThree = compVal;
                                            int newCost = curCost + (6 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        }
                                    } else if (cur.cThree == compVal && cur.cFour == compVal) {
                                        LargeBurrow newPos = cur.clone();
                                        if (depthFromTop == 0) {
                                            newPos.bOne = -1;
                                        } else if (depthFromTop == 1) {
                                            newPos.bTwo = -1;
                                        } else if (depthFromTop == 2) {
                                            newPos.bThree = -1;
                                        } else {
                                            newPos.bFour = -1;
                                        }
                                        newPos.cTwo = compVal;
                                        int newCost = curCost + (5 + depthFromTop) * factor;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.cTwo == compVal && cur.cThree == compVal && cur.cFour == compVal) {
                                    LargeBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.bOne = -1;
                                    } else if (depthFromTop == 1) {
                                        newPos.bTwo = -1;
                                    } else if (depthFromTop == 2) {
                                        newPos.bThree = -1;
                                    } else {
                                        newPos.bFour = -1;
                                    }
                                    newPos.cOne = compVal;
                                    int newCost = curCost + (4 + depthFromTop) * factor;
                                    handleCostL(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 4) { //moves for specifically 4 from b1 & b2
                            if (cur.secondMid == -1 && cur.thirdMid == -1 && cur.dOne == -1) {
                                if (cur.dTwo == -1) {
                                    if (cur.dThree == -1) {
                                        if (cur.dFour == -1) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.bOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.bTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.bThree = -1;
                                            } else {
                                                newPos.bFour = -1;
                                            }
                                            newPos.dFour = compVal;
                                            int newCost = curCost + (9 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        } else if (cur.dFour == compVal) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.bOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.bTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.bThree = -1;
                                            } else {
                                                newPos.bFour = -1;
                                            }
                                            newPos.dThree = compVal;
                                            int newCost = curCost + (8 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        }
                                    } else if (cur.dThree == compVal && cur.dFour == compVal) {
                                        LargeBurrow newPos = cur.clone();
                                        if (depthFromTop == 0) {
                                            newPos.bOne = -1;
                                        } else if (depthFromTop == 1) {
                                            newPos.bTwo = -1;
                                        } else if (depthFromTop == 2) {
                                            newPos.bThree = -1;
                                        } else {
                                            newPos.bFour = -1;
                                        }
                                        newPos.dTwo = compVal;
                                        int newCost = curCost + (7 + depthFromTop) * factor;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.dTwo == compVal && cur.dThree == compVal && cur.dFour == compVal) {
                                    LargeBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.bOne = -1;
                                    } else if (depthFromTop == 1) {
                                        newPos.bTwo = -1;
                                    } else if (depthFromTop == 2) {
                                        newPos.bThree = -1;
                                    } else {
                                        newPos.bFour = -1;
                                    }
                                    newPos.dOne = compVal;
                                    int newCost = curCost + (6 + depthFromTop) * factor;
                                    handleCostL(newPos, newCost);
                                }
                            }
                        }
                    }
                }
                for (int depthFromTop = 0; depthFromTop < 4; depthFromTop++) {
                    if ((depthFromTop == 0 && cur.cOne != -1 && !(cur.cOne == 3 && cur.cTwo == 3 && cur.cThree == 3 && cur.cFour == 3)) //moves from c1 & c2
                            || (depthFromTop == 1 && cur.cOne == -1 && cur.cTwo != -1 && !(cur.cTwo == 3 && cur.cThree == 3 && cur.cFour == 3))
                            || (depthFromTop == 2 && cur.cOne == -1 && cur.cTwo == -1 && cur.cThree != -1 && !(cur.cThree == 3 && cur.cFour == 3))
                            || (depthFromTop == 3 && cur.cOne == -1 && cur.cTwo == -1 && cur.cThree == -1 && cur.cFour != 3)) {
                        int compVal;
                        if (depthFromTop == 0) {
                            compVal = cur.cOne;
                        } else if (depthFromTop == 1) {
                            compVal = cur.cTwo;
                        } else if (depthFromTop == 2) {
                            compVal = cur.cThree;
                        } else {
                            compVal = cur.cFour;
                        }
                        int factor = 1;
                        if (compVal == 2) {
                            factor = 10;
                        } else if (compVal == 3) {
                            factor = 100;
                        } else if (compVal == 4) {
                            factor = 1000;
                        }
                        if (cur.leftIn == -1 && cur.firstMid == -1 && cur.secondMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.cOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.cTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.cThree = -1;
                            } else {
                                newPos.cFour = -1;
                            }
                            newPos.leftIn = compVal;
                            int newCost = curCost + (6 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.leftIn == -1 && cur.leftOut == -1 && cur.firstMid == -1 && cur.secondMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.cOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.cTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.cThree = -1;
                            } else {
                                newPos.cFour = -1;
                            }
                            newPos.leftOut = compVal;
                            int newCost = curCost + (7 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.firstMid == -1 && cur.secondMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.cOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.cTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.cThree = -1;
                            } else {
                                newPos.cFour = -1;
                            }
                            newPos.firstMid = compVal;
                            int newCost = curCost + (4 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.secondMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.cOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.cTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.cThree = -1;
                            } else {
                                newPos.cFour = -1;
                            }
                            newPos.secondMid = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.thirdMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.cOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.cTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.cThree = -1;
                            } else {
                                newPos.cFour = -1;
                            }
                            newPos.thirdMid = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.thirdMid == -1 && cur.rightIn == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.cOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.cTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.cThree = -1;
                            } else {
                                newPos.cFour = -1;
                            }
                            newPos.rightIn = compVal;
                            int newCost = curCost + (4 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.thirdMid == -1 && cur.rightIn == -1 && cur.rightOut == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.cOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.cTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.cThree = -1;
                            } else {
                                newPos.cFour = -1;
                            }
                            newPos.rightOut = compVal;
                            int newCost = curCost + (5 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (compVal == 1) { //moves for specifically 1 from c1 & c2
                            if (cur.firstMid == -1 && cur.secondMid == -1 && cur.aOne == -1) {
                                if (cur.aTwo == -1) {
                                    if (cur.aThree == -1) {
                                        if (cur.aFour == -1) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.cOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.cTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.cThree = -1;
                                            } else {
                                                newPos.cFour = -1;
                                            }
                                            newPos.aFour = compVal;
                                            int newCost = curCost + (9 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        } else if (cur.aFour == compVal) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.cOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.cTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.cThree = -1;
                                            } else {
                                                newPos.cFour = -1;
                                            }
                                            newPos.aThree = compVal;
                                            int newCost = curCost + (8 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        }
                                    } else if (cur.aThree == compVal && cur.aFour == compVal) {
                                        LargeBurrow newPos = cur.clone();
                                        if (depthFromTop == 0) {
                                            newPos.cOne = -1;
                                        } else if (depthFromTop == 1) {
                                            newPos.cTwo = -1;
                                        } else if (depthFromTop == 2) {
                                            newPos.cThree = -1;
                                        } else {
                                            newPos.cFour = -1;
                                        }
                                        newPos.aTwo = compVal;
                                        int newCost = curCost + (7 + depthFromTop) * factor;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.aTwo == compVal && cur.aThree == compVal && cur.aFour == compVal) {
                                    LargeBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.cOne = -1;
                                    } else if (depthFromTop == 1) {
                                        newPos.cTwo = -1;
                                    } else if (depthFromTop == 2) {
                                        newPos.cThree = -1;
                                    } else {
                                        newPos.cFour = -1;
                                    }
                                    newPos.aOne = compVal;
                                    int newCost = curCost + (6 + depthFromTop) * factor;
                                    handleCostL(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 2) { //moves for specifically 2 from c1 & c2
                            if (cur.secondMid == -1 && cur.bOne == -1) {
                                if (cur.bTwo == -1) {
                                    if (cur.bThree == -1) {
                                        if (cur.bFour == -1) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.cOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.cTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.cThree = -1;
                                            } else {
                                                newPos.cFour = -1;
                                            }
                                            newPos.bFour = compVal;
                                            int newCost = curCost + (7 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        } else if (cur.bFour == compVal) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.cOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.cTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.cThree = -1;
                                            } else {
                                                newPos.cFour = -1;
                                            }
                                            newPos.bThree = compVal;
                                            int newCost = curCost + (6 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        }
                                    } else if (cur.bThree == compVal && cur.bFour == compVal) {
                                        LargeBurrow newPos = cur.clone();
                                        if (depthFromTop == 0) {
                                            newPos.cOne = -1;
                                        } else if (depthFromTop == 1) {
                                            newPos.cTwo = -1;
                                        } else if (depthFromTop == 2) {
                                            newPos.cThree = -1;
                                        } else {
                                            newPos.cFour = -1;
                                        }
                                        newPos.bTwo = compVal;
                                        int newCost = curCost + (5 + depthFromTop) * factor;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.bTwo == compVal && cur.bThree == compVal && cur.bFour == compVal) {
                                    LargeBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.cOne = -1;
                                    } else if (depthFromTop == 1) {
                                        newPos.cTwo = -1;
                                    } else if (depthFromTop == 2) {
                                        newPos.cThree = -1;
                                    } else {
                                        newPos.cFour = -1;
                                    }
                                    newPos.bOne = compVal;
                                    int newCost = curCost + (4 + depthFromTop) * factor;
                                    handleCostL(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 4) { //moves for specifically 4 from c1 & c2
                            if (cur.thirdMid == -1 && cur.dOne == -1) {
                                if (cur.dTwo == -1) {
                                    if (cur.dThree == -1) {
                                        if (cur.dFour == -1) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.cOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.cTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.cThree = -1;
                                            } else {
                                                newPos.cFour = -1;
                                            }
                                            newPos.dFour = compVal;
                                            int newCost = curCost + (7 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        } else if (cur.dFour == compVal) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.cOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.cTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.cThree = -1;
                                            } else {
                                                newPos.cFour = -1;
                                            }
                                            newPos.dThree = compVal;
                                            int newCost = curCost + (6 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        }
                                    } else if (cur.dThree == compVal && cur.dFour == compVal) {
                                        LargeBurrow newPos = cur.clone();
                                        if (depthFromTop == 0) {
                                            newPos.cOne = -1;
                                        } else if (depthFromTop == 1) {
                                            newPos.cTwo = -1;
                                        } else if (depthFromTop == 2) {
                                            newPos.cThree = -1;
                                        } else {
                                            newPos.cFour = -1;
                                        }
                                        newPos.dTwo = compVal;
                                        int newCost = curCost + (5 + depthFromTop) * factor;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.dTwo == compVal && cur.dThree == compVal && cur.dFour == compVal) {
                                    LargeBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.cOne = -1;
                                    } else if (depthFromTop == 1) {
                                        newPos.cTwo = -1;
                                    } else if (depthFromTop == 2) {
                                        newPos.cThree = -1;
                                    } else {
                                        newPos.cFour = -1;
                                    }
                                    newPos.dOne = compVal;
                                    int newCost = curCost + (4 + depthFromTop) * factor;
                                    handleCostL(newPos, newCost);
                                }
                            }
                        }
                    }
                }
                for (int depthFromTop = 0; depthFromTop < 4; depthFromTop++) {
                    if ((depthFromTop == 0 && cur.dOne != -1 && !(cur.dOne == 4 && cur.dTwo == 4 && cur.dThree == 4 && cur.dFour == 4)) //moves from d1 & d2
                            || (depthFromTop == 1 && cur.dOne == -1 && cur.dTwo != -1 && !(cur.dTwo == 4 && cur.dThree == 4 && cur.dFour == 4))
                            || (depthFromTop == 2 && cur.dOne == -1 && cur.dTwo == -1 && cur.dThree != -1 && !(cur.dThree == 4 && cur.dFour == 4))
                            || (depthFromTop == 3 && cur.dOne == -1 && cur.dTwo == -1 && cur.dThree == -1 && cur.dFour != 4)) {
                        int compVal;
                        if (depthFromTop == 0) {
                            compVal = cur.dOne;
                        } else if (depthFromTop == 1) {
                            compVal = cur.dTwo;
                        } else if (depthFromTop == 2) {
                            compVal = cur.dThree;
                        } else {
                            compVal = cur.dFour;
                        }
                        int factor = 1;
                        if (compVal == 2) {
                            factor = 10;
                        } else if (compVal == 3) {
                            factor = 100;
                        } else if (compVal == 4) {
                            factor = 1000;
                        }
                        if (cur.leftIn == -1 && cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.dOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.dTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.dThree = -1;
                            } else {
                                newPos.dFour = -1;
                            }
                            newPos.leftIn = compVal;
                            int newCost = curCost + (8 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.leftIn == -1 && cur.leftOut == -1 && cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.dOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.dTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.dThree = -1;
                            } else {
                                newPos.dFour = -1;
                            }
                            newPos.leftOut = compVal;
                            int newCost = curCost + (9 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.dOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.dTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.dThree = -1;
                            } else {
                                newPos.dFour = -1;
                            }
                            newPos.firstMid = compVal;
                            int newCost = curCost + (6 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.secondMid == -1 && cur.thirdMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.dOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.dTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.dThree = -1;
                            } else {
                                newPos.dFour = -1;
                            }
                            newPos.secondMid = compVal;
                            int newCost = curCost + (4 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.thirdMid == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.dOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.dTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.dThree = -1;
                            } else {
                                newPos.dFour = -1;
                            }
                            newPos.thirdMid = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.toString().equals(stepL13.toString())) {
                            System.out.println("test");
                        }
                        if (cur.rightIn == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.dOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.dTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.dThree = -1;
                            } else {
                                newPos.dFour = -1;
                            }
                            newPos.rightIn = compVal;
                            int newCost = curCost + (2 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (cur.rightIn == -1 && cur.rightOut == -1) {
                            LargeBurrow newPos = cur.clone();
                            if (depthFromTop == 0) {
                                newPos.dOne = -1;
                            } else if (depthFromTop == 1) {
                                newPos.dTwo = -1;
                            } else if (depthFromTop == 2) {
                                newPos.dThree = -1;
                            } else {
                                newPos.dFour = -1;
                            }
                            newPos.rightOut = compVal;
                            int newCost = curCost + (3 + depthFromTop) * factor;
                            handleCostL(newPos, newCost);
                        }
                        if (compVal == 1) { //moves for specifically 1 from d1 & d2
                            if (cur.firstMid == -1 && cur.secondMid == -1 && cur.thirdMid == -1 && cur.aOne == -1) {
                                if (cur.aTwo == -1) {
                                    if (cur.aThree == -1) {
                                        if (cur.aFour == -1) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.dOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.dTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.dThree = -1;
                                            } else {
                                                newPos.dFour = -1;
                                            }
                                            newPos.aFour = compVal;
                                            int newCost = curCost + (11 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        } else if (cur.aFour == compVal) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.dOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.dTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.dThree = -1;
                                            } else {
                                                newPos.dFour = -1;
                                            }
                                            newPos.aThree = compVal;
                                            int newCost = curCost + (10 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        }
                                    } else if (cur.aThree == compVal && cur.aFour == compVal) {
                                        LargeBurrow newPos = cur.clone();
                                        if (depthFromTop == 0) {
                                            newPos.dOne = -1;
                                        } else if (depthFromTop == 1) {
                                            newPos.dTwo = -1;
                                        } else if (depthFromTop == 2) {
                                            newPos.dThree = -1;
                                        } else {
                                            newPos.dFour = -1;
                                        }
                                        newPos.aTwo = compVal;
                                        int newCost = curCost + (9 + depthFromTop) * factor;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.aTwo == compVal && cur.aThree == compVal && cur.aFour == compVal) {
                                    LargeBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.dOne = -1;
                                    } else if (depthFromTop == 1) {
                                        newPos.dTwo = -1;
                                    } else if (depthFromTop == 2) {
                                        newPos.dThree = -1;
                                    } else {
                                        newPos.dFour = -1;
                                    }
                                    newPos.aOne = compVal;
                                    int newCost = curCost + (8 + depthFromTop) * factor;
                                    handleCostL(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 2) { //moves for specifically 2 from d1 & d2
                            if (cur.secondMid == -1 && cur.thirdMid == -1 && cur.bOne == -1) {
                                if (cur.bTwo == -1) {
                                    if (cur.bThree == -1) {
                                        if (cur.bFour == -1) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.dOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.dTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.dThree = -1;
                                            } else {
                                                newPos.dFour = -1;
                                            }
                                            newPos.bFour = compVal;
                                            int newCost = curCost + (9 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        } else if (cur.bFour == compVal) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.dOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.dTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.dThree = -1;
                                            } else {
                                                newPos.dFour = -1;
                                            }
                                            newPos.bThree = compVal;
                                            int newCost = curCost + (8 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        }
                                    } else if (cur.bThree == compVal && cur.bFour == compVal) {
                                        LargeBurrow newPos = cur.clone();
                                        if (depthFromTop == 0) {
                                            newPos.dOne = -1;
                                        } else if (depthFromTop == 1) {
                                            newPos.dTwo = -1;
                                        } else if (depthFromTop == 2) {
                                            newPos.dThree = -1;
                                        } else {
                                            newPos.dFour = -1;
                                        }
                                        newPos.bTwo = compVal;
                                        int newCost = curCost + (7 + depthFromTop) * factor;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.bTwo == compVal && cur.bThree == compVal && cur.bFour == compVal) {
                                    LargeBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.dOne = -1;
                                    } else if (depthFromTop == 1) {
                                        newPos.dTwo = -1;
                                    } else if (depthFromTop == 2) {
                                        newPos.dThree = -1;
                                    } else {
                                        newPos.dFour = -1;
                                    }
                                    newPos.bOne = compVal;
                                    int newCost = curCost + (6 + depthFromTop) * factor;
                                    handleCostL(newPos, newCost);
                                }
                            }
                        }
                        if (compVal == 3) { //moves for specifically 3 from d1 & d2
                            if (cur.thirdMid == -1 && cur.cOne == -1) {
                                if (cur.cTwo == -1) {
                                    if (cur.cThree == -1) {
                                        if (cur.cFour == -1) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.dOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.dTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.dThree = -1;
                                            } else {
                                                newPos.dFour = -1;
                                            }
                                            newPos.cFour = compVal;
                                            int newCost = curCost + (7 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        } else if (cur.cFour == compVal) {
                                            LargeBurrow newPos = cur.clone();
                                            if (depthFromTop == 0) {
                                                newPos.dOne = -1;
                                            } else if (depthFromTop == 1) {
                                                newPos.dTwo = -1;
                                            } else if (depthFromTop == 2) {
                                                newPos.dThree = -1;
                                            } else {
                                                newPos.dFour = -1;
                                            }
                                            newPos.cThree = compVal;
                                            int newCost = curCost + (6 + depthFromTop) * factor;
                                            handleCostL(newPos, newCost);
                                        }
                                    } else if (cur.cThree == compVal && cur.cFour == compVal) {
                                        LargeBurrow newPos = cur.clone();
                                        if (depthFromTop == 0) {
                                            newPos.dOne = -1;
                                        } else if (depthFromTop == 1) {
                                            newPos.dTwo = -1;
                                        } else if (depthFromTop == 2) {
                                            newPos.dThree = -1;
                                        } else {
                                            newPos.dFour = -1;
                                        }
                                        newPos.cTwo = compVal;
                                        int newCost = curCost + (5 + depthFromTop) * factor;
                                        handleCostL(newPos, newCost);
                                    }
                                } else if (cur.cTwo == compVal && cur.cThree == compVal && cur.cFour == compVal) {
                                    LargeBurrow newPos = cur.clone();
                                    if (depthFromTop == 0) {
                                        newPos.dOne = -1;
                                    } else if (depthFromTop == 1) {
                                        newPos.dTwo = -1;
                                    } else if (depthFromTop == 2) {
                                        newPos.dThree = -1;
                                    } else {
                                        newPos.dFour = -1;
                                    }
                                    newPos.cOne = compVal;
                                    int newCost = curCost + (4 + depthFromTop) * factor;
                                    handleCostL(newPos, newCost);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(posCost.get(endString));
    }

    private static void handleCostS(SmallBurrow newPos, int newCost) {
        if (!posCost.containsKey(newPos.toString())) {
            positionsS.add(newPos);
            posCost.put(newPos.toString(), newCost);
        } else if (posCost.get(newPos.toString()) > newCost) {
            posCost.put(newPos.toString(), newCost);
        }
    }

    private static void handleCostL(LargeBurrow newPos, int newCost) {
        /*SmallBurrow temp = new SmallBurrow(-1, -1, -1, -1, -1, -1, 1, 2, 1, -1, 2, 3, 3, 4, 4);
        if (newPos.toString().equals(temp.toString()) && newCost == 12473) {
            System.out.println(newCost);
        }*/
        if (!posCost.containsKey(newPos.toString())) {
            positionsL.add(newPos);
            posCost.put(newPos.toString(), newCost);
        } else if (posCost.get(newPos.toString()) > newCost) {
            posCost.put(newPos.toString(), newCost);
        }
    }
}

class SmallBurrow {
    int leftOut;
    int leftIn;
    int rightOut;
    int rightIn;
    int firstMid;
    int secondMid;
    int thirdMid;
    int aOne;
    int aTwo;
    int bOne;
    int bTwo;
    int cOne;
    int cTwo;
    int dOne;
    int dTwo;

    public SmallBurrow(int a1, int a2, int b1, int b2, int c1, int c2, int d1, int d2) {
        leftOut = -1;
        leftIn = -1;
        rightOut = -1;
        rightIn = -1;
        firstMid = -1;
        secondMid = -1;
        thirdMid = -1;
        aOne = a1;
        aTwo = a2;
        bOne = b1;
        bTwo = b2;
        cOne = c1;
        cTwo = c2;
        dOne = d1;
        dTwo = d2;
    }

    public SmallBurrow(int lo, int li, int ro, int ri, int fm, int sm, int tm, int a1, int a2, int b1, int b2, int c1, int c2, int d1, int d2) {
        leftOut = lo;
        leftIn = li;
        rightOut = ro;
        rightIn = ri;
        firstMid = fm;
        secondMid = sm;
        thirdMid = tm;
        aOne = a1;
        aTwo = a2;
        bOne = b1;
        bTwo = b2;
        cOne = c1;
        cTwo = c2;
        dOne = d1;
        dTwo = d2;
    }

    public String toString() {
        return "" + leftOut + "," + leftIn + "," + rightOut + "," + rightIn + "," + firstMid + "," + secondMid + "," + thirdMid + "," + aOne + "," + aTwo + "," + bOne + "," + bTwo + "," + cOne + "," + cTwo + "," + dOne + "," + dTwo;
    }
    
    public SmallBurrow clone() {
        return new SmallBurrow(leftOut, leftIn, rightOut, rightIn, firstMid, secondMid, thirdMid, aOne, aTwo, bOne, bTwo, cOne, cTwo, dOne, dTwo);
    }
}

class LargeBurrow {
    int leftOut;
    int leftIn;
    int rightOut;
    int rightIn;
    int firstMid;
    int secondMid;
    int thirdMid;
    int aOne;
    int aTwo;
    int aThree;
    int aFour;
    int bOne;
    int bTwo;
    int bThree;
    int bFour;
    int cOne;
    int cTwo;
    int cThree;
    int cFour;
    int dOne;
    int dTwo;
    int dThree;
    int dFour;

    public LargeBurrow(int a1, int a2, int a3, int a4, int b1, int b2, int b3, int b4, int c1, int c2, int c3, int c4, int d1, int d2, int d3, int d4) {
        leftOut = -1;
        leftIn = -1;
        rightOut = -1;
        rightIn = -1;
        firstMid = -1;
        secondMid = -1;
        thirdMid = -1;
        aOne = a1;
        aTwo = a2;
        aThree = a3;
        aFour = a4;
        bOne = b1;
        bTwo = b2;
        bThree = b3;
        bFour = b4;
        cOne = c1;
        cTwo = c2;
        cThree = c3;
        cFour = c4;
        dOne = d1;
        dTwo = d2;
        dThree = d3;
        dFour = d4;
    }

    public LargeBurrow(int lo, int li, int ro, int ri, int fm, int sm, int tm, int a1, int a2, int a3, int a4, int b1, int b2, int b3, int b4, int c1, int c2, int c3, int c4, int d1, int d2, int d3, int d4) {
        leftOut = lo;
        leftIn = li;
        rightOut = ro;
        rightIn = ri;
        firstMid = fm;
        secondMid = sm;
        thirdMid = tm;
        aOne = a1;
        aTwo = a2;
        aThree = a3;
        aFour = a4;
        bOne = b1;
        bTwo = b2;
        bThree = b3;
        bFour = b4;
        cOne = c1;
        cTwo = c2;
        cThree = c3;
        cFour = c4;
        dOne = d1;
        dTwo = d2;
        dThree = d3;
        dFour = d4;
    }

    public String toString() {
        return "" + leftOut + "," + leftIn + "," + rightOut + "," + rightIn + "," + firstMid + "," + secondMid + "," + thirdMid + "," + aOne + "," + aTwo + "," + aThree + "," + aFour + "," + bOne + "," + bTwo + "," + bThree + "," + bFour + "," + cOne + "," + cTwo + "," + cThree + "," + cFour + "," + dOne + "," + dTwo + "," + dThree + "," + dFour;
    }

    public LargeBurrow clone() {
        return new LargeBurrow(leftOut, leftIn, rightOut, rightIn, firstMid, secondMid, thirdMid, aOne, aTwo, aThree, aFour, bOne, bTwo, bThree, bFour, cOne, cTwo, cThree, cFour, dOne, dTwo, dThree, dFour);
    }
}
