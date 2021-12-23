package year2021.code;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Question22 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2021/input/Question22.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        int[][] values = new int[input.length][7];
        for (int i = 0; i < input.length; i++) {
            String[] mainParts = input[i].split(" ");
            values[i][0] = mainParts[0].equals("on") ? 1 : 0;
            String[] coors = mainParts[1].split(",");
            String[] xcoorParts = coors[0].split("=");
            String[] ycoorParts = coors[1].split("=");
            String[] zcoorParts = coors[2].split("=");
            String[] xcoorValues = xcoorParts[1].split("\\.\\.");
            String[] ycoorValues = ycoorParts[1].split("\\.\\.");
            String[] zcoorValues = zcoorParts[1].split("\\.\\.");
            values[i][1] = Integer.parseInt(xcoorValues[0]);
            values[i][2] = Integer.parseInt(xcoorValues[1]);
            values[i][3] = Integer.parseInt(ycoorValues[0]);
            values[i][4] = Integer.parseInt(ycoorValues[1]);
            values[i][5] = Integer.parseInt(zcoorValues[0]);
            values[i][6] = Integer.parseInt(zcoorValues[1]);
        }
        boolean[][][] region = new boolean[101][101][101];
        for (int i = 0; i < values.length; i++) {
            if (values[i][1] >= -50 && values[i][1] <= 50) {
                for (int x = values[i][1] + 50; x <= values[i][2] + 50; x++) {
                    for (int y = values[i][3] + 50; y <= values[i][4] + 50; y++) {
                        for (int z = values[i][5] + 50; z <= values[i][6] + 50; z++) {
                            region[x][y][z] = values[i][0] == 1;
                        }
                    }
                }
            }
        }
        int count = 0;
        for (int x = 0; x < 101; x++) {
            for (int y = 0; y < 101; y++) {
                for (int z = 0; z < 101; z++) {
                    if (region[x][y][z]) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }

    private static void part2(String[] input) {
        Cuboid[] values = new Cuboid[input.length];
        for (int i = 0; i < input.length; i++) {
            String[] mainParts = input[i].split(" ");
            boolean on = mainParts[0].equals("on");
            String[] coors = mainParts[1].split(",");
            String[] xcoorParts = coors[0].split("=");
            String[] ycoorParts = coors[1].split("=");
            String[] zcoorParts = coors[2].split("=");
            String[] xcoorValues = xcoorParts[1].split("\\.\\.");
            String[] ycoorValues = ycoorParts[1].split("\\.\\.");
            String[] zcoorValues = zcoorParts[1].split("\\.\\.");
            int x1 = Integer.parseInt(xcoorValues[0]);
            int x2 = Integer.parseInt(xcoorValues[1]);
            int y1 = Integer.parseInt(ycoorValues[0]);
            int y2 = Integer.parseInt(ycoorValues[1]);
            int z1 = Integer.parseInt(zcoorValues[0]);
            int z2 = Integer.parseInt(zcoorValues[1]);
            values[i] = new Cuboid(x1, x2, y1, y2, z1, z2, on);
        }
        ArrayList<Cuboid> cuboidsPresent = new ArrayList<>();
        int startI = 0;
        while(!values[startI].on) {
            startI++;
        }
        cuboidsPresent.add(values[startI]);
        for (int inputIndex = startI + 1; inputIndex < values.length; inputIndex++) {
            //if (values[inputIndex].x1 >= -50 && values[inputIndex].x1 <= 50) {
                if (values[inputIndex].on) {
                    ArrayList<Cuboid> cuboidsToBeAdded = new ArrayList<>();
                    cuboidsToBeAdded.add(values[inputIndex]);
                    for (int presentIndex = 0; presentIndex < cuboidsPresent.size(); presentIndex++) {
                        for (int toBeAddedIndex = 0; toBeAddedIndex < cuboidsToBeAdded.size(); toBeAddedIndex++) {
                            Cuboid cuboidPresent = cuboidsPresent.get(presentIndex);
                            Cuboid cuboidToBeAdded = cuboidsToBeAdded.get(toBeAddedIndex);
                            if (cuboidToBeAdded.x1 >= cuboidPresent.x1 && cuboidToBeAdded.x2 <= cuboidPresent.x2 && cuboidToBeAdded.y1 >= cuboidPresent.y1 && cuboidToBeAdded.y2 <= cuboidPresent.y2 && cuboidToBeAdded.z1 >= cuboidPresent.z1 && cuboidToBeAdded.z2 <= cuboidPresent.z2) {
                                cuboidsToBeAdded.remove(toBeAddedIndex);
                                toBeAddedIndex--;
                            } else if (cuboidToBeAdded.x1 <= cuboidPresent.x1 && cuboidToBeAdded.x2 >= cuboidPresent.x2 && cuboidToBeAdded.y1 <= cuboidPresent.y1 && cuboidToBeAdded.y2 >= cuboidPresent.y2 && cuboidToBeAdded.z1 <= cuboidPresent.z1 && cuboidToBeAdded.z2 >= cuboidPresent.z2) {
                                cuboidsPresent.remove(presentIndex);
                                presentIndex--;
                            } else if (!(cuboidToBeAdded.x2 < cuboidPresent.x1 || cuboidToBeAdded.x1 > cuboidPresent.x2 || cuboidToBeAdded.y2 < cuboidPresent.y1 || cuboidToBeAdded.y1 > cuboidPresent.y2 || cuboidToBeAdded.z2 < cuboidPresent.z1 || cuboidToBeAdded.z1 > cuboidPresent.z2)) {
                                cuboidsToBeAdded.remove(toBeAddedIndex);
                                ArrayList<Cuboid> cuboidsToBeInsertedInToBeAdded = new ArrayList<>();
                                //add cuboids to insertlist
                                if (cuboidToBeAdded.x1 < cuboidPresent.x1) {
                                    cuboidsToBeInsertedInToBeAdded.add(new Cuboid(cuboidToBeAdded.x1, cuboidPresent.x1 - 1, cuboidToBeAdded.y1, cuboidToBeAdded.y2, cuboidToBeAdded.z1, cuboidToBeAdded.z2));
                                }
                                if (cuboidToBeAdded.x2 > cuboidPresent.x2) {
                                    cuboidsToBeInsertedInToBeAdded.add(new Cuboid(cuboidPresent.x2 + 1, cuboidToBeAdded.x2, cuboidToBeAdded.y1, cuboidToBeAdded.y2, cuboidToBeAdded.z1, cuboidToBeAdded.z2));
                                }
                                if (cuboidToBeAdded.y1 < cuboidPresent.y1) {
                                    cuboidsToBeInsertedInToBeAdded.add(new Cuboid(cuboidToBeAdded.x1, cuboidToBeAdded.x2, cuboidToBeAdded.y1, cuboidPresent.y1 - 1, cuboidToBeAdded.z1, cuboidToBeAdded.z2));
                                }
                                if (cuboidToBeAdded.y2 > cuboidPresent.y2) {
                                    cuboidsToBeInsertedInToBeAdded.add(new Cuboid(cuboidToBeAdded.x1, cuboidToBeAdded.x2, cuboidPresent.y2 + 1, cuboidToBeAdded.y2, cuboidToBeAdded.z1, cuboidToBeAdded.z2));
                                }
                                if (cuboidToBeAdded.z1 < cuboidPresent.z1) {
                                    cuboidsToBeInsertedInToBeAdded.add(new Cuboid(cuboidToBeAdded.x1, cuboidToBeAdded.x2, cuboidToBeAdded.y1, cuboidToBeAdded.y2, cuboidToBeAdded.z1, cuboidPresent.z1 - 1));
                                }
                                if (cuboidToBeAdded.z2 > cuboidPresent.z2) {
                                    cuboidsToBeInsertedInToBeAdded.add(new Cuboid(cuboidToBeAdded.x1, cuboidToBeAdded.x2, cuboidToBeAdded.y1, cuboidToBeAdded.y2, cuboidPresent.z2 + 1, cuboidToBeAdded.z2));
                                }
                                ArrayList<Cuboid> finalCuboidsToBeInsertedInToBeAdded = new ArrayList<>();
                                for (Cuboid insCuboid : cuboidsToBeInsertedInToBeAdded) {
                                    if (finalCuboidsToBeInsertedInToBeAdded.isEmpty()) {
                                        finalCuboidsToBeInsertedInToBeAdded.add(insCuboid);
                                    } else {
                                        for (Cuboid inFinalListCuboid : finalCuboidsToBeInsertedInToBeAdded) {
                                            if (inFinalListCuboid.x1 == insCuboid.x1 && inFinalListCuboid.x2 == insCuboid.x2) {
                                                if (inFinalListCuboid.y1 == insCuboid.y1 && inFinalListCuboid.y2 < insCuboid.y2) {
                                                    insCuboid.y1 = inFinalListCuboid.y2 + 1;
                                                } else if (inFinalListCuboid.y2 == insCuboid.y2 && inFinalListCuboid.y1 > insCuboid.y1) {
                                                    insCuboid.y2 = inFinalListCuboid.y1 - 1;
                                                } else if (inFinalListCuboid.z1 == insCuboid.z1 && inFinalListCuboid.z2 < insCuboid.z2) {
                                                    insCuboid.z1 = inFinalListCuboid.z2 + 1;
                                                } else if (inFinalListCuboid.z2 == insCuboid.z2 && inFinalListCuboid.z1 > insCuboid.z1) {
                                                    insCuboid.z2 = inFinalListCuboid.z1 - 1;
                                                }
                                            } else if (inFinalListCuboid.y1 == insCuboid.y1 && inFinalListCuboid.y2 == insCuboid.y2) {
                                                if (inFinalListCuboid.x1 == insCuboid.x1 && inFinalListCuboid.x2 < insCuboid.x2) {
                                                    insCuboid.x1 = inFinalListCuboid.x2 + 1;
                                                } else if (inFinalListCuboid.x2 == insCuboid.x2 && inFinalListCuboid.x1 > insCuboid.x1) {
                                                    insCuboid.x2 = inFinalListCuboid.x1 - 1;
                                                } else if (inFinalListCuboid.z1 == insCuboid.z1 && inFinalListCuboid.z2 < insCuboid.z2) {
                                                    insCuboid.z1 = inFinalListCuboid.z2 + 1;
                                                } else if (inFinalListCuboid.z2 == insCuboid.z2 && inFinalListCuboid.z1 > insCuboid.z1) {
                                                    insCuboid.z2 = inFinalListCuboid.z1 - 1;
                                                }
                                            } else if (inFinalListCuboid.z1 == insCuboid.z1 && inFinalListCuboid.z2 == insCuboid.z2) {
                                                if (inFinalListCuboid.x1 == insCuboid.x1 && inFinalListCuboid.x2 < insCuboid.x2) {
                                                    insCuboid.x1 = inFinalListCuboid.x2 + 1;
                                                } else if (inFinalListCuboid.x2 == insCuboid.x2 && inFinalListCuboid.x1 > insCuboid.x1) {
                                                    insCuboid.x2 = inFinalListCuboid.x1 - 1;
                                                } else if (inFinalListCuboid.y1 == insCuboid.y1 && inFinalListCuboid.y2 < insCuboid.y2) {
                                                    insCuboid.y1 = inFinalListCuboid.y2 + 1;
                                                } else if (inFinalListCuboid.y2 == insCuboid.y2 && inFinalListCuboid.y1 > insCuboid.y1) {
                                                    insCuboid.y2 = inFinalListCuboid.y1 - 1;
                                                }
                                            }
                                        }
                                        finalCuboidsToBeInsertedInToBeAdded.add(insCuboid);
                                    }
                                }

                                for (Cuboid cuboidToBeInserted : finalCuboidsToBeInsertedInToBeAdded) {
                                    cuboidsToBeAdded.add(toBeAddedIndex, cuboidToBeInserted);
                                    toBeAddedIndex++;
                                }
                                toBeAddedIndex--;
                            }
                            //check what overlaps. if exactly overlaps, or larger than on all sides: remove currentcuboid (don't forget to decrease presentIndex), else:
                            //if nothing overlaps, do nothing
                            //if something overlaps, take apart current cuboidToBeAdded (first step, removing it from cuboidsToBeAdded), get smaller cuboids that do not overlap, and add those back in the list at toBeAddedIndex, increasing the index each time
                        }
                    }
                    for (Cuboid addCuboid : cuboidsToBeAdded) {
                        cuboidsPresent.add(addCuboid);
                    }
                } else {
                    Cuboid cuboidNeg = values[inputIndex];
                    for (int presentIndex = 0; presentIndex < cuboidsPresent.size(); presentIndex++) {
                        Cuboid cuboidPresent = cuboidsPresent.get(presentIndex);
                        if (cuboidNeg.x1 <= cuboidPresent.x1 && cuboidNeg.x2 >= cuboidPresent.x2 && cuboidNeg.y1 <= cuboidPresent.y1 && cuboidNeg.y2 >= cuboidPresent.y2 && cuboidNeg.z1 <= cuboidPresent.z1 && cuboidNeg.z2 >= cuboidPresent.z2) {
                            cuboidsPresent.remove(presentIndex);
                            presentIndex--;
                        } else if (!(cuboidNeg.x1 > cuboidPresent.x2 || cuboidNeg.x2 < cuboidPresent.x1 || cuboidNeg.y1 > cuboidPresent.y2 || cuboidNeg.y2 < cuboidPresent.y1 || cuboidNeg.z1 > cuboidPresent.z2 || cuboidNeg.z2 < cuboidPresent.z1)) {
                            cuboidsPresent.remove(presentIndex);
                            ArrayList<Cuboid> cuboidsToBeReinserted = new ArrayList<>();
                            if (cuboidPresent.x1 < cuboidNeg.x1) {
                                cuboidsToBeReinserted.add(new Cuboid(cuboidPresent.x1, cuboidNeg.x1 - 1, cuboidPresent.y1, cuboidPresent.y2, cuboidPresent.z1, cuboidPresent.z2));
                            }
                            if (cuboidPresent.x2 > cuboidNeg.x2) {
                                cuboidsToBeReinserted.add(new Cuboid(cuboidNeg.x2 + 1, cuboidPresent.x2, cuboidPresent.y1, cuboidPresent.y2, cuboidPresent.z1, cuboidPresent.z2));
                            }
                            if (cuboidPresent.y1 < cuboidNeg.y1) {
                                cuboidsToBeReinserted.add(new Cuboid(cuboidPresent.x1, cuboidPresent.x2, cuboidPresent.y1, cuboidNeg.y1 - 1, cuboidPresent.z1, cuboidPresent.z2));
                            }
                            if (cuboidPresent.y2 > cuboidNeg.y2) {
                                cuboidsToBeReinserted.add(new Cuboid(cuboidPresent.x1, cuboidPresent.x2, cuboidNeg.y2 + 1, cuboidPresent.y2, cuboidPresent.z1, cuboidPresent.z2));
                            }
                            if (cuboidPresent.z1 < cuboidNeg.z1) {
                                cuboidsToBeReinserted.add(new Cuboid(cuboidPresent.x1, cuboidPresent.x2, cuboidPresent.y1, cuboidPresent.y2, cuboidPresent.z1, cuboidNeg.z1 - 1));
                            }
                            if (cuboidPresent.z2 > cuboidNeg.z2) {
                                cuboidsToBeReinserted.add(new Cuboid(cuboidPresent.x1, cuboidPresent.x2, cuboidPresent.y1, cuboidPresent.y2, cuboidNeg.z2 + 1, cuboidPresent.z2));
                            }
                            ArrayList<Cuboid> finalCuboidsToBeInsertedInToBeAdded = new ArrayList<>();
                            for (Cuboid insCuboid : cuboidsToBeReinserted) {
                                if (finalCuboidsToBeInsertedInToBeAdded.isEmpty()) {
                                    finalCuboidsToBeInsertedInToBeAdded.add(insCuboid);
                                } else {
                                    for (Cuboid inFinalListCuboid : finalCuboidsToBeInsertedInToBeAdded) {
                                        if (inFinalListCuboid.x1 == insCuboid.x1 && inFinalListCuboid.x2 == insCuboid.x2) {
                                            if (inFinalListCuboid.y1 == insCuboid.y1 && inFinalListCuboid.y2 < insCuboid.y2) {
                                                insCuboid.y1 = inFinalListCuboid.y2 + 1;
                                            } else if (inFinalListCuboid.y2 == insCuboid.y2 && inFinalListCuboid.y1 > insCuboid.y1) {
                                                insCuboid.y2 = inFinalListCuboid.y1 - 1;
                                            } else if (inFinalListCuboid.z1 == insCuboid.z1 && inFinalListCuboid.z2 < insCuboid.z2) {
                                                insCuboid.z1 = inFinalListCuboid.z2 + 1;
                                            } else if (inFinalListCuboid.z2 == insCuboid.z2 && inFinalListCuboid.z1 > insCuboid.z1) {
                                                insCuboid.z2 = inFinalListCuboid.z1 - 1;
                                            }
                                        } else if (inFinalListCuboid.y1 == insCuboid.y1 && inFinalListCuboid.y2 == insCuboid.y2) {
                                            if (inFinalListCuboid.x1 == insCuboid.x1 && inFinalListCuboid.x2 < insCuboid.x2) {
                                                insCuboid.x1 = inFinalListCuboid.x2 + 1;
                                            } else if (inFinalListCuboid.x2 == insCuboid.x2 && inFinalListCuboid.x1 > insCuboid.x1) {
                                                insCuboid.x2 = inFinalListCuboid.x1 - 1;
                                            } else if (inFinalListCuboid.z1 == insCuboid.z1 && inFinalListCuboid.z2 < insCuboid.z2) {
                                                insCuboid.z1 = inFinalListCuboid.z2 + 1;
                                            } else if (inFinalListCuboid.z2 == insCuboid.z2 && inFinalListCuboid.z1 > insCuboid.z1) {
                                                insCuboid.z2 = inFinalListCuboid.z1 - 1;
                                            }
                                        } else if (inFinalListCuboid.z1 == insCuboid.z1 && inFinalListCuboid.z2 == insCuboid.z2) {
                                            if (inFinalListCuboid.x1 == insCuboid.x1 && inFinalListCuboid.x2 < insCuboid.x2) {
                                                insCuboid.x1 = inFinalListCuboid.x2 + 1;
                                            } else if (inFinalListCuboid.x2 == insCuboid.x2 && inFinalListCuboid.x1 > insCuboid.x1) {
                                                insCuboid.x2 = inFinalListCuboid.x1 - 1;
                                            } else if (inFinalListCuboid.y1 == insCuboid.y1 && inFinalListCuboid.y2 < insCuboid.y2) {
                                                insCuboid.y1 = inFinalListCuboid.y2 + 1;
                                            } else if (inFinalListCuboid.y2 == insCuboid.y2 && inFinalListCuboid.y1 > insCuboid.y1) {
                                                insCuboid.y2 = inFinalListCuboid.y1 - 1;
                                            }
                                        }
                                    }
                                    finalCuboidsToBeInsertedInToBeAdded.add(insCuboid);
                                }
                            }

                            for (Cuboid cuboidToBeInserted : finalCuboidsToBeInsertedInToBeAdded) {
                                cuboidsPresent.add(presentIndex, cuboidToBeInserted);
                                presentIndex++;
                            }
                            presentIndex--;
                        }
                        //check what overlaps. If exactly overlaps, or larger than on all sides: remove currentcuboid (don't forget to decrease presentIndex), else:
                        //if nothing overlaps, do nothing
                        //if something overlaps, take apart present cuboid (first step, removing it from cuboidsPresent), get smaller cuboids that do not overlap, then add those back in the list at presentIndex, increasing the index each time
                    }
                }
            //}
        }
        long count = 0;
        for (Cuboid cuboid : cuboidsPresent) {
            count += (1L + cuboid.x2 - cuboid.x1) * (1L + cuboid.y2 - cuboid.y1) * (1L + cuboid.z2 - cuboid.z1);
        }
        System.out.println(count);
    }
}

class Cuboid {
    boolean on;
    int x1;
    int x2;
    int y1;
    int y2;
    int z1;
    int z2;

    public Cuboid(int x1New, int x2New, int y1New, int y2New, int z1New, int z2New) {
        x1 = x1New;
        x2 = x2New;
        y1 = y1New;
        y2 = y2New;
        z1 = z1New;
        z2 = z2New;
        on = true;
    }

    public Cuboid(int x1New, int x2New, int y1New, int y2New, int z1New, int z2New, boolean onNew) {
        x1 = x1New;
        x2 = x2New;
        y1 = y1New;
        y2 = y2New;
        z1 = z1New;
        z2 = z2New;
        on = onNew;
    }
}
