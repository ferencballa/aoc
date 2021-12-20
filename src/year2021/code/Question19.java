package year2021.code;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Question19 {
    private static ArrayList<ArrayList<Position>> scanners;
    private static HashMap<Integer, HashMap<Integer, ScannerDifference>> relativeScannersPerScanner;
    private static HashSet<Integer> visitedScanners;

    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2021/input/Question19.txt")).toArray(new String[0]);
        //System.out.println("Part 1:");
        //part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        visitedScanners = new HashSet<>();
        scanners = new ArrayList<>();
        ArrayList<Position> scannerHelper = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            String str = input[i];
            if (str.isEmpty()) {
                scanners.add((ArrayList<Position>)scannerHelper.clone());
                scannerHelper = new ArrayList<>();
            } else if(str.substring(0, 2).matches("-?[0-9,]+")) {
                String[] line = str.split(",");
                scannerHelper.add(new Position(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2])));
            }
        }
        scanners.add(scannerHelper);
        for (ArrayList<Position> scanner : scanners) {
            for (int i = 0; i < scanner.size(); i++) {
                for (int j = 0; j < scanner.size(); j++) {
                    if (i != j) {
                        Position position = scanner.get(i);
                        Position otherPosition = scanner.get(j);
                        position.neighbours.add(new RelativePosition(otherPosition.x - position.x, otherPosition.y - position.y, otherPosition.z - position.z, otherPosition));
                    }
                }
            }
        }
        relativeScannersPerScanner = new HashMap<>();
        for (int scannerIndex1 = 0; scannerIndex1 < scanners.size() - 1; scannerIndex1++) {
            ArrayList<Position> scanner1 = scanners.get(scannerIndex1);
            for (int scannerIndex2 = scannerIndex1 + 1; scannerIndex2 < scanners.size(); scannerIndex2++) {
                //if (scannerIndex1 == 23 || scannerIndex2 == 23) {
                System.out.println("Comparing scanner " + scannerIndex1 + " to scanner " + scannerIndex2);
                ArrayList<Position> scanner2 = scanners.get(scannerIndex2);
                searchOverlap:
                for (int rotationIndex = 0; rotationIndex < 24; rotationIndex++) {
                    for (int beacon1Index = 0; beacon1Index < scanner1.size(); beacon1Index++) {
                        for (int beacon2Index = 0; beacon2Index < scanner2.size(); beacon2Index++) {
                            Position beacon1 = scanner1.get(beacon1Index);
                            Position beacon2 = scanner2.get(beacon2Index);
                            int matchingDelta = 1;
                            Coordinate3D deltaCoor = null;
                            for (int beacon1Neighbour = 0; beacon1Neighbour < beacon1.neighbours.size(); beacon1Neighbour++) {
                                for (int beacon2Neighbour = 0; beacon2Neighbour < beacon2.neighbours.size(); beacon2Neighbour++) {
                                    RelativePosition neighbour1 = beacon1.neighbours.get(beacon1Neighbour);
                                    RelativePosition neighbour2 = beacon2.neighbours.get(beacon2Neighbour);
                                    Coordinate3D beacon2NeighbourTransformedCoors = transformCoordinate(new Position(neighbour2.dx, neighbour2.dy, neighbour2.dz), rotationIndex);
                                    if (neighbour1.dx == beacon2NeighbourTransformedCoors.x && neighbour1.dy == beacon2NeighbourTransformedCoors.y && neighbour1.dz == beacon2NeighbourTransformedCoors.z) {
                                        matchingDelta++;
                                        if (deltaCoor == null) {
                                            Coordinate3D transformedBeacon2 = transformCoordinate(beacon2, rotationIndex);
                                            deltaCoor = new Coordinate3D(beacon1.x - transformedBeacon2.x, beacon1.y - transformedBeacon2.y, beacon1.z - transformedBeacon2.z);
                                        }
                                    }
                                }
                            }
                            if ((scannerIndex1 == 23 || scannerIndex2 == 23) && matchingDelta > 1) {
                                System.out.println(matchingDelta);
                            }
                            if (matchingDelta > 11) {
                                if (!relativeScannersPerScanner.containsKey(scannerIndex1)) {
                                    HashMap<Integer, ScannerDifference> relativeScanners = new HashMap<>();
                                    relativeScannersPerScanner.put(scannerIndex1, relativeScanners);
                                }
                                relativeScannersPerScanner.get(scannerIndex1).put(scannerIndex2, new ScannerDifference(rotationIndex, deltaCoor));
                                if (!relativeScannersPerScanner.containsKey(scannerIndex2)) {
                                    HashMap<Integer, ScannerDifference> relativeScanners = new HashMap<>();
                                    relativeScannersPerScanner.put(scannerIndex2, relativeScanners);
                                }
                                Coordinate3D negativeDeltaCoor = transformCoordinate(new Position(-deltaCoor.x, -deltaCoor.y, -deltaCoor.z), getReverseTransform(rotationIndex));
                                relativeScannersPerScanner.get(scannerIndex2).put(scannerIndex1, new ScannerDifference(getReverseTransform(rotationIndex), negativeDeltaCoor));
                                break searchOverlap;
                            }
                        }
                    }
                    //}
                }
            }
        }
        HashSet<String> beacons = getBeaconsFromScanner(0, 0);
        ArrayList<String> printList = new ArrayList<>(beacons);
        printList.sort((String str1, String str2) -> {return Coordinate3D.fromString(str1).x > Coordinate3D.fromString(str2).x ? 1 : -1;});
        /*for (String s : printList) {
            System.out.println(s);
        }*/
        System.out.println(beacons.size());
    }

    private static void part2(String[] input) {
        visitedScanners = new HashSet<>();
        scanners = new ArrayList<>();
        ArrayList<Position> scannerHelper = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            String str = input[i];
            if (str.isEmpty()) {
                scanners.add((ArrayList<Position>)scannerHelper.clone());
                scannerHelper = new ArrayList<>();
            } else if(str.substring(0, 2).matches("-?[0-9,]+")) {
                String[] line = str.split(",");
                scannerHelper.add(new Position(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2])));
            }
        }
        scanners.add(scannerHelper);
        for (ArrayList<Position> scanner : scanners) {
            for (int i = 0; i < scanner.size(); i++) {
                for (int j = 0; j < scanner.size(); j++) {
                    if (i != j) {
                        Position position = scanner.get(i);
                        Position otherPosition = scanner.get(j);
                        position.neighbours.add(new RelativePosition(otherPosition.x - position.x, otherPosition.y - position.y, otherPosition.z - position.z, otherPosition));
                    }
                }
            }
        }
        relativeScannersPerScanner = new HashMap<>();
        for (int scannerIndex1 = 0; scannerIndex1 < scanners.size() - 1; scannerIndex1++) {
            ArrayList<Position> scanner1 = scanners.get(scannerIndex1);
            for (int scannerIndex2 = scannerIndex1 + 1; scannerIndex2 < scanners.size(); scannerIndex2++) {
                //if (scannerIndex1 == 23 || scannerIndex2 == 23) {
                System.out.println("Comparing scanner " + scannerIndex1 + " to scanner " + scannerIndex2);
                ArrayList<Position> scanner2 = scanners.get(scannerIndex2);
                searchOverlap:
                for (int rotationIndex = 0; rotationIndex < 24; rotationIndex++) {
                    for (int beacon1Index = 0; beacon1Index < scanner1.size(); beacon1Index++) {
                        for (int beacon2Index = 0; beacon2Index < scanner2.size(); beacon2Index++) {
                            Position beacon1 = scanner1.get(beacon1Index);
                            Position beacon2 = scanner2.get(beacon2Index);
                            int matchingDelta = 1;
                            Coordinate3D deltaCoor = null;
                            for (int beacon1Neighbour = 0; beacon1Neighbour < beacon1.neighbours.size(); beacon1Neighbour++) {
                                for (int beacon2Neighbour = 0; beacon2Neighbour < beacon2.neighbours.size(); beacon2Neighbour++) {
                                    RelativePosition neighbour1 = beacon1.neighbours.get(beacon1Neighbour);
                                    RelativePosition neighbour2 = beacon2.neighbours.get(beacon2Neighbour);
                                    Coordinate3D beacon2NeighbourTransformedCoors = transformCoordinate(new Position(neighbour2.dx, neighbour2.dy, neighbour2.dz), rotationIndex);
                                    if (neighbour1.dx == beacon2NeighbourTransformedCoors.x && neighbour1.dy == beacon2NeighbourTransformedCoors.y && neighbour1.dz == beacon2NeighbourTransformedCoors.z) {
                                        matchingDelta++;
                                        if (deltaCoor == null) {
                                            Coordinate3D transformedBeacon2 = transformCoordinate(beacon2, rotationIndex);
                                            deltaCoor = new Coordinate3D(beacon1.x - transformedBeacon2.x, beacon1.y - transformedBeacon2.y, beacon1.z - transformedBeacon2.z);
                                        }
                                    }
                                }
                            }
                            if ((scannerIndex1 == 23 || scannerIndex2 == 23) && matchingDelta > 1) {
                                System.out.println(matchingDelta);
                            }
                            if (matchingDelta > 11) {
                                if (!relativeScannersPerScanner.containsKey(scannerIndex1)) {
                                    HashMap<Integer, ScannerDifference> relativeScanners = new HashMap<>();
                                    relativeScannersPerScanner.put(scannerIndex1, relativeScanners);
                                }
                                relativeScannersPerScanner.get(scannerIndex1).put(scannerIndex2, new ScannerDifference(rotationIndex, deltaCoor));
                                if (!relativeScannersPerScanner.containsKey(scannerIndex2)) {
                                    HashMap<Integer, ScannerDifference> relativeScanners = new HashMap<>();
                                    relativeScannersPerScanner.put(scannerIndex2, relativeScanners);
                                }
                                Coordinate3D negativeDeltaCoor = transformCoordinate(new Position(-deltaCoor.x, -deltaCoor.y, -deltaCoor.z), getReverseTransform(rotationIndex));
                                relativeScannersPerScanner.get(scannerIndex2).put(scannerIndex1, new ScannerDifference(getReverseTransform(rotationIndex), negativeDeltaCoor));
                                break searchOverlap;
                            }
                        }
                    }
                    //}
                }
            }
        }
        /*HashSet<String> beacons = getBeaconsFromScanner(0, 0);
        ArrayList<String> printList = new ArrayList<>(beacons);
        printList.sort((String str1, String str2) -> {return Coordinate3D.fromString(str1).x > Coordinate3D.fromString(str2).x ? 1 : -1;});
        *//*for (String s : printList) {
            System.out.println(s);
        }*//*
        System.out.println(beacons.size());*/

        HashSet<String> scanners = getScannersLocations(0, 0);
        ArrayList<String> scannerList = new ArrayList<>(scanners);
        int largestManhat = 0;
        for (int i = 0; i < scannerList.size() - 1; i++) {
            Coordinate3D coors1 = Coordinate3D.fromString(scannerList.get(i));
            for (int j = i + 1; j < scannerList.size(); j++) {
                Coordinate3D coors2 = Coordinate3D.fromString(scannerList.get(j));
                int dx = coors1.x > coors2.x ? coors1.x - coors2.x : coors2.x - coors1.x;
                int dy = coors1.y > coors2.y ? coors1.y - coors2.y : coors2.y - coors1.y;
                int dz = coors1.z > coors2.z ? coors1.z - coors2.z : coors2.z - coors1.z;
                largestManhat = Math.max(largestManhat, dx + dy + dz);
            }
        }
        System.out.println(largestManhat);
    }

    private static Coordinate3D transformCoordinate(Position pos, int rotation) {
        switch (rotation) {
            case 0:
                return new Coordinate3D(pos.x, pos.y, pos.z);
            case 1:
                return new Coordinate3D(pos.x, pos.z, -pos.y);
            case 2:
                return new Coordinate3D(pos.x, -pos.y, -pos.z);
            case 3:
                return new Coordinate3D(pos.x, -pos.z, pos.y);

            case 4:
                return new Coordinate3D(pos.y, -pos.x, pos.z);
            case 5:
                return new Coordinate3D(pos.y, pos.z, pos.x);
            case 6:
                return new Coordinate3D(pos.y, pos.x, -pos.z);
            case 7:
                return new Coordinate3D(pos.y, -pos.z, -pos.x);

            case 8:
                return new Coordinate3D(-pos.x, -pos.y, pos.z);
            case 9:
                return new Coordinate3D(-pos.x, pos.z, pos.y);
            case 10:
                return new Coordinate3D(-pos.x, pos.y, -pos.z);
            case 11:
                return new Coordinate3D(-pos.x, -pos.z, -pos.y);

            case 12:
                return new Coordinate3D(-pos.y, pos.x, pos.z);
            case 13:
                return new Coordinate3D(-pos.y, pos.z, -pos.x);
            case 14:
                return new Coordinate3D(-pos.y, -pos.x, -pos.z);
            case 15:
                return new Coordinate3D(-pos.y, -pos.z, pos.x);

            case 16:
                return new Coordinate3D(pos.z, pos.y, -pos.x);
            case 17:
                return new Coordinate3D(pos.z, -pos.x, -pos.y);
            case 18:
                return new Coordinate3D(pos.z, -pos.y, pos.x);
            case 19:
                return new Coordinate3D(pos.z, pos.x, pos.y);

            case 20:
                return new Coordinate3D(-pos.z, pos.y, pos.x);
            case 21:
                return new Coordinate3D(-pos.z, pos.x, -pos.y);
            case 22:
                return new Coordinate3D(-pos.z, -pos.y, -pos.x);
            case 23:
                return new Coordinate3D(-pos.z, -pos.x, pos.y);
        }
        System.out.println("invalid rotation");
        return null;
    }

    private static int getReverseTransform(int i) {
        switch (i) {
            case 0:
                return 0;
            case 1:
                return 3;
            case 2:
                return 2;
            case 3:
                return 1;

            case 4:
                return 12;
            case 5:
                return 19;
            case 6:
                return 6;
            case 7:
                return 21;

            case 8:
                return 8;
            case 9:
                return 9;
            case 10:
                return 10;
            case 11:
                return 11;

            case 12:
                return 4;
            case 13:
                return 23;
            case 14:
                return 14;
            case 15:
                return 17;

            case 16:
                return 20;
            case 17:
                return 15;
            case 18:
                return 18;
            case 19:
                return 5;

            case 20:
                return 16;
            case 21:
                return 7;
            case 22:
                return 22;
            case 23:
                return 13;
        }
        System.out.println("invalid rotation");
        return -1;
    }

    private static HashSet<String> getBeaconsFromScanner(int index, int rotation) {
        visitedScanners.add(index);
        HashSet<String> beacons = new HashSet<>();
        ArrayList<Position> scanner = scanners.get(index);
        for (Position position : scanner) {
            beacons.add(transformCoordinate(position, rotation).toString());
        }
        HashMap<Integer, ScannerDifference> relativeScanners = relativeScannersPerScanner.get(index);
        if (relativeScanners != null) {
            for (Integer key : relativeScanners.keySet()) {
                if (!visitedScanners.contains(key)) {
                    ScannerDifference scannerDifference = relativeScanners.get(key);
                    Coordinate3D delta = scannerDifference.delta;
                    HashSet<String> relativeBeacons = getBeaconsFromScanner(key, scannerDifference.rotation);
                    for (String beacon : relativeBeacons) {
                        Coordinate3D coordinates = Coordinate3D.fromString(beacon);
                        beacons.add(transformCoordinate(new Position(coordinates.x + delta.x, coordinates.y + delta.y, coordinates.z + delta.z), rotation).toString());
                    }
                }
            }
        }
        return beacons;
    }

    private static HashSet<String> getScannersLocations(int index, int rotation) {
        visitedScanners.add(index);
        HashSet<String> scanners = new HashSet<>();
        scanners.add("0,0,0");
        HashMap<Integer, ScannerDifference> relativeScanners = relativeScannersPerScanner.get(index);
        if (relativeScanners != null) {
            for (Integer key : relativeScanners.keySet()) {
                if (!visitedScanners.contains(key)) {
                    ScannerDifference scannerDifference = relativeScanners.get(key);
                    Coordinate3D delta = scannerDifference.delta;
                    HashSet<String> relativeScannersRet = getScannersLocations(key, scannerDifference.rotation);
                    for (String scan : relativeScannersRet) {
                        Coordinate3D coordinates = Coordinate3D.fromString(scan);
                        scanners.add(transformCoordinate(new Position(coordinates.x + delta.x, coordinates.y + delta.y, coordinates.z + delta.z), rotation).toString());
                    }
                }
            }
        }
        return scanners;
    }
}

class Coordinate3D {
    int x;
    int y;
    int z;

    public Coordinate3D(int xNew, int yNew, int zNew) {
        x = xNew;
        y = yNew;
        z = zNew;
    }

    public String toString() {
        return x + "," + y + "," + z;
    }

    public static Coordinate3D fromString(String str) {
        String[] values = str.split(",");
        return new Coordinate3D(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
    }
}

class Position {
    int x;
    int y;
    int z;
    ArrayList<RelativePosition> neighbours;

    public Position(int xNew, int yNew, int zNew) {
        x = xNew;
        y = yNew;
        z = zNew;
        neighbours = new ArrayList<>();
    }
}

class RelativePosition {
    int dx;
    int dy;
    int dz;
    Position position;

    public RelativePosition(int x, int y, int z, Position pos) {
        dx = x;
        dy = y;
        dz = z;
        position = pos;
    }
}

class ScannerDifference {
    int rotation;
    Coordinate3D delta;

    public ScannerDifference(int r, Coordinate3D d) {
        rotation = r;
        delta = d;
    }
}
